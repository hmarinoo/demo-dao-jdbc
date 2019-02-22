package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {
	
	private Connection conn = null;
	
	public SellerDaoJDBC(Connection conn) {
	this.conn = conn;
	}

	@Override
	public void insert(Seller seller) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Seller seller) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Seller findById(Integer id) {
		PreparedStatement st =null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					 "SELECT seller.*,department.Name as DepName FROM coursejdbc.seller " + 
					 "inner join department on seller.DepartmentId = department.Id "
					 + "where seller.id = ?"  
					 );
			st.setInt(1, id);
			rs = st.executeQuery();
			if(rs.next()) {
				Department dep = intantiateDepartment(rs);
										
				Seller seller = intantiateSeller(rs,dep);
				
				return seller;
			}
			return null;
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
		
	}

	private Seller intantiateSeller(ResultSet rs, Department dep)throws SQLException {
		Seller seller = new Seller();
		seller.setId(rs.getInt("Id"));
		seller.setName(rs.getString("Name"));
		seller.setEmail(rs.getString("Email"));
		seller.setBaseSalary(rs.getDouble("BaseSalary"));
		seller.setBirthDate(rs.getDate("BirthDate"));
		seller.setDepartment(dep);
		return seller;
	}

	private Department intantiateDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setName(rs.getString("DepName"));
		return dep;
	}

	@Override
	public List<Seller> findAll() {
		 
				PreparedStatement st =null;
				ResultSet rs = null;
				
				List<Seller> sellers = new ArrayList<>();
				Map<Integer, Department> mapDepartment = new HashMap<>();
				try {
					st = conn.prepareStatement(
							 "SELECT seller.*,department.Name as DepName FROM coursejdbc.seller " 
							 +"inner join department on seller.DepartmentId = department.Id "
							 + "order by Name");
					
					rs = st.executeQuery();
					
					while(rs.next()) {
						Department dep = mapDepartment.get(rs.getInt("DepartmentId"));
						if(dep == null) {
						dep = intantiateDepartment(rs);	
						mapDepartment.put(rs.getInt("DepartmentId"), dep);
						}
						Seller seller = intantiateSeller(rs,dep);
						sellers.add(seller);
					}
					return sellers;
				}
				catch(SQLException e) {
					throw new DbException(e.getMessage());
				}finally {
					DB.closeStatement(st);
					DB.closeResultSet(rs);
				}
				
				
			
	}

	@Override
	public List<Seller> findByDepartment(Department department)  {
		PreparedStatement st =null;
		ResultSet rs = null;
		
		List<Seller> sellers = new ArrayList<>();
		Map<Integer, Department> mapDepartment = new HashMap<>();
		try {
			st = conn.prepareStatement(
					 "SELECT seller.*,department.Name as DepName FROM coursejdbc.seller " 
					 +"inner join department on seller.DepartmentId = department.Id "
					 + "where DepartmentId = ? "
					 + "order by Name");
			st.setInt(1, department.getId());
			rs = st.executeQuery();
			
			while(rs.next()) {
				Department dep = mapDepartment.get(rs.getInt("DepartmentId"));
				if(dep == null) {
				dep = intantiateDepartment(rs);	
				mapDepartment.put(rs.getInt("DepartmentId"), dep);
				}
				Seller seller = intantiateSeller(rs,dep);
				sellers.add(seller);
			}
			return sellers;
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
		
	}
}
