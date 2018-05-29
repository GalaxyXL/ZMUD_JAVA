package com.zmud.jlu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.Element;

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
			MessageManagement.showToPlayer(p0, "你进入了" + location.getRoomName());
			p0.setRoom(location.getRoomId());
			
			Server.OnlinePlayer.put(Integer.toString(p0.getId()), p0);
			
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
				
				MessageManagement.showToPlayer(p0, "你进入了" + location.getRoomName());
				p0.setRoom(location.getRoomId());
				
				Server.OnlinePlayer.put(Integer.toString(p0.getId()), p0);
				
				return p0;
			}else{
				output.write("密码错误！\n");
				output.flush();
			}
		}
		return null;
	}
	
	public static void dealInput(Player player, String inputMessage, int i){
		Player target = null;
		Room location = player.getRoom();
		String[] inputs = inputMessage.split(" ");
		if(inputs[0].equals("tell")){
			if((target = location.findPlayer(XmlFileManagement.getPlayerIdbyName(inputs[1]))) != null){
				String outMessage = "/re_message$" + player.getName() + "对你说：" + inputMessage.substring(5 + inputs[1].length() , inputMessage.length());
				String selfMessage = "/re_message$" + "你对" + target.getName() + "说：" + inputMessage.substring(5 + inputs[1].length() , inputMessage.length());
				MessageManagement.showToPlayer(target, outMessage);
				MessageManagement.showToPlayer(player, selfMessage);
			}else{
				MessageManagement.showToPlayer(player, "/re_message$房间中无此玩家");
			}
		}else{
			player.getRoom().announceMessage("/re_message$" + player.getName() + "：" +inputMessage);
		}
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
		if (inputs[0].equals("who")){
			String outMessage = "在线玩家：";
			for(Player online:Server.OnlinePlayer.values()){
				outMessage += online.getName() + "  ";
			}
			MessageManagement.showToPlayer(player, outMessage);
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
	}
}
