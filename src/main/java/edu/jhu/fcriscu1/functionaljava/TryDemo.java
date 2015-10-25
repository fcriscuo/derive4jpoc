package edu.jhu.fcriscu1.functionaljava;

import fj.Try;
import fj.data.Validation;
import fj.function.Try1;
import org.apache.log4j.Logger;

import java.util.Random;

/**
 * Created by fcriscuo on 10/25/15.
 */
public class TryDemo {
    private static final Logger logger = Logger.getLogger(TryDemo.class);
    public static void main (String...args) {
        Validation<Exception,Integer> v1 = getExternalValue();
        if(v1.isFail()){
            logger.error(v1.fail().getMessage());
        }else {
            logger.info("Success: " +v1.success());
        }
    }
    static Validation<Exception,Integer> getExternalValue() {
        Try1<Random, Integer,Exception> t = r -> {
            int i = r.nextInt();
            if (i == 0) {
                throw new NullPointerException();

            } else if (i == 2) {
                throw new ArithmeticException();
            } else {
                return i;
            }
        };
        return Try.f(t).f(new Random());
    }

}
