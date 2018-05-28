package com.zmud.jlu;

import java.io.*;
import java.net.*;
import java.util.Map;

public class Server {
	int i = 0;
	
	//Set server port number
	public static final int PORT_NUM = 2888;
	
	private boolean connected = false;
	
	//Set server socket to accept from client
	ServerSocket serverSocket;
	
	//Initial server
	public Server() throws IOException {
		RoomManagement.creatRooms();
		serverSocket = new ServerSocket(PORT_NUM);
	}
	
	public void start() throws IOException {
		while(true) {
			System.out.println(++i);
			Socket socket = serverSocket.accept();
			new ServerThread(socket).start();
		}
	}
	
	//Server thread creation and run
	static class ServerThread extends Thread {
		Socket socket;

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
						UserInput.dealInput(cmd, output, incmd);
						System.out.println(cmd);
					}else if(cmd.equals("GOOD_BYE"))
						break;
					output.write(cmd + " :frome server recieve\n");
					output.flush();
				}
				//socket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	

	static public void main(String[] args) throws IOException {
		new Server().start();
	}//end main

}//end Server class
