package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Usuario;

/**
 * UsuarioDAO: herda DAO e utiliza model Usuario.
 * Realiza insercao, atualizacao, exclusao e recuperacao
 * de usuarios do banco de dados com consultas SQL.
 * 
 * @author Henrique Pato Magalhães
 * @version 1 12/10/22
 */
public class UsuarioDAO extends DAO {
  /**
   * Construtor da classe: faz conexao com <code>DAO</code>
   */
  public UsuarioDAO() {
    super();
    conectar();
  }

  /**
   * Encerra conexao com <code>DAO</code>
   */
  public void finalize() {
    close();
  }

  /**
   * Insere usuario no banco de dados
   * 
   * @param usuario <code>Usuario</code> a ser inserida
   * @return <code>boolean</code> status
   *         <code>true</code> se conseguir inserir
   *         <code>false</code> se nao conseguir
   */
  public boolean create(Usuario usuario) {
    boolean status = false;

    try {
      String sql = "INSERT INTO usuario (Username, Nome, Senha, Email) VALUES (?, ?, ?, ?)";

      PreparedStatement st = conexao.prepareStatement(sql);
      st.setString(1, usuario.getUsername());
      st.setString(2, usuario.getName());
      st.setBytes(3, usuario.getHashedPassword());
      st.setString(4, usuario.getEmail());
      st.executeUpdate();
      st.close();

      status = true;
    } catch (SQLException u) {
      throw new RuntimeException(u);
    }

    return status;
  }

  /**
   * Recupera usuario do banco de dados pelo username
   * 
   * @param username <code>int</code> identificador do usuario
   * @return <code>Usuario</code> recuperada
   */
  public Usuario get(String username) {
    Usuario usuario = null;

    try {
      Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
      String sql = "SELECT * FROM usuario WHERE username=" + username;
      ResultSet rs = st.executeQuery(sql);

      if (rs.next()) {

        usuario = new Usuario(rs.getString("username"), rs.getString("email"), rs.getBytes("hashedPassword"));
      }

      st.close();
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }

    return usuario;
  }

  /**
   * Chama funcao <code>get(String orderBy)</code>
   * passando orderBy nulo
   * 
   * @return <code>List<Usuario></code> lista de usuarios nao ordenada
   */
  public List<Usuario> get() {
    return getList("");
  }

  /**
   * Chama funcao <code>get(String orderBy)</code>
   * passando username como orderBy
   * 
   * @return <code>List<Username></code> lista de usuarios ordenada pelo username
   */
  public List<Usuario> getOrderByUsername() {
    return getList("username");
  }

  /**
   * Ordena lista de usuarios pelo orderBy solicitado
   * 
   * @param orderBy <code>String</code> chave de ordenacao
   * @return <code>List<Usuario></code> lista de usuarios
   */
  private List<Usuario> getList(String orderBy) {
    List<Usuario> usuarios = new ArrayList<Usuario>();

    try {
      Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
      String sql = "SELECT * FROM usuario" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
      ResultSet rs = st.executeQuery(sql);

      while (rs.next()) {

        Usuario u = new Usuario(rs.getString("username"), rs.getString("email"), rs.getBytes("hashedPassword"));

        usuarios.add(u);
      }

      st.close();
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }

    return usuarios;
  }

  public Usuario[] list(String username) {
    Usuario[] usuarios = new Usuario[100];

    try {
      Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
      String sql = "SELECT * FROM usuario WHERE Username=" + username;
      ResultSet rs = st.executeQuery(sql);

      int i = 0;
      while (rs.next()) {

        usuarios[i] = new Usuario(rs.getString("username"), rs.getString("email"), rs.getBytes("hashedPassword"));

        i++;
      }

      st.close();
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }

    return usuarios;
  }

  /**
   * Atualiza usuario no banco de dados
   * 
   * @param usuario <code>Usuario</code> a ser inserida
   * @return <code>boolean</code> status
   *         <code>true</code> se conseguir atualizar
   *         <code>false</code> se nao conseguir
   */
  public boolean update(String username, Usuario usuario) {
    boolean status = false;

    try {
      String sql = "UPDATE usuario SET Email=?, HashedPassword=" + usuario.getHashedPassword()
          + " , Followers=?, Likes=?, Following=? WHERE username =" + username;

      PreparedStatement st = conexao.prepareStatement(sql);
      st.setString(1, usuario.getEmail());
      st.setObject(2, usuario.getFollowers());
      st.setInt(3, usuario.getLikes());
      st.setObject(4, usuario.getFollowing());
      st.executeUpdate();
      st.close();

      status = true;
    } catch (SQLException u) {
      throw new RuntimeException(u);
    }

    return status;
  }

  /**
   * Deleta usuario com username passado do banco de dados
   * 
   * @param username <code>String</code> identificador do usuario
   * @return <code>boolean</code> status
   *         <code>true</code> se conseguir deletar
   *         <code>false</code> se nao conseguir
   */
  public boolean delete(String username) {
    boolean status = false;

    try {
      Statement st = conexao.createStatement();
      st.executeUpdate("DELETE FROM usuario WHERE username = " + username);
      st.close();

      status = true;
    } catch (SQLException u) {
      throw new RuntimeException(u);
    }

    return status;
  }

  /**
   * Busca usuario a partir de um username
   * 
   * @param username <code>String</code> identificador do usuario
   * @return <code>Usuario</code> status
   */
  public Usuario[] search(String query) {
    Usuario[] usuarios = new Usuario[100];

    try {
      Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
      String sql = "SELECT * FROM usuario WHERE Username=" + query;
      ResultSet rs = st.executeQuery(sql);

      int i = 0;
      while (rs.next()) {

        usuarios[i] = new Usuario(rs.getString("username"), rs.getString("email"), rs.getBytes("hashedPassword"));

        i++;
      }
      st.close();
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }

    return usuarios;
  }
}
