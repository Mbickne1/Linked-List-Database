//Object that takes in a fName, lName, and ID to create an array of DataBaseRecords in the main DataBase
public class DataBaseRec {

	private String fName;
	private String lName;
	private String ID;

	//Default Constructor w/ no parameters
	public DataBaseRec()
	{
		fName = "defaultFirst";
		lName = "defaultLast";
		ID = "000000";
	}
	
	//Constructor w/ parameters 
	public DataBaseRec(String fName, String lName, String ID)
	{
		this.fName = new String(fName);
		this.lName = new String(lName);
		this.ID = new String(ID);
	}
	
	
	//Getters and Setters
	//Return and Change all variables: fName, lName, ID
	public String getFirst()
	{
		return fName;
	}
	
	public String getLast()
	{
		return lName;
	}
	
	public String getID()
	{
		return ID;
	}
	
	public void setFirst(String fName)
	{
		this.fName = fName;
	}
	
	public void setLast(String lName)
	{
		this.lName = lName;
	}
	
	public void setID(String ID)
	{
		this.ID = ID;
	}
	
	//toString Method
	public String toString()
	{
		return fName + " " + lName + " " + ID;
	}
	
	
}