package servlet;

import java.io.IOException;
import java.io.PrintWriter;



import java.sql.SQLException;



import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.SqlControule;

public class Users_login extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	/**
	 * Constructor of the object.
	 */
	public Users_login() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	

		/*��ȡ��¼����������û���������,��֤��*/
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String uyzm=request.getParameter("uyzm");//��֤������
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		
		//ͨ��request���session
		HttpSession session=request.getSession();
		session.setAttribute("uname",username);
		String yzm=session.getAttribute("yzm").toString();
		
//		SqlControule sqlobj = null;
//		try {
//			sqlobj = new  SqlControule();
//		} catch (ClassNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		
		
		
		
		/*�Ի�ȡ�ı����ݽ����ж� */
		//�ж��û�������
		
    try { 
    	if(!username.equals("")&&!password.equals("")&&!uyzm.equals("")){
    		
           // if(password.equals(sqlobj.queryUser(username).getPassword())&&uyzm.equals(yzm)){
    		if(password.equals(SqlControule.getSqlObj().queryUser(username).getPassword())&&uyzm.equals(yzm)){
		       response.sendRedirect("/CarRendDemo/Users_login_success.jsp");
		  }
           else{
        	  response.sendRedirect("/CarRendDemo/Users_login.jsp?isError=yes");
	
		     
		    }
            
    }else{
    	  response.sendRedirect("/CarRendDemo/Users_login.jsp?isNull=yes");
    	 // out.write("<script language='javascript'>alert('�����������¼��Ϣ')</script>");
    	  //response.sendRedirect("/CarRendDemo/Users_login.jsp");
    	 
    }
    	
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	
		
		
	}
	
	

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
		
	}

}
