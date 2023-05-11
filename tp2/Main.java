import java.io.IOException;
import java.nio.charset.Charset;

public class Main {
    public static void main(String[] args) throws IOException {
        System.setProperty("file.encoding", Charset.forName("UTF-8").name());
        Compressao x = new Compressao();
        /*x.criaDicionario();
        x.iniciaCompressao();
        x.test();*/
        Descompressao y = new Descompressao();
        y.criaDicionario();
        y.iniciaDescompressao();
    }
}
