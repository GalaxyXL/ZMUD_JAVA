package com.zmud.jlu;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class XmlFileManagement {
	
	//Get XML document
	public static Document getDocment(String filename){
		SAXReader reader = new SAXReader();
		Document document = null;
		try {
			document = reader.read(new File(filename));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return document;
	}
	
	//Get XML root element
	public static Element getRootElement(Document document){
        Element rootElement = document.getRootElement();
		return rootElement;
	}
	
	//Get the amount of players
	public static int readAmount(Element root){
		Attribute atrributeamount = root.attribute("Amount");
		String amount = atrributeamount.getText();
		int amot = Integer.parseInt(amount);
		return amot;
	}
	
	//Get target player node by username
	public static Element getPlayerNodeByName(Element rootElement, String username){
		List<Element> Players = rootElement.elements();
		List<Element> playerinfo = null;
		Iterator<Element> it = Players.iterator();
		Iterator<Element> it1 = null;
		Element targetplayer = null;
		
		while(it.hasNext()){
			Element eplayer = it.next();
			playerinfo = eplayer.elements();
			it1 = playerinfo.iterator();
			while(it1.hasNext()){
				Element info = it1.next();
				if(info.getName().equals("username"))
					if(info.getText().equals(username))
						targetplayer = eplayer;
			}
		}
		return targetplayer;
	}
	
	//Get target player node by id
	public static Element getPlayerNodeById(Element rootElement, String id){
		List<Element> Players = rootElement.elements();
		Iterator<Element> it = Players.iterator();
		Element targetplayer = null;

		while(it.hasNext()){
			Element eplayer = it.next();
			Attribute pid = eplayer.attribute("id");
			if(pid.getText().equals(id))
				targetplayer = eplayer;
		}
		return targetplayer;
	}
	
	//Get the Player object
	public static Player getPlayerAllInfoById(String id){
		Element pnode = getPlayerNodeById(getDocment("Players.xml").getRootElement(), id);
		String username = getRequiredPlayerInfo(pnode, "username");
		String password = getPlayerPassword(pnode);
		
		Player p0 = new Player(username,password);
		p0.setId(id);
		return p0;
	}
	
	//Get User id
	public static String getPlayerId(Element Player){
		String id = null;
		Attribute attr = Player.attribute("id");
		id = attr.getText();
		return id;
	}
	
	public static String getPlayerIdbyName(String name){
		return getPlayerId(getPlayerNodeByName(getDocment("Players.xml").getRootElement(), name));
	}
	
	//Get needed user info by name
	public static String getRequiredPlayerInfo(Element pnode, String infomess){
		List<Element> pinfo = pnode.elements();
		Iterator<Element> it = pinfo.iterator();
		Element info = null;
		String inf = null;
		
		while(it.hasNext()){
			info = it.next();
			if(info.getName().equals(infomess))
				inf = info.getText();
		}
		return inf;
	}
	
	//Write Required info
	public static void setRequiredPlayerInfo(Element pnode, String targetinfo, String infomation){
		List<Element> pinfo = pnode.elements();
		Iterator<Element> it = pinfo.iterator();
		Element info = null;

		while(it.hasNext()){
			info = it.next();
			if(info.getName().equals(targetinfo)){
				info.setText(infomation);
				
				//File Edit
				OutputFormat format = OutputFormat.createPrettyPrint();
		        format.setEncoding("UTF-8");
		        try {
					OutputStream outputStream = new FileOutputStream("Players.xml");
					XMLWriter xmlWriter = new XMLWriter(outputStream,format);
					xmlWriter.write(pnode.getDocument());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	//Get target player password
	public static String getPlayerPassword(Element eplayer){
		List<Element> pinfo = eplayer.elements();
		Iterator<Element> it = pinfo.iterator();
		Element info = null;
		String password = null;
		
		while(it.hasNext()){
			info = it.next();
			if(info.getName().equals("password"))
				password = info.getText();
		}
		return password;
	}
	
	//Save new user to file
	public static void addNewPlayer(String username, String password)  {
		Document document = getDocment("Players.xml");
		Element root = getRootElement(document);
		Element pnode = root.addElement("Player");
		Element Name = pnode.addElement("username");
		Element Password = pnode.addElement("password");
		Element Roome = pnode.addElement("room");
		String amount = root.attribute("Amount").getText();
		OutputStream outputStream = null;
		
		Name.setText(username);
		Password.setText(password);
		Roome.setText("yangzhou_guangchang");
		
		int amot = Integer.parseInt(amount);
		++amot;
		amount = Integer.toString(amot);
		root.attribute("Amount").setText(amount);;
		pnode.addAttribute("id", amount);
		Player.setUserAmount(amot);
		
		//File Edit
		OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("UTF-8");
        try {
			outputStream = new FileOutputStream("Players.xml");
			XMLWriter xmlWriter = new XMLWriter(outputStream,format);
			xmlWriter.write(document);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
