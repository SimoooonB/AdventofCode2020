package Day18;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day18 {
    private final static String path = "src\\Day18\\input.txt";
    private final static ArrayList<String> input = getInput();

    public static void main(String[] args){
        System.out.println("Part 1:");
        teil1();

        System.out.println("Part 2:");
        teil2();
    }

    public static void teil1(){
        long result = 0;
        for(String s:input){
            s = new StringBuilder(s).reverse().toString();
            s = s.replace("(","?");
            s = s.replace(")","(");
            s = s.replace("?",")");
            result += evaluate(s);
        }
        System.out.println(result);
    }

    private static long evaluate(String expression){
        if(!expression.contains("+") && !expression.contains("*")){
            return Long.parseLong(expression);
        }else{
           if(!expression.contains("(")){
               char op = '+';
               int firstOperator = Integer.MAX_VALUE;
               if(expression.contains("+")){
                   firstOperator = expression.indexOf("+");
               }
               if(expression.contains("*") && expression.indexOf("*") < firstOperator){
                   firstOperator = expression.indexOf("*");
                   op = '*';
               }
               long first = Long.parseLong(expression.substring(0,firstOperator-1));
               String second = expression.substring(firstOperator+2);
               if(op == '+'){
                   return first + evaluate(second);
               }else{
                   return first * evaluate(second);
               }

           }else{
               int firstBracketIndex = expression.indexOf("(");
               int secondBracketIndex = 0;
               int depth = 1;
               for(int i = firstBracketIndex+1;i<expression.length();i++){
                   if(expression.charAt(i) == '('){depth++;}
                   if(expression.charAt(i) == ')'){depth--;}
                   if(depth == 0){
                       secondBracketIndex = i;
                       break;
                   }
               }
               String beforeBrackets = "";
               String afterBrackets = "";
               if(firstBracketIndex != 0) {
                   beforeBrackets = expression.substring(0, firstBracketIndex);
               }
               String evaluatedBrackets = String.valueOf(evaluate(expression.substring(firstBracketIndex+1,secondBracketIndex)));
               if(secondBracketIndex < expression.length()-1){
                   afterBrackets = expression.substring(secondBracketIndex+1);
               }
               return evaluate(beforeBrackets + evaluatedBrackets + afterBrackets);
           }
        }
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
