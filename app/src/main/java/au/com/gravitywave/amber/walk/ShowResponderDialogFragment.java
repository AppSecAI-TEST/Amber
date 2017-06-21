package au.com.gravitywave.amber.walk;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import au.com.gravitywave.amber.R;

/**
 * Created by georg on 21/06/2017.
 */

public class ShowResponderDialogFragment
        extends DialogFragment
        implements View.OnClickListener {

    Button yesButton, noButton;
    OnShowResponderDialogCloseListener onShowResponderDialogCloseListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_responder_dialog, null);

        yesButton = (Button) view.findViewById(R.id.yes);
        yesButton.setOnClickListener(this);
        noButton = (Button) view.findViewById(R.id.no);
        noButton.setOnClickListener(this);

        setCancelable(false);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        onShowResponderDialogCloseListener = (OnShowResponderDialogCloseListener) context;
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.yes) {
            onShowResponderDialogCloseListener.onShowResponderMessage("thank you requester notified");
            dismiss();
        } else {
            dismiss();
        }
    }

    public interface OnShowResponderDialogCloseListener {
        void onShowResponderMessage(String message);
    }
}
