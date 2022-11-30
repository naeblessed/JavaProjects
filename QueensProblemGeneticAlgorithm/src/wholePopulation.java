import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import static java.util.Map.entry;

public class wholePopulation {
    protected Genome[] genomes;
    private Random generator = new Random();
    private int boardSize;
    public int getPopulation() {
        return population;
    }
    protected int population;

    wholePopulation(int boardSize, int sizeOfPopulation){// array of genomes
        this.population = sizeOfPopulation;
        this.boardSize = boardSize;
        genomes = new Genome[sizeOfPopulation];
        for (int i = 0; i < this.population; i++) {
            genomes[i] = new Genome(boardSize);
        }
    }

    protected void sortGenomes(){
        GenomeComparator genomeComparator = new GenomeComparator();
        Arrays.sort(genomes, genomeComparator);
    }

    protected Genome[] twoSiteCrossover2(){
        int n = this.population/2;
        Genome[] newPopulation = new Genome[n*(n - 1)/2];

        for (int i = 0; i < newPopulation.length; i++)
            newPopulation[i] = new Genome(boardSize);
        for (int i = this.population/2; i < this.population - 1; i++) {
            for (int j = i + 1, index = 0; j < this.population; j++) {
                twoSiteCrossoverHelper(this.genomes[i], this.genomes[j], newPopulation[index], newPopulation[++index]);
            }
        }
        //System.out.println(n*(n-1)/2);
        this.population = n*(n-1)/2;
        return newPopulation;
    }

    protected Genome[] twoSiteCrossover(){
        Genome [] newPopulation = new Genome[this.population];
        for (int i = 0; i < newPopulation.length; i++)
            newPopulation[i] = new Genome(boardSize);

        int counter = this.population/2, i = 0;
        while(counter-- > 0){
            int firstGenomeIndex, secondGenomeIndex;
            firstGenomeIndex = rouletteSelection();
            secondGenomeIndex = rouletteSelection();
            //System.out.println(firstGenomeIndex + " " + secondGenomeIndex);
            twoSiteCrossoverHelper(this.genomes[firstGenomeIndex], this.genomes[secondGenomeIndex], newPopulation[i++], newPopulation[i++]);
        }
        return newPopulation;
    }

    private int rouletteSelection(){
        int fitnessFunctionSum = 0;
        for (int i = 0; i < this.population; i++) {
            fitnessFunctionSum += genomes[i].fitnessFunction();
        }
        int randomNumber = generator.nextInt(fitnessFunctionSum);
        int partialSum = 0;
        for(int i = 0; i < this.population; i++){
            partialSum += this.genomes[i].fitnessFunction();
            if(partialSum >= randomNumber)
                return i;
        }
        return -690;
    }

    private void twoSiteCrossoverHelper(Genome A, Genome B, Genome newA, Genome newB){
        int left = generator.nextInt(A.size - 1);
        int right = generator.nextInt(left + 1, A.size);
        for (int i = 0; i < left; i++) {
            newA.genomeArray[i] = A.genomeArray[i];
            newB.genomeArray[i] = B.genomeArray[i];
        }
        for (int i = left; i <= right; i++) {
            newA.genomeArray[i] = B.genomeArray[i];
            newB.genomeArray[i] = A.genomeArray[i];
        }
        for (int i = right + 1; i < A.genomeArray.length; i++) {
            newA.genomeArray[i] = A.genomeArray[i];
            newB.genomeArray[i] = B.genomeArray[i];
        }
    }

}
