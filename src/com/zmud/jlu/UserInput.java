package com.zmud.jlu;
public class UserInput {
	public static void dealInput(Player player, String inputMessage) {
		/*
		 * 可以处理的命令 l,look e,east,w,west,n,north,s,south,
		 */

		String[] inputs = inputMessage.split(" ");
		if (inputs[0].equals("l") || inputs[0].equals("look"))
			if (inputs.length == 1) {
				
				return;
			}
		if (inputs[0].equals("quit")) {
			
			return;
		}
		if (inputs[0].equals("e") || inputs[0].equals("east")) {
			
			return;
		}
		if (inputs[0].equals("w") || inputs[0].equals("west")) {
			
			return;
		}
		if (inputs[0].equals("s") || inputs[0].equals("south")) {
			
			return;
		}
		if (inputs[0].equals("n") || inputs[0].equals("north")) {
			
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
