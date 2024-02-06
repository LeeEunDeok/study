package com.shopping.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shopping.model.bean.Product;
import com.shopping.utility.MyUtility;
import com.shopping.utility.Paging;

public class ProductDao extends SuperDao{
	public int deleteData(int pnum) {
		// 상품은 주문 상세 테이블과 참조 무결성 제약 조건 set null 을 가지고 있습니다.
		// 상품 삭제 시 주문 상세 테이블의 remark 컬럼을 갱신하도록 합니다.
		
		String sql = "";
		PreparedStatement pstmt = null;
		int cnt = -1;
		
		try {
			super.conn = super.getConnection();

			Product bean = this.getDataBean(pnum);
			String message = MyUtility.getCurrentTime() + bean.getName() + "(상품 번호 : " + pnum + "번)이 삭제 되었습니다. " ;
			
			// getDataBean() 메소드가 실행된 이후에 접속 개체가 닫힙니다.
			// 접속 객체를 다시 열어주는 효과를 내는 다음 메소드를 다시 호출하도록 합니다.
			super.conn = super.getConnection();
			conn.setAutoCommit(false); // dml 과 관련 있습니다.
			// 다음 항목도 공부하세요 : Connection Pooling 기법
			
			// step01 : 주문 상세 테이블의 비고 컬럼에 삭제된 히스토리를 남깁니다.
			sql = "update orderdetails set remark = ? where pnum = ?";
			pstmt = conn.prepareStatement(sql);
			// sql 문장 실행 전 치환
			pstmt.setString(1, message);
			pstmt.setInt(2, pnum);
			cnt = pstmt.executeUpdate();
			if(pstmt != null) {pstmt.close();} // 안적어도 되긴 함
			
			// step02 : 상품 테이블에서 해당 상품 번호를 삭제합니다.
			sql = "delete from products where pnum = ?";
			pstmt = conn.prepareStatement(sql);
			// sql 문장 실행 전 치환
			pstmt.setInt(1, pnum);
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
	
	public int updateData(Product bean) {
		String sql = "update products set name = ?, company = ?, image01 = ?, image02 = ?, image03 = ?, stock = ?,";
		sql	+= " price = ?, category = ?, contents = ?, point = ?, inputdate = ?";
		sql += " where pnum = ?";
		
		PreparedStatement pstmt = null;
		int cnt = -1;
		
		try {
			super.conn = super.getConnection();
			// 자동 커밋 기능을 비활성 합니다.
			// 실행이 성공적으로 완료되면 커밋 하도록 commit() 메소드를 명시합니다.
			conn.setAutoCommit(false);
			
			pstmt = conn.prepareStatement(sql);
			
			// 실행전 ? 치환하기
			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getCompany());
			pstmt.setString(3, bean.getImage01());
			pstmt.setString(4, bean.getImage02());
			pstmt.setString(5, bean.getImage03());
			pstmt.setInt(6, bean.getStock());
			pstmt.setInt(7, bean.getPrice());
			pstmt.setString(8, bean.getCategory());
			pstmt.setString(9, bean.getContents());
			pstmt.setInt(10, bean.getPoint());
			pstmt.setString(11, bean.getInputDate());
			pstmt.setInt(12, bean.getPnum());
			
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
	
	public Product getDataBean(int pnum) {
		String sql = "select * from products";
		sql += " where pnum = ?";
		
		PreparedStatement pstmt = null; // 문장 객체
		ResultSet rs = null;
		Product bean = null;
		
		super.conn = super.getConnection();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pnum);
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
		System.out.print("bean 데이터 조회 결과 : ");
		System.out.println(bean);
		
		return bean;
	}
	
	public List<Product> getDataList(Paging paging){
		String sql = "select pnum, name, company, image01, image02, image03, stock, price, category, contents, point, inputdate";
		sql += " from (select rank() over(order by pnum desc) as ranking, pnum, name, company, image01, image02, image03, stock, price, category, contents, point, inputdate";
		sql += " from products";
		// 필드 검색을 위해 mode 변수로 분기 처리 하도록 합니다.
		String mode = paging.getMode();
		String keyword = paging.getKeyword();
		
		if(mode==null || mode.equals("all") || mode.equals("null") || mode.equals("")) {
			
		}else { // 전체모드가 아니면 
			sql += " where " + mode + " like '%" + keyword + "%'";
		}
		sql += ")";
		sql += " where ranking between ? and ?";
		
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<Product> dataList = new ArrayList<Product>();
		
		super.conn = super.getConnection();
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, paging.getBeginRow());
			pstmt.setInt(2, paging.getEndRow());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Product bean = this.resultSet2Bean(rs);
				
				dataList.add(bean);
				
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
		
		
		return dataList;
	}
	
	public List<Product> getDataList(int beginRow, int endRow){
		String sql = "select pnum, name, company, image01, image02, image03, stock, price, category, contents, point, inputdate"
				+ " from (select rank() over(order by pnum asc) as ranking, pnum, name, company, image01, image02, image03, stock, price, category, contents, point, inputdate"
				+ " from products)"
				+ " where ranking between ? and ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<Product> dataList = new ArrayList<Product>();
		
		super.conn = super.getConnection();
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, endRow);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Product bean = this.resultSet2Bean(rs);
				
				dataList.add(bean);
				
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
		
		
		return dataList;
	}
	
	private Product resultSet2Bean(ResultSet rs) {
		try {
			Product bean = new Product();
			
			bean.setCategory(rs.getString("category"));
			bean.setCompany(rs.getString("company"));
			bean.setContents(rs.getString("contents"));
			bean.setImage01(rs.getString("image01"));
			bean.setImage02(rs.getString("image02"));
			bean.setImage03(rs.getString("image03"));
			bean.setInputDate(String.valueOf(rs.getString("inputdate")));
			bean.setName(rs.getString("name"));
			bean.setPnum(rs.getInt("pnum"));
			bean.setPoint(rs.getInt("point"));
			bean.setPrice(rs.getInt("price"));
			bean.setStock(rs.getInt("stock"));
			
			return bean;
			
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	public List<Product> getDataList02(){
		
		List<Product> dataList = new ArrayList<Product>();
		
		for (int i= 1; i<= 6; i++) {
			if(i % 2 == 0) {
				dataList.add(new Product(10 * i, "상품0" + i, null,
				"../image/croissant_0" + i + ".png", null, null, 0, 0, null, "이 상품은 너무 좋아여 엄청 맛있어요", 0, null));
			}else {
				dataList.add(new Product(10 * i, "상품0" + i, null,
						"../image/croissant_0" + i + ".png", null, null, 0, 0, null, "별로에요.", 0, null));
				
			}
		
		}
		return dataList;
	}

	public int insertData(Product bean) {
		System.out.println(bean);
		
		// 상품 번호는 시퀀스로 처리, 입고 일자는 상황에 맞도록 설정(기본 값 지정)하기로 합니다.
		String sql = "insert into products(pnum, name, company, image01, image02, image03, stock, price, category,";
		sql += " contents, point, inputdate)";
		sql += " values(seqproduct.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement pstmt = null;
		
		int cnt = -1;
		
		try {
			super.conn = super.getConnection();
			conn.setAutoCommit(false);
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getCompany());
			pstmt.setString(3, bean.getImage01());
			pstmt.setString(4, bean.getImage02());
			pstmt.setString(5, bean.getImage03());
			pstmt.setInt(6, bean.getStock());
			pstmt.setInt(7, bean.getPrice());
			pstmt.setString(8, bean.getCategory());
			pstmt.setString(9, bean.getContents());
			pstmt.setInt(10, bean.getPoint());
			pstmt.setString(11, bean.getInputDate());
			
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
