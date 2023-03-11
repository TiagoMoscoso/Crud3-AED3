import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {
	
  public static void main(String[] args) {
	//READ ALL / LE TUDO
    BufferedReader br = null;
    String linha = "";
    try {

    	br = new BufferedReader(new FileReader("src/netflix_titles_2021.csv"));
        while ((linha = br.readLine()) != null){
        	
            String archive[] =  new String[10];
            archive = linha.split(";");
            TableInfo tb = new TableInfo();
            
            tb.setALL(archive);
            
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
	//READ ALL / LE TUDO
	  
	//CREATE
	  
	//CREATE
	  // id(auto) - 
  }
}