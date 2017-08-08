package au.com.gravitywave.amber.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import au.com.gravitywave.amber.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class VerifyActivity extends AppCompatActivity {

    @BindView(R.id.verification_code)
    EditText mVerificationCodeTextView;

    @BindView(R.id.verify)
    Button mVerifyButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);

        ButterKnife.bind(this);

        mVerifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(VerifyActivity.this, "Verification Successful", Toast.LENGTH_LONG).show();

                //todo login page now
                Intent intent = new Intent(VerifyActivity.this, UserNameLoginActivity.class);
                startActivity(intent);
            }
        });
    }


}
