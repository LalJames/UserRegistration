package Servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.*;
import java.sql.*;


@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			try {
				PrintWriter out = response.getWriter();
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wenoxo", "root", "laljames003@");
				String email = request.getParameter("email");
				String password = request.getParameter("password");
				PreparedStatement ps = con.prepareStatement("select * from Register where Email =? and PasWord = ?");
				ps.setString(1, email);
				ps.setString(2, password);
				ResultSet rs = ps.executeQuery();
				if(rs.next()) 
				{
					RequestDispatcher rd = request.getRequestDispatcher("ProfilePage.jsp");
					rd.forward(request, response);
				}
				else
				{
					out.println("<font color=red size=12>Login Failed!!<br>");
					out.println("<p> Don't have an account? <a href = Registration.jsp> Register Now!</a> </p>");				
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
	}
}
