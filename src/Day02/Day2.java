package Day02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day2 {
    private final static String path = "src\\Day02\\input.txt";

    public static void main(String[] args){
        teil1();
        teil2();
    }

    public static void teil1(){
        try{
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            int validpasswordcounter = 0;
            int min,max;
            String password;
            char letter;
            int lettercounter;
            while((line = br.readLine()) != null){
                min = Integer.parseInt(line.split("-")[0]);
                max = Integer.parseInt(line.split("-")[1].split(" ")[0]);
                password = line.split(" ")[2];
                letter = line.split(" ")[1].charAt(0);
                lettercounter = 0;
                for(int i=0;i<password.length();i++){
                    if(letter == password.charAt(i)){
                        lettercounter++;
                    }
                }
                if(min<=lettercounter && lettercounter<=max){
                    validpasswordcounter++;
                }
            }
            System.out.println(validpasswordcounter);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void teil2(){
        try{
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            int validpasswordcounter = 0;
            int min,max;
            String password;
            char letter;
            while((line = br.readLine()) != null){
                min = Integer.parseInt(line.split("-")[0]);
                max = Integer.parseInt(line.split("-")[1].split(" ")[0]);
                password = line.split(" ")[2];
                letter = line.split(" ")[1].charAt(0);
                if((letter == password.charAt(min-1) && letter != password.charAt(max-1)) || (letter != password.charAt(min-1) && letter == password.charAt(max-1))){
                    validpasswordcounter++;
                }
            }
            System.out.println(validpasswordcounter);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
