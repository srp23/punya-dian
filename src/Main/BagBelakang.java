package Main;

import MainExtend.ShowBahanRequest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class BagBelakang extends javax.swing.JFrame {

    ImageIcon sucess = new ImageIcon(getClass().getResource("/Icon/checked.png"));
    ImageIcon invalid = new ImageIcon(getClass().getResource("/Icon/cancel.png"));
    ImageIcon warning = new ImageIcon(getClass().getResource("/Icon/warning.png"));
    PreparedStatement ps = null;
    ResultSet rs = null;
    private DefaultTableModel tablemasukbahan;

    public String ex_sbr_id, ex_sbr_suppid, ex_sbr_suppnama, ex_sbr_jenis, ex_sbr_total;

    public BagBelakang() {
        initComponents();
        ClearMasukBahan();
        
        lbl_nama.setText(Login1.getnama());
        lbl_akses.setText(Login1.getstatus());
        if (lbl_akses.getText().equals("gudang")) {
            menuGudang();
        }
        else if (lbl_akses.getText().equals("produksi")) {
            menuProduksi();
        }
    }
    
    protected void menuGudang(){
        gkeluar_bahan.setVisible(true);
        gkeluar_barang.setVisible(true);
        gmasuk_bahan.setVisible(true);
        gmasuk_produk.setVisible(true);
        p_kirim_barang.setVisible(false);
        p_req_bahan.setVisible(false);
        p_req_produksi.setVisible(false);
    }
    
    protected void menuProduksi(){
        gkeluar_bahan.setVisible(false);
        gkeluar_barang.setVisible(false);
        gmasuk_bahan.setVisible(false);
        gmasuk_produk.setVisible(false);
        p_kirim_barang.setVisible(true);
        p_req_bahan.setVisible(true);
        p_req_produksi.setVisible(true);
        
    }
    
    protected void ClearMasukBahan(){
        masuk_bahan_id.setText("");
        masuk_bahan_supid.setText("");
        masuk_bahan_supnama.setText("");
        masuk_bahan_jenis.setText("");
        masuk_bahan_jumlah.setText("");
       ((DefaultTableModel) masuk_bahan_tabel.getModel()).setNumRows(0);
       masuk_bahan_simpan.setVisible(false);
       masuk_bahan_batal.setVisible(false);
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
    
    protected void SimpanMasukBahan(){
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
        lbl_akses = new javax.swing.JLabel();
        side = new javax.swing.JPanel();
        gmasuk_bahan = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        gmasuk_produk = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        gkeluar_bahan = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        gkeluar_barang = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        p_req_produksi = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        p_req_bahan = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        p_kirim_barang = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        body = new javax.swing.JPanel();
        Utama = new javax.swing.JPanel();
        masuk_bahan = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        masuk_bahan_id = new javax.swing.JTextField();
        masuk_bahan_supid = new javax.swing.JTextField();
        masuk_bahan_cari = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        masuk_bahan_supnama = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        masuk_bahan_tabel = new javax.swing.JTable();
        jLabel23 = new javax.swing.JLabel();
        masuk_bahan_jenis = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        masuk_bahan_jumlah = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        masuk_bahan_tgl = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        masuk_bahan_status = new javax.swing.JComboBox<>();
        masuk_bahan_batal = new javax.swing.JButton();
        masuk_bahan_simpan = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        masuk_produk = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        keluar_bahan = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        keluar_barang = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        request_produksi = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        permintaan_bahan = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        kirim_produk = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        halaman8 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        halaman9 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

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

        lbl_akses.setText("Akses");

        javax.swing.GroupLayout headLayout = new javax.swing.GroupLayout(head);
        head.setLayout(headLayout);
        headLayout.setHorizontalGroup(
            headLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(profile)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_nama)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_akses)
                .addContainerGap())
        );
        headLayout.setVerticalGroup(
            headLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headLayout.createSequentialGroup()
                .addGroup(headLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(headLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(profile, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(headLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(headLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(lbl_nama)
                            .addComponent(lbl_akses))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        side.setBackground(new java.awt.Color(255, 255, 255));

        gmasuk_bahan.setBackground(new java.awt.Color(0, 204, 204));
        gmasuk_bahan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ClickMenu(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Masuk  Bahan");

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
        gkeluar_bahan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ClickMenu(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Keluar Bahan");

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
        gkeluar_barang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ClickMenu(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Keluar Barang");

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

        p_req_produksi.setBackground(new java.awt.Color(0, 204, 204));
        p_req_produksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ClickMenu(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Request Produksi");

        javax.swing.GroupLayout p_req_produksiLayout = new javax.swing.GroupLayout(p_req_produksi);
        p_req_produksi.setLayout(p_req_produksiLayout);
        p_req_produksiLayout.setHorizontalGroup(
            p_req_produksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 280, Short.MAX_VALUE)
            .addGroup(p_req_produksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(p_req_produksiLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel14)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        p_req_produksiLayout.setVerticalGroup(
            p_req_produksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
            .addGroup(p_req_produksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(p_req_produksiLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel14)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        p_req_bahan.setBackground(new java.awt.Color(0, 204, 204));
        p_req_bahan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ClickMenu(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Permintaan Bahan");

        javax.swing.GroupLayout p_req_bahanLayout = new javax.swing.GroupLayout(p_req_bahan);
        p_req_bahan.setLayout(p_req_bahanLayout);
        p_req_bahanLayout.setHorizontalGroup(
            p_req_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 280, Short.MAX_VALUE)
            .addGroup(p_req_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(p_req_bahanLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel15)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        p_req_bahanLayout.setVerticalGroup(
            p_req_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
            .addGroup(p_req_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(p_req_bahanLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel15)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        p_kirim_barang.setBackground(new java.awt.Color(0, 204, 204));
        p_kirim_barang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ClickMenu(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Kirim Produk");

        javax.swing.GroupLayout p_kirim_barangLayout = new javax.swing.GroupLayout(p_kirim_barang);
        p_kirim_barang.setLayout(p_kirim_barangLayout);
        p_kirim_barangLayout.setHorizontalGroup(
            p_kirim_barangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 280, Short.MAX_VALUE)
            .addGroup(p_kirim_barangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(p_kirim_barangLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel16)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        p_kirim_barangLayout.setVerticalGroup(
            p_kirim_barangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
            .addGroup(p_kirim_barangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(p_kirim_barangLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel16)
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
                    .addComponent(p_req_produksi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(p_req_bahan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(p_kirim_barang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(p_req_produksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(p_req_bahan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(p_kirim_barang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        body.setBackground(new java.awt.Color(0, 204, 204));
        body.setLayout(new java.awt.CardLayout());

        Utama.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout UtamaLayout = new javax.swing.GroupLayout(Utama);
        Utama.setLayout(UtamaLayout);
        UtamaLayout.setHorizontalGroup(
            UtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 889, Short.MAX_VALUE)
        );
        UtamaLayout.setVerticalGroup(
            UtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 656, Short.MAX_VALUE)
        );

        body.add(Utama, "card11");

        masuk_bahan.setBackground(new java.awt.Color(0, 204, 204));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("ID Request");

        masuk_bahan_id.setPreferredSize(new java.awt.Dimension(59, 35));

        masuk_bahan_supid.setPreferredSize(new java.awt.Dimension(59, 35));

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

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel22.setText("Supplier Nama");

        masuk_bahan_supnama.setPreferredSize(new java.awt.Dimension(59, 35));

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

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel23.setText("Jenis Item");

        masuk_bahan_jenis.setPreferredSize(new java.awt.Dimension(59, 35));

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel24.setText("Jumlah Item");

        masuk_bahan_jumlah.setPreferredSize(new java.awt.Dimension(59, 35));

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel25.setText("Tanggal Terima");

        masuk_bahan_tgl.setPreferredSize(new java.awt.Dimension(59, 35));

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel26.setText("Status Pengecekan");

        masuk_bahan_status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih", "Sesuai", "Bermasalah" }));
        masuk_bahan_status.setPreferredSize(new java.awt.Dimension(59, 35));

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

        jButton1.setBackground(new java.awt.Color(0, 153, 255));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Tampilkan Item");
        jButton1.setPreferredSize(new java.awt.Dimension(141, 40));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout masuk_bahanLayout = new javax.swing.GroupLayout(masuk_bahan);
        masuk_bahan.setLayout(masuk_bahanLayout);
        masuk_bahanLayout.setHorizontalGroup(
            masuk_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(masuk_bahanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(masuk_bahanLayout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addGroup(masuk_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(masuk_bahanLayout.createSequentialGroup()
                        .addGroup(masuk_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel21)
                            .addComponent(jLabel2)
                            .addGroup(masuk_bahanLayout.createSequentialGroup()
                                .addComponent(masuk_bahan_id, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(masuk_bahan_cari, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(masuk_bahan_supid, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                        .addGroup(masuk_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(masuk_bahanLayout.createSequentialGroup()
                                .addGroup(masuk_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(masuk_bahan_jenis, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel23))
                                .addGap(10, 10, 10)
                                .addGroup(masuk_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel24)
                                    .addComponent(masuk_bahan_jumlah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(masuk_bahan_supnama, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                        .addGroup(masuk_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel25)
                            .addComponent(masuk_bahan_tgl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel26)
                            .addComponent(masuk_bahan_status, 0, 230, Short.MAX_VALUE))
                        .addContainerGap(49, Short.MAX_VALUE))
                    .addGroup(masuk_bahanLayout.createSequentialGroup()
                        .addComponent(masuk_bahan_batal, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(masuk_bahan_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        masuk_bahanLayout.setVerticalGroup(
            masuk_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(masuk_bahanLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(masuk_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(masuk_bahanLayout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(masuk_bahan_tgl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(masuk_bahan_status, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(masuk_bahanLayout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(masuk_bahan_supnama, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(masuk_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24)
                            .addComponent(jLabel23))
                        .addGap(5, 5, 5)
                        .addGroup(masuk_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(masuk_bahan_jumlah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(masuk_bahan_jenis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(masuk_bahanLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(masuk_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(masuk_bahan_id, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(masuk_bahan_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel21)
                        .addGap(5, 5, 5)
                        .addComponent(masuk_bahan_supid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(38, 38, 38)
                .addGroup(masuk_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(masuk_bahan_batal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(masuk_bahan_simpan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(90, Short.MAX_VALUE))
        );

        body.add(masuk_bahan, "card2");

        masuk_produk.setBackground(new java.awt.Color(0, 204, 204));

        jLabel3.setText("Masuk Produk");

        javax.swing.GroupLayout masuk_produkLayout = new javax.swing.GroupLayout(masuk_produk);
        masuk_produk.setLayout(masuk_produkLayout);
        masuk_produkLayout.setHorizontalGroup(
            masuk_produkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 889, Short.MAX_VALUE)
            .addGroup(masuk_produkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(masuk_produkLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel3)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        masuk_produkLayout.setVerticalGroup(
            masuk_produkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 656, Short.MAX_VALUE)
            .addGroup(masuk_produkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(masuk_produkLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel3)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        body.add(masuk_produk, "card3");

        keluar_bahan.setBackground(new java.awt.Color(0, 204, 204));

        jLabel4.setText("Keluar Bahan");

        javax.swing.GroupLayout keluar_bahanLayout = new javax.swing.GroupLayout(keluar_bahan);
        keluar_bahan.setLayout(keluar_bahanLayout);
        keluar_bahanLayout.setHorizontalGroup(
            keluar_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 889, Short.MAX_VALUE)
            .addGroup(keluar_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(keluar_bahanLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel4)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        keluar_bahanLayout.setVerticalGroup(
            keluar_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 656, Short.MAX_VALUE)
            .addGroup(keluar_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(keluar_bahanLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel4)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        body.add(keluar_bahan, "card4");

        keluar_barang.setBackground(new java.awt.Color(0, 204, 204));

        jLabel5.setText("Keluar Barang");

        javax.swing.GroupLayout keluar_barangLayout = new javax.swing.GroupLayout(keluar_barang);
        keluar_barang.setLayout(keluar_barangLayout);
        keluar_barangLayout.setHorizontalGroup(
            keluar_barangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 889, Short.MAX_VALUE)
            .addGroup(keluar_barangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(keluar_barangLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel5)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        keluar_barangLayout.setVerticalGroup(
            keluar_barangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 656, Short.MAX_VALUE)
            .addGroup(keluar_barangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(keluar_barangLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel5)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        body.add(keluar_barang, "card5");

        request_produksi.setBackground(new java.awt.Color(0, 204, 204));

        jLabel6.setText("RequestProduk");

        javax.swing.GroupLayout request_produksiLayout = new javax.swing.GroupLayout(request_produksi);
        request_produksi.setLayout(request_produksiLayout);
        request_produksiLayout.setHorizontalGroup(
            request_produksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 889, Short.MAX_VALUE)
            .addGroup(request_produksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(request_produksiLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel6)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        request_produksiLayout.setVerticalGroup(
            request_produksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 656, Short.MAX_VALUE)
            .addGroup(request_produksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(request_produksiLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel6)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        body.add(request_produksi, "card6");

        permintaan_bahan.setBackground(new java.awt.Color(0, 204, 204));

        jLabel7.setText("Permintaan Bahan");

        javax.swing.GroupLayout permintaan_bahanLayout = new javax.swing.GroupLayout(permintaan_bahan);
        permintaan_bahan.setLayout(permintaan_bahanLayout);
        permintaan_bahanLayout.setHorizontalGroup(
            permintaan_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 889, Short.MAX_VALUE)
            .addGroup(permintaan_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(permintaan_bahanLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel7)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        permintaan_bahanLayout.setVerticalGroup(
            permintaan_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 656, Short.MAX_VALUE)
            .addGroup(permintaan_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(permintaan_bahanLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel7)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        body.add(permintaan_bahan, "card7");

        kirim_produk.setBackground(new java.awt.Color(0, 204, 204));

        jLabel8.setText("Kirim Produk");

        javax.swing.GroupLayout kirim_produkLayout = new javax.swing.GroupLayout(kirim_produk);
        kirim_produk.setLayout(kirim_produkLayout);
        kirim_produkLayout.setHorizontalGroup(
            kirim_produkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 889, Short.MAX_VALUE)
            .addGroup(kirim_produkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(kirim_produkLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel8)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        kirim_produkLayout.setVerticalGroup(
            kirim_produkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 656, Short.MAX_VALUE)
            .addGroup(kirim_produkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(kirim_produkLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel8)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        body.add(kirim_produk, "card8");

        halaman8.setBackground(new java.awt.Color(0, 204, 204));

        jLabel9.setText("Halaman Menu 8");

        javax.swing.GroupLayout halaman8Layout = new javax.swing.GroupLayout(halaman8);
        halaman8.setLayout(halaman8Layout);
        halaman8Layout.setHorizontalGroup(
            halaman8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 889, Short.MAX_VALUE)
            .addGroup(halaman8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(halaman8Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel9)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        halaman8Layout.setVerticalGroup(
            halaman8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 656, Short.MAX_VALUE)
            .addGroup(halaman8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(halaman8Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel9)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        body.add(halaman8, "card9");

        jLabel1.setText("halaman menu  profile");

        javax.swing.GroupLayout halaman9Layout = new javax.swing.GroupLayout(halaman9);
        halaman9.setLayout(halaman9Layout);
        halaman9Layout.setHorizontalGroup(
            halaman9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 889, Short.MAX_VALUE)
            .addGroup(halaman9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(halaman9Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        halaman9Layout.setVerticalGroup(
            halaman9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 656, Short.MAX_VALUE)
            .addGroup(halaman9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(halaman9Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        body.add(halaman9, "card10");

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
            request_produksi.setVisible(false);
            permintaan_bahan.setVisible(false);
            kirim_produk.setVisible(false);
            halaman8.setVisible(false);
            halaman9.setVisible(false);
        }
        if (evt.getSource() == gmasuk_produk) {
            Utama.setVisible(false);
            masuk_bahan.setVisible(false);
            masuk_produk.setVisible(true);
            keluar_bahan.setVisible(false);
            keluar_barang.setVisible(false);
            request_produksi.setVisible(false);
            permintaan_bahan.setVisible(false);
            kirim_produk.setVisible(false);
            halaman8.setVisible(false);
            halaman9.setVisible(false);
        }
        if (evt.getSource() == gkeluar_bahan) {
            Utama.setVisible(false);
            masuk_bahan.setVisible(false);
            masuk_produk.setVisible(false);
            keluar_bahan.setVisible(true);
            keluar_barang.setVisible(false);
            request_produksi.setVisible(false);
            permintaan_bahan.setVisible(false);
            kirim_produk.setVisible(false);
            halaman8.setVisible(false);
            halaman9.setVisible(false);
        }
        if (evt.getSource() == gkeluar_barang) {
            Utama.setVisible(false);
            masuk_bahan.setVisible(false);
            masuk_produk.setVisible(false);
            keluar_bahan.setVisible(false);
            keluar_barang.setVisible(true);
            request_produksi.setVisible(false);
            permintaan_bahan.setVisible(false);
            kirim_produk.setVisible(false);
            halaman8.setVisible(false);
            halaman9.setVisible(false);
        }
        if (evt.getSource() == p_req_produksi) {
            Utama.setVisible(false);
            masuk_bahan.setVisible(false);
            masuk_produk.setVisible(false);
            keluar_bahan.setVisible(false);
            keluar_barang.setVisible(false);
            request_produksi.setVisible(true);
            permintaan_bahan.setVisible(false);
            kirim_produk.setVisible(false);
            halaman8.setVisible(false);
            halaman9.setVisible(false);
        }
        if (evt.getSource() == p_req_bahan) {
            Utama.setVisible(false);
            masuk_bahan.setVisible(false);
            masuk_produk.setVisible(false);
            keluar_bahan.setVisible(false);
            keluar_barang.setVisible(false);
            request_produksi.setVisible(false);
            permintaan_bahan.setVisible(true);
            kirim_produk.setVisible(false);
            halaman8.setVisible(false);
            halaman9.setVisible(false);
        }
        if (evt.getSource() == p_kirim_barang) {
            Utama.setVisible(false);
            masuk_bahan.setVisible(false);
            masuk_produk.setVisible(false);
            keluar_bahan.setVisible(false);
            keluar_barang.setVisible(false);
            request_produksi.setVisible(false);
            permintaan_bahan.setVisible(false);
            kirim_produk.setVisible(true);
            halaman8.setVisible(false);
            halaman9.setVisible(false);
        }
        if (evt.getSource() == profile) {
            Utama.setVisible(false);
            masuk_bahan.setVisible(false);
            masuk_produk.setVisible(false);
            keluar_bahan.setVisible(false);
            keluar_barang.setVisible(false);
            request_produksi.setVisible(false);
            permintaan_bahan.setVisible(false);
            kirim_produk.setVisible(false);
            halaman8.setVisible(false);
            halaman9.setVisible(true);
        }
    }//GEN-LAST:event_ClickMenu

    private void masuk_bahan_batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_masuk_bahan_batalActionPerformed
        ClearMasukBahan();
    }//GEN-LAST:event_masuk_bahan_batalActionPerformed

    private void masuk_bahan_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_masuk_bahan_cariActionPerformed
        ShowBahanRequest sbr = new ShowBahanRequest();
        sbr.bgbelakang = this;
        sbr.setVisible(true);
        sbr.setResizable(false);
    }//GEN-LAST:event_masuk_bahan_cariActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (masuk_bahan_id.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Field ID Kosong!", "Peringatan", HEIGHT);
        } else {
            TableMasukBahan();
            masuk_bahan_batal.setVisible(true);
            masuk_bahan_simpan.setVisible(true);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void masuk_bahan_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_masuk_bahan_simpanActionPerformed
        String stat =  masuk_bahan_status.getSelectedItem().toString();
        
        if (stat.equals("Pilih")) {
            JOptionPane.showMessageDialog(null, "Field Kosong!", "Peringatan", HEIGHT);
        }
        else if (stat.equals("Sesuai")) {           
            SimpanMasukBahan();
        }
        else if (stat.equals("Bermasalah")) {
            UpdateMasukBahan();
        }
    }//GEN-LAST:event_masuk_bahan_simpanActionPerformed

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
            java.util.logging.Logger.getLogger(BagBelakang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BagBelakang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BagBelakang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BagBelakang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BagBelakang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Utama;
    private javax.swing.JPanel body;
    private javax.swing.JPanel gkeluar_bahan;
    private javax.swing.JPanel gkeluar_barang;
    private javax.swing.JPanel gmasuk_bahan;
    private javax.swing.JPanel gmasuk_produk;
    private javax.swing.JPanel halaman8;
    private javax.swing.JPanel halaman9;
    private javax.swing.JPanel head;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel keluar_bahan;
    private javax.swing.JPanel keluar_barang;
    private javax.swing.JPanel kirim_produk;
    private javax.swing.JLabel lbl_akses;
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
    private javax.swing.JTextField masuk_bahan_tgl;
    private javax.swing.JPanel masuk_produk;
    private javax.swing.JPanel p_kirim_barang;
    private javax.swing.JPanel p_req_bahan;
    private javax.swing.JPanel p_req_produksi;
    private javax.swing.JPanel permintaan_bahan;
    private javax.swing.JLabel profile;
    private javax.swing.JPanel request_produksi;
    private javax.swing.JPanel side;
    // End of variables declaration//GEN-END:variables
}
