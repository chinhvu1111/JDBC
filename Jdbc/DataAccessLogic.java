/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc;

import com.sun.rowset.CachedRowSetImpl;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.CachedRowSet;

/**
 *
 * @author pc
 */
public class DataAccessLogic {

    private final static Logger logger = Logger.getLogger(DataAccessLogic.class.getName());
    Connection conn = null;
    CachedRowSet rowset = null;

    ResultSet rs = null;

    public DataAccessLogic() throws SQLException {
        this.conn = DriverManager.getConnection("jdbc:derby://localhost:1527/fisrtDb", "chinhvu1111", "narutokun1");

        rowset = new CachedRowSetImpl();
        //dữ liệu trong rowset được đọc từ result set dữ liệu tạo ra resutSet nằm trong Command
        rowset.setCommand("select * from SINHVIEN");

        rowset.execute(conn);

    }

    public void disConnect() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataAccessLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private int pageSize = 1;

    List<String> loadNames(int page) throws SQLException {
        List names = new ArrayList();
        //quy định số row của 1 query command theo các tham chiếu SQL gốc
        //muốn đọc dữ liệu n rows thì ta set page n rows
        rowset.setPageSize(pageSize);

        rowset.execute(conn);

        int start = (page - 1) * pageSize + 1;

        //System.out.println(rowset.absolute(start));
        if (!rowset.absolute(start)) {
            return names;
        }

        rowset.previous();

        while (rowset.next()) {

            names.add(rowset.getString("Hoten"));

            //System.out.println(rowset.getString("Hoten"));
            if (names.size() >= pageSize) {
                break;
            }

        }

        return names;

    }

    public int numOfPage() {

        int totalPage = 0;
        try {
            Statement stmt = conn.createStatement();

            String sql = "select count(*) from Sinhvien";

            rs = stmt.executeQuery(sql);

            if (!rs.next()) {
                return 0;
            }

            //Lấy giá trị cột số 1 thế thôi
            int total = rs.getInt(1);

            totalPage = total / pageSize;

            if (total % pageSize != 0) {
                totalPage++;
            }
            
            //trả về tổng số trang
            return totalPage;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalPage;
    }

}
