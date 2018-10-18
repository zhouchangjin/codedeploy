package com.harmonywisdom.codedeploy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

public class LinuxShell {
	
	String hostName;
	String password;
	String ip;
	String userName;
	Connection conn;
	Session sess;
	SCPClient sclient;
	public static LinuxShell buildSell(String ip,String userName,String password){
		LinuxShell shell= new LinuxShell();
		shell.setIp(ip);
		shell.setUserName(userName);
		shell.setPassword(password);
		return shell;
	}
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	private void connect(){
		conn = new Connection(ip);
		try {
			conn.connect();
			boolean isAuthenticated = conn.authenticateWithPassword(userName, password);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void openSession(){
		try {
			sess = conn.openSession();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void closeSession(){
		sess.close();
	}
	
	private void openSCP(){
		try {

			 sclient=conn.createSCPClient();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void copyFileWithNewName(String sourcePath,String desPath,String name){
		try {
			connect();
			openSCP();
			sclient.put(sourcePath, name, desPath, "0777");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void copyFile(String sourcePath,String desPath){
		try {
			connect();
			openSCP();
			sclient.put(sourcePath, desPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String exeCommandWithContextFolderSet(String cmd,String contextFolder){
		connect();
		openSession();
		String res="";
		try {
			String finalCmd="cd "+contextFolder+";"+cmd;
			sess.execCommand(finalCmd);
			InputStream stdout = new StreamGobbler(sess.getStdout());
			InputStream stderr =new StreamGobbler(sess.getStderr());
			BufferedReader brerr = new BufferedReader(new InputStreamReader(stderr));
			while(true){
				String line = brerr.readLine();
		         
		         if (line == null)
		           break;
		         res+=line+"\n";
			}
			
	        BufferedReader br = new BufferedReader(new InputStreamReader(stdout));
	        while (true){
		         String line = br.readLine();
		         
		         if (line == null)
		           break;
		         res+=line+"\n";
	        }
	        br.close();
	        brerr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeSession();
		close();
		return res;
	}
	
	public String exeCommand(String cmd){
		connect();
		openSession();
		String res="";
		try {
			sess.execCommand(cmd);
			InputStream stdout = new StreamGobbler(sess.getStdout());
			InputStream stderr =new StreamGobbler(sess.getStderr());
			BufferedReader brerr = new BufferedReader(new InputStreamReader(stderr));
			while(true){
				String line = brerr.readLine();
		         
		         if (line == null)
		           break;
		         res+=line+"\n";
			}
	        BufferedReader br = new BufferedReader(new InputStreamReader(stdout));
	        while (true){
		         String line = br.readLine();
		         
		         if (line == null)
		           break;
		         res+=line+"\n";
	        }
	        brerr.close();
	        br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeSession();
		close();
		return res;
		
	}
	
	private void close(){
		conn.close();
	}

}
