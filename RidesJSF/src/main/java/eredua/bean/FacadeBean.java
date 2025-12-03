package eredua.bean;
import businessLogic.*;
import dataAccess.DataAccess;
import eredua.JPAUtil;


public class FacadeBean {

	private static FacadeBean singleton = new FacadeBean();
	private static BLFacade facadeInterface= new BLFacadeImplementation(new DataAccess(JPAUtil.getEntityManager()));

	private FacadeBean() {
		try {
			facadeInterface = new BLFacadeImplementation(new DataAccess(JPAUtil.getEntityManager()));
		} catch (Exception e) {
			System.out.println("FacadeBean: negozioaren logika sortzean errorea: " + e.getMessage());
		}
	}

	public static BLFacade getBusinessLogic() {
		return facadeInterface;
	}
}
