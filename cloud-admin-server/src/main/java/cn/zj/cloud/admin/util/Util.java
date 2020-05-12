package cn.zj.cloud.admin.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
	public static String plainDate(Timestamp ts) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(ts);
	}
	
	public static Date toDate(String time){
		Date date = null;
		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			date = df.parse(time); 
		} catch(Exception e) {
			e.printStackTrace();
		}
		return date;
	}
}
