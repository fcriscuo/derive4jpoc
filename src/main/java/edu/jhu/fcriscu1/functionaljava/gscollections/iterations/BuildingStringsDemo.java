package edu.jhu.fcriscu1.functionaljava.gscollections.iterations;

import com.gs.collections.api.block.predicate.Predicate;
import com.gs.collections.api.list.MutableList;
import com.gs.collections.impl.block.factory.Predicates2;
import com.gs.collections.impl.list.mutable.FastList;
import com.gs.collections.impl.list.mutable.ListAdapter;
import edu.jhu.fcriscu1.functionaljava.MiRNADataService;
import edu.jhu.fcriscu1.functionaljava.MiRnaData;
import org.apache.log4j.Logger;

/**
 * Created by fcriscuo on 11/16/15.
 */
public class BuildingStringsDemo {
    private static final Logger logger = Logger.getLogger(BuildingStringsDemo.class);
    public static void main (String...args){
        // default delimiter is a comma
        // start string & end string are starting and ending characters (e.g. [ & ] )
        FastList<Integer> integers = FastList.newListWith(1,2,3,4,5,6,7,8,9,10,11,12);
        logger.info(integers.makeString());
        logger.info(integers.makeString("[", "-","]"));
        logger.info(integers.makeString("\t"));
        // append a String unsing sppendString
        Appendable app = new StringBuilder("Numbers: ");
        integers.appendString(app,"\t");
        logger.info(app.toString());
        // count elements
        MutableList<MiRnaData.MiRnaCancer> rnaList = ListAdapter.adapt(MiRNADataService.INSTANCE.getMiRnaCancers());
        logger .info("Glioma count = " +rnaList.count( miRnaCancer -> miRnaCancer.cancer().equals("glioma")));
        Predicates2<MiRnaData.MiRnaCancer,String> pred01 =new Predicates2<MiRnaData.MiRnaCancer, String>() {
            @Override
            public boolean accept(MiRnaData.MiRnaCancer miRnaCancer, String s) {
                return miRnaCancer.cancer().contains(s);
            }
        };
        logger.info ("Breast count = " +rnaList.countWith(pred01,"breast"));


    }
}
