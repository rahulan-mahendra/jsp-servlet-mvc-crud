package net.assignment.employee.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.assignment.employee.dao.EmployeeDAO;
import net.assignment.employee.model.Employee;
/**
 * Servlet implementation class EmployeeServlet
 */
@WebServlet("/")
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EmployeeDAO employeeDAO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeServlet() {
    	this.employeeDAO = new EmployeeDAO();
    	
    }
    
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	this.doGet(request, response);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertEmployee(request, response);
				break;
			case "/delete":
				deleteEmployee(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateEmployee(request, response);
				break;
			default:
				listEmployee(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("employee-form.jsp");
		dispatcher.forward(request, response);
	}
	
	private void insertEmployee(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String firstName = request.getParameter("first_name");
		String lastName = request.getParameter("last_name");
		String email = request.getParameter("email");
		Employee newEmployee = new Employee(firstName, lastName, email);
		employeeDAO.createEmployee(newEmployee);
		response.sendRedirect("list");
	}

	private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		employeeDAO.deleteEmployee(id);
		response.sendRedirect("list");
	}
	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Employee existingEmployee = employeeDAO.selectEmployee(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("employee-form.jsp");
		request.setAttribute("employee", existingEmployee);
		dispatcher.forward(request, response);

	}
	
	private void updateEmployee(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String firstName = request.getParameter("first_name");
		String lastName = request.getParameter("last_name");
		String email = request.getParameter("email");

		Employee employee = new Employee(id, firstName, lastName, email);
		employeeDAO.updateEmployee(employee);
		response.sendRedirect("list");
	}
	
	private void listEmployee(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Employee> listEmployee = employeeDAO.selectAllEmployee();
		request.setAttribute("listEmployee", listEmployee);
		RequestDispatcher dispatcher = request.getRequestDispatcher("employee-list.jsp");
		dispatcher.forward(request, response);
	}
}
