import java.util.Comparator;

public class GenomeComparator implements Comparator<Genome> {
    @Override
    public int compare(Genome firstGenome, Genome secondGenome) {
        return Long.compare(firstGenome.fitnessFunctionScore, secondGenome.fitnessFunctionScore);
    }
}
