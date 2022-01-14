package Day05;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Day5 {
    private final static String path = "src\\Day05\\input.txt";

    public static void main(String[] args){
        teil1();
        teil2();
    }

    public static void teil1(){
        try{
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            ArrayList<String> boardingpasses = new ArrayList<>();
            while((line = br.readLine()) != null){
                boardingpasses.add(line);
            }
            int maxSeatID = 0;
            for(String s : boardingpasses){
                if(getSeatID(s) > maxSeatID){
                    maxSeatID = getSeatID(s);
                }
            }
            System.out.println("Max Seat ID: " + maxSeatID);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    private static int getSeatID(String s){
        int min = 0;
        int max = 127;
        for(int i = 0;i<7;i++){
            if(s.charAt(i) == 'F'){
               max -= (max-min+1)/2;
            }else{
                min += (max-min+1)/2;
            }
        }
        int rownumber = min;
        min = 0;
        max = 7;
        for(int i = 7;i<s.length();i++){
            if(s.charAt(i) == 'L'){
                max -= (max-min+1)/2;
            }else{
                min += (max-min+1)/2;
            }
        }
        int columnnumber = min;
        return rownumber * 8 + columnnumber;
    }

    public static void teil2(){
        try{
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            ArrayList<String> boardingpasses = new ArrayList<>();
            while((line = br.readLine()) != null){
                boardingpasses.add(line);
            }
            ArrayList<Integer> seats = new ArrayList<>();
            for(String s : boardingpasses){
                seats.add(getSeatID(s));
            }
            Collections.sort(seats);
            int tmp = 0;
            for(Integer i :seats){
                //System.out.println(i);
                if(tmp != i-1 && tmp != 0){
                    System.out.println("Your seat: " + (tmp+1));
                }
                tmp = i;
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
