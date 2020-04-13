package uph.com.final_project_mpm;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.room.Database;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import uph.com.final_project_mpm.Data.factory.AppDatabase;
import uph.com.final_project_mpm.Model.Aspirasi;

public class FragmentList extends Fragment {

    private ArrayList<Aspirasi> listAspirasi;
    private ListView listView;
    private AspirationListAdapter adapter;
    private Spinner spinner;
    private ArrayList<Aspirasi> listDisplay;
    private AppDatabase db;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = Room.databaseBuilder(getContext(), AppDatabase.class, "database").allowMainThreadQueries().build();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_list, container, false);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        spinner = view.findViewById(R.id.dropdownSort);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterSpinner = ArrayAdapter.createFromResource(getActivity().getApplicationContext(),
                R.array.sorting, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapterSpinner);

        listView = view.findViewById(R.id.aspiration_list);
        listAspirasi = new ArrayList<>(Arrays.asList(db.AspirasiDAO().SelectAllAspirasi())); // ambil data dari database



//        listAspirasi.add(new AspiModel("03082170008", "Lontong","17H3", "Lontong", "Lontong"));
//        listAspirasi.add(new AspiModel("03082170009", "Kambeng","17TI1", "Kambeng", "Kambeng"));
//        listAspirasi.add(new AspiModel("03082170010", "Super","17SI1", "Super", "Super"));
//        listAspirasi.add(new AspiModel("03082170011", "Kem","17L1", "nfoipajfepiajw", "Hahahaa"));

        final Runnable afterDelete = new Runnable() {
            @Override
            public void run() {
                listAspirasi = new ArrayList<>(Arrays.asList(db.AspirasiDAO().SelectAllAspirasi())); // ambil data dari database
                adapter.setDataSet(listAspirasi); // update dataset
                adapter.notifyDataSetChanged(); // redraw list
            }
        } ;

        adapter = new AspirationListAdapter(listAspirasi, getActivity().getApplicationContext(), db, afterDelete); // ondelete datang dari ListAdapter
        listView.setAdapter(adapter);
        listDisplay = new ArrayList<>(listAspirasi);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Aspirasi temp = listDisplay.get(position);

                FragmentDetails fragment = new FragmentDetails();
                Bundle bundle = new Bundle();

                bundle.putString("nim", temp.getNim());
                bundle.putString("nama", temp.getNama());
                bundle.putString("kelas", temp.getKelas());
                bundle.putString("jenis", temp.getJenisAspirasi());
                bundle.putString("aspirasi", temp.getIsiAspirasi());
                fragment.setArguments(bundle);

                ((AspirationListActivity)getActivity()).changeFragments(fragment);

            }
        });


        //EditProfile
        TextView btnLogout = view.findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AspirationListActivity)getActivity()).logout(v);
            }
        });

        TextView btnEditProfile = view.findViewById(R.id.editProfile);
        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity().getApplicationContext(),EditActivity.class);
                startActivity(i);
            }
        });


        //Search
        final TextView txtSearch = view.findViewById(R.id.txtSearch);

        txtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String search = txtSearch.getText().toString();
                listDisplay.clear();
                for (int i = 0; i < listAspirasi.size(); i++){
                    if(listAspirasi.get(i).getNama().toLowerCase().contains(search.toLowerCase()) || listAspirasi.get(i).getNim().toLowerCase().contains(search.toLowerCase()) || listAspirasi.get(i).getKelas().toLowerCase().contains(search.toLowerCase())|| listAspirasi.get(i).getJenisAspirasi().toLowerCase().contains(search.toLowerCase()))
                    {
                        listDisplay.add(listAspirasi.get(i));
                    }
                }
                adapter = new AspirationListAdapter(listDisplay, getActivity().getApplicationContext(), db, afterDelete);
                listView.setAdapter(adapter);
            }
        });


        //Sort
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = spinner.getSelectedItem().toString();
                if (selected.equals("NIM")){
                    Collections.sort(listDisplay, new Comparator<Aspirasi>() {
                        @Override
                        public int compare(Aspirasi o1, Aspirasi o2) {
                            return o1.getNim().compareTo(o2.getNim());
                        }
                    });

                }else if (selected.equals("Nama")){
                    Collections.sort(listDisplay, new Comparator<Aspirasi>() {
                        @Override
                        public int compare(Aspirasi o1, Aspirasi o2) {
                            return o1.getNama().compareTo(o2.getNama());
                        }
                    });
                }else if (selected.equals("Kelas")){
                    Collections.sort(listDisplay, new Comparator<Aspirasi>() {
                        @Override
                        public int compare(Aspirasi o1, Aspirasi o2) {
                            return o1.getKelas().compareTo(o2.getKelas());
                        }
                    });
                }else if (selected.equals("Jenis")){
                    Collections.sort(listDisplay, new Comparator<Aspirasi>() {
                        @Override
                        public int compare(Aspirasi o1, Aspirasi o2) {
                            return o1.getJenisAspirasi().compareTo(o2.getJenisAspirasi());
                        }
                    });
                }
                adapter = new AspirationListAdapter(listDisplay, getActivity().getApplicationContext(), db, afterDelete);
                listView.setAdapter(adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
}