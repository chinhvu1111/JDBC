/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pc
 */
public class JdbcDemoTransaction {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Connection conn = null;
        
        // TODO code application logic here
        try {
            
            Properties info=new Properties();
            
            info.put("user","chinhvu1111");
            info.put("password","narutokun1");
            
            conn=DriverManager.getConnection("jdbc:derby://localhost:1527/fisrtDb","chinhvu1111","narutokun1");
            
            PreparedStatement pstmt=conn.prepareStatement("Insert into SINHVIEN Values(?,?,?,?,?,?)");
            
            conn.setAutoCommit(false);
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
            
            while(true){
                String s;
                System.out.println("Nhập Id:");
                s=br.readLine();
                
                pstmt.setString(1,s);
                
                System.out.println("Nhập mã sinh viên");
                s=br.readLine();
                
                pstmt.setString(2,s);
                
                System.out.println("Nhập Họ tên:");
                s=br.readLine();
                
                pstmt.setString(3,s);
                
                System.out.println("Nhập lớp:");
                s=br.readLine();
                
                pstmt.setString(4, s);
                
                System.out.println("Nhập ngày sinh:");
                
                s=br.readLine();
                
                pstmt.setString(5, s);
                
                System.out.println("Nhập nới sinh:");
                s=br.readLine();
                pstmt.setString(6,s);
                pstmt.executeUpdate();
                System.out.println("Có update dữ liệu hay không");
                Scanner sc=new Scanner(System.in);
                
                String s1=sc.nextLine();
                
                if(s1.equals("yes")){
                    conn.commit();break;
                }
                else{
                    //quay trở lại trạng thái ban đầu setCommit
                    conn.rollback();break;
                }
            }
            
            
            if(conn!=null){
                conn.close();
            }
            
            if(pstmt!=null){
                pstmt.close();
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(JdbcDemoTransaction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JdbcDemoTransaction.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
