/* Creates an array of Data Base Records
* Also creates indexes of linked lists for each value(FirstName, LastName, ID) of the Data Base Records
*/


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DataBase {

	private DataBaseRec[] data;
	private Index firstIndex, lastIndex, idIndex;
	private int nextRec;
	
	//Constructor of Database
	//Creates an array of DataBaseRec of size 100 to load the initial database values into
	//Initializes all of the indexes also of size 100
	//Then reads in the data from the text file to load the database
	public DataBase()
	{
		data = new DataBaseRec[100];
		nextRec = 0;
		
		//Initialize the three indexes
		firstIndex = new Index();
		lastIndex = new Index();
		idIndex = new Index();
		
	    try 
	     {
	    	
	    	//Load the file and create a scanner to parse the file
	    	 File f = new File("data.txt");
	    	 Scanner scan = new Scanner(f);
	    	 
	    	 //While the file still has information
	    	 while(scan.hasNext())
	    	 {
	    		 //Load the fName, lName, and ID into the database by calling the loadDataBase method as well as the record index
	    		 loadDataBase(scan.next(),scan.next(),scan.next(),nextRec);
	    	 }
	    	 
	    	 scan.close();
	     } 
	    //If the file is not found
	     catch (FileNotFoundException e)
	     {
	    	 System.out.println("The File was not found");
	     }
		
	}
	

	//Method loadDataBase used to create the initial database with the data from the text file
	//Also loads all three of the indexes
	public void loadDataBase(String lName, String fName, String ID, int next)
	{

		if(next != 0  && (checkDuplicateID(ID) == true)) //If the index is not zero, check if the ID being entered is a duplicate
		{	
			//If it is a duplicate, print message and don't add to data base
			System.out.println(fName + " " + lName + " has a duplicate ID to an ID already entered in the database so this record will not be loaded: " + ID);
			System.out.println("*******");
		}	
		else
		{
			//Add DataBaseRec to the DataBase
			DataBaseRec dBR = new DataBaseRec(fName, lName, ID);
			data[next] = dBR;

			//Add First Name IndexRecord to the First Name Index
			IndexRec fIR = new IndexRec(fName, next);
			firstIndex.insert(fIR);

			//Add Last Name IndexRecord to the Last Name Index
			IndexRec lIR = new IndexRec(lName, next);
			lastIndex.insert(lIR);

			//Add ID IndexRecord to the ID Index
			IndexRec idIR = new IndexRec(ID, next);
			idIndex.insert(idIR);

			nextRec++;  //Increase the record count
		}
	}


	//checkID method to check for duplicate ID's
	public Boolean checkDuplicateID(String ID)
	{
		return((idIndex.find(ID) == null) ? false:true); //If find() returns null then the ID was not found so return false;not a duplicate---else return true; is a duplicate
	}


	//Method to add a new record into the Data Base
	public void addIt()
	{
		Scanner scan = new Scanner(System.in); //Scanner for User Input

		System.out.println("Enter in the last name, first name, and ID, of record to add: "); //Message for the user to input the information so it can be properly loaded

		loadDataBase(scan.next(), scan.next(), scan.next(), nextRec); //Call the loadDataBase method with the new record to add it to the database as well as inserting it into all three indexes

		System.out.println("--------");


	}


	//Finds a record in the Data Base by ID
	public void findIt()
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the ID to Locate in the Database");
		String findID = scan.next();

		try
		{
			System.out.println(data[idIndex.find(findID).getWhere()]);
		}
		catch (NullPointerException e)
		{
			System.out.println("The ID was not found");
		}
		System.out.println("--------");


	}

	//Deletes a record in the Data Base by ID
	public void deleteIt()
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the ID to be deleted: ");
		String delVal = scan.next();
		try
		{
			//Get the first name associated with the ID entered so that it can be deleted from the firstName index
			String f = data[idIndex.find(delVal).getWhere()].getFirst();

			//Get the last name associated with the ID so that it can be deleted from the lastName index
			String l = data[idIndex.find(delVal).getWhere()].getLast();
			
			if(idIndex.delete(delVal) && firstIndex.delete(f) && lastIndex.delete(l))  
			{
				System.out.println("The ID was deleted successfully: " + delVal); //Print statement so user knows it worked
				System.out.println("--------");
			}
		}
		catch (NullPointerException e)
		{
			System.out.println("The ID was not found.");
			System.out.println("--------");
		}
	}

	//Methods for displaying the Data Base through different means
	public void ListByIDAscending()
	{
		System.out.println("---------");
		display(idIndex, 0);
		System.out.println("---------");
	}

	public void ListByIDDescending()
	{
		System.out.println("---------");
		display(idIndex, 1);
		System.out.println("---------");
	}

	public void ListByFirstAscending()
	{
		System.out.println("---------");
		display(firstIndex, 0);
		System.out.println("---------");
	}

	public void ListByFirstDescending()
	{
		System.out.println("---------");
		display(firstIndex, 1);
		System.out.println("---------");
	}

	public void ListByLastAscending()
	{
		System.out.println("---------");
		display(lastIndex, 0);
		System.out.println("---------");
	}

	public void ListByLastDescending()
	{
		System.out.println("---------");
		display(lastIndex, 1);
		System.out.println("---------");
	}

	public void display(Index index, int key)
	{
		switch(key)
		{
		case 0: //Display the given index in ascending order
		{
			index.resetIteratorStart(); //Set iterator to start of the index
			while(index.getCurrent() != null) //While the index isn't null
			{
				System.out.println(data[index.getCurrent().getWhere()]); //Print the DataBaseRecord of the current position of the iterator in the index
				index.nextLinkForward(); //Move the iterator forward through the list
			}
			break;
		}
		case 1: //Display the given index in descending order
		{
			index.resetIteratorEnd(); //Set the iterator to the end of the list
			while(index.getCurrent() != null) //While the iterator is not null
			{
				System.out.println(data[index.getCurrent().getWhere()]);  //Print the DataBaseRecord of the current position of the iterator in the index
				index.nextLinkBackWard(); //Move the iterator backwords through the list
			}
			break;
		}
		}
	}

}
