package parceirosDaEstrada.web.server;

import javax.jdo.PersistenceManager;
import componentesdosistema.Usuario;

public class GerenciadorDeDadosJDO {
	
	public static void salvaUsuario(Usuario user) {
		PersistenceManager pm = PMF.get().getPersistenceManager();

		//Employee e = new Employee("Alfred", "Smith", new Date());
		user.setKey(user.getLogin());
		try {
			pm = PMF.get().getPersistenceManager();
			pm.makePersistent(user);
		} finally {
			if (null != pm)		
			pm.close();
		}
	}
}
