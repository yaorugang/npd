package com.unimelb.npd.server.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import com.unimelb.npd.server.dao.IPatientDao;
import com.unimelb.npd.server.dao.impl.PatientDaoImpl;
import com.unimelb.npd.server.vo.Patient;

/**
 * Servlet implementation class LoginServlet_iOS
 */
@WebServlet("/LoginServlet_iOS")
public class LoginServlet_iOS extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet_iOS() 
    {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String username = request.getParameter("username").toString();
		IPatientDao userDao = new PatientDaoImpl();
		Patient user = new Patient();
		user  = userDao.checkLogin(username);

		JSONObject json = new JSONObject();
		try
		{
			json.put("patientID", user.getPid());
			json.put("patientName", user.getPatient_name());
			json.put("patientAge", user.getAge());
			json.put("pipeLevel", user.getPipe_level());
			json.put("ballLevel", user.getBall_level());
			json.put("balloonLevel", user.getBalloon_level());
			json.put("breakoutLevel", user.getBreakout_level());
			json.put("pokerLevel", user.getPoker_level());
			json.put("colorLevel", user.getPoker_level());
		} 
		catch (JSONException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PrintWriter out = response.getWriter();
		out.println(json.toString());
	}

}
