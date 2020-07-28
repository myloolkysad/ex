package dto;

import java.io.Serializable;

public class PlayDto implements Serializable {

	private static final long serialVersionUID = -4274700572038677000L;
	
	private String playUserid; 
	private int playMusicNum;
	private int playNum; 
	private String playMusicName;
	
	public String getPlayMusicName() {
		return playMusicName;
	}
	public void setPlayMusicName(String playMusicName) {
		this.playMusicName = playMusicName;
	}
	public String getPlayUserid() {
		return playUserid;
	}
	public void setPlayUserid(String playUserid) {
		this.playUserid = playUserid;
	}
	public int getPlayMusicNum() {
		return playMusicNum;
	}
	public void setPlayMusicNum(int playMusicNum) {
		this.playMusicNum = playMusicNum;
	}
	public int getPlayNum() {
		return playNum;
	}
	public void setPlayNum(int playNum) {
		this.playNum = playNum;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
