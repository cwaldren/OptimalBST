import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BST {

	private Method method;
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

	public enum Method {
		GREEDY
	}

	// public void insert(Node n) {
	// rootNode = insert(rootNode, n);
	//
	// }

	public void setCost(double c) {
		cost = c;
	}

	public void printTree() {
		printTree(rootNode, "");
	}

	private void printTree(Node currentNode, String tabs) {
		if (currentNode != null) {
			tabs += "\t";
			printTree(currentNode.getRightChild(), tabs);
			System.out.println(tabs + currentNode.getKey() + "\n");
			printTree(currentNode.getLeftChild(), tabs);
		}
	}

	// private Node insert(Node r, Node n) {
	// if (r == null)
	// return new Node(n.getNumber(), n.getProbability());
	//
	// int cmp = n.compareTo(r);
	// if (cmp < 0)
	// r.setLeftChild(insert(r.getLeftChild(), n));
	// else if (cmp > 0)
	// r.setRightChild(insert(r.getRightChild(), n));
	// else {
	// // do nothing
	// }
	// r.setSize(1 + sizeOf(r.getLeftChild()) + sizeOf(r.getRightChild()));
	// return r;
	// }

	// public void setInput(List<Integer> numbers, List<Double> probabilities) {
	// for (int i = 0; i < numbers.size(); i++) {
	// input.add(new Node(numbers.get(i), probabilities.get(i)));
	// }
	// Collections.sort(input, new ProbabilityComparator());
	// List<Node> inputTemp = new ArrayList<Node>(input);
	//
	// while (!inputTemp.isEmpty()) {
	// Node n = Collections.min(inputTemp, new ProbabilityComparator());
	// insert(n);
	// inputTemp.remove(n);
	// }
	//
	// }

	//
	// public int size() {
	// return sizeOf(rootNode);
	// }
	//
	// private int sizeOf(Node n) {
	// if (n == null)
	// return 0;
	// else
	// return n.getSize();
	// }

	public double getCost() {
		return cost;
	}

	public Node getRootNode() {
		return rootNode;
	}

	public int getRootNodeKey() {
		return rootNode.getKey();
	}

	private class Node {
		private int key;
		private Node leftChild;
		private Node rightChild;

		public Node(int n) {
			key = n;
			leftChild = null;
			rightChild = null;
		}

		public Node(int n, Node left, Node right) {
			key = n;
			leftChild = left;
			rightChild = right;
		}

		public int getKey() {
			return key;
		}

		public Node getLeftChild() {
			return leftChild;
		}

		public Node getRightChild() {
			return rightChild;
		}

		// void print(int p) {
		//
		// for(int i = 0; i < p; i++)
		// System.out.print(" ");
		//
		// System.out.println(probability);
		// if (leftChild != null)
		// leftChild.print(p+1);
		// else {
		// if (rightChild != null) {
		// for(int i = 0; i < p+1; i++)
		// System.out.print(" ");
		// System.out.println("null");
		// }
		// }
		//
		// if (rightChild != null)
		// rightChild.print(p+1);
		// else {
		// if (leftChild != null) {
		// for(int i = 0; i < p+1; i++)
		// System.out.print(" ");
		// System.out.println("null");
		// }
		// }
		// }

	}

}
