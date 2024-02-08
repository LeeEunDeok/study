package com.shopping.model.dao;

import java.sql.PreparedStatement;

import com.shopping.model.bean.Comment;

public class CommentDao extends SuperDao {

	public int insertData(Comment bean) {
		// 넘겨진 댓글 bean 정보를 이용하여 테이블에 인서트 합니다.
		System.out.println("추가할 댓글 정보");
		System.out.println(bean);
		
		String sql = "insert into comments(cnum, no, id, contents, regdate)";
		sql += " values(seqcomment.nextval, ?, ?, ?, sysdate)";
		
		PreparedStatement pstmt = null;
		int cnt = -1;
		
		try {
			super.conn = super.getConnection();
			conn.setAutoCommit(false);
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, bean.getNo());
			pstmt.setString(2, bean.getId());
			pstmt.setString(3, bean.getContents());
			
			cnt = pstmt.executeUpdate();
			conn.commit();
			
		} catch(Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			}catch(Exception e2) {
				e2.printStackTrace();
				
			}
		}finally {
			try {
				if(pstmt != null) {pstmt.close();}
				super.closeConnection();
				
			}catch(Exception e3) {
				e3.printStackTrace();
				
			}
		}
		return cnt;
	}
	
}
