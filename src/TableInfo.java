
public class TableInfo{
	
		int ID;	 
		String 	Type; 
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
					setDirector(allstrings[x]);
					break;
				case 3:
					setCast(allstrings[x]);
					break;
				case 4:
					setCountry(allstrings[x]);
					break;
				case 5:
					setDate_Added(allstrings[x]);
					break;
				case 6:
					setRelease_Year(allstrings[x]);
					break;
				case 7:
					setRate(allstrings[x]);
					break;
				case 8:
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
			sb.append(1);
			sb.append(" ");
			sb.append(Integer.toHexString(Type.length()+2));
			sb.append(" " + Type + " ");
			sb.append(Integer.toHexString(Director.length()+2));
			sb.append(" " + Director+ " ");
			sb.append(Integer.toHexString(Cast.length()+2));
			sb.append(" " + Cast+ " ");
			sb.append(Integer.toHexString(Country.length()+2));
			sb.append(" " + Country + " ");
			sb.append(Integer.toHexString(Date_Added.length()+2));
			sb.append(" " + Date_Added+ " ");
			sb.append(Integer.toHexString(Release_Year.length()+2));
			sb.append(" " + Release_Year+ " ");
			sb.append(Integer.toHexString(Rate.length()+2));
			sb.append(" " + Rate+ " ");
			sb.append(Integer.toHexString(Duration.length()+2));
			sb.append(" " + Duration+ " ");
			System.out.println(sb);
			return sb.toString();
		}
	
}
