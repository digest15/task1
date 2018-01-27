package com.edu.task1.consoleprog;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Lists;
import com.google.common.collect.Multiset;
import com.google.common.primitives.Ints;

import java.text.MessageFormat;
import java.util.*;

/**
 * @author Eugeniy Murzin
 * @version $Id$
 */
public class Mset {

    private enum EntryComp implements Comparator<Multiset.Entry<?>> {
        DESCENDING{
            @Override
            public int compare(final Multiset.Entry<?> a, final Multiset.Entry<?> b){
                return Ints.compare(b.getCount(), a.getCount());
            }
        },
        ASCENDING{
            @Override
            public int compare(final Multiset.Entry<?> a, final Multiset.Entry<?> b){
                return Ints.compare(a.getCount(), b.getCount());
            }
        },
    }

    public static <E> List<Multiset.Entry<E>> getEntriesSortedByFrequency(
            final Multiset<E> ms, final boolean ascending){
        final List<Multiset.Entry<E>> entryList = Lists.newArrayList(ms.entrySet());
        Collections.sort(entryList, ascending
                ? EntryComp.ASCENDING
                : EntryComp.DESCENDING);
        return entryList;
    }

    public static void main(String[] args) {
        //Map maps = new HashMap();
        //Collections.sort(new ArrayList(maps.keySet()));
        final Multiset<String> ms =
                HashMultiset.create(Arrays.asList(
                        "Audi",
                        "BMW", "LADA",
                        "Datsun", "LADA", "ZAZ",
                        "Audi", "Audi", "Audi"
                ));


        System.out.println("ascending:");
        for(final Multiset.Entry<String> entry : getEntriesSortedByFrequency(ms, true)){
            System.out.println(MessageFormat.format("{0} ({1})",
                    entry.getElement(), entry.getCount()));
        }

        System.out.println("descending:");
        for(final Multiset.Entry<String> entry : getEntriesSortedByFrequency(ms, false)){
            System.out.println(MessageFormat.format("{0} ({1})",
                    entry.getElement(), entry.getCount()));
        }
    }
}