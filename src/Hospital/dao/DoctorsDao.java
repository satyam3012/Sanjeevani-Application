/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hospital.dao;

import Hospital.dbutil.DBConnection;
import static Hospital.dbutil.DBConnection.getConnection;
import Hospital.pojo.DoctorPojo;
import Hospital.pojo.UserPojo2;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;

/**
 *
 * @author ROHINI RAUT
 */
public class DoctorsDao {

    public static HashMap<String, String> getAllDoctors() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static HashMap<String,String> getRegisteredDoctor() throws SQLException
    {
        Connection conn = DBConnection.getConnection();
       String qry = "Select empname,empid from employees where job='Doctor' and empid not in (Select empid from users where usertype='Doctor')";
       Statement st = conn.createStatement();
       ResultSet rs = st.executeQuery(qry);
       HashMap<String, String> doctor= new HashMap();
       while(rs.next())
       {
           String name = rs.getString(1);
           String id = rs.getString(2);
           doctor.put(name,id);
       }
       return doctor;  
    }

    public static boolean addDoctor(UserPojo2 user)throws SQLException {
      Connection conn = DBConnection.getConnection();
      String qry = "insert into users values(?,?,?,?,?)";
      PreparedStatement ps = conn.prepareStatement(qry);
      ps.setString(1,user.getUserId());
      ps.setString(2,user.getUserName());
      ps.setString(3,user.getEmpId());
      ps.setString(4,user.getPassword());
      ps.setString(5,user.getUserType());
      int x = ps.executeUpdate();
      return x>0;   
    }
    
   
    public static boolean addDoctorDetails(DoctorPojo doc)throws SQLException{
        Connection conn = DBConnection.getConnection();
      String qry = "insert into doctors values(?,?,?,?)";
      PreparedStatement ps = conn.prepareStatement(qry);
      ps.setString(1,doc.getUserId());
      ps.setString(2,doc.getDoctorid());
      ps.setString(3,doc.getQualifcation());
      ps.setString(4,doc.getSpecialist());
      int x = ps.executeUpdate();
      return x==1; 
    }
    
    public static HashMap<String,String> getRegisteredDoctorId() throws SQLException
    {
        Connection conn = DBConnection.getConnection();
       String qry = "Select doctorid,userid from doctors";
       Statement st = conn.createStatement();
       ResultSet rs = st.executeQuery(qry);
       HashMap<String, String> doctor= new HashMap();
       while(rs.next())
       {
           String doctorid  = rs.getString(1);
           String userid = rs.getString(2);
           doctor.put(doctorid,userid);
       }
       return doctor;  
    }
     
    
    public static boolean deleteDoctor(String eno)throws SQLException {
       PreparedStatement ps=DBConnection.getConnection().prepareStatement("delete from doctors where doctorid=?");
       ps.setString(1,eno);
       int result=ps.executeUpdate();
            return result==1;
    }

    public static boolean deleteAllDoctors(UserPojo2 e)throws SQLException {
     PreparedStatement ps = DBConnection.getConnection().prepareStatement("delete from users where userid=?");
     ps.setString(1,e.getUserId());
     int result=ps.executeUpdate();
     return result==1;
    }
    
     public static ArrayList<DoctorPojo> getDoctorList() throws SQLException
    {
     Connection conn = DBConnection.getConnection();
     Statement st = conn.createStatement();
     ResultSet rs = st.executeQuery("select * from doctors ");
      ArrayList<DoctorPojo> receptionistList = new ArrayList<DoctorPojo>();     
         while(rs.next())
         {
            DoctorPojo e = new DoctorPojo();
            e.setDoctorid(rs.getString("doctorid"));
            e.setUserId(rs.getString("userid"));
            e.setQualifcation(rs.getString("qualification"));
            e.setSpecialist(rs.getString("specialist"));
             receptionistList.add(e);
         }
     return receptionistList;
    }
}