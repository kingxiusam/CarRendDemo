package config;

import java.util.ArrayList;
import java.util.Timer;

import timetask.Task;

public class TimeConfig {
     
	
public static void setTimer(ArrayList cars,String carid,String rendtype,Timer timer,long rendtime){
	
	if(rendtype.equals("����")){
        timer.schedule(new Task(cars,carid),rendtime*24*3600*1000);//����������
     }else{
     	timer.schedule(new Task(cars,carid),30*rendtime*24*3600*1000);//����������
     }
	
	}
	
}
