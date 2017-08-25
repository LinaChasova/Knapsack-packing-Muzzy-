import java.io.*;
import java.text.*;
import java.util.*;
import static java.lang.Math.abs;

/**
 * Created by AlinaCh on 21.02.2017.
 */
public class Main {

    private static ArrayList<Integer> time;
    private static ArrayList<Integer> money;
    private static int cash;

    /**
     * reads input, and places time for Muzzy to buy and its price
     * in required ArrayLists, and stores the cash Muzzy has
     * turns time into minute and price into pence
     */
    public static void read() {
        try {
            Scanner sc = new Scanner(new File("input.txt"));
            String input = sc.nextLine();
            NumberFormat format = NumberFormat.getInstance(Locale.US);
            cash = parseToInt(format.parse(sc.next()).doubleValue());
            String[] split = input.split(" ");
            for (int i = 0; i < split.length; i = i + 2) {
                String[] temp = split[i].split(":");
                time.add(Integer.parseInt(temp[1]) + Integer.parseInt(temp[0]) * 60);
                money.add(parseToInt(format.parse(split[i + 1]).doubleValue()));
            }
        } catch (FileNotFoundException | ParseException ex) {
            ex.printStackTrace();
        }
    }

    public static int parseToInt(double d) {
        int res = (int)d * 100;
        d = 100 * abs((int)d - d);
        res = res + (int)d;
        return res;
    }

    /**
     * writes the most time Muzzy can afford with his cash
     * @param s answer to write to the output file
     */
    public static void write(String s) {
        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream("output.txt"), "ascii"))) { writer.write(s); }
        catch (IOException ex) { }
    }

    public static void main(String[] args) {
        time = new ArrayList<>();
        money = new ArrayList<>();
        read();
        Knapsack solve = new Knapsack();
        write(solve.knapsack(time, money, cash).toString());
    }
}
