package com.zmud.jlu;

public class Player {
	
	public static int useramount = XmlFileManagement.readAmount(
			XmlFileManagement.getRootElement(
					XmlFileManagement.getDocment("Players.xml")));

	private int experience;
	private int con;
	private int dex;
	private int str;
	private int wis;
	private int hp, max_hp;
	private int nl, max_nl;
	private int jl, max_jl;
	private int id;
	private String username;
	private String party;	//帮派
	private String location;
	private String password;
	
	private Room room = null;

	public Player() {
		// creat player default value
	}
	
	public Player(String username, String password){
		this.username = username;
		this.password = password;
	}
		
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		System.out.println("player" + this.getName() + "is dead");
	}
	
	public Player(int experience,int con,int dex,int str,int wis,int hp,int max_hp,int nl,int max_nl,int jl,int max_jl,int id,String username,String party,String location){
		this.experience = experience;
		this.con = con;
		this.dex = dex;
		this.str = str;
		this.wis = wis;
		this.hp = hp;
		this.max_hp = max_hp;
		this.nl = nl;
		this.max_nl = max_nl;
		this.jl = jl;
		this.max_jl = jl;
		this.id = id;
		this.username = username;
		this.party = party;
		this.location = location;
	}

	public void move(CommonContent.DIRECTION direction) {
		
	}
	public void look(String something){
		if(something.equals(""))
			;//查看当前房间;
		else
			;//查看其它物品
	}
	
	public void quit(){
		//告诉房间退出了，释放资源
		
		
		//save添加在这里
	}
	public void setLocation(String location){
		
	}
	public String getLocation(){
		return this.location;
	}
	public int getId(){
		return id;
	}
	public void setId(String id){
		this.id = Integer.parseInt(id);
	}
	public String getName(){
		return this.username;
	}
	public void setName(String username){
		
	}
	
	public void setRoom(String roomid){
		room = Server.CityMap.get(roomid);
	}
	
	public Room getRoom(){
		return room;
	}
	
	public static void setUserAmount(int a){
		useramount = a;
	}
	
	public static int getUserAmount(){
		return useramount;
	}
}
