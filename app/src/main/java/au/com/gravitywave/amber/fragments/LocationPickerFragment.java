package au.com.gravitywave.amber.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import au.com.gravitywave.amber.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LocationPickerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LocationPickerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LocationPickerFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_INITIAL_LOCATION = "initial_location";
    private static final String ARG_LOCATION_PROMPT = "location_prompt";

    private String mInitialLocation;
    private String mLocationPrompt;
    private EditText mLocationText;

    private OnFragmentInteractionListener mListener;

    public LocationPickerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param initialLocation Parameter 1.
     * @param locationPrompt Parameter 2.
     * @return A new instance of fragment LocationPickerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LocationPickerFragment newInstance(String initialLocation, String locationPrompt) {
        LocationPickerFragment fragment = new LocationPickerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_INITIAL_LOCATION, initialLocation);
        args.putString(ARG_LOCATION_PROMPT, locationPrompt);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mInitialLocation = getArguments().getString(ARG_INITIAL_LOCATION);
            mLocationPrompt = getArguments().getString(ARG_LOCATION_PROMPT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_location_picker, container, false);

        mLocationText = (EditText) v.findViewById(R.id.location_text);

        mLocationText.setHint(mLocationPrompt);

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
}
