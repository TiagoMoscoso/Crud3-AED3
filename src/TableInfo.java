
public class TableInfo{
	
		int ID; 
		char Tam[] = new char[1]; 
		char Type [] = new char[1]; 
		char Director [] = new char[1]; 
		char Cast [] = new char[1]; 
		char Country [] = new char[1]; 
		char Date_Added [] = new char[1]; 
		char Release_Year [] = new char[1]; 
		char Rate [] = new char[1]; 
		char Duration [] = new char[1];
		

		TableInfo(){
    		Type[0] = '?';
    		Director[0] = '?';
    		Cast[0] = '?';
    		Date_Added[0] = '?';
    		Release_Year[0] = '?';
    		Rate[0] = '?';
    		Duration[0] = '?';
    		setTam();
    	}
    
		public void setTam(){
			int x =  Type.length + Director.length + Cast.length + Country.length + Date_Added.length + Release_Year.length + Rate.length + Duration.length;
			this.Tam = Integer.toHexString(x).toCharArray();
		}
		
		public void setID(String NewID){
			ID = Integer.parseInt(NewID);
		}
		
		public void setID(int NewID){
			ID = NewID;
		}
		
		
		public void setType(String NewType){
			this.Type = NewType.toCharArray();
			setTam();
		}
		
		public void setDirector(String NewDirector){
			this.Director = NewDirector.toCharArray();
			setTam();
		}
		
		public void setCast(String NewCast){
			this.Cast = NewCast.toCharArray();
			setTam();
		}
		
		public void setCountry(String NewCountry){
			this.Country = NewCountry.toCharArray();
			setTam();
		}
		
		public void setDate_Added(String NewDate_Added){
			this.Date_Added = NewDate_Added.toCharArray();
			setTam();
		}
		
		public void setRelease_Year(String NewRelease_Year){
			this.Release_Year = NewRelease_Year.toCharArray();
			setTam();
		}
		
		public void setRate(String NewRate){
			this.Rate = NewRate.toCharArray();
			setTam();
		}
		
		public void setDuration(String NewDuration){
			this.Duration = NewDuration.toCharArray();
			setTam();
		}
		
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
		
		public void printAll(){
			System.out.print(ID);
			System.out.print(";");
			System.out.print(Tam);
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
		
}
