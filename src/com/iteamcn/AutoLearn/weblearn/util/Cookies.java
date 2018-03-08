package com.iteamcn.AutoLearn.weblearn.util;

import java.util.HashMap;

public class Cookies {
	private HashMap<String,String> cookie=new HashMap<String,String>();
	/**
	 * @param key Cookie的key值
	 * @return 返回指定key值的Cookie值
	 */
	public String getCookie(String key){
		return cookie.get(key);
	}
	/**
	 * @param key Cookie的key值
	 * @param value Cookie的内容
	 * @param override 是否覆写存在的Cookie
	 */
	public void setCookie(String key,String value,boolean override){
		if(cookie.containsKey(key)&&override){
			cookie.put(key, value);
		}else if(!cookie.containsKey(key)){
			cookie.put(key, value);
		}
	}
	/**
	 * {@link HttpHandler#setCookie(String, String, boolean)}
	 * 不覆写已经存在的Cookie
	 */
	public void setCookie(String key,String value){
		setCookie(key,value,false);
	}
	/**
	 * @param key 要删除的Cookie的key值
	 */
	public void removeCookie(String key){
		cookie.remove(key);
	}
	
	/**
	 * @return 返回所有存在的Cookie
	 */
	public String getAllCookie(){
		String s="";
		for(String key:cookie.keySet()){
			s+=key+"="+cookie.get(key)+";";
		}
		return s;
	}
	/**
	 * @return 返回当前的CookieMap
	 */
	public HashMap<String,String> getCookieMap(){
		return cookie;
	}
}
