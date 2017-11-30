package teatro;
import java.sql.Connection;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Metodos {
	Scanner leitor = new Scanner(System.in);
	Dao dao = new Dao();
	DaoLocaçao locaçao = new DaoLocaçao();
	Connection conexao;
	Açoes açao = new Açoes();
	FunçoesMenu func = new FunçoesMenu();
	public int Menu(){
		int op;
		System.out.println("1- Cadastro de Usuário");
		System.out.println("2- Ocupar Lugar");
		System.out.println("3- Desocupar Lugar");
		System.out.println("4- Exibir Lugares Ocupados");
		System.out.println("5- Exibir Lugares Desocupados");
		System.out.println("6- CONSULTA");
		System.out.println("99- Sair");
		try{
			System.out.print("Digite a opção: ");
			op = leitor.nextInt();
			return op;
		}catch(InputMismatchException e){
			System.out.println("Valor digitado não é um número");
			return -1;
		}catch(NullPointerException e){
			System.out.println("Erro enquanto escolhe no menu");
			return -1;
		}
	}
	
	public int RealizaFuncao(int menu){
		switch(menu){
			case 1:
				açao.CadastroUsuario();
				açao.Pause();
				break;
			case 2:
				System.out.println("\n\n\nOcupa Lugar");
				açao.OcupaLugar();
				açao.Pause();
				break;
			case 3:
				System.out.println("\n\n\nDesocupar Lugar: ");
				açao.DesocupaLugar();
				açao.Pause();
				break;
			case 4:
				System.out.println("\n\n\nExibir Lugares Ocupados");
				açao.ExibirLugaresOcupados();
				açao.Pause();
				break;
			case 5:
				System.out.println("\n\n\nExibir Lugares Desocupados");
				açao.ExibirLugaresDesocupados();
				açao.Pause();
				break;
			case 6:
				System.out.println("\n\nConsulta por CPF");
				açao.ConsultaPorCPF();
				break;
			case 99:
				System.out.println("\n\n\nObrigado e volte sempre!!");
				return -1;
			default:
				System.out.println("Valor digitado inválido");
				
		}
		return 0;
	}
	
}
