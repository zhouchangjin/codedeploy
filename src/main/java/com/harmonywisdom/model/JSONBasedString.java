package com.harmonywisdom.model;

public class JSONBasedString extends JSONBasedObject{
	
	String value;
	public JSONBasedString(){
		super();
	}
	public JSONBasedString(String value){
		this.value=value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
