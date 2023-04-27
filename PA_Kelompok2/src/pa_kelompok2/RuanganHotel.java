package pa_kelompok2;

public abstract class RuanganHotel {
    protected int id;
    protected String nama;
    protected int harga;
    protected int kapasitas;
    protected String fasilitas;
    protected int lantai;
    protected String jenis;
    protected String status; // status saat ini sedang dibooking atau tersedia

    public RuanganHotel(int id, String nama, int harga, int kapasitas, String fasilitas, int lantai, String jenis, String status) {
        this.id = id;
        this.nama = nama;
        this.harga = harga;
        this.kapasitas = kapasitas;
        this.fasilitas = fasilitas;
        this.lantai = lantai;
        this.jenis = jenis;
        this.status = status;
    }

    public RuanganHotel() {}

    // GETTER & SETTER ==============================================================================

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int getKapasitas() {
        return kapasitas;
    }

    public void setKapasitas(int kapasitas) {
        this.kapasitas = kapasitas;
    }

    public String getFasilitas() {
        return fasilitas;
    }

    public void setFasilitas(String fasilitas) {
        this.fasilitas = fasilitas;
    }

    public int getLantai() {
        return lantai;
    }

    public void setLantai(int lantai) {
        this.lantai = lantai;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
}
