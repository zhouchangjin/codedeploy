package com.harmonywisdom.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class JSONBasedObject {
	
	
	public String className;
	
	public String toString(){
		return JSON.toJSONString(this);
	}
	
	public String getClassName() {
		if(className==null || "".equals(className)){
			className=this.getClass().getName();
		}
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public static Object parse(String text){
		try {
			String className=JSON.parseObject(text).getString("className");
			return JSON.parseObject(text, Class.forName(className));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
			
		}
	}
	
	public static Object parse(JSONObject object){
		try {
			String className=object.getString("className");
			return JSON.parseObject(object.toJSONString(), Class.forName(className));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
			
		}
	}

}
