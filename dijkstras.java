import java.io.*;
import java.util.*;

public class dijkstras {

    public static void main(String[] args) {

	// initialization
        Scanner in = new Scanner(System.in);
        int num_tests = in.nextInt();
	for(int t=0; t<num_tests; t++)
	{

		int num_nodes = in.nextInt();
		int num_edges = in.nextInt();
		Nodes nodes = new Nodes();
		for(int i=0; i<num_edges; i++)
		{
		    int thisNode = in.nextInt();
		    int target = in.nextInt();
		    int weight = in.nextInt();
		    if( !nodes.containsKey(thisNode) ) 
		    {
			nodes.put( thisNode, new Node(thisNode));
		    }
		    if( !nodes.containsKey(target) ) 
		    {
			nodes.put( target, new Node(target));
		    }
		    nodes.get(thisNode).addEdge( target , weight );
		    nodes.get(target).addEdge( thisNode, weight );
		}
	
	

		// Run Dijkstras for each of the test cases
		for(int x=0; x<num_tests; x++)
		{    
		    // init the graph
		    int startNode = in.nextInt();
		    nodes.init(startNode);   
		    

		    // Dijkstras
		    while( nodes.hasUnvisited() )
		    {               
			Node currentNode = nodes.bestUnvisited();
			while( currentNode.hasNextEdge() )
			{
			    Edge currentEdge = currentNode.nextEdge();
			    Node target = nodes.get( currentEdge.Destination );
			    
			    int newDistance = currentNode.MinDistanceTo + currentEdge.Weight;
			    if( newDistance < target.MinDistanceTo )
			    {
				nodes.update(target.Number, newDistance);
			    } 
			}
			nodes.markVisited(currentNode.Number);

		    }

		    // print the distances
		    for(int i=1; i<=nodes.size(); i++)
		    {
			if(i==startNode) continue;
			if( nodes.get(i).MinDistanceTo > 350)
			      System.out.print( "-1 " );
			else
			    System.out.print( nodes.get(i).MinDistanceTo + " " );
		    }
		    System.out.println();
		}
	
	    }
	}
}

class Node implements Comparable
{
    public int Number = -1;
    public boolean Visited = false;
    public ArrayList<Edge> edges = new ArrayList<Edge>();
    int edgeIndex = 0;
    public int MinDistanceTo;
    
    public Node(int number)
    {
	this.Number = number;
    }
   
 
    public void addEdge(int target, int weight)
    {
        edges.add( new Edge(target, weight) );
    }
    
    public boolean hasNextEdge()
    {
        if(edgeIndex >= edges.size())
        {
            edgeIndex = 0;
            return false;
        }
        return true;
    }
    public Edge nextEdge()
    {
        
        return edges.get(edgeIndex++);
    }
    
    public String toString()
    {
        return ( MinDistanceTo + ", " + edges.size());

    }   


        public int compareTo(Object other)
    {
        Node otherNode = (Node)other;
        return this.MinDistanceTo-otherNode.MinDistanceTo;
        
    }	 
}

class Nodes
{
	Hashtable<Integer, Node> nodes = new Hashtable<Integer, Node>();
	PriorityQueue<Node> unvisited = new PriorityQueue<Node>();
	
	// init to set everything unvisited, mark all but start node at infinity distance
	public void init(int startNode)
	{
		unvisited.clear();
		for(Node node : nodes.values())
		{
			node.MinDistanceTo = 351; // problem statement specifies 350 is largest weight
			unvisited.add(node);
		}
		update(startNode, 0);
		
	}


	public void put(int nodeNumber, Node node)
	{
		nodes.put(nodeNumber,node);
	}

	// get(number) returns the node of that number
	public Node get(int number)
	{
		return nodes.get(number);
	}
	
	public boolean containsKey(int number)
	{	
		return nodes.containsKey(number);
	}
	
	public int size()
	{
		return nodes.size();
	}



	// markVisited(number) just removes the node of that number from the unvisited priority queue
	public void markVisited(int number)
	{
		unvisited.remove( nodes.get(number) );
	}

	// update(node, weight) to set MinDistanceTo, and reprioritize (remove and reinsert?) the unvisited queue as to just pop the lowest O(1) later
	public void update(int nodeNumber, int newWeight)
	{
		Node toUpdate = nodes.get(nodeNumber);
		unvisited.remove( toUpdate );
		toUpdate.MinDistanceTo = newWeight;
		unvisited.add( toUpdate );
	}

	// bestUnvisited() returns head of the unvisited priority queue, but does not remove
	public Node bestUnvisited()
	{
		return unvisited.peek();
	}
	
	public boolean hasUnvisited()
	{
		return unvisited.size() > 0;
	}
	
}

// imbalanced in the sense it only has a destination, but in implementation will always be tied to a source node
class Edge 
{
    public int Destination;
    public int Weight;
    
    public Edge(int dest, int weight)
    {
        Destination = dest;
        Weight = weight;
    }
    

    
}

