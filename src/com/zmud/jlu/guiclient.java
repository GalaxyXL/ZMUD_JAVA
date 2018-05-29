package com.zmud.jlu;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.TextArea;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpringLayout;
//import net.miginfocom.swing.MigLayout;

public class guiclient extends JFrame {

	private static final String version = "0.2";
	
	public JPanel contentPane;
	
	
	JLabel label = new JLabel("Game");
	JLabel lblNewLabel = new JLabel("System");
	JLabel lblNewLabel_1 = new JLabel("Chat Room");
	
	JPanel panel = new JPanel();
	JPanel panel_1 = new JPanel();
	
	JButton btnNewButton = new JButton("Connect");
	JButton button_1 = new JButton("Map");
	JButton button_2 = new JButton("Help DOC");
	JButton button = new JButton("Exit");
	
	JTextArea textArea = new JTextArea();
	JTextArea textArea_1 = new JTextArea();
	JTextArea textArea_2 = new JTextArea();
	
	JTextField input = new JTextField();
	JTextField textField_1 = new JTextField();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					guiclient frame = new guiclient();
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public guiclient() {
		super(version);
		//Set Scroll
		textArea.setEditable(false);
		textArea.setAutoscrolls(true);
		textArea.setLineWrap(true);
		JScrollPane jsTextArea = new JScrollPane(textArea);
		
		textArea_1.setEditable(false);
		textArea_1.setAutoscrolls(true);
		textArea_1.setLineWrap(true);
		JScrollPane jsTextArea_1 = new JScrollPane(textArea_1);
		
		textArea_2.setEditable(false);
		textArea_2.setAutoscrolls(true);
		textArea_2.setLineWrap(true);
		JScrollPane jsTextArea_2 = new JScrollPane(textArea_2);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{774, 0};
		gbl_contentPane.rowHeights = new int[]{23, 528, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.NORTH;
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		contentPane.add(panel, gbc_panel);
		panel.setLayout(new GridLayout(0, 4, 0, 0));
		
		btnNewButton.setBorderPainted(false);
		panel.add(btnNewButton);
		
		button_1.setBorderPainted(false);
		panel.add(button_1);
		
		button_2.setBorderPainted(false);
		panel.add(button_2);
		
		button.setBorderPainted(false);
		panel.add(button);
		
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 1;
		contentPane.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		label.setBackground(Color.LIGHT_GRAY);
		label.setOpaque(true);
		
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.fill = GridBagConstraints.BOTH;
		gbc_label.gridwidth = 24;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		panel_1.add(label, gbc_label);
		
		lblNewLabel.setBackground(Color.LIGHT_GRAY);
		lblNewLabel.setOpaque(true);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 24;
		gbc_lblNewLabel.gridy = 0;
		panel_1.add(lblNewLabel, gbc_lblNewLabel);
		
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.gridwidth = 24;
		gbc_textArea.gridheight = 16;
		gbc_textArea.insets = new Insets(0, 0, 5, 5);
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 0;
		gbc_textArea.gridy = 1;
		panel_1.add(jsTextArea, gbc_textArea);
		
		GridBagConstraints gbc_textArea_1 = new GridBagConstraints();
		gbc_textArea_1.gridheight = 8;
		gbc_textArea_1.insets = new Insets(0, 0, 5, 0);
		gbc_textArea_1.fill = GridBagConstraints.BOTH;
		gbc_textArea_1.gridx = 24;
		gbc_textArea_1.gridy = 1;
		panel_1.add(jsTextArea_1, gbc_textArea_1);
		
		lblNewLabel_1.setBackground(Color.LIGHT_GRAY);
		lblNewLabel_1.setOpaque(true);
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.gridx = 24;
		gbc_lblNewLabel_1.gridy = 9;
		panel_1.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		GridBagConstraints gbc_textArea_2 = new GridBagConstraints();
		gbc_textArea_2.insets = new Insets(0, 0, 5, 0);
		gbc_textArea_2.anchor = GridBagConstraints.EAST;
		gbc_textArea_2.gridheight = 7;
		gbc_textArea_2.fill = GridBagConstraints.BOTH;
		gbc_textArea_2.gridx = 24;
		gbc_textArea_2.gridy = 10;
		panel_1.add(jsTextArea_2, gbc_textArea_2);
		
		input = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 24;
		gbc_textField.insets = new Insets(0, 0, 0, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 17;
		panel_1.add(input, gbc_textField);
		input.setColumns(10);
		
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 24;
		gbc_textField_1.gridy = 17;
		panel_1.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
	}
}
