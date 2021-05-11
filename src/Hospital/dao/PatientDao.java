/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hospital.dao;

import Hospital.dbutil.DBConnection;
import Hospital.pojo.PatientPojo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author ROHINI RAUT
 */
public class PatientDao {

    public static String getNewId()throws SQLException
    {
     Connection conn = DBConnection.getConnection();
     Statement st = conn.createStatement();
     ResultSet rs = st.executeQuery("select max(P_ID) from patient"); 
     int id=1;
     if(rs.next())
     {
         String pids=rs.getString(1);
         int eno=Integer.parseInt(pids.substring(1));
         id = id + eno;
     }
     String sr = "P" + id;
     System.out.println(sr);
     return sr;  
    }

    
    public static boolean addPatient(PatientPojo pp)throws SQLException
    {
     Connection conn = DBConnection.getConnection();
     String qry = "insert into patient values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     PreparedStatement ps = conn.prepareStatement(qry);
     ps.setString(1,pp.getP_id());
     ps.setString(2,pp.getF_name());
     ps.setString(3,pp.getS_name());
     ps.setInt(4,pp.getAge());
     ps.setString(5,pp.getOpd());
     ps.setString(13,pp.getBed());
     ps.setString(14,pp.getWard());
     ps.setString(6,pp.getGender());
     ps.setString(7,pp.getM_status());
     ps.setDate(8,pp.getDate());
     ps.setString(9,pp.getAddress());
     ps.setString(10,pp.getCity());
     ps.setString(11,pp.getMno());
     ps.setString(12,pp.getDoctor_id());
     int x = ps.executeUpdate();
        System.out.println(x);
     return x>0;
    }

    
    public static HashMap<String, String> addRegisteredPatient() throws SQLException
    {
      Connection conn = DBConnection.getConnection();
      String qry = "select p_ID,DOCTOR_ID from patient";
      Statement st = conn.createStatement();
      ResultSet rs = st.executeQuery(qry);
      HashMap<String,String> patient = new HashMap();
      while(rs.next())
      {
          String pid = rs.getString(1);
          String docid = rs.getString(2);
          patient.put(pid,docid);
      }
      return patient;
    }

    public static boolean deletePatient(String p)throws SQLException {
      PreparedStatement ps = DBConnection.getConnection().prepareStatement("delete from patient where p_id=?");
      ps.setString(1,p);
      int result=ps.executeUpdate();
      return result==1;
    }

    public static boolean updatePatient(PatientPojo p)throws SQLException {
    PreparedStatement ps=DBConnection.getConnection().prepareStatement("update patient set F_NAME=?,S_NAME=?,AGE=?,OPD=?,GENDER=?,M_STATUS_STATUS=?,P_DATE=?,ADDRESS=?,CITY=?,PHONE_NO=?,WARD=?,BED=? where  P_ID=?");
     ps.setString(1,p.getF_name());
     ps.setString(2,p.getS_name());
     ps.setInt(3,p.getAge());
     ps.setString(4,p.getOpd());
     ps.setString(5,p.getGender());
     ps.setString(6,p.getM_status());
     ps.setDate(7,p.getDate());
     ps.setString(8,p.getAddress());
     ps.setString(9,p.getCity());
     ps.setString(10,p.getMno());
     //ps.setString(11,p.getDoctor_id());
     ps.setString(11,p.getWard());
     ps.setString(12,p.getBed());
     ps.setString(13,p.getP_id());
     
     int result=ps.executeUpdate();
       return result==1;        
    }

    public static HashMap<String, String> addRegisteredPatientIdAndName()throws SQLException {
       Connection conn = DBConnection.getConnection();
      String qry = "select p_ID,F_NAME from patient";
      Statement st = conn.createStatement();
      ResultSet rs = st.executeQuery(qry);
      HashMap<String,String> patient = new HashMap();
      while(rs.next())
      {
          String pid = rs.getString(1);
          String fname = rs.getString(2);
          patient.put(pid,fname);
      }
      return patient; 
    }

    public static ArrayList<PatientPojo> getPatientTable()throws SQLException {
        Connection conn = DBConnection.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select * from patient");
        ArrayList<PatientPojo> list = new ArrayList<PatientPojo>();
        while(rs.next())
        {
            PatientPojo p = new PatientPojo();
            p.setP_id(rs.getString("P_ID"));
            p.setF_name(rs.getString("F_Name"));
            p.setS_name(rs.getString("S_NAME"));
            p.setAge(rs.getInt("AGE"));
            p.setOpd("OPD");
            p.setGender(rs.getString("GENDER"));
            p.setM_status(rs.getString("M_STATUS_STATUS"));
            p.setDate(rs.getDate("P_DATE"));
            p.setAddress(rs.getString("ADDRESS"));
            p.setCity(rs.getString("CITY"));
            p.setMno(rs.getString("PHONE_NO"));
            p.setDoctor_id(rs.getString("DOCTOR_ID"));
            p.setWard(rs.getString("WARD"));
            p.setBed(rs.getString("BED"));
            
            list.add(p);
        }
        return list;
    }
    
}
  