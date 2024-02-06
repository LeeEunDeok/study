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

public class ProductUpdateController extends SuperClass {
	private final String PREFIX = "product/";
	
	@Override // 상품 목록 페이지에서 관리자가 [수정] 버튼을 눌렀습니다.
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doGet(request, response);

		FillItemDao fdao = new FillItemDao();
		List<FillItem> categories = null;
		categories = fdao.getDataList("products", "category");

		
		// 수정하고자 하는 상품 번호를 우선 챙깁니다.
		int pnum = Integer.parseInt(request.getParameter("pnum"));
		
		ProductDao pdao = new ProductDao();
		
		// bean : 이전에 관리자가 등록했던 상품 1개의 정보
		Product bean = pdao.getDataBean(pnum);
		
		request.setAttribute("categories", categories);
		request.setAttribute("bean", bean);
		
		super.goToPage(PREFIX + "prUpdateForm.jsp");
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doPost(request, response);
		
		// 업로드와 관련된 컨트롤러에서는 request 스코프의 mr객체를 읽어옵니다.
		MultipartRequest mr = (MultipartRequest)request.getAttribute("mr");
		
		System.out.println("mr = " + mr);
		
		Product bean = new Product();
		// 상품 등록때와는 다르게 상품 번호를 파라미터로 챙겨야 합니다.
		bean.setPnum(Integer.parseInt(mr.getParameter("pnum")));
		
		bean.setImage01(mr.getFilesystemName("image01"));
		bean.setImage02(mr.getFilesystemName("image02"));
		bean.setImage03(mr.getFilesystemName("image03"));
		bean.setName(mr.getParameter("name"));
		bean.setCategory(mr.getParameter("category"));
		bean.setCompany(mr.getParameter("company"));
		bean.setContents(mr.getParameter("contents"));
		bean.setInputDate(mr.getParameter("inputdate"));
		bean.setStock(Integer.parseInt(mr.getParameter("stock")));
		bean.setPoint(Integer.parseInt(mr.getParameter("point")));
		bean.setPrice(Integer.parseInt(mr.getParameter("price")));
		
		
		
		ProductDao dao = new ProductDao();
		int cnt = -1;
		
		cnt = dao.updateData(bean);
		
		if(cnt == -1) {
			super.goToPage(PREFIX+"prUpdateForm.jsp");
		}else {
			new ProductListController().doGet(request, response);
		}
	}
}