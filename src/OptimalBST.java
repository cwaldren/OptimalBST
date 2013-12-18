
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//credit to github

public class OptimalBST {
	private List<Key> keys;

	public OptimalBST() {
		keys = new ArrayList<Key>();
	}
	

	public OptimalBST(List<Key> k) {
		keys = k;
	}
	
	public void addKey(int n, double freq) {
		keys.add(new Key(n, freq));
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
		BST t = new BST(-1);
		
		if (left <= right) 
			if (bs[left][right] != null) 
				return bs[left][right];
			
		double cost = 0;
		for (int i = left; i <= right; i++) {
			cost += keys[i].getFreq();
		}
		
		if (right <= left || left >= right) {
			if (left == right) {
				BST temp = new BST(-1);
				t = new BST(keys[right].getValue(), temp.getRootNode(), temp.getRootNode());
				t.setCost(cost);
			} else {
				return new BST(-1);
			}
		} else {
			for (int i = left; i <= right; i++) {
				BST leftTree = optimize(keys, left, i - 1, bs);
				BST rightTree = optimize(keys, i + 1, right, bs);
				
				BST testTree = new BST(keys[i].getValue(), leftTree.getRootNode(), rightTree.getRootNode());
				testTree.setCost(leftTree.getCost() + rightTree.getCost() + cost);

				if (t.getRootNodeKey() == -1 || t.getCost() > testTree.getCost()) {
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
	
	
	

}
