package edu.jhu.fcriscu1.functionaljava.gscollections.iterations;

import com.gs.collections.api.list.MutableList;
import com.gs.collections.api.list.ParallelListIterable;
import com.gs.collections.impl.list.mutable.FastList;
import org.apache.log4j.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by fcriscuo on 11/16/15.
 */
public class ParallelDemo {
    private static final Logger logger = Logger.getLogger(ParallelDemo.class);
    public static void main (String...args) {
        FastList<Integer> integers = FastList.newListWith(1,2,3,4,5,6,7,8,9,10,11,12);
        ExecutorService threadPool =
                Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        int batchSize = 2;
        ParallelListIterable<Integer> parallelListIterable = integers.asParallel(threadPool,batchSize);
        // deferred evaluation
        ParallelListIterable<Integer> evenNumbers = parallelListIterable.select(i -> i % 2 == 0);
        // deferred evaluation
        ParallelListIterable<String> evenStrings = evenNumbers.collect(Object::toString);
        // forced evaluation
        MutableList<String> strings = evenStrings.toList();
        threadPool.shutdown();
        for(String s: strings){ logger.info(s);}


    }
}
