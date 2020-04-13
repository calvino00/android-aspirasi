package uph.com.final_project_mpm;

public class AspiModel {
    private String NIM;
    private String nama;
    private String kelas;
    private String aspirasi;
    private String jenis;

//    public AspiModel(){
//        this.NIM = "03082170008";
//        this.nama = "Wesley Yando Tantra";
//        this.kelas = "17IT1";
//        this.aspirasi = "Saya sudah sangat lelah untuk coding";
//        this.jenis = "Umum";
//    }

    public AspiModel(String NIM, String nama, String kelas, String aspirasi, String jenis) {
        this.NIM = NIM;
        this.nama = nama;
        this.kelas = kelas;
        this.aspirasi = aspirasi;
        this.jenis = jenis;
    }

    public String getNIM() {
        return NIM;
    }

    public String getNama() {
        return nama;
    }

    public String getKelas() {
        return kelas;
    }

    public String getAspirasi() {
        return aspirasi;
    }

    public String getJenis() {
        return jenis;
    }

    public void setNIM(String NIM) {
        this.NIM = NIM;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public void setAspirasi(String aspirasi) {
        this.aspirasi = aspirasi;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }
}
