package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DAO.SearchDAO;
import DAO.createDAO;
import vo.AttendBean;

@WebServlet("/check")
public class cardtag extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public cardtag() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String requestURI = request.getRequestURI(); // 전체경로 읽기
		System.out.println(requestURI + "---1");
		String contextPath = request.getContextPath(); // 컨텍스트 패스
		System.out.println(contextPath + "---2");
		String command = requestURI.substring(contextPath.length()); // URI 중 컨텍스트패스 지우기
		System.out.println(command + "---3");
		createDAO createDAO = null;
		RequestDispatcher dispatcher = null;
		String name = "";
		AttendBean attendBean = null;
		
		if (command.equals("/check")) {
			System.out.println("출근DB이동");
			attendBean = new AttendBean(request);
			SearchDAO searchDAO = new SearchDAO(request);
			searchDAO.Search_Time_DAO(request);
			name = searchDAO.getName();
			createDAO = new createDAO();
			int count = createDAO.attendance(attendBean);
			if (name != null) {
				System.out.println(name);
				if (count == 0) {
					// 알림창
					dispatcher = request.getRequestDispatcher("A_completion.jsp");
					request.setAttribute("nfcid", attendBean.getNfcid());
					request.setAttribute("Commuting", "empty");
					dispatcher.forward(request, response);
				} else if (count == 1) {
					// 알림창
					dispatcher = request.getRequestDispatcher("A_completion.jsp");
					request.setAttribute("nfcid", attendBean.getNfcid());
					request.setAttribute("time", attendBean.getAttenddance());
					request.setAttribute("Commuting", "attend");
					request.setAttribute("name", name);
					dispatcher.forward(request, response);
				} else if (count == 10000) {
					System.out.println("출근이력있다 퇴근이동");
					attendBean = new AttendBean(request);
					searchDAO = new SearchDAO(request);
					name = searchDAO.getName();
					createDAO = new createDAO();
					count = createDAO.leave_Work(attendBean);
					if (count == 0) {
						// 알림창
						dispatcher = request.getRequestDispatcher("A_completion.jsp");
						request.setAttribute("nfcid", attendBean.getNfcid());
						request.setAttribute("Commuting", "empty");
						dispatcher.forward(request, response);
					} else if (count == 1) {// 성공구간
						dispatcher = request.getRequestDispatcher("A_completion.jsp");
						request.setAttribute("nfcid", attendBean.getNfcid());
						request.setAttribute("time", attendBean.getAttenddance());
						request.setAttribute("Commuting", "leave");
						request.setAttribute("name", name);
						dispatcher.forward(request, response);
						// 근무시간등록
						searchDAO.WorkingTime(request, attendBean);
						String w_Time = searchDAO.getWorking();
						createDAO.WorkingTime(attendBean, w_Time);
					} else if (count == 10001) {
						dispatcher = request.getRequestDispatcher("A_completion.jsp");
						request.setAttribute("nfcid", attendBean.getNfcid());
						request.setAttribute("time", attendBean.getAttenddance());
						request.setAttribute("Commuting", "already2");
						dispatcher.forward(request, response);
					}
					System.out.println("퇴근시간 등록완료");
					// 알림창
				} else {
					System.out.println(name);
					dispatcher = request.getRequestDispatcher("A_completion.jsp");
					request.setAttribute("nfcid", attendBean.getNfcid());
					request.setAttribute("time", attendBean.getAttenddance());
					request.setAttribute("Commuting", "already");
					dispatcher.forward(request, response);

				}
			}
		}

	}

}
