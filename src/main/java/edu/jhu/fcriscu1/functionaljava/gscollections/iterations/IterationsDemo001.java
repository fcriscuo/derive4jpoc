package edu.jhu.fcriscu1.functionaljava.gscollections.iterations;

import com.google.common.collect.Lists;
import com.gs.collections.api.block.function.Function;
import com.gs.collections.api.block.predicate.Predicate;
import com.gs.collections.api.block.predicate.Predicate2;
import com.gs.collections.api.list.MutableList;
import com.gs.collections.api.multimap.list.MutableListMultimap;
import com.gs.collections.api.partition.list.PartitionMutableList;
import com.gs.collections.api.set.MutableSet;
import com.gs.collections.impl.list.mutable.ListAdapter;
import edu.jhu.fcriscu1.functionaljava.MiRNADataService;
import edu.jhu.fcriscu1.functionaljava.MiRnaData;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.List;

/**
 * Created by fcriscuo on 11/14/15.
 */
public class IterationsDemo001 {

    private static final Logger logger = Logger.getLogger(IterationsDemo001.class);
    public static void main (String...args){
        MutableList<Integer> ages = ListAdapter.adapt( Arrays.asList(100,300,43, 8, 88,17, 49, 50,1,28,110,99,103,120));
        List<Integer> greaterThan50 = ages.select(a ->a >50);
        // create a partitioned list
        PartitionMutableList agePationedList = ages.partition(age -> age >=50);
        MutableList<Integer> oldList = agePationedList.getSelected();
        MutableList<Integer> youngList = agePationedList.getRejected();
        logger.info("There are " +oldList.size() +" old people and " +youngList.size() +" young size");
        // miRNA Cancer entries
        MutableList<MiRnaData.MiRnaCancer> rnaList = ListAdapter.adapt(MiRNADataService.INSTANCE.getMiRnaCancers());
        MutableList<String> pubMedList = rnaList.collect(MiRnaData.MiRnaCancer::pubMed);
        logger.info("First PubMed entry: " +pubMedList.get(0));
        /*
        Collect the miRNA ids that relate to breast cancer
         */

        MutableList<String> breastIdList = rnaList.collectIf(new Predicate<MiRnaData.MiRnaCancer>() {
            @Override
            public boolean accept(MiRnaData.MiRnaCancer miRnaCancer) {
                return miRnaCancer.cancer().contains("breast");
            }
        },MiRnaData.MiRnaCancer::mirId
        );
        for(String id : breastIdList){
            logger.info(id);
        }
        //groupBy generates a Multimap
        MutableListMultimap<String,MiRnaData.MiRnaCancer> rnaMap01 = rnaList.groupBy(new Function<MiRnaData.MiRnaCancer, String>() {
            @Override
            public String valueOf(MiRnaData.MiRnaCancer miRnaCancer) {
                return miRnaCancer.cancer();
            }
        });
        MutableList<MiRnaData.MiRnaCancer> gastricList = rnaMap01.get("gastric cancer");
        for (MiRnaData.MiRnaCancer cancer : gastricList){
            logger.info(cancer.toString());
        }








    }
}
