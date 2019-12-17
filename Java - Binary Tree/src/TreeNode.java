import java.util.Objects;

public class TreeNode {
    private int value;
    private TreeNode left;
    private TreeNode right;

    public TreeNode(int value) {
        this.setValue(value);
        this.setLeft(null);
        this.setRight(null);
    }

    public static void print(TreeNode node){
        if (node == null) {
            return;
        }
        print(node.getLeft());
        System.out.printf("%s ", node.getValue());
        print(node.getRight());
    }

    public static TreeNode search(TreeNode current, int value) {
        if (current == null) {
            return null;
        }
        if (value == current.getValue()) {
            return current;
        }
        return value < current.getValue()
                ? search(current.getLeft(), value)
                : search(current.getRight(), value);
    }

    public static TreeNode remove(TreeNode current, int value) {
        if (current == null) {
            return null;
        }
        if (value == current.getValue()) {
            if (current.getLeft() == null && current.getRight() == null) {
                return null;
            }
            if (current.getRight() == null) {
                return current.getLeft();
            }
            if (current.getLeft() == null) {
                return current.getRight();
            }
        }
        if (value < current.getValue()) {
            current.setLeft(remove(current.getLeft(), value));
            return current;
        }
        current.setRight(remove(current.getRight(), value));
        int min;
        if(current.getRight()!=null){
            min=min(current.getRight());
        }
        else{
            min=min(current);
        }
        current.setValue(min);
        current.setRight(remove(current.getRight(), min));
        return current;
    }

    private static int min(TreeNode cur) {
        if (cur.getLeft() == null){
            return cur.getValue();
        }
        else {
            return min(cur.getLeft());
        }
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TreeNode treeNode = (TreeNode) o;
        return getValue() == treeNode.getValue();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
