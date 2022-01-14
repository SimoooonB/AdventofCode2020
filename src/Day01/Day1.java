package Day01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day1 {
    private final static String path = "src\\Day01\\input.txt";

    public static void main(String[] args){
        teil1();
        teil2();
    }

    public static void teil1(){
        try{
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            ArrayList<Integer> input = new ArrayList<>();
            while((line = br.readLine()) != null){
                input.add(Integer.parseInt(line));
            }
            for(Integer x :input){
                if(input.contains(2020-x)){
                    System.out.println(x*(2020-x));
                    break;
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void teil2(){
        try{
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            ArrayList<Integer> input = new ArrayList<>();
            while((line = br.readLine()) != null){
                input.add(Integer.parseInt(line));
            }
            for(Integer x :input){
                for(Integer y : input){
                    //if(x==y){continue;}
                    if(input.contains(2020-x-y)){
                        System.out.println(x*y*(2020-x-y));
                        return;
                    }
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
