package sistemasdistribuidos;

import java.io.File;

import javax.swing.JFileChooser;

public class CompactadorParalelo {

   public static void main(String[] args) {
      long endTime, initTime = System.currentTimeMillis();

      System.out.println("Compactador paralelo.");

      JFileChooser jfc = new JFileChooser();
      jfc.setMultiSelectionEnabled(true);
      jfc.setDialogTitle("Seleção de arquivos");
      jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
      jfc.showOpenDialog(null);

      String nomeArquivo;
      try {
         File[] conteudo = jfc.getSelectedFile().listFiles();

         Thread[] threads = new Thread[conteudo.length];

         for (int i = 0; i < conteudo.length; i++) {
            nomeArquivo = conteudo[i].getPath();
            threads[i] = new Thread(new Compactador(nomeArquivo));
            threads[i].start();
         }
         for (Thread thread : threads) {
            try {
               thread.join();
            } catch (InterruptedException e) {
               e.printStackTrace();
            }
         }
         System.out.println("Arquivos compactados com sucesso.");
         endTime = System.currentTimeMillis();
         System.out.println("FINALIZADO.");
         System.out.println("Tempo final decorrido: "
                 + Compactador.exibeEmHoras(endTime - initTime) + "("
                 + (endTime - initTime) + ")");
      }catch(Exception exception){
         exception.printStackTrace();
      }
   }
}
