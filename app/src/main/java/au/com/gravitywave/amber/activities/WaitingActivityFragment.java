package au.com.gravitywave.amber.activities;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import au.com.gravitywave.amber.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class WaitingActivityFragment extends Fragment {

    private static final String ARG_JOURNEY_ID = "journey_id";
    private static final String ARG_PERSON_ID = "person_id";

    private int mPersonId;
    private int mJourneyId;

    public WaitingActivityFragment() {
    }

    public static WaitingActivityFragment newInstance(int journeyId, int personeId) {
        WaitingActivityFragment fragment = new WaitingActivityFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_JOURNEY_ID, journeyId);
        args.putInt(ARG_PERSON_ID, personeId);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mJourneyId = getArguments().getInt(ARG_JOURNEY_ID);
            mPersonId = getArguments().getInt(ARG_PERSON_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_waiting, container, false);
    }
}
