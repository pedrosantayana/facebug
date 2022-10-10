package dao;

import model._Produto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class _ProdutoDAO extends DAO {
	public _ProdutoDAO() {
		super();
		conectar();
	}

	public void finalize() {
		close();
	}

	public boolean insert(_Produto produto) {
		boolean status = false;
		try {
			String sql = "INSERT INTO produto (descricao, preco, quantidade, datafabricacao, datavalidade) "
					+ "VALUES ('" + produto.getDescricao() + "', "
					+ produto.getPreco() + ", " + produto.getQuantidade() + ", ?, ?);";
			PreparedStatement st = conexao.prepareStatement(sql);
			st.setTimestamp(1, Timestamp.valueOf(produto.getDataFabricacao()));
			st.setDate(2, Date.valueOf(produto.getDataValidade()));
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

	public _Produto get(int id) {
		_Produto produto = null;

		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM produto WHERE id=" + id;
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				produto = new _Produto(rs.getInt("id"), rs.getString("descricao"), (float) rs.getDouble("preco"),
						rs.getInt("quantidade"),
						rs.getTimestamp("datafabricacao").toLocalDateTime(),
						rs.getDate("datavalidade").toLocalDate());
			}
			st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return produto;
	}

	public List<_Produto> get() {
		return get("");
	}

	public List<_Produto> getOrderByID() {
		return get("id");
	}

	public List<_Produto> getOrderByDescricao() {
		return get("descricao");
	}

	public List<_Produto> getOrderByPreco() {
		return get("preco");
	}

	private List<_Produto> get(String orderBy) {
		List<_Produto> produtos = new ArrayList<_Produto>();

		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM produto" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				_Produto p = new _Produto(rs.getInt("id"), rs.getString("descricao"), (float) rs.getDouble("preco"),
						rs.getInt("quantidade"),
						rs.getTimestamp("datafabricacao").toLocalDateTime(),
						rs.getDate("datavalidade").toLocalDate());
				produtos.add(p);
			}
			st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return produtos;
	}

	public boolean update(_Produto produto) {
		boolean status = false;
		try {
			String sql = "UPDATE produto SET descricao = '" + produto.getDescricao() + "', "
					+ "preco = " + produto.getPreco() + ", "
					+ "quantidade = " + produto.getQuantidade() + ","
					+ "datafabricacao = ?, "
					+ "datavalidade = ? WHERE id = " + produto.getID();
			PreparedStatement st = conexao.prepareStatement(sql);
			st.setTimestamp(1, Timestamp.valueOf(produto.getDataFabricacao()));
			st.setDate(2, Date.valueOf(produto.getDataValidade()));
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
			st.executeUpdate("DELETE FROM produto WHERE id = " + id);
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}
}