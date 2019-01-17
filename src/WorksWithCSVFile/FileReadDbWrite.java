package WorksWithCSVFile;

import WorksWithCSVFile.Employees;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FileReadDbWrite {
    public static void main(String[] args) {
        String line = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connection = null;
        BufferedReader bufferedReader=null;
        List<Employees> dataList=new ArrayList<>();
        try {
            bufferedReader = new BufferedReader(new FileReader("D:\\data.csv"));
            while ((line = bufferedReader.readLine()) != null) {
                Employees empl=new Employees();
                String[] a = line.split(",");
                long id=Integer.parseInt(a[0]);
                String first_name=a[1];
                String last_name=a[2];
                BigDecimal salary=new BigDecimal(a[3]);
                   empl.setId(id);
                   empl.setFirst_name(first_name);
                   empl.setLast_name(last_name);
                   empl.setSalary(salary);
                   dataList.add(empl);
            }
            System.out.println(dataList);
            for (int i = 0; i < dataList.size(); i++) {
                System.out.println(dataList.get(i));
            }
            Class.forName("oracle.jdbc.OracleDriver");
            String jdbcUrl = "jdbc:oracle:thin:@//gunduz:1522/ORCL";
            String username = "hr";
            String password = "hr";
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            if (!connection.isClosed()) {
                connection.setAutoCommit(false);
                System.out.println("DB connection is successful!");
            }

            String sql = "insert into test(id,first_name,last_name,salary) " +
                    "values(test_seq.nextval, ?, ?, ?)";
            ps = connection.prepareStatement(sql);

            for (int i = 0; i <dataList.size(); i++) {
                //ps.setLong(1,dataList.get(i).getId());
                ps.setString(1, dataList.get(i).getFirst_name());
                ps.setString(2,  dataList.get(i).getLast_name());
                ps.setBigDecimal(3, dataList.get(i).getSalary());
                ps.executeUpdate();
            }

            System.out.println("Melumatlar fayldan database-e elave olundu!");
        }
        catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(bufferedReader!=null) {
                    bufferedReader.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
