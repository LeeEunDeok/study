package com.shopping.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shopping.model.bean.Board;
import com.shopping.model.bean.Member;
import com.shopping.utility.MyUtility;
import com.shopping.utility.Paging;

public class MemberDao extends SuperDao{
	public List<Member> getDataList(Paging paging) {
		String sql = "select id, name, password, gender, birth, marriage, hobby, address, mpoint";
		sql += " from (select rank() over(order by name asc) as ranking, id, name, password, gender,";
		sql += " birth, marriage, hobby, address, mpoint";
		sql += " from members";
		
		// 필드 검색을 위해 mode 변수로 분기 처리 하도록 합니다.
		String mode = paging.getMode();
		String keyword = paging.getKeyword();
		
		if(mode==null || mode.equals("all") || mode.equals("null") || mode.equals("")) {
			
		}else { // 전체모드가 아니면 
			sql += " where " + mode + " like '%" + keyword + "%'";
		}
		
		sql += " )";
		sql += " where ranking between ? and ? ";
		
		System.out.println("sql 구문 :\n" + sql);
		
		
		PreparedStatement pstmt = null; // 문장 객체
		ResultSet rs = null;
		
		List<Member> dataList = new ArrayList<Member>();
		
		super.conn = super.getConnection();
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, paging.getBeginRow());
			pstmt.setInt(2, paging.getEndRow());
			
			rs = pstmt.executeQuery();
			
			// 요소들을 읽어서 컬렉션에 담습니다.
			while(rs.next()) {
				Member bean = this.resultSet2Bean(rs);
				dataList.add(bean);
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
				
		return dataList;
	}
	
	public int deleteData(String id) {
		int cnt = -1;
		String sql = "";
		Member bean = this.getDataBean(id);
		String remark = MyUtility.getCurrentTime() + bean.getName() + "(아이디 : " + id + ")님이 탈퇴하셨습니다.";
		
		PreparedStatement pstmt = null;
		conn = super.getConnection();
		
		try {
			conn.setAutoCommit(false);
			
			// step01 : 게시물 테이블의 remark 컬럼을 업데이트 합니다.
			sql = "update boards set remark = ? where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, remark);
			pstmt.setString(2, id);
			cnt = pstmt.executeUpdate();
			if(pstmt != null) {pstmt.close();}
			
			// step02 : 주문 테이블의 remark 컬럼을 업데이트 합니다.
			sql = "update orders set remark = ? where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, remark);
			pstmt.setString(2, id);
			cnt = pstmt.executeUpdate();
			if(pstmt != null) {pstmt.close();}
			
			// step03 : 회원 테이블 데이터를 삭제합니다.
			sql = "delete from members where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			cnt = pstmt.executeUpdate();
			if(pstmt != null) {pstmt.close();}
			
			conn.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				super.closeConnection();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return cnt;
	}
	
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
		System.out.print("id로 조회 결과 : ");
		System.out.println(bean);
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
	
	public Member getDataByIdAndPasswrod(String id, String password) {
		// 아이디와 비밀번호를 이용하여 해당 회원이 존재하는지 확인합니다.
		String sql = "select * from members";
		sql += " where id = ? and password = ?";
		
		PreparedStatement pstmt = null; // 문장 객체
		ResultSet rs = null;
		Member bean = null;
		
		super.conn = super.getConnection();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
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
