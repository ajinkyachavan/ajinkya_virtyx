import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FlattenArray {

	public static void main(String[] args) {
			
		int[][][] arr = new int[][][] { {{1}, {2}, {3, 4, 5} }, {{4, 6, 7}}  };
				
		// I have extended the problem to consider the [1,2..] as [{1}, {2}]
		// which can be generalized to the given problem.
		// There are 3 solutions below: Intuitive approach(70715136 nanoseconds), 
		// Recursive(1171914 ns) and String manipulation (3554277 ns)
		
		// intuitive 
		// consider the array or array as 3 dimensional matrix.
		// for each i,j -> print all k's
		int[] newArr = intuitiveApproach(arr);
		print(newArr, "intuitive");
		
		// recursion 
		// increment from k to i
		List<Integer> newArrList = new ArrayList<>();
		newArr = recursionApproach(arr, 0, 0, 0, newArrList);
		print(newArr, "recursion");
		
		// String manipulation 
		// convert entire array to string. remove [] and stream the data to int array
		newArr = stringManipulationApproach(arr);
		print(newArr, "String manipulation");
	}
	
	
	
	// intuitive
	public static int[] intuitiveApproach(int[][][] arr) {
		
		List<Integer> newArrList = new ArrayList<>();
		
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr[i].length; j++) {
				for(int k=0; k<arr[i][j].length; k++) {
					newArrList.add(arr[i][j][k]);
				}
			}
		}
		
		
		
		int[] newArr = newArrList.stream().mapToInt(Integer::intValue).toArray();
		
		return newArr;
	}
	
	
	
	
	// recursive
	private static int[] recursionApproach(int[][][] arr, int i, int j, int k, List<Integer> newArrList) {
		
			if(i < arr.length) {
				if(j < arr[i].length) {						
					if(k < arr[i][j].length) {
						newArrList.add(arr[i][j][k]);
						recursionApproach(arr, i, j, k+1, newArrList);
						recursionApproach(arr, i, j+1, k, newArrList);
						recursionApproach(arr, i+1, j, k, newArrList);
					} 
				}
			}
		
		int[] newArr = newArrList.stream().mapToInt(Integer::intValue).toArray();
		
		return newArr;
	}
	
	
	
	
	
	// string manipulation
	public static int[] stringManipulationApproach(int[][][] arr) {

		String inputString = Arrays.deepToString(arr);
		inputString = inputString.replaceAll("[^\\d,]", "");
		
		int[] newArr = Arrays.stream(inputString.split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
		
		return newArr;
	}
	
	
	
	
	
	private static void print(int[] newArr, String string) {
		System.out.println();
		System.out.println("The result after "+string+" approach is: ");
		System.out.println();
		
		for(Integer element: newArr) 
			System.out.print(element+" ");
	
		
		System.out.println();
	}

}
