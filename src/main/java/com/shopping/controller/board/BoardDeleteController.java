package com.shopping.controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.common.SuperClass;
import com.shopping.model.dao.BoardDao;

public class BoardDeleteController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doGet(request, response);
		
		int no = Integer.parseInt(request.getParameter("no"));
		BoardDao dao = new BoardDao();
		int cnt = -1;
		cnt = dao.deleteData(no);
		
		new BoardListController().doGet(request, response);
	}
}
