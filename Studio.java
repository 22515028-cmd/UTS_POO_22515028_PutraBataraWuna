
public class Studio {
    private String kodeStudio;
    private String namaStudio;
    private int kapasitas;
    private int kursiTerisi;
    private boolean aktif;

    // Konstruktor awal
    public Studio() {
        this("ST00", "Studio Default", 1, false);
    }

    // Konstruktor untuk mengisi data studio
    public Studio(String kodeStudio, String namaStudio,
                  int kapasitas, boolean aktif) {
        this.setKodeStudio(kodeStudio);
        this.setNamaStudio(namaStudio);
        this.setKapasitas(kapasitas);
        this.kursiTerisi = 0;
        this.setAktif(aktif);
    }

    // Mengambil data studio
    public String getKodeStudio() {
        return this.kodeStudio;
    }

    public String getNamaStudio() {
        return this.namaStudio;
    }

    public int getKapasitas() {
        return this.kapasitas;
    }

    public int getKursiTerisi() {
        return this.kursiTerisi;
    }

    public boolean isAktif() {
        return this.aktif;
    }

    // Mengisi atau mengubah data studio
    public void setKodeStudio(String kodeStudio) {
        if (kodeStudio != null && !kodeStudio.trim().isEmpty()) {
            this.kodeStudio = kodeStudio.trim().toUpperCase();
        }
    }

    public void setNamaStudio(String namaStudio) {
        if (namaStudio != null && !namaStudio.trim().isEmpty()) {
            this.namaStudio = namaStudio.trim();
        }
    }

    public void setKapasitas(int kapasitas) {
        if (kapasitas > 0 && kapasitas >= this.kursiTerisi) {
            this.kapasitas = kapasitas;
        }
    }

    public void setAktif(boolean aktif) {
        this.aktif = aktif;
    }

    // Mengecek apakah kursi masih tersedia
    public boolean cekKetersediaan() {
        return this.aktif && this.kursiTerisi < this.kapasitas;
    }

    // Menambah jumlah kursi yang sudah dipesan
    public boolean pesanKursi() {
        if (this.cekKetersediaan()) {
            this.kursiTerisi++;
            return true;
        }
        return false;
    }

    // Mengurangi kursi yang sudah terisi
    public boolean batalkanKursi() {
        if (this.kursiTerisi > 0) {
            this.kursiTerisi--;
            return true;
        }
        return false;
    }

    // Menghitung jumlah kursi yang masih kosong
    public int sisaKursi() {
        return this.kapasitas - this.kursiTerisi;
    }

    // Menampilkan data studio
    public void tampilkanInfo() {
        System.out.println("Kode Studio   : " + this.kodeStudio);
        System.out.println("Nama Studio   : " + this.namaStudio);
        System.out.println("Kapasitas     : " + this.kapasitas);
        System.out.println("Kursi Terisi  : " + this.kursiTerisi);
        System.out.println("Sisa Kursi    : " + this.sisaKursi());
        System.out.println("Status        : " + (this.aktif ? "Aktif" : "Tidak Aktif"));
    }
}
