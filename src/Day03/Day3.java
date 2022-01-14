package Day03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day3 {
    private final static String path = "src\\Day03\\input.txt";

    public static void main(String[] args){
        teil1();
        teil2();
    }

    public static void teil1(){
        try{
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            ArrayList<String> treemap = new ArrayList<>();
            while((line = br.readLine()) != null){
                treemap.add(line);
            }
            int index = 0;
            int treecounter = 0;
            for(String s : treemap){
                if(s.charAt(index) == '#'){
                    treecounter++;
                }
                index = (index + 3) % s.length();
            }
            System.out.println("Trees: " + treecounter);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void teil2(){
        try{
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            ArrayList<String> treemap = new ArrayList<>();
            while((line = br.readLine()) != null){
                treemap.add(line);
            }
            long sum = countTrees(treemap,1,1);
            sum *= countTrees(treemap,3,1);
            sum *= countTrees(treemap,5,1);
            sum *= countTrees(treemap,7,1);
            sum *= countTrees(treemap,1,2);
            System.out.println("Trees: " + sum);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    private static int countTrees(ArrayList<String> treemap,int right,int down){
        int index = 0;
        int treecounter = 0;
        String line;
        for(int i = 0;i<treemap.size()-(down-1);i+=down){
            line = treemap.get(i);
            if(line.charAt(index) == '#'){
                treecounter++;
            }
            index = (index + right) % line.length();
        }
        return treecounter;
    }
}
