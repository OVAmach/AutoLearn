package com.iteamcn.AutoLearn.weblearn.icve.api.args;

import com.iteamcn.AutoLearn.weblearn.util.FormApiArgs;

public class LoginArgs extends FormApiArgs {
	public LoginArgs(String username,String pwd,String verifycode){
		super(	"username",username,
				"pwd",pwd,
				"verifycode",verifycode);
	}
}
