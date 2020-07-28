package dto;

import java.io.Serializable;

public class MusicDto implements Serializable {
	private static final long serialVersionUID = -4274700572038677000L;
	
	private int musicNum; /*음악번호*/
	private String musicName; /*음악이름*/
	private String musicArtist; /*가수명*/
	private String musicAlbum; /*앨범명*/
	private String musicImgSre; /*이미지 경로*/
	
	
	public int getMusicNum() {
		return musicNum;
	}
	public void setMusicNum(int musicNum) {
		this.musicNum = musicNum;
	}
	public String getMusicName() {
		return musicName;
	}
	public void setMusicName(String musicName) {
		this.musicName = musicName;
	}
	public String getMusicArtist() {
		return musicArtist;
	}
	public void setMusicArtist(String musicArtist) {
		this.musicArtist = musicArtist;
	}
	public String getMusicAlbum() {
		return musicAlbum;
	}
	public void setMusicAlbum(String musicAlbum) {
		this.musicAlbum = musicAlbum;
	}
	public String getMusicImgSre() {
		return musicImgSre;
	}
	public void setMusicImgSre(String musicImgSre) {
		this.musicImgSre = musicImgSre;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
