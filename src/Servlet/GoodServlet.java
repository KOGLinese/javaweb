package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Info.AnswerInfo;
import dao.AnswerDao;
import dao.UserDao;

/**
 * Servlet implementation class GoodServlet
 */
@WebServlet("/GoodServlet")
public class GoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoodServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		String mobile=request.getParameter("answer_id");
		String user_id=request.getParameter("user_id");
		UserDao udao=new UserDao();
		try {
			udao.addscore(user_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int answer_id= Integer.parseInt(mobile);
		AnswerInfo answer=new AnswerInfo();
		AnswerDao adao=new AnswerDao();
		PrintWriter out= response.getWriter();
		boolean flag = false;
		try {
			flag = adao.HadGood(answer_id, user_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(flag==false)
		{
			
			try {
				answer=adao.GetGood(answer_id,user_id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(answer==null) {
				out.write("#|#");//servlet以输出流的方式将信息返回给客户
			}else {
				int score=answer.getAnswer_score();
				System.out.println(score);
				out.write(answer_id+"|"+score+"");
				
			}
		}
		else {
			out.write("#|#");//说明用户点赞过
		}
		
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
