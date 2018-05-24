package com.zmud.jlu;


public interface CommonContent {
	String CANNOTMOVE = "这个方向没有出路。";
	enum DIRECTION {
		EAST, WEST, SOUTH, NORTH, NORTHEAST, NORTHWEST, SOUTHEAST, SOUTHWEST,UP, DOWN 
	};
	int CHARS_PER_LINE = 40;
}
