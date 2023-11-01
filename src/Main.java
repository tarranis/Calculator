import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner math = new Scanner(System.in);
        String calc = math.nextLine();

        try {
            String result = calc(calc.trim());
            System.out.println(result);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String calc(String input) {
        List<String> mathOP = new ArrayList<>(List.of("+", "-", "/", "*"));
        String op = null;
        input = input.replaceAll(" ","");

        for(String operator : mathOP){
            op = input.contains(operator) ? operator : null;
            if(op != null) break;
        }
        String[] data = op != null ? input.split("\\Q" + op + "\\E") : null;

        if(data == null || data.length != 2){
            throw new RuntimeException();
        }

        if(isNumeric(data[0]) && isNumeric(data[1])){
            int res = calculateInArabic(Integer.parseInt(data[0]),Integer.parseInt(data[1]),op);
            return(String.valueOf(res));
        }

        char[] firstOperand = data[0].toCharArray();
        char[] secondOperand = data[1].toCharArray();
        char[] resultCharArray = new char[firstOperand.length + secondOperand.length];
        int index = 0;

        for (char item: firstOperand) {
            resultCharArray[index++] = item;
        }
        for (char item: secondOperand) {
            resultCharArray[index++] = item;
        }

        for(char i : resultCharArray){
            String element = Character.toString(i);
            if(!arabic.contains(element)){
                throw new RuntimeException();
            }
        }
        int a = romanToInt(firstOperand);
        int b = romanToInt(secondOperand);
        int resultIntFromRoman = calculateInArabic(a,b,op);
        if (resultIntFromRoman <= 0) {
            throw new RuntimeException();
        }
        return(intToRoman(resultIntFromRoman));
    }
    public static String intToRoman(int num)
    {
        arabic[] values = arabic.values();
        StringBuilder roman = new StringBuilder();
        for(int i = values.length - 1; i >= 0 ; i--)
        {
            while(num >= values[i].getValue())
            {
                num = num - values[i].getValue();
                roman.append(values[i].name());
            }
        }
        return roman.toString();
    }
     public static int romanToInt(char[] operand) {
        int result = 0;
        int prev = 0;
        for(int i = operand.length - 1; i >= 0; i--) {
            int curr = arabic.getBySymbol(Character.toString(operand[i]));
            if(curr<prev)  {
                result -= curr;
            }
            else {
                result += curr;
            }
        }
        return result;
    }

    public static int calculateInArabic(int a, int b, String op){
        try {
            if (a < 1 || a > 10 || b < 1 || b > 10) {
                throw new RuntimeException("Введенные операнды превышают допустимый диапозон!");
            }
        } catch (NumberFormatException ex) {
            throw new RuntimeException();
        }

        switch (op) {
            case "+":
                return(a + b);
            case "-":
                return(a - b);
            case "*":
                return(a * b);
            case "/":
                return(a / b);
            default:
                throw new IllegalStateException();
        }
    }

    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}