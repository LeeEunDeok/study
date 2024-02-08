package com.shopping.controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.common.SuperClass;
import com.shopping.model.dao.EmoticonDao;

public class BoardEmoticonController extends SuperClass {
	@Override // 특정 게시물의 특정 이모티콘에 대한 클릭 수를 1 증가 시킵니다.
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doGet(request, response);
		
		int no = Integer.parseInt(request.getParameter("no")); // 상세 보기를 진행중인 현재 글의 글번호
		String emoticon = request.getParameter("emoticon"); // 좋아요, 싫어요 등의 mode 값
		
		EmoticonDao edao = new EmoticonDao();
		int cnt = edao.updateEmoticon(no, emoticon);
		
		new BoardDetailController().doGet(request, response);
	}
}
