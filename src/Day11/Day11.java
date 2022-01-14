package Day11;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Day11 {
    private final static String path = "src\\Day11\\input.txt";

    public static void main(String[] args){
        teil1();
        teil2();
    }

    public static void teil1(){
        try{
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            ArrayList<String> input = new ArrayList<>();
            while((line = br.readLine()) != null){
                input.add(line);
            }
            Character[][] seats = new Character[input.size()][input.get(0).length()];
            for(int i = 0;i< seats.length;i++){
                for(int j = 0;j< seats[0].length;j++){
                    seats[i][j] = input.get(i).charAt(j);
                }
            }
            Character[][] previous = new Character[input.size()][input.get(0).length()];
            while(true){
                for(int j = 0;j< seats.length;j++){
                    for(int k = 0;k< seats[0].length;k++){
                        previous[j][k] = seats[j][k];
                    }
                }
                seats = doStep(seats);
                if(Arrays.deepEquals(previous, seats)){
                    System.out.println("Teil 1: " + countOccupiedSeats(seats));
                    return;
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    private static int countOccupiedSeats(Character[][] seats){
        int counter = 0;
        for(int i = 0;i< seats.length;i++){
            for(int j = 0;j<seats[0].length;j++){
                if(seats[i][j] == '#'){counter++;}
            }
        }
        return counter;
    }

    private static void print(Character[][] seats){
        for(int i = 0;i< seats.length;i++){
            for(int j = 0;j< seats[0].length;j++){
                System.out.print(seats[i][j]);
            }
            System.out.println();
        }
    }

    private static Character[][] doStep(Character[][] seats){
        Character[][] newSeats = new Character[seats.length][seats[0].length];
        for(int j = 0;j< seats.length;j++){
            for(int k = 0;k< seats[0].length;k++){
                newSeats[j][k] = seats[j][k];
            }
        }
        for(int i = 0;i< seats.length;i++){
            for(int j = 0;j< seats[0].length;j++){
                if(seats[i][j] == 'L' && adjacentSeats(seats,i,j) == 0){
                    newSeats[i][j] = '#';
                }else if(seats[i][j] == '#' && adjacentSeats(seats,i,j) >= 4){
                    newSeats[i][j] = 'L';
                }
            }
        }
        return newSeats;
    }

    private static Character[][] doStep2(Character[][] seats){
        Character[][] newSeats = new Character[seats.length][seats[0].length];
        for(int j = 0;j< seats.length;j++){
            for(int k = 0;k< seats[0].length;k++){
                newSeats[j][k] = seats[j][k];
            }
        }
        for(int i = 0;i< seats.length;i++){
            for(int j = 0;j< seats[0].length;j++){
                if(seats[i][j] == 'L' && adjacentSeats2(seats,i,j) == 0){
                    newSeats[i][j] = '#';
                }else if(seats[i][j] == '#' && adjacentSeats2(seats,i,j) >= 5){
                    newSeats[i][j] = 'L';
                }
            }
        }
        return newSeats;
    }

    private static int adjacentSeats(Character[][] seats,int x,int y){
        int counter = 0;
        boolean topRow = (x == 0);
        boolean bottomRow = (x == seats.length-1);
        boolean leftColumn = (y == 0);
        boolean rightColumn = (y == seats[0].length-1);
        if(!topRow) {
            if (seats[x-1][y] == '#') {
                counter++;
            }
            if(!leftColumn){
                if (seats[x-1][y-1] == '#') {
                    counter++;
                }
            }
            if(!rightColumn){
                if (seats[x-1][y+1] == '#') {
                    counter++;
                }
            }
        }
        if(!leftColumn){
            if (seats[x][y-1] == '#') {
                counter++;
            }
        }
        if(!rightColumn){
            if (seats[x][y+1] == '#') {
                counter++;
            }
        }
        if(!bottomRow) {
            if (seats[x+1][y] == '#') {
                counter++;
            }
            if(!leftColumn){
                if (seats[x+1][y-1] == '#') {
                    counter++;
                }
            }
            if(!rightColumn){
                if (seats[x+1][y+1] == '#') {
                    counter++;
                }
            }
        }
        return counter;
    }

    private static int adjacentSeats2(Character[][] seats,int x,int y){
        int counter = 0;

        //topleft
        for(int i = 1;(x-i>=0) && (y-i>=0);i++){
            if(seats[x-i][y-i] == '#'){
                counter++;
                break;
            }
            if(seats[x-i][y-i] == 'L'){break;}
        }
        //topright
        for(int i = 1;(x-i>=0) && (y+i<=seats[0].length-1);i++){
            if(seats[x-i][y+i] == '#'){
                counter++;
                break;
            }
            if(seats[x-i][y+i] == 'L'){break;}
        }
        //bottomright
        for(int i = 1;(x+i<=seats.length-1) && (y+i<=seats[0].length-1);i++){
            if(seats[x+i][y+i] == '#'){
                counter++;
                break;
            }
            if(seats[x+i][y+i] == 'L'){break;}
        }
        //bottomleft
        for(int i = 1;(x+i<=seats.length-1) && (y-i>=0);i++){
            if(seats[x+i][y-i] == '#'){
                counter++;
                break;
            }
            if(seats[x+i][y-i] == 'L'){break;}
        }
        //top
        for(int i = 1;(x-i >= 0);i++){
            if(seats[x-i][y] == '#'){
                counter++;
                break;
            }
            if(seats[x-i][y] == 'L'){break;}
        }
        //bottom
        for(int i = 1;(x+i<=seats.length-1);i++){
            if(seats[x+i][y] == '#'){
                counter++;
                break;
            }
            if(seats[x+i][y] == 'L'){break;}
        }
        //left
        for(int i = 1;(y-i>=0);i++){
            if(seats[x][y-i] == '#'){
                counter++;
                break;
            }
            if(seats[x][y-i] == 'L'){break;}
        }
        //right
        for(int i = 1;(y+i<=seats[0].length-1);i++){
            if(seats[x][y+i] == '#'){
                counter++;
                break;
            }
            if(seats[x][y+i] == 'L'){break;}
        }
        return counter;
    }

    public static void teil2(){
        try{
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            ArrayList<String> input = new ArrayList<>();
            while((line = br.readLine()) != null){
                input.add(line);
            }
            Character[][] seats = new Character[input.size()][input.get(0).length()];
            for(int i = 0;i< seats.length;i++){
                for(int j = 0;j< seats[0].length;j++){
                    seats[i][j] = input.get(i).charAt(j);
                }
            }


            Character[][] previous = new Character[input.size()][input.get(0).length()];
            for(int i = 0;true;i++){
                for(int j = 0;j< seats.length;j++){
                    for(int k = 0;k< seats[0].length;k++){
                        previous[j][k] = seats[j][k];
                    }
                }
                seats = doStep2(seats);
                if(Arrays.deepEquals(previous, seats)){
                    System.out.println("Teil 2: " + countOccupiedSeats(seats));
                    return;
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
