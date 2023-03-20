import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

public class Cria_Arquivo{
	private BufferedReader br;
	private FileOutputStream fil;
	private DataOutputStream dos;
	private RandomAccessFile rnd;

	Cria_Arquivo(){
		br = null;
		fil = null;
		dos = null;
		rnd = null;
	}


  	public void cria_db() throws FileNotFoundException {
		
		
		String linha = "";
		byte[] vet_byte;
		TableInfo tb = new TableInfo();
		
		try {

			br = new BufferedReader(new FileReader("netflix_titles_2021.csv")); 
			fil = new FileOutputStream("netflix.db");
			dos = new DataOutputStream(fil);
			rnd = new RandomAccessFile("netflix.db", "rw");

			dos.writeInt(0);
			br.readLine();
			int cont=1;
			
			while ((linha = br.readLine()) != null && cont<=2){
				tb.setALL(linha.split(";"));

				vet_byte = tb.converte_bytearray();
				dos.writeInt(vet_byte.length);
				dos.write(vet_byte);
				
				rnd.seek(0);
				rnd.writeInt(cont);
				cont++;			
			}

			br.close();
			fil.close();
			dos.close();
			rnd.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
		
    
  	}
  
}