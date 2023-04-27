package pa_kelompok2;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.sql.Date;

/*
 * Tema : Sistem Manajemen Reservasi Hotel
 * 
 * KELOMPOK 2
 * -. Aziizah Oki Shofrina  (2109106004)
 * -. Aliya Irfani          (2109106007) 
 * -. Chintia Liu Wintin    (2109106008)
 * -. Muhammad Fikri        (2109106010)
 * 
 */

public class Main {
    
    static final Admin AKUN_ADMIN = new Admin("admin", "123");

    static ArrayList<Customer> arrCustomer = new ArrayList<>();
    static ArrayList<Ruangan> arrRuangan = new ArrayList<>();
    static ArrayList<Pesanan> arrPesanan = new ArrayList<>();

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static Koneksi db = new Koneksi();
    
    // akun yg login
    static User akunLogin;
    
    // RUNNING =====================================================================================

    public static void main(String[] args) throws IOException, InterruptedException{
        // ambil data dari database
        db.connectToDB();

        Pesanan.updateStatusHariIni(db);
        db.retrieveCustomer();
        db.retrieveRuangan();
        db.retrievePesanan();
        
        boolean running = true;

        while (running) {
            clear();      

            System.out.print(
                "\n|||=||=||=||=||=-------------------------------=||=||=||=||=|||" + 
                "\n                                                               " + color("bold") +
                "\n                         RESERVASI HOTEL                       " + color("reset") +
                "\n                                                               " +
                "\n                               -*-                             " +
                "\n                                                               " +
                "\n                       >> 1. Login akun                        " +
                "\n                                                               " +
                "\n                       >> 2. Register akun                     " +
                "\n                                                               " +
                "\n                       >> 0. Keluar                            " +
                "\n                                                               " +
                "\n-:-:-:-:-:-:-:-:-:-:-:-:-:-:-:-:-:-:-:-:-:-:-:-:-:-:-:-:-:-:-:-" +
                "\n                                                               " +
                "\n                             =: "
            );

            int menu;
            try {
                menu = Integer.parseInt(br.readLine()); 
    
                switch(menu) {
                    case 1: login(); break; 
                    case 2: registrasi(); break;
                    case 0:
                        gotoXY(0, 5);
                        message("\n\t\t     Keluar dari program. . .", "yellow", 2000);
                        clear();
                        System.exit(0); break;
    
                    default: 
                        gotoXY(0, 5);
                        message("\n\t\t       Menu tidak tersedia.", "red", 800);
                }
                
            } catch (Exception e) {
                gotoXY(0, 5);
                message("\n\t\t       Menu tidak tersedia.", "red", 800);
            }

        }
    }


    // MENU UTAMA ===========================================================================

    // ADMIN - MENU . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .
    private static void adminMenu() throws IOException, InterruptedException {
        boolean running = true;

        while (running) {
            clear();

            System.out.printf(
                "\n\n   Menu Admin %1$s %2$-22s %3$s %4$s %5$18s %3$s      ",
                color("cyan"), 
                "<" + akunLogin.getUsername() + ">", 
                color("reset"),
                color("purple"), 
                "Admin Mode"
            );

            System.out.print(         
                "\n                                                               " +
                "\n                              ---                              " +
                "\n                                                               " +
                "\n        >> 1. Tambah data             >> 5. Lihat riwayat      " +
                "\n              ruangan                       pemesanan          " +
                "\n                                                               " +
                "\n        >> 2. Lihat data              >> 6. Lihat data         " +
                "\n              ruangan                       customer           " +
                "\n                                                               " +
                "\n        >> 3. Ubah data               >> 0. Log out            " +
                "\n              ruangan                                          " +
                "\n                                                               " +
                "\n        >> 4. Hapus data                                       " +
                "\n              ruangan                                          " +
                "\n                                                               " +
                "\n. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . ." +
                "\n                                                               " +
                "\n                           =: "
            );

            int menu;
            try {
                menu = Integer.parseInt(br.readLine());

                switch (menu) {
                    case 1: ((Admin)akunLogin).createRuangan();  break; 
                    case 2: ((Admin)akunLogin).readRuangan(); break; 
                    case 3: ((Admin)akunLogin).updateRuangan(); break;
                    case 4: ((Admin)akunLogin).deleteRuangan();break;
                    case 5: ((Admin)akunLogin).readPesanan(); break;
                    case 6: ((Admin)akunLogin).readCustomer(); break;
                    case 0: ((Admin)akunLogin).logOut();running = false; break;
                    default:
                        gotoXY(0, 4);
                        message("\n\t\t       Menu tidak tersedia.", "red", 800);
                }

            } catch (Exception e) {
                gotoXY(0, 4);
                message("\n\t\t       Menu tidak tersedia.", "red", 800);
            }
        }
    }

    // CUSTOMER - MENU . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 
    private static void custMenu() throws IOException, InterruptedException {
        boolean running = true;
        while (running) {
            clear();

            System.out.printf(
                "\n\n   Menu Customer %1$s %2$-22s %3$s %4$s %5$18s %3$s",
                color("cyan"),
                "<" + akunLogin.getUsername() + ">",
                color("reset"),
                color("blue"),
                "Customer Mode"
            );

            System.out.print(
                "\n                                                               " +
                "\n                              ---                              " +
                "\n                                                               " +
                "\n        >> 1. Lihat data              >> 4. Lihat profile      " +
                "\n              ruangan                       saya               " +
                "\n                                                               " +
                "\n        >> 2. Buat pesanan            >> 0. Log out            " +
                "\n              ruangan                                                 " +
                "\n                                                               " +
                "\n        >> 3. Lihat riwayat                                    " +
                "\n              pemesanan                                        " +
                "\n                                                               " +
                "\n. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . ." +
                "\n                                                               " +
                "\n                           =: "
            );
                         
            int menu;
            try {
                menu = Integer.parseInt(br.readLine());

                switch (menu) {
                    case 1: ((Customer)akunLogin).readRuangan(); break;
                    case 2: ((Customer)akunLogin).createPesanan(); break;
                    case 3: ((Customer)akunLogin).readPesanan(); break;
                    case 4: ((Customer)akunLogin).display((Customer)akunLogin); break;
                    case 0: ((Customer)akunLogin).logOut(); running = false; break;
                    default:
                        gotoXY(0, 4);
                        message("\n\t\t       Menu tidak tersedia.", "red", 800);
                }

            } catch (Exception e) {
                gotoXY(0, 4);
                message("\n\t\t       Menu tidak tersedia.", "red", 800);
            }
        }

    }

    // CUSTOMER - REGISTRASI . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .
    public static void registrasi() throws IOException, InterruptedException {
        clear();
        System.out.print(
                "\n -:-:-:-:-:-:-:-:-:-:-:-:-:-REGISTRASI-:-:-:-:-:-:-:-:-:-:-:-:-\n\n");
        System.out.print("\tUsername\t: ");
        String username = Main.br.readLine();
        for (Customer cc : arrCustomer) {
            if (username.equals(cc.getUsername()) || username.equals(AKUN_ADMIN.getUsername())) {
                message("\n\t\t    Username sudah terdaftar!", "red", 1000);
                return;
            }
        }
        System.out.print("\tNo. Telepon\t: ");
        String noTelp = Main.br.readLine();
        System.out.print("\tAlamat\t\t: ");
        String alamat = Main.br.readLine();
        System.out.print("\tPassword\t: ");
        String pw = Main.br.readLine();
        System.out.print("\tKonfirmasi Password : ");
        String cek = Main.br.readLine();

        while (!cek.equals(pw)) {
            message("\n\t\t    Konfirmasi Password salah!", "red", 1000);
            System.out.print("\tPassword\t: ");
            pw = Main.br.readLine();
            System.out.print("\tKonfirmasi Password : ");
            cek = Main.br.readLine();
        }

        // cek apakah isian dikosongkan
        if (
            isSpace(username) || isSpace(noTelp) ||
            isSpace(alamat) || isSpace(pw) || 
            noTelp.length() > 13 || !isNumeric(noTelp)
        ) {
            message("\n\t\t         Isian tidak valid!", "red", 1000);
            return;
        }

        String thn, bln, tgl, s = null;
        int tg;
        System.out.print("\n\tTanggal Lahir\n");
        System.out.print("\tTahun\t\t: ");
        thn = br.readLine();
        if (isNumeric(thn)) {
            tg = Integer.parseInt(thn);

            if (tg > 1900) {
                System.out.print("\tBulan\t\t: ");
                bln = br.readLine();

                if (isNumeric(bln)) {
                    tg = Integer.parseInt(bln);
                    if (tg > 0 && tg <= 12) {
                        System.out.print("\tTanggal\t\t: ");
                        tgl = br.readLine();
                    } else {
                        message("\n\t\t   Masukkan tanggal yang valid!", "red", 1000);
                        return;
                    }

                    if (isNumeric(tgl)) {
                        int hari = Integer.parseInt(tgl);
                        switch (tg) {
                            case 1 -> {
                                if (hari > 0 && hari <= 31) {
                                    s = thn + "-" + bln + "-" + tgl;
                                }
                            }
                            case 2 -> {
                                if (hari > 0 && hari <= 28) {
                                    s = thn + "-" + bln + "-" + tgl;
                                }
                            }
                            case 3 -> {
                                if (hari > 0 && hari <= 31) {
                                    s = thn + "-" + bln + "-" + tgl;
                                }
                            }
                            case 4 -> {
                                if (hari > 0 && hari <= 30) {
                                    s = thn + "-" + bln + "-" + tgl;
                                }
                            }
                            case 5 -> {
                                if (hari > 0 && hari <= 31) {
                                    s = thn + "-" + bln + "-" + tgl;
                                }
                            }
                            case 6 -> {
                                if (hari > 0 && hari <= 30) {
                                    s = thn + "-" + bln + "-" + tgl;
                                }
                            }
                            case 7 -> {
                                if (hari > 0 && hari <= 31) {
                                    s = thn + "-" + bln + "-" + tgl;
                                }
                            }
                            case 8 -> {
                                if (hari > 0 && hari <= 31) {
                                    s = thn + "-" + bln + "-" + tgl;
                                }
                            }
                            case 9 -> {
                                if (hari > 0 && hari <= 30) {
                                    s = thn + "-" + bln + "-" + tgl;
                                }
                            }
                            case 10 -> {
                                if (hari > 0 && hari <= 31) {
                                    s = thn + "-" + bln + "-" + tgl;
                                }
                            }
                            case 11 -> {
                                if (hari > 0 && hari <= 30) {
                                    s = thn + "-" + bln + "-" + tgl;
                                }
                            }
                            case 12 -> {
                                if (hari > 0 && hari <= 31) {
                                    s = thn + "-" + bln + "-" + tgl;
                                }
                            }
                            default -> {
                                message("\n\t\t       Masukkan tanggal yang valid!", "red", 1000);
                                return;
                            }
                        }
                    } else {
                        message("\n\t\t   Masukkan tanggal yang valid!", "red", 1000);
                        return;
                    }
                } else {
                    message("\n\t\t   Masukkan tanggal yang valid dalam format angka!", "red", 1000);
                    return;
                }
            } else {
                message("\n\t\t   Masukkan tanggal yang valid!", "red", 1000);
                return;
            }
        } else {
            message("\n\t\t   Masukkan tanggal yang valid!", "red", 1000);
            return;
        }

        Date d = Date.valueOf(s);
        Customer c = new Customer(username, pw, d, noTelp, alamat);
        arrCustomer.add(c);
        db.insertToDB(c);
        db.retrieveCustomer();
        message("\n\t\t Registrasi Berhasil, Silahkan Login!", "green", 1000);
    }

    // LOGIN . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .
    private static void login() throws IOException, InterruptedException {
        clear();
        System.out.print(
                "\n -:-:-:-:-:-:-:-:-:-:-:-:-:-LOGIN-:-:-:-:-:-:-:-:-:-:-:-:-\n\n");
        System.out.print("    Username\t: ");
        String nama = br.readLine();
        String p;
        if (nama.equals(AKUN_ADMIN.getUsername())) {
            System.out.print("    Password\t: ");
            p = br.readLine();
            if (p.equals(AKUN_ADMIN.getPassword())) {
                message("\n\t\t     Selamat Datang Admin!", "green", 1000);
                AKUN_ADMIN.logIn();
                adminMenu();
                return;
            } else {
                message("\n\t\t        Password Salah!", "red", 1000);
                return;
            }
        }

        for (Customer cc : arrCustomer) {
            if (nama.equals(cc.getUsername())) {
                System.out.print("    Password\t: ");
                p = br.readLine();
                if (p.equals(cc.getPassword())) {
                    message("\n\t\t     Selamat Datang " + nama + "!", "green", 1000);
                    cc.logIn();
                    custMenu();
                    return;
                } else {
                    message("\n\t\t        Password Salah!", "red", 1000);
                    return;
                }
            }
        }
        message("\n\t\t   Username belum terdaftar!", "red", 1000);
        return;
    }


    // USER DEFINED FUNCTION =========================================================================
    
    public static void clear() throws IOException, InterruptedException {
        if (System.console() != null) {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } else {
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        }
    }

    public static void gotoXY(int x, int y) {
        char escCode = 0x1B;
        System.out.print(String.format("%c[%d;%df", escCode, y, x));
    }

    public static String color(String color) {
        String colorCode = switch (color) {
            case "black" -> "\u001B[30m";
            case "red" -> "\u001B[31m";
            case "green" -> "\u001B[32m";
            case "yellow" -> "\u001B[33m";
            case "cyan" -> "\u001B[36m";
            case "blue" -> "\u001B[34m";
            case "purple" -> "\u001B[35m";
            case "bold" -> "\033[1;97m";
            case "underline" -> "\033[4;37m";
            case "reset" -> "\u001B[0m";
            default -> "\u001B[0m";
        };
        return colorCode;
    }

    public static void message(String msg, String color, int timer) throws InterruptedException {
        System.out.println(color(color) + msg + "\u001B[0m");
        Thread.sleep(timer);
    }

    public static boolean isSpace(String str) {
        int space = 0;

        if (str.length() == 0) {
            return true;
        }

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ') {
                space++;
            }
        }

        if (space == str.length()) {
            return true;
        }

        return false;
    }

    public static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        try {
            for (char angka: str.toCharArray()) {
                Integer.parseInt("" + angka);    
            }
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}

