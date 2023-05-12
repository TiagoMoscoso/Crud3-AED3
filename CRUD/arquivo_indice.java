import java.io.RandomAccessFile;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class arquivo_indice {
    public static void main(String args[]) throws IOException {
        RandomAccessFile rnd = new RandomAccessFile("netflix.db", "r");
        FileOutputStream fil = new FileOutputStream("indexacao.db");
        DataOutputStream dos = new DataOutputStream(fil);
        try {
            rnd.readInt();
            while (true) 
            {
                long ponteiro_atual = rnd.getFilePointer();
                int tamanho = rnd.readInt();// lê indicador de tamanho;
                rnd.readUTF();// lê lápide
                int id = rnd.readInt();// lê id
                dos.writeInt(id);// escreve o ID no arquivo de indexacao
                dos.writeLong(ponteiro_atual);// escreve a posicao no arquivo de indexacao
                rnd.seek(ponteiro_atual);
                rnd.seek(ponteiro_atual + tamanho + 4);

            }
        } catch (Exception eoException) {
            System.out.println("Arquivo de Indexação Finalizado");
            rnd.close();
            dos.close();
        }

    }
}
