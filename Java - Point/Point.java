import java.util.Scanner;

class Point{
    private double x;
    private double y;

    public Point(){
        this.x=0;
        this.y=0;
    }

    private Point(double x, double y){
        this.x=x;
        this.y=y;
    }

    private void show(){
        System.out.println(this.x+" "+this.y);
    }

    private Point translate(double x, double y){
        this.x+=x;
        this.y+=y;
        return this;
    }

    private Point scale(double n){
        this.x*=n;
        this.y*=n;
        return this;
    }

    public static void main(String args[]){
        Scanner in=new Scanner(System.in);
        double x=in.nextDouble();
        double y=in.nextDouble();
        Point p=new Point(x, y);
        int action=in.nextInt();
        if(action==0){
            double movx=in.nextDouble();
            double movy=in.nextDouble();
            Point newp=p.translate(movx, movy);
            newp.show();
        }
        else if(action==1){
            int n=in.nextInt();
            Point newp=p.scale(n);
            newp.show();
        }
        else if(action==2){
            double movx=in.nextDouble();
            double movy=in.nextDouble();
            Point newp=p.translate(movx, movy);
            int n=in.nextInt();
            Point newp2=newp.scale(n);
            newp2.show();
        }
        else if(action==3){
            int n=in.nextInt();
            Point newp=p.scale(n);
            double movx=in.nextDouble();
            double movy=in.nextDouble();
            Point newp2=newp.translate(movx, movy);
            newp2.show();
        }
        else if(action==4){
            p.show();
        }else{
            System.out.println("XD");
        }
        in.close();
    }
}



