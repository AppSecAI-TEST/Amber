package au.com.gravitywave.amber.activities;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import au.com.gravitywave.amber.AmberApplication;
import au.com.gravitywave.amber.R;
import au.com.gravitywave.amber.entities.Journey;
import au.com.gravitywave.amber.entities.Message;
import au.com.gravitywave.amber.entities.Offer;
import au.com.gravitywave.amber.entities.Person;
import au.com.gravitywave.amber.fragments.MessagesFragment;
import butterknife.BindView;

public class WaitingActivity
        extends AppCompatActivity
        implements MessagesFragment.OnListFragmentInteractionListener {

    private static final String ARG_JOURNEY_ID = "journey_id";
    private static final String ARG_PERSON_ID = "person_id";

    Journey mJourney;


    @BindView(R.id.offerer_first_name)
    TextView mFromTextView;

    @BindView(R.id.offerer_when)
    TextView mWhenTextView;



    private int mJourneyId;
    private int mPersonId;
    private int mOfferId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();


//        mJourneyId= extras.getInt(ARG_JOURNEY_ID);
//        mPersonId= extras.getInt(ARG_PERSON_ID);

        mJourneyId = 1;
        mPersonId = 1;
        mOfferId = 1;

        mJourney = AmberApplication.journeyRepository.getJourneyById(mJourneyId);
        Person offerer = AmberApplication.personRepository.GetById(mPersonId);
        Offer acceptedOffer = AmberApplication.offerRepository.getOfferById(mOfferId);

        mFromTextView.setText(offerer.getFirstName());
//
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        mWhenTextView.setText(simpleDateFormat.format(acceptedOffer.getOfferTime()));

        Fragment fragment = MessagesFragment.newInstance(mJourneyId, mPersonId);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content, fragment)
                .commit();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void onListFragmentInteraction(Message item) {

    }
}
