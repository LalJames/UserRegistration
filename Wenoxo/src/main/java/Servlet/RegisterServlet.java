package Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.*;
import java.sql.*;


@WebServlet("/Registration")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			PrintWriter out = response.getWriter();
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wenoxo", "root", "laljames003@");
			String username = request.getParameter("username");
			String email = request.getParameter("email");
			String password = request.getParameter("password");			
			
			PreparedStatement ps = con.prepareStatement("insert into Register values (?,?,?) ");
			ps.setString(1, username);
			ps.setString(2, email);
			ps.setString(3, password);			
			int i = ps.executeUpdate();
			if (i>0) {			
				out.println("<font color=green size=12>Register Sucessfull!!<br>");
				out.println("<a href=Login.jsp> Login </a>");
			}
			else {
				out.println("<font color=red size=12>Register UnSucessfull<br>");
				out.println("<a href = Registration.jsp> Retry!</a> </p>");
				}
			
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
}


