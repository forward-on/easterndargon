package com.ly.database;

import com.alibaba.druid.pool.DruidDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author : ly.
 * @Date : 2018/5/7.
 */
public class ConnectTest {


    private static DruidDataSource dataSourceMDB=null;
    private static DruidDataSource dataSourceSSO=null;

    private static String DBURL = "jdbc:mysql://127.0.0.1:3306/fly";
    private static String DBDRIVER = "com.mysql.jdbc.Driver";
    private static String DBUSER = "root";
    private static String DBPASSWORLD = "123456";

    public String db;

    /**
     * 构造函数完成数据库的连接和连接对象的生成
     * @throws Exception
     */
    public ConnectTest(){

    }

    public void GetDbConnectSSO() throws Exception  {
        try{

            if(dataSourceSSO==null){

                dataSourceSSO=new DruidDataSource();

                //设置连接参数
                dataSourceSSO.setUrl(DBURL);
                dataSourceSSO.setDriverClassName(DBDRIVER);
                dataSourceSSO.setUsername(DBUSER);
                dataSourceSSO.setPassword(DBPASSWORLD);
                //配置初始化大小、最小、最大
                dataSourceSSO.setInitialSize(1);
                dataSourceSSO.setMinIdle(1);
                dataSourceSSO.setMaxActive(20);
                //连接泄漏监测
                dataSourceSSO.setRemoveAbandoned(true);
                dataSourceSSO.setRemoveAbandonedTimeout(30);
                //配置获取连接等待超时的时间
                dataSourceSSO.setMaxWait(20000);
                //配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
                dataSourceSSO.setTimeBetweenEvictionRunsMillis(20000);
                //防止过期
                dataSourceSSO.setValidationQuery("SELECT 'x'");
                dataSourceSSO.setTestWhileIdle(true);
                dataSourceSSO.setTestOnBorrow(true);

            }

        }catch(Exception e){

            throw e;

        }

    }

    /*public void GetDbConnectMDB() throws Exception {
        try{

            if(dataSourceMDB==null){

                dataSourceMDB=new DruidDataSource();

                //设置连接参数
                dataSourceMDB.setUrl(Config.MDB_DBURL);
                dataSourceMDB.setDriverClassName(Config.DBDRIVER);
                dataSourceMDB.setUsername(Config.MDB_DBUSER);
                dataSourceMDB.setPassword(Config.MDB_DBPASSWORLD);
                //配置初始化大小、最小、最大
                dataSourceMDB.setInitialSize(1);
                dataSourceMDB.setMinIdle(1);
                dataSourceMDB.setMaxActive(20);
                //连接泄漏监测
                dataSourceMDB.setRemoveAbandoned(true);
                dataSourceMDB.setRemoveAbandonedTimeout(30);
                //配置获取连接等待超时的时间
                dataSourceMDB.setMaxWait(20000);
                //配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
                dataSourceMDB.setTimeBetweenEvictionRunsMillis(20000);
                //防止过期
                dataSourceMDB.setValidationQuery("SELECT 'x'");
                dataSourceMDB.setTestWhileIdle(true);
                dataSourceMDB.setTestOnBorrow(true);

            }

        }catch(Exception e){

            throw e;

        }

    }*/
    /**
     * 取得已经构造生成的数据库连接
     * @return 返回数据库连接对象
     * @throws Exception
     */
    public Connection getConnect(String str) throws Exception{

        Connection con=null;
        this.db=str;

        try {

            if(db.equals("MDB")){
//                GetDbConnectMDB();
//                con=dataSourceMDB.getConnection();

            }else if(db.equals("SSO")){
                GetDbConnectSSO();
                con=dataSourceSSO.getConnection();

            }
        } catch (Exception e) {

            throw e;

        }
        return con;

    }

    public static void main(String[] args) throws Exception {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            ConnectTest connectTest = new ConnectTest();
            conn = connectTest.getConnect("SSO");
            String sql = "select * from leaflet where id = 5";
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String content = resultSet.getString("content");
                System.out.println(content);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
