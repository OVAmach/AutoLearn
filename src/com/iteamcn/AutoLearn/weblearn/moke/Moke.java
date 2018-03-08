package com.iteamcn.AutoLearn.weblearn.moke;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.CookieStore;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.util.URIUtil;
import org.json.JSONArray;
import org.json.JSONObject;


public class Moke {
	static final String HOST="student.zjedu.moocollege.com";
	static final String LOGIN_API_URL="http://student.zjedu.moocollege.com/nodeapi/3.0.1/student/system/login";
	static final String LOGIN_REFERER_URL="http://student.zjedu.moocollege.com/system/login";
	static final String COURSE_LIST_API_URL="http://student.zjedu.moocollege.com/nodeapi/3.0.1/student/course/system/list";
	static final String PLAN_LIST_API_URL="http://student.zjedu.moocollege.com/nodeapi/3.0.1/student/course/plan/list";
	static final String UNIT_DETAIL_API_URL="http://student.zjedu.moocollege.com/nodeapi/3.0.1/teacher/course/plan/unit/getDetail";
	static final String UPLOAD_LEARN_RATE_API_URL="http://student.zjedu.moocollege.com/nodeapi/3.0.1/student/course/uploadLearnRate";
	static final String PLAYINFO_API_URL="http://p.bokecc.com/servlet/playinfo";
	static final String USER_AGENT="Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.90 Safari/537.36 OPR/47.0.2631.71 (Edition Baidu)";
	private String cookie;
	private String token;
	private JSONObject loginData;
	private JSONObject planInfo;
	private List<Course> courseList=new ArrayList<Course>();
	public boolean login(LoginInfo loginInfo){
		try{
			cookie=getCookies();
//			System.out.println(loginInfo.toJson());
			String p = post(LOGIN_API_URL,loginInfo.toJson());
//			System.out.println(p);
			JSONObject response=new JSONObject(p);
//			System.out.println(json);
			if(checkResponse(response)){
				System.out.println("登陆成功");
				loginData=response.getJSONObject("data");
				token="%22"+loginData.getString("token")+"%22";
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	public List<Course> getCourseList() throws Exception {
		if(loginData==null) throw new Exception("你还没有登陆");
		try{
			JSONObject json=new JSONObject();
			json.put("status", "");
			json.put("search", "");
			json.put("current", 1);
			json.put("pageSize", 12);
			String p=post(COURSE_LIST_API_URL,json.toString());
			JSONObject response=new JSONObject(p);
			if(checkResponse(response)){
				JSONObject data = response.getJSONObject("data");
				int total = data.getInt("total");
				JSONArray dataList = data.getJSONArray("dataList");
				courseList.clear();
				for(int i=0;i<dataList.length();i++){
					JSONObject course = dataList.getJSONObject(i);
					courseList.add(new Course(course));
				}
				System.out.println("共读取"+total+"门课程");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return courseList;
	}
	public JSONObject getPlanInfo(int courseId) throws Exception{
		if(loginData==null) throw new Exception("你还没有登陆");
		try{
			JSONObject json=new JSONObject();
			json.put("courseId", ""+courseId);
			String p=post(PLAN_LIST_API_URL,json.toString());
//			System.out.println(p);
			JSONObject response=new JSONObject(p);
			if(checkResponse(response)){
				planInfo = response;
				System.out.println("共读取"+planInfo.length()+"段章节");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return planInfo;
	}
	public void learnPlan(final int courseId,JSONObject json){
//		System.out.println(json.toString());
		if(json.has("name"))
			System.out.println(json.getString("name"));
		if(json.has("data")){
			JSONArray jsa = json.getJSONArray("data");
			for(int i=0;i<jsa.length();i++){
				final JSONObject jso = jsa.getJSONObject(i);
				Thread t=new Thread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						learnPlan(courseId,jso);
					}
				});
				t.start();
			}
		}else{
			Plan plan=new Plan(json);

			if(plan.getType()==1){
				int time = getVideoTime(getVideoID(plan.getUnitId()));
				for(int i=0;i<time;i+=10){
					JSONObject j = new JSONObject();
					j.put("playPosition", i);
					j.put("courseId",courseId);
					j.put("unitId", plan.getUnitId());
					try {
						JSONObject a =new JSONObject(post(UPLOAD_LEARN_RATE_API_URL,j.toString()));
//						System.out.println(a);
						if(checkResponse(a)){
						}
					} catch (Exception e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
				}
				JSONObject j = new JSONObject();
				j.put("playPosition", time);
				j.put("courseId",courseId);
				j.put("unitId", plan.getUnitId());
				try {
					JSONObject a =new JSONObject(post(UPLOAD_LEARN_RATE_API_URL,j.toString()));
//					System.out.println(a);
					if(checkResponse(a)){
					}
				} catch (Exception e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}else if(plan.getType()==3){
				JSONObject j = new JSONObject();
				j.put("playPosition", 0);
				j.put("courseId",courseId);
				j.put("unitId", plan.getUnitId());
				try {
					JSONObject a =new JSONObject(post(UPLOAD_LEARN_RATE_API_URL,j.toString()));
					if(checkResponse(a)){
					}
				} catch (Exception e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
		}
	}
	private String getVideoID(int unidId){
		try {
			JSONObject json=new JSONObject();
			json.put("unitId", unidId);
			String p = post(UNIT_DETAIL_API_URL, json.toString());
			JSONObject response=new JSONObject(p);
//			System.out.println(response.toString());
			return response.getJSONObject("data").getJSONObject("data").getString("videoId");
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}
	private int getVideoTime(String videoId){
		try{
			String s = get(PLAYINFO_API_URL,"vid="+videoId+"&m=1");
//			duration="591"
			String pattern="(duration=)\"(\\d+)\"";
			Pattern r = Pattern.compile(pattern);
			Matcher m = r.matcher(s);
			if(m.find()){
				return Integer.parseInt(m.group(2));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0;

	}
	public static boolean checkResponse(JSONObject response){
//		System.out.println(response.toString());
		int code=(response.get("code") instanceof Integer)?(int)response.get("code"):0;
		String message=(response.get("message") instanceof String)?(String)response.get("message"):null;
		if(code==20000){
			return true;
		}else{
			System.out.println(code+message);
			return false;
		}
	}
	public String post(String url,String json) throws Exception{
        HttpClient httpClient = new HttpClient();  
        PostMethod post = new PostMethod(url);  
        post.setRequestHeader("Accept","*/*");
//        post.setRequestHeader("Accept-Encoding","gzip, deflate");
        post.setRequestHeader("Accept-Language","zh-cn,zh;q=0.8");
        post.setRequestHeader("Connection","keep-alive");
        post.setRequestHeader("Content-Length",json.length()+"");
        
        post.setRequestHeader("Content-Type","application/json");  
        String cookie="NOISSESUDEJZXJ="+this.cookie+";";
        if(token!=null)cookie+="; token-student-zjedu="+token+";";
        post.setRequestHeader("Cookie","NOISSESUDEJZXJ="+cookie+"; token-student-zjedu="+token+";"); 
        post.setRequestHeader("Host",HOST);  
        
        post.setRequestHeader("Origin","http://"+HOST);
        post.setRequestHeader("User-Agent",USER_AGENT);
        RequestEntity entity = new StringRequestEntity(json, "application/json", "utf-8");  
        post.setRequestEntity(entity);  
        httpClient.executeMethod(post);  
        if(post.getStatusCode()==200){
            BufferedReader reader = new BufferedReader(new InputStreamReader(post.getResponseBodyAsStream()));  
            StringBuffer stringBuffer = new StringBuffer();  
            String str = "";  
            while((str = reader.readLine())!=null){  
                stringBuffer.append(str);  
            }  
            String ts = stringBuffer.toString();  
            return ts;
        }else{
        	throw new Exception(post.getStatusCode()+post.getStatusText());
        }
	}
	public String get(String url,String queryString) throws Exception{
        HttpClient httpClient = new HttpClient();  
        GetMethod get=new GetMethod(url);
        get.setQueryString(URIUtil.encodeQuery(queryString));   
        get.setRequestHeader("Accept","*/*");
//        get.setRequestHeader("Accept-Encoding","gzip, deflate");
        get.setRequestHeader("Accept-Language","zh-cn,zh;q=0.8");
        get.setRequestHeader("Connection","keep-alive");
        get.setRequestHeader("Host",HOST);
        get.setRequestHeader("User-Agent",USER_AGENT);
        httpClient.executeMethod(get);
        if(get.getStatusCode()==200){
            BufferedReader reader = new BufferedReader(new InputStreamReader(get.getResponseBodyAsStream()));  
            StringBuffer stringBuffer = new StringBuffer();  
            String str = "";  
            while((str = reader.readLine())!=null){  
                stringBuffer.append(str);  
            }  
            String ts = stringBuffer.toString();  
            return ts;
        }else{
        	throw new Exception(get.getStatusCode()+get.getStatusText());
        }
	}
	private static String getCookies() throws IOException {
        CookieManager manager=new CookieManager();
        manager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        CookieHandler.setDefault(manager);
        URL url=new URL("http://student.zjedu.moocollege.com/system/login");
        HttpURLConnection conn= (HttpURLConnection) url.openConnection();
        conn.getHeaderFields();
        CookieStore store = manager.getCookieStore();
        String cookies = store.getCookies().toString().split("=")[1];
//        cookies = cookies.replace("[", "");
//        cookies = cookies.replace("]", "");
        return cookies;
    }
}
