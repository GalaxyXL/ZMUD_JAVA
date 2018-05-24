package com.zmud.jlu;

public class Player {

	private int experience;
	private int con;
	private int dex;
	private int str;
	private int wis;
	private int hp, max_hp;
	private int nl, max_nl;
	private int jl, max_jl;
	private String id;
	private String username;
	private String party;
	private String location;

	public Player() {
		// creat player default value
	}
	public Player(int experience,int con,int dex,int str,int wis,int hp,int max_hp,int nl,int max_nl,int jl,int max_jl,String id,String username,String party,String location){
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
	public String getId(){
		return id;
	}
	public void setId(String id){
		
	}
	public String getName(){
		return this.username;
	}
	public void setName(String username){
		
	}
}
