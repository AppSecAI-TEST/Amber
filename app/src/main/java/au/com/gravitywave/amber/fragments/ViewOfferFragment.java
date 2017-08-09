package au.com.gravitywave.amber.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import au.com.gravitywave.amber.AmberApplication;
import au.com.gravitywave.amber.R;
import au.com.gravitywave.amber.entities.Journey;
import au.com.gravitywave.amber.entities.Offer;
import au.com.gravitywave.amber.entities.Person;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ViewOfferFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ViewOfferFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewOfferFragment extends DialogFragment {

    private static final String ARG_OFFER_ID = "offer_id";

    private int mOfferId;
    private Offer mOffer;

    @BindView(R.id.offerer_first_name)
    TextView mOffererFirstNameTextView;

    @BindView(R.id.offerer_when)
    TextView mOffererWhenTextView;

    @BindView(R.id.offerer_message)
    TextView mOffererMessageTextView;

    @BindView(R.id.cancel)
    Button mCancelButton;

    @BindView(R.id.accept_offer)
    Button mAcceptOfferButton;


    private OnFragmentInteractionListener mListener;

    public ViewOfferFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param offerId Parameter 1.
     * @return A new instance of fragment ViewOfferFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ViewOfferFragment newInstance(String offerId) {
        ViewOfferFragment fragment = new ViewOfferFragment();
        Bundle args = new Bundle();
        args.putString(ARG_OFFER_ID, offerId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mOfferId = getArguments().getInt(ARG_OFFER_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_view_offer, container, false);

        ButterKnife.bind(this, v);

        mOffer = AmberApplication.offerRepository.getOfferById(mOfferId);
        Person person = AmberApplication.personRepository.GetById(mOffer.getOfferingPersonId());

        mOffererFirstNameTextView.setText(person.getFirstName());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm");

        mOffererWhenTextView.setText(simpleDateFormat.format(mOffer.getOfferTime().getTime()));

        mOffererMessageTextView.setText(mOffer.getMessage());

        SetCancelButton();

        return v;
    }

    private void SetCancelButton() {
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    private void SetAcceptButton() {
        mAcceptOfferButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 09/08/2017 go to the waiting view
            }
        });
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

    public interface ViewRequestDialogListener {
        void onAcceptOffer(Calendar when, String message, int offerId, int offererId);
    }
}
