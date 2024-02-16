package com.shopping.controller.mall;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.common.SuperClass;
import com.shopping.controller.product.ProductListController;
import com.shopping.model.dao.MallDao;

// 장바구니의 품목에 대하여 [결제]를 진행합니다.
public class MallCalculateController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doGet(request, response);
		
		MallDao dao = new MallDao();
		dao.calculate(super.logInfo, super.mycart.getAllCartList());
		
		// 결제가 마무리 되면 session 영역의 cart 정보만 지워주도록 합니다.
		super.session.removeAttribute("mycart");
		
		new ProductListController().doGet(request, response);
	}
}
