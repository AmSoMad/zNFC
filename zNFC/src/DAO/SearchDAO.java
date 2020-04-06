package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import db.JdbcUill;
import vo.AttendBean;

public class SearchDAO {
	Connection connection = null;
	private String name;
	private String work;
	private String work_2; 
	private String working;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWork() {
		return work;
	}
	public void setWork(String work) {
		this.work = work;
	}
	public String getWork_2() {
		return work_2;
	}
	public void setWork_2(String work) {
		this.work_2 = work;
	}
	public String getWorking() {
		return working;
	}
	public void setWorking(String working) {
		this.working = working;
	}
	
	public SearchDAO(HttpServletRequest request) {
		connection = JdbcUill.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		System.out.println(request.getParameter("nfcid")+"이걸 찾습니다.");
		String insert2 = "SELECT `이름` FROM nfc.state WHERE `아이디`=?";
		
		try {
			pstmt = connection.prepareStatement(insert2);
			pstmt.setString(1, request.getParameter("nfcid"));
			rs = pstmt.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getString(1)+"이게찍혀야한다.");
				setName(rs.getString(1));
				System.out.println(name);	
			}
						
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("검색실패");
		}finally {
			JdbcUill.close(connection);
			JdbcUill.close(pstmt);
			JdbcUill.close(rs);
		}
		
	}
	
	public void Search_Time_DAO(HttpServletRequest request) {
		connection = JdbcUill.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		System.out.println(request.getParameter("nfcid")+"이걸 찾습니다.");
		String insert2 = "SELECT `출근시간` FROM nfc.attendtime WHERE `아이디`=?";
		
		try {
			pstmt = connection.prepareStatement(insert2);
			pstmt.setString(1, request.getParameter("nfcid"));
			rs = pstmt.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getString(1)+"이게찍혀야한다.");
				setName(rs.getString(1));
				System.out.println(name);	
			}
						
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("검색실패");
		}finally {
			JdbcUill.close(connection);
			JdbcUill.close(pstmt);
			JdbcUill.close(rs);
		}
	}
	//
	public void WorkingTime(HttpServletRequest request, AttendBean attendBean) {
		connection = JdbcUill.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		System.out.println(request.getParameter("nfcid")+"출근시간찾습니다.");
		String select = "SELECT `출근시간`,`퇴근시간` FROM nfc.attendtime WHERE `아이디`=? AND `날짜`=?";
		try {
			
			pstmt = connection.prepareStatement(select);
			pstmt.setString(1, request.getParameter("nfcid"));
			pstmt.setString(2, attendBean.getAttenddance().substring(0,10));
			rs = pstmt.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getString(1) + "이거랑");
				System.out.println(rs.getString(2) + " 이거가 찍혀야한다.");
				setWork(rs.getString(1));
				setWork_2(rs.getString(2));
				System.out.println(work+work_2);	
			}
			
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date reqDate = simpleDateFormat.parse(getWork());
			Date reqDate2 = simpleDateFormat.parse(getWork_2());
			long time1 = reqDate.getTime();
			long time2 = reqDate2.getTime();
			System.out.println(time1 + " = time1");
			System.out.println(time2 + " = time2");
			//밀리색크
			long worktime = (time2 - time1) / 1000;
			System.out.println(worktime + "이건worktiime");
			int hour = (int)worktime / 3600;
			int minute = (int)(worktime % 3600 ) / 60;
			int sec = (int)(worktime % 60) % 60 ;
			setWorking(Integer.toString(hour) + " 시 "+Integer.toString(minute) + " 분 " + Integer.toString(sec) + " 초"); 
			System.out.println(getWorking() + "근무시간등록값");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("검색실패");
		}finally {
			JdbcUill.close(connection);
			JdbcUill.close(pstmt);
			JdbcUill.close(rs);
		}
		
	}
	
	
}
