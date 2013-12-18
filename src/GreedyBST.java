import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GreedyBST {
	private List<Key> keys;

	public GreedyBST() {
		keys = new ArrayList<Key>();
	}
	
	public GreedyBST(List<Key> k) {
		keys = k;
	}
	
	public void addKey(int n, double freq) {
		keys.add(new Key(n, freq));
	}
	
	private class ProbabilityComparator implements Comparator<Key> {
		public int compare(Key a, Key b) {
			if (a.getFreq() > b.getFreq())
				return -1;
			if (a.getFreq() < b.getFreq())
				return 1;
			return 0;
		}
	}
	
	public BST optimize() {
		BST greedy = new BST();
		Key[] sorted = sortKeys();
		for (Key k : sorted) {
			greedy.insert(new Node(k.getValue(), k.getFreq()));
		}

		double totalCost = 0;
		for (Key k : sorted) {
			totalCost += greedy.getCostNode(k.getValue());
		}
		greedy.setCost(totalCost);
		return greedy;
	}
	
	public Key[] sortKeys() {
		Collections.sort(keys, new ProbabilityComparator());
		Key[] sorted = new Key[keys.size()];
		keys.toArray(sorted);
		return sorted;
	}
	
	public void clearKeys() {
		keys.clear();
	}
}
