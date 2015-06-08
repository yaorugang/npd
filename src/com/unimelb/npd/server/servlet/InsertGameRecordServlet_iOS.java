package com.unimelb.npd.server.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.unimelb.npd.server.dao.IGameDao;
import com.unimelb.npd.server.dao.IPatientDao;
import com.unimelb.npd.server.dao.impl.GameDaoImpl;
import com.unimelb.npd.server.dao.impl.PatientDaoImpl;
import com.unimelb.npd.server.vo.Game;

/**
 * Servlet implementation class InsertGameRecordServlet_iOS
 */
@WebServlet("/InsertGameRecordServlet_iOS")
public class InsertGameRecordServlet_iOS extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertGameRecordServlet_iOS() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		IGameDao gameDao = new  GameDaoImpl();
		IPatientDao patientDao = new PatientDaoImpl();
		int  pid = Integer.parseInt(request.getParameter("pid").toString());
		int  gid = Integer.parseInt(request.getParameter("gid").toString());
		int  level = Integer.parseInt(request.getParameter("level").toString());
		int  time = Integer.parseInt(request.getParameter("time").toString());
		int  score = Integer.parseInt(request.getParameter("score").toString());
		String date = request.getParameter("date").toString();
		int  percent = Integer.parseInt(request.getParameter("percent").toString());
		int  accuracy = Integer.parseInt(request.getParameter("accuracy").toString());
		String col = null;
		
		switch(gid)
		{
		case 1:
			col = "pipe_level";
			break;
		case 2:
			col = "ball_level";
			break;
		case 3:
			col = "balloon_level";
			break;
		case 4:
			col = "breakout_level";
			break;
		case 5:
			col = "poker_level";
			break;
		case 6:
			col = "color_level";
			break;
		}
		
		JSONObject json = new JSONObject();
		
//		String result = null;
		int effect = patientDao.updatePatient(pid, col, level);
//		result = effect > 0 ? "UpdatePatient" : "F";
		
		json.put("Effect", effect > 0 ? "UpdatePatient" : "F");
		
		Game game = new Game();
		game.setGid(gid);
		game.setPid(pid);
		game.setLevel(level);
		game.setScore(score);
		game.setTime(time);
		game.setAccuracy(accuracy);
		game.setDate(date);
		game.setPercent(percent);
		int insert = gameDao.addGame(game);
//		result += insert>0?"AddGame":"F";
		
		json.put("Insert", insert > 0 ? "AddGame" : "F");
		
		PrintWriter out = response.getWriter();
		out.println(json.toString());
		
//		response.getOutputStream().write(result.getBytes());	
	}

}
