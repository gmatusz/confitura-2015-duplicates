package pl.gmatusz.confitura2015.viacom.duplicates;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by gmatusz on 2015-07-06.
 */
public class DuplicateProcessor<E> {
    private int initialCapacity;
    private float loadFactor;

    public DuplicateProcessor(int initialCapacity, float loadFactor) {
        this.initialCapacity = initialCapacity;
        this.loadFactor = loadFactor;
    }

    public List<E> uniq(List<E> inputList) {
        LinkedHashSet<E> uniqueElements = new LinkedHashSet<E>(initialCapacity, loadFactor);

        for (E elem : inputList) {
            if (!uniqueElements.contains(elem)) {
                uniqueElements.add(elem);
            }
        }

        return new LinkedList<E>(uniqueElements);
    }
}
