import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

public class TableInfo{
	
    int ID;
    String Lapide;	 
    String 	Type = new String(); 
    String  Title = new String();
    String  Director= new String(); 
    String  Cast= new String(); 
    String  Country= new String(); 
    String  Date_Added= new String(); 
    String  Release_Year =  new String(); 
    String  Rate= new String(); 
    String 	Duration= new String();
    

    public TableInfo(){
        //construtor
        Title = "?";
        Type = "?";
        Director = "?";
        Cast = "?";
        Country="?";
        Date_Added = "?";
        Release_Year = "?";
        Rate = "?";
        Duration = "?";
    }
    //Métodos para definir variaveis
    /////////////////////////////////////////////////////////////////////////
    public void setID(String NewID){
        if(NewID != " " && NewID != "")
            ID = Integer.parseInt(NewID);
    }
    //polimorfismo em ID em caso de entrada em String ou Int
    public void setID(int NewID){
        ID = NewID;
    }
    
    
    public void setType(String NewType){
        if(NewType.compareTo(" ") != 0  && NewType.compareTo("") != 0) 
            this.Type = NewType;
        else
            this.Type = "?";
    }

    public void setTitle(String Title){
        if (Title.compareTo(" ") != 0 && Title.compareTo("") != 0)
            this.Title = Title;
        else
            this.Title = "?";

    }
    
    public void setDirector(String NewDirector){
        if(NewDirector.compareTo(" ") != 0  && NewDirector.compareTo("") != 0)
            this.Director = NewDirector;
        else
            this.Director = "?";
    }
    
    public void setCast(String NewCast){
        if(NewCast.compareTo(" ") != 0  && NewCast.compareTo("") != 0)
            this.Cast = NewCast;
        else
            this.Cast = "?";
    }
    
    public void setCountry(String NewCountry){
        if(NewCountry.compareTo(" ") != 0  && NewCountry.compareTo("") != 0)
            this.Country = NewCountry;
        else
            this.Country = "?";
    }
    
    public void setDate_Added(String NewDate_Added){
        if(NewDate_Added.compareTo(" ") != 0  && NewDate_Added.compareTo("") != 0)
            this.Date_Added = NewDate_Added;
        else
            this.Date_Added = "?";
    }
    
    public void setRelease_Year(String NewRelease_Year){
        if(NewRelease_Year.compareTo(" ") != 0  && NewRelease_Year.compareTo("") != 0)
            this.Release_Year =  NewRelease_Year;
        else
            this.Release_Year = "?";
    }
    
    public void setRate(String NewRate){
        if(NewRate.compareTo(" ") != 0  && NewRate.compareTo("") != 0)
            this.Rate = NewRate;
        else
            this.Rate = "?";
    }
    
    public void setDuration(String NewDuration){
        if(NewDuration.compareTo(" ") != 0  && NewDuration.compareTo("") != 0)
            this.Duration = NewDuration;
        else
            this.Duration = "?";
    }

    public void setLapide(String NewLapide){
        if(NewLapide.compareTo(" ") == 0)
            this.Lapide = " ";
        else
            this.Lapide = "*";
    }
    /////////////////////////////////////////////////////////////////////////
    
    //Método para definir todos as variaveis por array
    public void setALL(String allstrings[])
    {
        for(int x = 0; x < allstrings.length; x++)
        {
            switch(x)
            {
            case 0:
                setID(allstrings[x]);
                break;
            case 1:
                setType(allstrings[x]);
                break;
            case 2:
                setTitle(allstrings[x]);
                break;
            case 3:
                setDirector(allstrings[x]);
                break;
            case 4:
                setCast(allstrings[x]);
                break;
            case 5:
                setCountry(allstrings[x]);
                break;
            case 6:
                setDate_Added(allstrings[x]);
                break;
            case 7:
                setRelease_Year(allstrings[x]);
                break;
            case 8:
                setRate(allstrings[x]);
                break;
            case 9:
                setDuration(allstrings[x]);
            default:
                x = allstrings.length;
                break;
            }
        }
    }
    

    /*Método que cria um vetor de bytes para escrever no arquivo*/ 
    public byte[] converte_bytearray() throws IOException{
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeUTF(" ");
        dos.writeInt(ID);
        dos.writeUTF(Type);
        dos.writeUTF(Title);
        dos.writeUTF(Director);
        dos.writeUTF(Cast);
        dos.writeUTF(Country);
        dos.writeUTF(Date_Added);
        dos.writeUTF(Release_Year);
        dos.writeUTF(Rate);
        dos.writeUTF(Duration);
        dos.writeUTF(";");
        return baos.toByteArray();
    }

    /*Método que faz a leitura de um registro, setando os valores dos atributos */
    public void readall(RandomAccessFile raf) throws IOException{
        long z = raf.getFilePointer();
        int tamanho = raf.readInt(); //lê indicador de tamanho
        Lapide = raf.readUTF();//lê lápide
        setID(raf.readInt());
        setType(raf.readUTF());
        setTitle(raf.readUTF());
        setDirector(raf.readUTF());
        setCast(raf.readUTF());
        setCountry(raf.readUTF());
        setDate_Added(raf.readUTF());
        setRelease_Year(raf.readUTF());
        setRate(raf.readUTF());
        setDuration(raf.readUTF());
        raf.readUTF();
        
        raf.seek(z);
        raf.seek(raf.getFilePointer()+4+tamanho);

    }

    public String[] array_registros(){
        String x = Integer.toString(ID);
        String[] aux = {x,Type,Title,Director,Cast,Country,Date_Added,Release_Year,Rate,Duration};
        return aux;
    }

}