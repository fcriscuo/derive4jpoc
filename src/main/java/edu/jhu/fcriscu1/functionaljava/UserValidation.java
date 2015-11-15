package edu.jhu.fcriscu1.functionaljava;

import fj.Semigroup;
import fj.data.Option;
import fj.data.Validation;
import org.apache.log4j.Logger;

/**
 * Created by fcriscuo on 10/31/15.
 */
public class UserValidation {
    private static final Logger logger = Logger.getLogger(UserValidation.class);

    public UserValidation() {}

    public Validation<String,String> ageOk(User u){
        if(u.age>18)
            return Validation.success("\tage OK");
        return Validation.fail("\tUser too young");
    }

    public Validation<String,String> emailOk(User u){
        if(u.email!=null && u.email.contains("@"))
            return Validation.success("\temail OK");
        return Validation.fail("\temail is invalid");
    }

    void performTests() {
        User user = new User(44,"");
        Option<String> v = ageOk(user).accumulate(
                Semigroup.stringSemigroup,  emailOk(user));
        logger.info(v.toString());
        Validation<String,String> v1 = ageOk(user);
        String message = (v1.isFail()) ?v1.fail():v1.success();
        logger.info("age message: " +message);

    }

    public class User{
        int age;
        String email;

        public User(int age, String email) {
            super();
            this.age = age;
            this.email = email;
        }
    }
    public static void main (String...args){
        UserValidation uv = new UserValidation();
        uv.performTests();


    }

}
