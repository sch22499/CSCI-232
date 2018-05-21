/*
 * Sarah Hall
 * CSCI 232
 * Program assignment 1
 */
package program1;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class BST {
	
	public Node root;
	
	public BST(){
		this.root = null;
	}
	
	public class Node{ //I just put the Node class inside of the BST tree because I'm lazy 
		int key; //key data whatever the value in the Node
		Node left; //the left child
		Node right;	//right child
		public Node(int key){ //constructor
			this.key = key; //initializing everything
			left = null;
			right = null;
		}
	}
	
	public void build() {
		try { //the try and catch thing to tell if the fiel is actually there
			@SuppressWarnings("resource")
				Scanner file = new Scanner(new File("input.txt")); //making a new file blah bal whatever
				while(file.hasNextLine()) { //while there's actually something IN the file we can do this shit
					String line = file.nextLine(); //I converted the file into a string because that makes it super easy to chop up all nice
					String words[] = line.split(",");//made an array for our strings hmhmhmhm
					for(int i = 0; i < words.length; i++) {//runnin through the array
						insert(Integer.parseInt(words[i]));
					}
				}
			}
			catch (FileNotFoundException e) { //boring shit
	            System.out.println("File not found");		
			}	
	}
	
	

	
	public boolean search(int id){
		Node current = root; //creating new Node called current, then setting it to root so we start 
							//our search at the top of the tree
		while(current!=null){ //keep the searching running only if there's anything in the tree
			if(current.key==id){ //cool found it nice
				System.out.println("Found");
				return true;
			}else if(current.key>id){ //if the current key is greater we need to move to the left
				current = current.left; //so now current is the node to the left
			}else{
				current = current.right; //otherwise we're going right
			}
		}
		System.out.println("The ID you are looking for does not exist");
		return false; //it's not there so 
	}
	
	
	public boolean delete(int target){ //this one is gnarly lmao.
		Node parent = root; //starting by making a parent Node which is equal to the root
		Node current = root; //also going to have a current that starts are the root
		boolean isLeftChild = false; 
		while(current.key!=target){  //loop through the tree when our current Node isn't the ID we wanna delete
			parent = current; //
			if(current.key>target){ //if our current is greater than our target
				isLeftChild = true; //our target is th left child
				current = current.left; //swap em
			}else{ 
				isLeftChild = false; //etc etc
				current = current.right;
			}
			if(current ==null){ 
				return false;
			}
		}
	//this is for deleting Nodes with no children
		if(current.left==null && current.right==null){ //no left or right children if a leaf
			if(current==root){
				root = null;//if the root is our current and it has no children we delete the root
			}
			if(isLeftChild ==true){ //if our Node's on the left, we set the parent's left child to null
				parent.left = null;
			}else{
				parent.right = null; //vice versa
			}
		}
		//Case 2 : if node to be deleted has only one child
		else if(current.right==null){ //if ther'es no right child
			if(current==root){
				root = current.left; //swap
			}else if(isLeftChild){
				parent.left = current.left; //etc
			}else{
				parent.right = current.left;
			}
		}
		else if(current.left==null){ //the same swapping except for when our Node has a null leftchild
			if(current==root){
				root = current.right;
			}else if(isLeftChild){
				parent.left = current.right;
			}else{
				parent.right = current.right;
			}
		}else if(current.left!=null && current.right!=null){
			 
			Node successor	 = getSuccessor(current); //see getsuccessor function
			if(current==root){
				root = successor;
			}else if(isLeftChild){
				parent.left = successor;
			}else{
				parent.right = successor;
			}			
			successor.left = current.left;
		}		
		System.out.println("The Node was deleted");
		printTree(root);
		return true;		
	}
	
	public Node getSuccessor(Node target){
		Node succ =null; //create the successor node and its parent node
		Node succP =null;//parent
		Node current = target.right; //got our current and our target Node
		while(current!=null){
			succP = succ; //while our current is on something, 
			succ = current;//swap successsor parent and current and current with its leftchild 
			current = current.left;
		}
		//check if successor has the right child, it cannot have left child for sure
		// if it does have the right child, add it to the left of successorParent.
//		successsorParent
		if(succ!=target.right){
			succP.left = succ.right;
			succ.right = target.right;
		}
		return succ;
	}
	
	
	public void insert(int id){
		Node add = new Node(id);
		if(root==null){ //start with the tree being empty
			root = add; 
			System.out.println("The Node was added");
			printTree(root);
			return;
		}
		Node current = root; //now we're assuming the tree isn't empty so we're starting the current at the root
		Node parent = null; //obv gotta set parent to null
		while(true){
			parent = current; 
			if(id<current.key){ //if the target is less than current than we go left				
				current = current.left; 
				if(current==null){  //if our current is null after setting it to our leftchild that means that there was space, so we can insert our Node
					parent.left = add;
					System.out.println("The Node was added");
					printTree(root);
					return;
				}
			}else{
				current = current.right; //same deal but on the rightside
				if(current==null){
					parent.right = add;
					System.out.println("The Node was added");
					printTree(root); //printing to show new Node
					return;
				}
			}
		}
	}
	public void inOrder(Node root){ //these are the algorithms shown in class
		if(root!=null){
			inOrder(root.left);
			System.out.print(" " + root.key);
			inOrder(root.right);
		}
	}
	
	public void postOrder(Node root){
		if(root!=null){
			postOrder(root.left);
			postOrder(root.right);
			System.out.print(" " + root.key);
		}
	}
	
	public void preOrder(Node root){
		if(root!=null){
			System.out.print(" " + root.key);
			preOrder(root.left);
			preOrder(root.right);
		}
	}
	
	public void printTree(Node root) { //this is the function to make the tree kinda look like a tree
	    if (root == null) //if the tree is empty then return
	        return;

	    Queue<Node> queue = new ArrayDeque<>(); //I make an arrayDeque so I can go through the tree more easily

	    queue.add(root); //put the tree in the queue
	    do { 
	        int size = queue.size(); //create a size int
	        for (int i=0; i<size; i++) { //loop through said int
	            Node node = queue.poll(); //poll will take the latest value in the queue and delete it
	            System.out.print("-" + node.key + "-"); //the printing looks weird but I'm really tired
	            System.out.print(" ");//adding a space
	            if (node.left != null) //if there's anything to the left of our element
	                queue.add(node.left);//it goes down here
	            if (node.right != null)//same dealio
	                queue.add(node.right);
	        }
	        System.out.println();
	    } while (queue.size() > 0);//stop going when our size reaches 0
	}
}


