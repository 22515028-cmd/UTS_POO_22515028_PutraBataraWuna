

public class Bioskop {
    private String namaBioskop;
    private String lokasi;

    private Film[] daftarFilm;
    private Studio[] daftarStudio;

    private int jumlahFilm;
    private int jumlahStudio;

    private SortedTiketList daftarTiket;

    // Batas maksimal data film dan studio
    private static final int MAX_FILM = 20;
    private static final int MAX_STUDIO = 10;

    // Konstruktor awal
    public Bioskop() {
        this("Bioskop UMK", "Kendari");
    }

    // Konstruktor untuk mengisi data bioskop
    public Bioskop(String namaBioskop, String lokasi) {
        this.namaBioskop = namaBioskop;
        this.lokasi = lokasi;

        this.daftarFilm = new Film[MAX_FILM];
        this.daftarStudio = new Studio[MAX_STUDIO];

        this.jumlahFilm = 0;
        this.jumlahStudio = 0;

        // Menyiapkan tempat penyimpanan tiket
        this.daftarTiket = new SortedTiketList();
    }

    // Mengambil data bioskop
    public String getNamaBioskop() {
        return this.namaBioskop;
    }

    public String getLokasi() {
        return this.lokasi;
    }

    public SortedTiketList getDaftarTiket() {
        return this.daftarTiket;
    }

    // Menambahkan data film
    public boolean tambahFilm(Film film) {
        if (film == null || this.jumlahFilm >= MAX_FILM) {
            return false;
        }

        if (this.cariFilm(film.getKodeFilm()) != null) {
            return false;
        }

        this.daftarFilm[this.jumlahFilm] = film;
        this.jumlahFilm++;

        return true;
    }

    // Menambahkan data studio
    public boolean tambahStudio(Studio studio) {
        if (studio == null || this.jumlahStudio >= MAX_STUDIO) {
            return false;
        }

        if (this.cariStudio(studio.getKodeStudio()) != null) {
            return false;
        }

        this.daftarStudio[this.jumlahStudio] = studio;
        this.jumlahStudio++;

        return true;
    }

    // Mencari film berdasarkan kode
    public Film cariFilm(String kode) {
        if (kode == null) {
            return null;
        }

        for (int i = 0; i < this.jumlahFilm; i++) {
            if (this.daftarFilm[i].getKodeFilm().equalsIgnoreCase(kode.trim())) {
                return this.daftarFilm[i];
            }
        }

        return null;
    }

    // Mencari film berdasarkan judul
    public Film cariFilmJudul(String judul) {
        if (judul == null) {
            return null;
        }

        for (int i = 0; i < this.jumlahFilm; i++) {
            if (this.daftarFilm[i].getJudul().equalsIgnoreCase(judul.trim())) {
                return this.daftarFilm[i];
            }
        }

        return null;
    }

    // Mencari studio berdasarkan kode
    public Studio cariStudio(String kode) {
        if (kode == null) {
            return null;
        }

        for (int i = 0; i < this.jumlahStudio; i++) {
            if (this.daftarStudio[i].getKodeStudio().equalsIgnoreCase(kode.trim())) {
                return this.daftarStudio[i];
            }
        }

        return null;
    }

    // Mencari tiket berdasarkan kode
    public Tiket cariTiket(String kode) {
        int indeks = this.daftarTiket.search(kode);

        if (indeks == -1) {
            return null;
        }

        return this.daftarTiket.get(indeks);
    }

    // Mengecek apakah kursi sudah dipesan
    public boolean kursiSudahDipesan(String kodeStudio, int nomorKursi) {
        for (int i = 0; i < this.daftarTiket.size(); i++) {
            Tiket tiket = this.daftarTiket.get(i);

            if (tiket.getStudio().getKodeStudio().equalsIgnoreCase(kodeStudio)
                    && tiket.getNomorKursi() == nomorKursi
                    && tiket.isTerjual()) {
                return true;
            }
        }

        return false;
    }

    // Mengatur proses pemesanan tiket
    public boolean pesanTiket(String kodeFilm, String kodeStudio, int nomorKursi) {
        Film film = this.cariFilm(kodeFilm);
        Studio studio = this.cariStudio(kodeStudio);

        if (film == null || studio == null) {
            System.out.println("Film atau studio tidak ditemukan.");
            return false;
        }

        if (!film.isAktif()) {
            System.out.println("Film sedang tidak aktif.");
            return false;
        }

        if (!studio.isAktif()) {
            System.out.println("Studio sedang tidak aktif.");
            return false;
        }

        if (nomorKursi < 1 || nomorKursi > studio.getKapasitas()) {
            System.out.println("Nomor kursi berada di luar kapasitas studio.");
            return false;
        }

        if (studio.getKursiTerisi() >= studio.getKapasitas()) {
            System.out.println("Studio sudah penuh.");
            return false;
        }

        if (this.kursiSudahDipesan(kodeStudio, nomorKursi)) {
            System.out.println("Kursi tersebut sudah dipesan.");
            return false;
        }

        if (this.daftarTiket.isFull()) {
            System.out.println("Penyimpanan tiket sudah penuh.");
            return false;
        }

        // Membuat kode tiket secara otomatis
        String kodeTiket = String.format("TK%03d",
                this.daftarTiket.size() + 1);

        // Mengecek agar kode tiket tidak sama
        // dengan tiket yang masih tersimpan
        while (this.cariTiket(kodeTiket) != null) {
            kodeTiket = "TK" + (this.daftarTiket.size() + 1)
                    + System.currentTimeMillis() % 1000;
        }

        Tiket tiket = new Tiket(
                kodeTiket,
                film,
                studio,
                nomorKursi,
                film.getHarga()
        );

        // Memesan kursi dan menyimpan data tiket
        if (!studio.pesanKursi()) {
            System.out.println("Gagal memesan kursi.");
            return false;
        }

        if (!this.daftarTiket.insert(tiket)) {
            studio.batalkanKursi();
            System.out.println("Gagal menyimpan tiket.");
            return false;
        }

        System.out.println("Pemesanan berhasil!");
        System.out.println("Kode tiket: " + kodeTiket);

        return true;
    }

    // Menghapus tiket dan mengembalikan kursi
    public boolean hapusTiket(String kodeTiket) {
        Tiket tiket = this.cariTiket(kodeTiket);

        if (tiket == null) {
            return false;
        }

        if (tiket.isTerjual()) {
            tiket.getStudio().batalkanKursi();
        }

        return this.daftarTiket.remove(kodeTiket);
    }

    // Menampilkan semua data film
    public void tampilkanFilm() {
        if (this.jumlahFilm == 0) {
            System.out.println("Data film masih kosong.");
            return;
        }

        for (int i = 0; i < this.jumlahFilm; i++) {
            System.out.println("\nFilm ke-" + (i + 1));
            this.daftarFilm[i].tampilkanInfo();
        }
    }

    // Menampilkan semua data studio
    public void tampilkanStudio() {
        if (this.jumlahStudio == 0) {
            System.out.println("Data studio masih kosong.");
            return;
        }

        for (int i = 0; i < this.jumlahStudio; i++) {
            System.out.println("\nStudio ke-" + (i + 1));
            this.daftarStudio[i].tampilkanInfo();
        }
    }

    // Menghitung dan menampilkan laporan bioskop
    public void tampilkanLaporan() {
        int totalTiket = this.daftarTiket.size();
        double totalPendapatan = 0;
        int totalSisaKursi = 0;

        for (int i = 0; i < this.daftarTiket.size(); i++) {
            totalPendapatan += this.daftarTiket.get(i).getHarga();
        }

        for (int i = 0; i < this.jumlahStudio; i++) {
            totalSisaKursi += this.daftarStudio[i].sisaKursi();
        }

        System.out.println("\n=== LAPORAN BIOSKOP ===");
        System.out.println("Nama Bioskop     : " + this.namaBioskop);
        System.out.println("Lokasi           : " + this.lokasi);
        System.out.println("Jumlah Film      : " + this.jumlahFilm);
        System.out.println("Jumlah Studio    : " + this.jumlahStudio);
        System.out.println("Tiket Terjual    : " + totalTiket);
        System.out.println("Sisa Kursi      : " + totalSisaKursi);
        System.out.printf("Total Pendapatan : Rp%.2f%n", totalPendapatan);
    }
}
