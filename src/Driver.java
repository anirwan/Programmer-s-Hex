import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by anirwanchowdhury on 22/04/2016.
 */

public class Driver {
    private static Hexagon[] hexagons;

    static ArrayList<Hexagon> potentialHexagons(ArrayList<Hexagon> currentHexagons) {
        int numberOfHexagons = currentHexagons.size();
        ArrayList<Hexagon> potentialHexagons = new ArrayList<Hexagon>();
        
        switch (numberOfHexagons) {
            case 0:
                for(int i = 0; i < 7; i++) {
                    potentialHexagons.add(hexagons[i]);
                }
                break;
            case 1:
                for(int i = 0; i < 7; i++) {
                    if(!currentHexagons.contains(hexagons[i])) {
                        potentialHexagons.add(hexagons[i]);
                    }
                }
                break;
            default:
                char hubValue = currentHexagons.get(0).getValue(numberOfHexagons - 1);
                char hubValuePrevious = currentHexagons.get(0).getValue(numberOfHexagons - 2);
                char previousValue = currentHexagons.get(numberOfHexagons - 1).getCounterClockwiseValue(hubValuePrevious);
                char firstValue = currentHexagons.get(1).getClockwiseValue(currentHexagons.get(0).getValue(0));
                
                for(int i = 0; i < 7; i++) {
                    if(!currentHexagons.contains(hexagons[i])
                       && hexagons[i].getClockwiseValue(hubValue) == previousValue
                       && (numberOfHexagons < 6 || hexagons[i].getCounterClockwiseValue(hubValue) == firstValue)) {
                        potentialHexagons.add(hexagons[i]);
                    }
                }
        }
        return potentialHexagons;
    }

    static void placePieces(ArrayList<Hexagon> currentHexagons) {
        if(currentHexagons.size() == 7) {
            System.out.println(currentHexagons.toString());
        } else {
            ArrayList<Hexagon> potentialHexagons = potentialHexagons(currentHexagons);
            
            for(Hexagon hex : potentialHexagons) {
                ArrayList<Hexagon> temp = (ArrayList<Hexagon>) currentHexagons.clone();
                temp.add(hex);
                placePieces(temp);
            }
        }
    }

    public static void main (String[] args) {
        Scanner scan = new Scanner(System.in);
        int input;

        System.out.println("Welcome to the Programmer's Hex solution");
        System.out.println("1. Use pre-determined hexagons");
        System.out.println("2. Input new hexagons");
        System.out.print("Please choose an option: ");
        input = scan.nextInt();

        switch(input) {
            case 1:
                hexagons = new Hexagon[] {
                        new Hexagon(new char[] {'E', 'A', 'C', 'F', 'B', 'D'}),
                        new Hexagon(new char[] {'D', 'F', 'E', 'C', 'A', 'B'}),
                        new Hexagon(new char[] {'F', 'E', 'D', 'A', 'B', 'C'}),
                        new Hexagon(new char[] {'A', 'F', 'D', 'E', 'B', 'C'}),
                        new Hexagon(new char[] {'D', 'C', 'B', 'A', 'F', 'E'}),
                        new Hexagon(new char[] {'A', 'C', 'B', 'E', 'F', 'D'}),
                        new Hexagon(new char[] {'A', 'F', 'E', 'D', 'C', 'B'})
                };
                break;
            case 2:
                hexagons = new Hexagon[7];
                System.out.print("Please enter the first hex: ");
                hexagons[0] = new Hexagon(scan.nextLine().toCharArray());
                System.out.print("Please enter the second hex: ");
                hexagons[1] = new Hexagon(scan.nextLine().toCharArray());
                System.out.print("Please enter the third hex: ");
                hexagons[2] = new Hexagon(scan.nextLine().toCharArray());
                System.out.print("Please enter the fourth hex: ");
                hexagons[3] = new Hexagon(scan.nextLine().toCharArray());
                System.out.print("Please enter the fifth hex: ");
                hexagons[4] = new Hexagon(scan.nextLine().toCharArray());
                System.out.print("Please enter the sixth hex: ");
                hexagons[5] = new Hexagon(scan.nextLine().toCharArray());
                System.out.print("Please enter the seventh hex: ");
                hexagons[6] = new Hexagon(scan.nextLine().toCharArray());
                break;
            default:
                System.out.println("Incorrect choice!");
                break;
        }
        scan.close();
        placePieces(new ArrayList<Hexagon>());
    }
}
