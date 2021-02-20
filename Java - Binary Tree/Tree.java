import java.util.Random;

public class Tree {
    private TreeNode tip;
    private boolean alt;

    public Tree() {
        this.setTip(null);
    }

    public static void main(String args[]){
        Tree tree = new Tree();
        Random rand = new Random();
        for(int i=0;i<30;i++) {
            tree.add(rand.nextInt(30));
        }
        tree.add(15);
        tree.printTree();
        System.out.println("\n" + tree.find(15) + " = " + tree.find(15).getValue() + "\n");
        tree.delete(15);
        tree.printTree();
    }

    public TreeNode getTip(){
        return tip;
    }

    public void setTip(TreeNode tip) {
        this.tip = tip;
    }

    public void setAlt() {
        this.alt = !this.alt;
    }

    public boolean getAlt() {
        return this.alt;
    }

    public void printTree(){
        System.out.println("Drzewo zawiera: ");
        TreeNode.print(this.getTip());
    }

    public void add(int value) {
        this.setTip(insert(this.getTip(), value));
    }

    public TreeNode insert(TreeNode current, int value) {
        if (current == null) {
            return new TreeNode(value);
        }
        if (value < current.getValue()) {
            current.setLeft(insert(current.getLeft(), value));
        } else if(value > current.getValue()){
            current.setRight(insert(current.getRight(), value));
        }
        else {
            if (this.getAlt()) {
                current.setRight(insert(current.getRight(), value));
            }
                if (!this.getAlt()) {
                current.setLeft(insert(current.getLeft(), value));
            }
            setAlt();
        }
        return current;
    }

    public TreeNode find(int value) {
        return TreeNode.search(this.getTip(), value);
    }

    public void delete(int value) {
        this.setTip(TreeNode.remove(this.getTip(), value));
    }
}
