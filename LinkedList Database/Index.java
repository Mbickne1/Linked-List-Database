
public class Index {

	private IndexRec front,back;
	private IndexRec current,previous; //Use for iterator
	
	//Construct the Empty Linked List by setting front and back to null
	public Index()
	{
		front = null;
		back = null;
	}
	
	//Method to insert a new IndexRecord node into the linked list
	public void insert(IndexRec newRec)
	{
		IndexRec rover = front;
		
		if(isEmpty())			//If the list is empty
		{
			front = newRec; 	//Set front and back to the first record
			back = newRec;
		}
		else if(rover.compareTo(newRec) >= 0) //If item is to be inserted at beginning of list
		{
			newRec.setPrev(null);  			  //Set Prev link to null 
			newRec.setNext(rover);			  //Set next to rover
			rover.setPrev(newRec);			  //Set rover.prev to newRec	
			front = newRec;					  //Set newRec to be new Front	
		}
		else
		{
			while(rover.getNext() != null && (rover.compareTo(newRec) < 0))  //While the rover isn't null and is less than the newRec
			{
				rover = rover.getNext();      //Increment the rover until the appropriate spot in the list is found
			}
			
			if(rover.getNext() == null && (rover.compareTo(newRec) < 0))  //If rover.getNext() is null and the rover is less than the new rec 
			{																		//Item is being inserted at the end of the list
				newRec.setNext(null);					//newRec.next is set to null
				rover.setNext(newRec);					//rover.next is the newRec
				newRec.setPrev(rover);					//newRec.prev is rover
				back = newRec;							//newRec is the back of the list
			}
			else		//Item isn't being inserted at the end
			{
				rover.getPrev().setNext(newRec);    //The record behind the newRec now points to the newRec
				newRec.setPrev(rover.getPrev());	//newRec points back
				newRec.setNext(rover);				//newRec.next is set to rover
				rover.setPrev(newRec);				//rover.prev is now set to the newRec
			}
		}
	}
	
	//Method to find an IndexRecord specified by ID using the Iterator 
	//Return the Record if found or return null if not found
	public IndexRec find(String ID)
	{
		resetIteratorStart(); //Set iterator to beginning of list
		
		while(getCurrent() != null && getCurrent().getKey().compareTo(ID) != 0) //Compare the value of Key in the current node to the ID being searched for
		{
			nextLinkForward(); //If not equal increment the iterator
		}
		
		return (getCurrent() != null ? getCurrent():null); 
	}
	
	//Method to delete a record from the Index
	//Removal from the index will not remove the item from the database but will prevent it from being shown when data is printed
	public Boolean delete(String key)
	{
		IndexRec delVal = find(key); //Set IndexRec object delVal to the record returned by the find method.
												//Will be null if not found
		if(delVal == null) //If delVal is null
			return false;		//return false because the id to be deleted does not exist
		else if(delVal == front) //If delVal is the front node
		{
			front = delVal.getNext(); //Set front to the next node
			front.setPrev(null); 		//Get rid of deleted node by setting Prev of newFront to null
		}
		else if(delVal == back)  //If delVal is the back node
		{
			back = delVal.getPrev(); //Set back to the node behind delVal
			back.setNext(null); 		//Get rid of deleted Node by setting next of newBack to null
		}
		else  					//The node to be deleted is not at the front or back
		{
			delVal.getPrev().setNext(delVal.getNext()); //Get rid of delVal by setting next of Previous node to node after delVal
			delVal.getNext().setPrev(delVal.getPrev());   //and by setting previous of next node to node behind delVal
		}
		return true; //return true because the item was found/deleted
	}
	
	//Method for determining if the Index is empty
	public Boolean isEmpty()
	{
		return(front == null ? true:false); //If front is null, list is empty-return true: else-return false;
	}
	
	
	//Methods to Make Use of an Iterator
	public void resetIteratorStart()
	{
		current = front;
		previous = null;
	}
	
	public void resetIteratorEnd()
	{
		current = back;
		previous = null;
	}
	
	public void nextLinkForward()
	{
		previous = current;
		current = current.getNext();
	}
	
	public void nextLinkBackWard()
	{
		previous = current;
		current = current.getPrev();
	}
	public IndexRec getCurrent()
	{
		return current;
	}
	
	public IndexRec getPrevious()
	{
		return previous;
	}
	
	public Boolean atEnd()
	{
		return(current.getNext() == null ? true:false);
	}
	
}
