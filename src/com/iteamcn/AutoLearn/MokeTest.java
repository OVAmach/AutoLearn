package com.iteamcn.AutoLearn;
import java.util.List;
import java.util.Scanner;

import com.iteamcn.AutoLearn.weblearn.moke.Course;
import com.iteamcn.AutoLearn.weblearn.moke.LoginInfo;
import com.iteamcn.AutoLearn.weblearn.moke.Moke;



public class MokeTest {
	public static void main(String[] args) {
		Scanner in=new Scanner(System.in);
		Moke moke=new Moke();
		System.out.print("院校号：");
		int schoolNo=in.nextInt();
		in.nextLine();
		System.out.print("学号：");
		String studentNo=in.nextLine();
		System.out.print("密码：");
		String password=in.nextLine();
		in.close();
		LoginInfo loginInfo=new LoginInfo(schoolNo, studentNo, password, "studentNo", true);
		moke.login(loginInfo);
		try {
			List<Course> a = moke.getCourseList();
			for(int i=0;i<a.size();i++){

				moke.learnPlan(a.get(i).getId(), moke.getPlanInfo(a.get(i).getId()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
