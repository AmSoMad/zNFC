package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import db.JdbcUill;
import vo.AttendBean;
import vo.CreateBean;

public class createDAO {
	Connection connection = null;
	int count = 0;
	int count2 = 0;

	public int createAction(CreateBean createBean) {
		connection = JdbcUill.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String insert = "INSERT INTO nfc.state (`아이디`,`이름`,`성별`,`직급`,`사원번호`) VALUES (?,?,?,?,?)";
		try {
			pstmt = connection.prepareStatement(insert);
			pstmt.setString(1, createBean.getNfcid());
			pstmt.setString(2, createBean.getName());
			pstmt.setString(3, createBean.getGender());
			pstmt.setString(4, createBean.getPosition());
			pstmt.setString(5, createBean.getEmployeeNumber());	
			count = pstmt.executeUpdate(); //익스큐트해야 쿼리날라감
			JdbcUill.commit(connection);
			System.out.println("create 쿼리성공");
		} catch (Exception e) {
			System.out.println("create 쿼리실패");
			e.printStackTrace();
		}finally {
			JdbcUill.close(connection);
			JdbcUill.close(pstmt);
			JdbcUill.close(rs);
		}
		return count + count2;
	}
	
	//출근
	public int attendance(AttendBean attendBean) {
		connection = JdbcUill.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String insert1 = "INSERT INTO nfc.attendtime (`아이디`,`날짜`,`출근시간`) VALUES (?,?,?)";
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date currentTimeDate = new Date();
			String dayString = simpleDateFormat.format(currentTimeDate);
			pstmt = connection.prepareStatement(insert1);
			pstmt.setString(1, attendBean.getNfcid());
			pstmt.setString(2, dayString);
			pstmt.setString(3, attendBean.getAttenddance());
			count = pstmt.executeUpdate(); //익스큐트해야 쿼리날라감		
			JdbcUill.commit(connection);
			System.out.println("attendance 쿼리성공");
			System.out.println(count + "개수 등록뜸");
		} catch (Exception e) {
			System.out.println("attendance 쿼리실패");
			count = 10000;
			System.out.println(count);
			e.printStackTrace();
		}finally {
			JdbcUill.close(connection);
			JdbcUill.close(pstmt);
			JdbcUill.close(rs);
		}
		return count;
	}

	//퇴근
	public int leave_Work(AttendBean attendBean) {
		connection = JdbcUill.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String insert = "UPDATE nfc.attendtime SET `퇴근시간`=? WHERE  `아이디`=? and `날짜`=?";
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date currentTimeDate = new Date();
			String dayString = simpleDateFormat.format(currentTimeDate);
			System.out.println(dayString + "이날짜가 입력되야함");
			pstmt = connection.prepareStatement(insert);
			pstmt.setString(1, attendBean.getAttenddance());
			pstmt.setString(2, attendBean.getNfcid());
			pstmt.setString(3, dayString);
			count = pstmt.executeUpdate(); //익스큐트해야 쿼리날라감
			JdbcUill.commit(connection);
			System.out.println("attendance 쿼리성공");
		} catch (Exception e) {
			System.out.println("attendance 쿼리실패");
			count = 10001;
			System.out.println(count);
			e.printStackTrace();
		}finally {
			JdbcUill.close(connection);
			JdbcUill.close(pstmt);
			JdbcUill.close(rs);
		}
		return count;
	}
	
	//출근시간 퇴근시간을 String 으로 받고. 입력하는곳
	public int WorkingTime(AttendBean attendBean, String w_Time) {
		connection = JdbcUill.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String insert = "UPDATE nfc.attendtime SET `근무시간`=? WHERE  `아이디`=? and `날짜`=?";
		
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date currentTimeDate = new Date();
			String dayString = simpleDateFormat.format(currentTimeDate);
			System.out.println(dayString + "이날짜가 입력되야함");
			pstmt = connection.prepareStatement(insert);
			pstmt.setString(1, w_Time);
			pstmt.setString(2, attendBean.getNfcid());
			pstmt.setString(3, dayString);
			count = pstmt.executeUpdate(); //익스큐트해야 쿼리날라감
			JdbcUill.commit(connection);
			System.out.println("attendance 쿼리성공");
		} catch (Exception e) {
			System.out.println("attendance 쿼리실패");
			count = 10001;
			System.out.println(count);
			e.printStackTrace();
		}finally {
			JdbcUill.close(connection);
			JdbcUill.close(pstmt);
			JdbcUill.close(rs);
		}
		return count;
	}
	
	
}
