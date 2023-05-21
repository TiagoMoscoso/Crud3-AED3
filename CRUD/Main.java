import java.io.IOException;
import java.nio.charset.Charset;

public class Main {

    public static void main(String args[]) throws IOException
    {
        
        ArquivoIndice z = new ArquivoIndice();
        z.cria_arquivo();
    
        Compressao y = new Compressao();
        y.iniciaCompressao();
        
        Descompressao x = new Descompressao();
        //x.iniciaDescompressao();
        x.ganho(1);
    }

}
