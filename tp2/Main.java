import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Compressao x = new Compressao();
        x.criaDicionario();
        x.iniciaCompressao();
        x.ganho();
    }
}
