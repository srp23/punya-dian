package Main;

import Alert.logout;
import Helper.ViewController;
import MainExtend.ShowBahanKeluar;
import MainExtend.ShowBahanRequest;
import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Gudang extends javax.swing.JFrame {

    ImageIcon sucess = new ImageIcon(getClass().getResource("/Icon/checked.png"));
    ImageIcon invalid = new ImageIcon(getClass().getResource("/Icon/cancel.png"));
    ImageIcon warning = new ImageIcon(getClass().getResource("/Icon/warning.png"));
    PreparedStatement ps = null;
    ResultSet rs = null;
    private DefaultTableModel tablemasukbahan;
    private DefaultTableModel tablemasukProduk;
    private DefaultTableModel tableitem_produk;
    private DefaultTableModel tableListKirim;
    private DefaultTableModel tableItemKirim;
    private DefaultTableModel tableItemKeluar;
    private DefaultTableModel tableKeluarBahan;
    private DefaultTableModel tablestokbahan;
    private DefaultTableModel tablestokproduk;
    ViewController jpload = new ViewController();
    
    public String ex_id_bahan, ex_nama_bahan, ex_satuan_bahan, ex_stok_bahan;
    public String ex_sbr_id, ex_sbr_suppid, ex_sbr_suppnama, ex_sbr_jenis, ex_sbr_total;

    public Gudang() {
        initComponents();
        ClearMasukBahan();
        runTable();
        KeluarID();
        TglSekarang();
        lbl_nama.setText(Login1.getnama());
        
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        this.setMaximizedBounds(env.getMaximumWindowBounds());
        this.setVisible(true);
    }
    
    protected void runTable(){
        TableMasukProduk();
        TableListKirim();
        TableKeluarBahan();
        TablestokBahan();
        TablestokProduk();
    }
    
    private void TglSekarang() {
        Date date = new Date();
        String myFormat = "EEEE, dd MMMM yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
        gudang_tgl_global.setText(sdf.format(date));
    }

    protected void menuGudang() {
        gkeluar_bahan.setVisible(true);
        gkeluar_barang.setVisible(true);
        gmasuk_bahan.setVisible(true);
        gmasuk_produk.setVisible(true);
    }

    protected void menuProduksi() {
        gkeluar_bahan.setVisible(false);
        gkeluar_barang.setVisible(false);
        gmasuk_bahan.setVisible(false);
        gmasuk_produk.setVisible(false);

    }

    public void itemTerpilihSBR() {
        ShowBahanRequest sbr = new ShowBahanRequest();
        sbr.bgbelakang = this;
        masuk_bahan_id.setText(ex_sbr_id);
        masuk_bahan_supid.setText(ex_sbr_suppid);
        masuk_bahan_supnama.setText(ex_sbr_suppnama);
        masuk_bahan_jenis.setText(ex_sbr_jenis);
        masuk_bahan_jumlah.setText(ex_sbr_total);
    }
    
    public void itemTerpilihBahan(){
        ShowBahanKeluar sbk = new ShowBahanKeluar();
        sbk.gdBahan = this;
        keluar_bahan_id.setText(ex_id_bahan);
        keluar_bahan_nama.setText(ex_nama_bahan);
        keluar_bahan_satuan.setText(ex_satuan_bahan);
    }

    protected void ClearMasukBahan() {
        masuk_bahan_id.setText("");
        masuk_bahan_supid.setText("");
        masuk_bahan_supnama.setText("");
        masuk_bahan_jenis.setText("");
        masuk_bahan_jumlah.setText("");
        ((DefaultTableModel) masuk_bahan_tabel.getModel()).setNumRows(0);
        masuk_bahan_simpan.setVisible(false);
        masuk_bahan_batal.setVisible(false);
        TableMasukBahan();
    }

    protected void ClearMasukProduk() {
        mp_jenis_produk.setText("");
        mp_jml_masuk.setText("");
        mp_jml_order.setText("");
        mp_nama_produk.setText("");
        mp_order_id.setText("");
        mp_produk_id.setText("");
        mp_order_id.setEnabled(true);
        mp_produk_id.setEnabled(true);
        mp_nama_produk.setEnabled(true);
        mp_jenis_produk.setEnabled(true);
        mp_jml_order.setEnabled(true);
        TableMasukProduk();
    }
    
    protected void ClearKeluarProduk(){
        gAlamat.setText("");
        gHarga.setText("");
        gInv.setText("");
        gItem.setText("");
        gNama.setText("");
        gTelp.setText("");
        ((DefaultTableModel) gTableItem.getModel()).setNumRows(0);
        TableListKirim();
    }
    
    protected void ClearKeluarBahan(){
        keluar_bahan_id.setText("");
        keluar_bahan_nama.setText("");
        keluar_bahan_qty.setText("");
        keluar_bahan_satuan.setText("");
        keluar_nama.setText("");
        ((DefaultTableModel) keluar_tb_item.getModel()).setNumRows(0);
    }
    
    protected void KeluarID() {
        try {
            String query = "select MAX(RIGHT(no,8)) AS NO  from keluar_bahan order by no desc";
            ps = Config.config.getConnection().prepareStatement(query);
            rs = ps.executeQuery(query);
            while (rs.next()) {
                if (rs.first() == false) {
                    keluar_id.setText("NOD10000001");
                } else {
                    rs.last();
                    int auto_id = rs.getInt(1) + 1;
                    String no = String.valueOf(auto_id);
                    int Nomor = no.length();
                    //MENGATUR jumlah 0
                    for (int j = 0; j < 8 - Nomor; j++) {
                        no = "0" + no;
                    }
                    keluar_id.setText("NOD" + no);
                }
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();//penanganan masalah
        }
    }

    protected void TableMasukBahan() {
        Object[] Baris = {"ID", "Supplier ID", "Supplier Nama", "Bahan ID", "Bahan Nama", "Qty", "Satuan"};
        tablemasukbahan = new DefaultTableModel(null, Baris);

        try {
            String sql = "SELECT * FROM item_po_bahan where id_order_bahan='" + masuk_bahan_id.getText() + "'";
            Statement stat = Config.config.getConnection().createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()) {
                tablemasukbahan.addRow(new Object[]{
                    hasil.getString(1),
                    hasil.getString(2),
                    hasil.getString(3),
                    hasil.getString(4),
                    hasil.getString(5),
                    hasil.getString(6),
                    hasil.getString(7)
                });
            }
            masuk_bahan_tabel.setModel(tablemasukbahan);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal Memuat: " + e);
        }
    }
    
     protected void TableKeluarBahan() {
        Object[] Baris = {"No.Faktur", "Penerima", "Tanggal", "Jumlah Item", "Total Qty"};
        tableKeluarBahan = new DefaultTableModel(null, Baris);

        try {
            String sql = "SELECT * FROM keluar_bahan order by tanggal desc";
            Statement stat = Config.config.getConnection().createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()) {
                tableKeluarBahan.addRow(new Object[]{
                    hasil.getString(1),
                    hasil.getString(2),
                    hasil.getString(3),
                    hasil.getString(4),
                    hasil.getString(5)
                });
            }
            keluar_tb_data.setModel(tableKeluarBahan);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal Memuat: " + e);
        }
    }
    
    protected void TableListKirim() {
        Object[] Baris = {"Invoice", "Nama", "Telp", "Alamat", "Tanggal", "Item", "Harga"};
        tableListKirim = new DefaultTableModel(null, Baris);

        try {
            String sql = "SELECT id, nama_customer, telp, alamat, tanggal, qty_item, harga FROM kirim_barang where status='Request'";
            Statement stat = Config.config.getConnection().createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()) {
                tableListKirim.addRow(new Object[]{
                    hasil.getString(1),
                    hasil.getString(2),
                    hasil.getString(3),
                    hasil.getString(4),
                    hasil.getString(5),
                    hasil.getString(6),
                    hasil.getString(7)
                });
            }
            gTableList.setModel(tableListKirim);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal Memuat: " + e);
        }

    }
    
    protected void TableItemKirim() {
        Object[] Baris = {"Invoice", "Produk ID", "Produk Nama", "Harga", "Qty", "Total Harga"};
        tableItemKirim = new DefaultTableModel(null, Baris);

        try {
            String sql = "SELECT * FROM item_kirim_barang where id_kirim_barang='" + gInv.getText() + "'";
            Statement stat = Config.config.getConnection().createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()) {
                tableItemKirim.addRow(new Object[]{
                    hasil.getString(1),
                    hasil.getString(2),
                    hasil.getString(3),
                    hasil.getString(4),
                    hasil.getString(5),
                    hasil.getString(6)
                });
            }
            gTableItem.setModel(tableItemKirim);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal Memuat: " + e);
        }

    }

    protected void TableMasukProduk() {
        Object[] Baris = {"ID", "Produk ID", "Produk Nama", "Jenis", "Jumlah"};
        tablemasukProduk = new DefaultTableModel(null, Baris);

        try {
            String sql = "SELECT id,id_produk,nama_produk,jenis_produk,qty FROM order_produk where status='Di Kirim'";
            Statement stat = Config.config.getConnection().createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()) {
                tablemasukProduk.addRow(new Object[]{
                    hasil.getString(1),
                    hasil.getString(2),
                    hasil.getString(3),
                    hasil.getString(4),
                    hasil.getString(5)
                });
            }
            mp_tbl_list.setModel(tablemasukProduk);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal Memuat: " + e);
        }

    }
    
    protected void TablestokBahan() {
        Object[] Baris = {"ID", "Nama Bahan", "Jenis Bahan", "Satuan", "Jumlah"};
        tablestokbahan = new DefaultTableModel(null, Baris);

        try {
            String sql = "SELECT * FROM bahan order by id asc";
            Statement stat = Config.config.getConnection().createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()) {
                tablestokbahan.addRow(new Object[]{
                    hasil.getString(1),
                    hasil.getString(2),
                    hasil.getString(3),
                    hasil.getString(4),
                    hasil.getString(5)
                });
            }
            masuk_bhn_stok.setModel(tablestokbahan);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal Memuat: " + e);
        }
    }
    
    protected void TablestokProduk() {
        Object[] Baris = {"ID", "Nama", "Kategori", "Jenis", "Harga", "Satuan", "Jumlah"};
        tablestokproduk = new DefaultTableModel(null, Baris);

        try {
            String sql = "SELECT * FROM produk order by id asc";
            Statement stat = Config.config.getConnection().createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()) {
                tablestokproduk.addRow(new Object[]{
                    hasil.getString(1),
                    hasil.getString(2),
                    hasil.getString(3),
                    hasil.getString(4),
                    hasil.getString(5),
                    hasil.getString(6),
                    hasil.getString(7)
                });
            }
            mp_tbl_item.setModel(tablestokproduk);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal Memuat: " + e);
        }
    }

    protected void UpdateMasukBahan() {
        try {
            String sql = "update order_bahan set status=? where id='" + masuk_bahan_id.getText() + "'";
            PreparedStatement stat = Config.config.getConnection().prepareStatement(sql);
            stat.setString(1, masuk_bahan_status.getSelectedItem().toString());
            stat.executeUpdate();
            ClearMasukBahan();
            JOptionPane.showMessageDialog(null, "Data Berhasil Di Simpan", "Berhasil!", HEIGHT, sucess);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan!", "Perhatian!", HEIGHT, invalid);
        }
    }

    protected void SimpanMasukBahan() {
        try {
            String query2 = "INSERT INTO `masuk_bahan`(`id_bahan`, `nama`, `qty`, `satuan`) VALUES (?,?,?,?)";
            int t = masuk_bahan_tabel.getRowCount();
            for (int i = 0; i < t; i++) {
                String idbahan = masuk_bahan_tabel.getValueAt(i, 3).toString();
                String namabahan = masuk_bahan_tabel.getValueAt(i, 4).toString();
                String jumlah = masuk_bahan_tabel.getValueAt(i, 5).toString();
                String satuan = masuk_bahan_tabel.getValueAt(i, 6).toString();

                PreparedStatement stat2 = Config.config.getConnection().prepareStatement(query2);
                stat2.setString(1, idbahan);
                stat2.setString(2, namabahan);
                stat2.setString(3, jumlah);
                stat2.setString(4, satuan);
                stat2.executeUpdate();
            }
            UpdateMasukBahan();
            ClearMasukBahan();
            runTable();
//            JOptionPane.showMessageDialog(null, "Data Berhasil Di Simpan", "Berhasil!", HEIGHT, sucess);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan!", "Perhatian!", HEIGHT, invalid);
        }
    }
    
    protected void SimpanKeluarProduk() {
        try {
            String query2 = "INSERT INTO `keluar_produk`(`inv`, `id_produk`, `nama`, `qty`) VALUES (?,?,?,?)";
            int t = gTableItem.getRowCount();
            for (int i = 0; i < t; i++) {
                String idbahan = gTableItem.getValueAt(i, 0).toString();
                String namabahan = gTableItem.getValueAt(i, 1).toString();
                String jumlah = gTableItem.getValueAt(i, 2).toString();
                String satuan = gTableItem.getValueAt(i, 4).toString();

                PreparedStatement stat2 = Config.config.getConnection().prepareStatement(query2);
                stat2.setString(1, idbahan);
                stat2.setString(2, namabahan);
                stat2.setString(3, jumlah);
                stat2.setString(4, satuan);
                stat2.executeUpdate();
            }
            UpdateStatusKirim();
            ClearKeluarProduk();
            runTable();
//            JOptionPane.showMessageDialog(null, "Data Berhasil Di Simpan", "Berhasil!", HEIGHT, sucess);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan!", "Perhatian!", HEIGHT, invalid);
        }
    }
    
    protected void SimpanKeluarItem() {
        try {
            String query2 = "INSERT INTO `keluar_bahan_item`(`no_faktur`, `id_bahan`, `nama`, `qty`, `satuan`) VALUES (?,?,?,?,?)";
            int t = keluar_tb_item.getRowCount();
            for (int i = 0; i < t; i++) {
                String nofaktur = keluar_tb_item.getValueAt(i, 0).toString();
                String idbahan = keluar_tb_item.getValueAt(i, 1).toString();
                String nama = keluar_tb_item.getValueAt(i, 2).toString();
                String qty = keluar_tb_item.getValueAt(i, 3).toString();
                String satuan = keluar_tb_item.getValueAt(i, 4).toString();

                PreparedStatement stat2 = Config.config.getConnection().prepareStatement(query2);
                stat2.setString(1, nofaktur);
                stat2.setString(2, idbahan);
                stat2.setString(3, nama);
                stat2.setString(4, qty);
                stat2.setString(5, satuan);
                stat2.executeUpdate();
            }
            UpdateStatusKirim();
            ClearKeluarProduk();
            runTable();
//            JOptionPane.showMessageDialog(null, "Data Berhasil Di Simpan", "Berhasil!", HEIGHT, sucess);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan!", "Perhatian!", HEIGHT, invalid);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        head = new javax.swing.JPanel();
        profile = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        lbl_nama = new javax.swing.JLabel();
        txt_menu_gudang = new javax.swing.JLabel();
        gudang_tgl_global = new javax.swing.JLabel();
        side = new javax.swing.JPanel();
        gmasuk_bahan = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        gmasuk_produk = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        gkeluar_bahan = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        gkeluar_barang = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        body = new javax.swing.JPanel();
        Utama = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        masuk_bahan = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        masuk_bahan_tabel = new javax.swing.JTable();
        masuk_bahan_batal = new javax.swing.JButton();
        masuk_bahan_simpan = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        masuk_bahan_id = new javax.swing.JTextField();
        masuk_bahan_cari = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        masuk_bahan_supid = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        masuk_bahan_supnama = new javax.swing.JTextField();
        masuk_bahan_jenis = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        masuk_bahan_jumlah = new javax.swing.JTextField();
        masuk_bahan_status = new javax.swing.JComboBox<>();
        jLabel26 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        masuk_bhn_stok = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        masuk_produk = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        mp_tbl_list = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        mp_tbl_item = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        mp_order_id = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        mp_produk_id = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        mp_nama_produk = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        mp_jenis_produk = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        mp_jml_order = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        mp_jml_masuk = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        mp_tgl_masuk = new com.toedter.calendar.JDateChooser();
        mp_bt_simpan = new javax.swing.JButton();
        mp_bt_batal = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        keluar_bahan = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        keluar_tb_item = new javax.swing.JTable();
        jLabel30 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        keluar_id = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        keluar_nama = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        keluar_bahan_id = new javax.swing.JTextField();
        keluar_bt_cari = new javax.swing.JButton();
        jLabel36 = new javax.swing.JLabel();
        keluar_bahan_nama = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        keluar_bahan_qty = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        keluar_bahan_satuan = new javax.swing.JTextField();
        keluar_tgl = new com.toedter.calendar.JDateChooser();
        jScrollPane8 = new javax.swing.JScrollPane();
        keluar_tb_data = new javax.swing.JTable();
        jLabel31 = new javax.swing.JLabel();
        keluar_bt_tambah = new javax.swing.JButton();
        keluar_bt_simpan = new javax.swing.JButton();
        keluar_bt_batal = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txt_jml_item = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        txt_total_qty = new javax.swing.JLabel();
        keluar_barang = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        gTableList = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        gInv = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        gNama = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        gTelp = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        gAlamat = new javax.swing.JTextArea();
        jLabel27 = new javax.swing.JLabel();
        gHarga = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        gItem = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        gTableItem = new javax.swing.JTable();
        gBtTampil = new javax.swing.JButton();
        gBtKirim = new javax.swing.JButton();
        gBtBatal = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        head.setBackground(new java.awt.Color(255, 255, 255));

        profile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/user.png"))); // NOI18N
        profile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ClickMenu(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setText("Hai,");

        lbl_nama.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl_nama.setText("Nama");

        txt_menu_gudang.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_menu_gudang.setText("Selamat Datang");

        gudang_tgl_global.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        gudang_tgl_global.setText("Taggal");

        javax.swing.GroupLayout headLayout = new javax.swing.GroupLayout(head);
        head.setLayout(headLayout);
        headLayout.setHorizontalGroup(
            headLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headLayout.createSequentialGroup()
                .addGroup(headLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(headLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_nama))
                    .addGroup(headLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(profile)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(headLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_menu_gudang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(gudang_tgl_global, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        headLayout.setVerticalGroup(
            headLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(profile)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(headLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(lbl_nama))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(gudang_tgl_global)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txt_menu_gudang)
                .addContainerGap())
        );

        side.setBackground(new java.awt.Color(255, 255, 255));

        gmasuk_bahan.setBackground(new java.awt.Color(0, 204, 204));
        gmasuk_bahan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        gmasuk_bahan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ClickMenu(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Masuk Bahan Baku");

        javax.swing.GroupLayout gmasuk_bahanLayout = new javax.swing.GroupLayout(gmasuk_bahan);
        gmasuk_bahan.setLayout(gmasuk_bahanLayout);
        gmasuk_bahanLayout.setHorizontalGroup(
            gmasuk_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(gmasuk_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(gmasuk_bahanLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel10)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        gmasuk_bahanLayout.setVerticalGroup(
            gmasuk_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
            .addGroup(gmasuk_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(gmasuk_bahanLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel10)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        gmasuk_produk.setBackground(new java.awt.Color(0, 204, 204));
        gmasuk_produk.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        gmasuk_produk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ClickMenu(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Masuk Produk");

        javax.swing.GroupLayout gmasuk_produkLayout = new javax.swing.GroupLayout(gmasuk_produk);
        gmasuk_produk.setLayout(gmasuk_produkLayout);
        gmasuk_produkLayout.setHorizontalGroup(
            gmasuk_produkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 280, Short.MAX_VALUE)
            .addGroup(gmasuk_produkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(gmasuk_produkLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel11)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        gmasuk_produkLayout.setVerticalGroup(
            gmasuk_produkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
            .addGroup(gmasuk_produkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(gmasuk_produkLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel11)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        gkeluar_bahan.setBackground(new java.awt.Color(0, 204, 204));
        gkeluar_bahan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        gkeluar_bahan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ClickMenu(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Kirim Bahan");

        javax.swing.GroupLayout gkeluar_bahanLayout = new javax.swing.GroupLayout(gkeluar_bahan);
        gkeluar_bahan.setLayout(gkeluar_bahanLayout);
        gkeluar_bahanLayout.setHorizontalGroup(
            gkeluar_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 280, Short.MAX_VALUE)
            .addGroup(gkeluar_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(gkeluar_bahanLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel12)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        gkeluar_bahanLayout.setVerticalGroup(
            gkeluar_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
            .addGroup(gkeluar_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(gkeluar_bahanLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel12)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        gkeluar_barang.setBackground(new java.awt.Color(0, 204, 204));
        gkeluar_barang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        gkeluar_barang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ClickMenu(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Kirim Produk");

        javax.swing.GroupLayout gkeluar_barangLayout = new javax.swing.GroupLayout(gkeluar_barang);
        gkeluar_barang.setLayout(gkeluar_barangLayout);
        gkeluar_barangLayout.setHorizontalGroup(
            gkeluar_barangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 280, Short.MAX_VALUE)
            .addGroup(gkeluar_barangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(gkeluar_barangLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel13)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        gkeluar_barangLayout.setVerticalGroup(
            gkeluar_barangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
            .addGroup(gkeluar_barangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(gkeluar_barangLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel13)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel4.setBackground(new java.awt.Color(255, 0, 0));
        jPanel4.setForeground(new java.awt.Color(255, 255, 255));
        jPanel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel4MouseClicked(evt);
            }
        });

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setText("Keluar");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel39)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel39)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout sideLayout = new javax.swing.GroupLayout(side);
        side.setLayout(sideLayout);
        sideLayout.setHorizontalGroup(
            sideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sideLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(sideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(gmasuk_bahan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(gmasuk_produk, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(gkeluar_bahan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(gkeluar_barang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        sideLayout.setVerticalGroup(
            sideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sideLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(gmasuk_bahan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(gmasuk_produk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(gkeluar_bahan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(gkeluar_barang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        body.setBackground(new java.awt.Color(0, 204, 204));
        body.setLayout(new java.awt.CardLayout());

        Utama.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel41.setText("Aplikasi Pengelolaan Bahan Baku dan Produk Jadi");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(200, Short.MAX_VALUE)
                .addComponent(jLabel41)
                .addContainerGap(200, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addComponent(jLabel41)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jLabel42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/logo_ska.png"))); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel42)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel43.setText("PT. SETIA KAWAN ABADI");

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel44.setText("Jl. Daan Mogot Km 10 No. 46 Kedaung Kali Angke Kec. Cengkareng Jakarta Barat");

        javax.swing.GroupLayout UtamaLayout = new javax.swing.GroupLayout(Utama);
        Utama.setLayout(UtamaLayout);
        UtamaLayout.setHorizontalGroup(
            UtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UtamaLayout.createSequentialGroup()
                .addGroup(UtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(UtamaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(UtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(UtamaLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel43)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(UtamaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel44)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        UtamaLayout.setVerticalGroup(
            UtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, UtamaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 105, Short.MAX_VALUE)
                .addComponent(jLabel43)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel44)
                .addContainerGap(87, Short.MAX_VALUE))
        );

        body.add(Utama, "card11");

        masuk_bahan.setBackground(new java.awt.Color(0, 204, 204));

        masuk_bahan_tabel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(masuk_bahan_tabel);

        masuk_bahan_batal.setBackground(new java.awt.Color(255, 0, 0));
        masuk_bahan_batal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        masuk_bahan_batal.setForeground(new java.awt.Color(255, 255, 255));
        masuk_bahan_batal.setText("Batal");
        masuk_bahan_batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                masuk_bahan_batalActionPerformed(evt);
            }
        });

        masuk_bahan_simpan.setBackground(new java.awt.Color(0, 204, 0));
        masuk_bahan_simpan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        masuk_bahan_simpan.setForeground(new java.awt.Color(255, 255, 255));
        masuk_bahan_simpan.setText("Simpan");
        masuk_bahan_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                masuk_bahan_simpanActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(0, 153, 255));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Tampilkan Item");
        jButton3.setPreferredSize(new java.awt.Dimension(141, 40));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("ID Request");

        masuk_bahan_id.setPreferredSize(new java.awt.Dimension(59, 30));

        masuk_bahan_cari.setBackground(new java.awt.Color(0, 153, 204));
        masuk_bahan_cari.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        masuk_bahan_cari.setForeground(new java.awt.Color(255, 255, 255));
        masuk_bahan_cari.setText("Cari");
        masuk_bahan_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                masuk_bahan_cariActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel21.setText("Supplier ID");

        masuk_bahan_supid.setPreferredSize(new java.awt.Dimension(59, 30));

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel22.setText("Supplier Nama");

        masuk_bahan_supnama.setPreferredSize(new java.awt.Dimension(59, 30));

        masuk_bahan_jenis.setPreferredSize(new java.awt.Dimension(59, 30));

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel23.setText("Jenis Item");

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel24.setText("Jumlah Item");

        masuk_bahan_jumlah.setPreferredSize(new java.awt.Dimension(59, 30));

        masuk_bahan_status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih", "Sesuai", "Bermasalah" }));
        masuk_bahan_status.setPreferredSize(new java.awt.Dimension(59, 30));

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel26.setText("Status Pengecekan");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel21)
                    .addComponent(jLabel2)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(masuk_bahan_supid, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(masuk_bahan_id, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(masuk_bahan_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel22)
                    .addComponent(masuk_bahan_supnama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(masuk_bahan_jenis, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23)
                    .addComponent(jLabel24)
                    .addComponent(masuk_bahan_jumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26)
                    .addComponent(masuk_bahan_status, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(masuk_bahan_id, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(masuk_bahan_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addGap(5, 5, 5)
                        .addComponent(masuk_bahan_jenis, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addGap(5, 5, 5)
                        .addComponent(masuk_bahan_supid, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addGap(5, 5, 5)
                        .addComponent(masuk_bahan_jumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(masuk_bahan_supnama, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(masuk_bahan_status, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        masuk_bhn_stok.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane9.setViewportView(masuk_bhn_stok);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Stok Data Bahan");

        javax.swing.GroupLayout masuk_bahanLayout = new javax.swing.GroupLayout(masuk_bahan);
        masuk_bahan.setLayout(masuk_bahanLayout);
        masuk_bahanLayout.setHorizontalGroup(
            masuk_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(masuk_bahanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(masuk_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane9)
                    .addGroup(masuk_bahanLayout.createSequentialGroup()
                        .addGroup(masuk_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(masuk_bahanLayout.createSequentialGroup()
                                .addComponent(masuk_bahan_batal, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(masuk_bahan_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE))
                    .addGroup(masuk_bahanLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        masuk_bahanLayout.setVerticalGroup(
            masuk_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(masuk_bahanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(masuk_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(masuk_bahanLayout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(masuk_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(masuk_bahan_simpan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(masuk_bahan_batal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                .addContainerGap())
        );

        body.add(masuk_bahan, "card2");

        masuk_produk.setBackground(new java.awt.Color(0, 204, 204));

        mp_tbl_list.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        mp_tbl_list.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mp_tbl_listMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(mp_tbl_list);

        mp_tbl_item.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(mp_tbl_item);

        jPanel1.setBackground(new java.awt.Color(0, 204, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel6.setText("No Order");

        jLabel7.setText("Produk ID");

        jLabel8.setText("Nama Produk");

        jLabel9.setText("Jenis Produk");

        jLabel14.setText("Jumlah Order");

        jLabel3.setText("Jumlah Masuk");

        mp_jml_masuk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                mp_jml_masukKeyTyped(evt);
            }
        });

        jLabel16.setText("Tanggal Masuk");

        mp_tgl_masuk.setDateFormatString("EEEE, dd MMMM yyyy");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(mp_order_id)
                    .addComponent(mp_produk_id)
                    .addComponent(mp_nama_produk, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(mp_jml_masuk, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(mp_jml_order, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(mp_tgl_masuk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(mp_jenis_produk, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(mp_jenis_produk, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(mp_order_id))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel14)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mp_jml_order, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(mp_produk_id)
                    .addComponent(mp_tgl_masuk, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(mp_nama_produk, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(mp_jml_masuk))
                .addGap(17, 17, 17))
        );

        mp_bt_simpan.setBackground(new java.awt.Color(0, 204, 0));
        mp_bt_simpan.setText("Simpan");
        mp_bt_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mp_bt_simpanActionPerformed(evt);
            }
        });

        mp_bt_batal.setBackground(new java.awt.Color(204, 0, 0));
        mp_bt_batal.setText("Batal");
        mp_bt_batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mp_bt_batalActionPerformed(evt);
            }
        });

        jLabel17.setText("Produk Masuk");

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel25.setText("Stok Produk Jadi");

        javax.swing.GroupLayout masuk_produkLayout = new javax.swing.GroupLayout(masuk_produk);
        masuk_produk.setLayout(masuk_produkLayout);
        masuk_produkLayout.setHorizontalGroup(
            masuk_produkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(masuk_produkLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(masuk_produkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(masuk_produkLayout.createSequentialGroup()
                        .addGroup(masuk_produkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17)
                            .addComponent(jLabel25))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(masuk_produkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(masuk_produkLayout.createSequentialGroup()
                                .addGap(0, 399, Short.MAX_VALUE)
                                .addComponent(mp_bt_batal, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(mp_bt_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        masuk_produkLayout.setVerticalGroup(
            masuk_produkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(masuk_produkLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(masuk_produkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(masuk_produkLayout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(masuk_produkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(masuk_produkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(mp_bt_batal, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(mp_bt_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                .addContainerGap())
        );

        body.add(masuk_produk, "card3");

        keluar_bahan.setBackground(new java.awt.Color(0, 204, 204));

        keluar_tb_item.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No.Faktur", "Bahan ID", "Nama", "Qty", "Satuan"
            }
        ));
        jScrollPane7.setViewportView(keluar_tb_item);

        jLabel30.setText("Item Bahan");

        jPanel3.setBackground(new java.awt.Color(0, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Input Data", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jLabel32.setText("No Faktur");

        keluar_id.setEnabled(false);

        jLabel33.setText("Penerima");

        jLabel34.setText("Tanggal Penyerahan");

        jLabel35.setText("Bahan ID");

        keluar_bahan_id.setEnabled(false);

        keluar_bt_cari.setBackground(new java.awt.Color(0, 102, 204));
        keluar_bt_cari.setText("Cari");
        keluar_bt_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keluar_bt_cariActionPerformed(evt);
            }
        });

        jLabel36.setText("Nama");

        keluar_bahan_nama.setEnabled(false);

        jLabel37.setText("Qty");

        jLabel38.setText("Satuan");

        keluar_bahan_satuan.setEnabled(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel32)
                    .addComponent(jLabel33)
                    .addComponent(jLabel34)
                    .addComponent(keluar_nama, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                    .addComponent(keluar_id)
                    .addComponent(keluar_tgl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(36, 36, 36)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel35)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(keluar_bahan_id)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(keluar_bt_cari))
                    .addComponent(jLabel36)
                    .addComponent(keluar_bahan_nama, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel37)
                            .addComponent(keluar_bahan_qty, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(keluar_bahan_satuan, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel38))))
                .addGap(20, 20, 20))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(jLabel35))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(keluar_id)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(keluar_bt_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(keluar_bahan_id))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(jLabel36))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(keluar_bahan_nama, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(keluar_nama))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(jLabel37)
                    .addComponent(jLabel38))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(keluar_bahan_satuan, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(keluar_bahan_qty)
                    .addComponent(keluar_tgl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(37, 37, 37))
        );

        keluar_tb_data.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane8.setViewportView(keluar_tb_data);

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel31.setText("Aktivitas");

        keluar_bt_tambah.setBackground(new java.awt.Color(0, 102, 204));
        keluar_bt_tambah.setText("Tambah");
        keluar_bt_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keluar_bt_tambahActionPerformed(evt);
            }
        });

        keluar_bt_simpan.setBackground(new java.awt.Color(0, 204, 0));
        keluar_bt_simpan.setText("Simpan");
        keluar_bt_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keluar_bt_simpanActionPerformed(evt);
            }
        });

        keluar_bt_batal.setBackground(new java.awt.Color(255, 0, 0));
        keluar_bt_batal.setText("Batal");
        keluar_bt_batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keluar_bt_batalActionPerformed(evt);
            }
        });

        jLabel4.setText("Jumlah Item");

        txt_jml_item.setText("0");

        jLabel40.setText("Total Qty");

        txt_total_qty.setText("0");

        javax.swing.GroupLayout keluar_bahanLayout = new javax.swing.GroupLayout(keluar_bahan);
        keluar_bahan.setLayout(keluar_bahanLayout);
        keluar_bahanLayout.setHorizontalGroup(
            keluar_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(keluar_bahanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(keluar_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(keluar_bahanLayout.createSequentialGroup()
                        .addGroup(keluar_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel31)
                            .addGroup(keluar_bahanLayout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(keluar_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(keluar_bt_tambah)
                                    .addGroup(keluar_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(keluar_bt_batal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(keluar_bt_simpan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(keluar_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(keluar_bahanLayout.createSequentialGroup()
                                .addComponent(jLabel30)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(keluar_bahanLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_jml_item, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel40)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_total_qty, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE))))
                .addContainerGap())
        );
        keluar_bahanLayout.setVerticalGroup(
            keluar_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(keluar_bahanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel30)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(keluar_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(keluar_bahanLayout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(keluar_bt_tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(keluar_bt_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(keluar_bt_batal, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(keluar_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt_jml_item)
                    .addComponent(jLabel40)
                    .addComponent(txt_total_qty)
                    .addComponent(jLabel31))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                .addContainerGap())
        );

        body.add(keluar_bahan, "card4");

        keluar_barang.setBackground(new java.awt.Color(0, 204, 204));

        gTableList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        gTableList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                gTableListMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(gTableList);

        jPanel2.setBackground(new java.awt.Color(0, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Input Data", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Invoice");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel15.setText("Customer Nama");

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel19.setText("Telp");

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel20.setText("Alamat");

        gAlamat.setColumns(20);
        gAlamat.setRows(5);
        jScrollPane6.setViewportView(gAlamat);

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel27.setText("Harga");

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel28.setText("Item");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(gInv, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(gNama, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(gTelp, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel20)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(gItem, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel27)
                            .addComponent(gHarga))))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(gInv, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(gNama, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jLabel27)
                    .addComponent(jLabel28))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(gItem, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(gHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(gTelp, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        gTableItem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane5.setViewportView(gTableItem);

        gBtTampil.setBackground(new java.awt.Color(0, 102, 204));
        gBtTampil.setText("Tampilkan Item");
        gBtTampil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                gBtTampilMouseClicked(evt);
            }
        });

        gBtKirim.setBackground(new java.awt.Color(0, 204, 0));
        gBtKirim.setText("Simpan");
        gBtKirim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gBtKirimActionPerformed(evt);
            }
        });

        gBtBatal.setBackground(new java.awt.Color(255, 0, 0));
        gBtBatal.setText("Batal");

        javax.swing.GroupLayout keluar_barangLayout = new javax.swing.GroupLayout(keluar_barang);
        keluar_barang.setLayout(keluar_barangLayout);
        keluar_barangLayout.setHorizontalGroup(
            keluar_barangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(keluar_barangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(keluar_barangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5)
                    .addGroup(keluar_barangLayout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(keluar_barangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(keluar_barangLayout.createSequentialGroup()
                                .addComponent(gBtBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(gBtKirim, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(keluar_barangLayout.createSequentialGroup()
                        .addComponent(gBtTampil)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        keluar_barangLayout.setVerticalGroup(
            keluar_barangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(keluar_barangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(keluar_barangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(keluar_barangLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(keluar_barangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(gBtKirim, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(gBtBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gBtTampil, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                .addContainerGap())
        );

        body.add(keluar_barang, "card5");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(side, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(head, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(head, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(side, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void ClickMenu(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ClickMenu
        if (evt.getSource() == gmasuk_bahan) {
            Utama.setVisible(false);
            masuk_bahan.setVisible(true);
            masuk_produk.setVisible(false);
            keluar_bahan.setVisible(false);
            keluar_barang.setVisible(false);
            gmasuk_bahan.setBackground(new Color(0, 153, 153));//dark
            gmasuk_produk.setBackground(new Color(0, 204, 204));//dark
            gkeluar_bahan.setBackground(new Color(0, 204, 204));//dark
            gkeluar_barang.setBackground(new Color(0, 204, 204));//dark
            txt_menu_gudang.setText("Masuk Bahan Baku");
            runTable();
        }
        if (evt.getSource() == gmasuk_produk) {
            Utama.setVisible(false);
            masuk_bahan.setVisible(false);
            masuk_produk.setVisible(true);
            keluar_bahan.setVisible(false);
            keluar_barang.setVisible(false);
            gmasuk_produk.setBackground(new Color(0, 153, 153));//dark
            gmasuk_bahan.setBackground(new Color(0, 204, 204));//dark
            gkeluar_bahan.setBackground(new Color(0, 204, 204));//dark
            gkeluar_barang.setBackground(new Color(0, 204, 204));//dark
            txt_menu_gudang.setText("Masuk Produk");
            runTable();
        }
        if (evt.getSource() == gkeluar_bahan) {
            Utama.setVisible(false);
            masuk_bahan.setVisible(false);
            masuk_produk.setVisible(false);
            keluar_bahan.setVisible(true);
            keluar_barang.setVisible(false);
            gkeluar_bahan.setBackground(new Color(0, 153, 153));//dark
            gmasuk_produk.setBackground(new Color(0, 204, 204));//dark
            gmasuk_bahan.setBackground(new Color(0, 204, 204));//dark
            gkeluar_barang.setBackground(new Color(0, 204, 204));//dark
            txt_menu_gudang.setText("Kirim Bahan");
            runTable();
        }
        if (evt.getSource() == gkeluar_barang) {
            Utama.setVisible(false);
            masuk_bahan.setVisible(false);
            masuk_produk.setVisible(false);
            keluar_bahan.setVisible(false);
            keluar_barang.setVisible(true);
            gkeluar_barang.setBackground(new Color(0, 153, 153));//dark
            gmasuk_produk.setBackground(new Color(0, 204, 204));//dark
            gkeluar_bahan.setBackground(new Color(0, 204, 204));//dark
            gmasuk_bahan.setBackground(new Color(0, 204, 204));//darkv
            txt_menu_gudang.setText("Kirim Produk");
            runTable();
        }
        if (evt.getSource() == profile) {
            Utama.setVisible(true);
            masuk_bahan.setVisible(false);
            masuk_produk.setVisible(false);
            keluar_bahan.setVisible(false);
            keluar_barang.setVisible(false);
            txt_menu_gudang.setText("Halaman Utama");
            runTable();
        }
    }//GEN-LAST:event_ClickMenu

    private void masuk_bahan_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_masuk_bahan_cariActionPerformed
        ShowBahanRequest sbr = new ShowBahanRequest();
        sbr.bgbelakang = this;
        sbr.setVisible(true);
        sbr.setResizable(false);
    }//GEN-LAST:event_masuk_bahan_cariActionPerformed

    private void masuk_bahan_batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_masuk_bahan_batalActionPerformed
        ClearMasukBahan();
    }//GEN-LAST:event_masuk_bahan_batalActionPerformed

    private void masuk_bahan_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_masuk_bahan_simpanActionPerformed
        String stat = masuk_bahan_status.getSelectedItem().toString();

        if (stat.equals("Pilih")) {
            JOptionPane.showMessageDialog(null, "Field Kosong!", "Peringatan", HEIGHT);
        } else if (stat.equals("Sesuai")) {
            SimpanMasukBahan();
        } else if (stat.equals("Bermasalah")) {
            UpdateMasukBahan();
        }
    }//GEN-LAST:event_masuk_bahan_simpanActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (masuk_bahan_id.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Field ID Kosong!", "Peringatan", HEIGHT);
        } else {
            TableMasukBahan();
            masuk_bahan_batal.setVisible(true);
            masuk_bahan_simpan.setVisible(true);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void mp_tbl_listMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mp_tbl_listMouseClicked
        mp_order_id.setEnabled(false);
        mp_produk_id.setEnabled(false);
        mp_nama_produk.setEnabled(false);
        mp_jenis_produk.setEnabled(false);
        mp_jml_order.setEnabled(false);
        int bar = mp_tbl_list.getSelectedRow();
        String a = tablemasukProduk.getValueAt(bar, 0).toString();
        String b = tablemasukProduk.getValueAt(bar, 1).toString();
        String c = tablemasukProduk.getValueAt(bar, 2).toString();
        String d = tablemasukProduk.getValueAt(bar, 3).toString();
        String e = tablemasukProduk.getValueAt(bar, 4).toString();

        mp_order_id.setText(a);
        mp_produk_id.setText(b);
        mp_nama_produk.setText(c);
        mp_jenis_produk.setText(d);
        mp_jml_order.setText(e);
    }//GEN-LAST:event_mp_tbl_listMouseClicked

    private void mp_bt_batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mp_bt_batalActionPerformed
        ClearMasukProduk();
    }//GEN-LAST:event_mp_bt_batalActionPerformed

    private void mp_bt_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mp_bt_simpanActionPerformed
        String query = "INSERT INTO `produk_jadi`(`order_id`, `id_produk`, `qty`) VALUES (?,?,?)";
        if (mp_order_id.getText().isEmpty() || mp_produk_id.getText().isEmpty() || mp_jml_masuk.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Periksa Field!", "Perhatian!", HEIGHT, invalid);
        } else {
            try {
                ps = Config.config.getConnection().prepareStatement(query);
                ps.setString(1, mp_order_id.getText().trim());
                ps.setString(2, mp_produk_id.getText().trim());
                ps.setString(3, mp_jml_masuk.getText().trim());
                ps.executeUpdate();
                UpdateStatusOrder();
                ClearMasukProduk();
                runTable();
//            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan..", "Berhasil!", HEIGHT, sucess);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Gagal"+e.getMessage(), "Alert Message!", HEIGHT, warning);
//            DisableField();
            }
        }
    }//GEN-LAST:event_mp_bt_simpanActionPerformed

    private void mp_jml_masukKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mp_jml_masukKeyTyped
        FilterAngka(evt);
    }//GEN-LAST:event_mp_jml_masukKeyTyped

    private void gTableListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gTableListMouseClicked
        gInv.setEnabled(false);
        gNama.setEnabled(false);
        gTelp.setEnabled(false);
        gAlamat.setEnabled(false);
        gHarga.setEnabled(false);
        gItem.setEnabled(false);
        int bar = gTableList.getSelectedRow();
        String a = tableListKirim.getValueAt(bar, 0).toString();
        String b = tableListKirim.getValueAt(bar, 1).toString();
        String c = tableListKirim.getValueAt(bar, 2).toString();
        String d = tableListKirim.getValueAt(bar, 3).toString();
        String e = tableListKirim.getValueAt(bar, 5).toString();
        String f = tableListKirim.getValueAt(bar, 6).toString();

        gInv.setText(a);
        gNama.setText(b);
        gTelp.setText(c);
        gAlamat.setText(d);
        gItem.setText(e);
        gHarga.setText(f);
    }//GEN-LAST:event_gTableListMouseClicked

    private void gBtTampilMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gBtTampilMouseClicked
        TableItemKirim();
    }//GEN-LAST:event_gBtTampilMouseClicked

    private void gBtKirimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gBtKirimActionPerformed
        if (gInv.getText().isEmpty()||gNama.getText().isEmpty()||gTelp.getText().isEmpty()||gAlamat.getText().isEmpty()
                || gItem.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Periksa Field!", "Perhatian!", HEIGHT, invalid);

        }
        else{   
            SimpanKeluarProduk();
        }
    }//GEN-LAST:event_gBtKirimActionPerformed

    private void keluar_bt_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_keluar_bt_tambahActionPerformed
        if (keluar_bahan_id.getText().isEmpty() || keluar_bahan_nama.getText().isEmpty() || keluar_bahan_qty.getText().isEmpty()
                || keluar_id.getText().isEmpty() || keluar_nama.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Data Belum Terpenuhi!", "Alert Message", HEIGHT, warning);
        } else {
            DefaultTableModel dmodel = (DefaultTableModel) keluar_tb_item.getModel();
            dmodel.addRow(new Object[]{
                keluar_id.getText(), keluar_bahan_id.getText(), keluar_bahan_nama.getText(), keluar_bahan_qty.getText(),
                keluar_bahan_satuan.getText()});
            keluar_tb_item.setModel(dmodel);
            keluar_bahan_id.setText("");
            keluar_bahan_nama.setText("");
            keluar_bahan_qty.setText("");
            keluar_bahan_satuan.setText("");
            int row = dmodel.getRowCount();
            txt_jml_item.setText("" + row);
            //menjumlahkan isi colom ke 4 dalam tabel
            int total = 0;
            for (int i = 0; i < keluar_tb_item.getRowCount(); i++) {
                int amount = Integer.parseInt((String) keluar_tb_item.getValueAt(i, 3));
                total += amount;
            }
            txt_total_qty.setText("" + total);
        }
    }//GEN-LAST:event_keluar_bt_tambahActionPerformed

    private void keluar_bt_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_keluar_bt_cariActionPerformed
        ShowBahanKeluar sbk = new ShowBahanKeluar();
        sbk.gdBahan = this;
        sbk.setVisible(true);
        sbk.setResizable(false);
    }//GEN-LAST:event_keluar_bt_cariActionPerformed

    private void keluar_bt_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_keluar_bt_simpanActionPerformed
        String query = "INSERT INTO `keluar_bahan`(`no`, `nama`, `tanggal`, `jumlah`, `total`) VALUES (?,?,?,?,?)";
        String date1 = ((JTextField) mp_tgl_masuk.getDateEditor().getUiComponent()).getText();
        if (keluar_id.getText().isEmpty() || keluar_nama.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Periksa Field!", "Perhatian!", HEIGHT, invalid);
        } else {
            try {
                ps = Config.config.getConnection().prepareStatement(query);
                ps.setString(1, keluar_id.getText().trim());
                ps.setString(2, keluar_nama.getText().trim());
                ps.setString(3, date1);
                ps.setString(4, txt_jml_item.getText().trim());
                ps.setString(5, txt_total_qty.getText().trim());
                ps.executeUpdate();
                SimpanKeluarItem();
                ClearKeluarBahan();
                runTable();
                txt_jml_item.setText("0");
                txt_total_qty.setText("0");
//            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan..", "Berhasil!", HEIGHT, sucess);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Gagal"+e.getMessage(), "Alert Message!", HEIGHT, warning);
//            DisableField();
            }
        }
    }//GEN-LAST:event_keluar_bt_simpanActionPerformed

    private void keluar_bt_batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_keluar_bt_batalActionPerformed
        ClearKeluarBahan();
    }//GEN-LAST:event_keluar_bt_batalActionPerformed

    private void jPanel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseClicked
        Login1 awal = new Login1();
        int ok = JOptionPane.showConfirmDialog(null, "Apakah Yakin Akan Keluar???", "Perhatian!", JOptionPane.YES_NO_OPTION, HEIGHT, warning);
        if (ok == 0) {
            awal.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_jPanel4MouseClicked

    private void UpdateStatusOrder() {
        String status = "Selesai";
        try {
            String sql = "update order_produk set status=? where id='" + mp_order_id.getText() + "'";
            PreparedStatement stat = Config.config.getConnection().prepareStatement(sql);
            stat.setString(1, status);
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan..", "Berhasil!", HEIGHT, sucess);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan!", "Perhatian!", HEIGHT, invalid);
        }
    }
    
    private void UpdateStatusKirim() {
        String status = "Di Kirimkan";
        try {
            String sql = "update kirim_barang set status=? where id='" + gInv.getText() + "'";
            PreparedStatement stat = Config.config.getConnection().prepareStatement(sql);
            stat.setString(1, status);
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan..", "Berhasil!", HEIGHT, sucess);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan!", "Perhatian!", HEIGHT, invalid);
        }
    }

    public void filterHuruf(KeyEvent a) {
        if (Character.isDigit(a.getKeyChar())) {
            a.consume();
            JOptionPane.showMessageDialog(null, "Masukan Hanya Huruf", "Peringatan", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void FilterAngka(KeyEvent b) {
        if (Character.isAlphabetic(b.getKeyChar())) {
            b.consume();
            JOptionPane.showMessageDialog(null, "Masukan Hanya Angka", "Peringatan", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Gudang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Gudang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Gudang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Gudang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Gudang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Utama;
    private javax.swing.JPanel body;
    private javax.swing.JTextArea gAlamat;
    private javax.swing.JButton gBtBatal;
    private javax.swing.JButton gBtKirim;
    private javax.swing.JButton gBtTampil;
    private javax.swing.JTextField gHarga;
    private javax.swing.JTextField gInv;
    private javax.swing.JTextField gItem;
    private javax.swing.JTextField gNama;
    private javax.swing.JTable gTableItem;
    private javax.swing.JTable gTableList;
    private javax.swing.JTextField gTelp;
    private javax.swing.JPanel gkeluar_bahan;
    private javax.swing.JPanel gkeluar_barang;
    private javax.swing.JPanel gmasuk_bahan;
    private javax.swing.JPanel gmasuk_produk;
    private javax.swing.JLabel gudang_tgl_global;
    private javax.swing.JPanel head;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JPanel keluar_bahan;
    private javax.swing.JTextField keluar_bahan_id;
    private javax.swing.JTextField keluar_bahan_nama;
    private javax.swing.JTextField keluar_bahan_qty;
    private javax.swing.JTextField keluar_bahan_satuan;
    private javax.swing.JPanel keluar_barang;
    private javax.swing.JButton keluar_bt_batal;
    private javax.swing.JButton keluar_bt_cari;
    private javax.swing.JButton keluar_bt_simpan;
    private javax.swing.JButton keluar_bt_tambah;
    private javax.swing.JTextField keluar_id;
    private javax.swing.JTextField keluar_nama;
    private javax.swing.JTable keluar_tb_data;
    private javax.swing.JTable keluar_tb_item;
    private com.toedter.calendar.JDateChooser keluar_tgl;
    private javax.swing.JLabel lbl_nama;
    private javax.swing.JPanel masuk_bahan;
    private javax.swing.JButton masuk_bahan_batal;
    private javax.swing.JButton masuk_bahan_cari;
    private javax.swing.JTextField masuk_bahan_id;
    private javax.swing.JTextField masuk_bahan_jenis;
    private javax.swing.JTextField masuk_bahan_jumlah;
    private javax.swing.JButton masuk_bahan_simpan;
    private javax.swing.JComboBox<String> masuk_bahan_status;
    private javax.swing.JTextField masuk_bahan_supid;
    private javax.swing.JTextField masuk_bahan_supnama;
    private javax.swing.JTable masuk_bahan_tabel;
    private javax.swing.JTable masuk_bhn_stok;
    private javax.swing.JPanel masuk_produk;
    private javax.swing.JButton mp_bt_batal;
    private javax.swing.JButton mp_bt_simpan;
    private javax.swing.JTextField mp_jenis_produk;
    private javax.swing.JTextField mp_jml_masuk;
    private javax.swing.JTextField mp_jml_order;
    private javax.swing.JTextField mp_nama_produk;
    private javax.swing.JTextField mp_order_id;
    private javax.swing.JTextField mp_produk_id;
    private javax.swing.JTable mp_tbl_item;
    private javax.swing.JTable mp_tbl_list;
    private com.toedter.calendar.JDateChooser mp_tgl_masuk;
    private javax.swing.JLabel profile;
    private javax.swing.JPanel side;
    private javax.swing.JLabel txt_jml_item;
    private javax.swing.JLabel txt_menu_gudang;
    private javax.swing.JLabel txt_total_qty;
    // End of variables declaration//GEN-END:variables
}
