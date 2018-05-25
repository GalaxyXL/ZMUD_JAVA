package com.zmud.jlu;

import java.io.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import org.omg.PortableInterceptor.INACTIVE;

import java.net.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Client extends guiclient {

	//Input command
	private String incmd;
	
	private Socket socket;
	private BufferedReader in;
	private BufferedWriter out;

	private String ipaddress = "127.0.0.1";
	private int port = 1888;
	private boolean connected = false;

	//Get information from server
	class MonitorThread extends Thread {
		public MonitorThread() throws IOException {
			//添加
			connet(InetAddress.getLoopbackAddress());
		}

		String cmd;
		BufferedReader br = in;

		@Override
		public void run() {
			try {
				while((cmd = br.readLine()) != null)
					textArea.append(cmd + "\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void connet (InetAddress address) throws IOException {
		this.socket = new Socket(address, Server.PORT_NUM);
	}
	
	
	//Send command to server
	public void sendCmdtoServer(String cmd, Socket socket) throws IOException{
		try{
			//connet(InetAddress.getLoopbackAddress());
			Writer output = new OutputStreamWriter(socket.getOutputStream());
			output.write(cmd);
			output.flush();
			output.close();
			//socket.shutdownOutput();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	//Get message from server
	public void getCmdfromServer(Socket socket) throws IOException{
		try{
			connet(InetAddress.getLoopbackAddress());
			BufferedReader in = new BufferedReader(new InputStreamReader(
					socket.getInputStream())); //Get control command
			String cmd = in.readLine();
			textArea.append(cmd + "\n");
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	
	public Client() {
		super();
		input.addKeyListener(new KeyListener() {
			
			@Override
			public void keyPressed(KeyEvent e) {
				//Enter listener for user command
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					try {
						connet(InetAddress.getLoopbackAddress());
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					incmd = input.getText();
					//Blank check
					if(!(incmd.equals(""))){
						try {
							sendCmdtoServer(incmd, socket);
							//socket.close();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} 
						input.setText("");
						textArea_1.append(incmd + "\n");
					}
				}
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		//Connect button function
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// Connect to server
				try {
					connet(InetAddress.getLoopbackAddress());
					BufferedReader in = new BufferedReader(new InputStreamReader(
							socket.getInputStream())); //Get control command
					Writer out = new OutputStreamWriter(socket.getOutputStream());	//Pass data
					out.write("Connecting...\n");
					out.flush();
					
					socket.setSoTimeout(3000);
					String cmd = in.readLine();
					if(cmd.equals("WHO_ARE_YOU?")){
						textArea.setText(textArea.getText() + cmd + "\n");
						connected = true;
						out.close();
					}
					//socket.close();
					//screen.setText("Test");
				} catch (Exception e) {
					e.printStackTrace();
					textArea_1.setText(textArea_1.getText() + "链接服务器失败！请重试\n");
					System.exit(1);
				}
			}
		});
		
		//Exit button function
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try{
					connected = false;
					socket.close();
					//in.close();
					//out.close();
					System.exit(0);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					System.exit(1);
				}
			}
		});
		
	}

	public void setDefaultCloseOperation(int arg0) {
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		if (connected) {
			try {
				connected = false;
				socket.close();
				in.close();
				out.close();
				System.out.println("All closed");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//System.exit(1);
	}

	public void setText(JTextArea screen, String Message) {
		String[] temp = Message.split("\t");
		for (int i = 0; i < temp.length; i++) {
			screen.setText(screen.getText() + temp[i] + "\n");
			// System.out.print(temp[i]+"\n");
		}
		screen.setCaretPosition(screen.getDocument().getLength());
	}
	
	public void start() throws IOException{
			new MonitorThread().start();
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client frame = new Client();
					frame.setVisible(true);
					frame.setResizable(false);
					//frame.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
