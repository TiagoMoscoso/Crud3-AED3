import java.io.IOException;
import java.io.RandomAccessFile;

public class Delete extends ArquivoIndice{
    RandomAccessFile rnd;
    public Delete(int id) throws IOException{
        long posicao = procura_indice(id);
        rnd = new RandomAccessFile("netflix.db", "rw");
        rnd.seek(posicao);
        rnd.readInt();//lê o tamanho do registro
        rnd.writeUTF("*");//Coloca a lápide
    }
}
