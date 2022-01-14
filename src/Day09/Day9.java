package Day09;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day9 {
    private final static String path = "src\\Day09\\input.txt";

    public static void main(String[] args){
        teil1();
        teil2();
    }

    public static void teil1(){
        try{
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            ArrayList<Long> numbers = new ArrayList<>();
            while((line = br.readLine()) != null){
                numbers.add(Long.parseLong(line));
            }
            ArrayList<Long> preamble = new ArrayList<>();
            for(int i = 0;i<25;i++){
                preamble.add(numbers.get(i));
            }
            for(int i = 25;i< numbers.size();i++){
                if(!sumExists(preamble,numbers.get(i))){
                    System.out.println("No sum for " + numbers.get(i) + " found");
                    break;
                }
                preamble.remove(0);
                preamble.add(numbers.get(i));
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    private static boolean sumExists(ArrayList<Long> numbers,long sum){
        for(int i = 0;i<numbers.size()-1;i++){
            if(numbers.contains(sum-numbers.get(i))){
                return true;
            }
        }
        return false;
    }

    public static void teil2(){
        try{
            final long invalidNumber = 90433990;
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            ArrayList<Long> numbers = new ArrayList<>();
            while((line = br.readLine()) != null){
                numbers.add(Long.parseLong(line));
            }
            long sum;
            long min;
            long max;
            long current;
            for(int i = 0;i<numbers.size();i++){
                min = numbers.get(i);
                max = numbers.get(i);
                sum = 0;
                for(int j = i;true;j++){
                    current = numbers.get(j);
                    sum += current;
                    if(current < min){min = current;}
                    if(current > max){max = current;}
                    if(sum > invalidNumber){
                        break;
                    }
                    if(sum == invalidNumber){
                        System.out.println("Set found! Sum is: " + (min+max));
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
