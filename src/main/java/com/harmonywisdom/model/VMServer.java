package com.harmonywisdom.model;

public class VMServer extends JSONBasedObject{
	String ip;
	String name;
	String password;
	String username;
	String id;
	String processLink;
	public String getProcessLink() {
		return processLink;
	}
	public void setProcessLink(String processLink) {
		this.processLink = processLink;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

}
