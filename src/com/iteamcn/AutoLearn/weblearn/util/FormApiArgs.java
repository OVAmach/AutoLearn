package com.iteamcn.AutoLearn.weblearn.util;

import java.util.HashMap;

public class FormApiArgs extends ApiArgs{
	private HashMap<String,String> args=new HashMap<String,String>();
	public FormApiArgs(String... args){
		if(args.length%2!=0){
			throw new IllegalArgumentException("Argument Length not match");
		}else{
			for(int i=0;i<args.length;i+=2){
				this.args.put(args[i], args[i+1]);
			}
			
		}
	}
	@Override
	public String argsToString() {
		// TODO 自动生成的方法存根
		String s="";
		for(String key:args.keySet()){
			s+=key+"="+args.get(key)+"&";
		}
		s=s.substring(0,s.length()-1);
		return s;
	}
}
