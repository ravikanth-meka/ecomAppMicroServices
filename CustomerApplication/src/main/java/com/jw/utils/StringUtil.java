package com.jw.utils;

public class StringUtil {

	
	public static boolean isNotNullorNotBlank(String s)
	{
		if (s!=null && s.trim()!="")
			return true;
		else
			return false;
	}
}
