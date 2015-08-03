import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);
        int size = scan.nextInt();
        int[] array = new int[size];
        int[] array2 = new int[size];
        int i = 0;

	Counter c = new Counter();


        while(scan.hasNextInt())
        {
            int next = scan.nextInt();
            array[i] = next; array2[i] = next;
            i++; 
        }
	

	System.out.println( c.insertionSortSteps(array) -  c.quickSortSteps(array2) );
	

        //return insertionSortSteps(array,size) - quicksortSteps(array, size);
          
        
    }
    
    
    
    
    
    
    
    
    

}








class Counter
{
	
int qs_steps = 0;
int is_steps = 0;

    	public int quickSortSteps(int[] array) 
    	{
		quicksort(array, 0, array.length-1);
		return qs_steps;
		
	}
	
	private void quicksort(int[] array, int left, int right)
	{
		if( left < right )
		{
			int i = L_Partition(array, left, right);
			quicksort(array, left, i-1);
			quicksort(array, i+1, right);
		}

	}





	public int insertionSortSteps(int[] array)
	{
		insertionSort(array);
		return is_steps;

	}

	private void insertionSort(int[] array)
	{
		boolean swapped = true;
		int forward,backward = 0;
		while( swapped )
		{
			
			/*for(int a=0; a < array.length; a++)
			{
				System.out.print(array[a] + " ");
			}
			System.out.println(); */

			swapped = false;
			forward = 1;
			for(; forward<array.length; forward++)
			{
				int current = array[forward];
				int prev = array[forward-1];
				backward = forward - 1;
				if( current< prev )
				{
					while( scootRight(array, backward) )
					{
						if( array[backward] >= current){ break;}	
						backward--;
					}
					
					array[backward] = current;
					is_steps++;					
					swapped = true;
					
				}
			}

		}
		
	}
	private boolean scootRight(int[] array, int originalPosition)
	{
		if( originalPosition==array.length-1 || originalPosition<0 ) return false;  
		array[originalPosition+1] = array[originalPosition];
		return true;

	}

    
    // i_a,b are indexes for the two swapMes in the array
    public void swap(int[] array, int i_a, int i_b)
    {	
	if(i_a == i_b) return;
        array[i_a] = array[i_a] + array[i_b]; // a has both
        array[i_b] = array[i_a] - array[i_b]; // b gets both minus itself, aka a_orig
        array[i_a] = array[i_a] - array[i_b]; // a gets both minus a_orig, aka b_orig
      
    }
    
    
    // Lomuto Partition, returning swapCount
    public int L_Partition(int[] array, int left, int right)
    {
        int swapCount = 0;
        
        int pivot = array[right];
        int i = left-1;
        int j = left;
        
        for(; j<right; j++)
        {
            if( array[j]<=pivot )
            {
                qs_steps++;
                swap(array, ++i, j);
            }
        }
	qs_steps++;
        swap(array, ++i, right);
        return i;
        
        
        
        
    }
    




}
