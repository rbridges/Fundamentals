public class Node<T extends Comparable> implements Comparable
{
	
	public Node(T data)
	{
		this.data = data;
	}


	// members
	private T data = null;
	public T getData()
	{
		return data;
	}
	
	private Node left = null;
	public Node getLeft()
	{
		return left;
	}

	private Node right = null;
	public Node getRight()
	{
		return right;
	}

	private int multiplicty = 1;
	public void incrMultiplicty()
	{
		multiplicity++;
	}


	
	public int compareTo(Object node)
	{
		if( (node instanceof Node) )
			return (this.data).compareTo( ((Node)node).getData() );
		
		
		else
			return (this.data).compareTo( node );
	
	}



	/* test
	public static void main(String[] args)
	{
		Node<Integer> a = new Node<Integer>(10); Node<Integer> b = new Node<Integer>(10); 
		Node<String> c = new Node<String>("c"); Node<String> d = new Node<String>("d");
		System.out.println(d.compareTo(c));

	} */





}
