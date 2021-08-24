package DBClient.Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class Salaries {

    private StringProperty empNum;
    private StringProperty salary;
    private StringProperty fromDate;
    private StringProperty toDate;

    public Salaries(String empNum, String salary, String fromDate, String toDate){
        this.empNum = new SimpleStringProperty(this, "empNo");
        this.salary = new SimpleStringProperty(this, "salary");
        this.fromDate = new SimpleStringProperty(this, "fromDate");
        this.toDate = new SimpleStringProperty(this, "toDate");

        // Set values in constructor
        this.setEmp_no(empNum);
        this.setSalary(salary);
        this.setFromDate(fromDate);
        this.setToDate(toDate);
    }


    public static ObservableList<Salaries> getSalaries() {

        // Observable array list for storing employees from DB
        ObservableList<Salaries> ret_val = FXCollections.observableArrayList();

        // database connection string
        String conn_url = "enter url here";


        Connection conn = null;
        try {
            conn = DriverManager.getConnection(conn_url); // get connection from DBConnection.java
            ResultSet rs = null;

            PreparedStatement stmt = conn.prepareStatement("select * from salaries limit 50");
            rs = stmt.executeQuery();


            while (rs != null && rs.next()) {
                String emp_no = rs.getString("emp_no");
                String salary = rs.getString("salary");
                String fromDate = rs.getDate("from_date").toString();
                String toDate = rs.getDate("to_date").toString();

                // add the Employee object to the result List
                ret_val.add(new Salaries(emp_no, salary, fromDate, toDate));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        return ret_val;
    }



    // Getter Methods
    public String getEmpNum(){
        return this.empNum.get();
    }

    public String getSalary(){
        return this.salary.get();
    }

    public String getFromDate(){
        return this.fromDate.get();
    }

    public String getToDate(){
        return this.toDate.get();
    }


    // Setter Methods
    public void setEmp_no(String value){
        this.empNum.set(value);
    }

    public void setSalary(String value){
        this.salary.set(value);
    }

    public void setFromDate(String value){
        this.fromDate.set(value);
    }

    public void setToDate(String value){
        this.toDate.set(value);
    }
}



