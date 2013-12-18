import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Driver {
	static final int MAX_DISTRIBUTIONS = 100;
	static final int MAX_SETS = 7;
	static final int MAX_TRIALS = MAX_DISTRIBUTIONS * 50;
	static final Random g = new Random();

	static void normalize(List<Key> items, double sum) {
		for (Key k : items)
			k.setFreq(k.getFreq() / sum);
	}

	static List<Key> generateProbDistribution(int n, Distribution dis) {

		List<Key> items = new ArrayList<Key>();
		double sum = 0;
		// Random distribution
		if (dis.equals(Distribution.RANDOM)) {

			for (int i = 1; i <= n; i++) {
				double d = g.nextDouble();
				items.add(new Key(i, d));
				sum += d;
			}
			normalize(items, sum);
		}

		// Uniform distribution
		if (dis.equals(Distribution.UNIFORM)) {
			for (int i = 1; i <= n; i++)
				items.add(new Key(i, (1.0 / n)));
		}

		// Sharp bias to the left
		if (dis.equals(Distribution.SHARPLY_BIASED)) {
			sum = 0;
			for (int i = 1, j = n; i <= n; i++, j--) {
				items.add(new Key(i, j));
				sum += j;
			}

			normalize(items, sum);
		}

		//Slight bias
		if (dis.equals(Distribution.SLIGHTLY_BIASED)) {
			sum = 0;
			int hump = (int) Math.round(n / 4.0);
			hump = (hump == 0 ? 1 : hump);
			if (n == 1) {
				items.add(new Key(1, 1));
			} else {
				for (int i = 1; i < hump; i++) {
					double j = 1 + (((i - 1) * n) / (hump + 1.0));
					items.add(new Key(i, j));
					sum += j;
				}

				for (int i = hump; i <= n; i++) {
					double j = n - ((i - hump) * (n - 1.0) / ((n - hump)));
					items.add(new Key(i, j));
					sum += j;
				}
				normalize(items, sum);
			}
		}
		
		//Symmetric triangle distribution
		if (dis.equals(Distribution.SYMMETRIC)) {
			sum = 0;
			int hump = (int) Math.round(n / 2.0);
			hump = (hump == 0 ? 1 : hump);
			if (n == 1) {
				items.add(new Key(1, 1));
			} else {
				for (int i = 1; i < hump; i++) {
					double j = 1 + (((i - 1) * n) / (hump + 1.0));
					items.add(new Key(i, j));
					sum += j;
				}

				for (int i = hump; i <= n; i++) {
					double j = n - ((i - hump) * (n - 1.0) / ((n - hump)));
					items.add(new Key(i, j));
					sum += j;
				}
				normalize(items, sum);
			}
		}

		return items;
	}

	static void printProbDistribution(List<Key> keys) {
		System.out.println("-------------------");
		System.out.println("Number	Probability");
		System.out.println("-------------------");
		for (Key k : keys) {
			System.out.println(k.getValue() + "	"
					+ String.format("%.3g", k.getFreq()));
		}
		System.out.println("-------------------");
	}

	public static void main(String[] args) throws InterruptedException {

		OptimalBST optimal = new OptimalBST();
		GreedyBST greedy = new GreedyBST();
		double[] probabilities = new double[] { .22, .18, .20, .05, .25, .02,
				.08 };
		int[] numbers = new int[] { 1, 2, 3, 4, 5, 6, 7 };

		for (int i = 0; i < numbers.length; i++) {
			optimal.addKey(numbers[i], probabilities[i]);
			greedy.addKey(numbers[i], probabilities[i]);
		}

		System.out.println("Optimal");
		BST best = optimal.optimize();
		best.printTree();
		System.out.println("-------------------------------------------");

		System.out.println("\nGreedy");
		BST okay = greedy.optimize();
		okay.printTree();
		System.out.println("-------------------------------------------");

		System.out.println("- Random - ");
		System.out.println("With " + MAX_TRIALS
				+ " tests per key amount, % of greedy BSTs that were optimal:");
		testDistributions(Distribution.RANDOM);

		System.out.println("\n- Even - ");
		System.out.println("With " + MAX_TRIALS
				+ " tests per key amount, % of greedy BSTs that were optimal:");
		testDistributions(Distribution.UNIFORM);

		System.out.println("\n- Sharply Biased - ");
		System.out.println("With " + MAX_TRIALS
				+ " tests per key amount, % of greedy BSTs that were optimal:");
		testDistributions(Distribution.SHARPLY_BIASED);

		System.out.println("\n- Slightly Biased - ");
		System.out.println("With " + MAX_TRIALS
				+ " tests per key amount, % of greedy BSTs that were optimal:");
		testDistributions(Distribution.SLIGHTLY_BIASED);
		
		System.out.println("\n- Symmetric - ");
		System.out.println("With " + MAX_TRIALS
				+ " tests per key amount, % of greedy BSTs that were optimal:");
		testDistributions(Distribution.SYMMETRIC);
		
//		List<Key> test = generateProbDistribution(8, Distribution.SYMMETRIC);
//		for (Key k : test) {
//			System.out.println(k);
//		}


	}

	private static void testDistributions(Distribution dist) {
		DecimalFormat df = new DecimalFormat("###.##");
		DecimalFormat df2 = new DecimalFormat("#.##");
		for (int s = 1; s <= MAX_SETS; s++) {
			// For every set
			double average = 0;
			double successCostAverage = 0;
			int successCostCounter = 0;
			double failCostAverage = 0;
			int failCostCounter = 0;
			double optimalCostAverage = 0;
			int optimalCostCounter = 0;
			for (int t = 0; t < MAX_TRIALS; t++) {
				// We do MAX_TRIALS trials, which will be 100 (as per CB
				// instruction ) * some amount to get a better average
				List<Key> keys = generateProbDistribution(
						dist.change(s), dist);
				GreedyBST g = new GreedyBST(keys);
				OptimalBST o = new OptimalBST(keys);
				BST greed = g.optimize();
				BST opt = o.optimize();
				if (greed.equals(opt)) {
					average++;
					successCostAverage += greed.getCost();
					successCostCounter++;
				} else {
					failCostAverage += greed.getCost();
					failCostCounter++;
					optimalCostAverage += opt.getCost();
					optimalCostCounter++;
				}
			}
			successCostAverage /= (double)successCostCounter;
			failCostAverage /= (double)failCostCounter;
			optimalCostAverage /= (double)optimalCostCounter;
			average /= MAX_TRIALS;
			System.out.println(dist.change(s) + " keys: "
					+ df.format(average * 100) + "%");
			if (!Double.isNaN(successCostAverage)) {
				System.out.println("	Average cost of successeful greedy BST: " + df2.format(successCostAverage));
			} else {
				System.out.println("	(no successes)");
			}
			if (!Double.isNaN(failCostAverage)) {
				System.out.println("	Average cost of failed greedy BST: " + df2.format(failCostAverage));
				System.out.println("	When it failed, the average cost of the optimal tree was: " + df2.format(optimalCostAverage));
			} else {
				System.out.println("	(no fails)");
			}
			
		}

	}

}
