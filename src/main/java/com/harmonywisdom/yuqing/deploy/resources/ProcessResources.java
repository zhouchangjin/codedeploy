package com.harmonywisdom.yuqing.deploy.resources;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.harmonywisdom.cached.InMemoryDB;
import com.harmonywisdom.codedeploy.FileStatus;
import com.harmonywisdom.codedeploy.FileSystemUtil;
import com.harmonywisdom.codedeploy.LinuxShell;
import com.harmonywisdom.codedeploy.ProcessUtil;
import com.harmonywisdom.model.Application;
import com.harmonywisdom.model.VMServer;
import com.harmonywisdom.codedeploy.Process;

@Path("vm/{vmId}/process")
public class ProcessResources {
	 @Path("{processName}")
	 @GET
	 @Produces(MediaType.APPLICATION_JSON)
	 public String process(@PathParam(value="vmId")String serverId,@PathParam(value="processName")String processName){
		VMServer server=(VMServer) ServerResource.serverDB.get(serverId);
		LinuxShell shell=LinuxShell.buildSell(server.getIp(), server.getUsername(), server.getPassword());
		Process p=ProcessUtil.searchProcess(shell, processName);
		return p.toString();
	 }
	 
	 @Path("{processName}/log")
	 @GET
	 @Produces(MediaType.APPLICATION_JSON)
	 public String processlogs(@PathParam(value="vmId")String serverId,@PathParam(value="processName")String processName){
		VMServer server=(VMServer) ServerResource.serverDB.get(serverId);
		LinuxShell shell=LinuxShell.buildSell(server.getIp(), server.getUsername(), server.getPassword());
		 Application app=(Application)ProgrammeResources.program.get(processName);
		FileStatus fs=FileSystemUtil.fileStatus(shell,app.getLogFile() , app.getLogPath());
		return fs.toString();
	 }
	 
	 @Path("{processName}/stop")
	 @POST
	 @Produces(MediaType.APPLICATION_JSON)
	 public String stopProcess(@PathParam(value="vmId")String serverId,@PathParam(value="processName")String processName){
		 VMServer server=(VMServer) ServerResource.serverDB.get(serverId);
			LinuxShell shell=LinuxShell.buildSell(server.getIp(), server.getUsername(), server.getPassword());
			int r=ProcessUtil.killProcess(shell, processName);
			return "{'res':'"+r+"'}";
	 }
	 
	 @Path("{processName}/start")
	 @POST
	 @Produces(MediaType.APPLICATION_JSON)
	 public String startProcess(@PathParam(value="vmId")String serverId,@PathParam(value="processName")String processName){
		 VMServer server=(VMServer) ServerResource.serverDB.get(serverId);
		 LinuxShell shell=LinuxShell.buildSell(server.getIp(), server.getUsername(), server.getPassword());
		 Application app=(Application)ProgrammeResources.program.get(processName);
		 int r=ProcessUtil.startProcess(shell, app.getStartCmd(),app.getPath());
		 return "{'res':'"+r+"'}";
	 }
	 
}
