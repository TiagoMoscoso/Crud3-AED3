import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Crud {
    public static void Create(BufferedReader br, DataOutputStream dos){
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
    public static void Read(DataInputStream dis){
        TableInfo tb = new TableInfo();
        int where = 0;
        try{
            while (dis.available() != 0){
                
                if(where == 0)
                {
                    tb.setID(Integer.parseInt(dis.readUTF(),16));
                    //System.out.println(Integer.parseInt(dis.readUTF(),16));
                    where ++;
                }
                else{
                    tb.DecompresString(dis.readUTF());
                    where = 0;
                }
                
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void Uptade(){
        
    }
    public void Delete(){
        
    }
    public void Search(){
        
    }
}
