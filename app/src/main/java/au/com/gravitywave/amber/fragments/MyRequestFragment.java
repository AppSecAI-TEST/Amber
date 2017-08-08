package au.com.gravitywave.amber.fragments;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import au.com.gravitywave.amber.R;
import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MyRequestFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MyRequestFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyRequestFragment
        extends Fragment
        implements TimePickerDialog.OnTimeSetListener {

    @BindView(R.id.from_location)
    EditText mFromLocationEditText;

    @BindView(R.id.to_location)
    EditText mToLocationEditText;

    @BindView(R.id.when)
    EditText mWhenEditView;

    @BindView(R.id.send_request)
    Button mSendRequest;


    int FROM_PLACE_PICKER_REQUEST = 1;
    int TO_PLACE_PICKER_REQUEST = 2;


    private OnFragmentInteractionListener mListener;

    public MyRequestFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MyRequestFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyRequestFragment newInstance() {
        MyRequestFragment fragment = new MyRequestFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_request, container, false);

        ButterKnife.bind(this, view);

        handleFromClick(view);
        handleToClick(view);
        handleWhenClick(view);
        handleSendRequestClick(view);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");

        mWhenEditView.setText(simpleDateFormat.format(Calendar.getInstance().getTime()));

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    private void handleFromClick(View view) {
        mFromLocationEditText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

                Intent intent = null;
                try {
                    intent = builder.build(getActivity());
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
                startActivityForResult(intent, FROM_PLACE_PICKER_REQUEST);

            }
        });
    }

    private void handleToClick(View view) {
        mToLocationEditText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                Intent intent = null;
                try {
                    intent = builder.build(getActivity());
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
                startActivityForResult(intent, TO_PLACE_PICKER_REQUEST);

            }
        });
    }

    private void handleWhenClick(final View view) {
        mWhenEditView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showTimePickerDialog(view);
            }
        });
    }

    private void handleSendRequestClick(final View view) {
        mSendRequest.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, newInstance())
                        .commit();
            }
        });
    }

    public void showTimePickerDialog(View v) {

        //// TODO: 8/08/2017 get the time from creation
        Calendar currentTime = Calendar.getInstance();
        int hour = currentTime.get(Calendar.HOUR_OF_DAY);
        int minute = currentTime.get(Calendar.MINUTE);

        TimePickerDialog mTimePicker;

        mTimePicker = new TimePickerDialog(
                getActivity(),
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        mWhenEditView.setText("" + selectedHour + ":" + selectedMinute);
                    }
                },
                hour,
                minute,
                false);

        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == FROM_PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(getContext(), data);
                String address = String.format("from: %s, ", place.getName());

                mFromLocationEditText.setText(address);
            }
        }
        if (requestCode == TO_PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(getContext(), data);
                String address = String.format("to: %s, ", place.getName());

                mToLocationEditText.setText(address);
            }
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

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        mWhenEditView.setText(hourOfDay + ":" + minute); //todo format correctly
        // TODO: 8/08/2017 validate is greater than now
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
