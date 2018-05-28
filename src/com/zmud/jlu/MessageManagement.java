package com.zmud.jlu;

import java.io.*;
import java.util.*;
public class MessageManagement {
	static HashMap<String,BufferedWriter> playerChannels = new HashMap<String,BufferedWriter>();
	
	public static void showToPlayer(Player player, String message){
		BufferedWriter bw = playerChannels.get(Integer.toString(player.getId()));
		try {
			bw.write(message + "\n");
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void addPlayerChannels(String playerId,BufferedWriter bw){
		playerChannels.put(playerId, bw);
	}
	public static void removePlayerChannels(String playerId){
		playerChannels.remove(playerId);
	}
}
