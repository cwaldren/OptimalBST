import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;




public class Driver {
	
	public static void main(String[] args) {
		

		OptimalBST opt = new OptimalBST();
		int[] numbers = new int[] {1, 2, 3, 4, 5, 6, 7};
		double[] probabilities = new double[] {.22, .18, .20, .05, .25, .02, .08};

		
		for (int i = 0; i < numbers.length; i++) 
			opt.addKey(numbers[i], probabilities[i]);
		
		
		BST best = opt.optimize();
		best.printTree();
			
	}
}
