package edu.jhu.fcriscu1.functionaljava;

import com.google.common.collect.Lists;
import fj.F;
import fj.F1Functions;
import fj.data.List;
import fj.data.Option;
import fj.data.Stream;
import org.apache.log4j.Logger;

import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static fj.data.List.list;
import static fj.data.Option.fromNull;
import static fj.data.Stream.fromString;

/**
 * Created by fcriscuo on 10/26/15.
 */
public class FileProcessor {

    private static final Logger logger = Logger.getLogger(FileProcessor.class);

    // Main program does the requisite IO gymnastics
    public static void main(String[] args) {
        if (args.length < 1) {
            args = new String[]{"/data/foundation/BATLEVI/heme"};
        }
        Path directoryPath = Paths.get(args[0]);
        logger.info("Processing XML files in " + directoryPath.toString());
        try {
            java.util.List<Path> xmlPathList = Files.list(directoryPath).filter(new Predicate<Path>() {
                @Override
                public boolean test(Path path) {
                    return path.endsWith(".xml");
                }
            }).collect(Collectors.toList());
            Path[] paths = (Path[]) xmlPathList.toArray();


        } catch (Exception e) {

        }
    }


    private static final DirectoryStream.Filter<Path> xmlFileFilter = new DirectoryStream.Filter<Path>() {
        @Override
        public boolean accept(Path entry) throws IOException {
            if (entry.toString().endsWith(".xml")) {
               return true;
            }
            return false;
        }
    };

}
