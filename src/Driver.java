


public class Driver {
	public static void main(String[] args) {
		OptimalBST bst = new OptimalBST(OptimalBST.Method.GREEDY);
		bst.insert(new Node(5, .2));
		bst.insert(new Node(2, .5));
		bst.insert(new Node(2, .1));
		bst.insert(new Node(5, .7));
	}
}
