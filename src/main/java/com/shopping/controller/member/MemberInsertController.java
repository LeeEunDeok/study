package com.shopping.controller.member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.common.SuperClass;
import com.shopping.model.bean.FillItem;
import com.shopping.model.bean.Member;
import com.shopping.model.dao.FillItemDao;
import com.shopping.model.dao.MemberDao;

public class MemberInsertController extends SuperClass {
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
		
		super.goToPage(PREFIX + "meInsertForm.jsp");
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doPost(request, response);
		
		MemberDao dao = new MemberDao();
		Member bean = new Member();
		
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");
		String birth = request.getParameter("birth");
		String marriage = request.getParameter("marriage");
		String address = request.getParameter("address");
		
		bean.setId(id);
		bean.setName(name);
		bean.setPassword(password);
		bean.setGender(gender);
		bean.setBirth(birth);
		bean.setMarriage(marriage);
		bean.setAddress(address);
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
		
		int cnt = dao.insertData(bean);
		
		if(cnt == 1){ // 인서트 성공
			// 로그인 컨트롤러의 doPost() 메소드를 호출하면 가입과 동시에 로그인하는 효과를 봅니다.
			new MemberLoginController().doPost(request, response);
		}else{ // 인서트 실패
			new MemberInsertController().doGet(request, response);
		}
		
	}
}
