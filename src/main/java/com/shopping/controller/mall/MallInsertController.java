package com.shopping.controller.mall;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.common.SuperClass;
import com.shopping.controller.product.ProductListController;

// 상품 상세 페이지에서 [장바구니] 버튼을 클릭하였습니다.
public class MallInsertController extends SuperClass {
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doPost(request, response);
		
		if(super.logInfo == null) {
			super.youNeededLogin();
			return;
		}
		
		int stock = Integer.parseInt(request.getParameter("stock")); // 재고 수량
		int qty = Integer.parseInt(request.getParameter("qty")); // 구매할 수량
		
		if(stock < qty) {
			String message = "재고가 부족합니다.";
			super.setAlertMessage(message);
			new ProductListController().doGet(request, response);
			
		} else {
			// 나의 카트에 상품 번호와 구매 수량을 저장하고, 세션에 바인딩한 다음, 장바구니 목록 페이지로 이동합니다.
			int pnum = Integer.parseInt(request.getParameter("pnum")); // 상품 번호
			super.mycart.addCart(pnum, qty);
			super.session.setAttribute("mycart", mycart);
			new MallListController().doGet(request, response);
		}
	}
}
