package servlet;



import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;



import java.util.Timer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.SqlControule;
import config.TimeConfig;
import entity.Car;
import timetask.Get_sum_money;
;

public class Curstomers_RendCar extends HttpServlet{
	private Timer timer;
	/**
	 * 
	 */
	private static final long serialVersionUID = -863432487451755094L;

	/**
	 * Constructor of the object.
	 */
	public Curstomers_RendCar() {
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
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
       
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String carid=request.getParameter("carid_check");
		String rendtype=request.getParameter("rendtype");
		
		long rendtime=Long.parseLong(request.getParameter("rendtime"));
		HttpSession session=request.getSession();
		ServletContext application=this.getServletContext();//��application����application���ö���ʹ��
		
		if(carid!=null){
		//	ArrayList clist=(ArrayList)session.getAttribute("clist");//��ѡ�����б�
			ArrayList clist=(ArrayList)application.getAttribute("clist");//��ѡ�����б�
			ArrayList<Car> cars=(ArrayList)application.getAttribute("cars_update");//ҳ�������б�
			
		   if(clist==null){
            clist=new ArrayList();
            clist.add(carid);
            Get_sum_money.getTimeState(rendtype,rendtime);
         //   session.setAttribute("clist",clist);
              application.setAttribute("clist",clist);
                try {
                	// System.out.println(SqlControule.queryCar("CarId",carid).getCarname());
					cars.remove(SqlControule.queryCar("CarId",carid));
		             SqlControule.updateCar("CarId", carid,"CarState","�����");   
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            application.setAttribute("cars_update",cars);//�������б����Ƴ�
        	System.out.print(cars.size());
            TimeConfig.setTimer(cars, carid, rendtype, timer, rendtime);//��Timer��ʱ������������������ض�ʱ��ִ��ָ������
            response.sendRedirect("/CarRendDemo/Curstomers/Curstomers_query_success.jsp?isSuccess=yes");    
            
		}else{
			
			if(!clist.contains(carid)){
				clist.add(carid);
				//session.setAttribute("clist",clist);
	            application.setAttribute("clist",clist);

				Get_sum_money.getTimeState(rendtype,rendtime);
				/* �˴��ɶ����ڽ����жϣ��ж��������Ƿ��ѵ��ڣ���δ���ڽ��������Ƴ��������Ƴ����ֽӼ�״̬*/
//				
				try {
					cars.remove(SqlControule.queryCar("CarId",carid));
					SqlControule.updateCar("CarId", carid,"CarState","�����");   
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				application.setAttribute("cars_update",cars);
				System.out.print(cars.size());
		           //�������б����Ƴ���ֱ�����������޵���
			    TimeConfig.setTimer(cars, carid, rendtype, timer, rendtime);//��Timer��ʱ������������������ض�ʱ��ִ��ָ������
			    response.sendRedirect("/CarRendDemo/Curstomers/Curstomers_query_success.jsp?isSuccess=yes");    
				 
				 
			 }else{
				  response.sendRedirect("/CarRendDemo/Curstomers/Curstomers_query_success.jsp?isOver=yes");
			 }
		}
	 }
		session.setAttribute("sum_money",Get_sum_money.getMoney());
   }

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
		timer=new Timer();
	}

	

}
