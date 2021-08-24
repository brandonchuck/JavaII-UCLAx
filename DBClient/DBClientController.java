package DBClient;

import DBClient.Models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DBClientController {

    @FXML
    private TextArea sqlArea;

    @FXML
    private TableView tableView = new TableView();


    // Department
    @FXML TableColumn<Department, String> deptNo_Department = new TableColumn<>("dept_no");
    @FXML TableColumn<Department, String> deptName_Department = new TableColumn<>("dept_name");


    // Department Employees
    @FXML TableColumn<DepartmentEmployee, String> empNum_DepartmentEmployee = new TableColumn<>("emp_no");
    @FXML TableColumn<DepartmentEmployee, String> deptNo_DepartmentEmployee = new TableColumn<>("dept_no");
    @FXML TableColumn<DepartmentEmployee, String> fromDate_DepartmentEmployee = new TableColumn<>("from_date");
    @FXML TableColumn<DepartmentEmployee, String> toDate_DepartmentEmployee = new TableColumn<>("to_date");


    // Department Manager
    @FXML TableColumn<DepartmentManager, String> empNum_DepartmentManager = new TableColumn<>("emp_no");
    @FXML TableColumn<DepartmentManager, String> deptNo_DepartmentManager = new TableColumn<>("dept_no");
    @FXML TableColumn<DepartmentManager, String> fromDate_DepartmentManager = new TableColumn<>("from_date");
    @FXML TableColumn<DepartmentManager, String> toDate_DepartmentManager = new TableColumn<>("to_date");


    // Employees Table
    @FXML TableColumn<Employee, String> empNum_Employee = new TableColumn<>("emp_no");
    @FXML TableColumn<Employee, String> birthDate_Employee = new TableColumn<>("birth_date");
    @FXML TableColumn<Employee, String> firstName_Employee = new TableColumn<>("first_name");
    @FXML TableColumn<Employee, String> lastName_Employee = new TableColumn<>("last_name");
    @FXML TableColumn<Employee, String> gender_Employee = new TableColumn<>("gender");
    @FXML TableColumn<Employee, String> hireDate_Employee = new TableColumn<>("hire_date");


    // Salaries
    @FXML TableColumn<Salaries, String> empNum_Salaries = new TableColumn<>("emp_no");
    @FXML TableColumn<Salaries, String> salary_Salaries = new TableColumn<>("salary");
    @FXML TableColumn<Salaries, String> fromDate_Salaries = new TableColumn<>("from_date");
    @FXML TableColumn<Salaries, String> toDate_Salaries = new TableColumn<>("to_date");


    // Title
    @FXML TableColumn<Title, String> empNum_Title = new TableColumn<>("emp_no");
    @FXML TableColumn<Title, String> title_Title = new TableColumn<>("title");
    @FXML TableColumn<Title, String> fromDate_Title = new TableColumn<>("from_date");
    @FXML TableColumn<Title, String> toDate_Title = new TableColumn<>("to_date");


    // Constructor
    public DBClientController(){
    }


    public void chooseTable(){

        // Regex is NOT case-insensitive
        String sqlStatement = "^SELECT\\s(.+)\\sFROM\\s(.+)$";

        Pattern sqlPattern = Pattern.compile(sqlStatement);

        String sqlText = sqlArea.getText();

        if (sqlText.isEmpty()){
            sqlArea.setText("Enter a valid SQL statement");
        }

        // Create Matcher with pattern and TextArea
        Matcher query_matcher = sqlPattern.matcher(sqlText);

        // the SQL statement must match the SQL pattern
        if (query_matcher.matches()) {

            String group1 = query_matcher.group(1);
            String group2 = query_matcher.group(2);

            if (group2.contains("employees")) {
                this.LoadEmployeeCols(group1);
            }

            if (query_matcher.group(2).contains("departments")) {
                this.LoadDepartmentCols(group1);
            }

            if (query_matcher.group(2).contains("dept_emp")) {
                this.LoadDeptEmployees(group1);
            }

            if (query_matcher.group(2).contains("dept_manager")) {
                this.LoadDeptManagers(group1);
            }

            if (query_matcher.group(2).contains("salaries")) {
                this.LoadSalaryCols(group1);
            }

            if (query_matcher.group(2).contains("titles")) {
                this.LoadTitleCols(group1);
            }
        } else {
            System.out.println("Invalid SQL statement");
        }
    }


    public void LoadDepartmentCols(String group1){

        ObservableList<Department> departments = Department.getDepartments();
        ObservableList<TableColumn<Department,String>> cols_to_add = FXCollections.observableArrayList();

        if (group1.contains("*")){
            cols_to_add.add(deptNo_Department);
            cols_to_add.add(deptName_Department);
        }

        // Trying to display the data
        if (group1.contains("dept_no")){
            cols_to_add.add(deptNo_Department);
        }

        if (group1.contains("dept_name")){
            cols_to_add.add(deptName_Department);
        }


        deptNo_Department.setCellValueFactory(new PropertyValueFactory("deptNo"));
        deptName_Department.setCellValueFactory(new PropertyValueFactory<>("deptName"));

        tableView.getColumns().setAll(cols_to_add);

        // Add data from db to TableView
        tableView.setItems(departments);
    }


    public void LoadEmployeeCols(String group1){

        ObservableList<Employee> employees = Employee.getEmployees();
        ObservableList<TableColumn<Employee,String>> cols_to_add = FXCollections.observableArrayList();

        if (group1.contains("*")){
            cols_to_add.add(empNum_Employee);
            cols_to_add.add(birthDate_Employee);
            cols_to_add.add(firstName_Employee);
            cols_to_add.add(lastName_Employee);
            cols_to_add.add(gender_Employee);
            cols_to_add.add(hireDate_Employee);
        }

        if (group1.contains("emp_no")){
            cols_to_add.add(empNum_Employee);
        }

        if (group1.contains("birth_date")){
            cols_to_add.add(birthDate_Employee);
        }

        if (group1.contains("first_name")){
            cols_to_add.add(firstName_Employee);
        }

        if (group1.contains("last_name")){
            cols_to_add.add(lastName_Employee);
        }

        if (group1.contains("gender")){
            cols_to_add.add(gender_Employee);
        }

        if (group1.contains("hire_date")){
            cols_to_add.add(hireDate_Employee);
        }

        empNum_Employee.setCellValueFactory(new PropertyValueFactory("empNo"));
        birthDate_Employee.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
        firstName_Employee.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastName_Employee.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        gender_Employee.setCellValueFactory(new PropertyValueFactory<>("gender"));
        hireDate_Employee.setCellValueFactory(new PropertyValueFactory<>("hireDate"));

        tableView.getColumns().setAll(cols_to_add);

        // Add data from db to TableView
        tableView.setItems(employees);
    }


    public void LoadSalaryCols(String group1){

        ObservableList<Salaries> salaries = Salaries.getSalaries();
        ObservableList<TableColumn<Salaries,String>> cols_to_add = FXCollections.observableArrayList();

        if (group1.contains("*")){
            cols_to_add.add(empNum_Salaries);
            cols_to_add.add(salary_Salaries);
            cols_to_add.add(fromDate_Salaries);
            cols_to_add.add(toDate_Salaries);
        }

        // Trying to display the data
        if (group1.contains("emp_no")){
            cols_to_add.add(empNum_Salaries);
        }

        if (group1.contains("salary")){
            cols_to_add.add(salary_Salaries);
        }

        if (group1.contains("from_date")){
            cols_to_add.add(fromDate_Salaries);
        }

        if (group1.contains("to_date")){
            cols_to_add.add(toDate_Salaries);
        }

        empNum_Salaries.setCellValueFactory(new PropertyValueFactory("empNo"));
        salary_Salaries.setCellValueFactory(new PropertyValueFactory<>("salary"));
        toDate_Salaries.setCellValueFactory(new PropertyValueFactory<>("fromDate"));
        fromDate_Salaries.setCellValueFactory(new PropertyValueFactory<>("toDate"));

        tableView.getColumns().setAll(cols_to_add);

        // Add data from db to TableView
        tableView.setItems(salaries);
    }


    public void LoadTitleCols(String group1){

        ObservableList<Title> titles = Title.getTitles();
        ObservableList<TableColumn<Title,String>> cols_to_add = FXCollections.observableArrayList();

        if (group1.contains("*")){
            cols_to_add.add(empNum_Title);
            cols_to_add.add(title_Title);
            cols_to_add.add(fromDate_Title);
            cols_to_add.add(toDate_Title);
        }

        // Trying to display the data
        if (group1.contains("emp_no")){
            cols_to_add.add(empNum_Title);
        }

        if (group1.contains("title")){
            cols_to_add.add(title_Title);
        }

        if (group1.contains("from_date")){
            cols_to_add.add(fromDate_Title);
        }

        if (group1.contains("to_date")){
            cols_to_add.add(toDate_Title);
        }

        empNum_Title.setCellValueFactory(new PropertyValueFactory("empNo"));
        title_Title.setCellValueFactory(new PropertyValueFactory<>("title"));
        toDate_Title.setCellValueFactory(new PropertyValueFactory<>("fromDate"));
        fromDate_Title.setCellValueFactory(new PropertyValueFactory<>("toDate"));

        tableView.getColumns().setAll(cols_to_add);

        // Add data from db to TableView
        tableView.setItems(titles);
    }


    public void LoadDeptEmployees(String group1){

        ObservableList<DepartmentEmployee> departmentEmployees = DepartmentEmployee.getDeptEmployees();
        ObservableList<TableColumn<DepartmentEmployee,String>> cols_to_add = FXCollections.observableArrayList();

        if (group1.contains("*")){
            cols_to_add.add(empNum_DepartmentEmployee);
            cols_to_add.add(deptNo_DepartmentEmployee);
            cols_to_add.add(fromDate_DepartmentEmployee);
            cols_to_add.add(toDate_DepartmentEmployee);
        }

        // Trying to display the data
        if (group1.contains("emp_no")){
            cols_to_add.add(empNum_DepartmentEmployee);
        }

        if (group1.contains("dept_no")){
            cols_to_add.add(deptNo_DepartmentEmployee);
        }

        if (group1.contains("from_date")){
            cols_to_add.add(fromDate_DepartmentEmployee);
        }

        if (group1.contains("to_date")){
            cols_to_add.add(toDate_DepartmentEmployee);
        }

        empNum_DepartmentEmployee.setCellValueFactory(new PropertyValueFactory("empNo"));
        deptNo_DepartmentEmployee.setCellValueFactory(new PropertyValueFactory<>("deptNo"));
        toDate_DepartmentEmployee.setCellValueFactory(new PropertyValueFactory<>("fromDate"));
        fromDate_DepartmentEmployee.setCellValueFactory(new PropertyValueFactory<>("toDate"));

        tableView.getColumns().setAll(cols_to_add);

        // Add data from db to TableView
        tableView.setItems(departmentEmployees);
    }

    public void LoadDeptManagers(String group1){

        ObservableList<DepartmentManager> departmentManagers = DepartmentManager.getDeptManagers();
        ObservableList<TableColumn<DepartmentManager,String>> cols_to_add = FXCollections.observableArrayList();

        if (group1.contains("*")){
            cols_to_add.add(empNum_DepartmentManager);
            cols_to_add.add(deptNo_DepartmentManager);
            cols_to_add.add(fromDate_DepartmentManager);
            cols_to_add.add(toDate_DepartmentManager);
        }

        // Trying to display the data
        if (group1.contains("emp_no")){
            cols_to_add.add(empNum_DepartmentManager);
        }

        if (group1.contains("dept_no")){
            cols_to_add.add(deptNo_DepartmentManager);
        }

        if (group1.contains("from_date")){
            cols_to_add.add(fromDate_DepartmentManager);
        }

        if (group1.contains("to_date")){
            cols_to_add.add(toDate_DepartmentManager);
        }

        empNum_DepartmentManager.setCellValueFactory(new PropertyValueFactory("empNo"));
        deptNo_DepartmentManager.setCellValueFactory(new PropertyValueFactory<>("deptNo"));
        toDate_DepartmentManager.setCellValueFactory(new PropertyValueFactory<>("fromDate"));
        fromDate_DepartmentManager.setCellValueFactory(new PropertyValueFactory<>("toDate"));

        tableView.getColumns().setAll(cols_to_add);

        // Add data from db to TableView
        tableView.setItems(departmentManagers);
    }
}
