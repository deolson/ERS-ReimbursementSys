package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name="MasterServlet", urlPatterns= {"/home/*", "/forward/*", "/json/*", "/db/*"})
public class MasterServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		System.out.println("MASTER SERVLET: In the doGet method!!!");
		Dispatcher.myVirtualRouter(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		System.out.println("MASTER SERVLET: In the doPost method!!!");
		doGet(req, resp);
	}
}
