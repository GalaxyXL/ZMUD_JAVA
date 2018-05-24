package com.zmud.jlu;

import java.io.*;
import javax.swing.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Client extends JFrame {
	private JTextArea screen;
	private JTextField input;
	private JButton connection;

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

	public Client() {
		super("Mud Client");
		Container container = this.getContentPane();
		container.setLayout(new BorderLayout());
		JPanel leftPanel = new JPanel();
		JPanel rightPanel = new JPanel();
		container.add(BorderLayout.CENTER, leftPanel);
		container.add(BorderLayout.EAST, rightPanel);
		leftPanel.setLayout(new BorderLayout());
		screen = new JTextArea();
		screen.setEditable(false);
		screen.setAutoscrolls(true);
		JScrollPane jsp = new  JScrollPane(screen);
		input = new JTextField();
		connection = new JButton("conncet");
		leftPanel.add(BorderLayout.CENTER, jsp);
		leftPanel.add(BorderLayout.SOUTH, input);
		// rightPanel.setLayout(new FlowLayout());
		rightPanel.add(connection);
		this.setSize(800, 600);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
					//连接服务器在这里添加

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
