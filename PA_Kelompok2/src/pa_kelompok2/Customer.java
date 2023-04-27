package pa_kelompok2;

import java.util.ArrayList;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.sql.Date;

public class Customer extends User implements Akun {

    private Date tglLahir;
    private String noTelp;
    private String alamat;

    public Customer(String username, String password, Date tglLahir, String noTelp, String alamat) {
        super(username, password);

        this.tglLahir = tglLahir;
        this.noTelp = noTelp;
        this.alamat = alamat;
    }

    public Customer() {}

    // GETTER & SETTER ==============================================================================
    
    public Date getTglLahir() {
        return tglLahir;
    }

    public void setTglLahir(Date tglLahir) {
        this.tglLahir = tglLahir;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    // METHOD ========================================================================================

    @Override
    public void logIn() throws InterruptedException {
        Main.akunLogin = this;
    }

    @Override
    public void logOut() throws InterruptedException {
        Main.gotoXY(0, 4);
        Main.message("\n\t\t       Keluar dari akun...", "yellow", 1500);
        Main.akunLogin = null;
    }

    // tampilkan atribut mode admin
    public void showAtt(Admin akun) {
        System.out.print(
            "\n\t Username      : " + this.username +
            "\n\t Tanggal Lahir : " + this.tglLahir +
            "\n\t No. Telepon   : " + this.noTelp +
            "\n\t Alamat        : " + this.alamat
        );
    }

    // tampilkan atribut mode customer
    public void showAtt(Customer akun) {
        System.out.print(
            "\n\t Username      : " + this.username +
            "\n\t Password      : "
        );
        for (int i=0; i<this.password.length(); i++) {
            System.out.print("*");
        }
        System.out.println(
            "\n\t Tanggal Lahir : " + this.tglLahir +
            "\n\t No. Telepon   : " + this.noTelp +
            "\n\t Alamat        : " + this.alamat
        );
    }

    // halaman detail akun
    public void display(User akun) throws IOException, InterruptedException {
        Main.clear();
        System.out.println(
            "\n                                                                 " + Main.color("bold") +
            "\n                         Detail Data Akun                        " + Main.color("reset") +
            "\n                                                                 " +
            "\n. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . ."
        );

        if (akun.getClass() == Admin.class) {
            showAtt((Admin) akun);
        } else {
            showAtt((Customer) akun);
        }

        System.out.printf("\n\n %60s", "Kembali => ");
        Main.br.readLine();
    }

    // tabel akun customer
    public static void table() {
                System.out.println(
            "\n      +----+---------------------+-----------------------+ " + 
            "\n      | No |      Username       |      No. Telepon      | " +
            "\n      +----+---------------------+-----------------------+ "
        );

        for (int i = 0; i < Main.arrCustomer.size(); i++) {
            System.out.printf(
                "      | %1$-2s | %2$-19s | %3$-21s | \n",
                i + 1,
                Main.arrCustomer.get(i).getUsername(),
                Main.arrCustomer.get(i).getNoTelp()
            );
        }

        System.out.println(
            "      +----+---------------------+-----------------------+ "
        );
    }

    // menu list ruangan
    @Override
    public void readRuangan() throws IOException, InterruptedException {
        Main.clear();
        System.out.println(
            "\n                                                               " + Main.color("bold") +
            "\n                -::-   Lihat Data Ruangan   -::-                " + Main.color("reset") +
            "\n                                                               " +
            "\n                              ---                              " +
            "\n                                                               "
        );

        for (int i = 0; i < Main.arrRuangan.size(); i++) {
            System.out.printf("\t|--------------------- %-2s ---------------------|\n\n", i + 1);
            Main.arrRuangan.get(i).showAtt(this);
            System.out.println("\n");
        }

        System.out.printf("\n\n %60s", "Kembali => ");
        Main.br.readLine();

    }

    // menu buat pesanan
    public void createPesanan() throws IOException, InterruptedException {
        boolean running = true;
        while (running) {
            Main.clear();
            System.out.println(
                "\n                                                               " + Main.color("bold") +
                "\n               -::-   Buat Pesanan Ruangan   -::-              " + Main.color("reset") +
                "\n                                                               " +
                "\n                              ---                              " +
                "\n                                                               "
            );    

            Date tglSekarang, tglCheckIn, tglCheckOut;

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate now = LocalDate.now();
            tglSekarang = Date.valueOf(dtf.format(now));

            System.out.println(
                "\t Dipesan pada " + tglSekarang + "\n" +
                "\n             Masukkan tanggal Check In dan Check Out " + 
                "\n                untuk mengecek ketersediaan kamar    "
            );

            
            try {
                System.out.println(Main.color("underline") + "\n\t Tanggal Check In (dd-mm-yyyy)" + Main.color("reset"));
                int dd1, mm1, yyyy1, dd2, mm2, yyyy2;
                System.out.print("\t dd   : ");
                dd1 = Integer.parseInt(Main.br.readLine());
                System.out.print("\t mm   : ");
                mm1 = Integer.parseInt(Main.br.readLine());
                System.out.print("\t yyyy : ");
                yyyy1 = Integer.parseInt(Main.br.readLine());
    
                System.out.println(Main.color("underline") + "\n\t Tanggal Check Out (dd-mm-yyyy)" + Main.color("reset"));
                System.out.print("\t dd   : ");
                dd2 = Integer.parseInt(Main.br.readLine());
                System.out.print("\t mm   : ");
                mm2 = Integer.parseInt(Main.br.readLine());
                System.out.print("\t yyyy : ");
                yyyy2 = Integer.parseInt(Main.br.readLine());

                tglCheckIn = Date.valueOf(yyyy1 + "-" + mm1 + "-" + dd1);
                tglCheckOut = Date.valueOf(yyyy2 + "-" + mm2 + "-" + dd2);

                if (
                    dd1 < 1 || dd1 > 31 ||
                    dd2 < 1 || dd2 > 31 ||
                    mm1 < 1 || mm1 > 12 ||
                    mm2 < 1 || mm2 > 12 ||
                    yyyy1 < 2000 || yyyy2 < 2000 ||
                    mm1 > mm2 || yyyy1 > yyyy2 ||
                    tglCheckIn.before(tglSekarang) ||
                    tglCheckOut.before(tglSekarang)
                ) {
                    Main.gotoXY(0, 4);
                    Main.message("\n\t\t      Tanggal tidak valid!", "red", 1200);
                    running = false;
                    break;
                }

                // pilih ruangan yang ingin dipesan
                boolean pilihRuangan = true;
                while (pilihRuangan) {
                    Main.clear();
                    System.out.println(
                        "\n                                                               " + Main.color("bold") +
                        "\n                  -::-   Pesan Ruangan   -::-                  " + Main.color("reset") +
                        "\n                                                               " +
                        "\n                              ---                              " 
                    );

                    // tampilkan list ruangan yang tersedia
                    String query = "SELECT * FROM ruangan " + 
                                    "WHERE id NOT IN (" +
                                        "SELECT idRuangan FROM pesanan " +
                                        "WHERE tglCheckIn <= '" + tglCheckIn + "' " +
                                        "AND tglCheckOut >= '" + tglCheckIn + "' " +
                                        "OR tglCheckIn <= '" + tglCheckOut + "' " + 
                                        "AND tglCheckOut >= '" + tglCheckOut + "' " +
                                    ")";

                    ArrayList<Ruangan> ruanganTersedia = Main.db.selectRuangan(query);
                    Ruangan.table(ruanganTersedia);

                    System.out.print(
                        "\n                                                               " +
                        "\n               Pilih nomor untuk memilih ruangan!              " +
                        "\n                  (Ketik '0' untuk membatalkan)                " +
                        "\n                                                               " +
                        "\n                             =: "
                    );
        
                    // pilih ruangan
                    int pilih;
                    try {
                        pilih = Integer.parseInt(Main.br.readLine());
        
                        // batal memesan
                        if (pilih == 0) {
                            Main.gotoXY(0, 4);
                            Main.message("\n\t\t      Membatalkan pesanan...", "yellow", 1500);
                            pilihRuangan = false;
                            running = false;
        
                        // ruangan tidak ditemukan
                        } else if (pilih < 0) {
                            Main.gotoXY(0, 4);
                            Main.message("\n\t\t    Ruangan tidak ditemukan!", "red", 1000);
        
                        // buat pesanan
                        } else {
                            String namaPemesan = "",
                                   pesan = "1";
                            do {
                                Ruangan ruanganDipesan = ruanganTersedia.get(pilih - 1);
                                ruanganDipesan.display(this);
    
                                System.out.print(
                                    "\n\t Ketik '1' untuk memesan ruangan ini dengan :" +
                                    "\n\t - Check in pada " + tglCheckIn +
                                    "\n\t - Check out pada " + tglCheckOut +
                                    "\n\t =: "
                                );
    
                                pesan = Main.br.readLine();
    
                                // jika batal memesan ruangan
                                if (!pesan.equals("1")) {
                                    Main.message("\n\t\t     Memilih ruangan baru...", "yellow", 1500);
                                    break;
                                }
    
                                System.out.print("\n\tNama Tamu : ");
                                namaPemesan = Main.br.readLine();

                            } while (Main.isSpace(namaPemesan));
    
                            if (!pesan.equals("1")){
                                continue;
                            }

                            // buat pesanan
                            Pesanan pesananBaru = new Pesanan(
                                "", tglSekarang, tglCheckIn, tglCheckOut,
                                ruanganTersedia.get(pilih - 1), namaPemesan, this, "Definite"
                            );
    
                            Main.db.insertToDB(pesananBaru);
                            Pesanan.updateStatusHariIni(Main.db);
                            
                            Main.db.retrievePesanan();
    
                            Main.message("\n\t\t    Pesanan berhasil dibuat!", "green", 1500);
                            
                            pilihRuangan = false;
                            running = false;
                        }
                            
                    } catch (Exception e) {
                        Main.gotoXY(0, 4);
                        Main.message("\n\t\t    Ruangan tidak ditemukan!", "red", 1000);
                    } // end try catch pilih ruangan

                } // end while pilih ruangan
            
            // input tanggal check in/out salah
            } catch (Exception e) {
                Main.gotoXY(0, 4);
                Main.message("\n\t\t      Tanggal tidak valid!", "red", 1200);
                running = false;
            } // end try catch input tanggal

        } // end while running
    }

    // menu lihat pesanan
    @Override
    public void readPesanan() throws IOException, InterruptedException {
        boolean running = true;

        while (running) {
            Main.clear();
            System.out.println(
                "\n                                                               " + Main.color("bold") +
                "\n               -::-   Riwayat Pesanan Saya   -::-             " + Main.color("reset") +
                "\n                                                               " +
                "\n                              ---                              "
            );

            // list pesanan yang dipakai hanya punya si customer
            ArrayList<Pesanan> pesananCustomer = new ArrayList<>();
            pesananCustomer = Main.db.selectPesanan(
                "SELECT * FROM pesanan " +
                "WHERE username='" + this.username + "'" 
            );

            Pesanan.table(pesananCustomer);

            System.out.print(
                "\n           Pilih nomor untuk melihat detail pesanan!         " +
                "\n                  (Ketik '0' untuk kembali)                  " +
                "\n                                                            " +
                "\n                            =: "
            );

            int pilih;
            try {
                pilih = Integer.parseInt(Main.br.readLine());
                
                // kembali 
                if (pilih == 0) {
                    running = false;
                    
                // pesanan tidak ada
                } else if (
                    pilih < 0 || 
                    pilih > pesananCustomer.size()
                ) {
                    Main.gotoXY(0, 4);
                    Main.message("\n\t\t     Pesanan tidak ditemukan!", "red", 1000);

                // tampilkan detail pesanan
                } else {
                    pesananCustomer.get(pilih - 1).display();
                }

            } catch (Exception e) {
                Main.gotoXY(0, 4);
                Main.message("\n\t\t     Pesanan tidak ditemukan!", "red", 1000);
            }
        }
        
    }

}
