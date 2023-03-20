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
      Crud.Search(raf,4);
      Crud.Search(raf,3);

      Crud.Update(raf, 1, 1, "test");
      System.out.println(Crud.Search(raf,1));
      

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