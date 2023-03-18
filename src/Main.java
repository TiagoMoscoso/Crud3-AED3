import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.DataOutputStream;

public class Main {
	
  public static void main(String[] args) {
	//READ ALL / LE TUDO
	  
    BufferedReader br = null;
    FileOutputStream fil = null;
    DataOutputStream dos = null;
    //BR - acessa o arquivo base csv
    //RAF - cria/acessa o arquivo db
    
    String linha = new String();
    
    try {

    	br = new BufferedReader(new FileReader("netflix_titles_2021.csv")); 
    	fil = new FileOutputStream("netflix.db");
    	dos = new DataOutputStream(fil);
    	TableInfo tb = new TableInfo();
        while ((linha = br.readLine()) != null){
        	
            tb.setALL(linha.split(";"));			
            	
            String temp = tb.BigString();
            dos.writeUTF(Integer.toHexString(tb.ID));
            dos.writeUTF(temp);
            tb.printAll();
        }
        
    } catch (IOException e) {
		e.printStackTrace();	// em caso de erro
	}
    
    try {
        br.close();
        fil.flush();
        dos.close();
        //fecha arquivos
        } catch (IOException e) {
        e.printStackTrace();
        }
  }
}