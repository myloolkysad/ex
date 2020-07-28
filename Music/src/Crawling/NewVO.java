package Crawling;

public class NewVO {
	String name;
	String artist;
	String album;
	String src;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public String getAlbum() {
		return album;
	}
	public void setAlbum(String album) {
		this.album = album;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	
public NewVO(String name, String artist,String album, String src) {
	this.name = name;
	this.artist = artist;
	this.album = album;
	this.src = src;
}

}
