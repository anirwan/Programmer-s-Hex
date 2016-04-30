import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by anirwanchowdhury on 22/04/2016.
 */

public class Driver {
    private static Hexagon[] hexagons;
    private static int count = 0;

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
                char firstHub = currentHexagons.get(0).getValue(0);

                for(int i = 0; i < 7; i++) {
                    if(!currentHexagons.contains(hexagons[i])
                        && hexagons[i].getPosition(firstHub) != -1) {
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
            count++;
            System.out.println(count + ". " + currentHexagons.toString());
        } else {
            ArrayList<Hexagon> potentialHexagons = potentialHexagons(currentHexagons);
            
            for(Hexagon hex : potentialHexagons) {
                ArrayList<Hexagon> temp = (ArrayList<Hexagon>) currentHexagons.clone();
                if(!currentHexagons.isEmpty()) {
                    hex = rotate(currentHexagons, hex);
                }
                temp.add(hex);
                placePieces(temp);
            }
        }
    }
    
    static Hexagon rotate(ArrayList<Hexagon> currentHexagons, Hexagon hex) {
        int piecePosition;
        int offset = hex.sides.length / 2;
        char hubValue = currentHexagons.get(0).getValue(currentHexagons.size() - 1);
        int hubPosition = currentHexagons.get(0).getPosition(hubValue);

        if(hubPosition < offset) {
            piecePosition = hubPosition + offset;
        } else {
            piecePosition = hubPosition - offset;
        }

        while (hex.getPosition(hubValue) != piecePosition) {
            char[] temp = new char[6];
            temp[0] = hex.sides[5];
            System.arraycopy(hex.sides, 0, temp, 1, 5);
            hex.sides = temp;
            rotate(currentHexagons, hex);
        }

        return hex;
    }

    public static void main (String[] args) {
        Scanner scan = new Scanner(System.in);
        int input;

        System.out.println("Welcome to the Programmer's Hex solution");
        System.out.println("1. Use pre-determined hexagons");
        System.out.println("2. Input new hexagons");
        System.out.print("Please choose an option: ");
        input = scan.nextInt();
        System.out.println();

        switch(input) {
            case 1:
                hexagons = new Hexagon[] {
                        new Hexagon(new char[] {'D', 'B', 'F', 'A', 'C', 'E'}),
                        new Hexagon(new char[] {'F', 'C', 'E', 'D', 'A', 'B'}),
                        new Hexagon(new char[] {'C', 'F', 'A', 'D', 'B', 'E'}),
                        new Hexagon(new char[] {'D', 'E', 'B', 'A', 'C', 'F'}),
                        new Hexagon(new char[] {'A', 'C', 'E', 'F', 'D', 'B'}),
                        new Hexagon(new char[] {'D', 'C', 'B', 'E', 'F', 'A'}),
                        new Hexagon(new char[] {'B', 'A', 'E', 'D', 'F', 'C'})
                };
                break;
            case 2:
                hexagons = new Hexagon[7];
                System.out.print("Please enter the first hex: ");
                hexagons[0] = new Hexagon(scan.next().toCharArray());
                System.out.print("Please enter the second hex: ");
                hexagons[1] = new Hexagon(scan.next().toCharArray());
                System.out.print("Please enter the third hex: ");
                hexagons[2] = new Hexagon(scan.next().toCharArray());
                System.out.print("Please enter the fourth hex: ");
                hexagons[3] = new Hexagon(scan.next().toCharArray());
                System.out.print("Please enter the fifth hex: ");
                hexagons[4] = new Hexagon(scan.next().toCharArray());
                System.out.print("Please enter the sixth hex: ");
                hexagons[5] = new Hexagon(scan.next().toCharArray());
                System.out.print("Please enter the seventh hex: ");
                hexagons[6] = new Hexagon(scan.next().toCharArray());
                System.out.println();
                break;
            default:
                System.out.println("Incorrect choice!");
                break;
        }
        scan.close();
        placePieces(new ArrayList<Hexagon>());
        System.out.println("\nTotal possible solutions: " + count);
    }
}
