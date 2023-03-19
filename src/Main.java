import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.DataOutputStream;

public class Main {
	
  public static void main(String[] args) {
	  //READ ALL / LE TUDO
    BufferedReader br = null;

    FileOutputStream fileOut = null;
    DataOutputStream dos = null;
    //Dos cria arquivos db

    RandomAccessFile raf = null;
    //RAF acessa o arquivos db
    
    try {
    	br = new BufferedReader(new FileReader("netflix_titles_2021.csv")); 
    	fileOut = new FileOutputStream("netflix.db");
    	dos = new DataOutputStream(fileOut);

      raf = new RandomAccessFile("netflix.db", "rw");
    	Crud.CreateArch(br, dos);

      Crud.Read(raf);
      System.out.println(Crud.GetTam(raf));

      Crud.Uptade(raf,2,1,"Movie");
      Crud.Uptade(raf,2,2,"Filme");
      Crud.Uptade(raf,2,3,"Piçola");
      Crud.Uptade(raf,2,4,"Daddy");
      Crud.Uptade(raf,2,5,"Romenia");
      Crud.Uptade(raf,2,6,"2023");
      Crud.Uptade(raf,2,7,"2022");
      Crud.Uptade(raf,2,8,"+18");
      Crud.Uptade(raf,2,9,"5 segundos");
      Crud.Delete(raf, 2); //BUG DELETE?
      Crud.Search(raf,2);

    } catch (IOException e) {
		e.printStackTrace();	// em caso de erro
	}
    
    try {
        br.close();
        fileOut.flush();
        fileOut.close();
        dos.close();
        //fecha arquivos
        } catch (IOException e) {
        e.printStackTrace();
        }
  }
}