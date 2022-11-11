import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ECA {
    private static final int[][] arrayOfEightNumbers = {{0, 0, 0}, {0, 0, 1}, {0, 1, 0}, {0, 1, 1}, {1, 0, 0}, {1, 0, 1}, {1, 1, 0}, {1, 1, 1}};
    public static int[] bin = new int[8];
    private static int[][] grid = new int[20][41];

    private static void decimalToBinary(int[] arr, int number){
        int i = 0;
        while(number > 0){
            arr[i++] = number%2;
            number /= 2;
        }
    }

    private static void initializeRequirements() throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter number between 0 and 255 (The Rule)");
        decimalToBinary(bin, scan.nextInt());
        getUserInput();
    }

    private static void getUserInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter indexes of cells which you want to be 1's");
        String lines = br.readLine();

        String[] strs = lines.trim().split("\\s+");

        for (int i = 0; i < strs.length; i++)
            grid[0][Integer.parseInt(strs[i])] = 1;
    }

    private static int findCorresponding(int numberOfIteration, int col){
            for (int j = 0; j < arrayOfEightNumbers.length; j++) {
                if(grid[numberOfIteration][(col - 1 + grid[0].length) % grid[0].length] == arrayOfEightNumbers[j][0] &&
                        grid[numberOfIteration][col] == arrayOfEightNumbers[j][1] &&
                        grid[numberOfIteration][(col + 1 + grid[0].length) % grid[0].length] == arrayOfEightNumbers[j][2])
                        return j;
            }
        System.out.println(numberOfIteration + " " + col);
        return -1559;
    }

    public static void simulateECA(){
        for (int i = 1; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j] = bin[findCorresponding(i - 1, j)];
            }
        }
    }


    public static void main(String[] args) throws IOException {
        initializeRequirements();
        simulateECA();
        for (int i = 0; i < bin.length; i++) {
            System.out.print(bin[i]);
        }
        System.out.println();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                System.out.print(grid[i][j]);
            }
            System.out.println();
        }
    }

}
