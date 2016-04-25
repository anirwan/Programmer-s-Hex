import java.util.Arrays;

/**
 * Created by anirwanchowdhury on 24/04/2016.
 */

public class Hexagon {
    public char[] sides;
    
    public Hexagon(char[] sides) {
        this.sides = new char[6];
        System.arraycopy(sides, 0, this.sides, 0, sides.length);
    }
    
    public char getValue(int position) {
        return this.sides[position];
    }
    
    public int getPosition(char value) {
        for(int i = 0; i < 6; i++) {
            if(this.sides[i] == value) {
                return i;
            } 
        }
        return -1;
    }
    
    public char getClockwiseValue(char value) {
        int position = getPosition(value);
        int clockwisePosition = (position+1) % 6;
        return getValue(clockwisePosition);
    }
    
    public char getCounterClockwiseValue(char value) {
        int position = getPosition(value);
        int counterClockwisePosition = (position +5) % 6;
        return getValue(counterClockwisePosition);
    }

    public String toString() {
        return Arrays.toString(sides);
    }
}
