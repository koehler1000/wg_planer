package de.ameyering.wgplaner.wgplaner.section.registration.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import de.ameyering.wgplaner.wgplaner.R;

public class PickDisplayNameFragment extends Fragment {
    private Button btnContinue;
    private EditText inputName;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
        @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pick_display_name_registration, container, false);

        btnContinue = view.findViewById(R.id.btn_continue_pick_display_name);
        inputName = view.findViewById(R.id.input_username);

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String displayName = inputName.getText().toString();

                if (displayName != null && !displayName.equals("")) {

                } else {
                    Toast.makeText(getContext(), PickDisplayNameFragment.this.getActivity().getString(
                            R.string.warning_input_username_empty), Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}
