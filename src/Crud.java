import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Crud {
    public static void CreateArch(BufferedReader br, DataOutputStream dos){
        //WORK
        TableInfo tb = new TableInfo();
        String linha = new String();
        try{
            while ((linha = br.readLine()) != null){
                tb.setALL(linha.split(";"));			
                String temp = tb.BigString();
                dos.writeUTF(temp);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void Create(RandomAccessFile raf){

    }

    public static void Read(RandomAccessFile raf){
        //WORK
        TableInfo tb = new TableInfo();
        try{
            raf.seek(0);
            String line = raf.readUTF();
            while (line != null){
                
                tb.DecompresString(line);
                System.out.println("meu id: " + tb.ID);
                line = raf.readUTF();
            }
        }catch (IOException e) {
            System.out.println("FIM DA EXECUÇÃO");
        }
    }
    public static void Update(RandomAccessFile raf, int SearchID, int x, String text){
        //WORK
        TableInfo tb = new TableInfo();
        try {
            long pos = Search(raf,SearchID);
            raf.seek(pos);
            tb.DecompresString(raf.readUTF());
            raf.seek(pos);
            tb.update(x,text);
             raf.writeUTF(tb.BigString());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static void Delete(RandomAccessFile raf, int SearchID){
        //
        TableInfo tb = new TableInfo();
        try {
            long pos = Search(raf,SearchID);
            raf.seek(pos);
            tb.DecompresString(raf.readUTF());
            tb.Delete();
            raf.seek(pos);
            raf.write(tb.BigString().getBytes("UTF-8"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static int GetTam(RandomAccessFile raf){
        //WORK
        TableInfo tb = new TableInfo();
        try{
            raf.seek(0);
            tb.DecompresString(raf.readUTF());


        }catch(IOException e) {
            e.printStackTrace();
        }

        int tamanho = Integer.parseInt(tb.Type);
        return tamanho;
    }


    public static long Search(RandomAccessFile raf, int SearchID, TableInfo tb){
        //WORK
        long ponteiro = -1;
        int posId = 0;
        int maxtam = GetTam(raf);
        boolean found = false;
        if(maxtam > SearchID){
        try{
            raf.seek(0);
            String line = raf.readUTF();
            while (line != null && found != true){
                if(posId >= SearchID)
                {   
                    ponteiro = raf.getFilePointer();
                    tb.DecompresString(line);
                    found = true;
                }
                ponteiro = raf.getFilePointer();
                posId++;
                line = raf.readUTF();
            }
            
            if(tb.ID != SearchID && tb.del != true)
            {
                System.out.println("Nao achei o : " + SearchID);
                ponteiro = -1;
            }
            else{
                System.out.println("Achei o : " + SearchID);
                tb.printAll();
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        }
        return ponteiro;  
    }

    public static long Search(RandomAccessFile raf, int SearchID){
        //WORK
        TableInfo tb = new TableInfo();
        long lastponteiro = -1;
        long ponteiro = -1;
        int posId = 0;
        int maxtam = GetTam(raf);
        boolean found = false;
        if(maxtam > SearchID){
        try{
            raf.seek(0);
            String line = raf.readUTF();
            while (line != null && found != true){
                if(posId >= SearchID)
                {   
                    tb.DecompresString(line);
                    found = true;
                }
                posId++;
                lastponteiro = ponteiro;
                ponteiro = raf.getFilePointer();
                line = raf.readUTF();
            }
            
            if(tb.ID != SearchID && tb.del != true)
            {
                System.out.println("Nao achei o : " + SearchID);
                ponteiro = -1;
            }
            else{
                System.out.println("Achei o : " + SearchID);
                tb.printAll();
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        }
        return lastponteiro;  
    }
}
