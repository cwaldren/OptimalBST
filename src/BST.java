

public class BST {

	private Node rootNode;
	private double cost;

	public BST() {
		rootNode = null;
	}

	public BST(int n) {
		rootNode = new Node(n);
	}

	public BST(int n, Node left, Node right) {
		rootNode = new Node(n, left, right);
	}


	public void insert(Node n) {
		 rootNode = insert(rootNode, n);
	}

	public void setCost(double c) {
		cost = c;
	}


	public void printTree() {
		
		printTree(rootNode, "");
		System.out.println("Cost: " + String.format("%.3g", cost));
	}

	private void printTree(Node current, String padding) {

		padding += " ";

		System.out.println(padding + (current.getKey() == -1 ? "$" : current.getKey()));
		if (!(current.getLeftChild() == null || current.getLeftChild().getKey() == -1) || 
			!(current.getRightChild() == null || current.getRightChild().getKey() == -1)) {
		
			if (current.getLeftChild() != null)
				printTree(current.getLeftChild(), padding);
			else
				System.out.println(padding + " $");
			if (current.getRightChild() != null)
				printTree(current.getRightChild(), padding);
			else
				System.out.println(padding + " $");
		}
		
	
	}

	public double getCostNode(int k) {
		Node current = rootNode;
		int depth = 0;
	
		while (current != null) {
			depth++;
			if (current.getKey() == k)
				return depth * current.getFreq();
			else if (k < current.getKey()) {
				current = current.getLeftChild();
			} else {
				current = current.getRightChild(); 
			}
		}
		return 0;
		
		
	}

	 private Node insert(Node r, Node n) {
		 if (r == null)
			 return new Node(n.getKey(), n.getFreq());
		
		 int cmp = n.compareTo(r);
		 if (cmp < 0)
			 r.setLeftChild(insert(r.getLeftChild(), n));
		 else if (cmp > 0)
			 r.setRightChild(insert(r.getRightChild(), n));
		 else {
			 // do nothing, don't insert
		 }
		 r.setSize(1 + sizeOf(r.getLeftChild()) + sizeOf(r.getRightChild()));
		 return r;
	 }


	 public int size() {
		 return sizeOf(rootNode);
	 }
	
	 private int sizeOf(Node n) {
		 if (n == null)
			 return 0;
		 else
			 return n.getSize();
	 }

	public double getCost() {
		return cost;
	}

	public Node getRootNode() {
		return rootNode;
	}

	public int getRootNodeKey() {
		return rootNode.getKey();
	}

	@Override
	public boolean equals(Object o) {
		BST other = (BST) o;
		return (Math.abs(cost - other.getCost()) <= .001) && (rootNode.getKey() == other.getRootNode().getKey());
	}

	
	
	
	
}
