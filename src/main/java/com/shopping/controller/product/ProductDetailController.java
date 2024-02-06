package com.shopping.controller.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.common.SuperClass;
import com.shopping.model.bean.Product;
import com.shopping.model.dao.ProductDao;

public class ProductDetailController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doGet(request, response);
		
		ProductDao dao = new ProductDao();
		
		int pnum = Integer.parseInt(request.getParameter("pnum"));
		
		Product bean = dao.getDataBean(pnum);
		
		request.setAttribute("bean", bean);
		
		super.goToPage("product/prDetail.jsp");
	}
}
