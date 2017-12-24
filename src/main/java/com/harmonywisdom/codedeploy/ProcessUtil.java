package com.harmonywisdom.codedeploy;

import java.util.ArrayList;
import java.util.List;

public class ProcessUtil {
	
	public static List<Process> listAllProcess(LinuxShell shell){
		String res=shell.exeCommand("ps -ef --no-headers");
		String lines[]=res.split("\n");
		List<Process> list=new ArrayList<Process>();
		for(int i=0;i<lines.length;i++){
			String line=lines[i];
			String items[]=line.split("\\s+");
			String user=items[0];
			Integer processId=Integer.parseInt(items[1]);
			Integer pProcessId=Integer.parseInt(items[2]);
			String startTime=items[4];
			String time=items[6];
			String cmd=items[7];
			Process p=new Process();
			p.setUserName(user);
			p.setProcessId(processId);
			p.setParentProcessId(pProcessId);
			p.setRunning(true);
			p.setCmd(cmd);
			list.add(p);
			
		}
		return list;
	}
	
	public static Process searchProcess(LinuxShell shell,String processName){
		boolean flag=false;
		 String res=shell.exeCommand("ps -ef|grep "+processName+"|awk '{print $2}'");
	        String lines[]=res.split("\n");
	        for(int i=0;i<lines.length;i++){
	        	//System.out.println(lines[i]);
	        	 res=shell.exeCommand("ps --no-headers  "+lines[i]);
	        	 if(!res.equals("")){
	        		 int id=Integer.parseInt(lines[i]);
	        		 Process process=new Process();
	        		 process.setProcessName(processName);
	        		 process.setRunning(true);
	        		 process.setProcessId(id);
	        		 flag=true;
	        		 return process;
	        	 }
	        }
	        Process p=new Process();
	        p.setRunning(false);
	        return p;
	}
	
	public static int killProcess(LinuxShell shell,String processName){
		boolean flag=false;
		 String res=shell.exeCommand("ps -ef|grep "+processName+"|awk '{print $2}'");
	        String lines[]=res.split("\n");
	        for(int i=0;i<lines.length;i++){
	        	//System.out.println(lines[i]);
	        	 res=shell.exeCommand("ps --no-headers  "+lines[i]);
	        	 if(!res.equals("")){
	        		 int id=Integer.parseInt(lines[i]);
	        		 Process process=new Process();
	        		 process.setProcessName(processName);
	        		 process.setRunning(true);
	        		 process.setProcessId(id);
	        		 flag=true;
	        		 
	        		 shell.exeCommand("kill -9 "+id);
	        		 return 1;
	        	 }
	        }

	        return 0;
	}
	
	public static int startProcess(LinuxShell shell,String cmd,String contextPath){
		String combinedCmd="cd "+contextPath+";"+cmd;
		shell.exeCommand(combinedCmd);
		return 1;
	}

}
