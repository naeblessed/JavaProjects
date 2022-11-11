import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class GameOfLife {

    private boolean[][] board;
    private final int amountOfRows;
    private final int amountOfCols;
    private final double p;

    public GameOfLife(int amountOfRows, int amountOfCols, double p){
        this.board = new boolean[amountOfRows][amountOfCols];
        this.amountOfRows = amountOfRows;
        this.amountOfCols = amountOfCols;
        this.p = p;
    }

    public void generateInitialState(){
        for (int i = 0; i < this.amountOfRows; i++) {
            for (int j = 0; j < this.amountOfCols; j++) {
                if(Math.random() < this.p)
                    board[i][j] = true;
                //System.out.println(i + " " + j + " " + board[i][j]);
            }
        }
    }

    public void getUserInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String  lines = br.readLine();

        String[] strs = lines.trim().split("\\s+");

        for (int i = 0; i < strs.length; i += 2) {
            board[Integer.parseInt(strs[i])][Integer.parseInt(strs[i+1])] = true;
        }

//        for (int i = 0; i < this.amountOfRows; i++) {
//            for (int j = 0; j < this.amountOfCols; j++) {
//                System.out.println(i + " " + j + " " + board[i][j]);
//            }
//        }
    }

    private boolean isAlive(int i, int j){
        return this.board[i][j];
    }

    private int calculateIndexRow(int i){
        if(i >= this.amountOfRows) return 0;
        else if(i < 0) return this.amountOfRows - 1;
        return i;
    }

    private int calculateIndexCol(int i){
        if(i >= this.amountOfCols) return 0;
        else if(i < 0) return this.amountOfCols - 1;
        return i;
    }

    private int countAliveNeighbours(int i, int j){
        int counter = 0;
        for (int k = i - 1; k <= i + 1; k++) {
            for (int l = j - 1; l <= j + 1; l++) {
                if(k == i && l == j) continue;
                if(isAlive(calculateIndexRow(k), calculateIndexCol(l))) counter++;
            }
        }
        return counter;
    }

    private boolean[][] cloneBoard(){
        boolean[][] copyOfBoard = new boolean[this.amountOfRows][this.amountOfCols];
        for (int i = 0; i < this.amountOfRows; i++) {
            for (int j = 0; j < this.amountOfCols; j++) {
                copyOfBoard[i][j] = this.board[i][j];
            }
        }
        return copyOfBoard;
    }

    public void updateBoardState(){
        boolean[][] copyOfBoard = cloneBoard();
        for (int i = 0; i < this.amountOfRows; i++) {
            for (int j = 0; j < this.amountOfCols; j++) {
                int liveNeighbours = countAliveNeighbours(i, j);
                if(isAlive(i, j)){
                    if(liveNeighbours < 2 || liveNeighbours > 3) copyOfBoard[i][j] = false;
                } else if(liveNeighbours == 3) copyOfBoard[i][j] = true;
            }
        }
        this.board = copyOfBoard;
    }

    private boolean[][] getBoard(){
        return this.board;
    }

    public static void drawBoard(boolean[][] board){
        StdDraw.clear();
        StdDraw.setXscale(0, board[0].length);
        StdDraw.setYscale(0, board.length);
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[0].length; j++)
                if(board[i][j]) StdDraw.filledRectangle(j + 0.5, i + 0.5, 0.47, 0.47);
        StdDraw.show();
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        Scanner scan = new Scanner(System.in);
        int rows = scan.nextInt();
        int cols = scan.nextInt();
        double p = scan.nextDouble();
        StdDraw.setPenColor(StdDraw.BOOK_BLUE);
        StdDraw.enableDoubleBuffering();
        GameOfLife gameOfLife = new GameOfLife(rows, cols, p);
        //gameOfLife.getUserInput();
        gameOfLife.generateInitialState();
        while(true){
            drawBoard(gameOfLife.getBoard());
            StdDraw.pause(500);
            gameOfLife.updateBoardState();
        }
    }
}