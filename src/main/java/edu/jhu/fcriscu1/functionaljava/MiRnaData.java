package edu.jhu.fcriscu1.functionaljava;

import com.google.auto.value.AutoValue;

/**
 * Created by fcriscuo on 11/15/15.
 */
public class MiRnaData {
    @AutoValue
    public abstract static class MiRnaCancer {
        MiRnaCancer() {
        }

        public static MiRnaCancer create(String mirId, String cancer, String profile, String pubMed) {
            return new AutoValue_MiRnaData_MiRnaCancer(mirId, cancer, profile, pubMed);
        }

        public abstract String mirId();

        public abstract String cancer();

        public abstract String profile();

        public abstract String pubMed();
        @Override
        public String toString() {
            return new StringBuilder("MiRnaCancer{")
                    .append("mirId= " +mirId())
                    .append(", ")
                    .append("Cancer= " +cancer())
                    .append(", ")
                    .append("Profile= " +profile())
                    .append(", ")
                    .append("PubMed= " +pubMed())
                    .append("}").toString();
        }

    }

    public static void main (String...args){
        MiRnaCancer miRna1 = MiRnaCancer.create("mir001","melanoma","down","PMID12345");
        System.out.println(miRna1.toString());
        System.out.println(miRna1.pubMed());
    }

}
