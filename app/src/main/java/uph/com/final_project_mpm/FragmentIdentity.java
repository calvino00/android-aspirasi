package uph.com.final_project_mpm;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import uph.com.final_project_mpm.Data.factory.AppDatabase;
import uph.com.final_project_mpm.Model.Aspirasi;

public class FragmentIdentity extends Fragment {

    View v;
    AppDatabase db;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = Room.databaseBuilder(getContext(), AppDatabase.class, "database").build();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        v = inflater.inflate(R.layout.fragment_identity, container, false);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String aspirasi ="";
        String jenis ="";

        Bundle bundle = this.getArguments();
        if (bundle != null){
            aspirasi = bundle.getString("aspirasi");
            jenis = bundle.getString("jenis");
        }

        final String aspirasi_final = aspirasi;
        final String jenis_final = jenis;
        Button btnSubmit = view.findViewById(R.id.btnSubmit_Terakhir);
        Button btnCancel = view.findViewById(R.id.btnCancel);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText nim = view.findViewById(R.id.nim_input);
                EditText nama = view.findViewById(R.id.nama_input);
                EditText kelas = view.findViewById(R.id.kelas_input);
                String number = "^[0-9]*$";

                if(!nim.getText().toString().equals("") && !kelas.getText().toString().equals("") && !nama.getText().toString().equals("")){
                    if(nim.getText().toString().trim().matches(number) && !nama.getText().toString().trim().matches(number)){
                        if(nim.getText().toString().length()==11){
                            if(kelas.getText().toString().trim().length()==5 || kelas.getText().toString().trim().length()==4){
                                Aspirasi aspirasi = new Aspirasi(nim.getText().toString(),nama.getText().toString(),kelas.getText().toString(), aspirasi_final, jenis_final);
                                ((MyApplication)getActivity().getApplication()).getListAspirasi().add(aspirasi);
                                insertData(aspirasi); // add to database
                                FragmentThankyou fragment = new FragmentThankyou();
                                ((AspirationActivity)getActivity()).changeFragments(fragment);
                            }else {
                                Toast.makeText(getActivity().getApplicationContext(), "ISI KELAS YANG BENAR !",Toast.LENGTH_LONG).show();
                            }

                        }else{
                            Toast.makeText(getActivity().getApplicationContext(), " ISI NIM YANG BENAR !",Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(getActivity().getApplicationContext(), "ISI DATA YANG BENAR !",Toast.LENGTH_LONG).show();
                    }

                }else{
                    Toast.makeText(getActivity().getApplicationContext(), "MASUKKAN SEMUA DATA !",Toast.LENGTH_LONG).show();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });


    }

    private void insertData(final Aspirasi aspirasi){

        new AsyncTask<Void, Void, Long>(){
            @Override
            protected Long doInBackground(Void... voids) {
                long status = db.AspirasiDAO().insertAspi(aspirasi);
                return status;
            }

            @Override
            protected void onPostExecute(Long status) {
                Toast.makeText(getContext(), "status row "+status, Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }


    @Override
    public void onResume() {
        super.onResume();
        EditText nim = v.findViewById(R.id.nim_input);
        EditText nama = v.findViewById(R.id.nama_input);
        EditText kelas = v.findViewById(R.id.kelas_input);

        nim.setText("");
        nama.setText("");
        kelas.setText("");
    }
}