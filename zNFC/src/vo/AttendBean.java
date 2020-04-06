package vo;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

public class AttendBean {
	private String nfcid;
	private String attenddance;
	
	public String getNfcid() {
		return nfcid;
	}
	public void setNfcid(String nfcid) {
		this.nfcid = nfcid;
	}
	public String getAttenddance() {
		return attenddance;
	}
	public void setAttenddance(String attenddance) {
		this.attenddance = attenddance;
	}
	
	public AttendBean(HttpServletRequest request) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date time = new java.util.Date();
		String currentDatetime = format.format(time);
		setNfcid(request.getParameter("nfcid"));
		setAttenddance(currentDatetime);
		System.out.println(currentDatetime);
	}
}
