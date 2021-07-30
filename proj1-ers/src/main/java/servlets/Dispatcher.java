package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;

import controllers.HomeController;
import controllers.LoginController;
import controllers.ReimbursementController;

public class Dispatcher {

	public static void myVirtualRouter(HttpServletRequest req, HttpServletResponse res)
			throws JsonProcessingException, IOException, ServletException {
		
//		System.out.println("\n\n\t\tIn MyDispatcher (myVirtualRouter())");
//		System.out.println("Current URL: "+req.getRequestURL());
//		System.out.println("Current URI: "+req.getRequestURI());
		
		
		switch(req.getRequestURI()) {
		case "/proj1-ers/forward/login":
			LoginController.login(req, res);
			break;
		case "/proj1-ers/home":
			HomeController.home(req, res);
			break;
		case "/proj1-ers/json/allUserReim":
			ReimbursementController.allUserReim(req, res);
			break;
		case "/proj1-ers/json/allReim":
			ReimbursementController.allReim(req, res);
			break;
		case "/proj1-ers/db/createReim":
			ReimbursementController.createReim(req, res);
			break;
		case "/proj1-ers/db/resolveReim":
			ReimbursementController.resolveReim(req, res);
			break;
		case "/proj1-ers/json/getUser":
			ReimbursementController.getUser(req, res);
			break;
		default:
			System.out.println("Dude, you gave me a bad URI.");
			req.getRequestDispatcher("/resources/html/badlogin.html").forward(req, res);
			return;
			
		}
	}
}