package com.iteamcn.AutoLearn.weblearn.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class Message {
	private HashMap<String,Object> message=new HashMap<String,Object>();
	public void addMessage(String key,Object value,boolean override){
		if(message.containsKey(key)&&override){
			message.put(key, value);
		}else if(!message.containsKey(key)){
			message.put(key, value);
		}
	}
	public void addMessage(String key,Object value){
		addMessage(key,value,false);
	}
	public Object getMessage(String key){
		return message.get(key);
	}
	public void removeMessage(String key){
		message.remove(key);
	}
	public void removeAllMessage() {
		message.clear();
	}
	public Set<String> messageKeySet() {
		return message.keySet();
	}
	public Collection<Object> messageSet(){
		return message.values();
	}
	@Override
	public String toString() {
		return "Message [message=" + message + "]";
	}
	
}
