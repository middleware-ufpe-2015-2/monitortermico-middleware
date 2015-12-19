package distribution.clientproxy;
import java.util.*;

public class TableExcpetions {
	
	private HashMap<Integer, String> tabelaExcecoes = new HashMap<Integer, String>();

	
	public void iniciarTabela(){
		
		tabelaExcecoes.put(400, "UnknownHostException" );
		tabelaExcecoes.put(405, "IOException" );
		tabelaExcecoes.put(410, "ClassNotFound" );
		tabelaExcecoes.put(415, "InterruptedException" );
	}
	
	
	public ErroMiddleware buscarError(Integer codigo){
		
		ErroMiddleware error = null; 
		
		iniciarTabela();
		
		if(codigo != null){
			
			error = new ErroMiddleware();
			
			error.setCodeError(codigo);
			
			error.setDescription(tabelaExcecoes.get(codigo));		
			
		}
		
		return error;
	}
	
}
