//Creates the Objects of Index Records
//Serves as the nodes of the linked list
//Contains variables for next and previous node, the key(value) of the index, and where that index is located in the main Data Base.
public class IndexRec {

	private IndexRec next,prev;
	private int where;
	private String key;
	
	//Default Constructor
	public IndexRec()
	{
		next = null;
		where = -1;
		key = "Default";
	}
	
	//Constructor w/parameters
	public IndexRec(String key, int where)
	{
		this.key = new String(key);
		this.where = where;
	}
	
	
	//Getters and Setters
	public IndexRec getNext()
	{
		return next;
	}
	
	public IndexRec getPrev()
	{
		return prev;
	}
	
	public int getWhere()
	{
		return where;
	}
	
	public String getKey()
	{
		return key;
	}
	
	public void setNext(IndexRec nextRec)
	{
		next = nextRec;
	}

	public void setPrev(IndexRec prevRec)
	{
		prev = prevRec;
	}

	public void setWhere(int newWhere)
	{
		where = newWhere;
	}
	
	public void setKey(String newKey)
	{
		key = newKey;
	}
	
	//compareTo method used to compare two objects of type index record
	public int compareTo(IndexRec otherRec)
	{
		if(key.toLowerCase().compareTo(otherRec.getKey().toLowerCase()) < 0)     //Check If value of this object is less than value of object to compare 
			return -1;	//return -1 to represent less than if true
		else if(key.toLowerCase().compareTo(otherRec.getKey().toLowerCase()) == 0)
			return 0;  //or return 0 if they are equal
		else
			return 1; //or return 1 to represent greater than if false
	}
	
	public String toString()
	{
		return key + " " + where;
	}
	
	
	
}	