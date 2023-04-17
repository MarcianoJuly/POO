import java.util.Scanner;

public class ProgramaAtivida1{
    public static void main(String[] args){
        Scanner enter = new Scanner(System.in);

        int a = Integer.parseInt(enter.nextLine());
        int b = Integer.parseInt(enter.nextLine());
        int c = Integer.parseInt(enter.nextLine());
        int delta = b*b - 4*a*c;
        double[] raiz= new double[2];
            if(delta > 0){
                for(int i=0; i<2; i++){
                    if(i==0)
                        raiz[i] = (-b + Math.sqrt(delta))/2*a;
                    else
                        raiz[i] = (-b - Math.sqrt(delta))/2*a;
                }
                for(int i=0; i<2; i++){
                    System.out.print(raiz[i] + ", ");
                }
            }else
                System.out.println("Não existe");
        enter.close();
    }
}