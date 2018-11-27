package com.iteamcn.AutoLearn;

public class Main {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		if(args.length==1){
			switch(args[0]){
				case "-icve":{
					IcveTest.main(null);
					break;
				}case "-moke":{
					MokeTest.main(null);
					break;
				}default:{
					System.out.println("unknown options");
				}
			}
		}else{
			System.out.println("use option -<icve/moke> to run this application");
		}
		System.out.println("end");
	}

}
