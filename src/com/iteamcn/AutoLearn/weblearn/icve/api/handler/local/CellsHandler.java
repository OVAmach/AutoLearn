package com.iteamcn.AutoLearn.weblearn.icve.api.handler.local;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.iteamcn.AutoLearn.weblearn.icve.api.MessageCode;
import com.iteamcn.AutoLearn.weblearn.icve.api.entity.CellInfo;
import com.iteamcn.AutoLearn.weblearn.util.InnerMessage;
import com.iteamcn.AutoLearn.weblearn.util.JsonHandler;

public class CellsHandler extends JsonHandler {

	public CellsHandler(JSONObject json) {
		super(json);
		// TODO 自动生成的构造函数存根
	}

	@Override
	public InnerMessage doHandle() throws JSONException{
		// TODO 自动生成的方法存根
		InnerMessage message=new InnerMessage();
		List<CellInfo> list=new ArrayList<CellInfo>();
		JSONArray a=json.getJSONArray("directory");
		for(int i=0;i<a.length();i++){
			JSONArray b = a.getJSONObject(i).getJSONArray("chapter");
			for(int j=0;j<b.length();j++){
				try{
				JSONArray c=b.getJSONObject(j).getJSONArray("cell");
				for(int k=0;k<c.length();k++){
					list.add(new CellInfo(c.getJSONObject(k)));
				}
				}catch(Exception e){
					System.out.println(b.toString());
					e.printStackTrace();
				}
			}
		}
		message.setObject(list);
		message.setCode(MessageCode.SUCCESS);
		return message;
	}

}
