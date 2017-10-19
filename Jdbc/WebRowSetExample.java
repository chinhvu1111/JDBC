/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc;

import com.sun.rowset.WebRowSetImpl;
import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.WebRowSet;

/**
 *
 * @author pc
 */
public class WebRowSetExample {

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            
            conn=DriverManager.getConnection("jdbc:derby://localhost:1527/fisrtDb","chinhvu1111","narutokun1");
            
           
            stmt=conn.createStatement();
            
            String sql="select * from Sinhvien";
            
            rs=stmt.executeQuery(sql);
            
            File file=new File("output.xml");
            
            FileWriter writer=new FileWriter(file);
            
            WebRowSet wrs=new WebRowSetImpl();
            wrs.writeXml(rs,writer);
            
            Desktop des=Desktop.getDesktop();
            
            des.open(file);
            
        } catch (SQLException ex) {
            Logger.getLogger(WebRowSetExample.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(WebRowSetExample.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}
