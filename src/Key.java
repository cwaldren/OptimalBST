

public class Key implements Comparable<Key> {
		private int value;
		private double freq;

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
		
		public void setFreq(double d) {
			freq = d;
		}

	
		public int compareTo(Key k) {
			if (value > k.getValue())
				return 1;
			if (value < k.getValue())
				return -1;
			return 0;
		}

	
		public String toString() {
			return getValue() + " " + getFreq();
		}
	}