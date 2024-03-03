package com.web.commonUtils;

import java.util.Date;
import java.util.Random;

public class JavaUtility 
{
	public int getRanDomNumber()
	{
		
		Random random = new Random();
		int Random = random.nextInt(10000);
		return Random;
	}
	
	public String getSystemdateAndTime()
	{
		Date date = new Date();
		return date.toString();
	}
	
	public String getSystemDateWithFormate()
	{
		Date date = new Date();
		String dateAndTime = date.toString();
		System.out.println(dateAndTime);
		String YYYY = dateAndTime.split(" ")[5];
		 String DD = dateAndTime.split(" ")[2];
		 int MM = date.getMonth()+1;
		 
		 String finalFormate = DD +"-"+MM+"-"+YYYY;
		 return finalFormate;
	}

}
