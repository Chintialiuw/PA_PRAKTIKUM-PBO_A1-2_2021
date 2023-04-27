package pa_kelompok2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Koneksi {
    private Connection conn;
    private final String URL = "jdbc:mysql://localhost:3306/reservasi_hotel";
    private final String USERNAME = "root";
    private final String PASSWORD = "";

    public Koneksi() {}

    public void connectToDB() {
        try {
            this.conn = DriverManager.getConnection(this.URL, this.USERNAME, this.PASSWORD);
            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void closeConnection() {
        try {
            this.conn.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }  

    // ambil data dari database -----------------------------------------------------------------

    public ArrayList<Customer> selectCustomer(String query) {
        ArrayList<Customer> arrSelected = new ArrayList<>();

        try {
            Statement st = this.conn.createStatement();
            ResultSet result = st.executeQuery(query);

            while (result.next()) {
                Customer obj = new Customer();

                obj.setUsername(result.getString("username"));
                obj.setPassword(result.getString("password"));
                obj.setTglLahir(result.getDate("tglLahir"));
                obj.setNoTelp(result.getString("noTelp"));
                obj.setAlamat(result.getString("alamat"));

                Main.arrCustomer.add(obj);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return arrSelected;
    }

    public ArrayList<Ruangan> selectRuangan(String query) {
        ArrayList<Ruangan> arrSelected = new ArrayList<>();

        try {
            Statement st = this.conn.createStatement();
            ResultSet result = st.executeQuery(query);

            while (result.next()) {
                Ruangan obj = new Ruangan();
                obj.setId(result.getInt("id"));
                obj.setNama(result.getString("nama"));
                obj.setHarga(result.getInt("harga"));
                obj.setKapasitas(result.getInt("kapasitas"));
                obj.setFasilitas(result.getString("fasilitas"));
                obj.setLantai(result.getInt("lantai"));
                obj.setJenis(result.getString("jenis"));
                obj.setStatus(result.getString("status"));

                arrSelected.add(obj);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        return arrSelected;
    }

    public ArrayList<Pesanan> selectPesanan(String query) {
        ArrayList<Pesanan> arrSelected = new ArrayList<>();

        try {
            Statement st = this.conn.createStatement();
            ResultSet result = st.executeQuery(query);

            while (result.next()) {
                Pesanan obj = new Pesanan();
                obj.setId(result.getString("id"));
                obj.setTglPesan(result.getDate("tglPesan"));
                obj.setTglCheckIn(result.getDate("tglCheckIn"));
                obj.setTglCheckOut(result.getDate("tglCheckOut"));

                int idRuangan = result.getInt("idRuangan");
                for (Ruangan r : Main.arrRuangan) {
                    if (r.id == idRuangan) {
                        obj.setRuanganDipesan(r);
                        break;
                    }
                }

                obj.setNamaTamu(result.getString("namaTamu"));

                String username = result.getString("username");
                for (Customer cust : Main.arrCustomer) {
                    if (cust.getUsername().equals(username)) {
                        obj.setAkunPemesan(cust);
                        break;
                    }
                }
                obj.setStatus(result.getString("status"));

                arrSelected.add(obj);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        return arrSelected;
    }

    // samakan arraylist dengan database --------------------------------------------------------

    public void retrieveCustomer() {
        Main.arrCustomer.clear();
        Main.arrCustomer.addAll(selectCustomer("SELECT * FROM customer"));
    }
    
    public void retrieveRuangan() {
        Main.arrRuangan.clear();
        Main.arrRuangan.addAll(selectRuangan("SELECT * FROM ruangan"));
    }

    public void retrievePesanan() {
        Main.arrPesanan.clear();
        Main.arrPesanan.addAll(
            selectPesanan(
                "SELECT * FROM pesanan " +
                "ORDER BY tglPesan DESC"
            )
        );
    }

    // masukkan data ke database ----------------------------------------------------------------

    public void insertToDB(Customer obj) {
        String query = "INSERT INTO customer VALUES ('" +
                        obj.getUsername() + "','" +
                        obj.getPassword() + "','" +
                        obj.getTglLahir() + "','" +
                        obj.getNoTelp()   + "','" +
                        obj.getAlamat()   + "')";
        try {
            Statement st = this.conn.createStatement();
            st.executeUpdate(query);

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void insertToDB(Ruangan obj) {
        String query = "INSERT INTO ruangan VALUES ('" +
                        obj.getId()        + "','" +
                        obj.getNama()      + "','" +
                        obj.getHarga()     + "','" +
                        obj.getKapasitas() + "','" +
                        obj.getFasilitas() + "','" +
                        obj.getLantai()    + "','" +
                        obj.getJenis()     + "','" +
                        obj.getStatus()    + "')";
        try {
            Statement st = this.conn.createStatement();
            st.executeUpdate(query);

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void insertToDB(Pesanan obj) {
        String query = "INSERT INTO pesanan VALUES ('" +
                        obj.getId()                      + "','" +
                        obj.getTglPesan()                + "','" +
                        obj.getTglCheckIn()              + "','" +
                        obj.getTglCheckOut()             + "','" +
                        obj.getRuanganDipesan().getId()  + "','" +
                        obj.getNamaTamu()             + "','" +
                        obj.getAkunPemesan().getUsername() + "','" +
                        obj.getStatus() + "')";

        try {
            Statement st = this.conn.createStatement();
            st.executeUpdate(query);

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    // update data di database ----------------------------------------------------------------

    public void updateToDB(Ruangan obj) {
        String query = "UPDATE ruangan SET " +
                        "nama='"      + obj.getNama()      + "'," +
                        "harga='"     + obj.getHarga()     + "'," +
                        "kapasitas='" + obj.getKapasitas() + "'," +
                        "fasilitas='" + obj.getFasilitas() + "'," +
                        "lantai='"    + obj.getLantai()    + "'," +
                        "jenis='"     + obj.getJenis()     + "'," +
                        "status='"    + obj.getStatus()    + "' "  +
                        "WHERE id='"  + obj.getId()        + "'";
        try {
            Statement st = this.conn.createStatement();
            st.executeUpdate(query);

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void updateToDB(String query) {
        try {
            Statement st = this.conn.createStatement();
            st.executeUpdate(query);

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    // hapus data di database ----------------------------------------------------------------
    public void deleteFromDB(Ruangan obj) {
        String query = "DELETE FROM ruangan " +
                       "WHERE id='" + obj.getId() + "'";
        try {
            Statement st = this.conn.createStatement();
            st.executeUpdate(query);

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

}
