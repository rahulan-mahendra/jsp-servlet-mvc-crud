package net.assignment.employee.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

import net.assignment.employee.model.Employee;

public class EmployeeDAO {
	
	private String jdbcURL = "jdbc:mysql://localhost:3306/mysql_database?useSSL=false";
	private String jdbcUserName = "root";
	private String jdbcPassword = "";
	
	private static final String INSERT_EMPLOYEE = "INSERT INTO employee"+" (first_name,last_name,email) VALUES"+"(?,?,?);";
	private static final String SELECT_EMPLOYEE_BY_ID = "select id,first_name,last_name,email from employee where id=?";
	private static final String SELECT_ALL_EMPLOYEES = "select * from employee";
	private static final String DELETE_EMPLOYEE = "delete from employee where id=?";
	private static final String UPDATE_EMPLOYEE = "update employee set first_name = ?, last_name = ? ,email = ? where id = ?";
	
	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL,jdbcUserName,jdbcPassword);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return connection;
	}
	
	//create employee
	public void createEmployee(Employee employee) throws SQLException {
		try(Connection connection = getConnection();
				PreparedStatement preparedStataement = connection.prepareStatement(INSERT_EMPLOYEE)){
			preparedStataement.setString(1, employee.getFirstName());
			preparedStataement.setString(2, employee.getLastName());
			preparedStataement.setString(3, employee.getEmail());
			preparedStataement.executeUpdate();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	//update employee
	public boolean updateEmployee(Employee employee) throws SQLException {
		boolean rowUpdated;
		try(Connection connection = getConnection();
				PreparedStatement stataement = connection.prepareStatement(UPDATE_EMPLOYEE)){
			stataement.setString(1, employee.getFirstName());
			stataement.setString(2, employee.getLastName());
			stataement.setString(3, employee.getEmail());
			stataement.setInt(4, employee.getId());
			rowUpdated = stataement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	
	//select by id
	public Employee selectEmployee (int id) {
		Employee employee = null;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMPLOYEE_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String email = rs.getString("email");
				employee = new Employee(id, firstName, lastName, email);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employee;
	}
	
	//select all
	public List<Employee> selectAllEmployee () {
		List<Employee> employees = new ArrayList<>();
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_EMPLOYEES);) {
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String email = rs.getString("email");
				employees.add(new Employee(id, firstName, lastName, email));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employees;
	}
	
	//delete
	public boolean deleteEmployee(int id) throws SQLException{
		boolean rowDeleted;
		try(Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_EMPLOYEE);){
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		
		return rowDeleted;
	}
}
