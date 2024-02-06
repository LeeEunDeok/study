package com.shopping.controller.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.common.SuperClass;
import com.shopping.model.dao.ProductDao;

public class ProductDeleteController extends SuperClass {
	@Override // 넘겨진 상품 번호를 이용하여 DB에서 삭제합니다.
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doGet(request, response);
		
		int pnum = Integer.parseInt(request.getParameter("pnum"));
		ProductDao dao = new ProductDao();
		int cnt = -1;
		
		cnt = dao.deleteData(pnum);
		
		new ProductListController().doGet(request, response);
		
	}
}
