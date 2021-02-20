import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class HaszLista {
    private int[] values;
    private int size;
    private ArrayList<Node> nodes;

    public static void main(String args[]){
        HaszLista test = new HaszLista(20000);
        try {
            test.read();
        } catch (Exception e){
            System.out.println("rip");
        }
        for(int i=0;i<test.getNodes().size();i++){
            test.hashInsert(test.getNodes().get(i));
        }
        double percent = 0.2;
        for(int i=test.getNodes().size()-1;i>test.getNodes().size()-1-(test.getSize()*percent);i--){
            test.hashSearch(test.getNodes().get(i));
        }
    }

    public HaszLista(int size) {
        this.setSize(size);
        this.setNodes(new ArrayList<>());
        this.setValues(new int[size]);
    }

    public void read() throws IOException {
        File file = new File("words.txt");
        Scanner input = new Scanner(file);
        double percet = 0.8;

        for(int i=0;i<(this.getSize()*percet);i++){
            String line = input.nextLine();
            String[] split = line.split(" ");
            this.getNodes().add(new Node(split[1]));
        }
    }

    public int naInt(String str){
        int result=0;
        for(int i=0;i<str.length();i=i+2){
            if(i<str.length()-1){
                result = result^(256*(int)str.charAt(i) + (int)str.charAt(i+1));
            }
            else{
                result = result^(256*(int)str.charAt(i));
            }
        }
        return result;
    }

    public void hashSearch(Node n) {
        int i = 0;
        int val = naInt(n.getNazwisko());
        int hashVal;
        do{
            hashVal = ((val%this.getSize())+i*(1+val%(this.getSize()-2)))%this.getSize();
            if(this.getValues()[hashVal]==val){
               // if(this.getSize()<100) {
                    System.out.println("Znaleziono wartosc " + val + " na pozycji " + hashVal);
                    System.out.println("Ilosc podejsc: " + i + 1);
                //}
                return;
            }
            i++;
        } while(this.getValues()[hashVal]!=0 && i<this.getSize());
        System.out.println("Nie znaleziono wartosci " + val);
        return;
    }

    public void hashInsert(Node n){
        int i = 0;
        int val = naInt(n.getNazwisko());
        int hashVal;
        do{
            hashVal = ((val%this.getSize())+i*(1+val%(this.getSize()-2)))%this.getSize();
            if(this.getValues()[hashVal]==0){
                this.insert(hashVal,val);
                return;
            }
            i++;
        } while(i<this.getSize());
        return;
    }

    public void insert(int i, int val){
        this.getValues()[i]=val;
    }

    public int[] getValues() {
        return values;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    private void setValues(int[] values) {
        this.values = values;
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public void setNodes(ArrayList<Node> nodes) {
        this.nodes = nodes;
    }
}
