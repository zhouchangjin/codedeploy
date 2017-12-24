package com.harmonywisdom.codedeploy;

public class ProcessExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String hostname = "101.201.113.137";
	    String username = "root";
        String password = "zthz123!";
        LinuxShell shell=LinuxShell.buildSell(hostname, username, password);
        //ProcessUtil.killProcess(shell, "agent.jar");
        
       // Process p=ProcessUtil.searchProcess(shell, "mongo");
      //  System.out.println(p.getProcessId());
        //ProcessUtil.startProcess(shell, "mkdir haha", "/home/data");
        //ProcessUtil.listAllProcess(shell);
        //System.out.println(p.getProcessId());
        SimpleFlow sf=new SimpleFlow();
        sf.loadFromInputStream(ProcessExample.class.getResourceAsStream("/vps.scpt"));
        //shell.exeCommand("source /home/data/zhouchangjin/test.sh");
        sf.work(shell);
       // System.out.println("#copy#".trim().indexOf("#copy#"));
	}

}
