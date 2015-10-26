package edu.jhu.fcriscu1.functionaljava;

import fj.F;
import fj.Try;
import fj.data.Validation;
import fj.function.Longs;
import fj.function.Strings;
import fj.function.Try0;
import fj.function.Try1;
import org.apache.commons.lang3.StringUtils;
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

        Validation<Exception,Integer> v2 = validParseString("A");
        if(v2.isFail()){
            logger.error(v2.fail().getMessage());
        } else {
            logger.info("Inetger value= " + v2.success());
        }
        Validation<Exception,Integer> v3 = validParseString("123");
        if(v3.isFail()){
            logger.error(v3.fail().getMessage());
        } else {
            logger.info("Inetger value= " + v3.success());
        }
        String testName1 = "Fred";
        Validation<Exception,Boolean>  v4 = isValidName(testName1);
        if(v4.isFail()){
            logger.error(v4.fail().getMessage());
        } else {
            logger.info(testName1 +" is valid name: "+v4.success());
        }
        String testName2 = "";
        Validation<Exception,Boolean>  v5 = isValidName(testName2);
        if(v5.isFail()){
            logger.error(v5.fail().getMessage());
        } else {
            logger.info(testName2 +" is valid name: "+v5.success());
        }

        logger.info( Longs.multiply.f(100L).f(200L));

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

    static Validation<Exception,Integer>  validParseString(String anInt)
    {
       if(!StringUtils.isNumeric(anInt)){
           return Validation.fail( new NumberFormatException("Number format")) ;
       }
        if (StringUtils.isEmpty(anInt)){
            return Validation.fail( new IllegalArgumentException("Null or empty string")) ;
       }
           return Validation.success(Integer.parseInt(anInt));

    }

   static Validation<Exception,Boolean> isValidName(String aName) {

       Try1<String, Boolean,Exception> t =  n -> {
           if (Strings.isNullOrBlank.f(n)) {
               throw new IllegalArgumentException("A name is required");
           } else if (Strings.contains.f("red").f(n)) {
               return true;
           } else {
               return false;
           }
       };
        return Try.f(t).f(aName);

   }


}
