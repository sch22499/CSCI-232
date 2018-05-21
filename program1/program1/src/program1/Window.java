/*
 * Sarah Hall
 * Program 1 GUI
 * CSCI 232
 */


package program1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.text.BadLocationException;

import com.jgoodies.*;
import java.io.*;

public class Window {

	private JFrame frame;
	static BST tree = new BST();
	private PrintStream standardOut;
	private JTextField textField_1;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		tree.build();
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	public class CustomOutputStream extends OutputStream {
	    private JTextArea textArea;
	     
	    public CustomOutputStream(JTextArea textArea) {
	        this.textArea = textArea;
	    }
	     
	    @Override
	    public void write(int b) throws IOException {
	        // redirects data to the text area
	        textArea.append(String.valueOf((char)b));
	        // scrolls the text area to the end of data
	        textArea.setCaretPosition(textArea.getDocument().getLength());
	    }
	}

	public Window() {
		initialize();
	}

	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{1, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
	         
		JTextArea textArea = new JTextArea();
		JScrollPane scroll = new JScrollPane();
		scroll.setEnabled(false);
		scroll.setViewportView(textArea);
		PrintStream printStream = new PrintStream(new CustomOutputStream(textArea));
	        // keeps reference of standard output stream
	        standardOut = System.out;
	         
	        // re-assigns standard output stream and error output stream
	        System.setOut(printStream);
	        System.setErr(printStream);
		
	   
        
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridheight = 5;
		gbc_scrollPane.gridwidth = 12;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		frame.getContentPane().add(scroll, gbc_scrollPane);
		
		
		
         
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		textField_1 = new JTextField("Enter your input");
		menuBar.add(textField_1);
		textField_1.setColumns(10);

		
		JButton btnSearch = new JButton("Search");
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String text = textField_1.getText();
				int input = Integer.valueOf(text);
				tree.search(input);
			}
		});
		menuBar.add(btnSearch);
		
		JButton btnAdd = new JButton("Insert");
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String text = textField_1.getText();
				int input = Integer.valueOf(text);
				tree.insert(input);
			}
		});
		menuBar.add(btnAdd);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String text = textField_1.getText();
				int input = Integer.valueOf(text);
				tree.delete(input);
			}
		});
		menuBar.add(btnDelete);
		
		JMenu mnTraverse = new JMenu("Display");
		menuBar.add(mnTraverse);
		
			JButton btnInorder = new JButton("In-Order");
			btnInorder.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					tree.inOrder(tree.root);
					System.out.println();
				}
			});
			mnTraverse.add(btnInorder);
			
			JButton btnPrinttree = new JButton("PrintTree");
			btnPrinttree.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					tree.printTree(tree.root);
					System.out.println();
				}
			});
			mnTraverse.add(btnPrinttree);
			
			
			JButton btnPostorder = new JButton("Post-Order");
			btnPostorder.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					tree.postOrder(tree.root);
					System.out.println();
				}
			});
			mnTraverse.add(btnPostorder);
			
			JButton btnPreorder = new JButton("Pre-Order");
			btnPreorder.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					tree.preOrder(tree.root);
					System.out.println();
				}
			});
			mnTraverse.add(btnPreorder);
			
			
		}

}
