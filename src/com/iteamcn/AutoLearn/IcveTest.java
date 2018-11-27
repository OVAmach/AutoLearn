package com.iteamcn.AutoLearn;

import java.util.List;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.json.JSONObject;

import com.iteamcn.AutoLearn.weblearn.icve.IcveHttpHandler;
import com.iteamcn.AutoLearn.weblearn.icve.api.ApiCode;
import com.iteamcn.AutoLearn.weblearn.icve.api.MessageCode;
import com.iteamcn.AutoLearn.weblearn.icve.api.args.AnswerPaperArgs;
import com.iteamcn.AutoLearn.weblearn.icve.api.args.LoginArgs;
import com.iteamcn.AutoLearn.weblearn.icve.api.entity.CellInfo;
import com.iteamcn.AutoLearn.weblearn.icve.api.entity.CourseInfo;
import com.iteamcn.AutoLearn.weblearn.icve.api.entity.QuestionInfo;
import com.iteamcn.AutoLearn.weblearn.icve.api.entity.UserInfo;
import com.iteamcn.AutoLearn.weblearn.icve.api.handler.AnswerPaperHandler;
import com.iteamcn.AutoLearn.weblearn.icve.api.handler.DirectoryHandler;
import com.iteamcn.AutoLearn.weblearn.icve.api.handler.LoginHandler;
import com.iteamcn.AutoLearn.weblearn.icve.api.handler.StudingHandler;
import com.iteamcn.AutoLearn.weblearn.icve.api.handler.SubmitPaperHandler;
import com.iteamcn.AutoLearn.weblearn.icve.api.handler.UpdateStatusHandler;
import com.iteamcn.AutoLearn.weblearn.icve.api.handler.VerifyCodeHandler;
import com.iteamcn.AutoLearn.weblearn.icve.api.handler.ViewHandler;
import com.iteamcn.AutoLearn.weblearn.icve.api.handler.local.PaperQuestionsHandler;
import com.iteamcn.AutoLearn.weblearn.util.InnerMessage;

public class IcveTest {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			IcveHttpHandler h=new IcveHttpHandler();
			VerifyCodeHandler v=new VerifyCodeHandler(h);
			LoginHandler login=new LoginHandler(h);
			StudingHandler s=new StudingHandler(h);
			DirectoryHandler d=new DirectoryHandler(h);
			ViewHandler view=new ViewHandler(h);
			UpdateStatusHandler up=new UpdateStatusHandler(h);
			AnswerPaperHandler ap=new AnswerPaperHandler(h);
			SubmitPaperHandler sp=new SubmitPaperHandler(h);
			PaperQuestionsHandler pq;
			Scanner in=new Scanner(System.in);
			JFrame jframe;
			{
				InnerMessage m = v.doHttp("",true);
				jframe = new JFrame();
				JLabel l=new JLabel("发生错误");
				if(m.getContent()!=null){
					ImageIcon i=new ImageIcon((byte[])m.getContent());
					l=new JLabel(i);
				}
				
				
				jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				jframe.add(l);
				jframe.setBounds(0, 0, 200, 200);
				jframe.setVisible(true);
				
			}
			System.out.print("用户名：");
			String username=in.next();
			in.nextLine();
			System.out.print("密码：");
			String password=in.next();
			in.nextLine();
			System.out.print("验证码：");
			String verifyCode=in.next();
			in.nextLine();
			jframe.dispose();
			InnerMessage m=new InnerMessage();

				m = login.doHttp(new LoginArgs(username, password,verifyCode ));

			UserInfo u=(UserInfo) m.getObject();
			System.out.println(u.getUserId());

				m=s.doHttp(u.getUserId(),true);

			List<CourseInfo> list = (List<CourseInfo>)m.getObject();
			for(CourseInfo course:list){
				m=d.doHttp(course.getCourseId(),true);
				List<CellInfo> cellList=(List<CellInfo>)m.getObject();
				for(CellInfo cell:cellList){
					//if(cell.getIsMission()){
						if(cell.getCellType().equals("question")){//交卷部分
							String workId=null;
							JSONObject json=null;
							m=view.doHttp(cell.getId(),true);
							if(m.getCode()==MessageCode.SUCCESS){
								json=(JSONObject)m.getObject();
								workId=json.getJSONObject("works").getString("Id");
								pq=new PaperQuestionsHandler(json);
								List<QuestionInfo> listQ=(List<QuestionInfo>) pq.doHandle().getObject();
								for(QuestionInfo q:listQ){
									m=ap.doHttp(new AnswerPaperArgs(workId, q.getQuestionId(), q.getAnswerContent()));
									if(((JSONObject)m.getObject()).getInt("code")!=ApiCode.ANSWER_PAPER_SUCCESS){
										System.out.println("保存答案失败");
										break;
									}
								}
								m=sp.doHttp(workId, true);
								if(((JSONObject)m.getObject()).getInt("code")!=ApiCode.SUBMIT_PAPER_SUCCESS){
									System.out.println("提交答案失败");
								}
							}
							continue;
						}else if(cell.getCellType().equals("video")){//看视频部分
							switch (cell.getIntResType()) {
							case 1:
								up.doHttp(cell.getId(),true);
								m=view.doHttp(cell.getId(),true);
								System.out.println(m.getCode());
								break;
								
							default:
								m=view.doHttp(cell.getId(),true);
								System.out.println(m.getCode());
								break;
							}
						}else{//其他部分默认处理
							m=view.doHttp(cell.getId(),true);
							System.out.println(m.getCode());
						}

					//}
					
				}
			}
			System.out.println(m.getContent());
			System.out.println(h.getCookies().getAllCookie());
			in.close();
		}catch(Exception e){
			e.printStackTrace();
		}

		
	}

}
