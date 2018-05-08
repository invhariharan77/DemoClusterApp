package com.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.demo.model.Node;
import com.demo.util.DbUtil;

public class UserDao {

	private Connection connection;

	public UserDao() {
		connection = DbUtil.getConnection();
	}
	
	public void resetConnection() {
		System.out.println("Resetting the connection...");
		DbUtil.invalidateConnection();
		connection = DbUtil.getConnection();
		if (connection != null) {
			System.out.println("Sucessfully reseted the connection...");
		} else {
			System.out.println("Failed to reset the connection");
		}
	}

	public List<Node> getAllNodes() {
		Statement statement = null;

		List<Node> nodes = new ArrayList<Node>();
		try {
			if (connection == null) {
				connection = DbUtil.getConnection();
			}
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from cluster_data");
			while (rs.next()) {
				Node node = new Node();
				node.setNodeid(rs.getInt("nodeid"));
				node.setNodecolor(rs.getString("nodecolor"));
				node.setHostname(rs.getString("hostname"));
				node.setIpaddress(rs.getString("ipaddress"));
				node.setRequests(rs.getInt("requests"));
				nodes.add(node);
			}
		} catch (SQLException e) {
			System.out.println("errorcode: " + e.getErrorCode() + " sqlstate: " + e.getSQLState() + " message: " + e.getMessage());
			e.printStackTrace();
			resetConnection();
		} finally {
			if (statement != null)
			{
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
				}
			}
			/**
			if (connection != null) {
				DbUtil.closeConnection();
				connection = null;
			}*/
				
		}

		return nodes;
	}
	
	public List<Node> getAllActiveNodes() {
		Statement statement = null;

		List<Node> nodes = new ArrayList<Node>();
		try {
			if (connection == null) {
				connection = DbUtil.getConnection();
			}
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from cluster_data");
			while (rs.next()) {
				if (rs.getString("inuse").equalsIgnoreCase("Y")) {
					Node node = new Node();
					node.setNodeid(rs.getInt("nodeid"));
					node.setNodecolor(rs.getString("nodecolor"));
					node.setHostname(rs.getString("hostname"));
					node.setIpaddress(rs.getString("ipaddress"));
					node.setRequests(rs.getInt("requests"));
					nodes.add(node);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (statement != null)
			{
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
				}
			}
			/**
			if (connection != null) {
				DbUtil.closeConnection();
				connection = null;
			}*/
		}

		return nodes;
	}
	
	public Node getNodeByHostname(String hostname) {
		PreparedStatement preparedStatement = null;

		Node node = new Node();
		node.setNodeid(-1);
		
		try {
			if (connection == null) {
				connection = DbUtil.getConnection();
			}
			preparedStatement = connection.
					prepareStatement("select * from cluster_data where hostname=?");
			preparedStatement.setString(1, hostname);
			ResultSet rs = preparedStatement.executeQuery();
			
			if (rs.next()) {
				node.setNodeid(rs.getInt("nodeid"));
				node.setRequests(rs.getInt("requests"));
				node.setHostname(rs.getString("hostname"));
				node.setIpaddress(rs.getString("ipaddress"));
				node.setNodecolor(rs.getString("nodecolor"));
			} 
		} catch (SQLException e) {
			System.out.println("errorcode: " + e.getErrorCode() + " sqlstate: " + e.getSQLState() + " message: " + e.getMessage());
			e.printStackTrace();
			resetConnection();
		} finally {
			if (preparedStatement != null)
			{
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
				}
			}
			/**
			if (connection != null) {
				DbUtil.closeConnection();
				connection = null;
			}**/
		}

		return node;
	}

	public void updateNode(Node node) {
		PreparedStatement preparedStatement = null;

		try {
			if (connection == null) {
				connection = DbUtil.getConnection();
			}
			preparedStatement = connection
					.prepareStatement("update cluster_data set requests=? where nodeid=?");
			preparedStatement.setInt(1, node.getRequests());
			preparedStatement.setInt(2, node.getNodeid());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if (preparedStatement != null)
			{
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
				}
			}
			/**
			if (connection != null) {
				DbUtil.closeConnection();
				connection = null;
			}**/
		}
	}

	public void addNode(Node node) {
		PreparedStatement preparedStatement = null;
		try {
			if (connection == null) {
				connection = DbUtil.getConnection();
			}

			preparedStatement = connection.
					prepareStatement("select * from cluster_data where inuse=? for update");
			preparedStatement.setString(1, "N");
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				node.setNodeid(rs.getInt("nodeid"));

				PreparedStatement preparedStatement1 = connection
						.prepareStatement("update cluster_data set inuse=?, hostname=?, ipaddress=?, requests=? where nodeid=?");
				preparedStatement1.setString(1, "Y");
				preparedStatement1.setString(2, node.getHostname());
				preparedStatement1.setString(3, node.getIpaddress());
				preparedStatement1.setInt(4, node.getRequests());
				preparedStatement1.setInt(5, node.getNodeid());
				preparedStatement1.executeUpdate();
			}
			else {
				System.out.println("Unable to add node. No usable slot in cluster table!");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (preparedStatement != null)
			{
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
				}
			}
			/**
			if (connection != null) {
				DbUtil.closeConnection();
				connection = null;
			}*/
		}
	}
}
