package buildModels;

import java.util.ArrayList;

//import com.google.gson.Gson;

public class BuildDAOTester {

	public static void main(String[] args) {
	
		// make the first build
		
		Build_Line bl1 = new Build_Line(1035,"cpu","intel cpu", 689.99,1);
		Build_Line bl2 = new Build_Line(1120,"motherboard", "msi motherboard", 173.99,1);
		
		ArrayList<Build_Line> blines = new ArrayList<Build_Line>();
		blines.add(bl1);
		blines.add(bl2);
		
		java.sql.Date d1 = new java.sql.Date(System.currentTimeMillis()-86400000);
		java.sql.Date d2 = new java.sql.Date(System.currentTimeMillis());
		System.out.println(d1 + " ; " + d2);
		
		double total = (bl1.getBuild_line_price() * bl1.getBuild_line_quantity())
			+ (bl2.getBuild_line_price() * bl2.getBuild_line_quantity());
		
		Build b1 = new Build(1011,1006,d1,d2,"p","c","m",10,total,20);
		b1.setBuildLines(blines);
		
		// make the second build
		
		Build_Line bl3 = new Build_Line(1038,"cpu", "amd cpu", 1896.59,1);
		Build_Line bl4 = new Build_Line(1073,"motherboard","asus motherboard", 523.2,1);
		
		ArrayList<Build_Line> blines2 = new ArrayList<Build_Line>();
		blines2.add(bl3);
		blines2.add(bl4);
		
		java.sql.Date d3 = new java.sql.Date(System.currentTimeMillis()-86400000);
		java.sql.Date d4 = new java.sql.Date(System.currentTimeMillis());
		System.out.println(d3 + " ; " + d4);
		
		double total2 = (bl3.getBuild_line_price() * bl3.getBuild_line_quantity())
			+ (bl4.getBuild_line_price() * bl4.getBuild_line_quantity());
		
		Build b2 = new Build(1011,1002,d3,d4,"p","g","h",10,total2,20);
		b2.setBuildLines(blines2);		
		
		Build b3 = new Build(1007,1002,d3,null,"p","g","h",10,total2,20);
		b3.setBuildLines(blines2);
		
		// call the tester singleton object and run tests
		
		//BuildDAO dao = BuildDAO.getBuildDAO();
		
		//Gson tester = new Gson();
		//String jsonText = tester.toJson(b1);
		//System.out.println(jsonText);
		
		//dao.createBuild(b1);
		
		//dao.createBuild(b2);
		
		//dao.createBuild(b1);
		
		//dao.retrieveBuildByBuildId("1011");
		
		//dao.retrieveBuildsByCustomerId("1011");
		
		//Build b4 = dao.retrieveBuildByBuildId("1012");
		//b4.setBuild_dispatch_date(d4);
		//b4.setBuild_status('d');
		//dao.updateBuild(b4);
		
		
		//dao.retrieveAllBuilds();
		
		// System.out.println(b2.getBuildLines().get(1).getBuild_line_product_type());
		
		//b2.setBuild_status('c');
		//b2.setBuild_dispatch_date(null);
		//dao.updateBuild(b2);
		
		//dao.deleteBuild("1019");
		
		/*for (int i = 1051; i < 1073; i++) {
			dao.deleteBuild(String.valueOf(i));
		}*/
		
		// need administrator privileges
		//dao.deleteBuildsByStatus("o");
	
	}
	
}
