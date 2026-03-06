package com.xwcloud.cloud.common;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class MongoDBUtils {

    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;
    private DB mongoDb;

    private MongoDBUtils() {
    }

    /**
     * 初始化MongoDb工具类，并使用安全模式连接
     * @param ip   数据库地址
     * @param port 端口号
     * @param userName  数据库用户名   admin
     * @param password  数据库密码     123
     * @param verifyTheDatabase  验证的数据库名称   admin
     * @param connectToDatabase  打开数据库名称
     */
    public MongoDBUtils(String ip, int port, String userName, String password, String verifyTheDatabase, String connectToDatabase) {
        List<ServerAddress> adds = new ArrayList<ServerAddress>();
        // ServerAddress()两个参数分别为 服务器地址 和 端口
        ServerAddress serverAddress = new ServerAddress(ip, port);
        adds.add(serverAddress);
        List<MongoCredential> credentials = new ArrayList<MongoCredential>();
        // MongoCredential.createScramSha1Credential()三个参数分别为 用户名 数据库名称 密码
        MongoCredential mongoCredential = MongoCredential.createScramSha1Credential(userName, verifyTheDatabase, password.toCharArray());
        credentials.add(mongoCredential);
        // 通过连接认证获取MongoDB连接
        mongoClient = new MongoClient(adds, credentials);
        // 连接到数据库
        // 若指定的数据库不存在，mongoDB将会在你第一次插入文档时创建数据库。
        mongoDatabase = mongoClient.getDatabase(connectToDatabase);
        mongoDb = mongoClient.getDB(connectToDatabase);
    }

    /**
     * 不通过认证获取连接数据库对象
     * @param ip   数据库地址
     * @param port 端口号
     * @param databaseName 数据库名称
     */
    public MongoDBUtils(String ip, int port, String databaseName){
        //连接到 mongodb 服务
        mongoClient = new MongoClient(ip, port);
        //连接到数据库
        mongoDatabase = mongoClient.getDatabase(databaseName);
    }

    /**
     * 获取数据集合
     * @param setName
     * @return
     */
    public MongoCollection<Document> getDocumentSet(String setName){
        //获取集合
       return mongoDatabase.getCollection(setName);
    }

    /**
     * 插入一条数据
     * @param setName  集合名称
     * @param document  插入的数据文档
     *  Document document = new Document("name","张三")
     *                             .append("sex", "男")
     *                             .append("age", 18);
     */
    public void insertOne(String setName, Document document){
        //获取数据库连接对象
        //获取集合
        MongoCollection<Document> collection = mongoDatabase.getCollection(setName);
        //插入一个文档
        collection.insertOne(document);
    }


    /**
     * 插入多个文档
     * @param setName  集合名称
     * @param documentList  插入的数据文档列表
     */
    public void insertMany(String setName,List<Document> documentList){

        //获取集合
        MongoCollection<Document> collection = mongoDatabase.getCollection(setName);

        //插入多个文档
        collection.insertMany(documentList);
    }

    /**
     * 删除一条数据
     * @param setName  集合名称
     * @param filter   删除的数据过滤器
     */
    public void deleteOne(String setName, Bson filter){
        //获取集合
        MongoCollection<Document> collection = mongoDatabase.getCollection(setName);
        //申明删除条件
        //Bson filter = Filters.eq("age",18);
        //删除与筛选器匹配的单个文档
        collection.deleteOne(filter);
    }


    /**
     * 删除与筛选器匹配的所有文档
     * @param setName  集合名称
     * @param filter   删除的数据过滤器
     */
    public void deleteMany(String setName, Bson filter){
        // 获取集合
        MongoCollection<Document> collection = mongoDatabase.getCollection(setName);
        // 申明删除条件
        // Bson filter = Filters.eq("age",18);
        // 删除与筛选器匹配的所有文档
        collection.deleteMany(filter);
    }

    /**
     * 修改单个文档
     * @param setName  集合名称
     * @param filter   修改文档的数据过滤器
     * @param document 修改的文档数据
     *                 Document document = new Document("$set", new Document("age", 100));
     */
    public void updateOne(String setName, Bson filter, Document document){
        //获取集合
        MongoCollection<Document> collection = mongoDatabase.getCollection(setName);
        //修改过滤器
        // Bson filter = Filters.eq("name", "张三");
        //指定修改的更新文档
        // Document document = new Document("$set", new Document("age", 100));
        //修改单个文档
        collection.updateOne(filter, document);
    }

    /**
     * 修改多个文档
     * @param setName  集合名称
     * @param filter   修改文档的数据过滤器
     * @param document  修改的文档数据
     */
    public void updateMany(String setName, Bson filter, Document document){
        //获取集合
        MongoCollection<Document> collection = mongoDatabase.getCollection(setName);
        /*//修改过滤器
        Bson filter = Filters.eq("name", "张三");
        //指定修改的更新文档
        Document document = new Document("$set", new Document("age", 100));*/
        //修改多个文档
        collection.updateMany(filter, document);
    }

    /**
     * 查找集合中的所有文档
     * @param setName  集合名称
     */
    public void findAll(String setName){
        //获取集合
        MongoCollection<Document> collection = mongoDatabase.getCollection(setName);
        //查找集合中的所有文档
        FindIterable findIterable = collection.find();
        MongoCursor cursor = findIterable.iterator();
        while (cursor.hasNext()) {
            System.out.println(cursor.next());
        }
    }

    //指定查询过滤器查询
    public void Filterfind(String setName, Bson filter){
        //获取集合
        MongoCollection collection = mongoDatabase.getCollection(setName);
        //指定查询过滤器
        // Bson filter = Filters.eq("name", "张三");
        //指定查询过滤器查询
        FindIterable findIterable = collection.find(filter);
        MongoCursor cursor = findIterable.iterator();
        while (cursor.hasNext()) {
            System.out.println(cursor.next());
        }
    }

    // 获取查询到的数据的第一条数据

    /**
     * 获取查询到的数据的第一条数据
     * @param setName 数据集名称
     * @return   第一条文档数据
     */
    public Document findFirst(String setName){
        //获取集合
        MongoCollection collection = mongoDatabase.getCollection(setName);
        //查找集合中的所有文档
        FindIterable findIterable = collection.find();
        //取出查询到的第一个文档
        Document document = (Document) findIterable.first();
        return document;
    }

    /**
     * 存储文件
     * @param collectionName 集合名
     * @param file 文件
     * @param filename 文件名称
     */
    public void SaveFile(String collectionName, File file,  String filename) {
        try {

            // 存储fs的根节点
            GridFS gridFS = new GridFS(mongoDb, collectionName);
            GridFSInputFile gfs = gridFS.createFile(file);
            gfs.put("filename", filename);
            gfs.put("contentType", filename.substring(filename.lastIndexOf(".")));
            gfs.save();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("存储文件时发生错误！！！");
        }
    }

    /**
     * 通过文件名取出文件
     * @param collectionName  集合名称
     * @param filename   文件名
     * @return
     */
    public InputStream retrieveFileOne(String collectionName, String filename) {
        try {
            // 获取fs的根节点
            GridFS gridFS = new GridFS( mongoDb, collectionName);
            DBObject query  = new BasicDBObject("filename", filename);
            GridFSDBFile dbfile = gridFS.findOne(query);
            if (dbfile != null) {
                return dbfile.getInputStream();
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }

    /**
     * 删除文件
     * @param collectionName  集合名称
     * @param filename  文件名称
     */
    public void removeFile(String collectionName, String filename){
        GridFS gridFS = new GridFS( mongoDb, collectionName);
        gridFS.remove(filename);
    }

}
