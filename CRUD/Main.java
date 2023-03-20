import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException{
        Cria_Arquivo aux = new Cria_Arquivo();
        aux.cria_db();
        Create create = new Create("Movie", "Interstellar", "Kip Thorne",
        "Pedro Caillaux, Tiago Moscoso, Rafael Vicente", "Brasil",
        "Marco 26, 2023", "2020", "TOP","90min");
        
        /*try{
            System.out.println("------------------");
            Read read = new Read(4);
        }catch(Exception EOFException){
            System.out.println("------------------");
        }
        System.out.println("------------------");*/

        Delete delete = new Delete(1);

    }
}
