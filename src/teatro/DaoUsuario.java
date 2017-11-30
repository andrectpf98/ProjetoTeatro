package teatro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DaoUsuario {
	
	
		//INSERT DEFAULT DO CADASTRO
		public static int InsertBancoCadastro(String cpf,String nome,Connection con){
			int rows = 0;
			String insert = "INSERT INTO usuario(CPF, Nome) VALUES(?,?)";
			while(rows != -1){
				try{
			  		PreparedStatement stmt = con.prepareStatement(insert);
			  		stmt.setString(1,cpf);
			  		stmt.setString(2,nome);
			  		rows = stmt.executeUpdate();
			  		stmt.close();
			  		return rows;
			  		
			  	}catch(SQLException e){
			  		e.printStackTrace();
			  		return -1;
			     }
			  }
			return rows;
		}
		
		//SELECT E PRINT GERAL DE TUDO DO BANCO DE USUARIO
		public static void SelectBancoUsuario(Connection con){
			
			try {
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM usuario");
				
					while(rs.next()){
						String cpf = rs.getString("CPF");
						String nome = rs.getString("Nome");
						System.out.println("CPF: "+cpf+"Nome: "+nome);
					}
					rs.close();
					stmt.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		//INICIA E RETORNA A LISTA DE CPF CADASTRADOS
		public List<String> ListaDeUsuariosCPF(Connection con){
			List<String> listaCpf = new ArrayList<String>();
			try {
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT CPF FROM usuario");
			
				while(rs.next()){
					String cpf = rs.getString("CPF");
					
					listaCpf.add(cpf);
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch(Exception e){
				System.out.println("Problema na inserçao do valor");
			}
			
			return listaCpf;
			
			
		}
}
