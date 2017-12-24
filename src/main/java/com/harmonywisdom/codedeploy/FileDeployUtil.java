package com.harmonywisdom.codedeploy;

import java.io.File;

public class FileDeployUtil {
	
	public static int deployGzFile(LinuxShell shell,String localGZFilePath,String remoteGZPath){
		File f=new File(localGZFilePath);
		if(f.exists()){
			shell.copyFile(localGZFilePath, remoteGZPath);
			String cmd="cd "+remoteGZPath+";tar -zxvf "+remoteGZPath+"/"+f.getName();
			shell.exeCommand(cmd);
		}
		
		return 0;
	}
	
	public static int copyFile(LinuxShell shell,String localFile,String remoteFile){
		File f=new File(localFile);
		if(f.exists()){
			shell.copyFile(localFile, remoteFile);
		}
		return 0;
	}

}
