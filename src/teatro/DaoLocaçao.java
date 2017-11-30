package teatro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DaoLocaçao {
	
	//INSERT DEFAULT DA LOCAÇAO
		public static int InsertBancoLocaçao(String cpf,int numCadeira,Connection con){
			  int rows =0;
			while(rows != -1){
				
				String insert = "INSERT INTO locaçao(CPF, NumCadeira) VALUES(?,?)";
				try{	
			        PreparedStatement stmt = con.prepareStatement(insert);
			        stmt.setString(1,cpf);
			        stmt.setInt(2, numCadeira);
			        rows = stmt.executeUpdate();
			        stmt.close();
			        return rows;
			        
			     }catch(SQLException e){
			    	 e.printStackTrace();
			    	 rows = -1;
			     }
			  }
			return rows;
			
			}
		
		//METODO UTILIZADO PARA INICIAR AS LIST OCUPADAS NO COMEÇO DO PROGRAMA
		public List<Integer> SelectLocaçaoIniciaList(Connection con){
			List<Integer> ocupadas = new ArrayList<Integer>();
			try{
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("Select NumCadeira FROM locaçao");
				while(rs.next()){
					int num = rs.getInt("NumCadeira");
					ocupadas.add(num);
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
			return ocupadas;
		}
		
		//PRINT DAS CADEIRAS OCUPADAS DO CPF INSERIDO
		public static List<Integer> SelectBancoLocaçaoDesocupa(Connection con, String cpf){
			List <Integer> listInt = new ArrayList<Integer>();
			try{
				String select = "SELECT * FROM locaçao WHERE CPF = ? ";
				PreparedStatement stmt = con.prepareStatement(select);
				stmt.setString(1, cpf);
				ResultSet rs =  stmt.executeQuery();
				System.out.println("Cadeiras ocupadas pelo CPF:  "+cpf);
				while(rs.next()){
					int numCadeira = rs.getInt("NumCadeira");
					listInt.add(numCadeira);
					System.out.println("NumeroCadeira: "+numCadeira);
				}
				rs.close();
				stmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
			return listInt;
		}
		
		//PRINT DAS CADEIRAS OCUPADAS DO BANCO LOCAÇAO
		public static void SelectBancoLocaçaoOcupadas(Connection con){
			
			try {
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT NumCadeira FROM locaçao");
				
				while(rs.next()){
						int numCadeira = rs.getInt("NumCadeira");
						System.out.print(numCadeira+" ");
					}
				stmt.close();
				rs.close();
				} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			}	
		}
		
		//SOBRECARGA PARA COMPARAR O CPF DA LOCAÇAO COM O DO USUARIO
		public static int SelectComparaCPFLocaçaoUsuario(Connection con, String cpf){
			
			try {
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT CPF FROM usuario");
				
					while(rs.next()){
						String ComparaCPF = rs.getString("CPF");
						if(ComparaCPF == cpf)
							return 1;
					}
				stmt.close();
				rs.close();
				}catch (SQLException e) {
					e.printStackTrace();
				}
			return 0;	
		}

		
		
		//DELEÇAO DE LOCAÇÃO POR CPF
		public static int DeleteBancoLocaçao(String cpf, Connection con){
			String delete = "DELETE FROM locaçao WHERE CPF = ?";
			try {
				PreparedStatement stmt = con.prepareStatement(delete);
				stmt.setString(1, cpf);
				return stmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
				return -1;
			}
			
		}
		
		//SOBRECARGA DE DELEÇAO DA LOCAÇAO POR CADEIRA
		public int DeleteBancoLocaçao(int numCadeira, Connection con){
			String delete = "DELETE FROM locaçao where NumCadeira = ?";
			try {
				PreparedStatement stmt = con.prepareStatement(delete);
				stmt.setInt(1, numCadeira);
				return stmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
				return -1;
			}
			
		}
}

