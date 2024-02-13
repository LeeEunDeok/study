package com.shopping.controller.comment;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.shopping.common.SuperClass;
import com.shopping.model.bean.Comment;
import com.shopping.model.dao.CommentDao;

/*
	json-simple-1.1.1.jar는 JSON 데이터를 파싱하고 생성하는 데 사용되는 간단한 JSON 처리 라이브러리입니다.
	 이 라이브러리는 JSON 데이터를 Java의 기본 데이터 구조로 변환하거나, 
	 Java의 데이터를 JSON 형식으로 변환하는 데 사용됩니다. 
	 주로 웹 애플리케이션에서 JSON 데이터를 처리하거나 외부 API와 통신할 때 사용됩니다.
*/
public class CommentListController extends SuperClass {
	// 특정 게시물에 대하여 작성한 댓글 목록을 읽어 옵니다.
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doGet(request, response);

		int no = Integer.parseInt(request.getParameter("no"));

		CommentDao dao = new CommentDao();

		List<Comment> comments = null;

		comments = dao.getDataList(no);
		System.out.println(no + "번 글에 작성된 댓글의 개수 : " + comments.size());
		
		// Json 객체들을 저장할 배열 객체
		JSONArray jsArr = new JSONArray(); 
		
		for(Comment comm : comments) {
			JSONObject obj = new JSONObject(); // Json 객체 1개
			obj.put("no", comm.getNo());
			obj.put("cnum", comm.getCnum());
			obj.put("id", comm.getId());
			obj.put("contents", comm.getContents());
			obj.put("regdate", comm.getRegdate());
			
			jsArr.add(obj);
		}
		
		System.out.println("jsArr.toString() 결과 보기");
		System.out.println(jsArr.toString());
		
		request.setAttribute("jsArr", jsArr);
		
		super.goToPage("comment/cmList.jsp");
		
		

	}
}
