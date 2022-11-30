import org.apache.commons.math3.util.CombinatoricsUtils;

import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

import static java.util.Map.entry;

public class QueensProblem {

    private int boardSize;
    private wholePopulation initialPopulation;
    private Genome[] res;
    private Map<Integer, Integer> possibleSolutions = Map.ofEntries(
            entry(4, 2),
            entry(5, 10),
            entry(6, 4),
            entry(7, 40),
            entry(8, 92),
            entry(9, 352),
            entry(10, 724),
            entry(11, 2680),
            entry(12, 14200),
            entry(13 , 71712)
    );

    private boolean isAlreadyFound(Genome genome){
        for (int i = 0; i < res.length; i++) {
            if(Arrays.equals(res[i].genomeArray, genome.genomeArray))
                return true;
        }
        return false;
    }

    public void initializeSimulation(){
        for (int i = 0; i < res.length; i++) {
            res[i] = new Genome(boardSize);
        }

        int counter = 0;
        while(counter < res.length){
            for (int i = 0; i < initialPopulation.genomes.length; i++) {
                if(initialPopulation.genomes[i].fitnessFunction() == initialPopulation.genomes[i].maximumNumberOfAttacks &&
                        !isAlreadyFound(initialPopulation.genomes[i])){
                    res[counter++] = initialPopulation.genomes[i];
                    System.out.print(counter + ") ");
                    initialPopulation.genomes[i].printGenome();
                }
            }
            initialPopulation.sortGenomes();
            initialPopulation.genomes = initialPopulation.twoSiteCrossover();
            for (int i = 0; i < initialPopulation.genomes.length; i++) {
                initialPopulation.genomes[i].mutation();
            }
        }
    }

    public QueensProblem(int boardSize, int population) {
        res = new Genome[possibleSolutions.get(boardSize)];
        this.boardSize = boardSize;
        this.initialPopulation = new wholePopulation(boardSize, population);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Size of the Board");
        int boardSize = sc.nextInt();
        System.out.println("Enter the initial population amount");
        int population = sc.nextInt();
        sc.close();
        QueensProblem globalTest = new QueensProblem(boardSize, population);
        globalTest.initializeSimulation();
    }
}
