package com.shopping.common;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.shopping.utility.MyUtility;

@WebServlet(urlPatterns = {"/Shopping", "/xxx"},
			initParams = {
					@WebInitParam(name = "todolist", value = "/WEB-INF/todolist.txt")
			}
		)
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String todolist = null; // 할 일을 명시해둔 장부 파일
	private Map<String, SuperController> todolistMap = null;
	private String imageUploadWebPath = null; // 실제 이미지가 업로드 되는 웹서버 상의 전체 경로
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		ServletContext application = config.getServletContext();
		
		this.todolist = config.getInitParameter("todolist");
		
		// 애플리케이션의 전체 경로를 매개 변수로 넘겨 줍니다.
		this.todolistMap = MyUtility.getTodolistMap(application.getRealPath(todolist));
		System.out.println("todolist Map Size : " + todolistMap.size());
		System.out.println(todolistMap.toString());
		
		String imsiPath = "image";
		this.imageUploadWebPath = application.getRealPath(imsiPath);
		System.out.println("이미지 업로드 경로 : \n" + imageUploadWebPath);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 한글 깨짐 방지
		request.setCharacterEncoding("UTF-8");
		
		// command Parameter : 컨트롤러 분기를 위한 핵심 파라미터
		String command = request.getParameter("command");
		
		// 파일은 업로드 시 request 내장 객체에서 직접 파라미터를 챙길 수 없습니다.
		// 이유는 폼 태그의 enctype="multipart/form-data" 속성 때문 입니다.
		// 이미지 관련 항목들을 String 이 아닌 객체 형태로 다루어야 하기 때문 입니다.
		// in prInsertForm.jsp
		if(command == null) {
			System.out.println("file upload event invoked");
			
			MultipartRequest mr = MyUtility.getMultipartRequest(request, imageUploadWebPath);
			// 분기 처리
			if(mr != null) {
				// 파일 업로드인 경우에는 mr 객체를 이용해야만 파라미터 정보를 읽어 올 수 있습니다.
				command = mr.getParameter("command");
				
				if(command.equals("prUpdate")) {
					// 상품 정보 수정 시 과거 이미지는 삭제해 주어야 합니다.
					MyUtility.deleteOldImageFile(imageUploadWebPath, mr);
				}
				
				// file upload object binding in request scope
				request.setAttribute("mr", mr);
				
			}else {
				System.out.println("MultipartRequest object is null");
			}
		}
		
		System.out.println("command is [" + command + "]");
		
		// controller 는 해당 command 에 상응하는 하위 컨트롤러 객체 입니다.
		SuperController controller = this.todolistMap.get(command);
		
		if(controller != null) {
			try {
				String method = request.getMethod().toLowerCase();
				if(method.equals("get")) {
					controller.doGet(request, response);
					System.out.println(controller.getClass() + " get 메소드 호출됨");
					
				}else {
					controller.doPost(request, response);
					System.out.println(controller.getClass() + " post 메소드 호출됨");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else {
			System.out.println("request command is not found");
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doProcess(request, response);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doProcess(request, response);
	}
}
