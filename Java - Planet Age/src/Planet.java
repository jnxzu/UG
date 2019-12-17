import java.util.Scanner;

public class Planet{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        float x = Float.parseFloat(in.nextLine());
        String planeta = in.nextLine();
        in.close();
        float wiek = x/31557600;
        switch(planeta){
            case "Ziemia":
                System.out.format("%.2f%n",wiek);
                break;

            case "Merkury":
                System.out.format("%.2f%n",wiek/0.2408467);
                break;

            case "Wenus":
                System.out.format("%.2f%n",wiek/0.61519726);
                break;

            case "Mars":
                System.out.format("%.2f%n",wiek/1.8808158);
                break;

            case "Jowisz":
                System.out.format("%.2f%n",wiek/11.862615);
                break;

            case "Saturn":
                System.out.format("%.2f%n",wiek/29.447498);
                break;

            case "Uran":
                System.out.format("%.2f%n",wiek/84.016846);
                break;

            case "Neptun":
                System.out.format("%.2f%n",wiek/164.79132);
                break;
        }
    }
}

