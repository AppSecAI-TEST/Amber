package au.com.gravitywave.amber.activities;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Places;

import java.util.Calendar;

import au.com.gravitywave.amber.AmberApplication;
import au.com.gravitywave.amber.R;
import au.com.gravitywave.amber.entities.Journey;
import au.com.gravitywave.amber.entities.Offer;
import au.com.gravitywave.amber.fragments.MonitorFragment;
import au.com.gravitywave.amber.fragments.MyRequestFragment;
import au.com.gravitywave.amber.fragments.OffersFragment;
import au.com.gravitywave.amber.fragments.RequestsFragment;
import au.com.gravitywave.amber.fragments.ViewRequestFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ShellActivity extends AppCompatActivity
        implements RequestsFragment.OnListFragmentInteractionListener,
        ViewRequestFragment.OnFragmentInteractionListener,
        ViewRequestFragment.ViewRequestDialogListener,
        MonitorFragment.OnFragmentInteractionListener,
        MyRequestFragment.OnFragmentInteractionListener

{

    public static final String ARG_REQUEST_ID = "request_id";

    @BindView(R.id.message)
    public TextView mTextMessage;


    @BindView(R.id.navigation)
    BottomNavigationView navigation;


    GoogleApiClient mGoogleApiClient = null;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment fragment = null;


            switch (item.getItemId()) {
                case R.id.navigation_requests_map:
                    mTextMessage.setText(R.string.title_requests_map);
                    fragment = MonitorFragment.newInstance();
                    break;
                case R.id.navigation_request_list:
                    fragment = RequestsFragment.newInstance();
                    break;
                case R.id.navigation_my_offers:
                    mTextMessage.setText(R.string.title_my_offers);
                    fragment = OffersFragment.newInstance(AmberApplication.currentPerson.getPersonId());
                    break;
                case R.id.navigation_my_request:
                    fragment = MyRequestFragment.newInstance();
                    break;
            }

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();

            return true;
        }

    };

    public GoogleApiClient getGoogleApiClient() {

        if (mGoogleApiClient == null)
            mGoogleApiClient = new GoogleApiClient
                    .Builder(this)
                    .addApi(Places.GEO_DATA_API)
                    .addApi(Places.PLACE_DETECTION_API)
                    .build();

        return mGoogleApiClient;
    }

    @Override
    protected void onStart() {
        super.onStart();
        getGoogleApiClient();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mGoogleApiClient.disconnect();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shell);

        ButterKnife.bind(this);

        Fragment fragment = RequestsFragment.newInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment)
                .commit();

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fragment = MonitorFragment.newInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment)
                .commit();


    }

    @Override
    public void onListFragmentInteraction(Journey journey) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        ViewRequestFragment fragment = new ViewRequestFragment();

        Bundle args = new Bundle();
        args.putInt(ARG_REQUEST_ID, journey.getJourneyId());
        fragment.setArguments(args);

        fragment.show(fragmentManager, "View Request");


    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onMakeOffer(Calendar when, String message, int journeyId) {

        Journey j = AmberApplication.journeyRepository.getJourneyById(journeyId);

        Offer o = new Offer();

        o.setJourneyId(j.getJourneyId());
        o.setMessage(message);
        o.setOfferTime(when);

        o.setOfferingPersonId(AmberApplication.currentPerson.getPersonId());

        int id = AmberApplication.offerRepository.Insert(o);

        o.setOfferId(id);

        Fragment fragment = RequestsFragment.newInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment)
                .commit();

    }
}
