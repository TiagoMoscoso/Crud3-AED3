import java.io.BufferedReader;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Crud extends TableInfo{
    public static void CreateArch(BufferedReader br, RandomAccessFile raf){
        //WORK
        TableInfo tb = new TableInfo();
        String linha = new String();
        byte[] bytes;

        
        try{
            while ((linha = br.readLine()) != null){
                tb.setALL(linha.split(";"));			
                bytes = tb.toBYte();
                raf.writeInt(bytes.length);
                raf.write(bytes);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void Create(RandomAccessFile raf, TableInfo tb) throws IOException{
        //work

        int NewId = Crud.GetTam(raf) + 1;
        raf.seek(0);
        raf.readInt();
        raf.readChar();
        raf.readUTF();
        raf.writeUTF(Integer.toString(NewId));
        raf.seek(0);

        tb.setID(NewId);
        byte[] bytes = tb.toBYte();

        long pos = Search(raf, NewId-1);
        raf.seek(pos);
        pos = pos + raf.readInt() + 4;


        raf.seek(pos);
        raf.writeInt(bytes.length);
        raf.write(bytes);
        
        
    }

    public static void Read(RandomAccessFile raf) throws IOException{
        //WORK
        int pos = 0;
        int id = 0;
        int max = GetTam(raf);
        raf.seek(0);
        int inter;
        System.out.println("Inicio");
        while(id < max){
            inter = raf.readInt();//tamanho registro
            if(raf.readChar() != '*')//del
            {   
                id = Integer.parseInt(raf.readUTF(),16);
                System.out.print(id + " ");//id
                System.out.print(raf.readUTF() + " ");//type
                System.out.print(raf.readUTF() + " ");//title
                System.out.print(raf.readUTF() + " ");//director
                System.out.print(raf.readUTF() + " ");//cast
                System.out.print(raf.readUTF() + " ");//country
                System.out.print(raf.readUTF() + " ");//date added
                System.out.print(raf.readUTF() + " ");//release year
                System.out.print(raf.readUTF() + " ");//rate
                System.out.println(raf.readUTF());//duration
            }
            pos = inter + pos +4;
            raf.seek(pos);
        }
    }
    public static void Update(RandomAccessFile raf, int SearchID, int x, String text) throws IOException{
        //
        if(SearchID <= GetTam(raf))
        {
            raf.seek(0);

            TableInfo tb = new TableInfo();
            long pos = Search(raf, SearchID);

            raf.seek(pos);
            tb.readall(raf);
            raf.seek(pos);
            tb.update(x,text);
            raf.seek(pos);

            byte[] bytes = tb.toBYte();

            raf.writeInt(bytes.length);
            raf.write(bytes);
        }
    }
    public static void Delete(RandomAccessFile raf, int SearchID) throws IOException{
        //
        if(SearchID <= Crud.GetTam(raf)){
            raf.seek(0);
            long pos = Search(raf, SearchID);
            raf.seek(pos);
            raf.readInt();
            raf.writeChar('*');
        }
    }

    public static int GetTam(RandomAccessFile raf) throws IOException{
        //WORK
        raf.seek(0);
        raf.readInt();
        raf.readChar();
        raf.readUTF();
        return Integer.parseInt(raf.readUTF());
    }

    public static long Search(RandomAccessFile raf, int SearchID) throws IOException{
        //WORK
        long pos = 0;
        int id = 0;
        int max = GetTam(raf);
        raf.seek(0);
        int inter;
        System.out.println("Inicio");
        if(id <= max){

            while(id <= SearchID){
                inter = raf.readInt();//tamanho registro
                System.out.print(id + "----->"); System.out.println(inter);
                if(raf.readChar() != '*')//del // id
                {   
                    id = Integer.parseInt(raf.readUTF(),16);
                    if(SearchID == id){
                        System.out.print(id + " ");//id
                        System.out.print(raf.readUTF() + " ");//type
                        System.out.print(raf.readUTF() + " ");//title
                        System.out.print(raf.readUTF() + " ");//director
                        System.out.print(raf.readUTF() + " ");//cast
                        System.out.print(raf.readUTF() + " ");//country
                        System.out.print(raf.readUTF() + " ");//date added
                        System.out.print(raf.readUTF() + " ");//release year
                        System.out.print(raf.readUTF() + " ");//rate
                        System.out.println(raf.readUTF());//duration
                        System.out.print(id + "----->"); System.out.println(inter);
                        return pos;
                    }
                }
                pos = inter + pos +4;
                raf.seek(pos);
            }
        }
        return -1;    
    }
}
