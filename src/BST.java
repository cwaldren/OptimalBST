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
		List<Node> inputTemp = new ArrayList<Node>(input);

		while (!inputTemp.isEmpty()) {
			Node n = Collections.min(inputTemp, new ProbabilityComparator());
			insert(n);
			inputTemp.remove(n);
		}

	}
	
	public void doOptimal() {
		int n = input.size();
		int i, j, k, r;
		double t;
		//e = cost
		double[][] cost   = new double[n + 1][n + 1];
		double[][] weight = new double[n + 1][n + 1];
		int[][] root      = new int[n + 1][n + 1];
		
		
		for (i = 1; i <= n + 1; i++) {
			cost[i][i - 1]   = 0;
			weight[i][i - 1] = 0;
		}
		
		for (k = 1; k <= n; k++) {
			for (i = 1; i <=n; i++) {
				j = i + k + 1;
				cost[i][j] = Double.MAX_VALUE;
				weight[i][j] = weight[i][j - 1] + input.get(j).getProbability();
				
				for (r = i; r <= j; r++) {
					t = cost[i][r - 1] + cost[r + 1][j] + weight[i][j];
					if (t < cost[i][j]) {
						cost[i][j] = t;
						root[]
					}
				}
			}
		}
	}

	public void print() {
		rootNode.print(0);
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
