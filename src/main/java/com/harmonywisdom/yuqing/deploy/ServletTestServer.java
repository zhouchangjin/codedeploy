package com.harmonywisdom.yuqing.deploy;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;

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
