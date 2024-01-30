package com.shopping.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shopping.model.bean.Member;

public class MemberDao extends SuperDao{
	
	public int updateData(Member bean) {
		
		String sql = "update members set name = ?, password = ?, gender = ?, birth = ?, marriage = ?, hobby = ?,"
				+ " address = ?, mpoint = ?";
		sql += " where id = ?";
		
		PreparedStatement pstmt = null;
		int cnt = -1;
		
		try {
			super.conn = super.getConnection();
			// 자동 커밋 기능을 비활성 합니다.
			// 실행이 성공적으로 완료되면 커밋 하도록 commit() 메소드를 명시합니다.
			conn.setAutoCommit(false);
			
			pstmt = conn.prepareStatement(sql);
			
			// 실행전 ? 치환하기
			pstmt.setString(1,bean.getName());
			pstmt.setString(2,bean.getPassword());
			pstmt.setString(3,bean.getGender());
			pstmt.setString(4,bean.getBirth());
			pstmt.setString(5,bean.getMarriage());
			pstmt.setString(6,bean.getHobby());
			pstmt.setString(7,bean.getAddress());
			pstmt.setInt(8,bean.getMpoint());
			pstmt.setString(9,bean.getId());
			
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
	
	//해당 아이디를 이용하여 1건의 정보를 반환합니다.
	public Member getDataBean(String id) {
			
			String sql = "select * from members";
			sql += " where id = ?";
			PreparedStatement pstmt = null; // 문장 객체
			ResultSet rs = null;
			Member bean = null;
			
			super.conn = super.getConnection();
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
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
					
			return bean;
			
		}
	
	public int insertData(Member bean) {
		System.out.println(bean);
		String sql = "insert into members(id, name, password, gender, birth, marriage, hobby, address, mpoint)";
		sql += " values(?, ?, ?, ?, ?, ?, ?, ?, default)";
		PreparedStatement pstmt = null;
		
		int cnt = -1;
		
		try {
			super.conn = super.getConnection();
			conn.setAutoCommit(false);
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, bean.getId());
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getPassword());
			pstmt.setString(4, bean.getGender());
			pstmt.setString(5, bean.getBirth());
			pstmt.setString(6, bean.getMarriage());
			pstmt.setString(7, bean.getHobby());
			pstmt.setString(8, bean.getAddress());
			
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
	
	private Member resultSet2Bean(ResultSet rs) {
		
		try {
			Member bean = new Member();
			
			bean.setAddress(rs.getString("address"));
			bean.setBirth(String.valueOf(rs.getDate("birth")));
			bean.setGender(rs.getString("gender"));
			bean.setHobby(rs.getString("hobby"));
			bean.setId(rs.getString("id"));
			bean.setMarriage(rs.getString("marriage"));
			bean.setMpoint(rs.getInt("mpoint"));
			bean.setName(rs.getString("name"));
			bean.setPassword(rs.getString("password"));
			
			return bean;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public List<Member> getDataList(){
		
		String sql = "select * from members";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<Member> dataList = new ArrayList<Member>();
		
		super.conn = super.getConnection();
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Member bean = this.resultSet2Bean(rs);
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
	
	
	
	public List<Member> getDataList02(){
		List<Member> dataList = new ArrayList<Member>();
		
		dataList.add(new Member("kim", "김앙드레", null, "남", null, null, "디자인", null, 1134));
		dataList.add(new Member("seong", "성시경", null, "남", null, null, "노래", null, 537));
		dataList.add(new Member("karina", "유지민", null, "여", null, null, "노래, 춤", null, 7777));
		dataList.add(new Member("park", "박지성", null, "남", null, null, "축구", null, 2314));
		
		return dataList;
	}
}
