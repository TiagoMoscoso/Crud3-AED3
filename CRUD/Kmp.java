import java.io.RandomAccessFile;

public class Kmp extends TableInfo
{

    public void KMPstart(String Busca)
    {
        long time = System.currentTimeMillis();
        char[] buscar = Busca.toCharArray();
        int x = 0;
        int lastID = -1;
        int total = 0;
        int vezes = 0;
        try 
        {
            RandomAccessFile raf = new RandomAccessFile("netflix.db", "r");
            TableInfo tb = new TableInfo();
            raf.seek(0);
            tb.setID(0);
            raf.readInt();
            System.out.println("Sua busca foi encontrada nos Id's :");
            while(true)
            {
                tb.readall(raf);
                char[] c = tb.toCharSTR();
                
                for(int i = 0; i < c.length; i++)
                {
                    //Comparacoes de busca
                    if(c[i] == buscar[x])
                    {
                        x++; //avança array de palavra procurada
                        if(x == buscar.length)
                        {
                            //printa id se achar e reseta array
                            if(lastID != tb.ID){

                                System.out.print("ID: " + tb.ID);
                                lastID = tb.ID;
                                //printa apenas se ID != de Last ID
                            }
                            vezes++;
                            x = 0;
                        }
                    }
                    else
                    {
                        //Transição de falhas
                        int y = 0;
                        for(int j = x; j >= 0; j--)
                        {
                            if(c[i] == buscar[j])
                            {
                                y++;
                                i--;
                            }
                            else if(y != 0)
                            {
                              i+=y;
                              y=0;  
                            }
                        }
                        //Volta o i para seu valor original e define x de acordo do a transicao
                        i+=y;
                        x = y;
                    }

                }
                if(vezes > 0) 
                {
                    System.out.print(" Vezes: "+ vezes + ",");
                    total += vezes;
                    vezes = 0;
                }

            }
        } 
        catch (Exception e) 
        {
              
        }
        System.out.println("");
        System.out.println("Numero de matches da palavra "+ Busca +" :" + total);
        time = System.currentTimeMillis() - time;
        System.out.println("Tempo de execucao milisegundos : " + time);
    }

}