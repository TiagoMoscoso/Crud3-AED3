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
    int camp;

    public HashMap<Integer, String> dicionario = new HashMap<Integer, String>();

    TableInfo tb = new TableInfo();
    boolean restored = false;

    public Descompressao() throws IOException {
        id = 1;
        insert ="";
        where = -1;
        camp = 1;
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

        String aux;

        if(id > num)
        {
            aux = dicionario.get(num);
        }
        else
        {
            aux = last;
            for(int x = 0; x< last.length(); x++)
            {
                aux+= last.charAt(x);
                if(dicionario.containsValue(aux) == false)
                {
                    x = last.length();
                }
            }
        }

        
        //System.out.print(aux);
        insert+=aux;
        int x = 0;
        int tamanho = aux.length() -1;

        if( insert.indexOf(28) != -1)
        {

            String str[] = insert.split(String.valueOf((char)28),0);
            //System.out.println(insert);
            int tam =  str.length;

            if(insert.endsWith(String.valueOf((char)28)) == false)
            {
                tam--;
                insert = str[tam];
            }
            else
            {
                insert = "";
            }

            for(int i = 0; i < tam; i++)
            {
                addTableInfo(str[i],deco);
            }

        }

        while(x <= tamanho)
        {
            last+=aux.charAt(x);

            if(dicionario.containsValue(last) == false)
            {
                if (id == 65535)
                {
                    dicionario.clear();
                    id = 1;
                    restored = true;
                    criaDicionario();
                }

                dicionario.put(id, last);
                id++;
                
                last = "";
                last+=aux.charAt(x);
            }
            x++;
        }

        return last;
    }

    private void addTableInfo(String aux, DataOutputStream deco) throws IOException 
    {   if(aux.compareTo("") != 0){
            /*System.out.print(aux);
            System.out.print(" -- >");
            System.out.println(where);*/

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
                    tb.setID(aux);
                    camp++;
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
                default:
                    byte[] baos = tb.converte_bytearray();
                    deco.writeInt(baos.length);
                    deco.write(baos);
                    where=-1;
                break;
            }
            where++;
        }
    }

    public void iniciaDescompressao() throws IOException {

        criaDicionario();
        FileOutputStream file = new FileOutputStream("decodificacao.db");

        DataOutputStream deco = new DataOutputStream(file);
        String laString = "";

        try (RandomAccessFile codi = new RandomAccessFile("codificacao.db", "r")) 
        {
            int reader = 1;
            while(reader > -1)
            {
                if(camp <= 100)
                {
                    reader = (codi.read()*256) + codi.read();
                    laString = descompressao(reader, laString, deco);
                }
                else
                {
                    reader = -1;
                }
            }

            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        descompressao(0, laString, deco);
        System.out.println("Descompressao finalizada");

    }

}