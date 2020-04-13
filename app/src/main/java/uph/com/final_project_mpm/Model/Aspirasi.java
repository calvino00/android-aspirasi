package uph.com.final_project_mpm.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "tAspirasi")
public class Aspirasi implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int intID;

    @ColumnInfo(name = "JenisAspirasi")
    public String jenisAspirasi;

    @ColumnInfo(name = "IsiAspirasi")
    public String isiAspirasi;

    @ColumnInfo(name = "Nama")
    public String nama;

    @ColumnInfo(name = "NIM")
    public String nim;

    @ColumnInfo(name = "Kelas")
    public String kelas;

    public Aspirasi () {

    }

    public Aspirasi(String nim, String Nama, String Kelas, String Aspirasi, String Jenis) {
        this.nim = nim;
        this.nama = Nama;
        this.kelas = Kelas;
        this.isiAspirasi = Aspirasi;
        this.jenisAspirasi = Jenis;
    }


    public int getIntID() {
        return intID;
    }

    public void setIntID(int intID) {
        this.intID = intID;
    }

    public String getJenisAspirasi() {
        return jenisAspirasi;
    }

    public void setJenisAspirasi(String jenisAspirasi) {
        this.jenisAspirasi = jenisAspirasi;
    }

    public String getIsiAspirasi() {
        return isiAspirasi;
    }

    public void setIsiAspirasi(String isiAspirasi) {
        this.isiAspirasi = isiAspirasi;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }
}
