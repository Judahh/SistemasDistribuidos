package sistemasdistribuidos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.swing.JFileChooser;

public class CompactadorSerial {

	public static void main(String[] args) {

		long endTime, initTime = System.currentTimeMillis();

		System.out.println("Compactador sequencial.");

		JFileChooser jfc = new JFileChooser();
		jfc.setMultiSelectionEnabled(true);
		jfc.setDialogTitle("Seleção de arquivos");
		jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		jfc.showOpenDialog(null);

		File[] conteudo = jfc.getSelectedFile().listFiles();// new
															// File("E:\\temp").listFiles();
		for (int i = 0; i < conteudo.length; i++) {
			String nomeArquivo = conteudo[i].getPath();
			compactar(nomeArquivo);
		}

		System.out.println("Arquivos compactados com sucesso.");
		
		//teste de geração da planilha
		Planilha planilha = new Planilha();
		try {
			planilha.gerar(jfc.getSelectedFile().toString(), 2, 2);
		} catch (IOException e) {
			System.err.println("Erro ao gerar planilha. ");
			e.printStackTrace();
		}

		endTime = System.currentTimeMillis();
		System.out.println("FINALIZADO.");
		System.out.println("Tempo final decorrido: "
				+ exibeEmHoras(endTime - initTime) + "(" + (endTime - initTime)
				+ ")");
	}

	public static void compactar(String nomeArquivo) {
		File arquivo, novoArquivo;
		FileInputStream fis;
		ZipOutputStream zos;
		byte[] dadosArquivo = new byte[nomeArquivo.length()]; // 2048

		arquivo = new File(nomeArquivo);
		if (arquivo.exists()) {
			try {
				fis = new FileInputStream(arquivo);
				novoArquivo = new File(nomeArquivo + ".zip");
				if (novoArquivo.createNewFile()) {
					novoArquivo.delete();
					novoArquivo.createNewFile();
				}
				System.out.print(novoArquivo.getName() + "....");
				zos = new ZipOutputStream(new FileOutputStream(novoArquivo));
				zos.putNextEntry(new ZipEntry(arquivo.getName()));
				while (fis.read(dadosArquivo) > 0) {
					zos.write(dadosArquivo);
					zos.flush();
				}
				fis.close();
				zos.close();
				System.out.println("OK!");
			} catch (FileNotFoundException e) {
				System.out.println("ERRO!");
				System.err.println("Erro ao compactar ou ler arquivo "
						+ nomeArquivo + ".");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("ERRO!");
				System.err.println("Erro ao escrever ou ler dados do arquivo "
						+ nomeArquivo + ".");
				e.printStackTrace();
			}
		} else {
			System.err.println("Arquivo " + nomeArquivo + " não existe.");
		}
	}

	public static String exibeEmHoras(long l) {
		String segundos, minutos, horas, milis, stringTime = "" + l;
		segundos = "" + (l / 1000);
		minutos = "" + (l / 1000) / 60;
		horas = "" + ((l / 1000) / 60) / 60;
		milis = ((l < 1000) ? stringTime : stringTime.substring(stringTime
				.length() - 3, stringTime.length()));
		return horas + ":" + minutos + ":" + segundos + "." + milis;
	}

}
