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
import DAO.listDAO;
import vo.AttendBean;
import vo.CreateBean;
import vo.SecurityBean;

@WebServlet("*.bo")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Servlet() {
       
    }

    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
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
		SecurityBean securityBean = new SecurityBean();
		//메인
		//신규등록
		if(command.equals("/createView.bo")) {
			response.sendRedirect("create.jsp");
			System.out.println("생성화면이동");
		}
		if(command.equals("/create.bo")) {
			System.out.println("생성DB이동");
			CreateBean createBean = new CreateBean(request);
			createDAO = new createDAO();
			createDAO.createAction(createBean);
			System.out.println("신규등록완료");
			dispatcher = request.getRequestDispatcher("A_completion.jsp");
			System.out.println("디스페처 링크탐");
			request.setAttribute("nfcid", createBean.getNfcid());
			request.setAttribute("name", createBean.getName());
			request.setAttribute("Commuting", "create");
			dispatcher.forward(request, response);
		}
		
		//출근처리
		if(command.equals("/attendance.bo")) {
			response.sendRedirect("attendance.jsp");
		}
		if(command.equals("/A_attendance.bo")) {
			System.out.println("출근DB이동");
			AttendBean attendBean = new AttendBean(request);
			SearchDAO searchDAO = new SearchDAO(request);
			name = searchDAO.getName();
			createDAO = new createDAO();
			int count = createDAO.attendance(attendBean);
						
			if(count == 0) {
				//알림창
				dispatcher = request.getRequestDispatcher("A_completion.jsp");
				request.setAttribute("nfcid", attendBean.getNfcid());
				request.setAttribute("Commuting", "empty");
				dispatcher.forward(request, response);
			}else if(count == 1) {
				//알림창
				dispatcher = request.getRequestDispatcher("A_completion.jsp");
				request.setAttribute("nfcid", attendBean.getNfcid());
				request.setAttribute("time", attendBean.getAttenddance());
				request.setAttribute("Commuting", "attend");
				request.setAttribute("name", name);
				dispatcher.forward(request, response);	
			}else if(count == 10000) {
				dispatcher = request.getRequestDispatcher("A_completion.jsp");
				request.setAttribute("nfcid", attendBean.getNfcid());
				request.setAttribute("time", attendBean.getAttenddance());
				request.setAttribute("Commuting", "already");
				dispatcher.forward(request, response);	
			}
			System.out.println("출근시간 등록완료");
			
		}
		
		//퇴근처리
		if(command.equals("/leave_Work.bo")) {
			response.sendRedirect("leave_Work.jsp");
		}
		if(command.equals("/A_leave_Work.bo")) {
			System.out.println("퇴근DB이동");
			AttendBean attendBean = new AttendBean(request);
			SearchDAO searchDAO = new SearchDAO(request);
			name = searchDAO.getName();
			createDAO = new createDAO();
			int count = createDAO.leave_Work(attendBean);
			
			
			
			
			if(count == 0) {
				//알림창
				dispatcher = request.getRequestDispatcher("A_completion.jsp");
				request.setAttribute("nfcid", attendBean.getNfcid());
				request.setAttribute("Commuting", "empty");
				dispatcher.forward(request, response);
			}else if(count == 1){//성공구간
				dispatcher = request.getRequestDispatcher("A_completion.jsp");
				request.setAttribute("nfcid", attendBean.getNfcid());
				request.setAttribute("time", attendBean.getAttenddance());
				request.setAttribute("Commuting", "leave");
				request.setAttribute("name", name);
				dispatcher.forward(request, response);
				//근무시간등록
				System.out.println("근무시간등록1111");
				searchDAO.WorkingTime(request,attendBean);
				String w_Time= searchDAO.getWorking();
				System.out.println("근무시간등록2222");
				int result = createDAO.WorkingTime(attendBean, w_Time);
				System.out.println(result + " 개 근무시간등록완료");
				
				
			}else if(count == 10001) {
				dispatcher = request.getRequestDispatcher("A_completion.jsp");
				request.setAttribute("nfcid", attendBean.getNfcid());
				request.setAttribute("time", attendBean.getAttenddance());
				request.setAttribute("Commuting", "already2");
				dispatcher.forward(request, response);
			}
			System.out.println("퇴근시간 등록완료");
			//알림창
			
		}
		//이름조회
		if(command.equals("/list.bo")) {
			dispatcher = request.getRequestDispatcher("search.jsp");
			listDAO listDAO = new listDAO();
			request.setAttribute("list", listDAO.selectArrayList(request.getParameter("nfcid")));
			dispatcher.forward(request, response);
			
		}
		
		
		//관리자 모드
		if(command.equals("/admin.bo")) {
			
			dispatcher = request.getRequestDispatcher("A_completion.jsp");
			request.setAttribute("Commuting", "adminQ");
			request.setAttribute("pass", "456123");
			dispatcher.forward(request, response);			
		}
		if(command.equals("/A_admin.bo")) {
			dispatcher = request.getRequestDispatcher("admin.jsp");
			request.setAttribute("Commuting", "A_adminQ");
			request.setAttribute("security", securityBean.getVar1());
			listDAO listDAO = new listDAO();
			request.setAttribute("list", listDAO.selectArrayList(request.getParameter("nfcid")));
			if(request.getParameter("nfcid") != null) {
				request.setAttribute("nfcid", request.getParameter("nfcid"));
			}else {
				request.setAttribute("nfcid", "");
			}
			dispatcher.forward(request, response);
		
		}
		//어드민 강제현재시간 퇴근처리
		if(command.equals("/Admin_leave_Work.bo")) {
			System.out.println("강제퇴근DB이동");
			AttendBean attendBean = new AttendBean(request);
			SearchDAO searchDAO = new SearchDAO(request);
			name = searchDAO.getName();
			createDAO = new createDAO();
			int count = createDAO.leave_Work(attendBean);
			
			if(count == 1){//성공구간
				dispatcher = request.getRequestDispatcher("A_admin.bo");
				request.setAttribute("nfcid", attendBean.getNfcid());
				request.setAttribute("time", attendBean.getAttenddance());
				request.setAttribute("Commuting", "admin_leave");
				request.setAttribute("name", name);
				dispatcher.forward(request, response);
				//근무시간등록
				searchDAO.WorkingTime(request,attendBean);
				String w_Time= searchDAO.getWorking();
				int result = createDAO.WorkingTime(attendBean, w_Time);
				System.out.println(result + " 개 근무시간등록완료");
				
			
			}
		}
		
		
    } //프로세스괄호
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
