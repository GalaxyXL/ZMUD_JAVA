package com.zmud.jlu;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

public class Server {	
	//Set server port number
	public static final int PORT_NUM = 2888; 
	
	public static Map<String, Room> CityMap = null;
	
	public static Map<String, Player> OnlinePlayer = new HashMap<String, Player>();
	
	private boolean connected = false;
	
	//Set server socket to accept from client
	ServerSocket serverSocket;
	
	//Initial server
	public Server() throws IOException {
		RoomManagement.creatRooms();
		CityMap = RoomManagement.getCityMap();
		serverSocket = new ServerSocket(PORT_NUM);
	}
	
	public void start() throws IOException {
		while(true) {
			Socket socket = serverSocket.accept();
			new ServerThread(socket).start();
		}
	}
	
	//Server thread creation and run
	static class ServerThread extends Thread {
		Socket socket;
		
		Player p0;
		
		Room location = null;
				
		public ServerThread(Socket socket) {
			this.socket = socket;
		}
				
		@Override
		public void run() {
			try {
				BufferedWriter output  = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));		//Pass control command
				DataInputStream input = new DataInputStream(socket.getInputStream());	//Get data from client
				
				BufferedReader incmd = new BufferedReader(new InputStreamReader(
						socket.getInputStream())); //Get control command
				
				String recieve_message = null;
				String index = null;
				
				String cmd = incmd.readLine();
				
				//Verification
				if(cmd.equals("I_AM_CLIENT")){
					output.write("CONNECTION_ESTABLISHED\n");
					output.flush();
					System.out.println("CONNECTION_ESTABLISHED"); 
				}else{
					System.out.println(cmd);
					output.write("WHO_ARE_YOU?\n");
					output.flush();
				}
				while(true){
					if((cmd = incmd.readLine()) == null)
						continue;
					if(cmd.equalsIgnoreCase("register") || cmd.equalsIgnoreCase("login")){
						p0 = UserInput.dealInput(cmd, output, incmd);
					}else if(cmd.equals("GOOD_BYE")){
						System.out.println("bye bye " + p0.getName());
						XmlFileManagement.setRequiredPlayerInfo(XmlFileManagement.getPlayerNodeByName(
								XmlFileManagement.getDocment("Players.xml").getRootElement(), p0.getName()), 
								"room", p0.getRoom().getRoomId());
						Room temp = p0.getRoom();
						System.out.println(temp.getRoomName());
						temp.removePlayer(p0);
						OnlinePlayer.remove(Integer.toString(p0.getId()));
						break;
					}else if (cmd.indexOf("/message$") != -1) {
						recieve_message = cmd.substring(9, cmd.length());
						UserInput.dealInput(p0, recieve_message, 1);
					}
					else{
						UserInput.dealInput(p0, cmd);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	

	static public void main(String[] args) throws IOException {
		new Server().start();
	}//end main

}//end Server class
