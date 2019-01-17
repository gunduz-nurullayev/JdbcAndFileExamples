package WorksWithCSVFile;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbReadFileWrite {
    public static void main(String[] args) {
        List<Employees> employeeList=new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connection = null;
        BufferedWriter bufferedWriter=null;
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            String jdbcUrl = "jdbc:oracle:thin:@//gunduz:1522/ORCL";
            String username = "hr";
            String password = "hr";
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            if (!connection.isClosed()) {
                connection.setAutoCommit(false);
                System.out.println("DB connection is successful!");
            }
            String sql="Select id,first_name, last_name, salary from test";
            ps=connection.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()){
                Employees employees=new Employees();
                employees.setId(rs.getLong("id"));
                employees.setFirst_name(rs.getString("first_name"));
                employees.setLast_name(rs.getString("last_name"));
                employees.setSalary(rs.getBigDecimal("salary"));
                employeeList.add(employees);
            }
            System.out.println(employeeList);
             bufferedWriter=new BufferedWriter(new FileWriter("data.csv",true));
            for (int i = 0; i <employeeList.size() ; i++) {
                bufferedWriter.write(employeeList.get(i).getId()+","+employeeList.get(i).getFirst_name()+","+employeeList.get(i).getLast_name()+","+employeeList.get(i).getSalary());
                bufferedWriter.newLine();
            }
            System.out.println("DataBase-den melumat fayla uğurla elave olundu!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {

                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();
                }
                if (bufferedWriter!=null){
                    bufferedWriter.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
