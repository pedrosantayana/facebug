package dao;

public class UsuarioDAO extends DAO {
  public UsuarioDAO() {
		super();
		conectar();
	}

	public void finalize() {
		close();
	}

  

}
