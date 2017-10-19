/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pc
 */
public class DataAccessTest {
    public static void main(String []args){
        try {
            DataAccessLogic data=new DataAccessLogic();
            
            List names=new ArrayList();
            
            names=data.loadNames(1);
            
            names.forEach((t) -> {
                System.out.println(t);
            });
            
            //-----------------------------
            System.out.println("Số trang để đọc hết dữ liệu cần là:");
            System.out.println(data.numOfPage());
            
        } catch (SQLException ex) {
            Logger.getLogger(DataAccessTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
