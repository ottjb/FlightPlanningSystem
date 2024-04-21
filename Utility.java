import java.util.Scanner;

public class Utility {
    private Scanner in = new Scanner(System.in);

    public Utility() {
        this.in = new Scanner(System.in);
    }

    public int getIntegerInput() {
        String input = in.nextLine();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
            return getIntegerInput();
        }
    }

    public double getDoubleInput() {
        String input = in.nextLine();
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
            return getDoubleInput();
        }
    }

    public String getStringInput() {
        String input = in.nextLine();
        return input;
    }

    public boolean isNumeric(String str) { 
        try {  
            Double.parseDouble(str);  
            return true;
        } catch(NumberFormatException e){  
            return false;  
        }  
      }

    public void close() {
        System.out.println("closing");
        in.close();
    }
}
