/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hospital.dao;

import Hospital.dbutil.DBConnection;
import Hospital.pojo.UserPojo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

/**
 *
 * @author ROHINI RAUT
 */
public class UserDao 
{
   public static String validateUser(UserPojo user)throws SQLException
   {
    Connection conn=DBConnection.getConnection();
    String qry="Select username from Users where userid=? and password=? and usertype=?";
    PreparedStatement ps=conn.prepareStatement(qry);
    ps.setString(1,user.getUserId());
    ps.setString(2,user.getPassword());
    ps.setString(3, user.getUserType());
    ResultSet rs=ps.executeQuery();
    String username=null;
    if(rs.next())
    {
    username=rs.getString(1);
    }
    return username;
  }
   
   public static boolean changePassword(String userid,String pwd) throws SQLException
   {
       PreparedStatement ps = DBConnection.getConnection().prepareStatement("update users set password=? where userid=?");
       ps.setString(1,pwd);
       ps.setString(2, userid);
       return (ps.executeUpdate()!=0);
   }
   
   public static HashMap<String,String>getReceptionList() throws SQLException
   {
     HashMap<String,String> recepionistList = new HashMap<>();
     ResultSet rs = DBConnection.getConnection().createStatement().executeQuery("select userid,username from users where usertype = 'Recepionist'");
     while(rs.next())
     {
         recepionistList.put(rs.getString(1),rs.getString(2));
     }
     return recepionistList;
   }
   
    public static String validatePasswordUser(UserPojo user)throws SQLException
   {
    Connection conn=DBConnection.getConnection();
    String qry="Select * from users where userid=? and password=? and usertype='RECEPIONIST'";
    PreparedStatement ps=conn.prepareStatement(qry);
    ps.setString(1,user.getUserId());
    ps.setString(2,user.getPassword());
    //ps.setString(3, user.getUserType());
    ResultSet rs=ps.executeQuery();
    String password=null;
    if(rs.next())
    {
    password=rs.getString(4);
    }
    return password;
  }
}

