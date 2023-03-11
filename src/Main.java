import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Main {
	
  public static void main(String[] args) {
	//READ ALL / LE TUDO
    BufferedReader br = null;
    RandomAccessFile raf = null;
    
    String linha = "";
    try {

    	br = new BufferedReader(new FileReader("src/netflix_titles_2021.csv"));
    	raf = new RandomAccessFile("netflix.db", "rw");
        while ((linha = br.readLine()) != null){
        	
            String archive[] =  new String[10];
            archive = linha.split(";");
            TableInfo tb = new TableInfo();
            
            tb.setALL(archive);
            
            raf.write(tb.BigString().getBytes());
            
            tb.printAll();
            
        }

    } catch (IOException e) {
		e.printStackTrace();
	}
    
    if (br != null) {
    try {
        br.close();
        } catch (IOException e) {
        e.printStackTrace();
        }
    }
    try {
		raf.close();
	} catch (IOException e) {

		e.printStackTrace();
	}
  }
}