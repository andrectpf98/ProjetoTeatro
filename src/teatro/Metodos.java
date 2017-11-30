package teatro;
import java.sql.Connection;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Metodos {
	Scanner leitor = new Scanner(System.in);
	Dao dao = new Dao();
	DaoLoca�ao loca�ao = new DaoLoca�ao();
	Connection conexao;
	A�oes a�ao = new A�oes();
	Fun�oesMenu func = new Fun�oesMenu();
	public int Menu(){
		int op;
		System.out.println("1- Cadastro de Usu�rio");
		System.out.println("2- Ocupar Lugar");
		System.out.println("3- Desocupar Lugar");
		System.out.println("4- Exibir Lugares Ocupados");
		System.out.println("5- Exibir Lugares Desocupados");
		System.out.println("6- CONSULTA");
		System.out.println("99- Sair");
		try{
			System.out.print("Digite a op��o: ");
			op = leitor.nextInt();
			return op;
		}catch(InputMismatchException e){
			System.out.println("Valor digitado n�o � um n�mero");
			return -1;
		}catch(NullPointerException e){
			System.out.println("Erro enquanto escolhe no menu");
			return -1;
		}
	}
	
	public int RealizaFuncao(int menu){
		switch(menu){
			case 1:
				a�ao.CadastroUsuario();
				a�ao.Pause();
				break;
			case 2:
				System.out.println("\n\n\nOcupa Lugar");
				a�ao.OcupaLugar();
				a�ao.Pause();
				break;
			case 3:
				System.out.println("\n\n\nDesocupar Lugar: ");
				a�ao.DesocupaLugar();
				a�ao.Pause();
				break;
			case 4:
				System.out.println("\n\n\nExibir Lugares Ocupados");
				a�ao.ExibirLugaresOcupados();
				a�ao.Pause();
				break;
			case 5:
				System.out.println("\n\n\nExibir Lugares Desocupados");
				a�ao.ExibirLugaresDesocupados();
				a�ao.Pause();
				break;
			case 6:
				System.out.println("\n\nConsulta por CPF");
				a�ao.ConsultaPorCPF();
				break;
			case 99:
				System.out.println("\n\n\nObrigado e volte sempre!!");
				return -1;
			default:
				System.out.println("Valor digitado inv�lido");
				
		}
		return 0;
	}
	
}
