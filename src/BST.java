import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BST {
	private List<Node> input;

	private Method method;
	private Node rootNode;

	public BST(Method m, List<Integer> ints, List<Double> probs) {
		method = m;
		rootNode = null;
		input = new ArrayList<Node>();
		setInput(ints, probs);
	}

	public enum Method {
		GREEDY
	}

	public void insert(Node n) {
		rootNode = insert(rootNode, n);
		
	}

	private Node insert(Node r, Node n) {
		if (r == null)
			return new Node(n.getNumber(), n.getProbability());

		int cmp = n.compareTo(r);
		if (cmp < 0)
			r.setLeftChild(insert(r.getLeftChild(), n));
		else if (cmp > 0)
			r.setRightChild(insert(r.getRightChild(), n));
		else {
			// do nothing
		}
		r.setSize(1 + sizeOf(r.getLeftChild()) + sizeOf(r.getRightChild()));
		return r;
	}

	public void setInput(List<Integer> numbers, List<Double> probabilities) {
		for (int i = 0; i < numbers.size(); i++) {
			input.add(new Node(numbers.get(i), probabilities.get(i)));
		}
		Collections.sort(input, new ProbabilityComparator());

		while (!input.isEmpty()) {
			Node n = Collections.min(input, new ProbabilityComparator());
			insert(n);
			input.remove(n);
		}

	}

	public void print() {
		print(rootNode);
	}
	
	private void print(Node n) {
		
		
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

	class ProbabilityComparator implements Comparator<Node> {
		public int compare(Node a, Node b) {
			if (a.getProbability() > b.getProbability())
				return -1;
			if (a.getProbability() < b.getProbability())
				return 1;
			return 0;
		}
	}

	
}
