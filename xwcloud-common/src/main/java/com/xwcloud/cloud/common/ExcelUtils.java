package com.xwcloud.cloud.common;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExcelUtils {

    public static void exportExcelByModel(String ExcelName, String ModelURl, List<Map<String, String>> dataSource, List<String> columnsOrder, HttpServletResponse response, String[] sheetNames, String[] keyNames, int startRow) {
        try {
            ExportExcel.ExcelByModel(ExcelName, ModelURl, dataSource, columnsOrder, response, sheetNames, keyNames, startRow);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * 导出多个工作簿
     * @param response
     * @param excelData
     * @param fileName
     * @param columnWidth
     */
    public static void exportMultipleSheetExcel(HttpServletResponse response,
                                                List<ExportExcel.ExcelSource> excelData,
                                                String fileName,
                                                int columnWidth) {
        try {
            ExportExcel.exportMultipleSheetExcel(response, excelData, fileName, columnWidth);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void exportExcel(HttpServletResponse response,
                                   List<List<Object>> excelData,
                                   String sheetName,
                                   String fileName,
                                   int columnWidth) {
        try {
            ExportExcel.exportExcel(response, excelData, sheetName, fileName, columnWidth);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 导入Excel表格,并且根据指定类型进行映射数据填充
     * @param clazz 需要映射的类class
     * @param fieldNames 需要转换类型的属性名后面加-D
     *                  如:fieldName-D
     *                   -D: Date
     *                   -B: Boolean
     *                   -I: Long||Int
     *                   -F: Double||float
     *                   默认: String
     * @param excelPath excel文件路径
     * @param cellNum 需要读的列数
     * @param <T>  使用方法参考底部  main 测试调用
     * @return
     * @throws Exception
     */
    public static <T> List<T> importExcel(Class<T> clazz, String[] fieldNames, String excelPath,int cellNum) throws Exception {
        List<T> tList = new ArrayList<>();
        // 获取表格中的数据
        String[] fieldNamesClone=fieldNames.clone();
        List<List<Object>> cellList=getExcelData(excelPath,fieldNames,cellNum,fieldNamesClone);
        for (List<Object> list : cellList) { // 遍历行数据并且进行映射
            T obj = createObj(clazz, fieldNamesClone, list);
            tList.add(obj);
        }
        return tList;
    }

    /**
     * 获取表格中的数据,并且根据属性类型进行类型映射
     * @param excelPath
     * @param fieldNames 需要转换类型的属性名后面加-D
     *                  如:fieldName-D
     *                   -D: Date
     *                   -B: Boolean
     *                   -I: Long||Int
     *                   -F: Double||float
     *                   默认: String
     * @return
     * @throws Exception
     */
    public static List<List<Object>> getExcelData(String excelPath,String[] fieldNames,int cellNum,String[] outFieldNames) throws Exception {
        List<List<Object>> lists =new ArrayList<>();

        File excel = new File(excelPath);
        if (!excel.isFile() || !excel.exists()) {
            throw new Exception("没有找到对应的文件");
        }

        String[] split = excel.getName().split("\\.");
        Workbook workbook = null;
        if ("xls".equals(split[1])) {
            FileInputStream fis = new FileInputStream(excel);   //文件流对象
            workbook = new HSSFWorkbook(fis);
        } else if ("xlsx".equals(split[1])) {
            workbook = new XSSFWorkbook(excel);
        } else {
            throw new Exception("文件类型错误");
        }

        Sheet sheet = workbook.getSheetAt(0);     //读取第一个sheet
        int firstRowIndex = sheet.getFirstRowNum()+1;   //第一行是列名，所以不读
        int lastRowIndex = sheet.getLastRowNum(); // 获取数据总数

        for(int rIndex = firstRowIndex; rIndex <= lastRowIndex; rIndex++) {   //遍历行
            Row row = sheet.getRow(rIndex);
            List<Object> list =new ArrayList<>();
            if (row != null) {
                int firstCellIndex = row.getFirstCellNum();
                int fieldIndex=0;
                // 以第一行标题行的列数为主
                for (int cIndex = firstCellIndex; cIndex < cellNum; cIndex++) {   //遍历列
                    Cell cell = row.getCell(cIndex);
                    String[] fieldName = fieldNames[fieldIndex].split("-");
                    String _fieldName="";
                    if (fieldName.length>=2){
                        _fieldName=fieldName[1];
                    }
                    if (cell != null) {
                         switch (_fieldName){
                             case "D":// D 时间
                                 list.add(cell.getDateCellValue());
                                 break;
                             case "B": // B 布尔
                                 cell.setCellType(CellType.STRING);
                                 list.add(cell.getStringCellValue().equals("是"));
                                 break;
                             case "I": // I int整型
                                 list.add(Integer.parseInt(new DecimalFormat("0").format(cell.getNumericCellValue())));
                                 break;
                             case "F": // F 浮点型
                                 list.add(cell.getNumericCellValue());
                                 break;
                             default:
                                 cell.setCellType(CellType.STRING);
                                 list.add(cell.getStringCellValue());
                                 break;
                         }

                    }else {
                        list.add(null);
                    }
                    outFieldNames[fieldIndex]= fieldName[0];
                    fieldIndex++;
                }
            }
            lists.add(list);
        }
        return lists;
    }

    /**
     * 通过反射创建对象
     *
     * @param clazz      对象class
     * @param fieldNames 需要写进对象的属性名称列表
     * @param objectList 写进属性的值列表,需要先对值进行类型处理好
     * @param <T>
     * @return
     */
    public static <T> T createObj(Class<T> clazz, String[] fieldNames, List<Object> objectList) {
        T obj = null;
        try {
            obj = clazz.newInstance();
            Field[] fields = FieldUtils.getAllFields(clazz);
            if (fieldNames.length != objectList.size()) {
                throw new Exception("属性名称列表和值列表的长度不同!");
            }
            for (Field field : fields) { // 遍历对象属性
                for (int i = 0; i < fieldNames.length; i++) { // 遍历需要写入的属性名称
                    String fieldNamestr=field.getName();
                    if (field.getName().equals(fieldNames[i])) {
                        field.setAccessible(true);
                        field.set(obj, objectList.get(i));// 如果对应属性是需要写入的属性,就把对应位置的值写进去
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }


    /*public static void main(String[] args) {
        List<AjaxJson> testList = ExcelUtils.importExcel(AjaxJson.class, new String[]{"success"}, "C:\\Users\\xw3q.com\\Desktop\\图书导入-模板.xls");
    }*/

}
