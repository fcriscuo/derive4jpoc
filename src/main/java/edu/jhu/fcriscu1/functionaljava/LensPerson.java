package edu.jhu.fcriscu1.functionaljava;

import com.google.common.collect.Lists;
import fj.Equal;
import fj.F;
import fj.data.optic.Lens;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by fcriscuo on 10/21/15.
 */
public class LensPerson {

    private static final Logger logger = Logger.getLogger(LensPerson.class);

    static final class Person {
        String name;
        Address address;

        Person(String name, Address address) {
            this.name = name;
            this.address = address;
        }
    }

    static final class ZipCode {
        String zipCode;

        public ZipCode(String aZip) { this.zipCode = aZip;}
    }
    static final class Address {
        int number;
        String street;
        ZipCode zipCode;

        public Address(int number, String street) {
            this.number = number;
            this.street = street;
            this.zipCode = new ZipCode("NA");
        }

        public Address(int number, String street, ZipCode zc) {
            this.number = number;
            this.street = street;
            this.zipCode = zc;
        }
    }


    static Lens<Person,String> personNameLens = Lens.lens(p -> p.name, s-> p->new Person(s, p.address));
    static Lens<Person,Address> personAddressLens = Lens.lens(p->p.address, a->p-> new Person(p.name,a));
    static Lens<Address,Integer> addressNumberLens = Lens.lens(a -> a.number, n -> a -> new Address(n, a.street));
    static Lens<Address,String> addressStreeLens = Lens.lens(a->a.street, s-> a-> new Address(a.number,s));
    static Lens<Person,Integer> personNumberLens = personAddressLens.composeLens(addressNumberLens);
    static Lens<Person,String> personStreetLens = personAddressLens.composeLens(addressStreeLens);
    static Lens<ZipCode,String> zipcodeCodeLens = Lens.lens(z -> z.zipCode, s ->z -> new ZipCode(s));
    static Lens<Address,ZipCode> addressZipcodeLens = Lens.lens(a ->a.zipCode, s->a->new Address(a.number, a.street,s));
    static Lens<Person,ZipCode> personZipCodeLens = personAddressLens.composeLens(addressZipcodeLens);


    static Equal<Address> addressEqual =  Equal.equal(a1 -> a2 -> a1.number == a2.number
                    && a1.street.equals(a2.street));
    static Equal<Person> personEqual = Equal.equal(p1 -> p2 -> p1.name.equals(p2.name) &&
                addressEqual.eq(p1.address,p2.address));

    static final String oldName = "Joe";
    static final int oldNumber = 10;
    static final String oldStreet = "Main St";
    static final ZipCode oldZipCOde = new ZipCode("07105");
    static final Address oldAddress = new Address(oldNumber, oldStreet);
    static final Person oldPerson = new Person(oldName, oldAddress);
    static final ZipCode newZipCode = new ZipCode("08859");


    public static void main(String...args) {
        Person newPerson = personNameLens.set("Mary").f(oldPerson);
        logger.info("Old name = " + oldPerson.name);
        logger.info("New name = " + newPerson.name);
        Person newPerson2 = personNumberLens.set(52).f(newPerson);
        logger.info("new number = " + newPerson2.address.number);
        Address newAddress = addressZipcodeLens.set(newZipCode).f(newPerson2.address);
        //Person newPerson3 = personAddressLens.set(newAddress).f(newPerson2);
        Person newPerson3 = personZipCodeLens.set(new ZipCode("92116")).f(newPerson2);
        logger.info("new person3 zip code " + newPerson3.address.zipCode.zipCode);

        F<Integer, Integer> timesTwo = new F<Integer, Integer>() {
            @Override
            public Integer f(Integer integer) {
                return integer * 2;
            }
        };
        F<Integer, Integer> plusOne = new F<Integer, Integer>() {
            @Override
            public Integer f(Integer integer) {
                return integer * 1;
            }
        };

        List<Integer> ints = Lists.newArrayList();


    }

}
