package timetask;



import java.sql.SQLException;
import java.util.ArrayList;
import java.util.TimerTask;

import bean.SqlControule;

public class Task extends TimerTask{
	private ArrayList cars;
	private String carid;
	
	
	public Task(){
		
	}
	
    public Task(ArrayList cars,String carid){
    	this.carid=carid;
    	this.cars=cars;
    	
    }
    
    
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		System.out.print("�����б���ʾ�ָ�");
        cars.add(carid);
        try {
			SqlControule.updateCar("CarId", carid,"CarState","δ����");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
		
		
	
	}

	

	

}
