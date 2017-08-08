package au.com.gravitywave.amber.registration;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import au.com.gravitywave.amber.R;
import au.com.gravitywave.amber.activities.VerifyActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity {

    int PLACE_PICKER_REQUEST = 1;

    DatePickerDialog.OnDateSetListener mDateSetListener;

    @BindView(R.id.address)
    TextView mAddressTextView;

//    @BindView(R.id.edit_address)
//    ImageButton mEditAddressImageButton;

    @BindView(R.id.first_name)
    EditText mFirstNameTextView;

    @BindView(R.id.last_name)
    EditText mLastNameTextView;

    @BindView(R.id.birthdate)
    TextView mBirthDateTextView;

    @BindView(R.id.gender)
    Spinner mGenderSpinner;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.register)
    Button mRegisterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        final Context context = this;

        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, VerifyActivity.class);
                startActivity(intent);
            }
        });

        mAddressTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectAddress();
            }
        });

        mBirthDateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int y = cal.get(Calendar.YEAR);
                int m = cal.get(Calendar.MONTH);
                int d = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(context, mDateSetListener,
                        y, m, d);
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                month++;

                Calendar c = Calendar.getInstance();
                c.set(year, month, dayOfMonth);

                SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
                mBirthDateTextView.setText(sdf.format(c.getTime())); //// TODO: 2/06/2017 fix date
            }
        };


    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(this, data);
                mAddressTextView.setText(place.getAddress());
            }
        }
    }

    private void selectAddress() {
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
        Intent intent = null;
        try {
            intent = builder.build(this);
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
        startActivityForResult(intent, PLACE_PICKER_REQUEST);
    }
}
