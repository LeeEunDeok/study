package com.shopping.controller.member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.common.SuperClass;
import com.shopping.model.bean.FillItem;
import com.shopping.model.bean.Member;
import com.shopping.model.dao.FillItemDao;
import com.shopping.model.dao.MemberDao;

public class MemberDetailController extends SuperClass {
	private final String PREFIX = "member/";
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doGet(request, response);
		
		MemberDao dao = new MemberDao();
		String id = request.getParameter("id");
		Member bean = dao.getDataBean(id);
		
		if (bean == null) {
			super.setAlertMessage("잘못된 회원 정보 입니다.");
			super.goToPage(PREFIX + "meLoginForm.jsp");
			
		} else {
			// 영문으로 되어 있는 취미를 한글로 바꾸기 위해 필요합니다.
			FillItemDao fdao = new FillItemDao();
			String module = "members";
			String field = "hobby";
			List<FillItem> hobbyList = fdao.getDataList(module, field);
			
			request.setAttribute("hobbyList", hobbyList);
			
			request.setAttribute("bean", bean);
			super.goToPage(PREFIX + "meDetail.jsp");
		}
	}
}
