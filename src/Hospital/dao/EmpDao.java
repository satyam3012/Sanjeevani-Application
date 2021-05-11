/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hospital.dao;

import Hospital.dbutil.DBConnection;
import Hospital.gui.RemoveEmployeesFrame;
import Hospital.pojo.EmpPojo;
import Hospital.pojo.UserPojo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author ROHINI RAUT
 */
public class EmpDao {
    
    public static String getNewId()throws SQLException{
     Connection conn = DBConnection.getConnection();
     Statement st = conn.createStatement();
     ResultSet rs = st.executeQuery("select max(Empid) from employees");
     int id=1;
     if(rs.next())
     {
         String empid=rs.getString(1);
         int eno=Integer.parseInt(empid.substring(1));
         id = id + eno;
     }
     String sr = "E" + id;
     System.out.println(sr);
     return sr;
    }
    
     public static boolean addEmployee(EmpPojo e)throws SQLException{
       Connection conn = DBConnection.getConnection();
       PreparedStatement ps = conn.prepareStatement("Insert into employees values(?,?,?,?)");
       ps.setString(1, e.getEmpid());
       ps.setString(2, e.getEmpname());
       ps.setString(3, e.getJob());
       ps.setDouble(4, e.getSal());
       int x = ps.executeUpdate();
         return x==1;
     }
    
    public static ArrayList<EmpPojo> getAllEmp() throws SQLException
    {
     Connection conn=DBConnection.getConnection();
     Statement st = conn.createStatement();
     ResultSet rs = st.executeQuery("select * from employees");
     ArrayList<EmpPojo> empList = new ArrayList<>();
     while(rs.next())
     {
         EmpPojo e = new EmpPojo();
         e.setEmpid(rs.getString(1));
         e.setEmpname(rs.getString(2));
         e.setJob(rs.getString(3));
         e.setSal(rs.getDouble(4));
         empList.add(e);
     }
     return empList;
    }
    
  public static boolean updateEmp(EmpPojo eno) throws SQLException
   {
    PreparedStatement ps=DBConnection.getConnection().prepareStatement("update employees set empname=?,job=?,sal=? where empid=?");
    ps.setString(1,eno.getEmpname());
     ps.setString(2,eno.getJob());
     ps.setDouble(3,eno.getSal());
     ps.setString(4,eno.getEmpid());
     int result=ps.executeUpdate();
       return result==1; 
  }
   
   public static Boolean deleteEmp(String eno) throws SQLException
  {
    PreparedStatement ps=DBConnection.getConnection().prepareStatement("delete from employees where empid=?");
    ps.setString(1,eno);
    int result=ps.executeUpdate();
            return result==1;
  }
   
  public static HashMap<String,String>getRegisteredRecepionist()throws SQLException{
    Connection conn = DBConnection.getConnection();
       String qry = "Select * from employees";
       Statement st = conn.createStatement();
       ResultSet rs = st.executeQuery(qry);
       HashMap<String, String> has= new HashMap();
       while(rs.next())
       {
           String id = rs.getString(1);
           String name = rs.getString(2);
           
           has.put(id,name);
       }
    return has; 
   } 
   
 
    
   public static HashMap<String,String> getNonRegisteredRecepionist()throws SQLException{
       Connection conn = DBConnection.getConnection();
       String qry = "Select empid,empname from employees where job='Recepionist' and empid not in (Select empid from users where usertype='Recepionist')";
       Statement st = conn.createStatement();
       ResultSet rs = st.executeQuery(qry);
       HashMap<String ,String> recepionist= new HashMap<String,String>();
       
       while(rs.next())
       {
           String id = rs.getString(1);
           String name = rs.getString(2);
           String job = rs.getString(3);
           Double sal = rs.getDouble(4);
           recepionist.put(id,name);
       }
       return recepionist;
   }
   
}  