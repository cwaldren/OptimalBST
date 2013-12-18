public enum Distribution {
		RANDOM { protected int change(int s) {
			return s*s;
		}}, 
		UNIFORM { protected int change(int s) {
			return s;
		}}, 
		SHARPLY_BIASED { protected int change(int s) {
			return s;
		}},
		SLIGHTLY_BIASED { protected int change(int s) {
			return s;
		}},
		SYMMETRIC { protected int change(int s) {
			return s;
		}};
		
		
		protected abstract int change(int s);
		
		public int transform(int s) {
			return change(s);
		}
}