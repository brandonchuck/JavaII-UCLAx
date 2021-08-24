package DBClient.Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class Title {

    private StringProperty empNum;
    private StringProperty title;
    private StringProperty fromDate;
    private StringProperty toDate;

    public Title(String empNum, String title, String fromDate, String toDate){
        this.empNum = new SimpleStringProperty(this, "empNo");
        this.title = new SimpleStringProperty(this, "title");
        this.fromDate = new SimpleStringProperty(this, "fromDate");
        this.toDate = new SimpleStringProperty(this, "toDate");

        // Set values in constructor
        this.setEmp_no(empNum);
        this.setTitle(title);
        this.setFromDate(fromDate);
        this.setToDate(toDate);
    }


    public static ObservableList<Title> getTitles() {

        // Observable array list for storing employees from DB
        ObservableList<Title> ret_val = FXCollections.observableArrayList();

        // database connection string
        String conn_url = "enter url here";


        Connection conn = null;
        try {
            conn = DriverManager.getConnection(conn_url); // get connection from DBConnection.java
            ResultSet rs = null;

            PreparedStatement stmt = conn.prepareStatement("select * from titles limit 50");
            rs = stmt.executeQuery();


            while (rs != null && rs.next()) {
                String emp_no = rs.getString("emp_no");
                String title = rs.getString("title");
                String fromDate = rs.getDate("from_date").toString();
                String toDate = rs.getDate("to_date").toString();

                // add the Employee object to the result List
                ret_val.add(new Title(emp_no, title, fromDate, toDate));
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

    public String getTitle(){
        return this.title.get();
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

    public void setTitle(String value){
        this.title.set(value);
    }

    public void setFromDate(String value){
        this.fromDate.set(value);
    }

    public void setToDate(String value){
        this.toDate.set(value);
    }
}



