package com.shopping.controller.mall;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.common.SuperClass;
import com.shopping.controller.product.ProductListController;


// 나의 카트 내역 보기에서 특정 상품 에 대한 [삭제] 링크를 클릭하였습니다.
public class MallDeleteController extends SuperClass {
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doGet(request, response);
		
		if(super.mycart.getCartItemSize()==0) {
			String message = "카트 목록이 존재하지 않아서, 상품 목록 페이지로 이동합니다.";
			super.setAlertMessage(message);
			new ProductListController().doGet(request, response);
			
		}else {
			// pnum : 삭제하고자 하는 상품의 번호
			int pnum = Integer.parseInt(request.getParameter("pnum"));
			super.mycart.deleteCart(pnum);
			super.session.setAttribute("mycart", mycart);
			new MallListController().doGet(request, response);
		}
	}
}
