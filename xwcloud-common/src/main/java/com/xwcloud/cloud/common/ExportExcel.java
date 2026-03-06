package com.xwcloud.cloud.common;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.*;

import javax.servlet.http.HttpServletResponse;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.*;

public class ExportExcel {
    /**
     * Excel表格导出
     * @param response HttpServletResponse对象
     * @param excelData Excel表格的数据，封装为List<List<String>>
     * @param sheetName sheet的名字
     * @param fileName 导出Excel的文件名
     * @param columnWidth Excel表格的宽度，建议为15
     * @throws IOException 抛IO异常
     */
    public static void exportExcel(HttpServletResponse response,
                                   List<List<Object>> excelData,
                                   String sheetName,
                                   String fileName,
                                   int columnWidth) throws IOException {

        //声明一个工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();

        //生成一个表格，设置表格名称
        HSSFSheet sheet = workbook.createSheet(sheetName);

        //设置表格列宽度
        sheet.setDefaultColumnWidth(columnWidth);

        //写入List<List<String>>中的数据
        int rowIndex = 0;
        for(List<Object> data : excelData){
            //创建一个row行，然后自增1
            HSSFRow row = sheet.createRow(rowIndex++);

            //遍历添加本行数据
            for (int i = 0; i < data.size(); i++) {
                //创建一个单元格
                HSSFCell cell = row.createCell(i);
                if (data.get(i) instanceof byte[]) {
                    //如果数据是一个二进制
                    HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
                    HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 1023, 255, (short) i, rowIndex-1, (short) i, rowIndex-1);
                    anchor.setAnchorType(ClientAnchor.AnchorType.MOVE_AND_RESIZE);
                    patriarch.createPicture(anchor, workbook.addPicture((byte[]) data.get(i), HSSFWorkbook.PICTURE_TYPE_JPEG));

                }else {
                    HSSFRichTextString text = new HSSFRichTextString(String.valueOf(data.get(i)));
                    //将内容对象的文字内容写入到单元格中
                    cell.setCellValue(text);
                }

            }
        }

        //准备将Excel的输出流通过response输出到页面下载
        //八进制输出流
        response.setContentType("application/octet-stream");

        //设置导出Excel的名称
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);

        //刷新缓冲
        response.flushBuffer();

        //workbook将Excel写入到response的输出流中，供页面下载该Excel文件
        workbook.write(response.getOutputStream());

        //关闭workbook
        workbook.close();
    }

    /**
     * 导出多个excel
     * @param response
     * @param excelData
     * @param fileName
     * @param columnWidth
     * @throws IOException
     */
    public static void exportMultipleSheetExcel(HttpServletResponse response,
                                   List<ExcelSource> excelData,
                                   String fileName,
                                   int columnWidth) throws IOException {

        //声明一个工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        for (ExcelSource _sheet:excelData) {
            //生成一个表格，设置表格名称
            HSSFSheet sheet = workbook.createSheet(_sheet.getSheetName());
            //设置表格列宽度
            sheet.setDefaultColumnWidth(columnWidth);
            //写入List<List<String>>中的数据
            int rowIndex = 0;
            List<List<Object>> _excelData = _sheet.tableData;
            for(List<Object> data : _excelData){
                //创建一个row行，然后自增1
                HSSFRow row = sheet.createRow(rowIndex++);

                //遍历添加本行数据
                for (int i = 0; i < data.size(); i++) {
                    //创建一个单元格
                    HSSFCell cell = row.createCell(i);
                    if (data.get(i) instanceof byte[]) {
                        //如果数据是一个二进制
                        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
                        HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 1023, 255, (short) i, rowIndex-1, (short) i, rowIndex-1);
                        anchor.setAnchorType(ClientAnchor.AnchorType.MOVE_AND_RESIZE);
                        patriarch.createPicture(anchor, workbook.addPicture((byte[]) data.get(i), HSSFWorkbook.PICTURE_TYPE_JPEG));

                    }else {
                        HSSFRichTextString text = new HSSFRichTextString(String.valueOf(data.get(i)));
                        //将内容对象的文字内容写入到单元格中
                        cell.setCellValue(text);
                    }

                }
            }
        }
        //准备将Excel的输出流通过response输出到页面下载
        //八进制输出流
        response.setContentType("application/octet-stream");

        //设置导出Excel的名称
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);

        //刷新缓冲
        response.flushBuffer();

        //workbook将Excel写入到response的输出流中，供页面下载该Excel文件
        workbook.write(response.getOutputStream());

        //关闭workbook
        workbook.close();
    }

    /**
     *
     * @param ExcelName 表格名称
     * @param ModelURl 模板路径
     * @param dataSource 数据源（list<map<String,string>> 格式)
     * @param columnsOrder 列的顺序 按照一定的顺序写入表格
     * @param response
     *  下面的参数暂时没用
     * @param sheetNames sheet的名字
     * @param keyNames
     * @param startRow
     * @throws Exception
     */
    public static void ExcelByModel(String ExcelName, String ModelURl, List<Map<String,String>> dataSource,List<String> columnsOrder, HttpServletResponse response, String[] sheetNames, String[] keyNames, int startRow) throws Exception {

        /*
        // 设置导出Excel报表的导出形式
        //response.setContentType("application/vnd.ms-excel;charset=GBK");
        // 设置导出Excel报表的响应文件名
        String fileName = new String(ExcelName.getBytes(), "iso8859-1");
        response.setContentType("application/OCTET-STREAM");
        response.addHeader("Content-Disposition", "attachment;filename="+fileName+".xls");
        */
        //response.setContentType("application/vnd.ms-excel;charset=GBK");

        //OutputStream os = response.getOutputStream();// 取得输出流

        response.reset();// 清空输出流
        //八进制输出流
        //response.setContentType("application/octet-stream");
        response.setContentType("application/vnd.ms-excel;charset=GBK");
        response.setHeader("Content-disposition", "attachment; filename="
                + URLEncoder.encode(ExcelName, "UTF-8"));

        // 设置导出Excel报表的响应文件名
        FileInputStream fis = new FileInputStream(ModelURl);
        //XSSFWorkbook workBook = new XSSFWorkbook(fis);
        Workbook workBook = WorkbookFactory.create(fis);

        // 进行模板的克隆(接下来的操作都是针对克隆后的sheet)

        Sheet sheet = workBook.cloneSheet(0);
        workBook.setSheetName(0, "sheet01"); // 给sheet命名
        sheet.autoSizeColumn(1);


        Row rowCellStyle1 =sheet.getRow(startRow);//sheet页的第二行
        CellStyle columnOne01 = rowCellStyle1.getCell(0).getCellStyle();//获取sheet页第二行的样式
        int rowCount = sheet.getPhysicalNumberOfRows();
        //int cellCount = sheet.getRow(0).getPhysicalNumberOfCells();
        for (int j = 0; j <dataSource.size(); j++) {
            Row row;
            if((j+startRow)>=(rowCount-2)){
                row = sheet.createRow(j+startRow);
                Map<String,String> item = dataSource.get(j);
                for(int k=0;k<columnsOrder.size();k++){
                    Cell cell = row.createCell(k);
                    cell.setCellValue(String.valueOf(item.get(columnsOrder.get(k))));
                    cell.setCellStyle(columnOne01);
                }
            }else{
                row = sheet.getRow(j+startRow);
                Map<String,String> item = dataSource.get(j);
                for(int k=0;k<columnsOrder.size();k++){
                    Cell cell = row.getCell(k);
                    cell.setCellValue(String.valueOf(item.get(columnsOrder.get(k))));
                    cell.setCellStyle(columnOne01);
                }
            }


        }

        // 输出为一个新的Excel，也就是动态修改完之后的excel
        OutputStream out = new FileOutputStream(""  + ExcelName);
        workBook.removeSheetAt(0); // 移除workbook中的模板sheet
        workBook.write(out);

        response.flushBuffer();
        workBook.write(response.getOutputStream());
        workBook.close();
//        ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
//        workBook.write(outByteStream);
//        byte[] outArray = outByteStream.toByteArray();
//        response.setContentLength(outArray.length);
//        os.write(outArray);

        fis.close();
        out.flush();
        out.close();

    }

    public static void replaceCellValue(Cell cell, Object value) {
        String val = value != null ? String.valueOf(value) : "";
        cell.setCellValue(val);
    }

    /**
     * 格式化数据为导出的格式
     * @param tableHeads 表头
     * @param objList   数据
     * @param fieldNames  需要读出的属性
     * @return
     */
    public static  List<List<Object>> formatDataToList(String[] tableHeads, List objList, String[] fieldNames){
        List<List<Object>> rowList =new ArrayList<>();// 定义列数据
        List<Object> heads = Arrays.asList(tableHeads);
        rowList.add(heads);
        for (Object obj:objList) {
            // 获取数据中的对象为一行
            List<Object> line =new ArrayList<>();//定义行数据
            // 获取对象的中的指定属性值为每行单元格的数据值
            for (int i = 0; i < fieldNames.length; i++) {
                Object val = getFieldValueByName(fieldNames[i],obj,null );
                line.add(val);
            }
            rowList.add(line);
        }
        return rowList;
    }

    /**
     * 格式化数据为导出的格式
     * @param tableHeads 表头
     * @param objList   数据
     * @param fieldNames  需要读出的属性
     * @return
     */
    public static  List<List<Object>> formatHashMapDataToList(String[] tableHeads, List<HashMap<String,Object>> objList, String[] fieldNames){
        List<List<Object>> rowList =new ArrayList<>();// 定义列数据
        List<Object> heads = Arrays.asList(tableHeads);
        rowList.add(heads);
        for (HashMap<String,Object> obj:objList) {
            // 获取数据中的对象为一行
            List<Object> line =new ArrayList<>();//定义行数据
            // 获取对象的中的指定属性值为每行单元格的数据值
            for (int i = 0; i < fieldNames.length; i++) {
                String val = String.valueOf(obj.get(fieldNames[i]));
                line.add(val);
            }
            rowList.add(line);
        }
        return rowList;
    }


    /**
     * 格式化数据为导出的格式
     * @param tableHeads 表头
     * @param objList   数据
     * @param fieldNames  需要读出的属性
     * @return
     */
    public static  List<List<Object>> formatDataToList(String[] tableHeads, List objList, String[] fieldNames,HashMap<String,Map> Case){
        List<List<Object>> rowList =new ArrayList<>();// 定义列数据
        List<Object> heads = Arrays.asList(tableHeads);
        rowList.add(heads);
        for (Object obj:objList) {
            // 获取数据中的对象为一行
            List<Object> line =new ArrayList<>();//定义行数据
            // 获取对象的中的指定属性值为每行单元格的数据值
            for (int i = 0; i < fieldNames.length; i++) {
                Object val = getFieldValueByName(fieldNames[i],obj,Case );
                line.add(val);
            }
            rowList.add(line);
        }
        return rowList;
    }

    /**
     * 根据属性名称获取属性值
     * @param fieldName  需要转换时间格式化的属性名后面加-D (-D:yyyy-MM-dd -T:HH:mm:ss -DT:yyyy-MM-dd HH:mm:ss)
     *                   如:fieldName-D
     * @param o
     * @return
     */
    private static Object getFieldValueByName(String fieldName, Object o,HashMap<String,Map> Case) {
        try {
            String[] fieldFormat=null;
            if (fieldName.contains("-")){
                fieldFormat=fieldName.split("-");
                fieldName=fieldFormat[0];
            }
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[] {});
            Object value = method.invoke(o, new Object[] {});// 获取到值
            if (fieldFormat!=null&&fieldFormat.length>1){
                switch (fieldFormat[1]){
                    case "D":
                        value=DateUtil.formatDate((Date) value,"yyyy-MM-dd");
                        break;
                    case "T":
                        value=DateUtil.formatDate((Date) value,"HH:mm:ss");
                        break;
                    case "DT":
                        value=DateUtil.formatDate((Date) value,"yyyy-MM-dd HH:mm:ss");
                        break;
                    case "Case": // 如果是替换型数据,就找map中的数据来替换
                        if (Case==null||fieldFormat.length<3){
                            throw new Exception("没有设置替换字符");
                        }
                        Map casemap= Case.get(fieldFormat[2]);
                        value=casemap.get(value);
                        break;
                }
            }
            if (value instanceof  Boolean){
                value=(boolean)value==true?"是":"否";
            }
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static class  ExcelSource{
        private String sheetName;
        private List<List<Object>> tableData;

        public String getSheetName() {
            return sheetName;
        }

        public void setSheetName(String sheetName) {
            this.sheetName = sheetName;
        }

        public List<List<Object>> getTableData() {
            return tableData;
        }

        public void setTableData(List<List<Object>> tableData) {
            this.tableData = tableData;
        }
    }
}
