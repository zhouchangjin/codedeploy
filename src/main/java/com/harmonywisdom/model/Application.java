package com.harmonywisdom.model;

public class Application  extends JSONBasedObject {
	
	String id;
	String name;
	String path;
	String startCmd;
	String logPath;
	String logFile;
	public String getLogFile() {
		return logFile;
	}
	public void setLogFile(String logFile) {
		this.logFile = logFile;
	}
	public String getLogPath() {
		return logPath;
	}
	public void setLogPath(String logPath) {
		this.logPath = logPath;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getStartCmd() {
		return startCmd;
	}
	public void setStartCmd(String startCmd) {
		this.startCmd = startCmd;
	}

}
