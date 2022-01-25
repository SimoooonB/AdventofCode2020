package DayXX;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DayXX {
    private final static String path = "src\\Day\\input.txt";
    private final static ArrayList<String> input = getInput();

    public static void main(String[] args){
        System.out.println("Part 1:");
        teil1();

        System.out.println("Part 2:");
        teil2();
    }

    public static void teil1(){
        //TODO
    }

    public static void teil2(){
        //TODO
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
