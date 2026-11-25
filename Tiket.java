
public class Tiket {
    private String kodeTiket;
    private Film film;
    private Studio studio;
    private int nomorKursi;
    private double harga;
    private boolean terjual;

    // Konstruktor awal
    public Tiket() {
        this("TK000", null, null, 0, 0, false);
    }

    // Konstruktor untuk mengisi data tiket
    public Tiket(String kodeTiket, Film film, Studio studio,
                 int nomorKursi, double harga) {
        this(kodeTiket, film, studio, nomorKursi, harga, true);
    }

    // Konstruktor dengan data lengkap
    public Tiket(String kodeTiket, Film film, Studio studio,
                 int nomorKursi, double harga, boolean terjual) {
        this.setKodeTiket(kodeTiket);
        this.setFilm(film);
        this.setStudio(studio);
        this.setNomorKursi(nomorKursi);
        this.setHarga(harga);
        this.setTerjual(terjual);
    }

    // Mengambil data tiket
    public String getKodeTiket() {
        return this.kodeTiket;
    }

    public Film getFilm() {
        return this.film;
    }

    public Studio getStudio() {
        return this.studio;
    }

    public int getNomorKursi() {
        return this.nomorKursi;
    }

    public double getHarga() {
        return this.harga;
    }

    public boolean isTerjual() {
        return this.terjual;
    }

    // Mengisi atau mengubah data tiket
    public void setKodeTiket(String kodeTiket) {
        if (kodeTiket != null && !kodeTiket.trim().isEmpty()) {
            this.kodeTiket = kodeTiket.trim().toUpperCase();
        }
    }

    public void setFilm(Film film) {
        if (film != null) {
            this.film = film;
        }
    }

    public void setStudio(Studio studio) {
        if (studio != null) {
            this.studio = studio;
        }
    }

    public void setNomorKursi(int nomorKursi) {
        if (nomorKursi > 0) {
            this.nomorKursi = nomorKursi;
        }
    }

    public void setHarga(double harga) {
        if (harga >= 0) {
            this.harga = harga;
        }
    }

    public void setTerjual(boolean terjual) {
        this.terjual = terjual;
    }

    // Mengambil total harga tiket
    public double hitungHargaTotal() {
        return this.harga;
    }

    // Mengubah status penjualan tiket
    public void ubahStatus() {
        this.terjual = !this.terjual;
    }

    // Menampilkan data tiket
    public void tampilkanInfo() {
        System.out.println("Kode Tiket : " + this.kodeTiket);
        System.out.println("Film       : " +
                (this.film != null ? this.film.getJudul() : "-"));
        System.out.println("Studio     : " +
                (this.studio != null ? this.studio.getNamaStudio() : "-"));
        System.out.println("Nomor Kursi: " + this.nomorKursi);
        System.out.printf("Harga      : Rp%.2f%n", this.harga);
        System.out.println("Status     : " + (this.terjual ? "Terjual" : "Belum Terjual"));
    }
}
