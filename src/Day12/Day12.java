package Day12;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day12 {
    private final static String path = "src\\Day12\\input.txt";
    private static ArrayList<String> input= getInput();

    public static void main(String[] args){
        teil1();
        teil2();
    }

    public static void teil1(){
        int northSouth = 0;
        int westEast = 0;
        int direction = 0;

        for(String instruction : input){
            int value = Integer.parseInt(instruction.substring(1));
            if(instruction.startsWith("N")){
                northSouth += value;
            }else if(instruction.startsWith("S")){
                northSouth -= value;
            }else if(instruction.startsWith("W")){
                westEast += value;
            }else if(instruction.startsWith("E")){
                westEast -= value;
            }else if(instruction.startsWith("L")){
                direction += (360-value);
                direction %= 360;
            }else if(instruction.startsWith("R")){
                direction += value;
                direction %= 360;
            }else if(instruction.startsWith("F")){
                if(direction == 0){westEast -= value;}
                else if(direction == 90){northSouth -= value;}
                else if(direction == 180){westEast += value;}
                else if(direction == 270){northSouth += value;}
                else{
                    System.out.println(direction + " is not valid direction");
                    System.exit(-1);
                }
            }
        }
        if(northSouth<0){northSouth *= -1;}
        if(westEast<0){westEast *= -1;}
        int manhattandistance = northSouth + westEast;
        System.out.println("Part 1: Manhattan distance: " + manhattandistance);
    }


    public static void teil2(){
        int shipnorthSouth = 0;
        int shipwestEast = 0;
        int waypointnorthSouth = 1;
        int waypointwestEast = -10;

        for(String instruction : input){
            int value = Integer.parseInt(instruction.substring(1));
            if(instruction.startsWith("N")){
                waypointnorthSouth += value;
            }else if(instruction.startsWith("S")){
                waypointnorthSouth -= value;
            }else if(instruction.startsWith("W")){
                waypointwestEast += value;
            }else if(instruction.startsWith("E")){
                waypointwestEast -= value;
            }else if(instruction.startsWith("L")){
                while(value > 0) {
                    int tmp = waypointwestEast;
                    waypointwestEast = waypointnorthSouth;
                    waypointnorthSouth = -1 * tmp;
                    value -= 90;
                }
            }else if(instruction.startsWith("R")){
                while (value > 0) {
                    int tmp = waypointwestEast;
                    waypointwestEast = -1*waypointnorthSouth;
                    waypointnorthSouth = tmp;
                    value -= 90;
                }
            }else if(instruction.startsWith("F")){
                shipnorthSouth += value * waypointnorthSouth;
                shipwestEast += value * waypointwestEast;
            }
          }
        if(shipnorthSouth<0){shipnorthSouth *= -1;}
        if(shipwestEast<0){shipwestEast *= -1;}
        int manhattandistance = shipnorthSouth + shipwestEast;
        System.out.println("Part 2: Manhattan distance: " + manhattandistance);
    }

    private static ArrayList<String> getInput(){
        try{
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            ArrayList<String> input = new ArrayList<>();
            while((line = br.readLine()) != null){
                input.add(line);
            }
            return input;
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
