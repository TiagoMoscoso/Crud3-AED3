import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;

public class Descompressao
{
    int id;

    public HashMap<Integer, String> dicionario = new HashMap<Integer, String>();

    public Descompressao() throws IOException {
        id = 1;
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
        System.out.println(id);
    }

    public String decompress(int num, String last) 
    {
        //tentativa 2
        String aux = dicionario.get(num);
        System.out.print(aux);

        String result = last + aux;

        if (dicionario.containsValue(result))
        {
            return result;
        }

        int newCode = dicionario.size();
        dicionario.put(newCode, last + aux);

        return aux;
    }

    String descompressao(int num, String last) throws IOException
    {
        //tentativa 1
        String aux = dicionario.get(num);

        last = last + aux;
        
        System.out.print(aux);


        if(dicionario.containsValue(last) == false)
        {
            dicionario.put(id, last);
            id++;

            return aux;
        }
        else
        {
            
            return last;
        }
    }

    public void iniciaDescompressao() throws IOException {

        FileOutputStream file = new FileOutputStream("decodificacao.db");

        DataOutputStream deco = new DataOutputStream(file);

        try (RandomAccessFile codi = new RandomAccessFile("codificacao.db", "r")) {
            int reader;
            String laString = "";
            System.out.println(laString.length());
            while(true)
            {
                reader = codi.readInt();
                laString = descompressao(reader, laString);
            }
        }
        
    }

}