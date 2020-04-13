package uph.com.final_project_mpm;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentEditPassword extends Fragment {




    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_editpassword, container, false);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final EditText txtoldpass = view.findViewById(R.id.txtoldpass);
        final EditText txtnewpass = view.findViewById(R.id.txtnewpass);
        Button btnChangePass = view.findViewById(R.id.btnChangePass);
        Button btnBack = view.findViewById(R.id.btnBackFromPass);

        btnChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!txtoldpass.getText().toString().equals("") && !txtnewpass.getText().toString().equals("")){
                    if(((MyApplication)getActivity().getApplication()).getPassword().equals(txtoldpass.getText().toString())) {
                        ((MyApplication) getActivity().getApplication()).setPassword(txtnewpass.getText().toString());
                        FragmentChangeSuccess fragment = new FragmentChangeSuccess();
                        ((EditActivity) getActivity()).changeFragments(fragment);
                    }
                    else
                        Toast.makeText(getActivity().getApplicationContext(), "MASUKKAN PASSWORD YANG BENAR !",Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(getActivity().getApplicationContext(), "ISI SEMUA KOLOM !",Toast.LENGTH_LONG).show();
                }


            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((EditActivity)getActivity()).onBackPressed();
            }
        });
    }
}