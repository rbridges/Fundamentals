
import java.util.*;

public class ArrayQueue<T> extends Queue<T>
{

	ArrayList<T> items = new ArrayList(10);
	int capacity = 10;
	int front = 0;
	int queueSize = 0;

	public ArrayQueue()
	{
		for(int i=0; i<capacity; i++)
		{
			items.add(null);
		}
	}

	public void enqueue(T toPut)
	{
		
		// time to dynamically resize
		if(queueSize == capacity)
			doubleSize();			
		
		items.set( (front+queueSize++)%capacity, toPut);
		


		if(queueSize > items.size())
			System.out.println("Bug");
		
	}

	public T pop()
	{
		// it's empty, uh oh
		if( queueSize == 0 )
		{
			System.out.println("it's empty, actually");
			return null;
		}
		// wrap "front" around to 0th index of items
		if(front==(capacity-1))
		{
			front = 0;
			queueSize--;
			return items.get(items.size()-1);

		}
		
		queueSize--;
		return items.get(front++);
		
	}



	private void doubleSize()
	{	
		int oldCapacity = capacity;
		capacity*=2;

		ArrayList<T> biggerBetterContainer = new ArrayList<T>(capacity);
		for(int i=front; i<front+queueSize; i++)
		{
			biggerBetterContainer.add( items.get( i%(oldCapacity) ) );
		}
		for(int i=queueSize; i<capacity; i++)
		{
			biggerBetterContainer.add(null);
		}
		front = 0;
		items = biggerBetterContainer;

	}











	// print stuff
	public void printQueue()
	{
		String item;
		for(int i=front; i<front+queueSize; i++)
		{
			item = items.get( i%capacity ).toString();
			System.out.println( i-front + ": " + item );
		}

	}
	private void printItems()
	{
		for(T item : items)
		{	
			System.out.println(item);
		}
	}












	
	public static void main(String[] args)
	{

		// test -- verify that the first 50 items are multiples of 3, and the next 50 are multiples of 4
		ArrayQueue<Integer> q = new ArrayQueue<Integer>();
		for(int i=0; i<100; i++)
		{
			q.enqueue(i*1000);
		}
		//q.printQueue();
		for(int i=0; i<5; i++)
		{
			for(int j=0; j<50; j++)	
			{
				q.pop();
			}	
			for(int j=0; j<50; j++)	
			{
				q.enqueue( j*i );
			}	

		}
		q.printQueue();

	}

}
