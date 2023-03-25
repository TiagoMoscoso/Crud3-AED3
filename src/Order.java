import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;


public class Order extends TableInfo{
    public static void Order() throws IOException
    {
        System.out.println("/////////////////////");
        RandomAccessFile raf = new RandomAccessFile("netflix.db", "r");
        RandomAccessFile f1 = new RandomAccessFile("arch1.db", "rw");
        RandomAccessFile f2 = new RandomAccessFile("arch2.db", "rw");
        int max = Crud.GetTam(raf);
        raf.seek(0);
        int x = 0;
        TableInfo[] tb = new TableInfo[10];
        for(int y=0; y<10; y++)
        {
            tb[y] = new TableInfo();
        }
        long pos = tb[0].readall(raf) + 4;
        System.out.println("/////////////////////");
        byte[] bytes = tb[0].toBYte();

        boolean fileselect = false;
        while (tb[x].ID < max){
            raf.seek(pos);
            pos = tb[x].readall(raf) + 4 + pos;
            x++;
            if(x >= 10 || tb[x].ID == max)
            {
                orderVets(tb);
                x = 0;
                for(int y = 0; y < 10; y++)
                {            
                    raf.seek(pos);
                    if(tb[y].ID != -1)
                    {
                        bytes = tb[y].toBYte();
                        if(fileselect == true)
                        { 
                            if(tb[y].del == false){
                                f1.writeInt(bytes.length);
                                f1.write(bytes);
                            }
                        }
                        else
                        {
                            if(tb[y].del == false){
                                f2.writeInt(bytes.length);
                                f2.write(bytes);
                            }
                        }
                    }
                }
                fileselect = !fileselect;
            }
        }   
        raf.close();
        RandomAccessFile f3 = new RandomAccessFile("arch3.db", "rw");
        f1.seek(0);
        f2.seek(0);
        int posf1 = 0, posf2 = 0;
        //0 - 4 = f1  5 - 9 f2
        /*for(int x = 0; x < max; x++)
        {
            f1.seek(pos);
            posf1 = tb[x].readall(f1) + 4 + posf1;  
        }*/
    }

    static void orderVets(TableInfo[] tb) throws IOException
    {   
        int vetorde[] = new int[10];

        for(int x = 0; x < 10; x ++)
        {
            vetorde[x] = tb[x].ID;
        }

        Arrays.sort(vetorde);

        for(int x = 0; x < 10; x ++)
        {
            for(int y = 0; y < 10; y ++)
            {
                if(vetorde[x] == tb[y].ID)
                {   
                    TableInfo aux = new TableInfo();
                    aux = tb[x];
                    tb[x] = tb[y];
                    tb[y] = aux;
                }
            } 
        }
    }

}
