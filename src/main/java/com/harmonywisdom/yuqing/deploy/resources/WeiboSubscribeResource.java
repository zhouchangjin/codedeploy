package com.harmonywisdom.yuqing.deploy.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.alibaba.fastjson.JSON;
import com.harmonywisdom.cached.InMemoryDB;
import com.harmonywisdom.codedeploy.LinuxShell;
import com.harmonywisdom.model.ShellConfig;

@Path("weibo")
public class WeiboSubscribeResource {
	
	public static InMemoryDB weiboDB=new InMemoryDB("weibo");
	
	@GET
	 @Produces(MediaType.TEXT_PLAIN)
	 public String getIt() {
	        return "Hellow World";
	 }
	
	 @Path("config")
	 @PUT
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces(MediaType.APPLICATION_JSON)
	 public String config(ShellConfig config){
		 weiboDB.put("conf",config);
		 return JSON.toJSONString(weiboDB.get("conf"));
	 }
	 
	 @Path("config")
	 @GET
	 @Produces(MediaType.APPLICATION_JSON)
	 public String config(){
		 return JSON.toJSONString(weiboDB.get("conf"));
	 }
	 
	 @Path("status")
	 @GET
	 public String status(){
		 ShellConfig config=(ShellConfig) weiboDB.get("conf");
		 LinuxShell shell=LinuxShell.buildSell(config.getIp(), config.getUsername(), config.getPassword());
		 return shell.exeCommand("ps -ef|grep weibo").replaceAll("\\n", "<br/>");
	 }
	 

}
