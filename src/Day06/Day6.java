package Day06;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day6 {
    private final static String path = "src\\Day06\\input.txt";

    public static void main(String[] args){
        teil1();
        teil2();
    }

    public static void teil1(){
        try{
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            String group = "";
            ArrayList<String> answers = new ArrayList<>();
            while((line = br.readLine()) != null){
                if(line.equals("")){
                    answers.add(group);
                    group = "";
                    continue;
                }
                for(int i = 0;i<line.length();i++){
                    if(!group.contains(line.substring(i,i+1))){
                        group = group + line.charAt(i);
                    }
                }

            }
            answers.add(group);
            int sum = 0;
            for(String s : answers){
                sum += s.length();
            }
            System.out.println("Sum: " + sum);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void teil2(){
        try{
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            String group = "*";
            String tmp;
            ArrayList<String> answers = new ArrayList<>();
            while((line = br.readLine()) != null){
                if(line.equals("")){
                    //System.out.println(group + " added");
                    answers.add(group);
                    group = "*";
                    continue;
                }
                if(group.equals("*")){
                    group = line;
                    continue;
                }
                tmp = "";
                for(int i = 0;i<line.length();i++){
                    //System.out.println("Checking " + line.charAt(i));
                    if(group.contains(line.substring(i,i+1))){
                        tmp = tmp + line.charAt(i);
                        //System.out.println(line.charAt(i) + " is in " + group);
                    }else{
                        //System.out.println(line.charAt(i) + " is not in " + group);
                    }
                }
                group = tmp;

            }
            answers.add(group);
            //System.out.println(group + " added");
            int sum = 0;
            for(String s : answers){
                sum += s.length();
                //System.out.println(s + ",added " + s.length() + " to counter " + sum);
            }
            System.out.println("Sum: " + sum);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
