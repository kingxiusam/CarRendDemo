package timetask;

import javax.servlet.http.HttpServlet;



public abstract class Get_sum_money {
	private static int basemonth_Money=500,eachMonth=1000;
	private static int baseday_Money=50,eachday=100;
	private static int sum_Money=0;
	
	public static void getTimeState(String rendtype, long rendtime) {
		// TODO Auto-generated method stub
		 if(rendtype.equals("����")){
			 sum_Money+=rendtime*eachday+baseday_Money;//����������
          }else{
        	  sum_Money+=rendtime*eachMonth+basemonth_Money;//����������
          }
	}
	public static int getMoney(){
		return sum_Money;
		
	}
}
