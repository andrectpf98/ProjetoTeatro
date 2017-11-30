package teatro;


import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;

public class Açoes {
	Scanner leitor = new Scanner(System.in);
	FunçoesMenu menu = new FunçoesMenu();
	Connection conexao;
	String cpf;
	String nome;
	int numCadeira;
	CustomExceptions ce = new CustomExceptions();
	DaoLocaçao locaçao = new DaoLocaçao();
	DaoUsuario usuario = new DaoUsuario();
	List<Integer>listaInt = new ArrayList<Integer>();
	List<String>listaString = new ArrayList<String>();
	
	public void CadastroUsuario(){
		
		
		System.out.println("Cadastro do Usuário");
		conexao = Dao.getConnection();
		
		cpf = menu.InserirCPFUsuario(cpf);
		if(usuario.ListaDeUsuariosCPF(conexao).contains(cpf)){
			System.out.println("CPF já cadastrado");
			cpf = menu.InserirCPFUsuario(cpf);
		}

		nome = menu.InserirNome(nome);
		
		usuario.InsertBancoCadastro(cpf, nome, conexao);
		 
		Dao.closeConnection(conexao);
	}
	
	public int OcupaLugar(){
		conexao = Dao.getConnection();
		
		cpf = menu.InserirCPFLocaçao(cpf, conexao);
		if(cpf == null)
			return 0;
		
		numCadeira = menu.InserirNumCadeira(numCadeira,conexao);
		if(numCadeira == 0)
			return 0;
		
		locaçao.InsertBancoLocaçao(cpf, numCadeira, conexao);
		 
		Dao.closeConnection(conexao);
		return 1;
	}
	
	public int DesocupaLugar(){
		conexao = Dao.getConnection();
		int desocupa =0;
		boolean desocupaLugar = true;
		while(desocupaLugar){
			System.out.print("Digite seu CPF: ");
			String buscaCPF = leitor.nextLine();
			if(!usuario.ListaDeUsuariosCPF(conexao).contains(buscaCPF)){
				System.out.println("Usuario não cadastrado");
				return 0;
			}
			listaInt = locaçao.SelectBancoLocaçaoDesocupa(conexao, buscaCPF);
			if(listaInt.isEmpty()){
				System.out.println("Nenhuma cadeira alocada por esse CPF");
				Pause();
				return 0;
			}
			System.out.print("Digite o numero da cadeira para desocupar: ");
			desocupa = leitor.nextInt();
			if(listaInt.contains(desocupa)){
				break;
			}else{
				System.out.println("Numero digitado não está nos acima");
				Pause();
				return 0;
			}
			
		}
		locaçao.DeleteBancoLocaçao(desocupa, conexao);
		Dao.closeConnection(conexao);
		return 1;
	}
	
	public void ExibirLugaresOcupados(){
		conexao = Dao.getConnection();
		System.out.println("Cadeiras Ocupadas:");
		
		locaçao.SelectBancoLocaçaoOcupadas(conexao);
		System.out.println();
		
		 
		Dao.closeConnection(conexao);
	}
	
	public void ExibirLugaresDesocupados(){
		conexao = Dao.getConnection();
		
		System.out.println("Lugares Desocupados: ");
		menu.imprimeLugaresDesocupados(locaçao.SelectLocaçaoIniciaList(conexao),menu.iniciaListaDesocupadas());
		System.out.println();
		
		Dao.closeConnection(conexao);
		
	}
	
	
	public int ConsultaPorCPF(){
		conexao = Dao.getConnection();
		listaString = usuario.ListaDeUsuariosCPF(conexao);
		cpf = leitor.nextLine();
		if(!listaString.contains(cpf)){
			System.out.println("CPF não cadastrado");
			return 1;
		}
		menu.LimpaBuffer();
		locaçao.SelectBancoLocaçaoDesocupa(conexao,cpf);
		Dao.closeConnection(conexao);
		return 0;
	}
	
	public void Pause(){
		try {
			System.out.println("Pressione enter para continuar...");
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
