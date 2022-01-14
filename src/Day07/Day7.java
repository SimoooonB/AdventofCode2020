package Day07;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Day7 {
    private final static String path = "src\\Day07\\input.txt";

    public static void main(String[] args){
        teil1();
        teil2();
    }

    public static void teil1(){
        try{
            System.out.println("Teil 1:");
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            String key;
            String value;
            HashMap<String,String> rules = new HashMap<>();
            while((line = br.readLine()) != null){
                key = line.split(" bags contain ")[0];
                value = line.split(" bags contain ")[1];
                rules.put(key,value);
            }
            int counter = 0;
            for(String color: rules.keySet()){
                //System.out.println("\nNew Bag: " + color);
                if(containsBag(rules,color,"shiny gold")){
                    //System.out.println(color + " contains shiny gold eventually");
                    counter++;
                }else{
                    //System.out.println(color + " doesnt contain shiny gold eventually");
                }
            }
            System.out.println(counter + " bags contain a shiny gold bag eventually");
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    private static boolean containsBag(HashMap<String,String> rules,String bagColor,String lookFor){
        String bagContents = rules.get(bagColor);
        bagContents = bagContents.replace(".","");
        bagContents = bagContents.replace(" bags","");
        bagContents = bagContents.replace(" bag","");
        for(int i=1;i<10;i++){
            bagContents = bagContents.replace(i + " ","");
        }
        if(bagContents.contains(lookFor)){return true;}
        if(!bagContents.contains(",")){
            if(bagContents.equals("no other")){return false;}
            return containsBag(rules,bagContents,lookFor);
        }
        String[] bags = bagContents.split(", ");
        for(int i = 0;i<bags.length;i++){
            if(containsBag(rules,bags[i],lookFor)){
                return true;
            }
        }
        return false;
    }

    public static void teil2(){
        try{
            System.out.println("Teil 2:");
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            String key;
            String value;
            HashMap<String,String> rules = new HashMap<>();
            while((line = br.readLine()) != null){
                key = line.split(" bags contain ")[0];
                value = line.split(" bags contain ")[1];
                rules.put(key,value);
            }
            int counter = 0;
            System.out.println("A shiny gold bag contains " + numberOfBagsInside(rules,"shiny gold") + " bags.");
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    private static int numberOfBagsInside(HashMap<String,String> rules,String bagColor){
        String bagContents = rules.get(bagColor);
        bagContents = bagContents.replace(".","");
        bagContents = bagContents.replace(" bags","");
        bagContents = bagContents.replace(" bag","");
        if(bagContents.equals("no other")){return 0;}
        String[] bags = bagContents.split(", ");
        int counter = 0;
        for(int i = 0;i<bags.length;i++){
            counter += Integer.parseInt(bags[i].substring(0,1));
            counter += Integer.parseInt(bags[i].substring(0,1)) * numberOfBagsInside(rules,bags[i].substring(2));
        }
        return counter;
    }
}
