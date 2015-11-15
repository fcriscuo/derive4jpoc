package edu.jhu.fcriscu1.functionaljava;

import autovalue.shaded.com.google.common.common.collect.Lists;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.log4j.Logger;
import rx.Observable;
import rx.functions.Action1;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by fcriscuo on 11/15/15.
 */
public enum MiRNADataService {
    INSTANCE;
   private static final Logger logger = Logger.getLogger(MiRNADataService.class);
    private List<MiRnaData.MiRnaCancer> miRnaCancers = Suppliers.memoize(new MiRnaDataSupplier()).get();

    public List<MiRnaData.MiRnaCancer> getMiRnaCancers() {
        return this.miRnaCancers;
    }

    /*
    private class that supplies a List of miRNA data entries from a text file
     */
    private class MiRnaDataSupplier implements Supplier<List<MiRnaData.MiRnaCancer>> {
        private  final Path rnaFilePath = Paths.get("/tmp/miRCancerMarch2013.txt");
        private List<MiRnaData.MiRnaCancer> miRnaList = Lists.newArrayList();

        MiRnaDataSupplier() {
            this.initList();
        }

        private void initList(){
            try
                    (Reader reader = new FileReader(rnaFilePath.toFile());) {
                final CSVParser parser = new CSVParser(reader, CSVFormat.TDF.withHeader());
                Observable<CSVRecord> recordObservable = Observable.from(parser.getRecords());
                recordObservable.subscribe(new Action1<CSVRecord>() {
                    @Override
                    public void call(CSVRecord record) {
                        miRnaList.add(MiRnaData.MiRnaCancer.create(record.get("mirId"), record.get("Cancer"),
                                record.get("Profile"), record.get("PubMed")));
                    }
                });

            }catch (IOException e ) {
                logger.error(e.getMessage());
            }
        }

        @Override
        public List<MiRnaData.MiRnaCancer> get() {
            return this.miRnaList;
        }
    }

    public static void main (String...args){
        List<MiRnaData.MiRnaCancer> rnaCancers = MiRNADataService.INSTANCE.getMiRnaCancers();
        logger.info("There are " +rnaCancers.size() +" miRNA cancer entries");
        logger.info(rnaCancers.get(0).toString());
    }

}
