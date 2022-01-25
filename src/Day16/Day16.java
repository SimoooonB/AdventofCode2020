package Day16;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day16 {
    private final static String path = "src\\Day16\\input.txt";
    private final static ArrayList<String> input= getInput();

    public static void main(String[] args){
        System.out.println("Part 1:");
        teil1();

        System.out.println("Part 2:");
        teil2();
    }

    public static void teil1(){
        int sum = 0;
        int min = 25;
        int max = 973;
        String[] values;
        for (int i = 25; i < input.size(); i++) {
            values = input.get(i).split(",");
            for (String value : values){
                int x = Integer.parseInt(value);
                if(x < min || x > max){
                    sum += x;
                }
            }
        }
        System.out.println(sum);
    }

    public static void teil2(){


        //remove invalid tickets
        int min = 25;
        int max = 973;
        String[] values;
        for (int i = 25; i < input.size(); i++) {
            values = input.get(i).split(",");
            for (String value : values){
                int x = Integer.parseInt(value);
                if(x < min || x > max){
                    input.remove(i);
                    i--;
                    break;
                }
            }
        }


        //create Fields in List
        int i = 0;
        ArrayList<Field> fields = new ArrayList<>();
        while (!input.get(i).equals("")) {
            fields.add(new Field(input.get(i)));
            i++;
        }

        //save "your ticket" data in int[] array
        while(!input.get(i).startsWith("your ticket")){i++;}
        i++;
        String[] tmp = input.get(i).split(",");
        int[] yourTicket = new int[tmp.length];
        for(int j = 0;j< yourTicket.length;j++){
            yourTicket[j] = Integer.parseInt(tmp[j]);
        }

        //read data of "nearby tickets" into List
        ArrayList<int[]> nearbyTickets = new ArrayList<>();
        while(!input.get(i).startsWith("nearby tickets")){i++;}
        i++;
        for(;i<input.size();i++){
            String[] s = input.get(i).split(",");
            int[] data = new int[s.length];
            for(int j = 0;j<s.length;j++){
                data[j] = Integer.parseInt(s[j]);
            }
            nearbyTickets.add(data.clone());
        }

        //get all fields
        long sum = 1;
        int fieldsLeft = fields.size();
        int validfieldscounter = 0;
        String field = "?";
        boolean valid;
        while(fieldsLeft > 0) {
            for(int index = 0;index<nearbyTickets.get(0).length;index++) {
                validfieldscounter = 0;
                for (Field f : fields) {
                    valid = true;
                    for (int[] nearbyTicket : nearbyTickets) {
                        if (!f.isValidValue(nearbyTicket[index])) {
                            //System.out.println(nearbyTickets.get(j)[index] + " not valid for " + f.getDescription());
                            valid = false;
                            break;
                        }
                    }
                    if (valid) {
                        //System.out.println("Value at index " + index + " from neary tickets possibly correlates to " + f.getDescription());
                        validfieldscounter++;
                        field = f.getDescription();
                    }
                }
                //If values are only valid for one field, found part of solution
                if (validfieldscounter == 1) {
                    System.out.println("Values at index " + index + " from nearby tickets correspond to " + field);
                    fieldsLeft--;
                    for(Field f:fields){
                        if(f.getDescription().equals(field)){
                            fields.remove(f);
                            break;
                        }
                    }
                    if(field.startsWith("departure")){
                        sum *= yourTicket[index];
                    }
                }
            }
        }
        System.out.println("Sum: " + sum);
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
