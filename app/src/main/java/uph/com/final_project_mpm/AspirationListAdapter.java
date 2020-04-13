package uph.com.final_project_mpm;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import uph.com.final_project_mpm.Data.factory.AppDatabase;
import uph.com.final_project_mpm.Model.Aspirasi;

public class AspirationListAdapter extends ArrayAdapter<Aspirasi> implements View.OnClickListener{

    private int lastPosition = -1;
    private ArrayList<Aspirasi> dataSet;
    private Context mContext;
    private Runnable afterDelete;
    private AppDatabase db;

    public AspirationListAdapter(ArrayList<Aspirasi> data, Context context, AppDatabase db, Runnable afterDelete) {
        super(context, R.layout.aspi_row, data);
        this.dataSet = data;
        this.mContext=context;
        this.db = db;
        this.afterDelete = afterDelete;
    }

    public void setDataSet(ArrayList<Aspirasi> dataSet) {
        this.dataSet.clear();
        this.dataSet.addAll(dataSet);
    }

    @Override
    public void onClick(View v) {
        int position=(Integer) v.getTag();
        Object object= getItem(position);


        Toast.makeText(mContext, "Info at " + (position+1), Toast.LENGTH_SHORT).show();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Aspirasi aspirasi = getItem(position);
        ViewHolder viewHolder;
        final View result;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.aspi_row, parent, false);


            viewHolder.txtNIM = (TextView) convertView.findViewById(R.id.nim);
            viewHolder.txtNama= (TextView) convertView.findViewById(R.id.nama);
            viewHolder.txtKelas= (TextView) convertView.findViewById(R.id.kelas);
            viewHolder.txtJenis= (TextView) convertView.findViewById(R.id.jenis);
            viewHolder.Delete = (Button)convertView.findViewById(R.id.btndelete);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        lastPosition = position;


        viewHolder.txtNIM.setText(aspirasi.getNim());
        viewHolder.txtNama.setText(aspirasi.getNama());
        viewHolder.txtKelas.setText(aspirasi.getKelas());
        viewHolder.txtJenis.setText(aspirasi.getJenisAspirasi());
        viewHolder.Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAspi(aspirasi);
            }
        });

        return convertView;
    }

    public void deleteAspi(Aspirasi aspirasi) {
        (new AsyncTask<Aspirasi, Void, Void>() {
            @Override
            protected Void doInBackground(Aspirasi... actions) {
                db.AspirasiDAO().deleteAspi(actions[0]);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                afterDelete.run();
            }
        }).execute(aspirasi);
    }

    private static class ViewHolder {
        private TextView txtNIM;
        private TextView txtNama;
        private TextView txtKelas;
        private TextView txtJenis;
        private Button Delete;
    }
}