package teatro;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import javax.naming.SizeLimitExceededException;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

//FUNÇOES COMPLEMENTARES DO MENU

public class FunçoesMenu {
	Scanner leitor = new Scanner(System.in);
	CustomExceptions ce = new CustomExceptions();
	DaoUsuario usuario = new DaoUsuario();
	DaoLocaçao locaçao = new DaoLocaçao();
	List<String> lista = new ArrayList<String>();
	List<Integer> listaInt = new ArrayList<Integer>();
	
	//INSERIR CPF PARA O CADASTRO DO USUARIO
	public String InserirCPFUsuario(String cpf){
		boolean bNum = false;
		while(!bNum){
			try{
				System.out.print("Digite o seu CPF: ");
				cpf = leitor.next();
				LimpaBuffer();
				ce.CPFGrande(cpf);
				bNum = ChecagemCPF(cpf);
			
			}catch(SizeLimitExceededException e){
				System.out.println("CPF grande demais pra ser CPF");
			}
		}
		return cpf;
	}
	
	//O que o nome diz
	public String InserirCPFLocaçao(String cpf,Connection con){
		boolean insereCpfLocaçao = true;
		int continua = 0;
		while(insereCpfLocaçao){
			
				System.out.print("Digite o CPF para ocupar local: ");
				cpf = leitor.next();
				LimpaBuffer();
				lista = usuario.ListaDeUsuariosCPF(con);
				if(lista.contains(cpf)){
					insereCpfLocaçao = false;
				}else{
					System.out.println("CPF não cadastrado");
					System.out.println("Deseja sair? 1-sim 2-nao");
					continua = leitor.nextInt();
					if(continua == 1)
						cpf = null;
						break;
				}
		}	
		return cpf;
	}
	//Inicializaçao do List de lugares não ocupados
		public List<Integer> iniciaListaDesocupadas(){
			List<Integer> desocupadas = new ArrayList<Integer>();
			for(int i = 0;i<=100;i++){
				desocupadas.add(i);
			}
			return desocupadas;
		}
		//IMPRIMIR LUGARES DESOCUPADOS L1 = LISTA OCUPADAS / L2 = LISTA DESOCUPADAS
		public void imprimeLugaresDesocupados(List<Integer> l1, List<Integer> l2){
			l2.removeAll(l1);
			for(int i = 0; i<l2.size();i++){
				System.out.print(l2.get(i)+" ");
			}
		}
		
	
	public String InserirNome(String nome){
		boolean bLetra = true;
		while(bLetra){
			System.out.print("Digite o seu Nome: ");
			nome = leitor.nextLine();
			bLetra = ChecagemNome(nome);
			if(bLetra){
				System.out.println("Nome contendo numeros");
			}
			nome = Uppercase(nome);
		}
		return nome;
	}
	
	public int InserirNumCadeira(int numCadeira, Connection con){
		boolean bValidaCadeira = true;
		int continua;
		while(bValidaCadeira){
			System.out.print("Digite o numero da cadeira de 0-100: ");
			try{	
				
				listaInt = locaçao.SelectLocaçaoIniciaList(Dao.getConnection());
				numCadeira = leitor.nextInt();
				LimpaBuffer();
				ce.MaiorQue100(numCadeira);
				
				if(!listaInt.contains(numCadeira)){
					bValidaCadeira = false;
				}else{
					System.out.println("Num Cadeira já ocupada");
					System.out.println("Deseja sair? 1-sim 2-nao");
					continua = leitor.nextInt();
					if(continua == 1){
						numCadeira = 0;
						break;
					}
				}	
			}catch(InputMismatchException e){
				System.out.println("Erro na inserção do valor: VALOR É OU POSSUI CARACTERES");
				LimpaBuffer();
				
			}catch(SizeLimitExceededException e){//CUSTOMEXCEPTION
				System.out.println("Valor digitado maior que cem");
				LimpaBuffer();
			}
		}
		
		return numCadeira;
		
	}
	
	public String Uppercase(String s){
		StringBuilder novaString = new StringBuilder(s);
		
		//Maisculo na primeira letra do nome
		String novoNome = novaString.substring(0, 1).toUpperCase() + novaString.substring(1);
		
		return novoNome;
	}
	
	//CHECAGEM CPF RETURN==FALSE(CPF COM LETRAS)
	public boolean ChecagemCPF(String s){
		try{
			Long.parseLong(s);
			return true;
		}catch(NumberFormatException e){
			System.out.println("O valor inserido contem letras");
			return false;
		}
		
	}
	
	
	//Checagem do nome RETURN==TRUE(NOME COM NUMEROS) 
	public boolean ChecagemNome(String s){
		
		return s.matches(".*\\d+.*");
	}
	
	public void LimpaBuffer(){
		leitor.nextLine();
	}
}
