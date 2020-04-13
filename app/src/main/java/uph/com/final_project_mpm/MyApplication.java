package uph.com.final_project_mpm;

import android.app.Application;

import java.util.ArrayList;

import uph.com.final_project_mpm.Model.Aspirasi;

public class MyApplication extends Application {

    private ArrayList<Aspirasi> listAspirasi = new ArrayList<>();
    private String username = "mpmuphmc";
    private String password = "passwordmpm";

    public ArrayList<Aspirasi> getListAspirasi() {
        return listAspirasi;
    }

    public void setListAspirasi(ArrayList<Aspirasi> listAspirasi) {
        this.listAspirasi = listAspirasi;
    }

    public void addAspirasi(Aspirasi aspi){
        this.listAspirasi.add(aspi);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
