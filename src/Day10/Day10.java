package Day10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Day10 {
    private final static String path = "src\\Day10\\input.txt";

    public static void main(String[] args){
        teil1();
        //teil2();
    }

    public static void teil1(){
        try{
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            ArrayList<Integer> adapters = new ArrayList<>();
            while((line = br.readLine()) != null){
                adapters.add(Integer.parseInt(line));
            }
            Collections.sort(adapters);
            adapters.add(adapters.get(adapters.size()-1) + 3);
            int oneJolt = 0;
            int twoJolt = 0;
            int threeJolt = 0;
            int previous = 0;
            for(int i : adapters){
                if(i-previous == 1){
                    oneJolt++;
                }else if(i-previous == 2){
                    twoJolt++;
                }else if(i-previous == 3){
                    threeJolt++;
                }
                previous = i;
            }
            System.out.println("1 Jolt: " + oneJolt);
            System.out.println("2 Jolt: " + twoJolt);
            System.out.println("3 Jolt: " + threeJolt);
            System.out.println("Result: " + (oneJolt * threeJolt));

        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void teil2(){
        //Neeeeee
    }
}
