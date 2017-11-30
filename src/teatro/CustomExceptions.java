package teatro;

import java.sql.Connection;
import java.util.List;

import javax.naming.SizeLimitExceededException;

public class CustomExceptions extends Exception{
	
	public void MaiorQue100(int valor) throws SizeLimitExceededException{
		if(valor > 100)
			throw new SizeLimitExceededException();
	}
	
	public void CPFGrande(String cpf) throws SizeLimitExceededException{
		if(cpf.length() > 11)
			throw new SizeLimitExceededException();
	}
	
	public void ChecaCPFCadastrado(String cpf,List<String> list,Connection con) throws Exception{
		for(int i=1; i <=list.size();i++){
			if(cpf == list.get(i)){
				throw new Exception();
			}
		}
	}
	
	
	

}