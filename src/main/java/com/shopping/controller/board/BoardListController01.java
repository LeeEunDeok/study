package com.shopping.controller.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.common.SuperClass;
import com.shopping.model.bean.Board;
import com.shopping.model.dao.BoardDao;

public class BoardListController01 extends SuperClass {
	private final String PREFIX = "board/";
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doGet(request, response);
		
		BoardDao dao = new BoardDao();
		List<Board> dataList = dao.getDataList();
		
		request.setAttribute("dataList", dataList);
		super.goToPage(PREFIX + "boList.jsp");
	}
}
