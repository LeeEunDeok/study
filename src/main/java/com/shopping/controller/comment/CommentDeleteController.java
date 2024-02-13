package com.shopping.controller.comment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.common.SuperClass;
import com.shopping.controller.board.BoardDetailController;
import com.shopping.model.dao.CommentDao;

public class CommentDeleteController extends SuperClass {
	@Override // 댓글 번호를 이용하여 댓글을 삭제합니다.
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doGet(request, response);
		
		int cnum = Integer.parseInt(request.getParameter("cnum"));
		CommentDao dao = new CommentDao();
		int cnt = -1;
		
		cnt = dao.deleteData(cnum);
		
		new BoardDetailController().doGet(request, response);
	}
}
