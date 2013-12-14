
public class OptimalBST {

		private Method method;
		private Node rootNode;
		
		
		public OptimalBST(Method m) {
			method = m;
			rootNode = null;
		}
		
		public enum Method {
			GREEDY
		}
		
		public void insert(Node n) {
			rootNode = insert(rootNode, n);
		}
		
		private Node insert(Node r, Node n) {
			if (r == null)
				r = new Node(n.getNumber(), n.getProbability());
			else {
				int cmp = n.compareTo(r);
				if (cmp < 0)
					r.setLeftChild(insert(r.getLeftChild(), n));
				else if (cmp > 0)
					r.setRightChild(insert(r.getRightChild(), n));
				else  {
					//do nothing
				}
				
				
			}
			return r;
		}
		
		
		
}
