package Day04;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Day4 {
    private final static String path = "src\\Day04\\input.txt";

    public static void main(String[] args){
        teil1();
        teil2();
    }

    public static void teil1(){
        try{
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            String passport = "";
            ArrayList<String> passports = new ArrayList<>();
            while((line = br.readLine()) != null){
                if(line.equals("")){
                    passports.add(passport);
                    passport = "";
                    continue;
                }
                if(passport.equals("")){
                    passport = line;
                }else{
                    passport = passport + " " + line;
                }
            }
            passports.add(passport);
            int counter = 0;
            for(String s : passports){
                if(isValid(s)){
                    counter++;
                }
            }
            System.out.println("Valid passports: " + counter);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    private static boolean isValid(String s){
        return (s.contains("byr:") &&
                s.contains("iyr:") &&
                s.contains("eyr:") &&
                s.contains("hgt:") &&
                s.contains("hcl:") &&
                s.contains("ecl:") &&
                s.contains("pid:"));
    }

    private static boolean isValid2(String s){
        System.out.println("Checking String " + s);
        String[] passport = s.split(" ");
        HashMap<String,String> values = new HashMap<>();
        for(String field : passport){
            values.put(field.split(":")[0],field.split(":")[1]);
        }
        if(values.containsKey("byr") && values.containsKey("iyr") && values.containsKey("eyr") && values.containsKey("hgt") && values.containsKey("hcl") && values.containsKey("ecl") && values.containsKey("pid")){
            //byr
            String tmp = values.get("byr");
            if(tmp.length() != 4 || Integer.parseInt(tmp) < 1920 || Integer.parseInt(tmp) > 2002){
                System.out.println("byr " + tmp + " is Invalid");
                return false;
            }
            //iyr
            tmp = values.get("iyr");
            if(tmp.length() != 4 || Integer.parseInt(tmp) < 2010  || Integer.parseInt(tmp) > 2020){
                System.out.println("iyr " + tmp + " is Invalid");
                return false;
            }
            //eyr
            tmp = values.get("eyr");
            if(tmp.length() != 4 || Integer.parseInt(tmp) < 2020  || Integer.parseInt(tmp) > 2030){
                System.out.println("eyr " + tmp + " is Invalid");
                return false;
            }
            //hgt
            tmp = values.get("hgt");
            if(tmp.length() < 4){return false;}
            int i = Integer.parseInt(tmp.substring(0, tmp.length() - 2));
            if(tmp.endsWith("cm")){
                if(i < 150  || i > 193){
                    System.out.println("hgt " + tmp + " is Invalid");
                    return false;
                }
            }else if(tmp.endsWith("in")){
                if(i < 59  || i > 76){
                    System.out.println("hgt " + tmp + " is Invalid");
                    return false;
                }
            }else{
                System.out.println("hgt " + tmp + " is Invalid");
                return false;
            }
            //hcl
            tmp = values.get("hcl");
            if(!tmp.matches("#([a-f|0-9]){6}") ){
                System.out.println("hcl " + tmp + " is invalid");
                return false;
            }
            //ecl
            tmp = values.get("ecl");
            if(!tmp.matches("(amb|blu|brn|gry|grn|hzl|oth)") ){
                System.out.println("ecl " + tmp + " is invalid");
                return false;
            }
            //pid
            tmp = values.get("pid");
            if(!tmp.matches("[0-9]{9}") ){
                System.out.println("pid " + tmp + " is invalid");
                return false;
            }
        }else{
            System.out.println("Missing fields detected");
            return false;
        }
        return true;
    }

    public static void teil2(){
        try{
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            String passport = "";
            ArrayList<String> passports = new ArrayList<>();
            while((line = br.readLine()) != null){
                if(line.equals("")){
                    passports.add(passport);
                    passport = "";
                    continue;
                }
                if(passport.equals("")){
                    passport = line;
                }else{
                    passport = passport + " " + line;
                }
            }
            passports.add(passport);
            int counter = 0;
            for(String s : passports){
                if(isValid2(s)){
                    counter++;
                }
            }
            System.out.println("Valid passports: " + counter);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
