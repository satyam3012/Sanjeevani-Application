/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hospital.pojo;

/**
 *
 * @author ROHINI RAUT
 */
public class EmpPojo {

    @Override
    public String toString() {
        return "EmpPojo{" + "Empid=" + Empid + ", Empname=" + Empname + ", job=" + job + ", sal=" + sal + '}';
    }


    public String getEmpid() {
        return Empid;
    }

    public void setEmpid(String Empid) {
        this.Empid = Empid;
    }

    public String getEmpname() {
        return Empname;
    }

    public void setEmpname(String Empname) {
        this.Empname = Empname;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public double getSal() {
        return sal;
    }

    public void setSal(double sal) {
        this.sal = sal;
    }
    String Empid;
    String Empname;
    String job;
    double sal;
    
}
