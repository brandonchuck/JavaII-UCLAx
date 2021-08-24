package DBClient.Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DepartmentEmployee {

    private StringProperty empNum;
    private StringProperty deptNo;
    private StringProperty fromDate;
    private StringProperty toDate;

    public DepartmentEmployee(String empNo, String deptNo, String fromDate, String toDate){
        this.empNum = new SimpleStringProperty(this, "empNo");
        this.deptNo = new SimpleStringProperty(this, "deptNo");
        this.fromDate = new SimpleStringProperty(this, "fromDate");
        this.toDate = new SimpleStringProperty(this, "toDate");

        // Set values in constructor
        this.setEmp_no(empNo);
        this.setDeptNo(deptNo);
        this.setFromDate(fromDate);
        this.setToDate(toDate);
    }


    public static ObservableList<DepartmentEmployee> getDeptEmployees() {

        // Observable array list for storing employees from DB
        ObservableList<DepartmentEmployee> ret_val = FXCollections.observableArrayList();

        // database connection string
        String conn_url = "enter url here";


        Connection conn = null;
        try {
            conn = DriverManager.getConnection(conn_url); // get connection from DBConnection.java
            ResultSet rs = null;

            PreparedStatement stmt = conn.prepareStatement("select * from dept_emp limit 50");
            rs = stmt.executeQuery();


            while (rs != null && rs.next()) {
                String emp_no = rs.getString("emp_no");
                String dept_no = rs.getString("dept_no");
                String fromDate = rs.getDate("from_date").toString();
                String toDate = rs.getDate("to_date").toString();

                // add the Employee object to the result List
                ret_val.add(new DepartmentEmployee(emp_no, dept_no, fromDate, toDate));
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

    public String getDeptNo(){
        return this.deptNo.get();
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

    public void setDeptNo(String value){
        this.deptNo.set(value);
    }

    public void setFromDate(String value){
        this.fromDate.set(value);
    }

    public void setToDate(String value){
        this.toDate.set(value);
    }
}



