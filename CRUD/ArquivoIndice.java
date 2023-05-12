import java.io.RandomAccessFile;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ArquivoIndice{
    
    public void cria_arquivo() throws IOException {
        RandomAccessFile rnd = new RandomAccessFile("netflix.db", "r");
        FileOutputStream fil = new FileOutputStream("indexacao.db");
        DataOutputStream dos = new DataOutputStream(fil);
        try {
            String lapide;
            rnd.readInt();//pula o cabecalho
            while (true) {
                long ponteiro_atual = rnd.getFilePointer();
                int tamanho = rnd.readInt();// lê indicador de tamanho;
                lapide = rnd.readUTF();// lê lápide
                int id = rnd.readInt();// lê id
                if (!(lapide.equals("*"))){
                    dos.writeInt(id);// escreve o ID no arquivo de indexacao
                    dos.writeLong(ponteiro_atual);// escreve a posicao no arquivo de indexacao
                }
                rnd.seek(ponteiro_atual);
                rnd.seek(ponteiro_atual + tamanho + 4);

            }
        } catch (Exception eoException) {
            System.out.println("Arquivo de Indexação Finalizado");
            rnd.close();
            dos.close();
        }

    }
    public long procura_indice(int id){
        try{
            RandomAccessFile raf = new RandomAccessFile("indexacao.db", "r");
            raf.seek(0);
            int id_arquivo;
            long posicao;
            while (true){
                id_arquivo = raf.readInt();
                posicao = raf.readLong();
                if (id_arquivo == id){
                    raf.close();
                   return posicao;
                }
            }
            
        }catch(Exception eOEFfileException){
            System.out.println("Não foi encontrado nenhum ID.");
            return 0;
        }
        
    }
}
