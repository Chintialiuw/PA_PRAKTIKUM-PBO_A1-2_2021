package pa_kelompok2;

import java.io.IOException;
import java.util.ArrayList;

public class Admin extends User implements Akun {
    
    public Admin(String username, String password) {
        super(username, password);
    }

    // METHOD ========================================================================================

    @Override
    public void logIn() throws InterruptedException {
        Main.akunLogin = this;
    }

    @Override
    public void logOut() throws InterruptedException {
        Main.gotoXY(0, 4);
        Main.message("\n\t\t       Keluar dari akun...", "yellow", 1000);
        Main.akunLogin = null;
    }

    public void createRuangan() throws IOException, InterruptedException {
        Main.clear();
        System.out.println(
            "\n                                                               " + Main.color("bold") +
            "\n               -::-   Tambah Data Ruangan   -::-               " + Main.color("reset") +
            "\n                                                               " +
            "\n                              ---                              " +
            "\n                                                               "
        );

        try {
            System.out.print("\t Nama      : ");
            String nama = Main.br.readLine();
            System.out.print("\t Harga     : Rp ");
            int harga = Integer.parseInt(Main.br.readLine());
            System.out.print("\t Kapasitas : ");
            int kapasitas = Integer.parseInt(Main.br.readLine());
            System.out.print("\t Fasilitas : ");
            String fasilitas = Main.br.readLine();
            System.out.print("\t Lantai    : ");
            int lantai = Integer.parseInt(Main.br.readLine());
            System.out.println("\t (Kamar / Ruang Rapat / Aula / Kolam Renang / Restaurant)");
            System.out.print("\t Jenis     : ");
            String jenis = Main.br.readLine();
            String status = "Tersedia";

            ArrayList<String> jenisArr = new ArrayList<>();
            jenisArr.add("kamar");
            jenisArr.add("ruang rapat");
            jenisArr.add("aula");
            jenisArr.add("kolam renang");
            jenisArr.add("restaurant");

            // cek formulir
            if (
                Main.isSpace(nama) ||
                Main.isSpace(fasilitas) ||
                Main.isSpace(jenis) ||
                !jenisArr.contains(jenis.toLowerCase())
            ) {
                Main.gotoXY(0, 4);
                Main.message("\n\t\t       Isian tidak valid!", "red", 1200);
                return;
            }
        
            Ruangan ruangan = new Ruangan(0, nama, harga, kapasitas, fasilitas, lantai, jenis, status);
            
            Main.arrRuangan.add(ruangan);
            Main.db.insertToDB(ruangan);
            Main.db.retrieveRuangan();
    
            Main.message(
                "\n\t\t   Ruangan berhasil ditambahkan!",
                "green",
                2000
            );
            
        } catch (Exception e) {
            Main.gotoXY(0, 4);
            Main.message("\n\t\t       Isian tidak valid!", "red", 1200);
        }
    }

    @Override
    public void readRuangan() throws IOException, InterruptedException {
        Main.clear();
        System.out.println(
            "\n                                                               " + Main.color("bold") +
            "\n               -::-   Lihat Data Ruangan   -::-                " + Main.color("reset") +
            "\n                                                               " +
            "\n                              ---                              " +
            "\n\n                                                              "
        );

        for (int i = 0; i < Main.arrRuangan.size(); i++) {
            System.out.printf("\t|--------------------- %-2s ---------------------|\n\n", i+1);
            Main.arrRuangan.get(i).showAtt(this);
            System.out.println("\n");
        }

        System.out.printf("\n\n %60s", "Kembali => ");
        Main.br.readLine();
    }

    public void updateRuangan() throws IOException, InterruptedException{
        boolean running = true;
        while (running) {
            Main.clear();
            System.out.println(
                "\n                                                               " + Main.color("bold") +
                "\n               -::-   Update Data Ruangan   -::-               " + Main.color("reset") +
                "\n                                                               " +
                "\n                              ---                              "
            );
    
            Ruangan.table(Main.arrRuangan);
    
            System.out.print(
                "\n            Masukan Nomor Ruangan yang akan diubah!           " +
                "\n                  (Ketik '0' untuk kembali)                   " +
                "\n                                                              " +
                "\n                           =: "
            );

            int edit;
    
            try {
                edit = Integer.parseInt(Main.br.readLine());

                // keluar menu ubah
                if (edit == 0) {
                    running = false;

                // edit ruangan
                } else if (edit <= Main.arrRuangan.size() || edit > 0) {
                    Ruangan ruanganDiedit = Main.arrRuangan.get(edit - 1);
                    ruanganDiedit.display(this);
        
                    System.out.println("\n\t--------------------- EDIT ---------------------\n");
        
                    try {
                        System.out.print("\t Nama      : ");
                        String nama = Main.br.readLine();
                        
                        System.out.print("\t Harga     : Rp ");
                        int harga = Integer.parseInt(Main.br.readLine());
                        
                        System.out.print("\t Kapasitas : ");
                        int kapasitas = Integer.parseInt(Main.br.readLine());

                        System.out.print("\t Fasilitas : ");
                        String fasilitas = Main.br.readLine();
                        
                        System.out.print("\t Lantai    : ");
                        int lantai = Integer.parseInt(Main.br.readLine());
                        
                        System.out.println("\t (Kamar / Ruang Rapat / Aula / Kolam Renang / Restaurant)");
                        System.out.print("\t Jenis     : ");
                        String jenis = Main.br.readLine();

                        // cek formulir
                        if (Main.isSpace(nama) ||
                        Main.isSpace(fasilitas) ||
                        Main.isSpace(jenis)) {
                            Main.message("\n\t\t       Isian tidak valid!", "red", 1200);
                            continue;
                        }
                        
                        ruanganDiedit.setNama(nama);
                        ruanganDiedit.setHarga(harga);
                        ruanganDiedit.setKapasitas(kapasitas);
                        ruanganDiedit.setFasilitas(fasilitas);
                        ruanganDiedit.setLantai(lantai);
                        ruanganDiedit.setJenis(jenis);
                        Main.db.updateToDB(ruanganDiedit);
                        Main.db.retrieveRuangan();
                        
                        Main.message(
                            "\n\t\t     Ruangan berhasil diubah!",
                            "green",
                            2000
                        );      
                        
                    } catch (Exception e) {
                        Main.message("\n\t\t       Isian tidak valid!", "red", 1200);
                        continue;
                    }
                
                // ruangan tidak ada
                } else {
                    Main.gotoXY(0, 4);
                    Main.message("\n\t\t     Ruangan tidak ditemukan!", "red", 1000);
                }

            } catch (Exception e) {
                Main.gotoXY(0, 4);
                Main.message("\n\t\t     Ruangan tidak ditemukan!", "red", 1000);
            }
        }
        
    }

    public void deleteRuangan() throws IOException, InterruptedException {
        
        boolean running = true;
        while (running) {
            Main.clear();

            System.out.println(
                "\n                                                               " + Main.color("bold") +
                "\n                -::-   Hapus Data Ruangan   -::-               " + Main.color("reset") +
                "\n                                                               " +
                "\n                              ---                              " 
            );
    
            Ruangan.table(Main.arrRuangan);
    
            System.out.print(
                "\n             Masukan Nomor Ruangan yang akan dihapus!         " +
                "\n                   (Ketik '0' untuk kembali)                  " +
                "\n                                                              " +
                "\n                            =: "
            );

            int hapus;
            try {
                hapus = Integer.parseInt(Main.br.readLine());

                // keluar menu hapus
                if (hapus == 0) {
                    running = false;

                // ruangan ditemukan
                } else if (hapus <= Main.arrRuangan.size() || hapus > 0) {
                    Ruangan ruanganDihapus = Main.arrRuangan.get(hapus - 1);
                    ruanganDihapus.display(this);
        
                    System.out.print(
                        "\n\t Ketik '1' untuk menghapus" +
                        "\n\t =: "
                    );
                    String konfirmasiHapus = Main.br.readLine();
        
                    // jika membatalkan hapus
                    if (!konfirmasiHapus.equals("1")) {
                        Main.message(
                            "\n\t\t     Hapus ruangan dibatalkan.",
                            "red",
                            2000
                        );
                        
                        return;
                    }
                    
                    // hapus ruangan dari database
                    Main.db.deleteFromDB(Main.arrRuangan.remove(hapus - 1));
                    Main.db.retrieveRuangan();
                    Main.db.retrievePesanan();
        
                    Main.message(
                        "\n\t\t     Ruangan berhasil dihapus!",
                        "green",
                        2000
                    );
        
                } else {
                    Main.gotoXY(0, 4);
                    Main.message("\n\t\t     Ruangan tidak ditemukan!", "red", 1000);
                }

            } catch (Exception e) {
                Main.gotoXY(0, 4);
                Main.message("\n\t\t     Ruangan tidak ditemukan!", "red", 1000);
            }
    
        }

    } 

    @Override
    public void readPesanan() throws IOException, InterruptedException {
        boolean running = true;
        while (running) {
            Main.clear();
            System.out.println(
                "\n                                                               " + Main.color("bold") +
                "\n             -::-   Riwayat Pesanan Ruangan   -::-             " + Main.color("reset") +
                "\n                                                               " +
                "\n                              ---                              "
            );
            

            Pesanan.table(Main.arrPesanan);

            System.out.print(
                "\n           Pilih nomor untuk melihat detail pesanan!         " +
                "\n                  (Ketik '0' untuk kembali)                  " +
                "\n                                                             " +
                "\n                            =: "
            );

            int pilih;
            try {
                pilih = Integer.parseInt(Main.br.readLine());

                // kembali
                if (pilih == 0) {
                    return;

                // pesanan tidak ada
                } else if (pilih < 0 ||
                    pilih > Main.arrPesanan.size()
                ) {
                    Main.gotoXY(0, 4);
                    Main.message("\n\t\t     Pesanan tidak ditemukan!", "red", 1000);

                // tampilkan detail pesanan
                } else {
                    Main.arrPesanan.get(pilih - 1).display();
                }

            } catch (Exception e) {
                Main.gotoXY(0, 4);
                Main.message("\n\t\t     Pesanan tidak ditemukan!", "red", 1000);
            }
            
        }
    }

    public void readCustomer() throws IOException, InterruptedException {
        boolean running = true;
        while (running) {
            Main.clear();
            System.out.println(
                "\n                                                               " + Main.color("bold") +
                "\n               -::-   Lihat Data Customer   -::-               " + Main.color("reset") +
                "\n                                                               " +
                "\n                              ---                              "
            );

            Customer.table();

            System.out.print(
                "\n           Pilih nomor untuk melihat detail customer!        " +
                "\n                  (Ketik '0' untuk kembali)                  " +
                "\n                                                            " +
                "\n                            =: "
            );

            int pilih;
            try {
                pilih = Integer.parseInt(Main.br.readLine());

                // kembali
                if (pilih == 0) {
                    return;

                    // customer tidak ada
                } else if (pilih < 0 ||
                        pilih > Main.arrCustomer.size()) {
                    Main.gotoXY(0, 4);
                    Main.message("\n\t\t     Customer tidak ditemukan!", "red", 1000);

                    // tampilkan detail customer
                } else {
                    Main.arrCustomer.get(pilih - 1).display(this);
                }

            } catch (Exception e) {
                Main.gotoXY(0, 4);
                Main.message("\n\t\t     Customer tidak ditemukan!", "red", 1000);
            }
        }
    }

}
