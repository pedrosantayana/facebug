package dao;

import model.Postagem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * PostagemDAO: herda DAO e utiliza model Postagem.
 * Realiza insercao, atualizacao, exclusao e recuperacao
 * de postagens do banco de dados com consultas SQL.
 * 
 * @author Carolina Nigri
 * @version 1 08/10/22
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
      String sql = "INSERT INTO postagem (usuario, categoria, titulo, data, conteudo, midia) "
          + "VALUES ('" + postagem.getUser() + "', '" + postagem.getCategory() + "', '"
          + postagem.getTitle() + "', ?, '" + postagem.getContent() + "', '"
          + postagem.getMedia() + "');";

      PreparedStatement st = conexao.prepareStatement(sql);
      st.setDate(1, postagem.getDate());
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
   * @param id <code>int</code> identificador da postagem
   * @return <code>Postagem</code> recuperada
   */
  public Postagem get(int id) {
    Postagem postagem = null;

    try {
      Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
      String sql = "SELECT * FROM postagem WHERE id=" + id;
      ResultSet rs = st.executeQuery(sql);

      if (rs.next()) {
        UUID _id = 
        postagem = new Postagem(rs.getInt("id"), rs.getString("usuario"), rs.getString("categoria"),
            rs.getString("titulo"), rs.getDate("data").toLocalDate(), rs.getString("conteudo"),
            rs.getString("midia"));
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
        Postagem p = new Postagem(rs.getInt("id"), rs.getString("usuario"), rs.getString("categoria"),
            rs.getString("titulo"), rs.getDate("data").toLocalDate(), rs.getString("conteudo"),
            rs.getString("midia"));

        postagens.add(p);
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
      String sql = "UPDATE postagem SET usuario = '" + postagem.getUser() + "', "
          + "categoria = '" + postagem.getCategory() + "'', "
          + "titulo = '" + postagem.getTitle() + "', "
          + "data = ?, "
          + "categoria = '" + postagem.getContent() + "'', "
          + "categoria = '" + postagem.getMedia() + "'', "
          + " WHERE id = " + postagem.getId();

      PreparedStatement st = conexao.prepareStatement(sql);
      st.setDate(1, postagem.getDate());
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
   * @param id <code>int</code> identificador da postagem
   * @return <code>boolean</code> status
   *         <code>true</code> se conseguir deletar
   *         <code>false</code> se nao conseguir
   */
  public boolean delete(int id) {
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
