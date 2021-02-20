import java.util.Scanner;

public class Armstrong{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        String x = in.nextLine();
        in.close();
        int sum=0;
        for(int i=0;i<x.length();i++){
            sum += Math.pow(Character.getNumericValue(x.charAt(i)), x.length());
        }
        System.out.println(sum==Integer.parseInt(x));
    }
}
