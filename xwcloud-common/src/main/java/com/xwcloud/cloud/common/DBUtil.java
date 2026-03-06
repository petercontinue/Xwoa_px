package com.xwcloud.cloud.common;

import com.alibaba.fastjson.JSON;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBUtil {
    /**
     * 驱动名称
     */
    //private final String DRIVER_NAME = "org.postgresql.Driver";
    /** 数据库链接地址 *//*
    private static final String url = "jdbc:mysql://127.0.0.1:3306/xdb";
    *//** 用户名 *//*
    private static final String userName = "root";
    *//** 密码 *//*
    private static final String password = "root";*/

    /**
     * 定义连接
     */
    private Connection conn;
    /**
     * 定义STMT
     */
    private PreparedStatement stmt;
    /**
     * 定义结果集
     */
    private ResultSet rs;

    private DBUtil() {
    }

    public DBUtil(String url, String userName, String password,String driver_name) throws SQLException, ClassNotFoundException {
        Class.forName(driver_name);
        conn = DriverManager.getConnection(url, userName, password);
    }

    /**
     * 获取链接
     */
    public Connection getConn() {
        return conn;
    }


    /**
     * 关闭链接,释放资源
     */
    public void close() {
        try {
            if (rs != null) {
                rs.close();
                rs = null;
            }
            if (stmt != null) {
                stmt.close();
                stmt = null;
            }

            if (conn != null) {
                conn.close();
                conn = null;
            }
        } catch (SQLException e) {
            System.err.println("资源释放发生异常");
        }
    }

    /**
     * 获取指定数据库下所有的表名
     *
     * @param dbNm
     * @return
     */
    public List<String> getAllTableName(String dbNm) {
        List<String> result = new ArrayList<String>();
        Statement st = null;
        try {
            st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES  WHERE TABLE_SCHEMA='" + dbNm + "'");
            while (rs.next()) {
                result.add(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        return result;
    }


    /**
     * 获取指定数据库下所有的表名
     *
     * @param tableName
     * @return
     */
    public List<String> getAllTableColumns(String tableName) {
        List<String> result = new ArrayList<String>();
        Statement st = null;
        try {
            st = conn.createStatement();
            ResultSet rs = st.executeQuery("select column_name from information_schema.columns where table_schema='public' and table_name='" + tableName + "'");
            while (rs.next()) {
                result.add(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    // String sql = "select * from admin where id=? or id=? ";

    /**
     * 执行SQL返回ResultSet
     */
    public ResultSet executeQuerySql(String sql, Object... args) throws SQLException {

        stmt = conn.prepareStatement(sql);
        if (null != args && args.length != 0) {
            for (int i = 0; i < args.length; i++) {
                stmt.setObject(i + 1, args[i]);
            }
        }
        System.out.println(stmt);
        rs = stmt.executeQuery();
        return rs;
    }

    /**
     * 执行SQL更新,插入，删除并返回影响行
     */
    public int executeUpdateSql(String sql, Object... args) throws SQLException {
        int cont = 0;

        stmt = conn.prepareStatement(sql);
        if (null != args && args.length != 0) {
            for (int i = 0; i < args.length; i++) {
                stmt.setObject(i + 1, args[i]);
            }
        }
        System.out.println(stmt);
        cont = stmt.executeUpdate();
        return cont;
    }

    /**
     * 执行SQL更新,插入，删除并返回影响行
     */
    public boolean executeSql(String sql) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        stmt = con.prepareStatement(sql);
        return stmt.execute();
    }

    /**
     * @title 查询数据结果 , 并封装为对象
     */
    public <T> T excuteQuery(Class<T> clazz, String sql, Object... args) throws SQLException {

        rs = executeQuerySql(sql, args);
        ResultSetMetaData metaData = rs.getMetaData();
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (rs.next()) {
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                String columnname = metaData.getColumnLabel(i);
                Object obj = rs.getObject(i);
                resultMap.put(columnname, obj);
            }
        }

        return JSON.parseObject(JSON.toJSONString(resultMap), clazz);

    }

    /**
     * @title 查询数据结果 , 并封装为List
     */
    public List<Map<String, Object>> excuteQueryToList(String sql, Object... args) throws SQLException {

        rs = executeQuerySql(sql, args);
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        Map<String, Object> resultMap = new HashMap<String, Object>();
        while (rs.next()) {
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                resultMap.put(metaData.getColumnName(i), rs.getObject(i));
            }
            resultList.add(resultMap);
        }

        return resultList;

    }


    /**
     * @param tableName
     * @param field
     * @return
     * @throws SQLException
     */
    public List<Map<String, Object>> excuteQueryTableToList(String tableName, List<String> field) throws SQLException {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        String fieldstr = " ";
        if (null != field && field.size() != 0) {
            for (int i = 0; i < field.size(); i++) {
                if (field.size() - 1 == i) {
                    fieldstr += field.get(i);
                } else {
                    fieldstr += field.get(i) + ",";
                }

            }
        }
        Statement st = conn.createStatement();

        ResultSet rs = st.executeQuery("Select  " + fieldstr + " from \"" + tableName + "\"");

        while (rs.next()) {
            Map<String, Object> map = new HashMap<String, Object>();
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                map.put(metaData.getColumnName(i), rs.getObject(i));
            }
            result.add(map);
        }

        return result;
    }

    /**
     * @param tableName
     * @return
     * @throws SQLException
     */
    public List<Map<String, Object>> excuteQueryTableToList(String tableName) throws SQLException {
        List<String> list = new ArrayList<String>();
        list.add("*");
        return excuteQueryTableToList(tableName, list);
    }
}
