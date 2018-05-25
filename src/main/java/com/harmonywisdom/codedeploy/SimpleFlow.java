package com.harmonywisdom.codedeploy;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class SimpleFlow extends CommandFlow {
	enum CmdType{
		TAR("#tar#"),
		CMD("#cmd#"),
		DEVIDER("#div#"),
		SCP("#scp#"),
		WAIT("#wait#"),
		INSTALLJDK("#jdk#");
		String cmd;
		private CmdType(String val){
			this.cmd=val;
		}
		public String getCmd(){
			return cmd;
		}
		
		public String toString(){
			return cmd;
		}
	}
	List<String> cmdList;
	public SimpleFlow() {
		cmdList=new ArrayList<String>();
	}
	public void addCmd(String cmd){
		cmdList.add(cmd);
	}
	
	public int loadFromInputStream(InputStream io){
		byte[] buff=new byte[1024];
		StringBuffer sb=new StringBuffer();
		int length=0;
		try {
			while((length=io.read(buff))>0){
				sb.append(new String(buff,0,length));
			}
			String lines[]=sb.toString().split("\n");
			for(int i=0;i<lines.length;i++){
				String line=lines[i].replaceAll("\r", "");
				cmdList.add(line);
			}
			
			
			return 1;
		} catch (IOException e) {
			e.printStackTrace();
			return -1;
			
		}
	}
	
	
	public void work(LinuxShell shell){
		for(int i=0;i<cmdList.size();i++){
			String cmd=cmdList.get(i).trim();
			System.out.println(cmd);
			if(cmd.indexOf(CmdType.TAR.getCmd())==0){
				String filelist[]=cmd.substring(CmdType.TAR.getCmd().length()).trim().split(CmdType.DEVIDER.getCmd());
				if(filelist.length==2){
					System.out.println("copy localfile "+filelist[0]+" to "+filelist[1]);
					FileDeployUtil.deployGzFile(shell, filelist[0], filelist[1]);
				}else{
					System.out.println("error cmd");
				}
			}else if(cmd.indexOf(CmdType.CMD.getCmd())==0){
				String script=cmd.substring(CmdType.TAR.getCmd().length()).trim();
				String cmds[]=script.split(CmdType.DEVIDER.getCmd());
				if(cmds.length==2){
					shell.exeCommandWithContextFolderSet(cmds[0], cmds[1]);
				}else{
					System.out.println(shell.exeCommand(script));
				}
				
			}else if(cmd.indexOf(CmdType.INSTALLJDK.getCmd())==0){
				String script=cmd.substring(CmdType.INSTALLJDK.getCmd().length()).trim();
				String cmds[]=script.split(CmdType.DEVIDER.getCmd());
				if(cmds.length==4){
					JavaInstaller.installJava(shell, cmds[0], cmds[1], cmds[2], cmds[3]);
				}
				
			}else if(cmd.indexOf("#process#")==0){
				
			}else if(cmd.indexOf(CmdType.SCP.getCmd())==0){
				String script=cmd.substring(CmdType.SCP.getCmd().length()).trim();
				String cmds[]=script.split(CmdType.DEVIDER.getCmd());
				if(cmds.length==2){
					String localFile=cmds[0];
					String remoteFile=cmds[1];
					FileDeployUtil.copyFile(shell, localFile, remoteFile);
				}else{
					// do nothing
				}
			}else if(cmd.indexOf(CmdType.WAIT.getCmd())==0){
				String scriptString=cmd.substring(CmdType.WAIT.getCmd().length()).trim();
				Long l=Long.parseLong(scriptString);
				try {
					Thread.sleep(l);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	
	

}
