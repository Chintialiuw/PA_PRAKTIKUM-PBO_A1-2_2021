package pa_kelompok2;

import java.io.IOException;
import java.util.ArrayList;

public class Ruangan extends RuanganHotel{

    public Ruangan(int id, String nama, int harga, int kapasitas, String fasilitas, int lantai, String jenis, String status) {
        super(id, nama, harga, kapasitas, fasilitas, lantai, jenis, status);
        createID();
    }

    public Ruangan() {}

    // METHOD ========================================================================================

    public void createID() {
        if (Main.arrRuangan.isEmpty()) {
            this.id = 1;
            return;
        }

        int idTerakhir = Main.arrRuangan.get(Main.arrRuangan.size()-1).getId();
        this.id = idTerakhir + 1;
    }

    public void showAtt(Admin akun) {
        System.out.println("\t ID        : " + this.getId());
        System.out.println("\t Nama      : " + this.getNama());
        System.out.println("\t Harga     : Rp " + this.getHarga());
        System.out.println("\t Kapasitas : " + this.getKapasitas());
        System.out.println("\t Fasilitas : " + this.getFasilitas());
        System.out.println("\t Lantai    : " + this.getLantai());
        System.out.println("\t Jenis     : " + this.getJenis());

        String c = switch (this.getStatus()) {
            case "Tersedia" -> Main.color("green");
            case "Penuh" -> Main.color("red");
            default -> Main.color("reset");
        };

        System.out.println("\t Saat ini  : " + c + this.getStatus() + Main.color("reset"));
    }

    public void showAtt(Customer akun) {
        System.out.println("\t Nama      : " + this.getNama());
        System.out.println("\t Harga     : Rp " + this.getHarga());
        System.out.println("\t Kapasitas : " + this.getKapasitas() + " orang");
        System.out.println("\t Fasilitas : " + this.getFasilitas());
        System.out.println("\t Lantai    : " + this.getLantai());
        System.out.println("\t Jenis     : " + this.getJenis());
    }

    public void display(User akun) throws IOException, InterruptedException{
        Main.clear();
        System.out.println(
            "\n                                                                 " + Main.color("bold") +
            "\n                         Detail Ruangan                          " + Main.color("reset") +
            "\n                                                                 " +
            "\n. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . ." +
            "\n                                                                 " 
        );

        if (akun.getClass() == Admin.class) {
            showAtt((Admin)akun);
        } else {
            showAtt((Customer)akun);
        }
    }

    public static void table(ArrayList<Ruangan> arr) {
        System.out.println(
                "\n +----+--------------------------+---------------+------------+ " + 
                "\n | No |       Nama Ruangan       | Jenis Ruangan | Harga (Rp) | " +
                "\n +----+--------------------------+---------------+------------+ "  
        );

        for (int i=0; i<arr.size(); i++) {
            System.out.printf(
                " | %1$-2s | %2$-24s | %3$-13s | %4$-11s| \n",
                i + 1,
                arr.get(i).getNama(),
                arr.get(i).getJenis(),
                arr.get(i).getHarga()
            );            
        }

        System.out.println(
            " +----+--------------------------+---------------+------------+ "
        );
    }

}
