import java.util.ArrayList;
import java.util.List;

public class Queens {

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<List<String>>();
        char[][] board = new char[n][n];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                board[i][j] = 'x';

        nQueensDFSHelper(result, board, 0);
        //System.out.println(result);
        result.forEach((list) -> {
            list.forEach((num) -> System.out.println(num));
        });
        return result;
    }

    private void nQueensDFSHelper(List<List<String>> result, char[][] board, int row){
        if(row == board.length){
            result.add(resultBuilder(board));
            return;
        }
        for(int col = 0; col < board.length; col++){
            if(isValidPosition(board, row, col)){
                board[row][col] = 'Q';
                nQueensDFSHelper(result, board, row + 1);
                board[row][col] = 'x';
            }
        }
    }

    private boolean isValidPosition(char[][] board, int row, int col){
        for(int i = 0;i < row; i++) {
            if(board[i][col] == 'Q')
                return false;
        }
        for(int i = row - 1,j = col +1 ;i >= 0 && j < board.length; i--,j++) {
            if(board[i][j] == 'Q')
                return false;
        }
        for(int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if(board[i][j] == 'Q')
                return false;
        }
        return true;
    }

    private List<String> resultBuilder(char board[][]) {
        List<String> val = new ArrayList<>();
        for(int i = 0; i < board.length; i++)
            val.add(new String(board[i]));
        return val;
    }

    public static void main(String[] args) {
        Queens instance = new Queens();
        instance.solveNQueens(4);

    }
}
