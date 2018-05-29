/*
 * Sarah Hall
 * CSCI 232
 * Program 2
 */

package program2;
import java.util.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.Object;



public class Huffman {

	 static Node root; // Holds root of nodes
	static Node node;
	static Node newRoot;
	static String codedString = "";
	  static String theString; // The input
	    static String encoded = ""; // The binary message
	    static String output = ""; // Binary to English string

	public static void main(String[] args) throws IOException {
		@SuppressWarnings("resource")
		Scanner file = new Scanner(new File("input.txt"));
		String message = file.nextLine();
		char[] msgChar = message.toCharArray();
		ArrayList<Character> characters = new ArrayList<Character>();

		/*
		 * Get a List of all the chars which are present in the string No
		 * repeating the characters!
		 */
		for (int i = 0; i < msgChar.length; i++) {
			if (!(characters.contains(msgChar[i]))) {
				characters.add(msgChar[i]);
			}
		}

		/* Print out the available characters */
		// System.out.println(characters);

		/* Count the number of occurrences of Characters */
		int[] countOfChar = new int[characters.size()];

		/* Fill The Array Of Counts with one as base value */
		for (int x = 0; x < countOfChar.length; x++) {
			countOfChar[x] = 0;
		}

		/* Do Actual Counting! */
		for (int i = 0; i < characters.size(); i++) {
			char checker = characters.get(i);
			for (int x = 0; x < msgChar.length; x++) {
				if (checker == msgChar[x]) {
					countOfChar[i]++;
				}
			}
		}

		/* Sort the arrays is descending order */
		for (int i = 0; i < countOfChar.length - 1; i++) {
			for (int j = 0; j < countOfChar.length - 1; j++) {
				if (countOfChar[j] < countOfChar[j + 1]) {
					int temp = countOfChar[j];
					countOfChar[j] = countOfChar[j + 1];
					countOfChar[j + 1] = temp;

					char tempChar = characters.get(j);
					characters.set(j, characters.get(j + 1));
					characters.set(j + 1, tempChar);
				}
			}
		}

		/* Print Out The Frequencies of the Characters */
		for (int x = 0; x < countOfChar.length; x++) {
			System.out.println(characters.get(x) + " - " + countOfChar[x]);
		}

		/* Form the Tree! */

		/* Form Leaf Nodes and Arrange them in a linked list */

		Node root = null;
		Node current = null;
		Node end = null;

		for (int i = 0; i < countOfChar.length; i++) {
			Node node = new Node(characters.get(i).toString(), countOfChar[i]);
			if (root == null) {
				root = node;
				end = node;
			} else {
				current = root;
				while (current.linker != null) {
					current = current.linker;
				}
				current.linker = node;
				current.linker.linkerBack = current;
				end = node;
			}
		}

		// Recursively add and make nodes!
		TreeMaker(root, end);

		// Calculate the ends and the meets!
		char[] messageArray = message.toCharArray();
		char checker;

		for (int i = 0; i < messageArray.length; i++) {
			current = node;
			checker = messageArray[i];
			String code = "";
			while (true) {
				if (current.left.value.toCharArray()[0] == checker) {
					code += "0";
					break;
				} else {
					code += "1";
					if (current.right != null) {
						if (current.right.value.toCharArray()[0] == characters
								.get(countOfChar.length - 1)) {
							break;
						}
						current = current.right;
					} else {
						break;
					}
				}
			}
			codedString += code;
		}
		System.out.println();
		System.out.println("The coded string is " + codedString);
		decode();
	}


	public static void TreeMaker(Node root, Node end) {
		node = new Node(end.linkerBack.value + end.value, end.linkerBack.count
				+ end.count);
		node.left = end.linkerBack;
		node.right = end;
		end.linkerBack.linkerBack.linker = node;
		node.linkerBack = end.linkerBack.linkerBack;
		end = node;
		end.linker = null;
		Node current = root;

		while (current.linker != null) {
			System.out.print(current.value + "->");
			current = current.linker;
		}

		System.out.println(current.value);

		if (root.linker == end) {
			node = new Node(root.value + end.value, root.count + end.count);
			node.left = root;
			node.right = end;
			node.linker = null;
			node.linkerBack = null;
			System.out.println(node.value);
			newRoot = node;
		} else {
			TreeMaker(root, end);
		}
	}
	
	public static void decode() throws IOException {
        int count;
        String stepBy;
        while (encoded.length() > 0) {
            Node current = root;
            count = 0;
            while (current.isLeaf() == false) {
                stepBy = Character.toString(encoded.charAt(count));
                if ("0".equals(stepBy)) {
                    current = current.left;
                } else {
                    current = current.right;
                }
                count++;
            }
            output += current.value;
            encoded = encoded.substring(count);
        }
        try (BufferedWriter out = new BufferedWriter(new FileWriter("output.txt"))) {
            out.write(output);
        }

    }

}

