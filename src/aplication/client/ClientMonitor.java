package aplication.client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import aplication.IMonitor;
import aplication.Medicao;
import aplication.TipoGrandeza;

public class ClientMonitor {

	public static void main(String[]args) throws RemoteException, NotBoundException{
		
		//TODO: utilizar o servico de nomes a ser desenvolvido
		Registry registry = LocateRegistry.getRegistry("localhost", 8080);
		
		//TODO: subsituir pelo padrao lookup
		IMonitor monitor = (IMonitor) registry.lookup("Monitor");
		
		while(true){
			
			Medicao m1 = monitor.getMedicao(TipoGrandeza.TEMPERATURA);
			System.out.println("Temperatura: "+m1.getValue()+ ", Unidade: "+m1.getUnidade());
			
			
			Medicao m2 = monitor.getMedicao(TipoGrandeza.UMIDADE);
			System.out.println("Temperatura: "+m2.getValue()+ ", Unidade: "+m2.getUnidade());
			
			
		}
		
	}
	
	
}
