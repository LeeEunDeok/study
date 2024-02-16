package com.shopping.controller.member;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.common.SuperClass;
import com.shopping.model.dao.MallDao;

public class MemberLogoutController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doGet(request, response);
		
		if(super.logInfo != null) { // 로그인 되어 있는 경우
			// session 영역에 장바구니 정보가 존재하면 임시 테이블 WishList에 저장합니다.
			Map<Integer, Integer> wishList = super.mycart.getAllCartList();
			
			MallDao dao = new MallDao();
			dao.insertWishList(super.logInfo.getId(), wishList);
			
			// 로그인 시 바인딩했던 로그인 정보를 깨끗이 지웁니다.
			super.session.invalidate(); // session 데이터들을 무효화
			super.goToPage("member/meLoginForm.jsp"); // 로그인 페이지로 다시 이동
			
		} else { // 미로그인 상태
			super.youNeededLogin();
			
			return;
		}
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doPost(request, response);
	}
}
