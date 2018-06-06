package program4;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/*
 * Sarah Hall
 * CSCI 232
 * Program 4
 */

public class Driver {

	public static void main(String[] args) throws IOException{
		int source;
		BufferedWriter out = new BufferedWriter(new FileWriter("output.txt"));
		Scanner scan = new Scanner(System.in);
		int[][] matrix = getMatrix();
		BFS_DFS graph = new BFS_DFS();
		System.out.println("Enter the source for your graph");
		source = scan.nextInt();
		out.write("The BFS traversal of the graph is: ");
		out.newLine();
		graph.bfs(matrix, source);
		out.write("The DFS traversal of the graph is: ");
		out.newLine();
		graph.dfs(matrix, source);
		
	}

	public static int[][] getMatrix(){
		int[][] matrix = null;
		int row = 0;
		boolean start = true;
		Scanner file = getFile();
		while(file.hasNextLine()) {
			String[] line = file.nextLine().split("\\s+");
			if(start) {
				matrix = new int[line.length][line.length];
				start = false;
			}
			for(int i = 0; i< line.length; i++) {
				int num = Integer.parseInt(line[i]);
				matrix[row][i] = num;
			}
			row++;
		}
		return matrix;
	}
	
	public static Scanner getFile() {
		try {
			@SuppressWarnings("resource")
			Scanner file = new Scanner(new File("input.txt"));
			return file;
			
		}catch(FileNotFoundException e) {
			System.out.println("File not found");
			System.exit(0);
			return null;
		}
		
	}
}
