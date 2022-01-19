package Day15;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Day15 {
    private final static String path = "src\\Day15\\input.txt";
    private final static int[] input = {0,3,6};

    public static void main(String[] args){
        teil1();
        teil2();
    }

    public static void teil1(){
        System.out.println("Part 1");
        HashMap<Integer,Integer> gamelog = new HashMap<>();
        for (int i = 0; i <= 2020; i++) {
            if(i<input.length){
                gamelog.put(i,input[i]);
            }else{
                int lastnumber = gamelog.get((i-1));
                if(alreadySaid(new HashMap<>(gamelog),lastnumber)){
                    gamelog.put(i,getAge(gamelog,lastnumber));
                }else{
                    gamelog.put(i,0);
                }
            }
        }
        System.out.println(gamelog.get(2019));
    }

    private static int getAge(HashMap<Integer,Integer> gamelog,int number){
        for(int i = gamelog.size()-2;i>=0;i--){
            if(gamelog.get(i) == number){
                return (gamelog.size()-1)-i;
            }
        }
        return -1;
    }

    private static boolean alreadySaid(HashMap<Integer,Integer> gamelog,int number){
        gamelog.remove(gamelog.size()-1);
        return gamelog.containsValue(number);
    }

    public static void teil2(){
        //TODO
    }
}
