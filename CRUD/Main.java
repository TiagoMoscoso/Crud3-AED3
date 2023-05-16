import java.io.IOException;
import java.nio.charset.Charset;

public class Main {
    public static void main(String[] args) throws IOException {
        //Cria_Arquivo.cria_db();
        System.setProperty("file.encoding", Charset.forName("UTF-8").name());
        Compressao x = new Compressao();
        x.criaDicionario();
        x.iniciaCompressao();
        x.ganho();
        x = new Compressao();
        Descompressao y = new Descompressao();
        y.criaDicionario();
        y.iniciaDescompressao();
    }
}
