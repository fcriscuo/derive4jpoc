package edu.jhu.fcriscu1.functionaljava.gscollections.iterations;

import com.gs.collections.api.LazyIterable;
import com.gs.collections.api.RichIterable;
import com.gs.collections.api.block.function.Function;
import com.gs.collections.api.block.predicate.Predicate;
import com.gs.collections.api.block.procedure.Procedure;
import com.gs.collections.api.list.MutableList;
import com.gs.collections.api.multimap.list.MutableListMultimap;
import com.gs.collections.impl.block.factory.Procedures;
import com.gs.collections.impl.list.mutable.ListAdapter;
import edu.jhu.fcriscu1.functionaljava.MiRNADataService;
import edu.jhu.fcriscu1.functionaljava.MiRnaData;
import org.apache.log4j.Logger;

/**
 * Created by fcriscuo on 11/16/15.
 */
public class LazyIterationDemo {
    private static final Logger logger = Logger.getLogger(LazyIterationDemo.class);
    public static void main (String...args){
        MutableList<MiRnaData.MiRnaCancer> rnaList = ListAdapter.adapt(MiRNADataService.INSTANCE.getMiRnaCancers());
        LazyIterable<MiRnaData.MiRnaCancer> lazyRna = rnaList.asLazy();
        LazyIterable<String> lazyPubMed = lazyRna.collect(MiRnaData.MiRnaCancer::pubMed);
        for (String s : lazyPubMed){
            logger.info(s);
        }
        MutableListMultimap<String,MiRnaData.MiRnaCancer> rnaMulitmap = rnaList.groupBy(new Function<MiRnaData.MiRnaCancer, String>() {
            @Override
            public String valueOf(MiRnaData.MiRnaCancer miRnaCancer) {
                return miRnaCancer.cancer();
            }
        });
        rnaMulitmap.multiValuesView()
                .select(new Predicate<RichIterable<MiRnaData.MiRnaCancer>>() {
                    @Override
                    public boolean accept(RichIterable<MiRnaData.MiRnaCancer> miRnaCancers) {
                        return miRnaCancers.anySatisfy(new Predicate<MiRnaData.MiRnaCancer>() {
                            @Override
                            public boolean accept(MiRnaData.MiRnaCancer miRnaCancer) {
                                return miRnaCancer.cancer().equals("glioma");
                            }
                        });
                    }
                }).asLazy()
                .forEach(new Procedure<RichIterable<MiRnaData.MiRnaCancer>>() {
                    @Override
                    public void value(RichIterable<MiRnaData.MiRnaCancer> miRnaCancers) {
                        miRnaCancers.forEach(new Procedure<MiRnaData.MiRnaCancer>() {
                            @Override
                            public void value(MiRnaData.MiRnaCancer miRnaCancer) {
                                logger.info(miRnaCancer.mirId());
                            }
                        });
                    }
                });






    }

}
