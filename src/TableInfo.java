
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
		public String BigString(){
			StringBuilder sb = new StringBuilder();
			sb.append(Integer.toHexString(ID));
			sb.append(" ");
			sb.append("2");
			if(del == false)
			{
			sb.append("  ");
			}
			else
			{
			sb.append(" *");
			}
			sb.append(Integer.toHexString(Type.length()+1));
			sb.append(" " + Type);
			sb.append(Integer.toHexString(Title.length()+1));
			sb.append(" " + Title);
			sb.append(Integer.toHexString(Director.length()+1));
			sb.append(" " + Director);
			sb.append(Integer.toHexString(Cast.length()+1));
			sb.append(" " + Cast);
			sb.append(Integer.toHexString(Country.length()+1));
			sb.append(" " + Country);
			sb.append(Integer.toHexString(Date_Added.length()+1));
			sb.append(" " + Date_Added);
			sb.append(Integer.toHexString(Release_Year.length()+1));
			sb.append(" " + Release_Year);
			sb.append(Integer.toHexString(Rate.length()+1));
			sb.append(" " + Rate);
			sb.append(Integer.toHexString(Duration.length()+1));
			sb.append(" " + Duration);
			System.out.println(sb);
			return sb.toString();
		}
		
		public void DecompresSwitch(int x, StringBuilder value)
		{
			switch(x)
			{
				case 0:
					setID(Integer.parseInt(value.toString(),16));
					System.out.println(value);
				break;
				case 1:
					if(value.toString() == "*")
					{
						Delete();
					}
					System.out.println(value);
				break;
				case 2:
					setType(value.toString());
					System.out.println(value);
					break;
				case 3:
					setTitle(value.toString());
					System.out.println(value);
					break;
				case 4:
					setDirector(value.toString());
					System.out.println(value);
					break;
				case 5:
					setCast(value.toString());
					System.out.println(value);
				break;
				case 6:
					setCountry(value.toString());
					System.out.println(value);
					break;
				case 7:
					setDate_Added(value.toString());
					System.out.println(value);
					break;
				case 8:
					setRelease_Year(value.toString());
					System.out.println(value);
					break;
				case 9:
					setRate(value.toString());
					System.out.println(value);
					break;
				case 10:
					setDuration(value.toString());
					System.out.println(value);
					break;
			}	
		}

		public void DecompresString(String textUTF)
		{	
			Integer pos = 0;
			Integer nextposic = 0;
			Integer oldpos = 0;
			int where = 0;
			StringBuilder value = new StringBuilder();
			System.out.print(textUTF);

			while(textUTF.charAt(pos) != ' ')
			{
				value.append(textUTF.charAt(pos));
				pos++;
			}
			DecompresSwitch(where, value);
			pos++;
			value = new StringBuilder();
			while(pos < textUTF.length())
			{	
				if(pos < nextposic + oldpos && pos < textUTF.length()-1)
				{
					System.out.print(textUTF.charAt(pos));
					value.append(textUTF.charAt(pos));
				}
				else{
					if(pos < textUTF.length()-1){
						StringBuilder temp = new StringBuilder();
						while(textUTF.charAt(pos) != ' ')
						{
							temp.append(textUTF.charAt(pos));
							pos++;
						}
						nextposic = Integer.parseInt(temp.toString(),16);
						oldpos = pos;
					}
					if(where > 0){
						DecompresSwitch(where, value);
						value = new StringBuilder();
					}
					where++;
				}
				pos++;
			}
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

		public void update(int x, String text) {
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
