package userModels;

public class CustomerDAOTester {

	public static void main(String[] args) {
		
		CustomerDAO dao = CustomerDAO.getCustomerDAO();
		
		/* Customer c1 = dao.retrieveCustomerByEmail("evelyn.woodcock@email.com");
		
		if (c1 == null) {
			System.out.println("boo");
		} else {
			System.out.println(c1.getCustomer_forename());
		}
		
		System.out.println("yo");
		System.out.println(c1.toString());
		System.out.println(c1.getCustomer_token());
		
		c1.setCustomer_token(dao.generateCustomerToken());
		
		System.out.println(c1.getCustomer_token());
		*/
		
		Customer c2 = new Customer("Monks","Peter","71 Duckpit Lane","Lower Rochford","WR15 5EZ","07773504234","peter.monks@email.com","password1","");
		
		//dao.createCustomer(c2);
		
		//System.out.println(c2.toString());
		
		//c2.setCustomer_token(dao.createCustomerToken(c2.getCustomer_email()));
		
		//System.out.println("yo" + c2.toString());
		//System.out.println("yo" + dao.retrieveCustomerToken(c2.getCustomer_email()));
		
		//c2.setCustomer_token(dao.removeCustomerToken(c2.getCustomer_email()));
		//System.out.println("hkdh" + c2.toString());
		
		//dao.deleteCustomer("peter.monks@email.com");
		
		System.out.println(c2.getCustomer_password());
		
		c2.setCustomer_password(UserUtilities.hashString(c2.getCustomer_password()));
		
		System.out.println(c2.getCustomer_password());
		
		
	}
	
}
