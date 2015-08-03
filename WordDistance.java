import java.util.*;

public class WordDistance
{

	static int[][] edits;
	public static void main(String[] args)
	{
		String A = args[0];
		String B = args[1];
		edits = new int[A.length()+1][B.length()+1];
		for(int i=0; i<=A.length(); i++)
		{
			for(int j=0; j<=B.length(); j++)
			{
				edits[i][j] = 99999;
			}

		}
		edits[1][1] = A.charAt(0)==B.charAt(0) ? 0 : 1;
		

		System.out.println( editDistance(A, B) );
	
	}

	public static int editDistance(String A, String B)
	{
		if(A.length()==0) return B.length();
		if(B.length()==0) return A.length();
		
		//System.out.println("A.length()-1: " + (A.length()-1) + " , B.length()-1: " + (B.length()-1)); 
		int lastCharPlusMaybeOneMaybeNotWhoKnows = A.charAt( A.length()-1) == B.charAt(B.length()-1)? 0 : 1;
		int A_lastChar = A.length()-1;		
		int B_lastChar = B.length()-1;
	

		int x,y,z; // three options to choose min of
		if( edits[A.length()][B.length()-1] == 99999)
		{
			editDistance(A, B.substring(0, B_lastChar) );
		}
		if( edits[A.length()-1][B.length()-1] == 99999)
		{
			editDistance(A.substring(0, A_lastChar), B.substring(0,B_lastChar) );
		}
		if( edits[A.length()-1][B.length()] == 99999)
		{
			editDistance(A.substring(0, A_lastChar), B);
		}		
		
		edits[A.length()][B.length()] = 
			min(
				edits[A.length()][B.length()-1]+1, 
				edits[A.length()-1][B.length()-1]+lastCharPlusMaybeOneMaybeNotWhoKnows, 
				edits[A.length()-1][B.length()]+1);

		System.out.println(A + " to " + B + " : " + edits[A.length()][B.length()]);
		return edits[A.length()][B.length()];	
		
	}


	public static int min(int... args)
	{
		int min = args[0];
		for(int n : args)
		{
			if(n<min) min = n;
		}
		return min;

	}

}
