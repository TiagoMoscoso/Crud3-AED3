import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;


public class Main {
	
  public static void main(String[] args)  throws IOException{
	  //READ ALL / LE TUDO
    BufferedReader br = null;
    
    RandomAccessFile raf = null;
    //RAF acessa o arquivos db
    
    try {
    	br = new BufferedReader(new FileReader("netflix_titles_2021.csv")); 
      
      raf = new RandomAccessFile("netflix.db", "rw");
      Crud.CreateArch(br, raf);
      Crud.Read(raf);
      System.out.println(Crud.GetTam(raf));
      
      Crud.Search(raf,4);
      Crud.Search(raf,3);
      System.out.println("////////////////////////");
      TableInfo tb = new TableInfo();
      Crud.Create(raf, tb);

      Crud.Update(raf, 1, 1, "test");
      
      Crud.Search(raf,1);
      Crud.Delete(raf,1);
      Crud.Search(raf,1);

    } catch (IOException e) {
		e.printStackTrace();	// em caso de erro
	}
    
    try {
        br.close();
        raf.close();
        //fecha arquivos
        } catch (IOException e) {
        e.printStackTrace();
        }
  }
}