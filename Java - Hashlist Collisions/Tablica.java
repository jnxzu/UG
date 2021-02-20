import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class Tablica {
    private int m;
    private ArrayList<Integer> kolizje;

    public int getM() {
        return m;
    }

    public void setM(int size) {
        this.m = size;
    }

    public ArrayList<Integer> getKolizje() {
        return kolizje;
    }

    public void setKolizje(ArrayList<Integer> kolizje) {
        this.kolizje = kolizje;
    }

    public Tablica(int m) {
        this.setKolizje(new ArrayList<>());
        this.setM(m);
    }

    public static void main(String args[]) {
        Tablica korzystna = new Tablica(1901);
        Tablica niekorzystna = new Tablica(1000);
        try {
            System.out.println("Wartość korzystna: ");
            korzystna.test();
        } catch (Exception e){}
        System.out.println("\n");
        try {
            System.out.println("Wartość niekorzystna: ");
            niekorzystna.test();
        }catch (Exception e){}
    }

    public void test() throws IOException{
        this.init();

        File file = new File("words.txt");
        Scanner input = new Scanner(file);

        while(input.hasNext()){
            String word = input.next();
            int result = this.hash(word);
            int increase = this.getKolizje().get(result);
            this.getKolizje().set(result,increase+1);
        }

        System.out.println("M = " + this.getM());

        int zerocount = 0;
        for(int i=0;i<this.getM();i++){
            if(this.getKolizje().get(i)==0){
                zerocount++;
            }
        }
        System.out.println("Zer = " + zerocount);

        int max = 0;
        for(int i=0;i<this.getM();i++){
            if(this.getKolizje().get(i)>max){
                max=this.getKolizje().get(i);
            }
        }
        System.out.println("Max = " + max);

        double sum = 0;
        double nonzero = 0;
        for(int i=0;i<this.getM();i++){
            sum = sum + this.getKolizje().get(i);
            if(this.getKolizje().get(i)!=0){
                nonzero++;
            }
        }
        System.out.println("Avg = " + sum/nonzero);
    }

    public int hash(String word){
        int result=0;
        for(int i=0;i<word.length();i=i+2){
            if(i<word.length()-1){
                result = result^(256*(int)word.charAt(i) + (int)word.charAt(i+1));
            }
            else{
                result = result^(256*(int)word.charAt(i));
            }
        }
        return result % this.getM();
    }

    private void init(){
        for(int i=0;i<this.getM();i++){
            this.getKolizje().add(0);
        }
    }
}
