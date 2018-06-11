package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Conexao.Conexao;

public class ConsultaDAO {
	Connection con;
	
	public void adicionar(Consulta c2) {
		Conexao c = new Conexao();
		con = c.abrir();
		PreparedStatement p;
		try {
			p = con.prepareStatement("insert into tbConsulta (descricaoRaca, codEspecie, codAdmin) values (?,?,?)");
				   p.setString(1, r.getDescricaoRaca());
				   p.setInt(2, r.getCodEspecie());
				   p.setInt(3, r.getCodAdmin());
				   p.executeUpdate();
				   p.close();
			con = c.fechar();
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao cadastrar");
		}	
	}

	public List<Consulta> retornaDados() {
		List<Consulta> co = new ArrayList<Consulta>();
		Conexao c = new Conexao();
		con = c.abrir();
		PreparedStatement p;
		try {
			p = con.prepareStatement("select * from tbRaca");
			ResultSet rs = p.executeQuery();
			while(rs.next()){
				Consulta c2 = new Consulta();
				r.setCodRaca(rs.getInt("codRaca"));
				r.setDescricaoRaca(rs.getString("descricaoRaca"));
				r.setCodEspecie(rs.getInt("codEspecie"));
				r.setCodAdmin(rs.getInt("codAdmin"));
				co.add(c2);
			}
		rs.close();
		p.close();
		con.close();
		} catch (SQLException e) {
			System.out.print("Erro ao listar raças");
		}		
		return co;	
	}

	public void atualizar(Consulta c2) {
		Conexao c = new Conexao();
		con = c.abrir();
		PreparedStatement p;
		try {
			p = con.prepareStatement("update tbRaca set descricaoRaca = ?, codEspecie = ?, codAdmin = ? where codRaca = ?");
			 		p.setString(1, r.getDescricaoRaca());
			 		p.setInt(2, r.getCodEspecie());
			 		p.setInt(3, r.getCodAdmin());
			 		p.setInt(4, r.getCodRaca());
			  		p.executeUpdate();
			  		p.close();
			con = c.fechar();
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao atualizar");
		}	
		
	}

	public List<Consulta> pesquisaEspecifica(String login) {
		List<Consulta> ra = new ArrayList<Consulta>();
		Conexao c = new Conexao();
		con = c.abrir();
		PreparedStatement p;
		try {
			p = con.prepareStatement("SELECT * from tbRaca WHERE descricaoRaca like ?");
			p.setString(1, "%" + login + "%");
			ResultSet rs = p.executeQuery();
			while(rs.next()){
				Raca r = new Raca();
				r.setCodRaca(rs.getInt("codRaca"));
				r.setDescricaoRaca(rs.getString("descricaoRaca"));
				r.setCodEspecie(rs.getInt("codEspecie"));
				r.setCodAdmin(rs.getInt("codAdmin"));
				ra.add(r);
			}
		rs.close();
		p.close();
		con.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Nenhuma dado encontrado");
		}		
		return ra;
	}
}
