package utilities;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBC {

    public static void main(String[] args) throws SQLException {

        String URL ="jdbc:postgresql://localhost/HR";
        String username ="postgres";
        String password ="Admin";
        String query ="SELECT * FROM employees LIMIT 5 ";


        Connection connection;
        Statement statement;
        ResultSet resultSet;
        ResultSetMetaData resultSetMetaData;


        connection = DriverManager.getConnection(URL, username,password);
        statement = connection.createStatement();
        resultSet = statement.executeQuery(query);
        resultSet.next();

        System.out.println(resultSet.getString(1));
        System.out.println(resultSet.getString("employee_id"));
        resultSet.next();// everytime switching to next row
        System.out.println(resultSet.getString(3));
        System.out.println(resultSet.getString("salary"));
        System.out.println("================================================");
        while(resultSet.next()){
            System.out.print(resultSet.getString(1)+" ");
            System.out.print(resultSet.getString(2)+" ");
            System.out.print(resultSet.getString(3)+" ");
            System.out.print(resultSet.getString("salary")+" ");
            System.out.println(resultSet.getString("manager_id")+" ");
        }
        //information about your data...metaData!
        resultSetMetaData = resultSet.getMetaData();//information about your data...metaData!
        System.out.println(resultSetMetaData.getColumnName(1));
        System.out.println(resultSetMetaData.getColumnCount());
        System.out.println(resultSetMetaData.getTableName(1));

        ResultSet resultSet2= statement.executeQuery(query);

        List<Map<String,Object>> data = new ArrayList<>();


        while (resultSet2.next()){
            Map<String,Object> row = new HashMap<>();//always going new row
            for(int i =1; i<=resultSetMetaData.getColumnCount(); i++){
                row.put(resultSetMetaData.getColumnName(i),resultSet2.getString(i));

            }
            data.add(row);

        }
        connection.close();

       System.out.println(data.size());
//        System.out.println(data.get(0));
//        System.out.println(data.get(0).get("email"));
//        System.out.println(data.get(1).values());
//        System.out.println(data.get(1).get("employee_id"));
//        System.out.println(data.get(1).get("salary"));
//
//        connection.close();
//        System.out.println(resultSet.getString(0));
    }

}
