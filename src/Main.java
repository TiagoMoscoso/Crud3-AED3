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
    //BR - acessa o arquivo base csv
    //RAF - cria/acessa o arquivo db
    
    String linha = "";
    
    try {

    	br = new BufferedReader(new FileReader("src/netflix_titles_2021.csv")); 
    	raf = new RandomAccessFile("netflix.db", "rw");
    	
        while ((linha = br.readLine()) != null){
        	//Lee arquivo csv e transcreve para classe TableInfo que depois transcreve para o db
        	
            String archive[] =  new String[10];
           
            archive = linha.split(";");				// ";" separador ultilizado pelo csv
            
            TableInfo tb = new TableInfo();			// cria objeto tb para transcrever os dados de csv pra db
            
            
            tb.setALL(archive);						//define todo objeto tb com dados do arquivo
            
            raf.write(tb.BigString().getBytes());	//passa objeto tb para arquivo ja configurado 
            
            tb.printAll();							//printa as variaveis do objeto tb
            
        }

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