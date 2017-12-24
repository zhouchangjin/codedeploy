package com.harmonywisdom.restproxy.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RestProxyServlet extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control", "private");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Connection", "Keep-Alive");
		response.setHeader("Proxy-Connection", "Keep-Alive");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("start   dfsdfasdfasd");
		out.flush();
		
		if (!request.isAsyncSupported()) {
			   System.out.println("the servlet is not supported Async");
			   return;
		}
		
		request.startAsync(request, response);

		  if (request.isAsyncStarted()) {
		   AsyncContext asyncContext = request.getAsyncContext();
		   asyncContext.setTimeout(1L * 60L * 1000L);// 60sec

		   new CounterThread(asyncContext).start();
		  } else {
		   System.err.println("the ruquest is not AsyncStarted !");
		  }
		 

	}
	
	
	private static class CounterThread extends Thread {
		  private AsyncContext asyncContext;

		  public CounterThread(AsyncContext asyncContext) {
		   this.asyncContext = asyncContext;
		  }

		  @Override
		  public void run() {
		   int interval = 1000 * 20; // 20sec
		    System.out.println("now sleep 20s, just as do some big task ...");
		    try {
				Thread.sleep(interval);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    System.out.println("now dispatch to another Async Servlet");

		    ServletRequest request = asyncContext.getRequest();
		 }
		}


}


