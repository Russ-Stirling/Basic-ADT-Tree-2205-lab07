package rstirli2_lab07;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.LinkedList;
import java.util.ListIterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.StyledDocument;



public class rstirli2_lab07_Main extends JFrame {
	
	public static boolean isNumeric(String text)  
	{  
	  try  
	  {  
	    int i = Integer.parseInt(text);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}
	
	 private JTextField jtfNumber = new JTextField(8);
	 private JTextArea jtaNumbers = new JTextArea();
	 private JButton jbtTree = new JButton("Print the Tree Structure");
	 private JButton jbtPost = new JButton("Print in Post-Order");
	 private JButton jbtPre = new JButton("Print in Pre-Order ");
	 private String text, list;
	 private rstirli2_lab07_Tree tree = new rstirli2_lab07_Tree();
	 
	 
	 public rstirli2_lab07_Main(){
		 
		 JPanel panel1 = new JPanel();
		 panel1.add(new JLabel("Enter a number: "));
		 panel1.add(jtfNumber);
		 
		 JScrollPane jsp = new JScrollPane(jtaNumbers);
		 
		 JPanel panel2 = new JPanel();
		 panel2.add(jbtTree);
		 panel2.add(jbtPost);
		 panel2.add(jbtPre);
		 
		 this.add(panel1, BorderLayout.NORTH);
		 add(jsp, BorderLayout.CENTER);
		 add(panel2, BorderLayout.SOUTH);
		
		 jtfNumber.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 
				text = jtfNumber.getText();
				System.out.println(text);
				if (isNumeric(text))
				{
					tree.add(Integer.parseInt(text));
				}
				
				jtaNumbers.setText(tree.getTreeStructure());

				 
						 
		}});
		 
		 jbtTree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				jtaNumbers.setText(tree.getTreeStructure());

		 }});
		 
		jbtPost.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
			
				list=tree.post();
				jtaNumbers.setText(list);
			
		 }});
		 
		jbtPre.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {

				 
				list=tree.pre();
				jtaNumbers.setText(list);
		 }});
	 }

	public static void main(String[] args) { 
		rstirli2_lab07_Main gui = new rstirli2_lab07_Main();
		gui.setVisible(true);
		gui.setSize(900, 1000);

	}
}
