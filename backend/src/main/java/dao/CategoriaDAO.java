package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Categoria;

public class CategoriaDAO extends DAO {

    public CategoriaDAO(){
        super();
        conectar();
    }

    public void finalize(){
        close();
    }

    public boolean insertCategoria(Categoria categoria){
        boolean status = false;

        try{
            String sql = "INSERT INTO categoria (nome, UUID) " + "VALUES ('" + categoria.getNome() + "', " + categoria.getUUID() + ");";
            PreparedStatement st = conexao.prepareStatement(sql);
            st.executeUpdate();
            st.close();
            status = true;
        } catch(SQLException e){
            throw new RuntimeException(e);
        }

        return status;
    }

    public Categoria getCategoria(int id) {
        Categoria categoria = null;

        try{
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM categoria WHERE UUID = " + id;
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                categoria = new Categoria(rs.getString("nome"), rs.getInt("UUID"));
            }
            st.close();
        } catch(Exception e){
            System.err.println(e.getMessage());
        }
        return categoria;
    }

    public List<Categoria> get(){
        return getCategoria("");
    }

    public List<Categoria> getOrderByUUID(){
        return getCategoria("UUID");
    }

    public List<Categoria> getOrderByName(){
        return getCategoria("nome");
    }

    private List<Categoria> getCategoria(String orderBy){
        List<Categoria> categorias = new ArrayList<Categoria>();

        try{
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM categoria" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
            ResultSet rs = st.executeQuery(sql);

            while(rs.next()) {
                Categoria c = new Categoria(rs.getString("nome"), rs.getInt("UUID"));
                categorias.add(c);
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return categorias;
    }

    public boolean update(Categoria categoria) {
        boolean status = false;

        try{
            String sql = "UPDATE categoria SET nome = '" + categoria.getNome() + "', " + "UUID = " + categoria.getUUID() + " WHERE UUID = " + categoria.getUUID();
            PreparedStatement st = conexao.prepareStatement(sql);
            st.executeUpdate();
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }

    public boolean delete(int id) {
        boolean status = false;

        try {
            Statement st = conexao.createStatement();
            st.executeUpdate("DELETE FROM categoria WHERE UUID = " + id);
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }
}
