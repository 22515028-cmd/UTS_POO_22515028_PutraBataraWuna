//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import java.util.Scanner;

/**
 * Class MainBioskop menjalankan menu console Sistem Manajemen Bioskop.
 */
public class MainBioskop {
    private Scanner input;
    private Bioskop bioskop;

    // Konstruktor default
    public MainBioskop() {
        this(new Scanner(System.in), new Bioskop());
    }

    // Konstruktor berparameter
    public MainBioskop(Scanner input, Bioskop bioskop) {
        this.input = input;
        this.bioskop = bioskop;
    }

    public static void main(String[] args) {
        MainBioskop aplikasi = new MainBioskop();
        aplikasi.menu();
    }

    // Data awal aplikasi
    private void dataAwal() {
        this.bioskop.tambahFilm(
                new Film("F001", "Agak Laen", "Komedi,Horor", 150, 50000, true)
        );

        this.bioskop.tambahFilm(
                new Film("F002", "KKN di Desa Penari", "Horor,Misteri", 100, 40000, true)
        );

        this.bioskop.tambahFilm(
                new Film("F003", "Pengabdi Setan 2 Communion", "Horor", 169, 55000, true)
        );

        this.bioskop.tambahStudio(
                new Studio("ST01", "Studio 1", 20, true)
        );

        this.bioskop.tambahStudio(
                new Studio("ST02", "Studio 2", 15, true)
        );

        this.bioskop.tambahStudio(
                new Studio("ST03", "Studio 3", 10, true)
        );
    }

    // Menu utama
    public void menu() {
        this.dataAwal();

        int pilihan;

        do {
            System.out.println("\n=================================");
            System.out.println("   SISTEM MANAJEMEN BIOSKOP");
            System.out.println("=================================");
            System.out.println("1. Tambah dan tampilkan Film");
            System.out.println("2. Tambah dan tampilkan Studio");
            System.out.println("3. Pemesanan Tiket");
            System.out.println("4. Cari Data");
            System.out.println("5. Operasi ADT Sorted List");
            System.out.println("6. Laporan Bioskop");
            System.out.println("7. Keluar");
            System.out.println("=================================");

            pilihan = bacaInt("Pilih menu: ");

            switch (pilihan) {
                case 1:
                    menuFilm();
                    break;
                case 2:
                    menuStudio();
                    break;
                case 3:
                    menuPesanTiket();
                    break;
                case 4:
                    menuCariData();
                    break;
                case 5:
                    menuADT();
                    break;
                case 6:
                    this.bioskop.tampilkanLaporan();
                    break;
                case 7:
                    System.out.println("Program selesai. Terima kasih.");
                    break;
                default:
                    System.out.println("Menu tidak tersedia.");
            }

        } while (pilihan != 7);
    }

    // Menu film
    private void menuFilm() {
        System.out.println("\n=== MENU FILM ===");
        System.out.println("1. Tambah Film");
        System.out.println("2. Tampilkan Film");

        int pilihan = bacaInt("Pilih: ");

        if (pilihan == 1) {
            String kode = bacaString("Kode film: ");
            String judul = bacaString("Judul: ");
            String genre = bacaString("Genre: ");
            int durasi = bacaInt("Durasi (menit): ");
            double harga = bacaDouble("Harga tiket: ");

            Film film = new Film(kode, judul, genre,
                    durasi, harga, true);

            if (this.bioskop.tambahFilm(film)) {
                System.out.println("Film berhasil ditambahkan.");
            } else {
                System.out.println("Film gagal ditambahkan. Kode mungkin sudah digunakan.");
            }
        } else if (pilihan == 2) {
            this.bioskop.tampilkanFilm();
        } else {
            System.out.println("Pilihan tidak tersedia.");
        }
    }

    // Menu studio
    private void menuStudio() {
        System.out.println("\n=== MENU STUDIO ===");
        System.out.println("1. Tambah Studio");
        System.out.println("2. Tampilkan Studio");

        int pilihan = bacaInt("Pilih: ");

        if (pilihan == 1) {
            String kode = bacaString("Kode studio: ");
            String nama = bacaString("Nama studio: ");
            int kapasitas = bacaInt("Kapasitas kursi: ");

            Studio studio = new Studio(kode, nama, kapasitas, true);

            if (this.bioskop.tambahStudio(studio)) {
                System.out.println("Studio berhasil ditambahkan.");
            } else {
                System.out.println("Studio gagal ditambahkan.");
            }
        } else if (pilihan == 2) {
            this.bioskop.tampilkanStudio();
        } else {
            System.out.println("Pilihan tidak tersedia.");
        }
    }

    // Menu pemesanan tiket
    private void menuPesanTiket() {
        System.out.println("\n=== PEMESANAN TIKET ===");

        this.bioskop.tampilkanFilm();
        String kodeFilm = bacaString("Masukkan kode film: ");

        this.bioskop.tampilkanStudio();
        String kodeStudio = bacaString("Masukkan kode studio: ");

        int nomorKursi = bacaInt("Nomor kursi: ");

        this.bioskop.pesanTiket(kodeFilm, kodeStudio, nomorKursi);
    }

    // Menu pencarian
    private void menuCariData() {
        System.out.println("\n=== PENCARIAN DATA ===");
        System.out.println("1. Cari Film berdasarkan kode");
        System.out.println("2. Cari Film berdasarkan judul");
        System.out.println("3. Cari Studio");
        System.out.println("4. Cari Tiket");

        int pilihan = bacaInt("Pilih: ");

        switch (pilihan) {
            case 1: {
                String kode = bacaString("Kode film: ");
                Film film = this.bioskop.cariFilm(kode);

                if (film != null) {
                    film.tampilkanInfo();
                } else {
                    System.out.println("Film tidak ditemukan.");
                }
                break;
            }

            case 2: {
                String judul = bacaString("Judul film: ");
                Film film = this.bioskop.cariFilmJudul(judul);

                if (film != null) {
                    film.tampilkanInfo();
                } else {
                    System.out.println("Film tidak ditemukan.");
                }
                break;
            }

            case 3: {
                String kode = bacaString("Kode studio: ");
                Studio studio = this.bioskop.cariStudio(kode);

                if (studio != null) {
                    studio.tampilkanInfo();
                } else {
                    System.out.println("Studio tidak ditemukan.");
                }
                break;
            }

            case 4: {
                String kode = bacaString("Kode tiket: ");
                Tiket tiket = this.bioskop.cariTiket(kode);

                if (tiket != null) {
                    tiket.tampilkanInfo();
                } else {
                    System.out.println("Tiket tidak ditemukan.");
                }
                break;
            }

            default:
                System.out.println("Pilihan tidak tersedia.");
        }
    }

    // Menu operasi ADT
    private void menuADT() {
        SortedTiketList list = this.bioskop.getDaftarTiket();

        int pilihan;

        do {
            System.out.println("\n=== OPERASI ADT SORTED LIST ===");
            System.out.println("1. Insert tiket");
            System.out.println("2. Remove tiket");
            System.out.println("3. Search tiket");
            System.out.println("4. Get tiket berdasarkan index");
            System.out.println("5. Tampilkan isi list");
            System.out.println("6. Cek size dan kapasitas");
            System.out.println("7. Kembali");

            pilihan = bacaInt("Pilih: ");

            switch (pilihan) {
                case 1:
                    System.out.println("Insert tiket dilakukan melalui transaksi pemesanan.");
                    System.out.println("Gunakan Menu 3 untuk menambahkan tiket.");
                    break;

                case 2: {
                    String kode = bacaString("Kode tiket yang dihapus: ");

                    if (this.bioskop.hapusTiket(kode)) {
                        System.out.println("Tiket berhasil dihapus dan kursi dikembalikan.");
                    } else {
                        System.out.println("Tiket tidak ditemukan.");
                    }
                    break;
                }

                case 3: {
                    String kode = bacaString("Kode tiket: ");
                    int index = list.search(kode);

                    if (index >= 0) {
                        System.out.println("Tiket ditemukan pada indeks " + index);
                        list.get(index).tampilkanInfo();
                    } else {
                        System.out.println("Tiket tidak ditemukan.");
                    }
                    break;
                }

                case 4: {
                    int index = bacaInt("Masukkan index: ");
                    Tiket tiket = list.get(index);

                    if (tiket != null) {
                        tiket.tampilkanInfo();
                    } else {
                        System.out.println("Index tidak valid.");
                    }
                    break;
                }

                case 5:
                    list.tampilkan();
                    break;

                case 6:
                    System.out.println("Jumlah data : " + list.size());
                    System.out.println("Kapasitas  : " + list.getKapasitas());
                    System.out.println("Kosong     : " + list.isEmpty());
                    System.out.println("Penuh      : " + list.isFull());
                    break;

                case 7:
                    System.out.println("Kembali ke menu utama.");
                    break;

                default:
                    System.out.println("Pilihan tidak tersedia.");
            }

        } while (pilihan != 7);
    }

    // Membaca input String dan menolak input kosong
    private String bacaString(String pesan) {
        while (true) {
            System.out.print(pesan);
            String nilai = this.input.nextLine().trim();

            if (!nilai.isEmpty()) {
                return nilai;
            }

            System.out.println("Input tidak boleh kosong.");
        }
    }

    // Membaca input integer dengan try-catch
    private int bacaInt(String pesan) {
        while (true) {
            System.out.print(pesan);
            String nilai = this.input.nextLine().trim();

            try {
                return Integer.parseInt(nilai);
            } catch (NumberFormatException e) {
                System.out.println("Masukkan angka bulat yang benar.");
            }
        }
    }

    // Membaca input double dengan try-catch
    private double bacaDouble(String pesan) {
        while (true) {
            System.out.print(pesan);
            String nilai = this.input.nextLine().trim();

            try {
                double angka = Double.parseDouble(nilai);

                if (angka >= 0) {
                    return angka;
                }

                System.out.println("Harga tidak boleh negatif.");
            } catch (NumberFormatException e) {
                System.out.println("Masukkan angka yang benar.");
            }
        }
    }
}
