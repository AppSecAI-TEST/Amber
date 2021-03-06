package au.com.gravitywave.amber.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.plus.PlusOneButton;

import au.com.gravitywave.amber.R;

/**
 * A fragment with a Google +1 button.
 * Activities that contain this fragment must implement the
 * {@link PersonPickerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PersonPickerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PersonPickerFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PERSON_ID = "person_id";
    private static final String ARG_FIRST_NAME = "firstName";
    private static final String ARG_LAST_NAME = "last_name";
    // The request code must be 0 or greater.
    private static final int PICK_PERSONE_REQUEST_CODE = 0;
    // The URL to +1.  Must be a valid URL.
    private final String PLUS_ONE_URL = "http://developer.android.com";
    // TODO: Rename and change types of parameters
    private String mPersonId;
    private String mFirstName;
    private String mLasttName;

    private PlusOneButton mPlusOneButton;

    private OnFragmentInteractionListener mListener;

    public PersonPickerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param personId  Parameter 1.
     * @param firstName Parameter 2.
     * @return A new instance of fragment PersonPickerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PersonPickerFragment newInstance(String personId, String firstName, String lastName) {
        PersonPickerFragment fragment = new PersonPickerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PERSON_ID, personId);
        args.putString(ARG_FIRST_NAME, firstName);
        args.putString(ARG_LAST_NAME, lastName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPersonId = getArguments().getString(ARG_PERSON_ID);
            mFirstName = getArguments().getString(ARG_FIRST_NAME);
            mLasttName = getArguments().getString(ARG_LAST_NAME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_person_picker, container, false);

        //Find the +1 button
//        mPlusOneButton = (PlusOneButton) view.findViewById(R.offerId.plus_one_button);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        // Refresh the state of the +1 button each time the activity receives focus.
        mPlusOneButton.initialize(PLUS_ONE_URL, PICK_PERSONE_REQUEST_CODE);
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
