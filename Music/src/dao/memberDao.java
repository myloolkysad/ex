package dao;

import java.util.ArrayList;

import dto.MemberDto;

public class memberDao {
	
	private ArrayList<MemberDto> listOfProducts = new ArrayList<MemberDto>();
	private static memberDao instance = new memberDao();

	public static memberDao getInstance(){
		return instance;
	} 

	public ArrayList<MemberDto> getAllMembers() {
		return listOfProducts;
	}
	
	public MemberDto getMemberById(String userid) {
		MemberDto memberId = null;

		for (int i = 0; i < listOfProducts.size(); i++) {
			MemberDto member = listOfProducts.get(i);
			//if (member != null && member.getMemberid() != null && member.getMemberid().equals(userid)) {
				memberId = member;
				break;
			}
		return null;
		}
		//return memberId;
	//}
	
	public void addMember(MemberDto product) {
		listOfProducts.add(product);
	}
	
}
