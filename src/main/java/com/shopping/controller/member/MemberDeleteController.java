package com.shopping.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.common.SuperClass;
import com.shopping.model.dao.MemberDao;

public class MemberDeleteController extends SuperClass {
	@Override // 특정 회원이 탈퇴할 때 사용하는 컨트롤러 입니다.
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doGet(request, response);
		
		String id = request.getParameter("id");
		MemberDao dao = new MemberDao();
		int cnt = -1;
		cnt = dao.deleteData(id);
		
		// 세션 공간에 들어있는 모든 정보를 무효화시킵니다.
		super.session.invalidate();
		
		new MemberInsertController().doGet(request, response);
	}
}
