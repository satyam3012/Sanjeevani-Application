/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hospital.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class DBConnection 
{
    private static Connection conn;
    static {
        try{
            Class.forName("oracle.jdbc.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@//DESKTOP-8TR8TPL:1521/xe","myhms","student");
            JOptionPane.showMessageDialog(null,"connection done successfully!");
           }
         catch(ClassNotFoundException cnfe)
             {
               JOptionPane.showMessageDialog(null,"cann't load Driver"+cnfe);
               cnfe.printStackTrace();
             }
        catch(SQLException sqlex)
        {
           JOptionPane.showMessageDialog(null,"problem in DB!"+sqlex);
           sqlex.printStackTrace();
        }
      } 
    
    public static Connection getConnection()
    {
        return conn;
    }
    
    public static void closeConnection()
    {
        try{
             if(conn!=null)
             {
             conn.close();
              JOptionPane.showMessageDialog(null,"connection closed successfully!");
            }
          }
         catch(SQLException sqlex)
          {
           JOptionPane.showMessageDialog(null,"problem in DB!"+sqlex);
           sqlex.printStackTrace();
          }
     }
   }



