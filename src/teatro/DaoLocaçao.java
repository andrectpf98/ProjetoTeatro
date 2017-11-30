package teatro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DaoLoca�ao {
	
	//INSERT DEFAULT DA LOCA�AO
		public static int InsertBancoLoca�ao(String cpf,int numCadeira,Connection con){
			  int rows =0;
			while(rows != -1){
				
				String insert = "INSERT INTO loca�ao(CPF, NumCadeira) VALUES(?,?)";
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
		
		//METODO UTILIZADO PARA INICIAR AS LIST OCUPADAS NO COME�O DO PROGRAMA
		public List<Integer> SelectLoca�aoIniciaList(Connection con){
			List<Integer> ocupadas = new ArrayList<Integer>();
			try{
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("Select NumCadeira FROM loca�ao");
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
		public static List<Integer> SelectBancoLoca�aoDesocupa(Connection con, String cpf){
			List <Integer> listInt = new ArrayList<Integer>();
			try{
				String select = "SELECT * FROM loca�ao WHERE CPF = ? ";
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
		
		//PRINT DAS CADEIRAS OCUPADAS DO BANCO LOCA�AO
		public static void SelectBancoLoca�aoOcupadas(Connection con){
			
			try {
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT NumCadeira FROM loca�ao");
				
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
		
		//SOBRECARGA PARA COMPARAR O CPF DA LOCA�AO COM O DO USUARIO
		public static int SelectComparaCPFLoca�aoUsuario(Connection con, String cpf){
			
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

		
		
		//DELE�AO DE LOCA��O POR CPF
		public static int DeleteBancoLoca�ao(String cpf, Connection con){
			String delete = "DELETE FROM loca�ao WHERE CPF = ?";
			try {
				PreparedStatement stmt = con.prepareStatement(delete);
				stmt.setString(1, cpf);
				return stmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
				return -1;
			}
			
		}
		
		//SOBRECARGA DE DELE�AO DA LOCA�AO POR CADEIRA
		public int DeleteBancoLoca�ao(int numCadeira, Connection con){
			String delete = "DELETE FROM loca�ao where NumCadeira = ?";
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

