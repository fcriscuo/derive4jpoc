package edu.jhu.fcriscu1.functionaljava.gscollections.iterations;

import com.gs.collections.api.list.MutableList;
import com.gs.collections.api.partition.list.PartitionMutableList;
import com.gs.collections.impl.list.mutable.ListAdapter;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.List;

/**
 * Created by fcriscuo on 11/14/15.
 */
public class IterationsDemo001 {

    private static final Logger logger = Logger.getLogger(IterationsDemo001.class);
    public static void main (String...args){
        MutableList<Integer> ages = ListAdapter.adapt( Arrays.asList(100,300,43, 8, 88,17, 49, 50,1,28));
        List<Integer> greaterThan50 = ages.select(a ->a >50);
        // create a partitioned list
        PartitionMutableList agePationedList = ages.partition(age -> age >=50);
        MutableList<Integer> oldList = agePationedList.getSelected();
        MutableList<Integer> youngList = agePationedList.getRejected();
        logger.info("There are " +oldList.size() +" old people and " +youngList.size() +" young size");




    }
}
