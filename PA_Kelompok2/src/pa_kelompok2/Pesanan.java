package pa_kelompok2;

import java.io.IOException;
import java.util.ArrayList;
import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class Pesanan extends Pemesanan {

    public Pesanan(String id, Date tglPesan, Date tglCheckIn, Date tglCheckOut, 
            Ruangan ruanganDipesan, String namaTamu, Customer akunPemesan, String status) { 
        super(id, tglPesan, tglCheckIn, tglCheckOut, ruanganDipesan, namaTamu, akunPemesan, status);
        createID();
    }

    public Pesanan() {}

    // METHOD ========================================================================================

    private void createID() {
        String ruangan, tgl;
        int no;

        ruangan = switch (this.ruanganDipesan.getJenis().toLowerCase()) {
            case "kamar" -> "km";
            case "ruang rapat" -> "rp";
            case "aula" -> "au";
            case "kolam renang" -> "kr";
            case "restaurant" -> "rs";
            default -> "xx";
        };
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMyy");
        LocalDate now = LocalDate.now();
        tgl = dtf.format(now);

        ArrayList<Pesanan> pesananHariIni = Main.db.selectPesanan(
            "SELECT * FROM pesanan " +
            "WHERE id LIKE '%" + tgl + "%'"
        );
        no = pesananHariIni.size() + 1;

        this.id = ruangan + tgl + no;
    }

    public void display() throws IOException, InterruptedException {
        Main.clear();
        System.out.println(
            "\n                                                                 " + Main.color("bold") +
            "\n                    Detail Pemesanan Ruangan                     " + Main.color("reset") +
            "\n                                                                 " +
            "\n. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .");

        System.out.printf(
            "\n\t ID Pesanan %1$10s %2$25s\n",
            this.id, this.tglPesan
        );

        System.out.println(
            "\n\t Check In      : " + this.tglCheckIn +
            "\n\t Check Out     : " + this.tglCheckOut +
            "\n\t Ruangan       : " + this.ruanganDipesan.getNama() +
            "\n\t Jenis Ruangan : " + this.ruanganDipesan.getJenis() +
            "\n\t Nama Tamu     : " + this.namaTamu + 
            "\n\t Customer      : " + this.akunPemesan.getUsername()
        );

        System.out.printf("\n\n %60s", "Kembali => ");
        Main.br.readLine();
    }

    public static void table(ArrayList<Pesanan> arr) throws InterruptedException {
        System.out.println(
            "\n       +----+-------------+------------+--------------+ " + 
            "\n       | No |   Tanggal   | ID Pesanan |    Status    | " +
            "\n       +----+-------------+------------+--------------+ "
        );

        for (int i = 0; i < arr.size(); i++) {
            String status, c;
            status = arr.get(i).getStatus();
            
            c = switch (status) {
                case "Definite" -> Main.color("yellow");
                case "In-House" -> Main.color("green");
                case "Check Out" -> Main.color("red");
                default -> Main.color("reset");
            };
            
            System.out.printf(
                "       | %1$-2s | %2$-11s | %3$-10s | %4$-21s | \n",
                i + 1,
                arr.get(i).getTglPesan(),
                arr.get(i).getId(),
                c + status + Main.color("reset")
            );
        }

        System.out.println(
            "       +----+-------------+------------+--------------+"
        );
    }

    public static void updateStatusHariIni(Koneksi db) {

        // update status ruangan saat ini
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate now = LocalDate.now();
        String tglSekarang = dtf.format(now);

        db.updateToDB(
            "UPDATE pesanan SET " +
            "status = 'In-House' " +
            "WHERE tglCheckIn <= '" + tglSekarang + "' " +
            "AND tglCheckOut >= '" + tglSekarang + "' "
        );

        db.updateToDB(
            "UPDATE ruangan SET " +
            "status = 'Penuh' " +
            "WHERE id IN (" +
            "SELECT idRuangan FROM pesanan " +
            "WHERE status = 'In-House'" +
            ")"
        );

        db.updateToDB(
            "UPDATE pesanan SET " +
            "status = 'Check Out' " +
            "WHERE tglCheckOut < '" + tglSekarang + "' " +
            "AND status != 'Check Out'"
        );

        db.updateToDB(
            "UPDATE ruangan SET " +
            "status = 'Tersedia' " +
            "WHERE id NOT IN (" +
            "SELECT idRuangan FROM pesanan " +
            "WHERE status = 'In-House'" +
            ")"
        );

    }

}
