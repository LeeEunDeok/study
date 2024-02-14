package com.shopping.controller.mall;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.common.SuperClass;
import com.shopping.controller.product.ProductListController;


// 나의 카트 내역 보기에서 특정 상품에 대하여 [수정] 버튼을 클릭하였습니다.
public class MallUpdateController extends SuperClass {
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doGet(request, response);
		
		if(super.mycart.getCartItemSize()==0) {
			String message = "카트 목록이 존재하지 않아서, 상품 목록 페이지로 이동합니다.";
			super.setAlertMessage(message);
			new ProductListController().doGet(request, response);
			
		}else {
			// pnum : 삭제하고자 하는 상품의 번호
			int pnum = Integer.parseInt(request.getParameter("pnum")); // 상품 번호
			int qty = Integer.parseInt(request.getParameter("qty")); // 수정할 수량
			
			super.mycart.editCart(pnum, qty);
			super.session.setAttribute("mycart", mycart);
			new MallListController().doGet(request, response);
		}
	}
}
