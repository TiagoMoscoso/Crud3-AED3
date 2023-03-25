import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

public class TableInfo{
	
		int ID;
		boolean del = new Boolean(false);	 
		String 	Type= new String();
		String 	Title = new String();
		String  Director= new String(); 
		String  Cast= new String(); 
		String  Country= new String(); 
		String  Date_Added= new String(); 
		String  Release_Year= new String(); 
		String  Rate= new String(); 
		String 	Duration= new String();
		

		TableInfo(){
			//construtor
			ID = -1;
    		Type = "?";
			del = false;
			Title = "?";
    		Director = "?";
			Country = "?";
    		Cast = "?";
    		Date_Added = "?";
    		Release_Year = "?";
    		Rate = "?";
    		Duration = "?";
    	}
		//Funcoes para definir variaveis
		/////////////////////////////////////////////////////////////////////////
		public void setID(String NewID){
			if(NewID != " " && NewID != "")
				ID = Integer.parseInt(NewID);
		}
		//polimorfismo em ID em caso de entrada em String ou Int
		public void setID(int NewID){
			ID = NewID;
		}
		
		public void setTitle(String NewTitle){
			if(NewTitle != " " && NewTitle != "")
				this.Title= NewTitle;
			else
				this.Title= "?";
		}

		public void setType(String NewType){
			if(NewType != " " && NewType != "")
				this.Type = NewType;
			else
				this.Type = "?";
		}
		
		public void setDirector(String NewDirector){
			if(NewDirector != " " && NewDirector != "")
				this.Director = NewDirector;
			else
				this.Director = "?";
		}
		
		public void setCast(String NewCast){
			if(NewCast != " " && NewCast != "")
				this.Cast = NewCast;
			else
				this.Cast = "?";
		}
		
		public void setCountry(String NewCountry){
			if(NewCountry != " " && NewCountry != "")
				this.Country = NewCountry;
			else
				this.Country = "?";
		}
		
		public void setDate_Added(String NewDate_Added){
			if(NewDate_Added != " " && NewDate_Added != "")
				this.Date_Added = NewDate_Added;
			else
				this.Date_Added = "?";
		}
		
		public void setRelease_Year(String NewRelease_Year){
			if(NewRelease_Year!= " " && NewRelease_Year != "")
				this.Release_Year = NewRelease_Year;
			else
				this.Release_Year = "?";
		}
		
		public void setRate(String NewRate){
			if(NewRate!= " " && NewRate!= "")
				this.Rate = NewRate;
			else
				this.Rate = "?";
		}
		
		public void setDuration(String NewDuration){
			if(NewDuration!= " " && NewDuration!= "")
				this.Duration = NewDuration;
			else
				this.Duration = "?";
		}
		/////////////////////////////////////////////////////////////////////////
		
		//funcao para definir todos as variaveis por array
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
					break;
				default:
					x = allstrings.length;
					break;
				}
			}
		}
		
		
		//funcao para printar no console
		public void printAll(){
			System.out.print(ID);
			System.out.print(";");
			System.out.print(Title);
			System.out.print(";");
			System.out.print(Type);
			System.out.print(";");
			System.out.print(Director);
			System.out.print(";");
			System.out.print(Cast);
			System.out.print(";");
			System.out.print(Country);
			System.out.print(";");
			System.out.print(Date_Added);
			System.out.print(";");
			System.out.print(Release_Year);
			System.out.print(";");
			System.out.print(Rate);
			System.out.print(";");
			System.out.println(Duration);
			
		}

		//funcao para transformar todas variaveis em uma unica string
		public byte[] toBYte() throws IOException
		{
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			DataOutputStream bytes = new DataOutputStream(baos);

			if(del == false)
			{
				bytes.writeChar(' ');
			}
			else
			{
				bytes.writeChar('*');
			}
			bytes.writeUTF(Integer.toHexString(ID));
			bytes.writeUTF(Type);
			bytes.writeUTF(Title);
			bytes.writeUTF(Director);
			bytes.writeUTF(Cast);
			bytes.writeUTF(Country);
			bytes.writeUTF(Date_Added);
			bytes.writeUTF(Release_Year);
			bytes.writeUTF(Rate);
			bytes.writeUTF(Duration);
			

			return baos.toByteArray();
		}
		
		public long readall(RandomAccessFile raf) throws IOException
		{
			
			long pos = raf.readInt();
			System.out.println("here :" + pos);
			if(raf.readChar() == '*')
			{
				this.Delete();
			}

			this.setID(Integer.parseInt(raf.readUTF(),16));
			this.setType(raf.readUTF());
			this.setTitle(raf.readUTF());
			this.setDirector(raf.readUTF());
			this.setCast(raf.readUTF());
			this.setCountry(raf.readUTF());
			this.setDate_Added(raf.readUTF());
			this.setRelease_Year(raf.readUTF());
			this.setRate(raf.readUTF());
			this.setDuration(raf.readUTF());
				
			return pos;
		}

		
		public String Dif(int takedif, String text)
		{
			//WORK
			StringBuilder append = new StringBuilder();
			append.append(text);
			if(takedif<0){
				while(takedif < 0)
				{
					append.append(" ");
					takedif++;
				}
			}
			return append.toString();
		}

		public void update(int x, String text) 
		{
			//WORK
			int takedif = 0;
			switch(x)
            {
            case 1:
				takedif = text.length() - Type.length();
                setType(Dif(takedif,text) );
                break;
            case 2:
				takedif = text.length() - Title.length();
                setTitle(Dif(takedif,text));
				break;
			case 3:
				takedif = text.length() - Director.length();
                setDirector(Dif(takedif,text));
				break;
			case 4:
				takedif = text.length() - Cast.length();
                setCast(Dif(takedif,text));
				break;
			case 5:
				takedif = text.length() - Country.length();
                setCountry(Dif(takedif,text));
				break;
			case 6:
				takedif = text.length() - Date_Added.length();
                setDate_Added(Dif(takedif,text));
				break;
			case 7:
				takedif = text.length() - Release_Year.length();
                setRelease_Year(Dif(takedif,text));
				break;
			case 8:
				takedif = text.length() - Rate.length();
                setRate(Dif(takedif,text));
				break;
			case 9:
				takedif = text.length() - Duration.length();
				setDuration(Dif(takedif,text));
				break;
			}
		}
	
	public void Delete()
	{
		del = true;
	}
}
