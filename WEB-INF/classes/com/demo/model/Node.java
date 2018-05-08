package com.demo.model;

public class Node {
	private int nodeid;
	private String nodecolor;
	private String hostname;
	private String ipaddress;
	private int requests;
	
	public int getNodeid() {
		return nodeid;
	}
	public void setNodeid(int nodeid) {
		this.nodeid = nodeid;
	}
	
	public String getNodecolor() {
		return nodecolor;
	}
	public void setNodecolor(String nodecolor) {
		this.nodecolor = nodecolor;
	}
	
	public String getHostname() {
		return hostname;
	}
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	
	public String getIpaddress() {
		return ipaddress;
	}
	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}
	
	public int getRequests() {
		return requests;
	}
	public void setRequests(int requests) {
		this.requests = requests;
	}
	
	@Override
	public String toString() {
		return "Node [nodeid=" + nodeid + ", nodecolor=" + nodecolor + ", hostname=" + hostname + ", ipaddress="
				+ ipaddress + ", requests=" + requests + "]";
	}
}
