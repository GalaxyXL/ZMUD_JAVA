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
			MessageManagement.showToPlayer(mplayer.getValue(), "���" + player.getName() + "�뿪���˷���");
		}
	}
	
	public void addPlayer(Player player){
	//�û����߽��룬�����б�֪ͨ�����������
		Iterator it = playerList.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Player> mplayer = (Map.Entry)it.next();
			MessageManagement.showToPlayer(mplayer.getValue(), "���" + player.getName() + "�����˷���");
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
		// ������
		roomLooking = roomName + "\t";
		// ��������
		// Ӧ����Client�����������������ַ����趨���壬ÿ��������
		roomLooking += roomDescription + "\t";

		// �������
		roomLooking += getChuKou() + "\t";
		// ����npc

		// ����player
		roomLooking += listRoomPlayers();
		// ����obj
		return roomLooking;
	}
	private String listRoomPlayers(){
		String temp = "";
		Iterator it = playerList.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Player> mplayer = (Map.Entry)it.next();
			temp +=  mplayer.getValue().getName() + "\t";
		}
		//�г���������е��������
		return temp;
	}
	private String getChuKou() {
		/*����ÿ������ĳ���
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
