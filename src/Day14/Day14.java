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
        assert input != null;
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
        String bitmask = "";
        HashMap<Long,Long> memory= new HashMap<>();
        ArrayList<Long> keys;
        long value;
        assert input != null;
        for(String line : input){
            if(line.startsWith("mask")){
                bitmask = line.substring(7);
            }else if(line.startsWith("mem")){
                int initialKey = Integer.parseInt(line.replace("["," ").replace("]"," ").split(" ")[1]);
                keys = getKeys(bitmask,initialKey);
                value = Long.parseLong(line.split(" = ")[1]);
                for(long key:keys) {
                    memory.put(key, value);
                }
            }else{
                System.exit(-1);
            }
        }
        long sum = 0;
        for(long i : memory.keySet()){
            sum += memory.get(i);
        }
        System.out.println(sum);
    }

    /*
    Returns a List of Long values that get generated by applying the bitmask on the initial
    key (Method for Part 2)
     */
    private static ArrayList<Long> getKeys(String bitmask,long initialKey){
        ArrayList<Long> keys = new ArrayList<>();
        StringBuilder binaryKey = new StringBuilder(Long.toBinaryString(initialKey));
        while(binaryKey.length()<bitmask.length()){
            binaryKey.insert(0, "0");
        }
        int floatingBitsCount = 0;
        for(int i = 0;i<bitmask.length();i++){
            if(bitmask.charAt(i) == '1'){
                binaryKey.setCharAt(i,'1');
            }else if(bitmask.charAt(i) == 'X'){
                binaryKey.setCharAt(i,'X');
                floatingBitsCount++;
            }
        }
        ArrayList<StringBuilder> adresses= new ArrayList<>();
        ArrayList<StringBuilder> newAdresses= new ArrayList<>();
        adresses.add(binaryKey);
        for(int i = 0;i<floatingBitsCount;i++){
            newAdresses.clear();
            for(StringBuilder current : adresses){
                int index = current.lastIndexOf("X");
                current.setCharAt(index, '0');
                newAdresses.add(new StringBuilder(current));
                current.setCharAt(index, '1');
                newAdresses.add(new StringBuilder(current));
            }
            adresses.clear();
            adresses.addAll(newAdresses);
        }
        for(StringBuilder s : adresses){
            keys.add(binaryToLong(s.toString()));
        }
        return keys;
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
