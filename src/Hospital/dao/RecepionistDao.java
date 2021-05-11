package Hospital.dao;

import Hospital.dbutil.DBConnection;
import static Hospital.dbutil.DBConnection.getConnection;
import Hospital.pojo.EmpPojo;
import Hospital.pojo.UserPojo2;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;


public class RecepionistDao {
    public static boolean addRecepionist(UserPojo2 user)throws SQLException
    {
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

    public static HashMap<String, String> getRecepionistDetails()throws SQLException {
     Connection conn =DBConnection.getConnection();
     String qry="select empid,userid from users where usertype='Recepionist'";
     Statement st = conn.createStatement();
     ResultSet rs = st.executeQuery(qry);
     HashMap<String,String> res = new HashMap();
     while(rs.next())
     {
         String empid = rs.getString(1);
         String userid = rs.getString(2);
         res.put(empid,userid);
     }
     return res;
    }

    public static boolean removeRecepionist(String rid)throws SQLException {
        PreparedStatement ps =DBConnection.getConnection().prepareStatement("delete from users where empid=?");
        ps.setString(1,rid);
        int result = ps.executeUpdate();
        return result==1;
    }
    
     public static ArrayList<EmpPojo> getReceptionistList() throws SQLException
    {
        Connection conn = DBConnection.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select * from employees where job='Recepionist'");
       
         ArrayList<EmpPojo> receptionistList = new ArrayList<EmpPojo>();
         while(rs.next())
         {
              EmpPojo e = new EmpPojo();
             e.setEmpid(rs.getString("empid"));
             e.setEmpname( rs.getString("empname"));
             e.setJob(rs.getString("job"));
            e.setSal(rs.getDouble("sal"));
             receptionistList.add(e);
         }
         return receptionistList;
    }
}
