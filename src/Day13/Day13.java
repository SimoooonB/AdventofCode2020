package Day13;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day13 {
    private final static String path = "src\\Day13\\input.txt";
    private static final ArrayList<String> input= getInput();

    public static void main(String[] args){
        teil1();
        teil2();
    }

    public static void teil1(){
        System.out.println("Part 1:");
        int earliestDepartTime = Integer.parseInt(input.get(0));
        String[] busIDs = input.get(1).replace(",x","").split(",");
        for(int i = earliestDepartTime;true;i++){
            for(String ID:busIDs){
                if(i%Integer.parseInt(ID) == 0){
                    int result = (i-earliestDepartTime) * Integer.parseInt(ID);
                    System.out.println(result);
                    return;
                }
            }
        }
    }

    public static void teil2(){
        System.out.println("Part 2:");
        String[] busIDs = input.get(1).split(",");
        long increaseBy = 1;
        int IDsDone = 0;
        for(long i = 1;true;i+=increaseBy){
            int currentID = Integer.parseInt(busIDs[IDsDone]);
            if(((i+IDsDone)%currentID) == 0){
                System.out.println("ID " + currentID + "done (timestamp:" + i + ")");
                IDsDone++;
                while(IDsDone < busIDs.length && busIDs[IDsDone].equals("x")){
                    IDsDone++;
                }
                increaseBy *= currentID;
            }
            if(IDsDone >= busIDs.length){
                System.out.println("Done, " + i);
                return;
            }
        }
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
