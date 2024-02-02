package com.shopping.controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.common.SuperClass;
import com.shopping.model.bean.Board;
import com.shopping.model.dao.BoardDao;

public class TemplateController extends SuperClass {
	private final String PREFIX = "board/";
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doGet(request, response);
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doPost(request, response);
		
		BoardDao dao = new BoardDao();
		Board bean = new Board();
		
		String id = request.getParameter("id");
		int no = Integer.parseInt(request.getParameter("no"));
		
		super.goToPage(PREFIX + "boInsertForm.jsp");
	}
	
}
