package utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DatabaseReader {
    private static ResultSet rSet;
    private static ResultSetMetaData rSetMData;
    /*
    This method create connection to the database,execute query and return object resultSet
    @param query
    @return rSet
     */

    public static ResultSet getResultSet(String query) {
        try {
            Connection conn = DriverManager.getConnection(ConfigReader.getPropertyValue("dbUrl"),
                    ConfigReader.getPropertyValue("dbUsername"), ConfigReader.getPropertyValue("dbPassword"));
            Statement st = conn.createStatement();
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
                    String value = rSet.getString(key);
                    map.put(key, value);
                }
                listOfMap.add(map);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listOfMap;
    }
}
