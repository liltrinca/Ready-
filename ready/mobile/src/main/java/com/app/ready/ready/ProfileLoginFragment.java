package com.app.ready.ready;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class ProfileLoginFragment extends Fragment {
    private Button btnLogin;
    private EditText tbLogin, tbPass;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profilelogin, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnLogin = view.findViewById(R.id.btnLogin);
        tbLogin = view.findViewById(R.id.tbLogin);
        tbPass = view.findViewById(R.id.tbPass);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getusername = tbLogin.getText().toString();
                String getpass = tbPass.getText().toString();
                String type = "login";


                BackgroundWorker bWorker = new BackgroundWorker(getContext());
                bWorker.execute(type, getusername, getpass);

                //ProfileFragment profileFragment = new ProfileFragment();
                //FragmentTransaction changeProfile = getFragmentManager().beginTransaction();
                //changeProfile.replace(R.id.frame, profileFragment);
                //changeProfile.commit();
            }
        });
    }
}