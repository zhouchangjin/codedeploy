package com.harmonywisdom.yuqing.deploy;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

import com.harmonywisdom.restproxy.servlet.RestProxyServlet;

public class ServletTestServer {
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Server server=new Server(5566);
		ServletHandler handler = new ServletHandler();
	    server.setHandler(handler);
	    handler.addServletWithMapping(RestProxyServlet.class, "/*");
	    try {
			server.start();
			server.join();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		
	}

}
