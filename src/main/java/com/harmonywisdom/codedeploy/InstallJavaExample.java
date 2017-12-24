package com.harmonywisdom.codedeploy;

public class InstallJavaExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String downloadPath="http://download.oracle.com/otn-pub/java/jdk/8u91-b14/jdk-8u91-linux-x64.tar.gz";
		String hostname = "191.101.10.232";
	    String username = "root";
        String password = "ghvVWVynrM7Gi9";
        LinuxShell shell=LinuxShell.buildSell(hostname, username, password);
        JavaInstaller.installJava(shell, downloadPath, "/root","jdk-8u91.tar.gz","jdk1.8.0_91");
	}

}
