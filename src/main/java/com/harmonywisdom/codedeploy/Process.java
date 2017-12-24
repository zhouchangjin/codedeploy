package com.harmonywisdom.codedeploy;

import com.harmonywisdom.model.JSONBasedObject;

public class Process extends JSONBasedObject{
	
	int processId;
	int parentProcessId;
	String processName;
	boolean isRunning;
	String userName;
	String cmd;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public boolean isRunning() {
		return isRunning;
	}
	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}
	public int getProcessId() {
		return processId;
	}
	public void setProcessId(int processId) {
		this.processId = processId;
	}
	public String getProcessName() {
		return processName;
	}
	public void setProcessName(String processName) {
		this.processName = processName;
	}
	
	public int getParentProcessId() {
		return parentProcessId;
	}
	public void setParentProcessId(int parentProcessId) {
		this.parentProcessId = parentProcessId;
	}
	public String getCmd() {
		return cmd;
	}
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	
	

}
