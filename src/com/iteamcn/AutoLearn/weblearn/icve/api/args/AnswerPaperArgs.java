package com.iteamcn.AutoLearn.weblearn.icve.api.args;

import com.iteamcn.AutoLearn.weblearn.util.FormApiArgs;

public class AnswerPaperArgs extends FormApiArgs {
	public AnswerPaperArgs(String works,String paperItemId,String answer){
		super("works",works
				,"paperItemId",paperItemId
				,"answer",answer);
	}
}
