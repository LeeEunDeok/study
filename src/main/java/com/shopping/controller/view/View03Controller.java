package com.shopping.controller.view;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.common.SuperClass;
import com.shopping.model.bean.Combo03;
import com.shopping.model.dao.CompositeDao;

public class View03Controller extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doGet(request, response);
		
		CompositeDao dao = new CompositeDao();
		List<Combo03> dataList = dao.view03();
		
		request.setAttribute("dataList", dataList);
		super.goToPage("view/View03.jsp");
	}
}
