

public class SortedTiketList {
    private static final int MAX_KAPASITAS = 20;

    private Tiket[] data;
    private int size;
    private int kapasitas;

    // Konstruktor awal
    public SortedTiketList() {
        this(MAX_KAPASITAS);
    }

    // Konstruktor untuk menentukan kapasitas list
    public SortedTiketList(int kapasitas) {
        if (kapasitas <= 0) {
            kapasitas = MAX_KAPASITAS;
        }

        this.kapasitas = kapasitas;
        this.data = new Tiket[kapasitas];
        this.size = 0;
    }

    // Menambahkan tiket sesuai urutan kode
    public boolean insert(Tiket tiket) {
        if (tiket == null || this.isFull()) {
            return false;
        }

        // Mengecek apakah kode tiket sudah ada
        if (this.search(tiket.getKodeTiket()) != -1) {
            return false;
        }

        int posisi = 0;

        // Mencari posisi yang sesuai dengan kode tiket
        while (posisi < this.size &&
                data[posisi].getKodeTiket().compareToIgnoreCase(
                        tiket.getKodeTiket()) < 0) {
            posisi++;
        }

        // Menggeser data ke kanan
        for (int i = this.size; i > posisi; i--) {
            this.data[i] = this.data[i - 1];
        }

        this.data[posisi] = tiket;
        this.size++;

        return true;
    }

    // Menghapus tiket berdasarkan kode
    public boolean remove(String kodeTiket) {
        int posisi = this.search(kodeTiket);

        if (posisi == -1) {
            return false;
        }

        // Menggeser data ke kiri setelah tiket dihapus
        for (int i = posisi; i < this.size - 1; i++) {
            this.data[i] = this.data[i + 1];
        }

        this.data[this.size - 1] = null;
        this.size--;

        return true;
    }

    // Mencari tiket berdasarkan kode
    public int search(String kodeTiket) {
        if (kodeTiket == null) {
            return -1;
        }

        for (int i = 0; i < this.size; i++) {
            if (this.data[i].getKodeTiket().equalsIgnoreCase(kodeTiket.trim())) {
                return i;
            }
        }

        return -1;
    }

    // Mengambil data tiket berdasarkan posisi
    public Tiket get(int index) {
        if (index < 0 || index >= this.size) {
            return null;
        }

        return this.data[index];
    }

    // Menampilkan semua tiket berdasarkan urutan kode
    public void tampilkan() {
        if (this.isEmpty()) {
            System.out.println("Data tiket masih kosong.");
            return;
        }

        System.out.println("\n=== DAFTAR TIKET TERURUT ===");

        for (int i = 0; i < this.size; i++) {
            System.out.println("\nData ke-" + (i + 1));
            this.data[i].tampilkanInfo();
        }
    }

    // Menghitung jumlah tiket yang tersimpan
    public int size() {
        return this.size;
    }

    // Mengecek apakah list masih kosong
    public boolean isEmpty() {
        return this.size == 0;
    }

    // Mengecek apakah kapasitas list sudah penuh
    public boolean isFull() {
        return this.size == this.kapasitas;
    }

    // Mengambil kapasitas list
    public int getKapasitas() {
        return this.kapasitas;
    }
}
