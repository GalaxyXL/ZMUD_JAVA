package com.zmud.jlu;

import java.util.*;

import com.zmud.jlu.Server.ServerThread;

public class Room {

	private HashMap<CommonContent.DIRECTION, Room> neighbor = new HashMap<CommonContent.DIRECTION, Room>();

	void setRoom(CommonContent.DIRECTION d, Room r) {
		neighbor.put(d, r);
		// assert r.getRoom(d) == this;
	}

	public Room getRoom(CommonContent.DIRECTION d) {
		return neighbor.get(d);

	}

	private String roomDescription;
	private String roomLooking;
	private String roomId;
	private String roomName;
	private HashMap<String, Player> playerList = new HashMap<String, Player>();

	public void exist(Player player, CommonContent.DIRECTION direction) {
		
	}

	//enter room
	public void enter(Player player, CommonContent.DIRECTION direction) {
		removePlayer(player);
		player.setRoom(neighbor.get(direction).getRoomId());
		neighbor.get(direction).addPlayer(player);
	}
	
	//Remove player push notify to other user
	public void removePlayer(Player player){
		playerList.remove(Integer.toString(player.getId()), player);
		Iterator it = playerList.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Player> mplayer = (Map.Entry)it.next();
			MessageManagement.showToPlayer(mplayer.getValue(), "玩家" + player.getName() + "离开了了房间");
		}
	}
	
	public void addPlayer(Player player){
	//用户连线进入，加入列表，通知房间其他玩家
		Iterator it = playerList.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Player> mplayer = (Map.Entry)it.next();
			MessageManagement.showToPlayer(mplayer.getValue(), "玩家" + player.getName() + "进入了房间");
		}
		playerList.put(Integer.toString(player.getId()), player);
	}

	public void setDescription(String roomDescription) {
		this.roomDescription = roomDescription;
	}

	public String getDescription() {
		return roomDescription;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getRoomId() {
		return roomId;
	}

	public void SetRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getRoomName() {
		return roomName;
	}

	/*public String getRoomLooking() {
		return roomLooking;
	}*/

	public String getRoomLooking() {
		// 房间名
		roomLooking = roomName + "\t";
		// 房间描述
		// 应该由Client负责解析传输过来的字符（设定字体，每行字数）
		roomLooking += roomDescription + "\t";

		// 房间出口
		roomLooking += getChuKou() + "\t";
		// 房间npc

		// 房间player
		roomLooking += listRoomPlayers();
		// 房间obj
		return roomLooking;
	}
	private String listRoomPlayers(){
		String temp = "";
		Iterator it = playerList.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Player> mplayer = (Map.Entry)it.next();
			temp +=  mplayer.getValue().getName() + "\t";
		}
		//列出这个房间中的所有玩家
		return temp;
	}
	private String getChuKou() {
		/*描述每个房间的出口
		 * 
		 * 
		 * */
		String temp = "";
		Iterator it = neighbor.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry<CommonContent.DIRECTION, Room> chukou = (Map.Entry)it.next();
			temp += chukou.getKey() + chukou.getValue().getRoomName() + "\t";
		}
		
		
		return temp;
	}
}
