
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class OptimalBST {
	private List<Key> keys;

	public OptimalBST() {
		keys = new ArrayList<Key>();
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
	public Key[] sortKeys() {
		Collections.sort(keys);
		Key[] sorted = new Key[keys.size()];
		keys.toArray(sorted);
		return sorted;
	}
	
	public BST optimize() {
		Key[] keys = sortKeys();
		BST[][] bs = new BST[keys.length][keys.length];
		return optimize(keys, 0, keys.length - 1, bs);
		
	}
	
	private BST optimize(Key[] keys, int left, int right, BST[][] bs) {
		BST t = new BST(0);
		
		if (left <= right) 
			if (bs[left][right] != null) 
				return bs[left][right];
			
		int cost = 0;
		for (int i = left; i <= right; i++) {
			cost += keys[i].getFreq();
		}
		
		if (right <= left || left >= right) {
			if (left == right) {
				BST temp = new BST(0);
				t = new BST(keys[right].getValue(), temp.getRootNode(), temp.getRootNode());
				t.setCost(cost);
			} else {
				return new BST(0);
			}
		} else {
			for (int i = left; i <= right; i++) {
				BST leftTree = optimize(keys, left, i - 1, bs);
				BST rightTree = optimize(keys, i + 1, right, bs);
				
				BST testTree = new BST(keys[i].getValue(), leftTree.getRootNode(), leftTree.getRootNode());
				testTree.setCost(leftTree.getCost() + rightTree.getCost() + cost);

				if (t.getRootNodeKey() == 0 || t.getCost() > testTree.getCost()) {
					t = testTree;
				}
			}
		}

		bs[left][right] = t;
		return t;
		
	}
	public void clearKeys() {
		keys.clear();
	}
	
	
	private class Key implements Comparable<Key> {
		private int value;
		private double freq;

		public Key() {
			value = 0;
			freq = 0;
		}

		public Key(int n, double f) {
			value = n;
			freq = f;
		}

		public int getValue() {
			return value;
		}

		public double getFreq() {
			return freq;
		}

		// Override compareTo
		public int compareTo(Key k) {
			if (value > k.getValue())
				return 1;
			if (value < k.getValue())
				return -1;
			return 0;
		}

		public String toString() {
			return "(" + value + "," + freq + ")";
		}

	}

}
