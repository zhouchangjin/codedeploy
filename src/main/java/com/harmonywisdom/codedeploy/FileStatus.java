package com.harmonywisdom.codedeploy;

import com.harmonywisdom.model.JSONBasedObject;

public class FileStatus extends JSONBasedObject{
	
	String accessFlags;
	String owner;
	String owner2;
	String size;
	String Mongth;
	String day;
	String time;
	String file;
	public String getAccessFlags() {
		return accessFlags;
	}
	public void setAccessFlags(String accessFlags) {
		this.accessFlags = accessFlags;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getOwner2() {
		return owner2;
	}
	public void setOwner2(String owner2) {
		this.owner2 = owner2;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getMongth() {
		return Mongth;
	}
	public void setMongth(String mongth) {
		Mongth = mongth;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}

}
