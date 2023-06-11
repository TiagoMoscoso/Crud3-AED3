import java.io.FileNotFoundException;

public class Main {

    public static void main(String args[]) throws FileNotFoundException
    {
        Cria_Arquivo.cria_db();

        Kmp x = new Kmp();
        x.KMPstart("Steven");
    }

}
