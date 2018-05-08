package com.demo.controller;

import java.io.BufferedInputStream;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.net.DatagramSocket;

import com.demo.dao.UserDao;
import com.demo.model.Node;
import com.demo.util.DbUtil;

public class UserController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static String LIST_NODES = "/listNodes.jsp";
    private static String HELLO_NODES = "/sayhello.jsp";
    private static String MY_LIST_NODES = "/mylistnodes.jsp";
    private UserDao dao;

    public UserController() {
        super();
        dao = new UserDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward="";
        String action = request.getParameter("action");
        String hostname;
        String ipaddress;
        
        String r_ipAddress = request.getHeader("X-FORWARDED-FOR");  
        if (r_ipAddress == null) {  
        	r_ipAddress = request.getRemoteAddr();  
        }
        System.out.println("Got Request from... " + r_ipAddress);

        try {
            hostname = InetAddress.getLocalHost().getHostName();
            ipaddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
        	hostname = System.getenv("HOSTNAME");
        	ipaddress = "";
        }
        
        try {
        	DatagramSocket socket = new DatagramSocket();
	        socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
	        ipaddress = socket.getLocalAddress().getHostAddress();
	        socket.close();
        } catch (Exception e) {
        	System.out.println("Unable to get local IP address");
        }
        
        if (hostname == null) {
	        try (BufferedInputStream in = new BufferedInputStream(Runtime.getRuntime().exec("hostname").getInputStream()))
	        {
	          byte[] b = new byte[80];
	          in.read(b, 0, b.length); //guaranteed to read all data before returning
	          hostname = new String(b);
	        }
	        catch (IOException e)
	        {
	          String message = "Error reading hostname";
	          System.out.println(message);
	        }        
        }
        
        if (hostname == null) {
        	hostname = ipaddress;
        }
       
        // Check if database is active.. if not reset it
        try {
        	System.out.println("About to invoke getAllNodes");
            dao.getAllNodes();
            System.out.println("DB is ok");
        } catch (Exception e) {
        	System.out.println("Exception connecting to the DB... " + e.getMessage());
        	dao.resetConnection();        	
        }
        
        
        Node thisNode = null;
        try {
        	System.out.println("About to invoke getNodeByHostname");
	        thisNode = dao.getNodeByHostname(hostname);
	        if (thisNode.getNodeid() == -1) {
	        	thisNode.setHostname(hostname);
	        	thisNode.setIpaddress(ipaddress);
	        	thisNode.setRequests(1);
	        	dao.addNode(thisNode);
	        }
	        else {
	        	thisNode.setRequests(thisNode.getRequests() + 1);
	        	dao.updateNode(thisNode);
	        }
        } catch (Exception e) {
            System.out.println("IPaddress: " + ipaddress + " hostname: " + hostname);
        	System.out.println("Exception connecting to the DB... " + e.getMessage());
        	dao.resetConnection();
        }
        
        if (thisNode == null) {
        	thisNode = new Node();
        	thisNode.setHostname(hostname);
        	thisNode.setIpaddress(ipaddress);
        	if(action.equals("mylistnodes")){
        		forward = LIST_NODES;
        	}else{
        		forward = HELLO_NODES;
        	}
            request.setAttribute("currentnode", thisNode);
        } 
        else {
        	//forward = LIST_NODES;
        	if(action.equals("mylistnodes")){
        		forward = LIST_NODES;
        	}else{
        		forward = HELLO_NODES;
        	}
        	
            request.setAttribute("nodes", dao.getAllActiveNodes());
            request.setAttribute("currentnode", thisNode);
        }
        
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);

    }
}
