package teatro;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class Principal{
	
	public static void main(String[] args){
		int i = 0;
		while(i != 99){
			Metodos met = new Metodos();
			i = met.Menu();
			met.RealizaFuncao(i);
			
		}

	}
}