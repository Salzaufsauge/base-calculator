import src.BaseContainer;

import java.util.Scanner;
public class Main {
    private static Scanner in;
    public static void main(String[] args){
        in = new Scanner(System.in);
        System.out.println("Bitte Zahl eingeben:");
        double zahl = in.nextDouble();
        System.out.println("Bitte Basis eingeben:");
        int basis = in.nextInt();
        BaseContainer zahlZurBasis = new BaseContainer(basis,zahl);
        System.out.println("Zahl in Basis 10: ("+zahl+")10");
        System.out.println(zahlZurBasis.toString());
        System.out.println(zahlZurBasis.convertToBaseTenDouble());
    }
}
