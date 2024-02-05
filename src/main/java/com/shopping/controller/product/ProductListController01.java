package com.shopping.controller.product;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.common.SuperClass;
import com.shopping.model.bean.Product;
import com.shopping.model.dao.ProductDao;

public class ProductListController01 extends SuperClass {
	private final String PREFIX = "product/";
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doGet(request, response);
		System.out.println("상품 목록 페이지로 이동 합니다.");
		
		ProductDao dao = new ProductDao();
		int beginRow = 1;
		int endRow = 6;
		List<Product> dataList = dao.getDataList(beginRow, endRow);
		
		request.setAttribute("dataList", dataList); // 상품 목록을 request 영역에 바인딩
		
		super.goToPage(PREFIX + "prList.jsp");
	}
}
