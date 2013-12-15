import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;




public class Driver {
	
	public static void main(String[] args) {
		
		//Greedy method
		
		
		List<Integer> numbers = new ArrayList<Integer>(
				Arrays.asList(1, 2, 3, 4, 5, 6, 7)
		);
		List<Double> probabilities = new ArrayList<Double>(
				Arrays.asList(.22, .18, .20, .05, .25, .02, .08)
		);
	
		BST bst = new BST(BST.Method.GREEDY, numbers, probabilities);
		bst.print();
		bst.doOptimal();
		
		
			
	}
}
