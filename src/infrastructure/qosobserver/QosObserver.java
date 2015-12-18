package infrastructure.qosobserver;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class QosObserver implements IQosObserver {

	public QosObserver() {
	}
	
	@Override
	public Calendar tempo1() throws IOException, InterruptedException, IOException {
		 
		SimpleDateFormat sdf = new SimpleDateFormat("HHmmssSSS");
		//Pega o tempo 1, salva no arquivo e retorna para o Invoker
		Date hora = Calendar.getInstance().getTime();
		//alterando o formato da data e hora 
		String hr = sdf.format(hora);
		Calendar date = Calendar.getInstance();
		//Salvar o "hora" no arquivo
		BufferedWriter buffWrite = new BufferedWriter(new FileWriter("../qos_timetable.txt",true));
		Date linha = hora;
		buffWrite.append(linha + "\n");
		buffWrite.close();
		
		return date;
	}

	@Override
	//Recebe o tempo 1 para comparar com o 2 e resultar em um tempo total
	public void tempo2(Calendar date) throws IOException, InterruptedException, IOException{
		SimpleDateFormat sdf = new SimpleDateFormat("HHmmssSSS");  
		SimpleDateFormat sdf2 = new SimpleDateFormat("HHmmssSSS");
		
		//Pega o tempo 2 e salva no arquivo
		Date hora2 = Calendar.getInstance().getTime();
		//alterando o formato da data e hora 
		String hr2 = sdf.format(hora2);
		Calendar endDate = Calendar.getInstance();  
		try {
			endDate.setTime(sdf2.parse(hr2));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//Salvar o "hora2" no arquivo
		BufferedWriter buffWrite2 = new BufferedWriter(new FileWriter("../qos_timetable.txt",true));
		String linha = sdf.format(hora2);
		buffWrite2.append(linha + "\n");
		buffWrite2.close();	
		
		//Pega o tempo 2 e diminui do tempo 1
		//Resultado sai em Milisegundos
		endDate.add(Calendar.HOUR_OF_DAY, -date.get(Calendar.HOUR_OF_DAY));  
		endDate.add(Calendar.MINUTE, -date.get(Calendar.MINUTE));  
		endDate.add(Calendar.SECOND, -date.get(Calendar.SECOND));  
		endDate.add(Calendar.MILLISECOND, -date.get(Calendar.MILLISECOND));  
		
		/**
		 * Função para ler arquivo texto (pode ser útil em futuras funcionalidades
		 * 
		 * BufferedReader buffRead = new BufferedReader(new FileReader("../qos_timetable.txt"));
		String linha2 = "";
		while (true) {
			if (linha2 != null) {
				System.out.println(linha2);
			} else
				break;
			linha2 = buffRead.readLine();
		}
		buffRead.close();
		
		 */

		String endTime = sdf.format(endDate.getTime());
		int tempoTotal = Integer.parseInt(endTime);
		//resultado em inteiro para colocar no gráfico
	}
}