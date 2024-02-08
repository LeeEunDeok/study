package com.shopping.controller.comment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.common.SuperClass;
import com.shopping.controller.board.BoardDetailController;
import com.shopping.model.bean.Comment;
import com.shopping.model.dao.CommentDao;

public class CommentInsertController extends SuperClass {
	@Override // 부모 글번호에 대하여 로그인 한 사람이 댓글을 작성하는 로직입니다.
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doPost(request, response);
		
		Comment bean = new Comment();
		// 댓글 번호는 시퀀스를 사용하여 자동으로 넣기
		// 생성 일자는 DB 기본값 또는 sysdate를 사용하면 됩니다.
		
		bean.setNo(Integer.parseInt(request.getParameter("no"))); // 게시물 부모 글번호
		bean.setContents(request.getParameter("contents")); // 내가 작성한 댓글의 내용
		bean.setId(request.getParameter("id")); // 나의 아이디
		
		CommentDao dao = new CommentDao();
		int cnt = dao.insertData(bean);
		new BoardDetailController().doGet(request, response);
		
	}
}
