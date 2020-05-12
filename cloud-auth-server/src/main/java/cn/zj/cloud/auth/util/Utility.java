package cn.zj.cloud.auth.util;

public class Utility {

	public static boolean isEmpty(String str) {
		boolean checkResult = false;
		if(str == null || str.length() == 0) {
			checkResult = true; 
		}
		return checkResult;
	}
}
