package uph.com.final_project_mpm;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentAspirasi extends Fragment {




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
        View v = inflater.inflate(R.layout.fragment_aspirasi, container, false);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Spinner spinner = (Spinner) view.findViewById(R.id.dropdown);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity().getApplicationContext(),
                R.array.jenis_aspirasi, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);


        Button btnSubmit = view.findViewById(R.id.btnSubmit);
        TextView login = view.findViewById(R.id.login);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                FragmentIdentity fragment = new FragmentIdentity();
                Bundle bundle = new Bundle();

                Spinner spinner = (Spinner)view.findViewById(R.id.dropdown);
                String jenis = spinner.getSelectedItem().toString();

                EditText aspirasi_input = view.findViewById(R.id.aspi_input);
                String aspirasi = aspirasi_input.getText().toString();

                if(!aspirasi.equals("")){



                    bundle.putString("aspirasi", aspirasi);
                    bundle.putString("jenis", jenis);
                    fragment.setArguments(bundle);
                    ((AspirationActivity)getActivity()).changeFragments(fragment);
                }else{
                    Toast.makeText(getActivity().getApplicationContext(), "Masukkan aspirasi !",Toast.LENGTH_LONG).show();
                }

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AspirationActivity)getActivity()).showLoginPage(v);
            }
        });
    }
}