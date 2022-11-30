import org.apache.commons.math3.util.CombinatoricsUtils;

import java.util.Random;

public class Genome{
    public int[] genomeArray;
    private Random generator = new Random();
    protected int size;//size of genome (= size of board's side)
    protected long maximumNumberOfAttacks;
    protected long fitnessFunctionScore;

    public Genome(int size) {//initialize genome  randomly
        this.maximumNumberOfAttacks = CombinatoricsUtils.binomialCoefficient(size,2);
        this.size = size;
        this.genomeArray = new int[size];
        for (int i = 0; i < size; i++) {
            genomeArray[i] = generator.nextInt(size);
        }
        this.fitnessFunctionScore = fitnessFunction();
    }

    protected long fitnessFunction(){
        return this.maximumNumberOfAttacks - calculateNumberOfAttacks();
    }

    private int calculateNumberOfAttacks(){
        int numberOfAttacks = 0;
        int[][] tmpBoard = new int[this.size][this.size];
        for (int i = 0; i < this.size; i++) {
            tmpBoard[this.genomeArray[i]][i] = 1;
        }
        for(int counter = this.size - 1; counter >= 0; counter--){
            int row = this.genomeArray[counter];
            int col = counter;
            for (int i = counter - 1; i >= 0; i--) {
                if(tmpBoard[row][i] == 1)
                    numberOfAttacks++;

            }
            for(int i = row - 1, j = col + 1; i >= 0 && j < this.size; i--, j++){
                if(tmpBoard[i][j] == 1)
                    numberOfAttacks++;
            }

            for(int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--){
                if(tmpBoard[i][j] == 1)
                    numberOfAttacks++;
            }
        }
        return numberOfAttacks;
    }

    protected void mutation(){
        if(generator.nextInt(100) == 4)
            this.genomeArray[generator.nextInt(this.genomeArray.length)] = generator.nextInt(this.size);
    }

    void printGenome(){
        for (int i = 0; i < this.size; i++) {
            System.out.print(genomeArray[i] + " ");
        }
        System.out.println();
    }
}
