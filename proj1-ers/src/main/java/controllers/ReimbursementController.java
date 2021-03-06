package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import models.*;

import service.ReimbursementServiceImpl;
import service.UserServiceImpl;

public class ReimbursementController  {

	public static void allReim(HttpServletRequest req, HttpServletResponse res)
			throws IOException, JsonProcessingException {
		
		HttpSession sess = req.getSession();
		UserServiceImpl myUserServ = new UserServiceImpl();
		PrintWriter printer = res.getWriter();
		
		List<Reimbursement> userReims = myUserServ.viewAllRequests();	
		printer.write(new ObjectMapper().writeValueAsString(userReims));
	}
	
	public static void allUserReim(HttpServletRequest req, HttpServletResponse res)
			throws IOException, JsonProcessingException {
		
		HttpSession sess = req.getSession();
		UserServiceImpl myUserServ = new UserServiceImpl();
		PrintWriter printer = res.getWriter();
		
		List<Reimbursement> userReim = myUserServ.viewUserRequests((User)sess.getAttribute("user"));	
		printer.write(new ObjectMapper().writeValueAsString(userReim));
	}
	
	public static void createReim(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		ReimbursementServiceImpl myReimServ = new ReimbursementServiceImpl();
		HttpSession sess = req.getSession();
		ObjectMapper mapper = new ObjectMapper();
		
		Reimbursement userReimReq = mapper.readValue(req.getInputStream(), Reimbursement.class);
		userReimReq.setAuthor((User)sess.getAttribute("user"));
		
		myReimServ.createReimbursement(userReimReq);
	}
	
	public static void resolveReim(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		ReimbursementServiceImpl myReimServ = new ReimbursementServiceImpl();
		HttpSession sess = req.getSession();
		ObjectMapper mapper = new ObjectMapper();
		
		Reimbursement resolvedReim = mapper.readValue(req.getInputStream(), Reimbursement.class);
		System.out.println(resolvedReim);
		
		myReimServ.resolveReimbursement(resolvedReim.getReimbId(),resolvedReim.getrStatus(),(User)sess.getAttribute("user"));
	}
	
	public static void getUser(HttpServletRequest req, HttpServletResponse res)
			throws IOException, JsonProcessingException {
		
		HttpSession sess = req.getSession();
		PrintWriter printer = res.getWriter();
		User user = (User)sess.getAttribute("user");
		user.setPassword("");
		printer.write(new ObjectMapper().writeValueAsString(user));
	}
}
