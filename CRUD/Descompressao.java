import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;


public class Descompressao extends TableInfo
{
    int id;
    String insert;
    int where;

    public HashMap<Integer, String> dicionario = new HashMap<Integer, String>();

    TableInfo tb = new TableInfo();

    public Descompressao() throws IOException {
        id = 1;
        where = -1;
        insert ="";
    }

    public void criaDicionario() 
    {
        dicionario.put(0, String.valueOf( ( (char) 28 ) ));
        
        for (int i = 32; i < 127; i++) {
            String aux = String.valueOf((char) i);
            dicionario.put(id, aux);
            id++;
        }
        for (int i = 128; i < 168; i++) {
            String aux = String.valueOf((char) i);
            dicionario.put(id, aux);
            id++;
        }
    }


    String descompressao(int num, String last, DataOutputStream deco) throws IOException
    {
        //tentativa 1
        String aux = dicionario.get(num);
        
        insert+=aux;

        int x = 0;
        int tamanho = aux.length() -1;

        if(insert.endsWith(String.valueOf((char)28)))
        {
            String str[] = insert.split(String.valueOf((char)28),0);
            
            for(int i = 0; i < str.length; i++)
            {
                addTableInfo(str[i],deco);
            }

            insert = "";
        }

        while(x <= tamanho)
        {
            last+=aux.charAt(x);

            if(dicionario.containsValue(last) == false)
            {  
                dicionario.put(id, last);
                id++;

                String temp ="";
                
                for(int i = x; i <= tamanho; i++ )
                {
                    temp += aux.charAt(i);
                }
                
                return temp;
            }
            x++;
        }
        return last;
    }

    private void addTableInfo(String aux, DataOutputStream deco) throws IOException 
    {   
        System.out.print(aux);
        System.out.print(" -- >");
        System.out.println(where);
        
        switch(where)
        {
            case -1:
                deco.writeInt( Integer.parseInt(aux) );
                break;
            case 0:
                
                break;
            case 1:
                tb.setLapide(aux);
                break;
            case 2:
                tb.setID( Integer.parseInt(aux) );
                break;
            case 3:
                tb.setType(aux);
                break;
            case 4:
                tb.setTitle(aux);
                break;
            case 5:
                tb.setDirector(aux);
                break;
            case 6:
                tb.setCast(aux);
                break;
            case 7:
                tb.setCountry(aux);
                break;
            case 8:
                tb.setDate_Added(aux);
                break;
            case 9:
                tb.setRelease_Year(aux);
                break;
            case 10:
                tb.setRate(aux);
                break;
            case 11:
                tb.setDuration(aux);
            break;
            case 12:
                byte[] baos = tb.converte_bytearray();
                deco.writeInt(baos.length);
                deco.write(baos);
                where=-1;
            break;
        }
        where++;
    }

    public void iniciaDescompressao() throws IOException {

        FileOutputStream file = new FileOutputStream("decodificacao.db");

        DataOutputStream deco = new DataOutputStream(file);
        String laString = "";

        try (RandomAccessFile codi = new RandomAccessFile("codificacao.db", "r")) 
        {
            int reader = 1;
            while(reader > -1)
            {
                
                reader = (codi.read()*256) + codi.read();
                laString = descompressao(reader, laString, deco);
            }

        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("Descompressao finalizada");
        }

    }

}