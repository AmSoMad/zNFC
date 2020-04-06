package vo;

import javax.servlet.http.HttpServletRequest;

public class CreateBean {
	private String nfcid;
	private String name;
	private String gender;
	private String position;
	private String employeeNumber;
	
	public String getNfcid() {
		return nfcid;
	}
	public void setNfcid(String nfcid) {
		this.nfcid = nfcid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getEmployeeNumber() {
		return employeeNumber;
	}
	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}
	
	public CreateBean(HttpServletRequest request) {
		setNfcid(request.getParameter("nfcid"));
		setName(request.getParameter("name"));
		setGender(request.getParameter("gender"));
		setPosition(request.getParameter("position"));
		setEmployeeNumber(request.getParameter("employeeNumber"));
		System.out.println(getNfcid() + getName()+getGender()+getPosition()+getEmployeeNumber());
		
	}
	
}
