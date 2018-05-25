package com.zmud.jlu;

import java.io.*;
import java.net.*;

public class Server {
	public static int i = 0;
	//Set server port number
	public static final int PORT_NUM = 2888;
	
	
	
	//Set server socket to accept from client
	ServerSocket serverSocket;
	
	//Initial server
	public Server() throws IOException {
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

		public ServerThread(Socket socket) {
			this.socket = socket;
		}
		
		//String incmd;
		
		@Override
		public void run() {
			try {
				Writer output  = new OutputStreamWriter(socket.getOutputStream());		//Pass control command
				DataInputStream input = new DataInputStream(socket.getInputStream());	//Get data from client
				
				BufferedReader incmd = new BufferedReader(new InputStreamReader(
						socket.getInputStream())); //Get control command
				
				//Verification
				String cmd = incmd.readLine();
				//String cmd = "hello";
				if(!cmd.equals("\n")){
				System.out.println(cmd);
				}else{
					incmd.readLine();
				}
				output.write("WHO_ARE_YOU?\n");
				output.flush();
				//socket.shutdownOutput();
				//socket.shutdownInput();
				//System.out.println("Client Connected!");			//Print id information
				
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
