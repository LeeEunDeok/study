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
	public int replyData(Board bean, Integer orderno) {
		System.out.println("답글 달기");
		System.out.println(bean);
		
		String sql = "";
		PreparedStatement pstmt = null;
		int cnt = -1;
		
		super.conn = super.getConnection();
		
		try {
			// step 01 : 동일한 그룹 번호에 대하여 orderno 컬럼의 숫자를 1씩 증가시켜 주어야 합니다.
			sql = "update boards set orderno = orderno + 1";
			sql += " where groupno = ? and orderno > ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bean.getGroupno());
			pstmt.setInt(2, orderno);
			cnt = pstmt.executeUpdate();
			
			if(pstmt != null) {pstmt.close();}
			
			// step 02 : 답글(bean) 객체 정보를 이용하여 DB에 추가합니다.
			sql = "insert into boards(no, id, password, subject, contents, groupno, orderno, depth)";
			sql += " values(seqboard.nextval, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, bean.getId());
			pstmt.setString(2, bean.getPassword());
			pstmt.setString(3, bean.getSubject());
			pstmt.setString(4, bean.getContents());
			pstmt.setInt(5, bean.getGroupno());
			pstmt.setInt(6, bean.getOrderno());
			pstmt.setInt(7, bean.getDepth());
			
			cnt = pstmt.executeUpdate();
			conn.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
		} finally {
			try {
				if(pstmt != null) {pstmt.close();}
				super.closeConnection();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return cnt;
	}
	
	public Integer getReplyCount(Integer groupno) {
		// 해당 그룹 번호(groupno)에 속해 있는 데이터의 행 개수를 반환해줍니다.
		System.out.println("검색할 그룹 번호 : " + groupno);
		
		String sql = "select count(*) as cnt from boards";
		sql += " where groupno = ?";
		
		int cnt = -1; // 데이터 행 개수
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		super.conn = super.getConnection();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, groupno);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				cnt = rs.getInt("cnt");
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			try {
				if(rs != null) {rs.close();}
				if(pstmt != null) {pstmt.close();}
				super.closeConnection();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return cnt;
	}
	
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
		// 게시물 추가하기
		// step01 : 게시물 테이블에 1건 추가하기
		// step02 : 방금 추가된 게시물 글번호를 활용하여 Emoticons 테이블에 데이터 1건 추가하기
		// 오라클의 트리거(trigger)를 사용하면 자동으로 추가가 가능합니다.(향후 고려 사항)
		
		// no 컬럼은 시퀀스가 알아서 처리합니다.
		String sql = "insert into boards(no, id, password, subject, contents, groupno)";
		sql += " values(seqboard.nextval, ?, ?, ?, ?, seqboard.currval)";
		
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
			
			// 방금 추가된 게시물 글번호를 이용하여 Emoticons 테이블에 데이터를 추가합니다.
			if(pstmt != null) {pstmt.close();}
			
			// 방금 추가된 글 번호 추출하기
			sql = "select seqboard.currval as no from dual";
			ResultSet rs = null;
			int no = 0; // 방금 추가된 글 번호
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				no = rs.getInt("no");
			}
			if(pstmt != null) {pstmt.close();}
			
			// 이모티콘 테이블에 인서트 합니다.
			sql = "insert into emoticons(no, likes, hates, love, sad, angry)";
			sql += " values(?, 0, 0, 0, 0, 0)";
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
		// 답글을 고려해야 하기 때문에 over() 구문을 변경합니다.
		String sql = "select no, id, password, subject, contents, readhit, regdate, remark, groupno, orderno, depth";
		sql += " from (select rank() over(order by groupno desc, orderno asc) as ranking, no, id, password, subject, contents, readhit, regdate,";
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
