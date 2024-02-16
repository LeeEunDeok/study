package com.shopping.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.shopping.model.bean.Combo01;
import com.shopping.model.bean.Combo02;
import com.shopping.model.bean.Combo03;
import com.shopping.model.bean.Combo04;
import com.shopping.model.bean.Combo05;

// 2개 이상의 테이블을 조인한 결과를 컬렉션으로 반환하기 위하여 설계된 Dao
public class CompositeDao extends SuperDao {

	public List<Combo01> view01() {
		List<Combo01> dataList = new ArrayList<Combo01>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select m.name, b.subject, b.contents, b.regdate" ;
		sql += " from members m inner join boards b" ;
		sql += " on m.id = b.id" ;
		sql += " order by m.name";
		
		try {
			conn = super.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				dataList.add(this.makeBeanCombo01(rs));
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

	private Combo01 makeBeanCombo01(ResultSet rs) {
		Combo01 bean = new Combo01();
		
		try {
			bean.setContents(rs.getString("contents"));
			bean.setName(rs.getString("name"));
			bean.setRegdate(String.valueOf(rs.getDate("regdate")));
			bean.setSubject(rs.getString("subject"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return bean;
	}
	
	public List<Combo02> view02() {
		List<Combo02> dataList = new ArrayList<Combo02>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = " select m.name, count(*) as cnt " ;
		sql += " from members m inner join boards b  " ;
		sql += " on m.id = b.id " ;
		sql += " group by m.name " ;
		sql += " order by m.name  " ;
		
		try {
			conn = super.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				dataList.add(this.makeBeanCombo02(rs));
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

	private Combo02 makeBeanCombo02(ResultSet rs) {
		Combo02 bean = new Combo02();
		
		try {
			bean.setCnt(rs.getInt("cnt"));
			bean.setName(rs.getString("name"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return bean;
	}
	
	public List<Combo03> view03() {
		List<Combo03> dataList = new ArrayList<Combo03>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = " select m.name mname, p.name pname, o.orderdate, od.qty, p.price, od.qty * p.price as amount " ;
		sql += " from ((members m inner join orders o " ;
		sql += " on m.id=o.id) inner join orderdetails od " ;
		sql += " on o.oid=od.oid) inner join products p " ;
		sql += " on od.pnum=p.pnum " ;
		sql += " order by p.name desc, m.name asc  " ;
		
		try {
			conn = super.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				dataList.add(this.makeBeanCombo03(rs));
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

	private Combo03 makeBeanCombo03(ResultSet rs) {
		Combo03 bean = new Combo03();
		
		try {
			bean.setAmount(rs.getInt("amount"));
			bean.setMname(rs.getString("mname"));
			bean.setOrderdate(String.valueOf(rs.getDate("orderdate")));
			bean.setPname(rs.getString("pname"));
			bean.setPrice(rs.getInt("price"));
			bean.setQty(rs.getInt("qty"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return bean;
	}
	
	public List<Combo04> view04() {
		List<Combo04> dataList = new ArrayList<Combo04>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = " select m.id, sum(od.qty * p.price) as sumtotal  " ;
		sql += " from ((members m inner join orders o " ;
		sql += " on m.id=o.id) inner join orderdetails od " ;
		sql += " on o.oid=od.oid) inner join products p " ;
		sql += " on od.pnum=p.pnum " ;
		sql += " group by m.id  " ;
		
		try {
			conn = super.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				dataList.add(this.makeBeanCombo04(rs));
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

	private Combo04 makeBeanCombo04(ResultSet rs) {
		Combo04 bean = new Combo04();
		
		try {
			bean.setId(rs.getString("id"));
			bean.setSumtotal(rs.getInt("sumtotal"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return bean;
	}
	
	public List<Combo05> view05() {
		List<Combo05> dataList = new ArrayList<Combo05>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = " select m.id, count(o.id) as cnt  " ;
		sql += " from members m left outer join orders o " ;
		sql += " on m.id=o.id  " ;
		sql += " group by m.id " ;
		sql += " order by cnt desc, m.id asc  " ;
		
		try {
			conn = super.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				dataList.add(this.makeBeanCombo05(rs));
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

	private Combo05 makeBeanCombo05(ResultSet rs) {
		Combo05 bean = new Combo05();
		
		try {
			bean.setCnt(rs.getInt("cnt"));
			bean.setId(rs.getString("id"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return bean;
	}
}
