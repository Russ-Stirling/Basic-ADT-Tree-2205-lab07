package rstirli2_lab07;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;




class Node<T extends Comparable<?>> {
    Node<T> left=null, right=null;
    T data;
    
    
	private int state=0;
	
	private Node parent=null;
	private Boolean preO;
	public Boolean getPreO(){return preO;}
	public void setPreO(Boolean p){preO=p;}
	public int getState(){return state;}
	public void setState(int s){state=s;}
	private int level=0;
	private boolean sideL=true;
	public void setSideL(boolean l){sideL=l;}
	public boolean getSideL(){return sideL;}
	public void setLevel(int l){level=l;}
	public int getLevel(){return level;}
    public void setParent(Node p){parent = p;}
    public Node getParent(){return parent;}        
	public int getData(){return (Integer) data;}
	public void setLeft(Node l){left = l;}
    public void setRight(Node r){right = r;}
    public Node getLeft(){return left;}        
    public Node getRight(){return right;}
   

    public Node(T data) {
        this.data = data;
    }
}

class BTreePrinter {
static String text="";
	
    public static <T extends Comparable<?>> void printNode(Node<T> head) {
        int maxLevel = BTreePrinter.maxLevel(head);

        printNodeInternal(Collections.singletonList(head), 1, maxLevel);
    }

    private static <T extends Comparable<?>> void printNodeInternal(List<Node<T>> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || BTreePrinter.isAllElementsNull(nodes))
            return;

        int floor = maxLevel - level;
        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

        BTreePrinter.printWhitespaces(firstSpaces);

        List<Node<T>> newNodes = new ArrayList<Node<T>>();
        for (Node<T> node : nodes) {
            if (node != null) {
                System.out.print(node.data);
                text+=node.data;

                newNodes.add(node.left);
                newNodes.add(node.right);
            } else {
                newNodes.add(null);
                newNodes.add(null);
                System.out.print(" ");
                text+=" ";

            }

            BTreePrinter.printWhitespaces(betweenSpaces);
        }
        System.out.println("");
        text+=("\n");

        for (int i = 1; i <= endgeLines; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                BTreePrinter.printWhitespaces(firstSpaces - i);
                if (nodes.get(j) == null) {
                    BTreePrinter.printWhitespaces(endgeLines + endgeLines + i + 1);
                    continue;
                }

                if (nodes.get(j).left != null)
                {
                    System.out.print("/");
                    text+="/";
                }
            
                else
                    BTreePrinter.printWhitespaces(1);

                BTreePrinter.printWhitespaces(i + i - 1);

                if (nodes.get(j).right != null)
                {
                    System.out.print("\\");
                    text+="\\";

                }
                else
                    BTreePrinter.printWhitespaces(1);

                BTreePrinter.printWhitespaces(endgeLines + endgeLines - i);
            }

            System.out.println("");
            text+="\n";

        }

        printNodeInternal(newNodes, level + 1, maxLevel);
    }

    private static void printWhitespaces(int count) {
        for (int i = 0; i < count; i++)
        {
            System.out.print(" ");
            text+=" ";
        }
    }

    private static <T extends Comparable<?>> int maxLevel(Node<T> node) {
        if (node == null)
            return 0;

        return Math.max(BTreePrinter.maxLevel(node.left), BTreePrinter.maxLevel(node.right)) + 1;
    }

    private static <T> boolean isAllElementsNull(List<T> list) {
        for (Object object : list) {
            if (object != null)
                return false;
        }

        return true;
    }
    
    public String getText(){return text;}
    
    public void clearText(){text="";}

}
