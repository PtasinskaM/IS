package com.ptasinska;

import com.ptasinska.data.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersistenceService {

    public static String DB_URL = "jdbc:mysql://localhost:3306/integracja_systemow?serverTimezone=UTC&characterEncoding=UTF-8";
    public static String DB_USER = "root";
    public static String DB_PASSWORD = "";
    private Connection connection;

    public PersistenceService(){
        this.connection = getConnection();
    }
    public Connection getConnection(){
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public List<Laptop> select() {
        ResultSet resultSet = null;
        List<Laptop> result = null;
        try {
            resultSet = connection.createStatement().executeQuery("SELECT * FROM laptops");
            result = new ArrayList<>();

            Laptop laptop = null;
            int id = 1;
            while (resultSet.next()) {
                //laptop.setId(resultSet.getInt("id"));
                String[] data = new String[15];
                data[0] = resultSet.getString("manufacturer");
                data[1] = resultSet.getString("screenSize");
                data[2] = resultSet.getString("screenResolution");
                data[3] = resultSet.getString("screenType");
                data[4] = resultSet.getString("screenTouch");
                data[5] = resultSet.getString("processorName");
                data[6] = String.valueOf(resultSet.getInt("processorCores"));
                data[7] = resultSet.getString("processorClockSpeed");
                data[8] = resultSet.getString("ramSize");
                data[9] = resultSet.getString("discStorage");
                data[10] = resultSet.getString("discType");
                data[11] = resultSet.getString("gpuName");
                data[12] = resultSet.getString("gpuMemory");
                data[13] = resultSet.getString("os");
                data[14] = resultSet.getString("driveType");
                laptop = new Laptop(id, data);
                id++;
                result.add(laptop);
            }

            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public boolean insert(List<Laptop> data) {

        for (Laptop o : data) {
            try {
                connection.createStatement().execute(o.sqlInsert());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
        return true;
    }

}
