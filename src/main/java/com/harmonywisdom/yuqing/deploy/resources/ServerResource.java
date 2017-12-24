package com.harmonywisdom.yuqing.deploy.resources;


import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.alibaba.fastjson.JSON;
import com.harmonywisdom.cached.InMemoryDB;
import com.harmonywisdom.model.JSONBasedString;
import com.harmonywisdom.model.VMServer;

@Path("server")
public class ServerResource {
	
	public static InMemoryDB serverDB=new InMemoryDB("server");
    public static InMemoryDB registreredProgram=new InMemoryDB("registry");
	 @GET
	 @Produces(MediaType.TEXT_PLAIN)
	 public String getIt() {
	        return "Hellow World";
	 }
	 
	 @Path("vm")
	 @GET
	 @Produces(MediaType.APPLICATION_JSON)
	 public String servers(){
		 return JSON.toJSONString(serverDB.get());
	 }
	 
	 @Path("vm/{page}")
	 @GET
	 @Produces(MediaType.APPLICATION_JSON)
	 public String servers(@PathParam(value="page")Integer page){
		 return JSON.toJSONString(serverDB.getPage(page, 10));
	 }
	 
	 
	 
	 
	 @Path("vm")
	 @POST
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces(MediaType.APPLICATION_JSON)
	 public String addServer(VMServer vm){
		String id=UUID.randomUUID().toString();
		vm.setId(id);
		vm.setProcessLink("http://localhost:9998/vm/"+id+"/process/client");
		serverDB.put(id, vm); 
		return JSON.toJSONString(vm);
	 }
	 
	 @Path("vm/{id}")
	 @DELETE
	 public String removeServer(@PathParam("id")String id){
		 serverDB.remove(id);
		 return "{\"success\":\"true\"}";
	 }
	 
	 @Path("register/{ip}")
	 @GET
	 @Produces(MediaType.APPLICATION_JSON)
	 public String registeredProgram(@PathParam("ip")String ip){
		return registreredProgram.get(ip).toString();
	 }
	 
	 /**
	  * 这里以后改成post
	  * @param ip
	  * @param programName
	  * @return
	  */
	 @Path("register/{ip}/{programName}")
	 @GET
	 @Produces(MediaType.APPLICATION_JSON)
	 public String registerProgram(@PathParam("ip")String ip,@PathParam("programName")String programName){
		 registreredProgram.put(ip,new JSONBasedString(programName));
		 return "{'success':'true'}";
	 }
	 @GET
	 @Path("test")
	 @Produces(MediaType.TEXT_PLAIN)
	 public String test(){
		 return "Hello Word！(MS)";
	 }
}
