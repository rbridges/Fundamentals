

public class BinaryTree<T>
{

	private Node root;
	public Node getRoot()
	{
		return root;
	}

	public void insert(T insertVal)
	{
		insert( new Node<T>(insertVal) );
	}
	public void insert(Node insertMe)
	{
		insert(insertMe, root);
	}
	private void insert(Node insertMe, Node currentNode)
	{
		// insertMe is already in the tree
		if( insertMe.compareTo(currentNode) == 0 )
		{
			currentNode.incrMultiplicity();
		}
	
		// insertMe is greater than the currentNode
		else if( insertMe.compareTo(currentNode) > 0 )
		{
			insert(insertMe, currentNode.getRight());
		}
		
		// insertMe is less than the currentNode	
		else 
		{
			insert(insertMe, currentNode.getLeft());
		}
	
	}

		/*
			insert
			removal
			test for membership
			height
			
			printers
			
			to list

		*/


}
