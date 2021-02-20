import java.util.HashSet;

public class NodeList{
    private Node first;

    public static void main(String[] args){
        NodeList test = new NodeList();

        test.add("A");
        test.add("B");
        test.add("C");
        test.add("D");

        test.print();

        try {
            System.out.println(test.find("C").getData());
        }catch(Exception ex){
            System.out.println("null\n");
        }

        test.delete("A");
        test.delete("D");
        test.print();

        test.clean();
        test.print();

        test.add("wow");
        test.add("lol");
        test.add("test");
        NodeList test2 = new NodeList();
        test2.add("test");
        test2.add("lol");
        test2.add("yee");
        test2.add("yolo");

        test.combine(test2);
        test.print();
        test.removeDups();
        test.print();
    }

    public Node getFirst() {
        return this.first;
    }

    public void setFirst(Node first) {
        this.first = first;
    }

    public void add(String data){
        Node node = new Node(data);
        Node tmp = this.getFirst();
        this.setFirst(node);
        this.getFirst().setNext(tmp);
    }

    public void print(){
        if(this.getFirst()!=null) {
            Node current = this.getFirst();
            String output = "";
            int i = 1;

            while (current != null) {
                output += i + ": " + current.getData() + "\n";
                current = current.getNext();
                i++;
            }
            System.out.println(output);
        }
        else{
            System.out.println("Lista pusta");
        }
    }

    public Node find(String data){
        if(this.getFirst()!=null) {
            Node current = this.getFirst();

            while (current != null) {
                if (current.getData().equals(data)) {
                    return current;
                } else {
                    current = current.getNext();
                }
            }
        }
        return null;
    }

    public void delete(String data) {
        if(this.getFirst()!=null) {
            Node current = this.getFirst();

            if (this.getFirst().getData().equals(data)) {
                this.setFirst(this.getFirst().getNext());
            } else {
                while (current != null && current.getNext() != null) {
                    if (current.getNext().getData().equals(data)) {
                        current.setNext(current.getNext().getNext());
                        break;
                    } else {
                        current = current.getNext();
                    }
                }
            }
        }
    }

    public void clean(){
        if(this.getFirst()!=null){
            while(this.getFirst().getNext()!=null){
                this.setFirst(this.getFirst().getNext());
            }
            this.setFirst(null);
        }
    }

    public NodeList combine(NodeList B){
        if(this.getFirst()==null & B.getFirst()==null){
            return null;
        }
        if(this.getFirst()==null && B.getFirst()!=null){
            return B;
        }
        if(B.getFirst()==null && this.getFirst()!=null){
            return this;
        }
        Node current = this.getFirst();

        while(current.getNext()!=null){
            current = current.getNext();
        }
        current.setNext(B.getFirst());
        B.clean();
        return this;
    }

    public NodeList removeDups() {
        if (this.getFirst() != null) {
            HashSet values = new HashSet();
            Node current = this.getFirst();
            values.add(current.getData());

            do{
                if(values.contains(current.getNext().getData())){
                    current.setNext(current.getNext().getNext());
                }
                else {
                    values.add(current.getNext().getData());
                    current = current.getNext();
                }
            }while(current!=null && current.getNext()!=null);
            return this;
        }
        return null;
    }
}
