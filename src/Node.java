
public class Node implements Comparable<Node> {
	private int number;
	private double probability;
	private int size;
	
	private Node leftChild;
	private Node rightChild;
	private Node parent;
	
	public Node(int n, double p) {
		number = n;
		probability = p;
		leftChild = null;
		rightChild = null;
		parent = null;
	}
	public void setSize(int x) {size = x;}
	public void setParent(Node n) { parent = n; }
	public void setLeftChild(Node n) {
		leftChild = n;
		leftChild.setParent(this);
	}
	public void setRightChild(Node n) {
		rightChild = n;
		rightChild.setParent(this);
	}
	
	public int getSize() {return size;}
	public Node getLeftChild() { return leftChild; }
	public Node getRightChild() { return rightChild; }

	public int getNumber() { return number; }
	public double getProbability() { return probability; }

	@Override
	public int compareTo(Node o) {
		if (this.number > o.getNumber())
			return 1;
		if (this.number < o.getNumber())
			return -1;
		return 0;
	}

	void print(int p) {
		
		for(int i = 0; i < p; i++)
			System.out.print(" ");
		
		System.out.println(probability);
		if (leftChild != null)
			leftChild.print(p+1);
		else {
			if (rightChild != null) {
			for(int i = 0; i < p+1; i++)
				System.out.print(" ");
			System.out.println("null");
			}
		}
		
		if (rightChild != null)
			rightChild.print(p+1);
		else {
			if (leftChild != null) {
			for(int i = 0; i < p+1; i++)
				System.out.print(" ");
			System.out.println("null");
			}
		}
	}
	public String toString() {
		return "Value: " + this.number + ", Probability: " + this.probability;
	}
}
