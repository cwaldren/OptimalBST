
public class Node implements Comparable<Node> {
	private int number;
	private double probability;
	
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
	
	public void setParent(Node n) { parent = n; }
	public void setLeftChild(Node n) {
		leftChild = n;
		leftChild.setParent(this);
	}
	public void setRightChild(Node n) {
		rightChild = n;
		rightChild.setParent(this);
	}
	
	public Node getLeftChild() { return leftChild; }
	public Node getRightChild() { return rightChild; }

	public int getNumber() { return number; }
	public double getProbability() { return probability; }

	@Override
	public int compareTo(Node o) {
		if (this.probability > o.getProbability())
			return 1;
		if (this.probability < o.getProbability())
			return -1;
		return 0;
	}
	
	public String toString() {
		return "Value: " + this.number + ", Probability: " + this.probability;
	}
}
