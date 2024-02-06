package com.shopping.controller.product;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.shopping.common.SuperClass;
import com.shopping.model.bean.FillItem;
import com.shopping.model.bean.Product;
import com.shopping.model.dao.FillItemDao;
import com.shopping.model.dao.ProductDao;

public class ProductInsertController extends SuperClass {
	private final String PREFIX = "product/";

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doGet(request, response);

		FillItemDao dao = new FillItemDao();
		List<FillItem> categories = null;
		categories = dao.getDataList("products", "category");

		request.setAttribute("categories", categories);

		super.goToPage(PREFIX + "prInsertForm.jsp");
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doPost(request, response);
		
		// 업로드와 관련된 컨트롤러에서는 request 스코프의 mr객체를 읽어옵니다.
		MultipartRequest mr = (MultipartRequest)request.getAttribute("mr");
		
		System.out.println("mr = " + mr);
		
		Product bean = new Product();
		
		bean.setImage01(mr.getFilesystemName("image01"));
		bean.setImage02(mr.getFilesystemName("image02"));
		bean.setImage03(mr.getFilesystemName("image03"));
		bean.setName(mr.getParameter("name"));
		bean.setCategory(mr.getParameter("category"));
		bean.setCompany(mr.getParameter("company"));
		bean.setContents(mr.getParameter("contents"));
		bean.setInputDate(mr.getParameter("inputdate"));
		
		// 상품 번호는 시퀀스로 자동처리합니다.
		// bean.setPnum(Integer.parseInt(mr.getParameter("pnum")));
		bean.setStock(Integer.parseInt(mr.getParameter("stock")));
		bean.setPoint(Integer.parseInt(mr.getParameter("point")));
		bean.setPrice(Integer.parseInt(mr.getParameter("price")));
		
		
		
		ProductDao dao = new ProductDao();
		int cnt = -1;
		cnt = dao.insertData(bean);
		if(cnt == -1) {
			super.goToPage(PREFIX+"prInsertForm.jsp");
		}else {
			new ProductListController().doGet(request, response);
		}
	}
}