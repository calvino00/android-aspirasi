package uph.com.final_project_mpm;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentDetails extends Fragment {

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
        View v = inflater.inflate(R.layout.fragment_details, container, false);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String aspirasi ="";
        String jenis ="";
        String nim ="";
        String nama ="";
        String kelas ="";

        Bundle bundle = this.getArguments();
        if (bundle != null){
            nim = bundle.getString("nim");
            nama = bundle.getString("nama");
            kelas = bundle.getString("kelas");
            aspirasi = bundle.getString("aspirasi");
            jenis = bundle.getString("jenis");
        }

        TextView _nim = view.findViewById(R.id.txtNIM);
        _nim.setText(nim);
        TextView _nama = view.findViewById(R.id.txtNama);
        _nama.setText(nama);
        TextView _kelas = view.findViewById(R.id.txtKelas);
        _kelas.setText(kelas);
//        TextView _kelas= view.findViewById(R.id.txtKelas);
//        _kelas.setText(kelas);
        TextView _jenis = view.findViewById(R.id.txtJenis);
        _jenis.setText(jenis);
        TextView _aspi = view.findViewById(R.id.txtAspi);
        _aspi.setText(aspirasi);


        Button btnBack = view.findViewById(R.id.btnBackFromDetails);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });





    }
}