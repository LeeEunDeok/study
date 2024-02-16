package com.shopping.controller.member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.common.SuperClass;
import com.shopping.controller.product.ProductListController;
import com.shopping.model.bean.Member;
import com.shopping.model.bean.WishList;
import com.shopping.model.dao.MallDao;
import com.shopping.model.dao.MemberDao;

public class MemberLoginController extends SuperClass {
	private final String PREFIX = "member/";

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("로그인 페이지로 이동합니다.");
		super.doGet(request, response);
		super.goToPage(PREFIX + "meLoginForm.jsp");
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doPost(request, response);
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		System.out.println(id + "/" + password);

		MemberDao dao = new MemberDao();
		Member bean = dao.getDataByIdAndPassword(id, password);

		if (bean == null) {
			String message = "로그인 정보가 잘못되었습니다.";
			super.setAlertMessage(message);
			super.goToPage(PREFIX + "meLoginForm.jsp");
		} else { // 로그인 성공
			// session 영역(scope)에 나의 로그인 정보를 저장(바인딩)합니다.
			// logInfo 속성을 사용하여 현재 로그인 상태를 확인할 수 있습니다.
			super.session.setAttribute("logInfo", bean);
			
			// 나의 찜 목록(WishList)을 테이블에서 읽어 session 영역에 바인딩 합니다.
			MallDao mdao = new MallDao();
			List<WishList> wishList = mdao.getWishList(bean.getId());
			for(WishList item : wishList) {
				super.mycart.addCart(item.getPnum(), item.getQty());
			}
			super.session.setAttribute("mycart", mycart);
			
			// 로그인 성공 이후 상품 목록 페이지로 이동합니다.
			new ProductListController().doGet(request, response);
		}
	}

}
