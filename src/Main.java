import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Main {

	
	@SuppressWarnings("unlikely-arg-type")
	public static void SearchPos(RandomAccessFile db, Integer search){
		try {
			db.seek(0);
			char charNav = (char) db.read();
			long pos = 1;
			int where = 0;
			StringBuilder sb = new StringBuilder(); 
			
			while(sb.equals(search.toString()) == false || where != 0 ||charNav==';')
			{
				charNav = (char) db.read();
				pos++;
				System.out.println(charNav);
				if(charNav==';')
				{
					
					where++;
					if(where > 2)
					{
						where = 0;
						if(sb.equals("") != true){
							pos = pos + Integer.parseInt(sb.toString(), 16)-1;
							db.seek(pos);
						}
					}
					sb.setLength(0);
				}
				else{
					if(charNav !=' ') {
					sb.append(charNav);
					}
				}
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
  public static void main(String[] args) {
	//READ ALL / LE TUDO
	  
    BufferedReader br = null;
    RandomAccessFile raf = null;
    //BR - acessa o arquivo base csv
    //RAF - cria/acessa o arquivo db
    
    String linha = "";
    
    try {

    	br = new BufferedReader(new FileReader("src/netflix_titles_2021.csv")); 
    	raf = new RandomAccessFile("src/netflix.db", "rw");
    	
        while ((linha = br.readLine()) != null){
        	//Lee arquivo csv e transcreve para classe TableInfo que depois transcreve para o db
        	
            String archive[] =  new String[10];
           
            archive = linha.split(";");				// ";" separador ultilizado pelo csv
            
            TableInfo tb = new TableInfo();			// cria objeto tb para transcrever os dados de csv pra db
            
            
            tb.setALL(archive);						//define todo objeto tb com dados do arquivo
            
            raf.write(tb.BigString().getBytes());	//passa objeto tb para arquivo ja configurado 
            
            //tb.printAll();							//printa as variaveis do objeto tb
            
        }
     SearchPos(raf,100);
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