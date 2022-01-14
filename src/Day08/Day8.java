package Day08;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day8 {
    private final static String path = "src\\Day08\\input.txt";
    private static int acc = 0;

    public static void main(String[] args){
        teil1();
        teil2();
    }

    public static void teil1(){
        try{
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            ArrayList<String> instructions = new ArrayList<>();
            ArrayList<Integer> instructionsDone = new ArrayList<>();
            while((line = br.readLine()) != null){
                instructions.add(line);
            }
            int index = 0;
            while(!instructionsDone.contains(index)){
                instructionsDone.add(index);
                index = interpreteInstruction(instructions.get(index),index);
            }
            System.out.println("Teil 1 : " + acc);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    private static int interpreteInstruction(String instruction,int index){
        String type = instruction.split(" ")[0];
        boolean plus = instruction.split(" ")[1].startsWith("+");
        int value = Integer.parseInt(instruction.split(" ")[1].substring(1));
        switch(type){
            case "acc":
                if(plus){
                    acc += value;
                }else{
                    acc -= value;
                }
                return index + 1;
            case "jmp":
                if(plus){
                    return index + value;
                }
                return index - value;
            case "nop":
                return index + 1;
            default:
                return -1;
        }
    }


    public static void teil2(){
        try{
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            ArrayList<String> instructions = new ArrayList<>();
            ArrayList<Integer> instructionsDone = new ArrayList<>();
            while((line = br.readLine()) != null){
                instructions.add(line);
            }
            ArrayList<String> changedInstructions = new ArrayList<>();
            for(int i = 0;i<instructions.size();i++) {
                changedInstructions.clear();
                changedInstructions.addAll(instructions);
                if(changedInstructions.get(i).startsWith("nop")){
                    changedInstructions.set(i,"jmp" + changedInstructions.get(i).substring(3));
                }else if(changedInstructions.get(i).startsWith("jmp")){
                    changedInstructions.set(i,"nop" + changedInstructions.get(i).substring(3));
                }else{
                    continue;
                }
                //System.out.println("Testing to change instruction " + i);
                int index = 0;
                acc = 0;
                instructionsDone.clear();
                while (!instructionsDone.contains(index)) {
                    if (index >= instructions.size()) {
                        System.out.println("Teil 2 : " + acc);
                        return;
                    }
                    instructionsDone.add(index);
                    index = interpreteInstruction(changedInstructions.get(index), index);
                }
                //System.out.println(i + " doesnt have to be changed");
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
