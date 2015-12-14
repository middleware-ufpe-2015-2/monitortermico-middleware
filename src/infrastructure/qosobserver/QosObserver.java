package infrastructure.qosobserver;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public abstract class QosObserver implements IQosObserver {

	@Override
	public Date tempo1() throws IOException, InterruptedException {
		 
		SimpleDateFormat sdf = new SimpleDateFormat("HHmmssSSS");
		//Pega o tempo 1, salva no arquivo e retorna para o Invoker
		Date hora = Calendar.getInstance().getTime();
		//Salvar o "hora" no arquivo
		String hr = sdf.format(hora);
		Calendar date = Calendar.getInstance();
		return date;
	}

	@Override
	//Recebe o tempo 1 para comparar com o 2 e resultar em um tempo total
	public void tempo2(Date date) throws IOException, InterruptedException{
		SimpleDateFormat returnDateSF = new SimpleDateFormat("HHmmssSSS");  
		SimpleDateFormat sdf2 = new SimpleDateFormat("HHmmssSSS");
		
		//Pega o tempo 2 e salva no arquivo
		Date hora2 = Calendar.getInstance().getTime();
		//Salvar o "hora2" no arquivo
		String hr2 = sdf.format(hora2);
		Calendar endDate = Calendar.getInstance();  
		endDate.setTime(sdf2.parse(hr2));
		
		//Pega o tempo 2 e diminui do tempo 1
		//Resultado sai em Milisegundos
		endDate.add(Calendar.HOUR_OF_DAY, -date.get(Calendar.HOUR_OF_DAY));  
		endDate.add(Calendar.MINUTE, -date.get(Calendar.MINUTE));  
		endDate.add(Calendar.SECOND, -date.get(Calendar.SECOND));  
		endDate.add(Calendar.MILLISECOND, -date.get(Calendar.MILLISECOND));  

		String endTime = returnDateSF.format(endDate.getTime());
		int tempoTotal = Integer.parseInt(endTime);
		//resultado em inteiro para colocar no gráfico
	}

}