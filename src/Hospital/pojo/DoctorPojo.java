package Hospital.pojo;

public class DoctorPojo
{

    @Override
    public String toString() {
        return "DoctorPojo{" + "UserId=" + UserId + ", Doctorid=" + Doctorid + ", Qualifcation=" + Qualifcation + ", specialist=" + specialist + '}';
    }

   
   
    public String getDoctorid() {
        return Doctorid;
    }

    public void setDoctorid(String Doctorid) {
        this.Doctorid = Doctorid;
    }

    public String getQualifcation() {
        return Qualifcation;
    }

    public void setQualifcation(String Qualifcation) {
        this.Qualifcation = Qualifcation;
    }

    public String getSpecialist() {
        return specialist;
    }

    public void setSpecialist(String specialist) {
        this.specialist = specialist;
    }
     public String getUserId() {
        return UserId;
    }

    public void setUserId(String UserId) {
        this.UserId = UserId;
    }
  private String UserId;  
  private String Doctorid;
  private String Qualifcation;
  private String specialist;
}
