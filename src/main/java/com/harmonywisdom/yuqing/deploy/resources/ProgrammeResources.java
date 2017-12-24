package com.harmonywisdom.yuqing.deploy.resources;

import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.alibaba.fastjson.JSON;
import com.harmonywisdom.cached.InMemoryDB;
import com.harmonywisdom.model.Application;
import com.harmonywisdom.model.VMServer;

@Path("app")
public class ProgrammeResources {
	public static InMemoryDB program=new InMemoryDB("program");
	
	 @POST
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces(MediaType.APPLICATION_JSON)
	 public String addApp(Application application){
		String id=UUID.randomUUID().toString();
		application.setId(id);
		program.put(application.getName(), application);
		return JSON.toJSONString(program);
	 }
	 
	 @GET
	 @Produces(MediaType.APPLICATION_JSON)
	 public String apps(){
		return JSON.toJSONString(program.get());
	 }
}
