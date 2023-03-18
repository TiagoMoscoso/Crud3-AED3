import java.io.BufferedReader;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
public class Main {
	
  public static void main(String[] args) {
	//READ ALL / LE TUDO
    BufferedReader br = null;
    FileOutputStream fileOut = null;
    InputStream fileIn = null;
    DataOutputStream dos = null;
    DataInputStream dis = null;
    //BR - acessa o arquivo base csv
    //RAF - cria/acessa o arquivo db
    
    try {

    	br = new BufferedReader(new FileReader("netflix_titles_2021.csv")); 
    	fileOut = new FileOutputStream("netflix.db");
    	dos = new DataOutputStream(fileOut);
    	Crud.Create(br, dos);
    	
    	fileIn = new FileInputStream("netflix.db");
    	dis = new DataInputStream(fileIn);

      Crud.Read(dis);
        
    } catch (IOException e) {
		e.printStackTrace();	// em caso de erro
	}
    
    try {
        br.close();
        fileOut.flush();
        fileOut.close();
        fileIn.close();
        dis.close();
        dos.close();
        //fecha arquivos
        } catch (IOException e) {
        e.printStackTrace();
        }
  }
}