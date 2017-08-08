package au.com.gravitywave.amber;

import android.app.Application;

import au.com.gravitywave.amber.entities.Person;
import au.com.gravitywave.amber.repositories.JourneyRepository;
import au.com.gravitywave.amber.repositories.OfferRepository;
import au.com.gravitywave.amber.repositories.PersonRepository;

/**
 * Created by georg on 6/08/2017.
 */

public class AmberApplication extends Application {

    public static JourneyRepository journeyRepository;
    public static PersonRepository personRepository;
    public static OfferRepository offerRepository;

    public static Person currentPerson;

    @Override
    public void onCreate() {
        super.onCreate();

        journeyRepository = new JourneyRepository();
        personRepository = new PersonRepository();
        offerRepository = new OfferRepository();

        currentPerson = personRepository.GetById(1);

    }

}
