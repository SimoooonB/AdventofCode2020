package Day14;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Day14 {
    private final static String path = "src\\Day14\\input.txt";
    private final static ArrayList<String> input= getInput();

    public static void main(String[] args){
        teil1();
        teil2();
    }

    public static void teil1(){
        String bitmask = "";
        HashMap<Integer,Long> memory= new HashMap<>();
        int key;
        long value;
        for(String line : input){
            if(line.startsWith("mask")){
                bitmask = line.substring(7);
            }else if(line.startsWith("mem")){
                key = Integer.parseInt(line.replace("["," ").replace("]"," ").split(" ")[1]);
                value = Long.parseLong(line.split(" = ")[1]);
                value = applyBitmask(value,bitmask);
                memory.put(key,value);
            }else{
                System.exit(-1);
            }
        }
        long sum = 0;
        for(int i : memory.keySet()){
            sum += memory.get(i);
        }
        System.out.println(sum);
    }

    private static long applyBitmask(long value, String bitmask){
        StringBuilder number = new StringBuilder(Long.toBinaryString(value));
        while(number.length()<bitmask.length()){
            number.insert(0, "0");
        }
        for(int i = 0;i<bitmask.length();i++){
            if(bitmask.charAt(i) != 'X'){
                number.setCharAt(i,bitmask.charAt(i));
            }
        }
        return binaryToLong(number.toString());
    }

    private static long binaryToLong(String binary){
        long sum = 0L;
        for(int i = binary.length()-1;i>=0;i--){
            if(binary.charAt(i) == '1'){
                sum += Math.pow(2,binary.length()-i-1);
            }
        }
        return sum;
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
