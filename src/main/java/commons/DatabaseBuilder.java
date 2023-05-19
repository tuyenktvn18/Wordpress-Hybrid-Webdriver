package commons;

import java.sql.*;
import java.util.LinkedHashMap;

public class DatabaseBuilder {

    public static Connection getConnection() {
        String hostName = GlobalConstants.getGlobalConstants().getDbHostName();
        String dbName = GlobalConstants.getGlobalConstants().getDbName();
        String dbUserName = GlobalConstants.getGlobalConstants().getDbUsername();
        String dbUserPassword = GlobalConstants.getGlobalConstants().getDbPassword();

        return getMySQLConnection(hostName, dbName, dbUserName, dbUserPassword);

    }

    private static Connection getMySQLConnection(String hostName, String dbName, String dbUserName, String dbUserPassword) {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;
            con = DriverManager.getConnection(connectionURL, dbUserName, dbUserPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    public synchronized LinkedHashMap<String, String> getSqlResults(Connection connection, String sql) {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData md = rs.getMetaData();
            LinkedHashMap<String, String> data_map = new LinkedHashMap<>();
            while (rs.next()) {
                for (int i = 1; i <= md.getColumnCount(); i++) {
                    data_map.put(md.getColumnName(i), rs.getString(i));
                }
            }
            return data_map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
