/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetProvider;

/**
 *
 * @author pc
 */
public class DemoRowset {

    public static void main(String[] args) {

        //Connection conn=null;
        JdbcRowSet jdbcrs = null;
        try {
            //conn=DriverManager.getConnection("jdbc:derby://localhost:1527/fisrtDb");

            jdbcrs = RowSetProvider.newFactory().createJdbcRowSet();

            jdbcrs.setUrl("jdbc:derby://localhost:1527/fisrtDb");

            jdbcrs.setUsername("chinhvu1111");
            jdbcrs.setPassword("narutokun1");

            jdbcrs.setCommand("select * from SINHVIEN");

            jdbcrs.execute();

            while (jdbcrs.next()) {
                //Tạo sự kiên con trỏ di chuyển
                System.out.println("Id" + jdbcrs.getString("Id"));
                System.out.println("Id" + jdbcrs.getString("Masv"));
                System.out.println("Id" + jdbcrs.getString("Hoten"));
                System.out.println("Id" + jdbcrs.getString("Lop"));
                System.out.println("Id" + jdbcrs.getString("Ngaysinh"));
                System.out.println("Id" + jdbcrs.getString("Noisinh"));

            }

            System.out.println("Lấy dữ liệu ở cột thứ 2");
            //chuyển con trỏ đến vị trí row 2
            jdbcrs.absolute(2);

            System.out.println("Id" + jdbcrs.getString("Id"));
            System.out.println("Id" + jdbcrs.getString("Masv"));
            System.out.println("Id" + jdbcrs.getString("Hoten"));
            System.out.println("Id" + jdbcrs.getString("Lop"));
            System.out.println("Id" + jdbcrs.getString("Ngaysinh"));
            System.out.println("Id" + jdbcrs.getString("Noisinh"));

            if (jdbcrs != null) {
                jdbcrs.close();
            }

        } catch (Exception e) {
        }
    }
}
