
public class Node {
		private int key;
		private Node leftChild;
		private Node rightChild;
		private int size;
		private double freq;

		public Node(int n) {
			size = 0;
			key = n;
			leftChild = null;
			rightChild = null;
		}

		public Node (int n, double f) {
			size = 0;
			key = n;
			leftChild = null;
			rightChild = null;
			freq = f;
			
		}

		public void setSize(int i) {
			size = i;
		}


		public int getSize() {
			return size;
		}


		public Node(int n, Node left, Node right) {
			key = n;
			leftChild = left;
			rightChild = right;
		}

		public void setRightChild(Node insert) {
			rightChild = insert;
		}

		public void setLeftChild(Node insert) {
			leftChild = insert;
		}
		
		public double getFreq() {
			return freq;
		}

		public int compareTo(Node r) {
			if (key > r.getKey())
				return 1;
			if (key < r.getKey())
				return -1;
			return 0;
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

	}
