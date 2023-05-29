package utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DatabaseReader {
    private static ResultSet rSet;
    private static ResultSetMetaData rSetMData;

    static Statement st;
    static Connection conn;
    /*
    This method create connection to the database,execute query and return object resultSet
    @param query
    @return rSet
     */

    public static ResultSet getResultSet(String query) {
        try {
            //to establish the connection with DB
          conn = DriverManager.getConnection(ConfigReader.getPropertyValue("dbUrl"),
                    ConfigReader.getPropertyValue("dbUsername"), ConfigReader.getPropertyValue("dbPassword"));
            //create a statement to execute the query
             st = conn.createStatement();
            //execute the query and storing the results
            rSet = st.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rSet;
    }

    /*
    This method return an object of ResultSetMetadata
    @param query
    @return rSetMData
     */
    public static ResultSetMetaData getResetSetMetaData(String query) {

        try {
            //we use this line to get the data in tabular format so that we
            //use we can use these in column keys and values retrieval operation
            rSetMData = rSet.getMetaData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rSetMData;
    }

    /*
    this method extract data from resultSet and store into list of maps
    @param query
    @return listOfMaps
     */
    public static List<Map<String, String>> getListOfMapsFromRSet(String query) {
        getResultSet(query);
        getResetSetMetaData(query);
        List<Map<String, String>> listOfMap = new ArrayList<>();
        Map<String, String> map;
        try {
            while (rSet.next()) {
                map = new LinkedHashMap<>();
                for (int i = 1; i <= rSetMData.getColumnCount(); i++) {
                    String key = rSetMData.getColumnName(i);
                    //it will return the value against the key
                    String value = rSet.getString(key);
                    map.put(key, value);
                }
                listOfMap.add(map);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DatabaseReader.closeResultSet(rSet);
            DatabaseReader.closeStatement(st);
            DatabaseReader.closeConnection(conn);
        }

        return listOfMap;
    }
    public static void closeResultSet(ResultSet rset){
        if(rset!=null){
            try{
                rset.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeStatement(Statement statement){
        if(statement!=null){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeConnection(Connection conn){
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
