package com.harmonywisdom.yuqing.deploy;

import java.io.File;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AllowSymLinkAliasChecker;
import org.eclipse.jetty.webapp.WebAppContext;


public class WebServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 Server server = new Server(9090);
         WebAppContext webapp = new WebAppContext();
	     webapp.setContextPath("/");
	    // webapp.setHandler(new RootHandler());
	     String path=new File("").getAbsolutePath()+"/src/main/webapp";
	     File warFile = new File(path);
	     webapp.setWar(warFile.getAbsolutePath());
	     webapp.addAliasCheck(new AllowSymLinkAliasChecker());
         server.setHandler(webapp);
         try {
			server.start();
			server.join();
		 } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
	}

}
