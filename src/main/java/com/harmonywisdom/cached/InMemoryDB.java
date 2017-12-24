package com.harmonywisdom.cached;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.harmonywisdom.model.JSONBasedObject;

public class InMemoryDB {
	String dbName;
	public InMemoryDB(String dbName){
		this.dbName=dbName;
		initialize();
	}
	
	
	
	private void initialize(){
		String path=new File("").getAbsolutePath()+"/"+dbName+".db";
		File f=new File(path);
		if(f.exists()){
			try {
				FileReader fi=new FileReader(f);
				char b[]=new char[1024];
				int readSize=0;
				StringBuffer sb=new StringBuffer();
				while((readSize=fi.read(b))>0){
					sb.append(b,0,readSize);
				}
				JSONObject object=JSON.parseObject(sb.toString());
				Iterator<String> it=object.keySet().iterator();
				while(it.hasNext()){
					String keyName=it.next();
					map.put(keyName,(JSONBasedObject)JSONBasedObject.parse(object.getJSONObject(keyName)));
				}
				fi.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public ConcurrentHashMap<String, JSONBasedObject> map=new ConcurrentHashMap<String,JSONBasedObject>();
	
	public synchronized void put(String key,JSONBasedObject value){
		map.put(key, value);
		persist();
	}
	
	public synchronized void remove(String key){
		map.remove(key);
		persist();
	}
	
	public JSONBasedObject get(String key){
		if(map.containsKey(key)){
			return map.get(key);
		}
		return null;
	}
	
	public int countPage(long pos,int pageSize){
		int currentPage=(int)(pos-1)/pageSize+1;
		return currentPage;
	}
	
	public PageObject getPage(int page,int pageSize){
		PageObject p=new PageObject();
		List<JSONBasedObject> list=new ArrayList<JSONBasedObject>();
		Iterator<String> it=map.keySet().iterator();
		p.setTotal(map.keySet().size());
		
		long total=p.getTotal();
		int totalPage=countPage(total,pageSize);
		p.setMaxPage(totalPage);
		int counter=0;
		while(it.hasNext()){
			counter++;
			if(countPage(counter,pageSize)==page){
				list.add(map.get(it.next()));
			}else if(countPage(counter,pageSize)>page){
				break;
			}else{
				it.next();
			}
			
		}
		p.setPageList(list);
		return p;
	}
	
	public List<JSONBasedObject> get(){
		List<JSONBasedObject> list=new ArrayList<JSONBasedObject>();
		Iterator<String> it=map.keySet().iterator();
		while(it.hasNext()){
			list.add(map.get(it.next()));
		}
		return list;
	}
	
	private void persist(){
		String path=new File("").getAbsolutePath()+"/"+dbName+".db";
		File f=new File(path);
		if(f.exists()){
			f.delete();
		}
		try {
				FileOutputStream fos=new FileOutputStream(path);
				fos.write(JSON.toJSONString(map).getBytes());
				fos.flush();
				fos.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}

}
