package com.shopping.common;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shopping.model.bean.World;

/**
 * Servlet implementation class WorldServlet
 */
@WebServlet(
		urlPatterns = { "/world" }, 
		initParams = { 
				@WebInitParam(name = "tel", value = "0212345678"), 
				@WebInitParam(name = "fax", value = "0211112222")
		})
public class WorldServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String tel;
    private String fax;
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WorldServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		this.tel = config.getInitParameter("tel");
		this.fax = config.getInitParameter("fax");
		
		ServletContext application = config.getServletContext();
		String world = "온난화로 인하여 전세계가 더워요.";
		application.setAttribute("world", world);
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String method = request.getMethod();
		if(method.equalsIgnoreCase("get")) {
			doGet(request, response);
		} else {
			doPost(request, response);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost 동작");
		Integer pnum = Integer.parseInt(request.getParameter("pnum"));
		String name = request.getParameter("name");
		String company = request.getParameter("company");
		Integer price = Integer.parseInt(request.getParameter("price"));
		
		// 바인딩(binding) : scope 에 attribute 를 추가하는 동작
		request.setAttribute("pnum", pnum);
		request.setAttribute("name", name);
		request.setAttribute("company", company);
		request.setAttribute("price", price);
		
		// bean 객체를 이용한 바인딩(주로 이 방식 사용)
		World bean = new World();
		bean.setCompany(company);
		bean.setName(name);
		bean.setPnum(pnum);
		bean.setPrice(price);
		request.setAttribute("aaa", bean);
		
		// session 영역 바인딩
		HttpSession session = request.getSession();
		session.setAttribute("tel", tel);
		session.setAttribute("fax", fax);
		
		String goToPage = "exercise/servletTo02.jsp"; //이동할 페이지
		// 포워딩 방식
		RequestDispatcher dispatcher = request.getRequestDispatcher(goToPage);
		dispatcher.forward(request, response);
		
	}

}
