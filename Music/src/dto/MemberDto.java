//�α����� ������ ������ ����

package dto;

import java.io.Serializable;

public class MemberDto implements Serializable {

	private static final long serialVersionUID = -4274700572038677000L;

	private String memberid; /*���̵�*/
	private String memberpw; /*��й�ȣ*/
	private String memberName; /*�̸�*/
	private String memberBirth; /*�������*/
	private String memberPhone; /*����ó*/
	private String memberMail; /*�̸���*/
	private String memberAddress; /*�ּ�*/
	private String memberSignUpDay; /*������*/
	private String memberFinalLogin; /*������������*/
	private String memberFreeTicket; /*�̿������*/
	
	
	public String getMemberFreeTicket() {
		return memberFreeTicket;
	}
	public void setMemberFreeTicket(String memberFreeTicket) {
		this.memberFreeTicket = memberFreeTicket;
	}
	public String getMemberid() {
		return memberid;
	}
	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}
	public String getMemberpw() {
		return memberpw;
	}
	public void setMemberpw(String memberpw) {
		this.memberpw = memberpw;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberBirth() {
		return memberBirth;
	}
	public void setMemberBirth(String memberBirth) {
		this.memberBirth = memberBirth;
	}
	public String getMemberPhone() {
		return memberPhone;
	}
	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}
	public String getMemberMail() {
		return memberMail;
	}
	public void setMemberMail(String memberMail) {
		this.memberMail = memberMail;
	}
	public String getMemberAddress() {
		return memberAddress;
	}
	public void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
	}
	public String getMemberSignUpDay() {
		return memberSignUpDay;
	}
	public void setMemberSignUpDay(String memberSignUpDay) {
		this.memberSignUpDay = memberSignUpDay;
	}
	public String getMemberFinalLogin() {
		return memberFinalLogin;
	}
	public void setMemberFinalLogin(String memberFinalLogin) {
		this.memberFinalLogin = memberFinalLogin;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	
}
