package com.shopping.controller.member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.common.SuperClass;
import com.shopping.controller.product.ProductListController;
import com.shopping.model.bean.FillItem;
import com.shopping.model.bean.Member;
import com.shopping.model.dao.FillItemDao;
import com.shopping.model.dao.MemberDao;

public class MemberUpdateController extends SuperClass {
	private final String PREFIX = "member/";
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doGet(request, response);
		
		// 라디오버튼과 체크박스는 데이터베이스에서 읽어 동적으로 채웁니다.
		FillItemDao fdao = new FillItemDao();
		List<FillItem> genderList = fdao.getDataList("members", "gender");
		List<FillItem> marriageList = fdao.getDataList("members", "marriage");
		List<FillItem> hobbyList = fdao.getDataList("members", "hobby");
		
		request.setAttribute("genderList", genderList);
		request.setAttribute("marriageList", marriageList);
		request.setAttribute("hobbyList", hobbyList);
		
		// 현재 로그인 한 사람의 아이디(Primary Key)
		String id = request.getParameter("id");
		
		MemberDao dao = new MemberDao();
		Member bean = dao.getDataBean(id);
		
		request.setAttribute("bean", bean);
		
		super.goToPage(PREFIX + "meUpdateForm.jsp");
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doPost(request, response);
		
		MemberDao dao = new MemberDao();
		Member bean = new Member();
		
		bean.setId(request.getParameter("id"));
		bean.setName(request.getParameter("name"));
		bean.setPassword(request.getParameter("password"));
		bean.setGender(request.getParameter("gender"));
		bean.setBirth(request.getParameter("birth"));
		bean.setMarriage(request.getParameter("marriage"));
		bean.setAddress(request.getParameter("address"));
		// 적립 포인트는 DB의 기본 값으로 대체하면 문제 없음
		
		// for checkBox control
		String hobby = "";
		String[] hobbies = request.getParameterValues("hobby");
		if(hobbies == null){
			hobby = null; // DB에 null 값으로 채우기
		} else {
			for(int i=0; i<hobbies.length; i++){
				hobby += hobbies[i] + ", ";
			}
		}
		bean.setHobby(hobby);
		
		int cnt = dao.updateData(bean);
		
		if(cnt == 1){ // 수정 성공
			new ProductListController().doGet(request, response);
		}else{ // 수정 실패
			new MemberUpdateController().doGet(request, response);
		}
		
	}
}
