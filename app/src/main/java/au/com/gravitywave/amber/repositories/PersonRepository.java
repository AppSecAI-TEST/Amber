package au.com.gravitywave.amber.repositories;

import java.util.ArrayList;
import java.util.List;

import au.com.gravitywave.amber.entities.Person;


/**
 * Created by BollasG on 12/07/2017.
 */

public class PersonRepository extends RepositoryBase {

    private static final List<Person> mPeople;
    private static int nextPersonId = 1;

    static {
        mPeople = new ArrayList<Person>() {{
            add(new Person(nextPersonId++, "John", "Smith", "M", 25, 4, "john.smith@gmail.com", "password", "ChIJm9GTcxq5EmsR4aLUocq1I5k", "A"));
            add(new Person(nextPersonId++, "Jane", "Jones", "F", 22, 4, "jane.jones@gmail.com", "password", "ChIJm9GTcxq5EmsR4aLUocq1I5k", "A"));
            add(new Person(nextPersonId++, "Imma", "Candle", "F", 19, 5, "imma.candle@gmail.com", "password", "ChIJm9GTcxq5EmsR4aLUocq1I5k", "A"));
            add(new Person(nextPersonId++, "Sarah", "Connor", "F", 54, 3, "sarah.connor@gmail.com", "password", "ChIJm9GTcxq5EmsR4aLUocq1I5k", "A"));
            add(new Person(nextPersonId++, "Mary", "Sward", "F", 34, 2, "mary.sward@gmail.com", "password", "ChIJm9GTcxq5EmsR4aLUocq1I5k", "A"));
            add(new Person(nextPersonId++, "Sue", "Talent", "F", 46, 4, "sue.talent@gmail.com", "password", "ChIJm9GTcxq5EmsR4aLUocq1I5k", "A"));
            add(new Person(nextPersonId++, "Sandra", "Dee", "F", 18, 4, "sandra.dee@gmail.com", "password", "ChIJm9GTcxq5EmsR4aLUocq1I5k", "A"));
        }};

    }

    public PersonRepository() {


    }

    public Person GetById(int personId) {

        for (Person p : mPeople
                ) {
            if (p.getPersonId() == personId)
                return p;
        }
        return null;
    }

    public Person GetByEmail(String email) {
        for (Person p : mPeople
                ) {
            if (p.getEmailAddress() == email)
                return p;
        }
        return null;
    }

    public List<Person> GetAll() {
        return mPeople;
    }
}
