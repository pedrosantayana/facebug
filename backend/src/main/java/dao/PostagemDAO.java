package dao;

import model.Postagem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
// import java.sql.Timestamp;
// import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * PostagemDAO: herda DAO e utiliza model Postagem.
 * Realiza insercao, atualizacao, exclusao e recuperacao
 * de postagens do banco de dados com consultas SQL.
 * 
 * @author Carolina Nigri
 * @version 2 12/10/22
 */
public class PostagemDAO extends DAO {
  /**
   * Construtor da classe: faz conexao com <code>DAO</code>
   */
  public PostagemDAO() {
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
   * Insere postagem no banco de dados
   * 
   * @param postagem <code>Postagem</code> a ser inserida
   * @return <code>boolean</code> status
   *         <code>true</code> se conseguir inserir
   *         <code>false</code> se nao conseguir
   */
  public boolean insert(Postagem postagem) {
    boolean status = false;

    try {
      String sql = "INSERT INTO postagem (id, title, content, media, categoryId, date, ownerUsername) VALUES (?, ?, ?, ?, ?, ?, ?);";

      PreparedStatement st = conexao.prepareStatement(sql);
      st.setObject(1, postagem.getId());
      st.setString(2, postagem.getTitle());
      st.setString(3, postagem.getContent());
      st.setString(4, postagem.getMedia());
      st.setObject(5, postagem.getCategoryId());
      st.setDate(6, postagem.getDate());
      st.setString(7, postagem.getOwnerUsername());
      st.executeUpdate();
      st.close();

      status = true;
    } catch (SQLException u) {
      throw new RuntimeException(u);
    }

    return status;
  }

  /**
   * Recupera postagem do banco de dados pelo id
   * 
   * @param id <code>UUID</code> identificador da postagem
   * @return <code>Postagem</code> recuperada
   */
  public Postagem get(UUID id) {
    Postagem postagem = null;

    try {
      Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
      String sql = "SELECT * FROM postagem WHERE id=" + id;
      ResultSet rs = st.executeQuery(sql);

      if (rs.next()) {
        UUID _id = (UUID) rs.getObject("id");
        UUID _categoryId = (UUID) rs.getObject("categoryId");

        postagem = new Postagem(_id, rs.getString("title"), rs.getString("content"),
            rs.getString("media"), _categoryId, rs.getDate("date"),
            rs.getString("ownerUsername"));
      }

      st.close();
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }

    return postagem;
  }

  /**
   * Chama funcao <code>get(String orderBy)</code>
   * passando orderBy nulo
   * 
   * @return <code>List<Postagem></code> lista de postagens nao ordenada
   */
  public List<Postagem> get() {
    return get("");
  }

  /**
   * Chama funcao <code>get(String orderBy)</code>
   * passando id como orderBy
   * 
   * @return <code>List<Postagem></code> lista de postagens ordenada pelo id
   */
  public List<Postagem> getOrderByID() {
    return get("id");
  }

  /**
   * Ordena lista de postagens pelo orderBy solicitado
   * 
   * @param orderBy <code>String</code> chave de ordenacao
   * @return <code>List<Postagem></code> lista de postagens
   */
  private List<Postagem> get(String orderBy) {
    List<Postagem> postagens = new ArrayList<Postagem>();

    try {
      Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
      String sql = "SELECT * FROM postagem" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
      ResultSet rs = st.executeQuery(sql);

      while (rs.next()) {
        UUID _id = (UUID) rs.getObject("id");
        UUID _categoryId = (UUID) rs.getObject("categoryId");

        Postagem p = new Postagem(_id, rs.getString("title"), rs.getString("content"),
            rs.getString("media"), _categoryId, rs.getDate("date"),
            rs.getString("ownerUsername"));

        postagens.add(p);
      }

      st.close();
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }

    return postagens;
  }

  public Postagem[] list(String username) {
    Postagem[] postagens = new Postagem[100];

    try {
      Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
      String sql = "SELECT * FROM postagem WHERE ownerUsername=" + username;
      ResultSet rs = st.executeQuery(sql);

      int i = 0;
      while (rs.next()) {
        UUID _id = (UUID) rs.getObject("id");
        UUID _categoryId = (UUID) rs.getObject("categoryId");

        postagens[i] = new Postagem(_id, rs.getString("title"), rs.getString("content"),
            rs.getString("media"), _categoryId, rs.getDate("date"),
            rs.getString("ownerUsername"));

        i++;
      }

      st.close();
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }

    return postagens;
  }

  /**
   * Atualiza postagem no banco de dados
   * 
   * @param postagem <code>Postagem</code> a ser inserida
   * @return <code>boolean</code> status
   *         <code>true</code> se conseguir atualizar
   *         <code>false</code> se nao conseguir
   */
  public boolean update(Postagem postagem) {
    boolean status = false;

    try {
      String sql = "UPDATE postagem SET title=?, content=?, media=?, categoryId=?, date=?, ownerUsername=?";

      PreparedStatement st = conexao.prepareStatement(sql);
      st.setString(1, postagem.getTitle());
      st.setString(2, postagem.getContent());
      st.setString(3, postagem.getMedia());
      st.setObject(4, postagem.getCategoryId());
      st.setDate(5, postagem.getDate());
      st.setString(6, postagem.getOwnerUsername());
      st.executeUpdate();
      st.close();

      status = true;
    } catch (SQLException u) {
      throw new RuntimeException(u);
    }

    return status;
  }

  /**
   * Deleta postagem com id passado do banco de dados
   * 
   * @param id <code>UUID</code> identificador da postagem
   * @return <code>boolean</code> status
   *         <code>true</code> se conseguir deletar
   *         <code>false</code> se nao conseguir
   */
  public boolean delete(UUID id) {
    boolean status = false;

    try {
      Statement st = conexao.createStatement();
      st.executeUpdate("DELETE FROM postagem WHERE id = " + id);
      st.close();

      status = true;
    } catch (SQLException u) {
      throw new RuntimeException(u);
    }

    return status;
  }
}
