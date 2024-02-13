package com.shopping.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.common.SuperClass;
import com.shopping.model.bean.Member;
import com.shopping.model.dao.MemberDao;

public class MemberIdCheckController extends SuperClass {
	@Override // id 중복 체크를 하는 로직입니다.
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doGet(request, response);
		
		String id = request.getParameter("id");
		MemberDao dao = new MemberDao();
		Member bean = dao.getDataBean(id);
		
		String message = ""; // 사용자에게 보여줄 메시지
		
		if(bean == null) {// 존재하지 않는 회원
			request.setAttribute("isRegister", true); // 가입 가능
			message = "<font color='blue'><b>사용 가능</b></font>한 아이디 입니다.";
			
		}else {// 존재하는 회원
			request.setAttribute("isRegister", false);
			
			if(bean.getId().equals("admin")) { // 관리자
				message = "<font color='red'><b>사용할 수 없는</b></font> 아이디 입니다.";
				request.setAttribute("plus_message", "<font color='blue'><b>관리자</b></font>를 위한 아이디입니다.");
				
			}else { // 일반 회원
				message = "<font color='red'><b>이미 사용중인</b></font> 아이디입니다.";
			}
		}
		
		request.setAttribute("message", message);
		super.goToPage("member/idCheck.jsp");
	}
}
