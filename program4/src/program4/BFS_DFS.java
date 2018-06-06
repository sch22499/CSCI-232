package program4;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Stack;
/*
 * Sarah Hall
 * CSCI 232
 * Program 4
 */


public class BFS_DFS {
	public BufferedWriter out = null;
	private Queue<Integer> queue;
	private Stack<Integer> stack;
	public BFS_DFS() throws IOException {
		queue = new LinkedList<Integer>();
		stack = new Stack<Integer>();
		out = new BufferedWriter(new FileWriter("output.txt"));
		
	}
	
	
	
	
	public void bfs(int adj_matrix[][], int source) throws IOException {
		int nodeNum = adj_matrix[source].length;
		int[] visited = new int[nodeNum + 1];
		int i, element;
		
		visited[source] = 1;
		queue.add(source);
		
		while(!queue.isEmpty()) {
			element = queue.remove();
			i = element;
			out.write(i + "\t");
			while(i<= nodeNum){
				if(adj_matrix[element][i] == 1 && visited[i] == 0) {
					queue.add(i);
					visited[i] = 1;
				}
				i++;
			}
			out.newLine();
		}
		
	}
	
	public void dfs(int adj_matrix[][], int source) throws IOException {
		int nodeNum = adj_matrix[source].length -1;
		int visited[] = new int[nodeNum +1];
		int element = source;
		int i = source;
		out.write(element + "\t");
		visited[source] = 1;
		stack.push(source);
		while(!stack.isEmpty()) {
			element = stack.peek();
			i = element;
			while(i <= nodeNum) {
				if(adj_matrix[element][i] == 1 && visited[i] == 0) {
					stack.push(i);
					visited[i] = 1;
					element = i;
					i = 1;
					out.write(element + "\t");
					continue;
					
				}
				i++;
			}
			stack.pop();
			out.newLine();
		}
		
	}
	
}
