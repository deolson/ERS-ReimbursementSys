package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.UserServiceImpl;
import models.User;
import models.UserRole;

public class LoginController {
	
	
	public static void login(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		UserServiceImpl myUserDao = new UserServiceImpl();
		String myPath = null;
		
		//extracting the form data
		String username = req.getParameter("username");
		String password = req.getParameter("uPassword");
		
		if(!req.getMethod().equals("POST") || username==null || password==null){
			myPath = "/home";
			req.getRequestDispatcher(myPath).forward(req, res);
			return;
		}
		
		
		User user = myUserDao.getUser(username,password);
		if(user != null) {
			//Successful Login
			System.out.println("LOGGED IN");
			req.getSession().setAttribute("user", user);
			System.out.println(req.getSession().getAttribute("user"));
			if(user.getUserRole()==UserRole.FINANCE_MANAGER) {
				myPath = "/resources/html/manager.html";
				req.getRequestDispatcher(myPath).forward(req, res);
			} else {				
				myPath = "/resources/html/employee.html";
				req.getRequestDispatcher(myPath).forward(req, res);
			}
			return;
		}else {
			//Bad Login
			myPath = "/home";
			req.getRequestDispatcher(myPath).forward(req, res);
			return;
		}
	}
}
