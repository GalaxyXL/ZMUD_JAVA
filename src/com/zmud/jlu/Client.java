package com.zmud.jlu;

import java.io.*;
import javax.swing.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Client extends JFrame {
	//Main screen
	private JTextArea screen;
	
	//Input Field
	private JTextField input;
	
	//Connection button
	private JButton connection;
	
	//Show map button
	private JButton map;
	
	//Help Document botton
	private JButton helpdoc;
	
	//Exit buttom
	private JButton exit;

	private Socket socket;
	private BufferedReader in;
	private BufferedWriter out;

	private String ipaddress = "127.0.0.1";
	private int port = 1888;
	private boolean connected = false;

	class MonitorThread extends Thread {
		public MonitorThread(BufferedReader br) {
			//添加
		}

		BufferedReader br;

		@Override
		public void run() {
			//接收服务器消息的控制在这里添加
			
		}
	}
	
	public void connet (InetAddress address) throws IOException {
		this.socket = new Socket(address, Server.PORT_NUM);
	}

	public Client() {
		super("Mud Client");
		Container container = this.getContentPane();
		container.setLayout(new BorderLayout());
		JPanel leftPanel = new JPanel();
		JPanel rightPanel = new JPanel();
		container.add(BorderLayout.CENTER, leftPanel);
		container.add(BorderLayout.NORTH, rightPanel);
		
		leftPanel.setLayout(new BorderLayout());
		rightPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		
		screen = new JTextArea();
		screen.setEditable(false);
		screen.setAutoscrolls(true);
		JScrollPane jsp = new  JScrollPane(screen);
		
		//Button
		input = new JTextField();
		connection = new JButton("Conncet");	//Initial connection button
		helpdoc = new JButton("Help Doc");		//Initial help document button
		map = new JButton("Map");				//Initial map button
		exit = new JButton("Exit");				//Initial exit button
		
		Dimension preferredJbSize = new Dimension(295,30); 
		connection.setPreferredSize(preferredJbSize);
		helpdoc.setPreferredSize(preferredJbSize);
		map.setPreferredSize(preferredJbSize);
		exit.setPreferredSize(preferredJbSize);
		
		//Set button bounds
		connection.setBorderPainted(false);
		helpdoc.setBorderPainted(false);
		map.setBorderPainted(false);
		exit.setBorderPainted(false);
		
		leftPanel.add(BorderLayout.CENTER, jsp);
		leftPanel.add(BorderLayout.SOUTH, input);
		
		//Add button to panel
		rightPanel.add(connection);
		rightPanel.add(helpdoc);
		rightPanel.add(map);
		rightPanel.add(exit);
		
		
		this.setSize(1200, 800);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Connect to Server
		
		input.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				//用户键盘输入在这里添加
			}
		});
		connection.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					connet(InetAddress.getLoopbackAddress());
					BufferedReader input = new BufferedReader(new InputStreamReader(
							socket.getInputStream())); //Get control command
					DataOutputStream output = new DataOutputStream(socket.getOutputStream());	//Pass data
					
					String cmd = input.readLine();
					if(cmd.equals("WHO_ARE_YOU")){
						screen.setText("Connected!");
					}
					socket.close();
					//screen.setText("Test");
				} catch (Exception e) {
					e.printStackTrace();
					screen.setText(screen.getText() + "链接服务器失败！请重试\n");
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

	public static void main(String[] args) {
		new Client();
	}
}
