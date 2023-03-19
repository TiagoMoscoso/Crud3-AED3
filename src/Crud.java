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
                dos.writeUTF(Integer.toHexString(tb.ID));
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
        int where = 0;
        try{
            raf.seek(0);
            String line = raf.readUTF();
            while (line != null){
                
                if(where == 0)
                {
                    tb.setID(Integer.parseInt(line,16));
                    where ++;
                }
                else{
                    tb.DecompresString(line);
                    System.out.println("meu id: " + tb.ID);
                    where = 0;
                }
                line = raf.readUTF();
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void Uptade(RandomAccessFile raf, int SearchID, int x, String text){
        //WORK
        TableInfo tb = new TableInfo();
        try {
            long pos = Search(raf,SearchID,tb);
            raf.seek(pos);
            tb.DecompresString(raf.readUTF());
            tb.update(x,text);
            raf.seek(pos);
            raf.writeUTF(tb.BigString());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static void Delete(RandomAccessFile raf, int SearchID){
        //DONT WORK
        TableInfo tb = new TableInfo();
        try {
            long pos = Search(raf,SearchID,tb);
            raf.seek(pos);
            tb.DecompresString(raf.readUTF());
            tb.Delete();
            raf.seek(pos);
            raf.writeUTF(tb.BigString());
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
            raf.skipBytes(3);
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
        int where = 0;
        int posId = 0;
        int maxtam = GetTam(raf);
        boolean found = false;
        if(maxtam > SearchID){
        try{
            raf.seek(0);
            String line = raf.readUTF();
            while (line != null && found != true){
                if(where == 0)
                {
                    posId = Integer.parseInt(line,16);
                    if(posId >= SearchID)
                    {   
                        ponteiro = raf.getFilePointer();
                        tb.DecompresString(line);
                        found = true;
                        if(tb.del == true)
                        {
                            posId++;
                        }
                    }
                    where ++;
                }
                else{
                    where=0;
                }
                line = raf.readUTF();
            }
            
            if(posId != SearchID)
            {
                System.out.println("Nao achei o : " + SearchID);
                ponteiro = -1;
            }
            else{
                System.out.println("Achei o : " + SearchID);
                tb.DecompresString(line);
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
        long ponteiro = -1;
        int where = 0;
        int posId = 0;
        int maxtam = GetTam(raf);
        boolean found = false;
        TableInfo tb = new TableInfo();
        if(maxtam >= SearchID){
        try{
            raf.seek(0);
            String line = raf.readUTF();
            System.out.println(line);
            while (line != null && found != true){
                if(where == 0)
                {
                    posId = Integer.parseInt(line,16);
                    if(posId >= SearchID)
                    {   
                        ponteiro = raf.getFilePointer();
                        tb.DecompresString(line);
                        found = true;
                        System.out.println(tb.del);
                    }
                    where ++;
                }
                else{
                    where=0;
                }
                line = raf.readUTF();
            }
            
            if(posId != SearchID)
            {
                System.out.println("Nao achei o : " + SearchID);
                ponteiro = -1;
            }
            else{
                System.out.println("Achei o : " + SearchID);
                tb.DecompresString(line);
                tb.printAll();
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        }
        return ponteiro;  
    }
}
