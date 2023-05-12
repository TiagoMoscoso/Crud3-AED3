import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ArquivoIndice z = new ArquivoIndice();
        z.cria_arquivo();
        Compressao x = new Compressao();
        x.criaDicionario();
        x.iniciaCompressao();
        x.ganho();
        //Update up = new Update(1);
       // Read x = new Read(1);
        
    }
}
