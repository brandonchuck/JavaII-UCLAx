package DBClient.Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class Employee {

    private StringProperty empNum;
    private StringProperty birthDate;
    private StringProperty firstName;
    private StringProperty lastName;
    private StringProperty gender;
    private StringProperty hireDate;

    public Employee(String empNum, String birthDate, String firstName, String lastName, String gender, String hireDate){
        this.empNum = new SimpleStringProperty(this, "empNo");
        this.birthDate = new SimpleStringProperty(this, "birthDate");
        this.firstName = new SimpleStringProperty(this, "firstName");
        this.lastName = new SimpleStringProperty(this, "lastName");
        this.gender = new SimpleStringProperty(this, "gender");
        this.hireDate = new SimpleStringProperty(this, "hireDate");

        // Set values in constructor
        this.setEmp_no(empNum);
        this.setBirthDate(birthDate);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setGender(gender);
        this.setHireDate(hireDate);
    }


    public static ObservableList<Employee> getEmployees() {

        // Observable array list for storing employees from DB
        ObservableList<Employee> ret_val = FXCollections.observableArrayList();

        // database connection string
        String conn_url = "enter url here";


        Connection conn = null;
        try {
            conn = DriverManager.getConnection(conn_url); // get connection from DBConnection.java
            ResultSet rs = null;

            PreparedStatement stmt = conn.prepareStatement("select * from employees limit 50");
            rs = stmt.executeQuery();


            while (rs != null && rs.next()) {
                String emp_no = rs.getString("emp_no");
                String birthDate = rs.getString("birth_date");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String gender = rs.getString("gender");
                String hireDate = rs.getDate("hire_date").toString();

                ret_val.add(new Employee(emp_no, birthDate, firstName, lastName, gender, hireDate));
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

    public String getBirthDate(){
        return this.birthDate.get();
    }

    public String getFirstName(){
        return this.firstName.get();
    }

    public String getLastName(){
        return this.lastName.get();
    }

    public String getGender(){
        return this.gender.get();
    }

    public String getHireDate(){
        return this.hireDate.get();
    }


    // Setter Methods
    public void setEmp_no(String value){
        this.empNum.set(value);
    }

    public void setBirthDate(String value){
        this.birthDate.set(value);
    }

    public void setFirstName(String value){
        this.firstName.set(value);
    }

    public void setLastName(String value){
        this.firstName.set(value);
    }

    public void setGender(String value){
        this.gender.set(value);
    }

    public void setHireDate(String value){
        this.hireDate.set(value);
    }
}



