package pa_kelompok2;

import java.sql.Date;

public abstract class Pemesanan {
    
    protected String id;
    protected Date tglPesan;
    protected Date tglCheckIn;
    protected Date tglCheckOut;
    protected Ruangan ruanganDipesan;
    protected String namaTamu;
    protected Customer akunPemesan;
    protected String status;

    public Pemesanan(String id, Date tglPesan, Date tglCheckIn, Date tglCheckOut, 
            Ruangan ruanganDipesan, String namaTamu, Customer akunPemesan, String status) {
        this.id = id;
        this.tglPesan = tglPesan;
        this.tglCheckIn = tglCheckIn;
        this.tglCheckOut = tglCheckOut;
        this.ruanganDipesan = ruanganDipesan;
        this.namaTamu = namaTamu;
        this.akunPemesan = akunPemesan;
        this.status = status;
    }

    public Pemesanan() {}

    // GETTER & SETTER ==============================================================================

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getTglPesan() {
        return tglPesan;
    }

    public void setTglPesan(Date tglPesan) {
        this.tglPesan = tglPesan;
    }

    public Date getTglCheckIn() {
        return tglCheckIn;
    }

    public void setTglCheckIn(Date tglCheckIn) {
        this.tglCheckIn = tglCheckIn;
    }

    public Date getTglCheckOut() {
        return tglCheckOut;
    }

    public void setTglCheckOut(Date tglCheckOut) {
        this.tglCheckOut = tglCheckOut;
    }

    public Ruangan getRuanganDipesan() {
        return ruanganDipesan;
    }

    public void setRuanganDipesan(Ruangan ruanganDipesan) {
        this.ruanganDipesan = ruanganDipesan;
    }

    public String getNamaTamu() {
        return namaTamu;
    }

    public void setNamaTamu(String namaTamu) {
        this.namaTamu = namaTamu;
    }

    public Customer getAkunPemesan() {
        return akunPemesan;
    }

    public void setAkunPemesan(Customer akunPemesan) {
        this.akunPemesan = akunPemesan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
