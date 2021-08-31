/* 
Author: Matthew Bicknell

This program is an emulation of relational database using a Double-Ended Doubly Linked List
The database contains information about students: A first name, last name, and ID number
It contains five classes:
	Driver - This is the class that allows takes input for manipulation and different ways of accessing information stored in the data base.
	DataBase - This class loads the student information into the database and holds the different methods that are called by driver.
	DataBaseRec - This class creates the object of Data Base Records that hold the information of the students. The database is loaded with these objects
	Index - Creates the data structure of the Linked List to hold Indexes of the first name, last name, and ID of the students. 
	Index Rec - Creates the objects of the nodes that are linked through the linked lists created by Index
*/

import java.util.*;

public class Driver
{
	public static void main(String[] args)
	{
		DataBase d = new DataBase();  
		int response;
		Scanner keyboard = new Scanner(System.in);

	
        do
        {
            System.out.println(" 1 Add a new student");
            System.out.println(" 2 Delete a student");
            System.out.println(" 3 Find a student by ID");
            System.out.println(" 4 List students by ID increasing");
            System.out.println(" 5 List students by first name increasing");
            System.out.println(" 6 List students by last name increasing");
            System.out.println(" 7 List students by ID decreasing");
            System.out.println(" 8 List students by first name decreasing");
            System.out.println(" 9 List students by last name decreasing");
            System.out.println(" ");
            System.out.println(" 0 End");

            response=keyboard.nextInt();

            switch (response)
            {
                case 1: d.addIt(); 	//Note: if the user enters an ID already in use, issue a warning and return to the menu
                        break;
                case 2: d.deleteIt();	//Note: output either "Deleted" or "ID not Found" and return to menu
                        break;
                case 3: d.findIt();	//Note: output the entire record or the message "ID not Found" and return to menu
                        break;
                case 4: d.ListByIDAscending();		
                        break;
                case 5: d.ListByFirstAscending();	
                        break;
                case 6: d.ListByLastAscending();
                        break;
                case 7: d.ListByIDDescending();
                        break;
                case 8: d.ListByFirstDescending();
                        break;
                case 9: d.ListByLastDescending();
                        break;
                default:                
            }

        } while (response!=0); 
        
	}
}