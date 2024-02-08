package com.shopping.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.shopping.model.bean.Emoticon;

public class EmoticonDao extends SuperDao {
	public int updateEmoticon(int no, String columnName) {
		// 해당 게시물 번호 no의 컬럼 columnName 의 값을 1 증가 시킵니다.
		String sql = "update emoticons set " + columnName + " = " + columnName + " + 1";
		sql += " where no = ?";
		
		System.out.println("updateEmoticon 메소드 sql 구문");
		System.out.println(sql);
		
		PreparedStatement pstmt = null;
		int cnt = -1;
		
		try {
			super.conn = super.getConnection();
			// 자동 커밋 기능을 비활성 합니다.
			// 실행이 성공적으로 완료되면 커밋 하도록 commit() 메소드를 명시합니다.
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			
			// 실행전 ? 치환하기
			pstmt.setInt(1, no);
			
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

	public Emoticon getEmoticon(int no) {
		// 해당 게시물(no)에 대한 이모티콘 정보를 반환합니다.
		String sql = "select * from emoticons";
		sql += " where no = ?";
		
		PreparedStatement pstmt = null; // 문장 객체
		ResultSet rs = null;
		Emoticon bean = null;
		
		super.conn = super.getConnection();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			// 요소를 읽어서 bean에 담습니다.
			if(rs.next()) {
				bean = this.resultSet2Bean(rs);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			try {
				if(rs != null) {rs.close();}
				if(pstmt != null) {pstmt.close();}
				super.closeConnection();
				
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		System.out.println(bean);
		return bean;
		
	}

	private Emoticon resultSet2Bean(ResultSet rs) {
		try {
			Emoticon bean = new Emoticon();
			bean.setAngry(rs.getInt("angry"));
			bean.setHates(rs.getInt("hates"));
			bean.setLikes(rs.getInt("likes"));
			bean.setLove(rs.getInt("love"));
			bean.setNo(rs.getInt("no"));
			bean.setSad(rs.getInt("sad"));
			
			return bean;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
