/*
 * Sarah Hall
 * CSCI 232
 * program 3 part 2
 */
package program3;

import java.util.Arrays;
import java.util.Collections;

public class part2Algorithm {
	
	public static Integer[] getMinCount (Integer[] coinDoms, int value) throws Exception {
		Integer[] count = new Integer[0];
		Integer[] prevList = new Integer[0];
		int index = 0;
		
		Arrays.sort(coinDoms, Collections.reverseOrder());
		// From https://stackoverflow.com/questions/20251762/sorting-an-int-array-from-highest-to-lowest
		
		for(int i = 0; i < coinDoms.length; i++) {
			while(value >= coinDoms[i]) {
				count = new Integer[index + 1];
				for(int j = 0; j < prevList.length; j++) {
					count[j] = prevList[j];
				}
				count[index] = coinDoms[i];
				prevList = count;
				index++;
				value -= coinDoms[i];
			}
		}
		
		
		return count;
	}

}
