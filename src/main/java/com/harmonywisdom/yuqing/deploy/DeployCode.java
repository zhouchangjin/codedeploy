package com.harmonywisdom.yuqing.deploy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import com.harmonywisdom.codedeploy.LinuxShell;
import com.harmonywisdom.codedeploy.SimpleFlow;
import com.harmonywisdom.codedeploy.Task;

public class DeployCode {
	
	Properties prop;
	String hostFile;
	String taskFile;
	HashMap<String,LinuxShell> shellMap;
	List<Task> taskList;
	public void initialize(){
		prop=new Properties();
		try {
			File f=new File("");
			String path=f.getAbsolutePath();
			System.out.println(f.getAbsolutePath());
			prop.load(new FileInputStream(new File(path+"/conf/deploy.conf")));
			hostFile=prop.getProperty("host_file");
			taskFile=prop.getProperty("task_file");
			prepareShell();
			prepareTask();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void prepareTask(){
		taskList=new ArrayList<Task>();
		String path=new File("").getAbsolutePath();
		try {
			BufferedReader br=new BufferedReader(new FileReader(new File(path+"/conf/"+taskFile)));
			String line="";
			while((line=br.readLine())!=null){
				String params[]=line.split("\\s+");
				if(params.length==2){
					String ip=params[0];
					String taskScript=params[1];
					SimpleFlow flow=new SimpleFlow();
					flow.loadFromInputStream(new FileInputStream(new File(taskScript)));
					Task t=new Task();
					t.setFlow(flow);
					t.setShell(shellMap.get(ip));
					taskList.add(t);
					
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void prepareShell(){
		shellMap=new HashMap<String,LinuxShell>();
		String path=new File("").getAbsolutePath();

		try {
			BufferedReader br=new BufferedReader(new FileReader(new File(path+"/conf/"+hostFile)));
			String line="";
			while((line=br.readLine())!=null){
				String params[]=line.split("\\s+");
				if(params.length==3){
					String ip=params[0];
					String user=params[1];
					String password=params[2];
					shellMap.put(ip,LinuxShell.buildSell(ip, user, password));
				}
				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void deploy(){
		for(int i=0;i<taskList.size();i++){
			Task t=taskList.get(i);
			t.run();
		}
	}
	

	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		DeployCode deploy=new DeployCode();
		deploy.initialize();
		deploy.deploy();

	}

}
