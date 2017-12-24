package com.harmonywisdom.codedeploy;

public class Task {
	
	SimpleFlow flow;
	LinuxShell shell;
	public SimpleFlow getFlow() {
		return flow;
	}
	public void setFlow(SimpleFlow flow) {
		this.flow = flow;
	}
	public LinuxShell getShell() {
		return shell;
	}
	public void setShell(LinuxShell shell) {
		this.shell = shell;
	}
	
	public void run(){
		flow.work(shell);
	}
	

}
