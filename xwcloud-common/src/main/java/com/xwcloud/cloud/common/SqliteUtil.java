package com.xwcloud.cloud.common;


import java.sql.*;

public class SqliteUtil {
    private String DbName;

    private String DbPath;

    private Connection conn = null;//定义数据库连接对象

    public SqliteUtil(String DbPath, String DbName) {
        this.DbPath = DbPath;
        this.DbName = DbName;
        try {
            Class.forName("org.sqlite.JDBC");//加载数据库驱动
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //不允许无传参的实例化
    private SqliteUtil() {
    }

    /**
     * 创建连接
     *
     * @return
     */
    public Connection DBconnect() {

        try {
            String url = "jdbc:sqlite:" + DbPath + DbName;   //定义连接数据库的url(url:访问数据库的URL路径),test为数据库名称
            //如果conn为null或者无效
            if (conn == null || !conn.isValid(3000)) {
                conn = DriverManager.getConnection(url);    //获取数据库连接
            } else {
                return conn;
            }
            System.out.println("数据库连接成功！\n");//数据库连接成功输出提示
        }
        //捕获异常信息
        catch (SQLException e) {
            System.out.println("数据库连接失败！" + e.getMessage());
        }
        return conn;//返回一个连接
    }

    ///执行 INSERT DELETE UPDATE 的方法
    public boolean executeUpdate(String sql) {
        //打开数据库
        DBconnect();
        //创建执行 sql 的对象
        try {
            Statement stmt = conn.createStatement();
            //执行sql 语句
            stmt.executeUpdate(sql);
            DBClose();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        //不关闭数据库，避免高并发时报错
       /* finally
        {
            DBClose();
        }*/
    }

    //执行 sql 的 查找 SELECT 的方法
    public ResultSet executeQuery(String sql) {
        //打开数据库链接
        DBconnect();
        //创建执行sql 的对象
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rs;

    }

    //绑定的sql INSERT DELETE UPDATE 方法
    public boolean executeUpdate(String sql, Object[] params) {
        //打开数据库
        DBconnect();
        //创建绑定对象
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            //填充 ？ 的值
            for (int i = 0; params != null && i < params.length; i++) {
                if (params[i] instanceof Integer) {
                    pstmt.setInt(i + 1, Integer.parseInt(params[i].toString()));
                    continue;
                }
                if (params[i] instanceof Float) {
                    pstmt.setFloat(i + 1, Float.parseFloat(params[i].toString()));
                    continue;
                }
                if (params[i] instanceof Double) {
                    pstmt.setDouble(i + 1, Double.parseDouble(params[i].toString()));
                    continue;
                }
                if (params[i] instanceof String) {
                    pstmt.setString(i + 1, params[i].toString());
                }

                //java.sql.Date,   java.util.Date
                if (params[i] instanceof java.util.Date) {
                    //将 java.util.Date 转成  java.sql.Date
                    java.util.Date javaDate = (java.util.Date) params[i];
                    Date sqlDate = new Date(javaDate.getTime());
                    pstmt.setDate(i + 1, sqlDate);
                    continue;
                }

               /* if(params[i] instanceof java.util.Timestamp)
                {
                    //将 java.util.Date 转成  java.sql.Date
                    java.util.Date javaDate=(java.util.Date)params[i];
                    java.sql.Timestamp timestamp=new java.sql.Timestamp(javaDate.getTime());
                    pstmt.setTimestamp(i+1, timestamp);
                    continue;
                }*/
            }
            //执行 绑定的 sql 语句
            if (pstmt.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //不关闭数据库，避免并发时报错
       /* finally
        {
            DBClose();
        }*/

        return false;
    }

    //绑定的sql 查找 SELECT 方法
    public ResultSet executeQuery(String sql, Object[] params) {
        //打开数据库
        DBconnect();
        //创建绑定对象
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            //填充 ？ 的值
            for (int i = 0; params != null && i < params.length; i++) {
                if (params[i] instanceof Integer) {
                    pstmt.setInt(i + 1, Integer.parseInt(params[i].toString()));
                    continue;
                }
                if (params[i] instanceof Float) {
                    pstmt.setFloat(i + 1, Float.parseFloat(params[i].toString()));
                    continue;
                }
                if (params[i] instanceof Double) {
                    pstmt.setDouble(i + 1, Double.parseDouble(params[i].toString()));
                    continue;
                }
                if (params[i] instanceof String) {
                    pstmt.setString(i + 1, params[i].toString());
                }

                //java.sql.Date,   java.util.Date
                if (params[i] instanceof java.util.Date) {
                    //将 java.util.Date 转成  java.sql.Date
                    java.util.Date javaDate = (java.util.Date) params[i];
                    Date sqlDate = new Date(javaDate.getTime());
                    pstmt.setDate(i + 1, sqlDate);
                    continue;
                }

                /*if(params[i] instanceof java.util.Timestamp)
                {
                    //将 java.util.Date 转成  java.sql.Date
                    java.util.Date javaDate=(java.util.Date)params[i];
                    java.sql.Timestamp timestamp=new java.sql.Timestamp(javaDate.getTime());
                    pstmt.setTimestamp(i+1, timestamp);
                    continue;
                }*/
            }
            //执行 绑定的 sql 语句
            return pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    //关闭数据库链接
    public void DBClose() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public String getDbName() {
        return DbName;
    }

    public void setDbName(String dbName) {
        DbName = dbName;
    }

    public String getDbPath() {
        return DbPath;
    }

    public void setDbPath(String dbPath) {
        DbPath = dbPath;
    }


}
