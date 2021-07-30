package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class HomeController {

	public static void home(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		HttpSession sess = req.getSession();
		sess.invalidate();
		String myPath = "/index.html";
		System.out.println(myPath);
		req.getRequestDispatcher(myPath).forward(req, res);
	}
}