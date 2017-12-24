package com.harmonywisdom.codedeploy;

public class JavaInstaller {
	public static int counter=0;
	/**
	 * 
	 * @param shell
	 * @param wgetPath
	 * @param javaHome java安装的的父目录。例如 java的目录为/root/jdk 则传入/root
	 * @param savedJarFileName
	 * @param extractFolderName
	 * @return
	 */
	public static int installJava(LinuxShell shell,String wgetPath,String javaHome,String savedJarFileName,String extractFolderName){
		//第一步下载jdk
		counter++;
		String cmd="cd "+javaHome+";wget --no-check-certificate --no-cookies --header 'Cookie: oraclelicense=accept-securebackup-cookie' "+wgetPath+" -O "+savedJarFileName;
		String result=shell.exeCommand(cmd);
		if(result.contains("ERROR 403: Forbidden")){
			System.out.println("download failed");
			return -1;
		}else{
			//第二步解压jdk
			String tarcmd="cd "+javaHome+";tar -zxvf "+savedJarFileName;
			shell.exeCommand(tarcmd);
			//第三步备份环境变量文件并更改javahome
			String backup="cp /etc/profile /home/profile.back.hm"+counter;
			shell.exeCommand(backup);
			String javahomeCmd="echo  'export JAVA_HOME="+javaHome+"/"+extractFolderName+"'>>/etc/profile";
			String jrehomeCmd="echo 'export JRE_HOME="+javaHome+"/"+extractFolderName+"'>>/etc/profile";
			String pathCmd="echo 'export PATH=$PATH:$JAVA_HOME/bin'>>/etc/profile";
			String sourceCmd="source /etc/profile";
			String java="java -version";
			String entireCmd=jrehomeCmd+";"+javahomeCmd+";"+pathCmd+";"+sourceCmd+";"+java;
			String res=shell.exeCommand(entireCmd);
			System.out.println(res);
			if(res.toLowerCase().contains("java version")){
				System.out.println("成功安装JAVA");
				return 1;
			}
		}
		return 0;
		
	}
	
	

}
