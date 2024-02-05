package com.shopping.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shopping.model.bean.Board;
import com.shopping.utility.Paging;

// 게시물과 관련된 Dao(Data Access Object) 클래스
public class BoardDao extends SuperDao {
	public int updateData(Board bean) {
		System.out.print("게시물 수정 페이지 : ");
		System.out.println(bean);
		
		String sql = "update boards set id = ?, password = ?, subject = ?, contents = ?, regdate = default";
		sql += " where no = ?";
		
		PreparedStatement pstmt = null;
		int cnt = -1;
		
		try {
			super.conn = super.getConnection();
			// 자동 커밋 기능을 비활성 합니다.
			// 실행이 성공적으로 완료되면 커밋 하도록 commit() 메소드를 명시합니다.
			conn.setAutoCommit(false);
			
			pstmt = conn.prepareStatement(sql);
			
			// 실행전 ? 치환하기
			pstmt.setString(1,bean.getId());
			pstmt.setString(2,bean.getPassword());
			pstmt.setString(3,bean.getSubject());
			pstmt.setString(4,bean.getContents());
			pstmt.setInt(5,bean.getNo());
			
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
	
	//해당 게시물 번호를 이용하여 1건의 정보를 반환합니다.
	public Board getDataBean(int no) {
		
		String sql = "select * from boards";
		sql += " where no = ?";
		PreparedStatement pstmt = null; // 문장 객체
		ResultSet rs = null;
		Board bean = null;
		
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
				
		return bean;
		
	}
	
	public int deleteData(int no) {
		String sql = "delete from boards where no = ?";
		
		PreparedStatement pstmt = null;
		int cnt = -1;
		
		try {
			super.conn = super.getConnection();
			// 자동 커밋 기능을 비활성 합니다.
			// 실행이 성공적으로 완료되면 커밋 하도록 commit() 메소드를 명시합니다.
			conn.setAutoCommit(false);
			
			pstmt = conn.prepareStatement(sql);
			
			// sql 문장 실행 전 치환
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
	
	public int insertData(Board bean) {
		// no 컬럼은 시퀀스가 알아서 처리합니다.
		
		String sql = "insert into boards(no, id, password, subject, contents)";
		sql += " values(seqboard.nextval, ?, ?, ?, ?)";
		
		PreparedStatement pstmt = null;
		int cnt = -1;
		
		try {
			super.conn = super.getConnection();
			// 자동 커밋 기능을 비활성 합니다.
			// 실행이 성공적으로 완료되면 커밋 하도록 commit() 메소드를 명시합니다.
			conn.setAutoCommit(false);
			
			pstmt = conn.prepareStatement(sql);
			
			// 실행전 ? 치환하기
			pstmt.setString(1, bean.getId());
			pstmt.setString(2, bean.getPassword());
			pstmt.setString(3, bean.getSubject());
			pstmt.setString(4, bean.getContents());
			
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
	
	public BoardDao() {
		super();
	}
	
	private Board resultSet2Bean(ResultSet rs) {
		try {
			Board bean = new Board();
			
			bean.setNo(rs.getInt("no"));
			bean.setId(rs.getString("id"));
			bean.setPassword(rs.getString("password"));
			bean.setSubject(rs.getString("subject"));
			bean.setContents(rs.getString("contents"));
			bean.setReadhit(rs.getInt("readhit"));
			// 날짜 형식의 컬럼
			bean.setRegdate(String.valueOf(rs.getDate("regdate")));
			bean.setRemark(rs.getString("remark"));
			bean.setGroupno(rs.getInt("groupno"));
			bean.setOrderno(rs.getInt("orderno"));
			bean.setDepth(rs.getInt("depth"));
			return bean;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public List<Board> getDataList(){
		
		String sql = "select * from boards";
		PreparedStatement pstmt = null; // 문장 객체
		ResultSet rs = null;
		
		List<Board> dataList = new ArrayList<Board>();
		
		super.conn = super.getConnection();
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			// 요소들을 읽어서 컬렉션에 담습니다.
			while(rs.next()) {
				Board bean = this.resultSet2Bean(rs);
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
	
	public List<Board> getDataList(Paging paging){
		// 페이징 처리를 이용하여 데이터를 조회합니다.
		String sql = "select no, id, password, subject, contents, readhit, regdate, remark, groupno, orderno, depth";
		sql += " from (select rank() over(order by no desc) as ranking, no, id, password, subject, contents, readhit, regdate,";
		sql += " remark, groupno, orderno, depth";
		sql += " from boards";
		
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
		
		List<Board> dataList = new ArrayList<Board>();
		
		super.conn = super.getConnection();
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, paging.getBeginRow());
			pstmt.setInt(2, paging.getEndRow());
			
			rs = pstmt.executeQuery();
			
			// 요소들을 읽어서 컬렉션에 담습니다.
			while(rs.next()) {
				Board bean = this.resultSet2Bean(rs);
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
	
	public List<Board> getDataList02(){
		
		List<Board> dataList = new ArrayList<Board>();
		
		dataList.add(new Board(10, "hong", null, "jsp", "제이에스피", 11, "2023/07/17", null, 0, 0, 0));
		dataList.add(new Board(20, "park", null, "python", "파이썬", 22, "2023/08/15", null, 0, 0, 1));
		dataList.add(new Board(30, "kim", null, "java", "나자바바라", 33, "2023/12/25", null, 0, 0, 2));
		
		return dataList;
	}

	public void updateReadhit(int no) {
		String sql = "update boards set readhit = readhit + 1 where no = ?";
		PreparedStatement pstmt = null;
		int cnt = -1;
		
		try {
			super.conn = super.getConnection();
			// 자동 커밋 기능을 비활성 합니다.
			// 실행이 성공적으로 완료되면 커밋 하도록 commit() 메소드를 명시합니다.
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
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
	}

}
