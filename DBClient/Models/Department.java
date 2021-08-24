package DBClient.Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class Department {

    private StringProperty deptNo;
    private StringProperty deptName;


    public Department(String deptNo, String deptName){
        this.deptNo = new SimpleStringProperty(this, "deptNum");
        this.deptName = new SimpleStringProperty(this, "deptName");

        // Set values in constructor
        this.setDeptNo(deptNo);
        this.setDeptName(deptName);
    }


    public static ObservableList<Department> getDepartments() {

        // Observable array list for storing employees from DB
        ObservableList<Department> ret_val = FXCollections.observableArrayList();

        // database connection string
        String conn_url = "enter url here";


        Connection conn = null;
        try {
            conn = DriverManager.getConnection(conn_url); // get connection from DBConnection.java
            ResultSet rs = null;

            PreparedStatement stmt = conn.prepareStatement("select * from departments limit 50");
            rs = stmt.executeQuery();


            while (rs != null && rs.next()) {
                String dept_no = rs.getString("dept_no");
                String dept_name= rs.getString("dept_name");

                // add the Employee object to the result List
                ret_val.add(new Department(dept_no, dept_name));
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
    public String getDeptNo(){
        return this.deptNo.get();
    }

    public String getDeptName(){
        return this.deptName.get();
    }


    // Setter Methods
    public void setDeptNo(String value){
        this.deptNo.set(value);
    }

    public void setDeptName(String value){
        this.deptName.set(value);
    }
}



