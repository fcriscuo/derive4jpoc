package edu.jhu.fcriscu1.functionaljava;

import static fj.Show.arrayShow;
import static fj.data.List.list;

import fj.F;
import fj.data.List;
import static fj.Show.intShow;
import static fj.Show.listShow;
import fj.data.Array;
import fj.data.Option;
import fj.function.Effect0;

import static fj.data.Array.array;
import static fj.data.List.fromString;
import static fj.function.Characters.isLowerCase;
import static fj.function.Integers.even;
import static fj.function.Integers.add;
import static fj.Show.optionShow;
import static fj.data.Option.none;
import static fj.data.Option.some;

/**
 * Created by fcriscuo on 10/19/15.
 */
public class ListMapExample {
    public static void main (String...args) {


        // combined into a single line
        listShow(intShow).println(list(1,2,3).map(i -> i+50));
        final Array<String> a = array("Hello", "There", "what", "DAY", "iS", "iT");
        final boolean b =a.exists(s->fromString(s).forall(isLowerCase));
        System.out.println(b);
        //Array filter
        final Array<Integer> a2 = array(97, 44, 67, 3, 22, 90, 1, 77, 98, 1078, 6, 64, 6, 79, 42);
        final Array<Integer>b2 = a2.filter(even);
        final Array<Integer>c2 = a2.filter(i -> i%2 ==0);
        arrayShow(intShow).println(b2);
        arrayShow(intShow).println(c2);
        //Array fold left
        final int b3 = a2.foldLeft(add,0);
        System.out.println(b3);
        // use a function to do the same thing
        F <Integer,F<Integer,Integer> >add2 = i -> (j ->i+j);
        final int c3 = a2.foldLeft(add2,0);
        System.out.println(c3);
        //Option bind - binds a function across the optional value type
        final Option<Integer> o1 = some(7);
        final Option<Integer> o2 = some(8);
        final Option<Integer> o3 = none();
        F<Integer, Option<Integer>> f1 = i -> i % 2 ==0 ? (some(i*3)): none();
        final Option<Integer>o4 = o1.bind(f1);
        final Option<Integer>o5 = o2.bind(f1);
        final Option<Integer>o6 = o3.bind(f1);
        optionShow(intShow).println(o4);
        optionShow(intShow).println(o5);
        optionShow(intShow).println(o6);
        // Option filter - removes the optional value if it does not match a given predicate
        final Option<Integer> o4a = o1.filter(even);
        final Option<Integer> o5a = o2.filter(even);
        final Option<Integer> o6a = o3.filter(even);
        F<Integer,Boolean> f = i -> i % 2 == 0;
        final Option<Integer> o7 = o1.filter(f);
        final Option<Integer> o8 = o2.filter(f);
        final Option<Integer> o9 = o1.filter(i -> i % 2 == 0);

        optionShow(intShow).println(o4a); // None
        optionShow(intShow).println(o5a); // Some(8)
        optionShow(intShow).println(o9); // None)






    }
}
