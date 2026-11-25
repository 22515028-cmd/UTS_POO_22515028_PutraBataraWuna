
public class Film {
    private String kodeFilm;
    private String judul;
    private String genre;
    private int durasi;
    private double harga;
    private boolean aktif;

    // Konstruktor awal
    public Film() {
        this("F000", "Belum Ada Judul", "Umum", 1, 0, false);
    }

    // Konstruktor dengan isi data
    public Film(String kodeFilm, String judul, String genre,
                int durasi, double harga, boolean aktif) {
        this.setKodeFilm(kodeFilm);
        this.setJudul(judul);
        this.setGenre(genre);
        this.setDurasi(durasi);
        this.setHarga(harga);
        this.setAktif(aktif);
    }

    // Mengambil data film
    public String getKodeFilm() {
        return this.kodeFilm;
    }

    public String getJudul() {
        return this.judul;
    }

    public String getGenre() {
        return this.genre;
    }

    public int getDurasi() {
        return this.durasi;
    }

    public double getHarga() {
        return this.harga;
    }

    public boolean isAktif() {
        return this.aktif;
    }

    // Mengisi atau mengubah data film
    public void setKodeFilm(String kodeFilm) {
        if (kodeFilm != null && !kodeFilm.trim().isEmpty()) {
            this.kodeFilm = kodeFilm.trim().toUpperCase();
        }
    }

    public void setJudul(String judul) {
        if (judul != null && !judul.trim().isEmpty()) {
            this.judul = judul.trim();
        }
    }

    public void setGenre(String genre) {
        if (genre != null && !genre.trim().isEmpty()) {
            this.genre = genre.trim();
        }
    }

    public void setDurasi(int durasi) {
        if (durasi > 0) {
            this.durasi = durasi;
        }
    }

    public void setHarga(double harga) {
        if (harga >= 0) {
            this.harga = harga;
        }
    }

    public void setAktif(boolean aktif) {
        this.aktif = aktif;
    }

    // Menampilkan data film
    public void tampilkanInfo() {
        System.out.println("Kode   : " + this.kodeFilm);
        System.out.println("Judul  : " + this.judul);
        System.out.println("Genre  : " + this.genre);
        System.out.println("Durasi : " + this.durasi + " menit");
        System.out.printf("Harga  : Rp%.2f%n", this.harga);
        System.out.println("Status : " + (this.aktif ? "Aktif" : "Tidak Aktif"));
    }

    // Mengubah status film
    public void ubahStatus(boolean status) {
        this.setAktif(status);
    }

    // Menghitung harga setelah diskon
    public double hitungHargaDenganDiskon(double diskon) {
        if (diskon < 0 || diskon > 100) {
            return this.harga;
        }

        return this.harga - (this.harga * diskon / 100);
    }
}
