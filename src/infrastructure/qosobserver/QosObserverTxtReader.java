package infrastructure.qosobserver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.JOptionPane;

public class QosObserverTxtReader {

	public static String medeTempo() {
		String tempos;
		String mostra = "";
		String nomeArq = "../qos_timetable2.txt"; // Nome do arquivo, pode ser
													// absoluto, ou pastas
													// /dir/teste.txt
		String linha = "";
		File arq = new File(nomeArq);

		// Arquivo existe
		if (arq.exists()) {
			mostra = "Arquivo ' " + nomeArq + " ' , aberto com sucesso! \n";
			// mostra+="Tamanho do arquivo "+Long.toString(arq.length())+"\n";
			// tentando ler arquivo
			try {
				mostra = mostra + "\nMedi��es:\n\n";
				// abrindo arquivo para leitura
				FileReader reader = new FileReader(nomeArq);
				// leitor do arquivo
				BufferedReader leitor = new BufferedReader(reader);

				while (true) {
					linha = leitor.readLine();
					if (linha == null) {
						break;
					}
					mostra += linha + " milissegundos" + "\n";
				}
				leitor.close();
			} catch (Exception erro) {
			}
		tempos = mostra;
		//JOptionPane.showMessageDialog(null, mostra,"Tempos totais das medi��es", 1);
		}
		// Se nao existir
		else
			//JOptionPane.showMessageDialog(null, "Arquivo n�o existe!", "Erro",0);
			tempos = "Arquivo n�o existe!";
		
		return tempos;
	}

}
