package com.zmud.jlu;

import java.io.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import org.omg.PortableInterceptor.INACTIVE;

import java.net.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Client extends guiclient{
	
	//Input command
	private String incmd;
	
	private Socket socket;
	private BufferedReader in;
	private Writer out;

	private boolean connected = false;

	//Get information from server
	class MonitorThread extends Thread {
		String cmd;
		
		public MonitorThread() {

		}

		@Override
		public void run() {
			while(true){
				try {
					cmd = in.readLine();
				} catch (Exception e) {
					try {
						this.sleep(500);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					continue;
				}
				textArea.append(cmd + "\n");
			}
		}
	}
	
	//Establish connection to server
	public void connet (InetAddress address) throws IOException {
		this.socket = new Socket(address, Server.PORT_NUM);
	}
	
	
	//Send command to server
	public void sendCmdtoServer(String cmd) throws IOException{
		try{
			Writer output = new OutputStreamWriter(socket.getOutputStream());
			output.write(cmd + "\n");
			output.flush();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Get message from server
	public void getCmdfromServer() {
		String message;
		while(connected){
			try {
				if((message = in.readLine()) == null)
					break;
				textArea.append(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	
	public Client() {
		super();
		input.addKeyListener(new KeyListener() {
			
			@Override
			public void keyPressed(KeyEvent e) {
				//Enter listener for user command
				if(e.getKeyCode() == KeyEvent.VK_ENTER && connected){
					incmd = input.getText();
					//Blank check
					if(!(incmd.equals(""))){
						try {
							sendCmdtoServer(incmd);
							//socket.close();
						} catch (IOException e1) {
							e1.printStackTrace();
						} 
						input.setText("");
						textArea_1.append(incmd + "\n");
					}
				}else if (e.getKeyCode() == KeyEvent.VK_ENTER && !connected) {	//Connection check
					incmd = input.getText();
					input.setText("");
					textArea_1.append(incmd + "\n");
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
					in = new BufferedReader(new InputStreamReader(
							socket.getInputStream())); //Get control command
					out = new OutputStreamWriter(socket.getOutputStream());	//Pass data
					String cmd = "";
					if(!connected){
						out.write("I_AM_CLIENT\n");
						out.flush();
						socket.setSoTimeout(3000);
						cmd = in.readLine();
					}
					if(cmd.equals("CONNECTION_ESTABLISHED") && !connected){
						textArea_1.setText(textArea.getText() + cmd + "\n");
						connected = true;
					}else if(connected) {	//Connection check
						textArea_1.append("Already Connected\n");
					}
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
				try{
					out.write("GOOD_BYE\n");
					out.flush();
					connected = false;
					socket.close();
					//in.close();
					out.close();
					System.exit(0);
				} catch (Exception e) {
					e.printStackTrace();
					System.exit(1);
				}
			}
		});
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				try{
					out.write("GOOD_BYE\n");
					out.flush();
					connected = false;
					socket.close();
					out.close();
				}catch (Exception e) {
					e.printStackTrace();
				}
				super.windowClosing(arg0);
			}
		});
	}
	
	//
	public void setDefaultCloseOperation(int arg0) {
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
					frame.getCmdfromServer();
					frame.start();
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
