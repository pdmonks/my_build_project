package productModels;

public class DAOTester {

	public static void main(String[] args) {
		
		CPUDAO cpuDAO = CPUDAO.getCPUDAO();
		
		//cpuDAO.retrieveCPUById("1001");
		
		//CPU cpu = new CPU("cpu","Intel","Intel Core i5-10500 Processor",65,185.99,28,'y','y','n','b',
		//	"i5-10500a","FCLGA1200","Desktop",6,12,3100,12,"Intel UHD Graphics 630",'y');
		
		//INSERT INTO product VALUES(1007,'cpu','Intel','Intel Core i5-10500 Processor',65,185.99,28,'y','y','n','b');
		//INSERT INTO cpu VALUES(1007,'i5-10500','FCLGA1200','Desktop',6,12,3100,12,'Intel UHD Graphics 630','y');
		
		//cpuDAO.createCPU(cpu);
		
		cpuDAO.retrieveCPUById("1027");
		
		//cpuDAO.retrieveCPUByBudget('d');
		
		//CPU cpu2 = cpuDAO.retrieveCPUById("1377");
		
		//cpu2.setProduct_description("example changed description");
		
		//cpuDAO.updateCPU(cpu2);
		
		//cpuDAO.retrieveCPUById("1377");
		
		// cpuDAO.deleteCPU("1377");
		
	}
	
}
