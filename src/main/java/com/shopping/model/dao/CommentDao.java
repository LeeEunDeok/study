package com.shopping.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shopping.model.bean.Comment;

public class CommentDao extends SuperDao {
	public int deleteData(int cnum) {
		String sql = "delete from comments where cnum = ?";
		
		PreparedStatement pstmt = null;
		int cnt = -1;
		
		try {
			super.conn = super.getConnection();
			// 자동 커밋 기능을 비활성 합니다.
			// 실행이 성공적으로 완료되면 커밋 하도록 commit() 메소드를 명시합니다.
			conn.setAutoCommit(false);
			
			pstmt = conn.prepareStatement(sql);
			
			// sql 문장 실행 전 치환
			pstmt.setInt(1, cnum);
			
			cnt = pstmt.executeUpdate();
			conn.commit();
			
		}catch (Exception e) {
			e.printStackTrace();
			
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				
			}
		}finally {
			try {
				if(pstmt != null) {pstmt.close();}
				super.closeConnection();
				
			}catch(Exception e2) {
				e2.printStackTrace();
				
			}
		}
		return cnt;
	}
	
	public List<Comment> getDataList(int no) {
		String sql = "select * from comments";
		sql += " where no = ?";
		sql += " order by cnum asc";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<Comment> dataList = new ArrayList<Comment>();
		
		super.conn = super.getConnection();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Comment bean = this.resultSet2Bean(rs);
				dataList.add(bean);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			try {
				if(rs != null) {rs.close();}
				if(pstmt != null) {pstmt.close();}
				super.closeConnection();
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return dataList;
	}

	private Comment resultSet2Bean(ResultSet rs) {
		Comment bean = new Comment();
		try {
			bean.setCnum(rs.getInt("cnum"));
			bean.setContents(rs.getString("contents"));
			bean.setId(rs.getString("id"));
			bean.setNo(rs.getInt("no"));
			bean.setRegdate(String.valueOf(rs.getDate("regdate")));
			
			return bean;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

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
