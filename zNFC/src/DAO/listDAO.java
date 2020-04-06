package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import db.JdbcUill;
import vo.listBean;

public class listDAO {
	Connection connection = null;
	String name;
	
	public ArrayList<listBean> selectArrayList(String name){
		connection = JdbcUill.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<listBean> list = new ArrayList<listBean>();
		listBean listBean = null;
		String insert = "SELECT * FROM nfc.attendtime WHERE `아이디`=?";
		try {
			pstmt = connection.prepareStatement(insert);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String workingString = "근무중";
				listBean = new listBean();
				listBean.setNfcid(rs.getString(1));
				listBean.setDay(rs.getString(2));
				listBean.setAttend(rs.getString(3));
				listBean.setLeave_Work(rs.getString(4));
				//근무시간
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date reqDateStr = dateFormat.parse(listBean.getAttend());
				if(listBean.getLeave_Work() != null) {
					Date curDate = dateFormat.parse(listBean.getLeave_Work());
					//현재시간을 요청시간의 형태로 format 후 time 가져오기
					long curDateTime = curDate.getTime();
					long reqDateTime = reqDateStr.getTime();
					//분으로 표현
					long minuteresult = (curDateTime - reqDateTime)/1000;
					System.out.println(minuteresult);
					int hour = (int)minuteresult / 3600;
					int minute = (int)(minuteresult % 3600 ) / 60;
					int sec = (int)(minuteresult % 60) % 60 ;
				    workingString = Integer.toString(hour) + " 시 "+Integer.toString(minute) + " 분 " + Integer.toString(sec) + " 초";
					System.out.println(workingString);
					listBean.setWorking(workingString);
				}else {
					workingString = "근무중";
					listBean.setLeave_Work("");
					listBean.setWorking(workingString);
				}
				
				list.add(listBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUill.close(connection);
			JdbcUill.close(rs);
			JdbcUill.close(pstmt);
		}
		return list;
	}
	
	
}
