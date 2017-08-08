package au.com.gravitywave.amber.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import au.com.gravitywave.amber.AmberApplication;
import au.com.gravitywave.amber.R;
import au.com.gravitywave.amber.entities.Journey;
import au.com.gravitywave.amber.entities.Person;
import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ViewRequestFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ViewRequestFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewRequestFragment
        extends DialogFragment
        implements GoogleApiClient.OnConnectionFailedListener {

    private static final String ARG_REQUEST_ID = "request_id";
    GoogleApiClient mGoogleApiClient;
    @BindView(R.id.first_name)
    TextView mFirstNameTextView;
    @BindView(R.id.from)
    TextView mFromTextView;
    @BindView(R.id.distance)
    TextView mDistanceTextView;
    @BindView(R.id.when)
    TextView mWhenTextView;
    @BindView(R.id.message)
    TextView mMessageTextView;
    @BindView(R.id.cancel)
    Button mCancelButton;
    @BindView(R.id.make_offer)
    Button mMakeOfferButton;
    @BindView(R.id.propose_time)
    EditText mProposedTimeTextView;
    @BindView(R.id.message_to_requester)
    TextView mMessageToRequesterTextView;
    private int mRequestId;
    private OnFragmentInteractionListener mListener;
    private ViewRequestDialogListener mViewRequestDialogListener;


    public ViewRequestFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param requestId Parameter 1.
     * @return A new instance of fragment ViewRequestFragment.
     */
    public static ViewRequestFragment newInstance(int requestId) {
        ViewRequestFragment fragment = new ViewRequestFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_REQUEST_ID, requestId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mRequestId = getArguments().getInt(ARG_REQUEST_ID);
        }


        mGoogleApiClient = new GoogleApiClient
                .Builder(getActivity())
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(getActivity(), this)
                .build();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_view_request, container, false);

        ButterKnife.bind(this, v);
        //mCancelButton

        Journey j = AmberApplication.journeyRepository.getJourneyById(mRequestId);
        Person r = AmberApplication.personRepository.GetById(j.getRequestorId());

        mFirstNameTextView.setText(r.getFirstName());
        mDistanceTextView.setText("100km");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm");

        mWhenTextView.setText(simpleDateFormat.format(j.getStartTime().getTime()));
        mProposedTimeTextView.setText(simpleDateFormat.format(j.getStartTime().getTime()));

        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });


        mMakeOfferButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar cal = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
                try {
                    cal.setTime(sdf.parse(mProposedTimeTextView.getText().toString()));// all done
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                mViewRequestDialogListener.onMakeOffer(
                        cal
                        , mMessageToRequesterTextView.getText().toString()
                        , mRequestId);

                dismiss();
            }
        });


        Places.GeoDataApi.getPlaceById(mGoogleApiClient, j.getStartPlaceId())
                .setResultCallback(new ResultCallback<PlaceBuffer>() {
                    @Override
                    public void onResult(PlaceBuffer places) {
                        if (places.getStatus().isSuccess() && places.getCount() > 0) {
                            final Place place = places.get(0);
                            Log.i(TAG, "Place found: " + place.getName());
                            mFromTextView.setText(place.getName());
                        } else {
                            Log.e(TAG, "Place not found");
                        }
                        places.release();
                    }
                });


        //todo make this a calculate distance
//        Places.GeoDataApi.getPlaceById(mGoogleApiClient, j.getDestinationPlaceId())
//                .setResultCallback(new ResultCallback<PlaceBuffer>() {
//                    @Override
//                    public void onResult(PlaceBuffer places) {
//                        if (places.getStatus().isSuccess() && places.getCount() > 0) {
//                            final Place place = places.get(0);
//                            Log.i(TAG, "Place found: " + place.getName());
//                            mToTextView.setText(place.getName());
//                        } else {
//                            Log.e(TAG, "Place not found");
//                        }
//                        places.release();
//                    }
//                });

        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
            mViewRequestDialogListener = (ViewRequestDialogListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public interface ViewRequestDialogListener {
        void onMakeOffer(Calendar when, String message, int journeyId);
    }
}
