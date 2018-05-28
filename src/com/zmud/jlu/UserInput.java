package com.zmud.jlu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import javax.crypto.CipherInputStream;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;

import com.zmud.jlu.Server.ServerThread;

public class UserInput {
	
	
	public static Player dealInput(String inputMessage, BufferedWriter output, BufferedReader input) throws IOException{
		/*
		 * Dealing with command register and login
		 */
		String incmd;
		String username, password;
		Player p0 = null;
		Room location = null;
		
		if(inputMessage.equalsIgnoreCase("register")){
			output.write("请输入用户名：\n");
			output.flush();
			incmd = input.readLine();
			username = incmd;
			output.write(incmd + "\n");
			output.flush();
			output.write("请输入密码： \n");
			output.flush();
			incmd = input.readLine();
			password = incmd;
			output.write(incmd + "\n");
			output.flush();
			
			p0 = new Player(username, password);
			XmlFileManagement.addNewPlayer(username, password);
			p0.setId(Integer.toString(Player.getUserAmount()));
			
			MessageManagement.addPlayerChannels(Integer.toString(p0.getId()), output);

			location = Server.CityMap.get("yangzhou_guangchang");
			
			location.addPlayer(p0);
			MessageManagement.showToPlayer(p0, location.getDescription());
			p0.setRoom(location.getRoomId());
			return p0;
		}
		
		else if(inputMessage.equalsIgnoreCase("login")){
			output.write("请输入用户名：\n");
			output.flush();
			incmd = input.readLine();
			output.write(incmd + "\n");
			output.flush();
			
			Document document = XmlFileManagement.getDocment("Players.xml");
			Element root = XmlFileManagement.getRootElement(document);
			Element player = XmlFileManagement.getPlayerNodeByName(root, incmd);
			
			output.write("请输入密码： \n");
			output.flush();
			incmd = input.readLine();
			output.write(incmd + "\n" + "登陆中...." + "\n");
			output.flush();
			
			password = XmlFileManagement.getPlayerPassword(player);
			if(incmd.equals(password)){
				p0 = XmlFileManagement.getPlayerAllInfoById(XmlFileManagement.getPlayerId(player));
				MessageManagement.addPlayerChannels(Integer.toString(p0.getId()), output);
				MessageManagement.showToPlayer(p0, "登陆成功！");
				location = Server.CityMap.get(XmlFileManagement.getRequiredPlayerInfo(player, "room"));
				location.addPlayer(p0);
				p0.setRoom(location.getRoomId());
				return p0;
			}else{
				output.write("密码错误！\n");
				output.flush();
			}
		}
		return null;
	}
	
	public static void dealInput(Player player, String inputMessage) {
		/*
		 * 可以处理的命令 l,look e,east,w,west,n,north,s,south,
		 */

		String[] inputs = inputMessage.split(" ");
		if (inputs[0].equals("l") || inputs[0].equals("look"))
			if (inputs.length == 1) {
				MessageManagement.showToPlayer(player, player.getRoom().getRoomLooking());
				return;
			}
		if (inputs[0].equals("quit")) {
			
			return;
		}
		if (inputs[0].equals("e") || inputs[0].equals("east")) {
			player.getRoom().enter(player, CommonContent.DIRECTION.EAST);
			return;
		}
		if (inputs[0].equals("w") || inputs[0].equals("west")) {
			player.getRoom().enter(player, CommonContent.DIRECTION.WEST);
			return;
		}
		if (inputs[0].equals("s") || inputs[0].equals("south")) {
			player.getRoom().enter(player, CommonContent.DIRECTION.SOUTH);
			return;
		}
		if (inputs[0].equals("n") || inputs[0].equals("north")) {
			player.getRoom().enter(player, CommonContent.DIRECTION.NORTH);
			return;
		}
		if (inputs[0].equals("ne") || inputs[0].equals("northeast")) {
			
			return;
		}
		if (inputs[0].equals("nw") || inputs[0].equals("northwest")) {
			
			return;
		}
		if (inputs[0].equals("se") || inputs[0].equals("southeast")) {
			
			return;
		}
		if (inputs[0].equals("sw") || inputs[0].equals("southwest")) {
			
			return;
		}
		if (inputs[0].equals("u") || inputs[0].equals("up")) {
			
			return;
		}
		if (inputs[0].equals("d") || inputs[0].equals("down")) {
			
			return;
		}
		
		//MessageManagement.showToPlayer(player, "什么？\n");
	}
}
