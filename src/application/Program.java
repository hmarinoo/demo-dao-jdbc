package application;

import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program {

	public static void main(String[] args) {
				
//		SellerDao sellerDao = DaoFactory.createSellerDao();
//		
//		System.out.println("=== Test 1: seller findById ====" );
//		Seller seller = sellerDao.findById(3);
//		System.out.println(seller );
//		
//		System.out.println("\n=== Test 2: seller findByDepartment" );
//		List<Seller> sellers = sellerDao.findByDepartment(new Department(2, null));
//		sellers.forEach(System.out::println);
//		
//		System.out.println("\n=== Test 3: seller findAll" );
//		sellers = sellerDao.findAll();
//		sellers.forEach(System.out::println);
//		
//		System.out.println("\n=== Test 4: seller insert" );
//		Seller newSeller = new Seller(null, "junio", "junior@gmail.com", new Date(), 2500.00,new Department(2, null) ) ;
//		sellerDao.insert(newSeller);
//		System.out.println(newSeller.getId());
//		
//		System.out.println("\n=== Test 5: seller Update" );
//		seller = sellerDao.findById(2);
//		seller.setName("Jonas");
//		seller.setBaseSalary(1750.00);
//		sellerDao.update(seller);
//		System.out.println("Update Complete");
//		
//		System.out.println("\n=== Test 6: seller delete" );
//		System.out.print("Enter id for delete test:");
//		int id = new Scanner(System.in).nextInt();
//		sellerDao.deleteById(id);
//		System.out.println("Delet Complete");
		
		DepartmentDao depDao = DaoFactory.createDepartmentDao();
		
		System.out.println("\n=== Test 1: Department insert" );
		Department newDep = new Department(null, "depInsert");
		depDao.insert(newDep);
		System.out.println(newDep.getId());
		
		System.out.println("\n=== Test 2: Department find by Id" );
		Department dep = depDao.findById(7);
		System.out.println(dep);
		
		System.out.println("\n=== Test 3: Department find all" );
		List<Department> departments = depDao.findAll();
		departments.forEach(System.out::println);
		
		System.out.println("\n=== Test 4: Department update" );
		dep = depDao.findById(8);
		dep.setName("depUpdate");
		depDao.update(dep);
		
		System.out.println("\n=== Test 5: Department delete" );
		System.out.print("Enter id for delete test:");
		int id = new Scanner(System.in).nextInt();
		depDao.deleteById(id);
		System.out.println("Delet Complete");
		
		System.out.println();
		departments = depDao.findAll();
		departments.forEach(System.out::println);
		
		
		
		
		
		
	}

}
