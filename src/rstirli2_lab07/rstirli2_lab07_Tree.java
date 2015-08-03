package rstirli2_lab07;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


public class rstirli2_lab07_Tree<T> {
		
	private int size;
	
	public BTreePrinter printer = new BTreePrinter();


	private Node head;
	
	
	public class PreIt{
		Node next, head;
		int cycle=0, s;
		
		public PreIt(Node root, int size)
		{
			s=size;
			head=root;
			next=root;
		}
		
		public Boolean hasNext()
		{
			if (cycle<(s*2)){return true;}
			else {return false;}
		}
		
		public Node next()
		{
			if (cycle==0)
			{
				cycle++;
				next.setPreO(true);
				return next;
			}
			
			if((next.getLeft())!=null&&(next.getState()==0))
			{
				next.setState(1);
				next=next.getLeft();
				next.setPreO(true);
				System.out.println(next.getData());
				cycle++;
				return next;
			}
			
			else if((next.getRight()!=null)&&(next.getState()<=1))
			{
				next.setState(2);
				next=next.getRight();
				next.setPreO(true);
				System.out.println(next.getData());
				cycle++;
				return next;
			}
				
			else 
			{
				next.setState(0);
				Node m = next;
				m.setPreO(false);
				next=next.getParent();
				System.out.println(m.getData());
				cycle++;
				return m;
				
			}
			
			
		}
	}
	
	public rstirli2_lab07_Tree()
	{
		size=0;
	}
	
	public void add(int num)
	{
		if (size==0)
		{
			head = new Node(num);
            System.out.println("Added node");            

		}
		
		else
		{
			insert(head, new Node(num));
		}
		size++;
		
	}
	
	public void insert(Node root, Node n){
        if(root == null|| n == null) {return;}
        
        if(root.getData() > n.getData())
        {
            if(root.getLeft() == null)
            {
            	n.setParent(root);
            	n.setLevel(root.getLevel()+1);
            	n.setSideL(true);
                root.setLeft(n);
                System.out.println("Added node to left of "+root.getData()+" of value "+n.getData());            
            }
            else
            {
                insert(root.getLeft(),n);
            }
 
        }
        else if(root.getData() < n.getData())
        {
            if(root.getRight() == null)
            {
            	n.setParent(root);
                root.setRight(n);
            	n.setSideL(false);

            	n.setLevel(root.getLevel()+1);
                System.out.println("Added node to Right of "+root.getData()+" of value "+n.getData());      
            }
            else
            {
                insert(root.getRight(),n);
            }
             
        }
        
        else
        {
            System.out.println("number is in the tree");
            size--;
        }
    }
	
	public String pre()
	{
        System.out.println("function called");      
        boolean i = false;
		Node temp;
        PreIt pre = new PreIt(head, size);
		String text="Pre-order: ";
		while (pre.hasNext())
		{
			temp=pre.next();
			if ((i)&&(temp.getPreO())) {text+=", ";}
			if(temp.getPreO()){text+=temp.getData();i=true;}
			
		}
		
		return text;
	}
	
	public String post()
	{
		System.out.println("function called");      
        boolean i = false;

		Node temp;
        PreIt pre = new PreIt(head, size);
        //PreIterator pre = new PreIterator(head, size);
		String text="Post-order: ";
		while (pre.hasNext())
		{
			temp=pre.next();
			if ((i)&&(!temp.getPreO())) {text+=", ";}
			if(!temp.getPreO()){text+=temp.getData();i=true;}
		}
		return text;
	}
	
	public String getTreeStructure()
	{		
		printer.clearText();
		printer.printNode(head);
		return printer.getText();
	
	}
	
	 public Stack<Node> levelOrder(Node n)
	 {
		 int i = 0;
		 Queue<Node> nodequeue = new LinkedList<Node>();
		 Stack<Node> list = new Stack<Node>();

		 if (n != null)
		 {
			 list.push(n);
			 nodequeue.add(n);
		 }
		 while (!nodequeue.isEmpty())
		 {
			 Node next = nodequeue.remove();
	   
			 
			 if (next.getLeft() != null)
			 {
				 nodequeue.add(next.getLeft());
				 list.push(next.getLeft());
				 
			 }
			 else
			 {
				 Node empty = new Node(-1);
				 empty.setParent(next);
				 empty.setLevel(next.getLevel()+1);
				 empty.setSideL(true);
				 list.push(empty);
			 }
			 if (next.getRight() != null)
			 {
				 nodequeue.add(next.getRight());
				 list.push(next.getRight());

			 }
			 else
			 {
				 Node empty = new Node(-1);
				 empty.setParent(next);
				 empty.setLevel(next.getLevel()+1);
				 empty.setSideL(false);
				 list.push(empty);
			 }
		 }
	  return list;
	 }
}
