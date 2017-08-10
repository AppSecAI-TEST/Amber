package au.com.gravitywave.amber.activities;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import au.com.gravitywave.amber.AmberApplication;
import au.com.gravitywave.amber.R;
import au.com.gravitywave.amber.entities.Journey;
import au.com.gravitywave.amber.fragments.MonitorFragment;

public class WaitingActivity extends AppCompatActivity {

    private static final String ARG_JOURNEY_ID = "journey_id";
    private static final String ARG_PERSON_ID = "persone_id";

    Journey mJourney;
    TextView mFromTextView;
    TextView mToTextView;
    TextView mWhenTextView;

    private int mJourneyId;
    private int mPersonId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();


        mJourneyId= extras.getInt(ARG_JOURNEY_ID);
        mPersonId= extras.getInt(ARG_PERSON_ID);

        mJourney = AmberApplication.journeyRepository.getJourneyById(mJourneyId);

        mFromTextView.setText(mJourney.getStartPlaceId()); // TODO: 10/08/2017 get place name
        mToTextView.setText(mJourney.getDestinationPlaceId()); // TODO: 10/08/2017 get place name

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        mWhenTextView.setText(simpleDateFormat.format(mJourney.getStartTime()));

        Fragment fragment = WaitingActivityFragment.newInstance(mJourneyId, mPersonId);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment)
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

}
