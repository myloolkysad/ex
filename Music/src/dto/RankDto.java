package dto;

import java.io.Serializable;

public class RankDto implements Serializable {
	
	private static final long serialVersionUID = -4274700572038677000L;
	
	private int rankMusicNum; /*���ǹ�ȣ*/   
	private int rankSearchNum;  /*�˻���*/
	private int rankPlayNum; /*�����*/
	
	public int getRankMusicNum() {
		return rankMusicNum;
	}
	public void setRankMusicNum(int rankMusicNum) {
		this.rankMusicNum = rankMusicNum;
	}
	public int getRankSearchNum() {
		return rankSearchNum;
	}
	public void setRankSearchNum(int rankSearchNum) {
		this.rankSearchNum = rankSearchNum;
	}
	public int getRankPlayNum() {
		return rankPlayNum;
	}
	public void setRankPlayNum(int rankPlayNum) {
		this.rankPlayNum = rankPlayNum;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
