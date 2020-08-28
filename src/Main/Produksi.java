package Main;

import Alert.logout;
import Helper.ViewController;
import MainExtend.BahanBaku;
import MainExtend.ShowBahanRequest;
import MainExtend.Show_KP_Produk;
import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Produksi extends javax.swing.JFrame {

    ImageIcon sucess = new ImageIcon(getClass().getResource("/Icon/checked.png"));
    ImageIcon invalid = new ImageIcon(getClass().getResource("/Icon/cancel.png"));
    ImageIcon warning = new ImageIcon(getClass().getResource("/Icon/warning.png"));
    PreparedStatement ps = null;
    ResultSet rs = null;
    private DefaultTableModel tableReqProduk;
    private DefaultTableModel Model;
    public String ex_id_bahan, ex_nama_bahan, ex_satuan_bahan, ex_stok_bahan;
    public String ex_id_produk, ex_nama_produk, ex_satuan_produk, ex_stok_produk, ex_jenis_produk, ex_kp_id;

    public Produksi() {
        initComponents();
        run();
        TglSekarang();
        p_req_bahan.setVisible(false);
        lbl_nama.setText(Login1.getnama());
        simpan_ubah_kirim.setVisible(false);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        this.setMaximizedBounds(env.getMaximumWindowBounds());
        this.setVisible(true);
    }

    private void run() {
        ReqProdukTable();
        ProdukID();
        KirimID();
    }

    public void itemTerpilihBahan() {
        BahanBaku sb = new BahanBaku();
        sb.pdbahan = this;
        req_id_bahan.setText(ex_id_bahan);
        req_bahan_nama.setText(ex_nama_bahan);
        req_bahan_satuan.setText(ex_satuan_bahan);
        req_bahan_stok.setText(ex_stok_bahan);
        
    }

    public void itemTerpilihSKP() {
        Show_KP_Produk skp = new Show_KP_Produk();
        skp.pdSHK = this;
        kp_idorder.setText(ex_kp_id);
        kp_produk_id.setText(ex_id_produk);
        kp_nama.setText(ex_nama_produk);
        kp_jenis.setText(ex_jenis_produk);
        kp_jml.setText(ex_stok_produk);
        kp_satuan.setText(ex_satuan_produk);
    }
    
    private void TglSekarang() {
        Date date = new Date();
        String myFormat = "EEEE, dd MMMM yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
        req_bahan_tgl.setText(sdf.format(date));
        req_tgl_selesai.setText(sdf.format(date));
        tgl_sekarang_produksi.setText(sdf.format(date));
    }

    protected void ResetField() {
        req_id.setText("");
        req_produk_id.setText("");
        req_nama.setText("");
        req_tgl_target.setText("");
        req_jenis.setText("");
        req_jml.setText("");
        req_satuan.setText("");
        req_jml_selesai.setText("");

        req_id_bahan.setText("");
        req_bahan_nama.setText("");
        req_bahan_stok.setText("");
        req_bahan_satuan.setText("");
        req_bahan_jml.setText("0");
        
        kp_produk_id.setText("");
        kp_nama.setText("");
        kp_jenis.setText("");
        kp_jml.setText("");
        kp_satuan.setText("");
    }

    protected void ProdukID() {
        try {
            String query = "select MAX(RIGHT(id,4)) AS NO  from orderbahan_produksi order by id desc";
            Date date = new Date();
            String myFormat = "yyyyMM";
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
            ps = Config.config.getConnection().prepareStatement(query);
            rs = ps.executeQuery(query);
            while (rs.next()) {
                if (rs.first() == false) {
                    req_bahan_id.setText(sdf.format(date) + "0001");
                } else {
                    rs.last();
                    int auto_id = rs.getInt(1) + 1;
                    String no = String.valueOf(auto_id);
                    int Nomor = no.length();
                    //MENGATUR jumlah 0
                    for (int j = 0; j < 4 - Nomor; j++) {
                        no = "0" + no;
                    }
                    req_bahan_id.setText(sdf.format(date) + no);
                }
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();//penanganan masalah
        }
    }
    
    protected void KirimID() {
        try {
            String query = "select MAX(RIGHT(id,3)) AS NO  from produksi_kirim order by id desc";
            Date date = new Date();
            String myFormat = "yyyyMM";
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
            ps = Config.config.getConnection().prepareStatement(query);
            rs = ps.executeQuery(query);
            while (rs.next()) {
                if (rs.first() == false) {
                    kp_id.setText("SIK" +sdf.format(date)+ "001");
                } else {
                    rs.last();
                    int auto_id = rs.getInt(1) + 1;
                    String no = String.valueOf(auto_id);
                    int Nomor = no.length();
                    //MENGATUR jumlah 0
                    for (int j = 0; j < 3 - Nomor; j++) {
                        no = "0" + no;
                    }
                    kp_id.setText("SIK" +sdf.format(date) + no);
                }
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();//penanganan masalah
        }
    }

    protected void ReqProdukTable() {

        Object[] Baris = {"ID", "Produk ID", "Produk Nama", "Jenis Produk", "Target Tanggal", "Jumlah Permintaan", "Satuan"};
        tableReqProduk = new DefaultTableModel(null, Baris);

        try {
            String sql = "SELECT id, id_produk, nama_produk, jenis_produk, tgl_target, qty, satuan FROM order_produk where status='Request' order by tgl_target asc";
            Statement stat = Config.config.getConnection().createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()) {
                tableReqProduk.addRow(new Object[]{
                    hasil.getString(1),
                    hasil.getString(2),
                    hasil.getString(3),
                    hasil.getString(4),
                    hasil.getString(5),
                    hasil.getString(6),
                    hasil.getString(7)
                });
            }
            req_produk_tbl.setModel(tableReqProduk);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal Memuat: " + e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        head = new javax.swing.JPanel();
        profile = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        lbl_nama = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        tgl_sekarang_produksi = new javax.swing.JLabel();
        txt_menu_produksi = new javax.swing.JLabel();
        side = new javax.swing.JPanel();
        p_req_produksi = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        p_req_bahan = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        p_kirim_barang = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        keluar = new javax.swing.JPanel();
        jLabel106 = new javax.swing.JLabel();
        body = new javax.swing.JPanel();
        Utama = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        task = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        req_produk_tbl = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        req_id = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        req_tgl_target = new javax.swing.JTextField();
        req_produk_id = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        req_nama = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        req_jenis = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        req_jml = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        req_satuan = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        req_tgl_selesai = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        req_jml_selesai = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        req_bahan = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        req_bahan_table = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        req_id_bahan = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        req_bahan_nama = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        req_bahan_tgl = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        req_bahan_stok = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        req_bahan_id = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        req_bahan_satuan = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        req_bahan_jml = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        produk_jadi = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        kp_tambah = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        kp_id = new javax.swing.JTextField();
        jButton10 = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        kp_produk_id = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        kp_nama = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        kp_jml = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        kp_satuan = new javax.swing.JTextField();
        kp_jenis = new javax.swing.JTextField();
        kp_idorder = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        kp_tbl_list = new javax.swing.JTable();
        kp_simpan = new javax.swing.JButton();
        kp_batal = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        kp_tbl_riwayat = new javax.swing.JTable();
        jLabel29 = new javax.swing.JLabel();
        simpan_ubah_kirim = new javax.swing.JButton();

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

        jButton11.setBackground(new java.awt.Color(0, 102, 204));
        jButton11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton11.setForeground(new java.awt.Color(255, 255, 255));
        jButton11.setText("Refresh");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        tgl_sekarang_produksi.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tgl_sekarang_produksi.setText("jLabel30");

        txt_menu_produksi.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_menu_produksi.setText("Selamat Datang");
        txt_menu_produksi.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout headLayout = new javax.swing.GroupLayout(head);
        head.setLayout(headLayout);
        headLayout.setHorizontalGroup(
            headLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headLayout.createSequentialGroup()
                .addGroup(headLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(headLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_nama))
                    .addGroup(headLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(profile)))
                .addGap(216, 216, 216)
                .addComponent(txt_menu_produksi)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(headLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton11, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tgl_sekarang_produksi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        headLayout.setVerticalGroup(
            headLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(headLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_menu_produksi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(headLayout.createSequentialGroup()
                        .addGroup(headLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(headLayout.createSequentialGroup()
                                .addComponent(tgl_sekarang_produksi)
                                .addGap(10, 10, 10)
                                .addComponent(jButton11))
                            .addGroup(headLayout.createSequentialGroup()
                                .addComponent(profile)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(headLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel18)
                                    .addComponent(lbl_nama))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        side.setBackground(new java.awt.Color(255, 255, 255));

        p_req_produksi.setBackground(new java.awt.Color(0, 204, 204));
        p_req_produksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ClickMenu(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Permintaan Produk");

        javax.swing.GroupLayout p_req_produksiLayout = new javax.swing.GroupLayout(p_req_produksi);
        p_req_produksi.setLayout(p_req_produksiLayout);
        p_req_produksiLayout.setHorizontalGroup(
            p_req_produksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(p_req_produksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(p_req_produksiLayout.createSequentialGroup()
                    .addGap(0, 61, Short.MAX_VALUE)
                    .addComponent(jLabel14)
                    .addGap(0, 62, Short.MAX_VALUE)))
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
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(p_req_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(p_req_bahanLayout.createSequentialGroup()
                    .addGap(0, 64, Short.MAX_VALUE)
                    .addComponent(jLabel15)
                    .addGap(0, 65, Short.MAX_VALUE)))
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
        jLabel16.setText("Simpan Produk");

        javax.swing.GroupLayout p_kirim_barangLayout = new javax.swing.GroupLayout(p_kirim_barang);
        p_kirim_barang.setLayout(p_kirim_barangLayout);
        p_kirim_barangLayout.setHorizontalGroup(
            p_kirim_barangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(p_kirim_barangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(p_kirim_barangLayout.createSequentialGroup()
                    .addGap(0, 75, Short.MAX_VALUE)
                    .addComponent(jLabel16)
                    .addGap(0, 76, Short.MAX_VALUE)))
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

        keluar.setBackground(new java.awt.Color(255, 0, 0));
        keluar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        keluar.setMinimumSize(new java.awt.Dimension(0, 30));
        keluar.setPreferredSize(new java.awt.Dimension(113, 45));
        keluar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                keluarMenuClick(evt);
            }
        });

        jLabel106.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel106.setForeground(new java.awt.Color(255, 255, 255));
        jLabel106.setText("Keluar");

        javax.swing.GroupLayout keluarLayout = new javax.swing.GroupLayout(keluar);
        keluar.setLayout(keluarLayout);
        keluarLayout.setHorizontalGroup(
            keluarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(keluarLayout.createSequentialGroup()
                .addContainerGap(106, Short.MAX_VALUE)
                .addComponent(jLabel106)
                .addContainerGap(106, Short.MAX_VALUE))
        );
        keluarLayout.setVerticalGroup(
            keluarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(keluarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel106, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout sideLayout = new javax.swing.GroupLayout(side);
        side.setLayout(sideLayout);
        sideLayout.setHorizontalGroup(
            sideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sideLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(sideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(p_req_produksi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(p_req_bahan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(p_kirim_barang, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(keluar, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE))
                .addContainerGap())
        );
        sideLayout.setVerticalGroup(
            sideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sideLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(p_req_produksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(p_req_bahan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(p_kirim_barang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(keluar, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        body.setBackground(new java.awt.Color(0, 204, 204));
        body.setLayout(new java.awt.CardLayout());

        Utama.setBackground(new java.awt.Color(255, 255, 255));

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel30.setText("Jl. Daan Mogot Km 10 No. 46 Kedaung Kali Angke Kec. Cengkareng Jakarta Barat");

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel31.setText("PT. SETIA KAWAN ABADI");

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/logo_ska.png"))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel32)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel33.setText("Aplikasi Pengelolaan Bahan Baku dan Produk Jadi");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(159, Short.MAX_VALUE)
                .addComponent(jLabel33)
                .addContainerGap(159, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addComponent(jLabel33)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout UtamaLayout = new javax.swing.GroupLayout(Utama);
        Utama.setLayout(UtamaLayout);
        UtamaLayout.setHorizontalGroup(
            UtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UtamaLayout.createSequentialGroup()
                .addGroup(UtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(UtamaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(UtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(UtamaLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel30)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(UtamaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel31)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        UtamaLayout.setVerticalGroup(
            UtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, UtamaLayout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addComponent(jLabel31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel30)
                .addGap(72, 72, 72))
        );

        body.add(Utama, "card11");

        task.setBackground(new java.awt.Color(0, 204, 204));

        req_produk_tbl.setModel(new javax.swing.table.DefaultTableModel(
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
        req_produk_tbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                req_produk_tblMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(req_produk_tbl);

        jPanel1.setBackground(new java.awt.Color(0, 204, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Permintaan Produk"));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Permintaan ID");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Produk ID");

        req_id.setEnabled(false);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Target Pengerjaan");

        req_tgl_target.setEnabled(false);

        req_produk_id.setEnabled(false);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Nama Produk");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Jenis Produk");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Jumlah Permintaan");

        req_jml.setEnabled(false);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("Satuan");

        req_satuan.setEnabled(false);

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("Tanggal Selesai");

        req_tgl_selesai.setEnabled(false);

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setText("Jumlah DiBuat");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(req_tgl_target, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                        .addComponent(req_id, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5)
                    .addComponent(jLabel3)
                    .addComponent(req_produk_id)
                    .addComponent(req_nama, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel9)
                            .addComponent(req_jml, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(req_satuan)))
                    .addComponent(req_jenis, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(req_tgl_selesai, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(req_jml_selesai, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(req_tgl_selesai, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(req_jenis)
                    .addComponent(req_produk_id)
                    .addComponent(req_id))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(req_satuan, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(req_tgl_target)
                    .addComponent(req_nama)
                    .addComponent(req_jml)
                    .addComponent(req_jml_selesai))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jButton1.setBackground(new java.awt.Color(255, 0, 0));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setText("Batal");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(0, 102, 204));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setText("Simpan");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Permintaan Masuk");

        javax.swing.GroupLayout taskLayout = new javax.swing.GroupLayout(task);
        task.setLayout(taskLayout);
        taskLayout.setHorizontalGroup(
            taskLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(taskLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(taskLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, taskLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        taskLayout.setVerticalGroup(
            taskLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, taskLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(taskLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(taskLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
                .addContainerGap())
        );

        body.add(task, "card6");

        req_bahan.setBackground(new java.awt.Color(0, 204, 204));

        req_bahan_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
            }
        ));
        jScrollPane2.setViewportView(req_bahan_table);

        jPanel2.setBackground(new java.awt.Color(0, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Request Bahan Baku", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        jLabel13.setText("Bahan ID");

        req_id_bahan.setEnabled(false);

        jButton6.setText("Cari");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel19.setText("Bahan Nama");

        req_bahan_nama.setPreferredSize(new java.awt.Dimension(59, 30));

        jLabel23.setText("Tanggal");

        req_bahan_tgl.setEnabled(false);

        jLabel24.setText("Stok");

        jButton5.setText("Tambah");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        req_bahan_id.setEnabled(false);

        jLabel20.setText("Invoice");

        jLabel21.setText("Satuan");

        jLabel22.setText("Jumlah");

        req_bahan_jml.setText("0");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel13)
                    .addComponent(jLabel20)
                    .addComponent(req_bahan_id, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(req_id_bahan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23)
                    .addComponent(jLabel19)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(req_bahan_stok, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel24))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel21)
                                    .addGap(78, 78, 78))
                                .addComponent(req_bahan_satuan)))
                        .addComponent(req_bahan_nama, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(req_bahan_tgl, javax.swing.GroupLayout.Alignment.LEADING)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)
                    .addComponent(req_bahan_jml, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(req_bahan_tgl, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(req_bahan_id, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel19)
                    .addComponent(jLabel22))
                .addGap(4, 4, 4)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(req_bahan_jml)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(req_id_bahan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(req_bahan_nama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(req_bahan_stok, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(req_bahan_satuan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jButton3.setBackground(new java.awt.Color(255, 0, 0));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Batal");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(0, 102, 204));
        jButton4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Simpan");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(jTable2);

        jLabel7.setText("Data Bahan Baku");

        javax.swing.GroupLayout req_bahanLayout = new javax.swing.GroupLayout(req_bahan);
        req_bahan.setLayout(req_bahanLayout);
        req_bahanLayout.setHorizontalGroup(
            req_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(req_bahanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(req_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 908, Short.MAX_VALUE)
                    .addGroup(req_bahanLayout.createSequentialGroup()
                        .addGroup(req_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(req_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(req_bahanLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE))))
                .addContainerGap())
        );
        req_bahanLayout.setVerticalGroup(
            req_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, req_bahanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(req_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(req_bahanLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(req_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, req_bahanLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                        .addComponent(jLabel7)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)
                .addContainerGap())
        );

        body.add(req_bahan, "card7");

        produk_jadi.setBackground(new java.awt.Color(0, 204, 204));

        jPanel3.setBackground(new java.awt.Color(0, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Kirim Produk Jadi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        kp_tambah.setText("Tambah");
        kp_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kp_tambahActionPerformed(evt);
            }
        });

        jLabel8.setText("No.Keluar Produk");

        kp_id.setEnabled(false);

        jButton10.setText("Cari");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jLabel25.setText("Data Produk");

        kp_produk_id.setEnabled(false);

        jLabel26.setText("Nama Produk");

        jLabel17.setText("Jenis Produk");

        jLabel27.setText("Jumlah");

        jLabel28.setText("Satuan");

        kp_idorder.setText("jLabel30");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel26)
                    .addComponent(jLabel17)
                    .addComponent(jLabel8)
                    .addComponent(jLabel25)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(kp_produk_id, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                            .addComponent(kp_id, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton10))
                    .addComponent(kp_nama)
                    .addComponent(kp_jenis))
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(kp_tambah, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                        .addComponent(jLabel27)
                        .addComponent(jLabel28)
                        .addComponent(kp_satuan)
                        .addComponent(kp_jml, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(kp_idorder))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(kp_id, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(kp_idorder))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(jLabel27))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kp_produk_id, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(kp_jml, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(jLabel28))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(kp_satuan, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(kp_nama))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kp_jenis, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(kp_tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );

        kp_tbl_list.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
            }
        ));
        jScrollPane4.setViewportView(kp_tbl_list);

        kp_simpan.setText("Simpan");
        kp_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kp_simpanActionPerformed(evt);
            }
        });

        kp_batal.setText("Batal");

        kp_tbl_riwayat.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane5.setViewportView(kp_tbl_riwayat);

        jLabel29.setText("Riwayat Aktivitas");

        simpan_ubah_kirim.setText("Simpan");

        javax.swing.GroupLayout produk_jadiLayout = new javax.swing.GroupLayout(produk_jadi);
        produk_jadi.setLayout(produk_jadiLayout);
        produk_jadiLayout.setHorizontalGroup(
            produk_jadiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(produk_jadiLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(produk_jadiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5)
                    .addGroup(produk_jadiLayout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(produk_jadiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)
                            .addGroup(produk_jadiLayout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(kp_batal, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(kp_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(simpan_ubah_kirim, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(produk_jadiLayout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        produk_jadiLayout.setVerticalGroup(
            produk_jadiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(produk_jadiLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(produk_jadiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, produk_jadiLayout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(produk_jadiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(produk_jadiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(kp_batal, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(kp_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(simpan_ubah_kirim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(18, 18, 18)
                .addComponent(jLabel29)
                .addGap(5, 5, 5)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                .addContainerGap())
        );

        body.add(produk_jadi, "card8");

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

        if (evt.getSource() == p_req_produksi) {
            Utama.setVisible(false);
            task.setVisible(true);
            req_bahan.setVisible(false);
            produk_jadi.setVisible(false);
            p_req_produksi.setBackground(new Color(0, 153, 153));//dark
//            p_req_bahan.setBackground(new Color(0, 204, 204));//dark
            p_kirim_barang.setBackground(new Color(0, 204, 204));//dark
            txt_menu_produksi.setText("Permintaan Produksi");
            p_req_bahan.setVisible(false);
            run();
        }
//        if (evt.getSource() == p_req_bahan) {
//            Utama.setVisible(false);
//            task.setVisible(false);
//            req_bahan.setVisible(true);
//            produk_jadi.setVisible(false);
//            p_req_bahan.setBackground(new Color(0, 153, 153));//dark
//            p_req_produksi.setBackground(new Color(0, 204, 204));//dark
//            p_kirim_barang.setBackground(new Color(0, 204, 204));//dark
//            txt_menu_produksi.setText("Permintaan Bahan");
//            run();
//        }
        if (evt.getSource() == p_kirim_barang) {
            Utama.setVisible(false);
            task.setVisible(false);
            req_bahan.setVisible(false);
            produk_jadi.setVisible(true);
            p_kirim_barang.setBackground(new Color(0, 153, 153));//dark
//            p_req_bahan.setBackground(new Color(0, 204, 204));//dark
            p_req_produksi.setBackground(new Color(0, 204, 204));//dark
            txt_menu_produksi.setText("Simpan Produk");
            p_req_bahan.setVisible(false);
            run();
        }
    }//GEN-LAST:event_ClickMenu

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        int stok = Integer.parseInt(req_bahan_stok.getText());
        int jml = Integer.parseInt(req_bahan_jml.getText());
        if (req_bahan_jml.getText().equals("0")) {
            JOptionPane.showMessageDialog(null, "Permintaan Kosong!", "Peringatan!", HEIGHT, warning);

        } else if (jml > stok) {
            JOptionPane.showMessageDialog(null, "Stok Kurang Dari Permintaan!", "Peringatan!", HEIGHT, warning);
        } else {

            DefaultTableModel model = (DefaultTableModel) req_bahan_table.getModel();
            model.addRow(new Object[]{
                req_bahan_id.getText(), req_bahan_tgl.getText(), req_id_bahan.getText(),
                req_bahan_nama.getText(), req_bahan_jml.getText(), req_bahan_satuan.getText()});
            req_bahan_table.setModel(model);
            ResetField();
        }

    }//GEN-LAST:event_jButton5ActionPerformed

    private void req_produk_tblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_req_produk_tblMouseClicked
        int bar = req_produk_tbl.getSelectedRow();
        String a = req_produk_tbl.getValueAt(bar, 0).toString();
        String b = req_produk_tbl.getValueAt(bar, 1).toString();
        String c = req_produk_tbl.getValueAt(bar, 2).toString();
        String d = req_produk_tbl.getValueAt(bar, 3).toString();
        String e = req_produk_tbl.getValueAt(bar, 4).toString();
        String f = req_produk_tbl.getValueAt(bar, 5).toString();
        String g = req_produk_tbl.getValueAt(bar, 6).toString();

        req_id.setText(a);
        req_produk_id.setText(b);
        req_nama.setText(c);
        req_jenis.setText(d);
        req_tgl_target.setText(e);
        req_jml.setText(f);
        req_satuan.setText(g);
//        bahan_id.setText(a);
//        bahan_nama.setText(b);
//        bahan_jenis.setSelectedItem(c);
//        bahan_satuan.setSelectedItem(d);
    }//GEN-LAST:event_req_produk_tblMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String Status = "Di Proses";
        if (req_id.getText().isEmpty() || req_jml_selesai.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Periksa Data!", "Peringatan!", HEIGHT, warning);
        } else {
            try {
                String sql = "update order_produk set tgl_selesai=?,qty_jadi=?,status=? where id='" + req_id.getText() + "'";
                PreparedStatement stat = Config.config.getConnection().prepareStatement(sql);
                stat.setString(1, req_tgl_selesai.getText());
                stat.setString(2, req_jml_selesai.getText());
                stat.setString(3, Status);
                stat.executeUpdate();
                ResetField();
                run();
                JOptionPane.showMessageDialog(null, "Data Berhasil Di Simpan!", "Berhasil!", HEIGHT, sucess);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Terjadi Kesalahan!", "Perhatian!", HEIGHT, invalid);
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ResetField();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        BahanBaku ssp = new BahanBaku();
        ssp.pdbahan = this;
        ssp.setVisible(true);
        ssp.setResizable(false);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        ResetField();

        ((DefaultTableModel) req_bahan_table.getModel()).setNumRows(0);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int x = req_bahan_table.getModel().getRowCount();
        if (x == 0) {

            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan!", "Perhatian!", HEIGHT, invalid);
        } else {
            String query2 = "INSERT INTO `orderbahan_produksi`(`id`, `id_bahan`, `nama_bahan`, `jumlah`, `satuan`, `tanggal`, `status`) VALUES (?,?,?,?,?,?,?)";
            String status = "Request";
            try {
                int t = req_bahan_table.getRowCount();
                for (int i = 0; i < t; i++) {
                    String id = req_bahan_table.getValueAt(i, 0).toString();
                    String idbahan = req_bahan_table.getValueAt(i, 2).toString();
                    String nama = req_bahan_table.getValueAt(i, 3).toString();
                    String jml = req_bahan_table.getValueAt(i, 4).toString();
                    String satuan = req_bahan_table.getValueAt(i, 5).toString();
                    String tgl = req_bahan_table.getValueAt(i, 1).toString();
//
                    PreparedStatement stat2 = Config.config.getConnection().prepareStatement(query2);
                    stat2.setString(1, id);
                    stat2.setString(2, idbahan);
                    stat2.setString(3, nama);
                    stat2.setString(4, jml);
                    stat2.setString(5, satuan);
                    stat2.setString(6, tgl);
                    stat2.setString(7, status);
                    stat2.executeUpdate();
                }
                ProdukID();
                ResetField();
                ((DefaultTableModel) req_bahan_table.getModel()).setNumRows(0);
                JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan..", "Berhasil!", HEIGHT, sucess);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Terjadi Kesalahan!", "Perhatian!", HEIGHT, invalid);

            }
        }


    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        run();
        ResetField();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void keluarMenuClick(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_keluarMenuClick
        Login1 awal = new Login1();
        int ok = JOptionPane.showConfirmDialog(null, "Apakah Yakin Akan Keluar???", "Perhatian!", JOptionPane.YES_NO_OPTION, HEIGHT, warning);
        if (ok == 0) {
            awal.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_keluarMenuClick

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        Show_KP_Produk skp = new Show_KP_Produk();
        skp.pdSHK = this;
        skp.setVisible(true);
        skp.setResizable(false);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void kp_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kp_simpanActionPerformed
        String query2 = "INSERT INTO `produksi_kirim`(`id`, `id_produk`, `nama`, `jenis`, `qty`, `satuan`, `tanggal`) VALUES (?,?,?,?,?,?,?)";
        String tanggal = tgl_sekarang_produksi.getText().trim();
        try {
                int t = kp_tbl_list.getRowCount();
                for (int i = 0; i < t; i++) {
                    String id = kp_tbl_list.getValueAt(i, 0).toString();
                    String idproduk = kp_tbl_list.getValueAt(i, 1).toString();
                    String nama = kp_tbl_list.getValueAt(i, 2).toString();
                    String jenis = kp_tbl_list.getValueAt(i, 3).toString();
                    String qty = kp_tbl_list.getValueAt(i, 4).toString();
                    String satuan = kp_tbl_list.getValueAt(i, 5).toString();
                    PreparedStatement stat2 = Config.config.getConnection().prepareStatement(query2);
                    stat2.setString(1, id);
                    stat2.setString(2, idproduk);
                    stat2.setString(3, nama);
                    stat2.setString(4, jenis);
                    stat2.setString(5, qty);
                    stat2.setString(6, satuan);
                    stat2.setString(7, tanggal);
                    stat2.executeUpdate();
                    
                    UpdateKeluarProduk();
                }
                run();
                ResetField();
                ((DefaultTableModel) kp_tbl_list.getModel()).setNumRows(0);
                JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan..", "Berhasil!", HEIGHT, sucess);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Terjadi Kesalahan!"+e.getMessage(), "Perhatian!", HEIGHT, invalid);

            }
    }//GEN-LAST:event_kp_simpanActionPerformed

    private void kp_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kp_tambahActionPerformed
        if (kp_jml.getText().isEmpty() || kp_id.getText().isEmpty() || kp_produk_id.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Permintaan Kosong!", "Peringatan!", HEIGHT, warning);

        }else {
            DefaultTableModel mdl = (DefaultTableModel) kp_tbl_list.getModel();
            mdl.addRow(new Object[]{
                kp_id.getText(), kp_produk_id.getText(), kp_nama.getText(),
                kp_jenis.getText(), kp_jml.getText(), kp_satuan.getText()});
            kp_tbl_list.setModel(mdl);
            ResetField();
        }
    }//GEN-LAST:event_kp_tambahActionPerformed

    protected void UpdateKeluarProduk() {
        String status = "Di Kirim";
        try {
            String sql = "update order_produk set status=? where id='" + kp_idorder.getText() + "'";
            PreparedStatement stat = Config.config.getConnection().prepareStatement(sql);
            stat.setString(1, status);
            stat.executeUpdate();
//            JOptionPane.showMessageDialog(null, "Data Berhasil Di Simpan", "Berhasil!", HEIGHT, sucess);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan!", "Perhatian!", HEIGHT, invalid);
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
            java.util.logging.Logger.getLogger(Produksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Produksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Produksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Produksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Produksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Utama;
    private javax.swing.JPanel body;
    private javax.swing.JPanel head;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel106;
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
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel4;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTable2;
    private javax.swing.JPanel keluar;
    private javax.swing.JButton kp_batal;
    private javax.swing.JTextField kp_id;
    private javax.swing.JLabel kp_idorder;
    private javax.swing.JTextField kp_jenis;
    private javax.swing.JTextField kp_jml;
    private javax.swing.JTextField kp_nama;
    private javax.swing.JTextField kp_produk_id;
    private javax.swing.JTextField kp_satuan;
    private javax.swing.JButton kp_simpan;
    private javax.swing.JButton kp_tambah;
    private javax.swing.JTable kp_tbl_list;
    private javax.swing.JTable kp_tbl_riwayat;
    private javax.swing.JLabel lbl_nama;
    private javax.swing.JPanel p_kirim_barang;
    private javax.swing.JPanel p_req_bahan;
    private javax.swing.JPanel p_req_produksi;
    private javax.swing.JPanel produk_jadi;
    private javax.swing.JLabel profile;
    private javax.swing.JPanel req_bahan;
    private javax.swing.JTextField req_bahan_id;
    private javax.swing.JTextField req_bahan_jml;
    private javax.swing.JTextField req_bahan_nama;
    private javax.swing.JTextField req_bahan_satuan;
    private javax.swing.JTextField req_bahan_stok;
    private javax.swing.JTable req_bahan_table;
    private javax.swing.JTextField req_bahan_tgl;
    private javax.swing.JTextField req_id;
    private javax.swing.JTextField req_id_bahan;
    private javax.swing.JTextField req_jenis;
    private javax.swing.JTextField req_jml;
    private javax.swing.JTextField req_jml_selesai;
    private javax.swing.JTextField req_nama;
    private javax.swing.JTextField req_produk_id;
    private javax.swing.JTable req_produk_tbl;
    private javax.swing.JTextField req_satuan;
    private javax.swing.JTextField req_tgl_selesai;
    private javax.swing.JTextField req_tgl_target;
    private javax.swing.JPanel side;
    private javax.swing.JButton simpan_ubah_kirim;
    private javax.swing.JPanel task;
    private javax.swing.JLabel tgl_sekarang_produksi;
    private javax.swing.JLabel txt_menu_produksi;
    // End of variables declaration//GEN-END:variables
}
