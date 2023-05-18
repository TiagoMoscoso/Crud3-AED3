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
            // condicao normal id maior que numero do dicionario
            aux = dicionario.get(num);
        }
        else
        {
            //condicao especifica de id menor que o numero do dicionario
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

        //string de insercao do arquivo mais auxiliar com valor do dicionario
        insert+=aux;


        int x = 0;
        int tamanho = aux.length() -1;

        if( insert.indexOf(28) > -1)
        {
            //separa campos pelo file selector e distribui pela classe table info para depois inserir no arquivo

            String str[] = insert.split(String.valueOf((char)28),0);
            int tam =  str.length;

            if(insert.endsWith(String.valueOf((char)28)) == false)
            {
                //se ultimo campo nao termina com file selector entao ele não entra para inserção do arquivo (pois está incompleto)
                tam--;
                insert = str[tam];
            }
            else
            {
                insert = "";
            }

            for(int i = 0; i < tam; i++)
            {
                //adiciona campos selecionados pelo file selector em classe para depois inserir no arquivo
                addTableInfo(str[i],deco);
            }

        }

        while(x <= tamanho)
        {
            //vai incrementando characteres do auxiliar na last para comparar com dicionario
            last+=aux.charAt(x);

            if(dicionario.containsValue(last) == false)
            {
                if (id == 65535)
                {
                    //se o tamnho do dicionario igual a 65535 (2 bytes), reseta dicionario
                    dicionario.clear();
                    id = 1;
                    restored = true;
                    criaDicionario();
                }

                //adiciona nova string ao dicionario e incrementa o id
                dicionario.put(id, last);
                id++;
                
                //reseta last e inseri o ultimo char do auxiliar nele
                last = "";
                last+=aux.charAt(x);
            }
            x++;
        }

        return last;
    }

    private void addTableInfo(String aux, DataOutputStream deco) throws IOException 
    {   if(aux.compareTo("") != 0){

        //insere campo respequitivo pela posicao que foi adicionado por ultimo

            switch(where)
            {

                case -1:
                //cabeçalho
                    deco.writeInt( Integer.parseInt(aux) );
                    break;
                case 0:
                //tamanho
                    break;
                case 1:
                //lapide
                    tb.setLapide(aux);
                    break;
                case 2:
                //id
                    tb.setID(aux);
                    camp++;
                    break;
                case 3:
                //tipo
                    tb.setType(aux);
                    break;
                case 4:
                //titulo
                    tb.setTitle(aux);
                    break;
                case 5:
                //diretor
                    tb.setDirector(aux);
                    break;
                case 6:
                    tb.setCast(aux);
                    break;
                case 7:
                //pais
                    tb.setCountry(aux);
                    break;
                case 8:
                //data de adicao
                    tb.setDate_Added(aux);
                    break;
                case 9:
                //data de lancamento
                    tb.setRelease_Year(aux);
                    break;
                case 10:
                //classificacao indicativa
                    tb.setRate(aux);
                    break;
                case 11:
                //duracao
                    tb.setDuration(aux);
                break;
                default:
                //ultimo campo, salva elementos da tabela no arquvo
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
                //le dois bytes do arquivo compress
                reader = (codi.read()*256) + codi.read();

                //insere numero respequitivo aos 2 bytes lidos no arquivo e incrementa dicionario
                laString = descompressao(reader, laString, deco);

            }

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        descompressao(0, laString, deco);
        //finaliza proscesso
        
        System.out.println("Descompressao finalizada");

    }

}