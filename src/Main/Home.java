/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Helper.ClearDefault;
import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyEvent;
import static java.awt.image.ImageObserver.HEIGHT;
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

public class Home extends javax.swing.JFrame {

    ImageIcon sucess = new ImageIcon(getClass().getResource("/Icon/checked.png"));
    ImageIcon invalid = new ImageIcon(getClass().getResource("/Icon/cancel.png"));
    ImageIcon warning = new ImageIcon(getClass().getResource("/Icon/warning.png"));
    PreparedStatement ps = null;
    ResultSet rs = null;

    private DefaultTableModel tablemodelProduk;
    private DefaultTableModel tablemodelBahan;
    private DefaultTableModel tablemodelCustomer;
    private DefaultTableModel tablemodelSupplier;
    private DefaultTableModel tabmodeOrderBahan;
    private DefaultTableModel tabmodeOrderProduk;

    String ex_id_supplier, ex_nama_supplier, ex_email_supplier, ex_telp_supplier, ex_alamat_supplier, ex_jenis_supplier;
    String ex_id_bahan, ex_nama_bahan, ex_jenis_bahan, ex_satuan_bahan, ex_stok_bahan;
    String ex_produk_id, ex_produk_nama, ex_produk_kategori, ex_produk_jenis, ex_produk_satuan, ex_produk_qty;
    String ex_id_cust, ex_nama_cust, ex_jenis_cust, ex_email_cust, ex_telp_cust, ex_alamat_cust;
    String ex_stock_id, ex_stock_nama, ex_stock_kategori, ex_stock_jenis, ex_stock_qty, ex_stock_satuan, ex_stock_harga;

    public Home() {
        initComponents();
        HideMenu();
        BtHide();
        DisableField();
        ClearField();
        RunID();
        RunTable();
        TglSekarang();

        lbl_nama.setText(Login1.getnama());
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        this.setMaximizedBounds(env.getMaximumWindowBounds());
        this.setVisible(true);

    }

    protected void RunID() {
        ProdukID();
        BahanID();
        CustID();
        SuppID();
        ReqBahanID();
        ReqProdukID();
        ReqKirimID();
    }

    protected void RunTable() {
        BahanTable();
        ProdukTable();
        CustomerTable();
        SupplierTable();
        ReqProdukTable();
    }

    private void TglSekarang() {
        Date date = new Date();
        String myFormat = "EEEE, dd MMMM yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
        req_bahan_tgl.setText(sdf.format(date));
        req_produk_tgl.setText(sdf.format(date));
    }

    public void itemTerpilihSupplier() {
        ShowSupplier ssp = new ShowSupplier();
        ssp.hmSupplier = this;
        req_bahan_idsupp.setText(ex_id_supplier);
        req_bahan_namasup.setText(ex_nama_supplier);
        req_bahan_telp.setText(ex_telp_supplier);
    }

    public void itemTerpilihBahan() {
        ShowBahan sb = new ShowBahan();
        sb.hmBahan = this;
        req_bahan_idbahan.setText(ex_id_bahan);
        req_bahan_namabahan.setText(ex_nama_bahan);
        req_bahan_satuan.setSelectedItem(ex_satuan_bahan);
    }

    public void itemTerpilihProduk() {
        ShowProduk sp = new ShowProduk();
        sp.hmProduk = this;
        req_produk_idproduk.setText(ex_produk_id);
        req_produk_nama.setText(ex_produk_nama);
        req_produk_jenis.setText(ex_produk_jenis);
    }

    public void itemTerpilihStok() {
        ShowStokProduk stp = new ShowStokProduk();
        stp.hmStok = this;
        req_kirim_produkid.setText(ex_stock_id);
        req_kirim_produknama.setText(ex_stock_nama);
        req_kirim_harga.setText(ex_stock_harga);
    }

    public void itemTerpilihCustomer() {
        ShowCustomer sc = new ShowCustomer();
        sc.hmCustomer = this;
        req_kirim_custid.setText(ex_id_cust);
        req_kirim_nama.setText(ex_nama_cust);
        req_kirim_telp.setText(ex_telp_cust);
        req_kirim_alamat.setText(ex_alamat_cust);
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

    @SuppressWarnings("unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Main_Menu = new javax.swing.JPanel();
        null_request = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        sub_request = new javax.swing.JPanel();
        req_down = new javax.swing.JLabel();
        req_up = new javax.swing.JLabel();
        request_produk = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        request_bahan = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        request_kirim = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        nul_master = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        sub_master = new javax.swing.JPanel();
        master_down = new javax.swing.JLabel();
        master_up = new javax.swing.JLabel();
        master_produk = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        master_bahan = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        master_supplier = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        master_customer = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        sub_stock = new javax.swing.JPanel();
        stock_down = new javax.swing.JLabel();
        stock_up = new javax.swing.JLabel();
        null_stock = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        stock_produk_jadi = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        stock_bahan_baku = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        laporan = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        img_home = new javax.swing.JLabel();
        Header = new javax.swing.JPanel();
        title_page = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lbl_nama = new javax.swing.JLabel();
        Body = new javax.swing.JPanel();
        body_home = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        body_req_produk = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        req_produk_tabel = new javax.swing.JTable();
        jLabel59 = new javax.swing.JLabel();
        req_produk_id = new javax.swing.JTextField();
        jLabel60 = new javax.swing.JLabel();
        req_produk_idproduk = new javax.swing.JTextField();
        req_produk_cari = new javax.swing.JPanel();
        jLabel83 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        req_produk_nama = new javax.swing.JTextField();
        jLabel62 = new javax.swing.JLabel();
        req_produk_jenis = new javax.swing.JTextField();
        req_produk_tgl_target = new com.toedter.calendar.JDateChooser();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        req_produk_tgl = new javax.swing.JTextField();
        req_produk_simpan = new javax.swing.JPanel();
        jLabel80 = new javax.swing.JLabel();
        req_produk_batal = new javax.swing.JPanel();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        req_produk_jumlah = new javax.swing.JTextField();
        jLabel84 = new javax.swing.JLabel();
        req_produk_satuan = new javax.swing.JComboBox<>();
        body_req_bahan = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        req_bahan_tabel = new javax.swing.JTable();
        req_bahan_hapus = new javax.swing.JPanel();
        jLabel76 = new javax.swing.JLabel();
        req_bahan_tambah = new javax.swing.JPanel();
        jLabel77 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        req_bahan_id = new javax.swing.JTextField();
        req_bahan_tgl = new javax.swing.JTextField();
        req_bahan_idbahan = new javax.swing.JTextField();
        req_bahan_carisup = new javax.swing.JPanel();
        req_bahan_caribahan = new javax.swing.JPanel();
        req_bahan_namabahan = new javax.swing.JTextField();
        req_bahan_qtybahan = new javax.swing.JTextField();
        jLabel73 = new javax.swing.JLabel();
        req_bahan_satuan = new javax.swing.JComboBox<>();
        req_bahan_idsupp = new javax.swing.JTextField();
        req_bahan_namasup = new javax.swing.JTextField();
        req_bahan_telp = new javax.swing.JTextField();
        req_bahan_batal = new javax.swing.JPanel();
        jLabel74 = new javax.swing.JLabel();
        req_bahan_simpan = new javax.swing.JPanel();
        jLabel75 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        txt_jumlah_item = new javax.swing.JLabel();
        txt_total_ite = new javax.swing.JLabel();
        req_bahan_lihat = new javax.swing.JPanel();
        jLabel85 = new javax.swing.JLabel();
        body_req_kirim = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel86 = new javax.swing.JLabel();
        req_kirim_id = new javax.swing.JTextField();
        req_kirim_btCust = new javax.swing.JButton();
        jLabel87 = new javax.swing.JLabel();
        req_kirim_custid = new javax.swing.JTextField();
        jLabel88 = new javax.swing.JLabel();
        req_kirim_nama = new javax.swing.JTextField();
        jLabel89 = new javax.swing.JLabel();
        req_kirim_telp = new javax.swing.JTextField();
        jLabel90 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        req_kirim_alamat = new javax.swing.JTextArea();
        jLabel91 = new javax.swing.JLabel();
        req_kirim_produkid = new javax.swing.JTextField();
        jLabel92 = new javax.swing.JLabel();
        req_kirim_produknama = new javax.swing.JTextField();
        jLabel93 = new javax.swing.JLabel();
        req_kirim_harga = new javax.swing.JTextField();
        jLabel94 = new javax.swing.JLabel();
        req_kirim_qty = new javax.swing.JTextField();
        req_kirim_btProduk = new javax.swing.JButton();
        jLabel96 = new javax.swing.JLabel();
        req_kirim_subtot = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        req_kirim_btTambah = new javax.swing.JPanel();
        jLabel102 = new javax.swing.JLabel();
        req_kirim_btHapus = new javax.swing.JPanel();
        jLabel101 = new javax.swing.JLabel();
        req_kirim_btBatal = new javax.swing.JPanel();
        jLabel98 = new javax.swing.JLabel();
        req_kirim_btSimpan = new javax.swing.JPanel();
        jLabel100 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        req_kirim_tabel = new javax.swing.JTable();
        jLabel95 = new javax.swing.JLabel();
        txt_kirim_item = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        txt_kirim_total = new javax.swing.JLabel();
        jLabel99 = new javax.swing.JLabel();
        txt_kirim_totharga = new javax.swing.JLabel();
        body_master_produk = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        produk_tabel = new javax.swing.JTable();
        produk_tambah = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        produk_id = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        produk_nama = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        produk_kategori = new javax.swing.JComboBox<>();
        jLabel34 = new javax.swing.JLabel();
        produk_jenis = new javax.swing.JComboBox<>();
        jLabel36 = new javax.swing.JLabel();
        produk_satuan = new javax.swing.JComboBox<>();
        jLabel37 = new javax.swing.JLabel();
        produk_harga = new javax.swing.JTextField();
        produk_simpan = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        produk_edit = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        produk_delete = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        body_master_bahan = new javax.swing.JPanel();
        bahan_tambah = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        bahan_tabel = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        bahan_id = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        bahan_nama = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        bahan_jenis = new javax.swing.JComboBox<>();
        jLabel26 = new javax.swing.JLabel();
        bahan_satuan = new javax.swing.JComboBox<>();
        bahan_simpan = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        bahan_edit = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        bahan_delete = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        body_master_supplier = new javax.swing.JPanel();
        supplier_tambah = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        supplier_id = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        supplier_nama = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        supplier_email = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        supplier_telp = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        supplier_jenis = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        supplier_alamat = new javax.swing.JTextArea();
        jLabel46 = new javax.swing.JLabel();
        supplier_edit = new javax.swing.JPanel();
        jLabel47 = new javax.swing.JLabel();
        supplier_simpan = new javax.swing.JPanel();
        jLabel49 = new javax.swing.JLabel();
        supplier_delete = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        supplier_txt = new javax.swing.JLabel();
        supplier_status = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        supplier_tabel = new javax.swing.JTable();
        body_master_customer = new javax.swing.JPanel();
        customer_tambah = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        customer_simpan = new javax.swing.JPanel();
        jLabel56 = new javax.swing.JLabel();
        customer_hapus = new javax.swing.JPanel();
        jLabel57 = new javax.swing.JLabel();
        customer_edit = new javax.swing.JPanel();
        jLabel58 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        customer_id = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        customer_nama = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        customer_jenis = new javax.swing.JComboBox<>();
        jLabel53 = new javax.swing.JLabel();
        customer_email = new javax.swing.JTextField();
        jLabel54 = new javax.swing.JLabel();
        customer_telp = new javax.swing.JTextField();
        jLabel55 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        customer_alamat = new javax.swing.JTextArea();
        customer_txt = new javax.swing.JLabel();
        customer_status = new javax.swing.JComboBox<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        customer_tabel = new javax.swing.JTable();
        body_stok_produk = new javax.swing.JPanel();
        body_stok_bahan = new javax.swing.JPanel();
        body_laporan = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Main_Menu.setBackground(new java.awt.Color(255, 255, 255));

        null_request.setBackground(new java.awt.Color(0, 204, 204));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Request Order");

        javax.swing.GroupLayout null_requestLayout = new javax.swing.GroupLayout(null_request);
        null_request.setLayout(null_requestLayout);
        null_requestLayout.setHorizontalGroup(
            null_requestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, null_requestLayout.createSequentialGroup()
                .addContainerGap(57, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap(58, Short.MAX_VALUE))
        );
        null_requestLayout.setVerticalGroup(
            null_requestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(null_requestLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        sub_request.setBackground(new java.awt.Color(0, 204, 204));

        req_down.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/down16.png"))); // NOI18N
        req_down.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        req_down.setFocusable(false);
        req_down.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        req_down.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                OpenSubMenu(evt);
            }
        });

        req_up.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/up16.png"))); // NOI18N
        req_up.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        req_up.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        req_up.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        req_up.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                OpenSubMenu(evt);
            }
        });

        javax.swing.GroupLayout sub_requestLayout = new javax.swing.GroupLayout(sub_request);
        sub_request.setLayout(sub_requestLayout);
        sub_requestLayout.setHorizontalGroup(
            sub_requestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
            .addGroup(sub_requestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sub_requestLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(req_down, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)))
            .addGroup(sub_requestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sub_requestLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(req_up, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)))
        );
        sub_requestLayout.setVerticalGroup(
            sub_requestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(sub_requestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(req_down, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
            .addGroup(sub_requestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(req_up, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
        );

        request_produk.setBackground(new java.awt.Color(0, 204, 204));
        request_produk.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        request_produk.setPreferredSize(new java.awt.Dimension(156, 40));
        request_produk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MenuClick(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Produk");

        javax.swing.GroupLayout request_produkLayout = new javax.swing.GroupLayout(request_produk);
        request_produk.setLayout(request_produkLayout);
        request_produkLayout.setHorizontalGroup(
            request_produkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(request_produkLayout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        request_produkLayout.setVerticalGroup(
            request_produkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(request_produkLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
                .addContainerGap())
        );

        request_bahan.setBackground(new java.awt.Color(0, 204, 204));
        request_bahan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        request_bahan.setPreferredSize(new java.awt.Dimension(155, 40));
        request_bahan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MenuClick(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Bahan");

        javax.swing.GroupLayout request_bahanLayout = new javax.swing.GroupLayout(request_bahan);
        request_bahan.setLayout(request_bahanLayout);
        request_bahanLayout.setHorizontalGroup(
            request_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(request_bahanLayout.createSequentialGroup()
                .addGap(101, 101, 101)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        request_bahanLayout.setVerticalGroup(
            request_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(request_bahanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
                .addContainerGap())
        );

        request_kirim.setBackground(new java.awt.Color(0, 204, 204));
        request_kirim.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        request_kirim.setPreferredSize(new java.awt.Dimension(155, 40));
        request_kirim.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MenuClick(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Kirim Barang");

        javax.swing.GroupLayout request_kirimLayout = new javax.swing.GroupLayout(request_kirim);
        request_kirim.setLayout(request_kirimLayout);
        request_kirimLayout.setHorizontalGroup(
            request_kirimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, request_kirimLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        request_kirimLayout.setVerticalGroup(
            request_kirimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(request_kirimLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
                .addContainerGap())
        );

        nul_master.setBackground(new java.awt.Color(0, 204, 204));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Data Master");

        javax.swing.GroupLayout nul_masterLayout = new javax.swing.GroupLayout(nul_master);
        nul_master.setLayout(nul_masterLayout);
        nul_masterLayout.setHorizontalGroup(
            nul_masterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, nul_masterLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        nul_masterLayout.setVerticalGroup(
            nul_masterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nul_masterLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        sub_master.setBackground(new java.awt.Color(0, 204, 204));

        master_down.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/down16.png"))); // NOI18N
        master_down.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        master_down.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                OpenSubMenu(evt);
            }
        });

        master_up.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/up16.png"))); // NOI18N
        master_up.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        master_up.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                OpenSubMenu(evt);
            }
        });

        javax.swing.GroupLayout sub_masterLayout = new javax.swing.GroupLayout(sub_master);
        sub_master.setLayout(sub_masterLayout);
        sub_masterLayout.setHorizontalGroup(
            sub_masterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
            .addGroup(sub_masterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sub_masterLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(master_down, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)))
            .addGroup(sub_masterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(sub_masterLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(master_up, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)))
        );
        sub_masterLayout.setVerticalGroup(
            sub_masterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(sub_masterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(master_down, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
            .addGroup(sub_masterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(master_up, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
        );

        master_produk.setBackground(new java.awt.Color(0, 204, 204));
        master_produk.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        master_produk.setPreferredSize(new java.awt.Dimension(70, 40));
        master_produk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MenuClick(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Produk");

        javax.swing.GroupLayout master_produkLayout = new javax.swing.GroupLayout(master_produk);
        master_produk.setLayout(master_produkLayout);
        master_produkLayout.setHorizontalGroup(
            master_produkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, master_produkLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        master_produkLayout.setVerticalGroup(
            master_produkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(master_produkLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
                .addContainerGap())
        );

        master_bahan.setBackground(new java.awt.Color(0, 204, 204));
        master_bahan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        master_bahan.setPreferredSize(new java.awt.Dimension(64, 40));
        master_bahan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MenuClick(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Bahan");

        javax.swing.GroupLayout master_bahanLayout = new javax.swing.GroupLayout(master_bahan);
        master_bahan.setLayout(master_bahanLayout);
        master_bahanLayout.setHorizontalGroup(
            master_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(master_bahanLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        master_bahanLayout.setVerticalGroup(
            master_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(master_bahanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
                .addContainerGap())
        );

        master_supplier.setBackground(new java.awt.Color(0, 204, 204));
        master_supplier.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        master_supplier.setPreferredSize(new java.awt.Dimension(76, 40));
        master_supplier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MenuClick(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Supplier");

        javax.swing.GroupLayout master_supplierLayout = new javax.swing.GroupLayout(master_supplier);
        master_supplier.setLayout(master_supplierLayout);
        master_supplierLayout.setHorizontalGroup(
            master_supplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(master_supplierLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        master_supplierLayout.setVerticalGroup(
            master_supplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(master_supplierLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
                .addContainerGap())
        );

        master_customer.setBackground(new java.awt.Color(0, 204, 204));
        master_customer.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        master_customer.setPreferredSize(new java.awt.Dimension(88, 40));
        master_customer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MenuClick(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Customer");

        javax.swing.GroupLayout master_customerLayout = new javax.swing.GroupLayout(master_customer);
        master_customer.setLayout(master_customerLayout);
        master_customerLayout.setHorizontalGroup(
            master_customerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(master_customerLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        master_customerLayout.setVerticalGroup(
            master_customerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(master_customerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
                .addContainerGap())
        );

        sub_stock.setBackground(new java.awt.Color(0, 204, 204));

        stock_down.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/down16.png"))); // NOI18N
        stock_down.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        stock_down.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                OpenSubMenu(evt);
            }
        });

        stock_up.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/up16.png"))); // NOI18N
        stock_up.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        stock_up.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                OpenSubMenu(evt);
            }
        });

        javax.swing.GroupLayout sub_stockLayout = new javax.swing.GroupLayout(sub_stock);
        sub_stock.setLayout(sub_stockLayout);
        sub_stockLayout.setHorizontalGroup(
            sub_stockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
            .addGroup(sub_stockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sub_stockLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(stock_down, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)))
            .addGroup(sub_stockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(sub_stockLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(stock_up, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)))
        );
        sub_stockLayout.setVerticalGroup(
            sub_stockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(sub_stockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(stock_down, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
            .addGroup(sub_stockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(stock_up, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
        );

        null_stock.setBackground(new java.awt.Color(0, 204, 204));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Stock Data");

        javax.swing.GroupLayout null_stockLayout = new javax.swing.GroupLayout(null_stock);
        null_stock.setLayout(null_stockLayout);
        null_stockLayout.setHorizontalGroup(
            null_stockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, null_stockLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        null_stockLayout.setVerticalGroup(
            null_stockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(null_stockLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        stock_produk_jadi.setBackground(new java.awt.Color(0, 204, 204));
        stock_produk_jadi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        stock_produk_jadi.setPreferredSize(new java.awt.Dimension(101, 40));
        stock_produk_jadi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MenuClick(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Produk Jadi");

        javax.swing.GroupLayout stock_produk_jadiLayout = new javax.swing.GroupLayout(stock_produk_jadi);
        stock_produk_jadi.setLayout(stock_produk_jadiLayout);
        stock_produk_jadiLayout.setHorizontalGroup(
            stock_produk_jadiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(stock_produk_jadiLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        stock_produk_jadiLayout.setVerticalGroup(
            stock_produk_jadiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(stock_produk_jadiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
                .addContainerGap())
        );

        stock_bahan_baku.setBackground(new java.awt.Color(0, 204, 204));
        stock_bahan_baku.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        stock_bahan_baku.setPreferredSize(new java.awt.Dimension(103, 40));
        stock_bahan_baku.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MenuClick(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Bahan Baku");

        javax.swing.GroupLayout stock_bahan_bakuLayout = new javax.swing.GroupLayout(stock_bahan_baku);
        stock_bahan_baku.setLayout(stock_bahan_bakuLayout);
        stock_bahan_bakuLayout.setHorizontalGroup(
            stock_bahan_bakuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(stock_bahan_bakuLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        stock_bahan_bakuLayout.setVerticalGroup(
            stock_bahan_bakuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(stock_bahan_bakuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
                .addContainerGap())
        );

        laporan.setBackground(new java.awt.Color(0, 204, 204));
        laporan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        laporan.setMinimumSize(new java.awt.Dimension(0, 30));
        laporan.setPreferredSize(new java.awt.Dimension(113, 45));
        laporan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MenuClick(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Laporan Data");

        javax.swing.GroupLayout laporanLayout = new javax.swing.GroupLayout(laporan);
        laporan.setLayout(laporanLayout);
        laporanLayout.setHorizontalGroup(
            laporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(laporanLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        laporanLayout.setVerticalGroup(
            laporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(laporanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        img_home.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/logo.png"))); // NOI18N
        img_home.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        img_home.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MenuClick(evt);
            }
        });

        javax.swing.GroupLayout Main_MenuLayout = new javax.swing.GroupLayout(Main_Menu);
        Main_Menu.setLayout(Main_MenuLayout);
        Main_MenuLayout.setHorizontalGroup(
            Main_MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Main_MenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Main_MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(master_produk, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                    .addComponent(request_bahan, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                    .addGroup(Main_MenuLayout.createSequentialGroup()
                        .addComponent(nul_master, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, 0)
                        .addComponent(sub_master, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(request_produk, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                    .addComponent(master_bahan, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                    .addComponent(master_supplier, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                    .addGroup(Main_MenuLayout.createSequentialGroup()
                        .addComponent(null_request, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, 0)
                        .addComponent(sub_request, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(master_customer, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                    .addComponent(stock_produk_jadi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                    .addComponent(stock_bahan_baku, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                    .addGroup(Main_MenuLayout.createSequentialGroup()
                        .addComponent(null_stock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, 0)
                        .addComponent(sub_stock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(request_kirim, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                    .addComponent(laporan, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Main_MenuLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(img_home)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        Main_MenuLayout.setVerticalGroup(
            Main_MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Main_MenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(img_home)
                .addGap(30, 30, 30)
                .addGroup(Main_MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(null_request, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sub_request, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(request_produk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(request_bahan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(request_kirim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(Main_MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(nul_master, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sub_master, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(master_produk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(master_bahan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(master_supplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(master_customer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(Main_MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(null_stock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sub_stock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(stock_produk_jadi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(stock_bahan_baku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(laporan, 39, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        Header.setBackground(new java.awt.Color(255, 255, 255));

        title_page.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        title_page.setText("Halaman Utama");

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/user.png"))); // NOI18N

        lbl_nama.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl_nama.setText("nama");
        lbl_nama.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout HeaderLayout = new javax.swing.GroupLayout(Header);
        Header.setLayout(HeaderLayout);
        HeaderLayout.setHorizontalGroup(
            HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HeaderLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(title_page)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_nama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(31, 31, 31))
        );
        HeaderLayout.setVerticalGroup(
            HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HeaderLayout.createSequentialGroup()
                .addGroup(HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(HeaderLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(title_page, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(HeaderLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_nama)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Body.setBackground(new java.awt.Color(255, 255, 255));
        Body.setLayout(new java.awt.CardLayout());

        body_home.setBackground(new java.awt.Color(0, 204, 204));

        jPanel1.setBackground(new java.awt.Color(0, 204, 204));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel17.setText("Aplikasi bla bla bla bla");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel17)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(jLabel17)
                .addContainerGap(53, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(0, 204, 204));

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/logo_ska.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel19.setText("PT. SETIA KAWAN ABADI");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setText("Bla bla  bla bla bla bla bla bla bla bla bla bla bla bla bla bla blabla bla bla bla bla");

        javax.swing.GroupLayout body_homeLayout = new javax.swing.GroupLayout(body_home);
        body_home.setLayout(body_homeLayout);
        body_homeLayout.setHorizontalGroup(
            body_homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(body_homeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(body_homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(body_homeLayout.createSequentialGroup()
                        .addGap(0, 263, Short.MAX_VALUE)
                        .addGroup(body_homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addGroup(body_homeLayout.createSequentialGroup()
                                .addGap(61, 61, 61)
                                .addComponent(jLabel19)))
                        .addGap(0, 254, Short.MAX_VALUE)))
                .addContainerGap())
        );
        body_homeLayout.setVerticalGroup(
            body_homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(body_homeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel19)
                .addGap(29, 29, 29)
                .addComponent(jLabel16)
                .addGap(78, 78, 78))
        );

        Body.add(body_home, "card11");

        body_req_produk.setBackground(new java.awt.Color(0, 204, 204));

        req_produk_tabel.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane7.setViewportView(req_produk_tabel);

        jLabel59.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel59.setText("ID");

        req_produk_id.setEnabled(false);
        req_produk_id.setPreferredSize(new java.awt.Dimension(6, 35));

        jLabel60.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel60.setText("ID Produk");

        req_produk_idproduk.setEnabled(false);
        req_produk_idproduk.setPreferredSize(new java.awt.Dimension(6, 35));

        req_produk_cari.setBackground(new java.awt.Color(51, 204, 0));
        req_produk_cari.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                req_produk_cariMouseClicked(evt);
            }
        });

        jLabel83.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel83.setForeground(new java.awt.Color(255, 255, 255));
        jLabel83.setText("Cari");

        javax.swing.GroupLayout req_produk_cariLayout = new javax.swing.GroupLayout(req_produk_cari);
        req_produk_cari.setLayout(req_produk_cariLayout);
        req_produk_cariLayout.setHorizontalGroup(
            req_produk_cariLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
            .addGroup(req_produk_cariLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(req_produk_cariLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel83)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        req_produk_cariLayout.setVerticalGroup(
            req_produk_cariLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 35, Short.MAX_VALUE)
            .addGroup(req_produk_cariLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(req_produk_cariLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel83)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jLabel61.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel61.setText("Nama Produk");

        req_produk_nama.setPreferredSize(new java.awt.Dimension(6, 35));

        jLabel62.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel62.setText("Jenis Produk");

        req_produk_jenis.setPreferredSize(new java.awt.Dimension(6, 35));

        req_produk_tgl_target.setDateFormatString("EEEE, dd MMMM yyyyy");
        req_produk_tgl_target.setPreferredSize(new java.awt.Dimension(91, 35));

        jLabel63.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel63.setText("Tangal Pengajuan");

        jLabel64.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel64.setText("Target Pengerjaan");

        req_produk_tgl.setEnabled(false);
        req_produk_tgl.setPreferredSize(new java.awt.Dimension(59, 35));

        req_produk_simpan.setBackground(new java.awt.Color(0, 153, 204));
        req_produk_simpan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                req_produk_simpanMouseClicked(evt);
            }
        });

        jLabel80.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel80.setForeground(new java.awt.Color(255, 255, 255));
        jLabel80.setText("Simpan");

        javax.swing.GroupLayout req_produk_simpanLayout = new javax.swing.GroupLayout(req_produk_simpan);
        req_produk_simpan.setLayout(req_produk_simpanLayout);
        req_produk_simpanLayout.setHorizontalGroup(
            req_produk_simpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, req_produk_simpanLayout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addComponent(jLabel80)
                .addGap(32, 32, 32))
        );
        req_produk_simpanLayout.setVerticalGroup(
            req_produk_simpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, req_produk_simpanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel80, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        req_produk_batal.setBackground(new java.awt.Color(204, 0, 0));
        req_produk_batal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                req_produk_batalMouseClicked(evt);
            }
        });

        jLabel81.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel81.setForeground(new java.awt.Color(255, 255, 255));
        jLabel81.setText("Batal");

        javax.swing.GroupLayout req_produk_batalLayout = new javax.swing.GroupLayout(req_produk_batal);
        req_produk_batal.setLayout(req_produk_batalLayout);
        req_produk_batalLayout.setHorizontalGroup(
            req_produk_batalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, req_produk_batalLayout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addComponent(jLabel81)
                .addGap(36, 36, 36))
        );
        req_produk_batalLayout.setVerticalGroup(
            req_produk_batalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, req_produk_batalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel81, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel82.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel82.setText("Jumlah Produksi");

        req_produk_jumlah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                req_produk_jumlahKeyTyped(evt);
            }
        });

        jLabel84.setText("Satuan");

        req_produk_satuan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih", "PCS", "Botol", "Pack" }));
        req_produk_satuan.setPreferredSize(new java.awt.Dimension(56, 35));

        javax.swing.GroupLayout body_req_produkLayout = new javax.swing.GroupLayout(body_req_produk);
        body_req_produk.setLayout(body_req_produkLayout);
        body_req_produkLayout.setHorizontalGroup(
            body_req_produkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(body_req_produkLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(body_req_produkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7)
                    .addGroup(body_req_produkLayout.createSequentialGroup()
                        .addGroup(body_req_produkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel59)
                            .addComponent(jLabel63)
                            .addComponent(req_produk_tgl, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                            .addComponent(req_produk_id, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                        .addGroup(body_req_produkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel61)
                            .addComponent(jLabel60)
                            .addGroup(body_req_produkLayout.createSequentialGroup()
                                .addGroup(body_req_produkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(req_produk_nama, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(req_produk_idproduk, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(req_produk_cari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel62)
                            .addComponent(req_produk_jenis, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
                        .addGroup(body_req_produkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(body_req_produkLayout.createSequentialGroup()
                                .addComponent(req_produk_batal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(req_produk_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(req_produk_tgl_target, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, body_req_produkLayout.createSequentialGroup()
                                .addGroup(body_req_produkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, body_req_produkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(req_produk_jumlah, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel82, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jLabel64, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(18, 18, 18)
                                .addGroup(body_req_produkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(body_req_produkLayout.createSequentialGroup()
                                        .addComponent(jLabel84)
                                        .addGap(22, 22, 22))
                                    .addComponent(req_produk_satuan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addGap(30, 30, 30))
        );
        body_req_produkLayout.setVerticalGroup(
            body_req_produkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, body_req_produkLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(body_req_produkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel59)
                    .addComponent(jLabel60)
                    .addComponent(jLabel82)
                    .addComponent(jLabel84))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(body_req_produkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(body_req_produkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(body_req_produkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(req_produk_id, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(req_produk_idproduk, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(req_produk_jumlah)
                        .addComponent(req_produk_cari, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(req_produk_satuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(body_req_produkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel63)
                    .addComponent(jLabel61)
                    .addComponent(jLabel64))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(body_req_produkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(req_produk_tgl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(req_produk_nama, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(req_produk_tgl_target, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(body_req_produkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(body_req_produkLayout.createSequentialGroup()
                        .addComponent(jLabel62)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(req_produk_jenis, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(req_produk_batal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(req_produk_simpan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(103, Short.MAX_VALUE))
        );

        Body.add(body_req_produk, "card2");

        body_req_bahan.setBackground(new java.awt.Color(0, 204, 204));
        body_req_bahan.setEnabled(false);

        req_bahan_tabel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Tanggal", "ID Supplier", "ID Bahan", "Nama Bahan", "Jumlah Pesan", "Satuan"
            }
        ));
        jScrollPane8.setViewportView(req_bahan_tabel);

        req_bahan_hapus.setBackground(new java.awt.Color(204, 0, 0));
        req_bahan_hapus.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        req_bahan_hapus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                req_bahan_hapusMouseClicked(evt);
            }
        });

        jLabel76.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel76.setForeground(new java.awt.Color(255, 255, 255));
        jLabel76.setText("Hapus");

        javax.swing.GroupLayout req_bahan_hapusLayout = new javax.swing.GroupLayout(req_bahan_hapus);
        req_bahan_hapus.setLayout(req_bahan_hapusLayout);
        req_bahan_hapusLayout.setHorizontalGroup(
            req_bahan_hapusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 72, Short.MAX_VALUE)
            .addGroup(req_bahan_hapusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(req_bahan_hapusLayout.createSequentialGroup()
                    .addGap(0, 17, Short.MAX_VALUE)
                    .addComponent(jLabel76)
                    .addGap(0, 17, Short.MAX_VALUE)))
        );
        req_bahan_hapusLayout.setVerticalGroup(
            req_bahan_hapusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
            .addGroup(req_bahan_hapusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(req_bahan_hapusLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel76)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        req_bahan_tambah.setBackground(new java.awt.Color(0, 153, 0));
        req_bahan_tambah.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        req_bahan_tambah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                req_bahan_tambahMouseClicked(evt);
            }
        });

        jLabel77.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel77.setForeground(new java.awt.Color(255, 255, 255));
        jLabel77.setText("Tambah");

        javax.swing.GroupLayout req_bahan_tambahLayout = new javax.swing.GroupLayout(req_bahan_tambah);
        req_bahan_tambah.setLayout(req_bahan_tambahLayout);
        req_bahan_tambahLayout.setHorizontalGroup(
            req_bahan_tambahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(req_bahan_tambahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(req_bahan_tambahLayout.createSequentialGroup()
                    .addGap(0, 11, Short.MAX_VALUE)
                    .addComponent(jLabel77)
                    .addGap(0, 12, Short.MAX_VALUE)))
        );
        req_bahan_tambahLayout.setVerticalGroup(
            req_bahan_tambahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(req_bahan_tambahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(req_bahan_tambahLayout.createSequentialGroup()
                    .addGap(0, 12, Short.MAX_VALUE)
                    .addComponent(jLabel77)
                    .addGap(0, 13, Short.MAX_VALUE)))
        );

        jLabel65.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel65.setText("ID Request Bahan");

        jLabel66.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel66.setText("Nama Bahan");

        jLabel67.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel67.setText("Tanggal Pembuatan");

        jLabel68.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel68.setText("ID Supplier");

        jLabel69.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel69.setText("ID Bahan");

        jLabel70.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel70.setText("Nama Supplier");

        jLabel71.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel71.setText("Jumlah");

        jLabel72.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel72.setText("No.Telp");

        req_bahan_id.setEnabled(false);
        req_bahan_id.setPreferredSize(new java.awt.Dimension(59, 35));

        req_bahan_tgl.setEnabled(false);
        req_bahan_tgl.setPreferredSize(new java.awt.Dimension(59, 35));

        req_bahan_idbahan.setEnabled(false);
        req_bahan_idbahan.setPreferredSize(new java.awt.Dimension(59, 35));

        req_bahan_carisup.setBackground(new java.awt.Color(51, 204, 0));
        req_bahan_carisup.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        req_bahan_carisup.setPreferredSize(new java.awt.Dimension(40, 35));
        req_bahan_carisup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                req_bahan_carisupMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout req_bahan_carisupLayout = new javax.swing.GroupLayout(req_bahan_carisup);
        req_bahan_carisup.setLayout(req_bahan_carisupLayout);
        req_bahan_carisupLayout.setHorizontalGroup(
            req_bahan_carisupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        req_bahan_carisupLayout.setVerticalGroup(
            req_bahan_carisupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 35, Short.MAX_VALUE)
        );

        req_bahan_caribahan.setBackground(new java.awt.Color(51, 204, 0));
        req_bahan_caribahan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        req_bahan_caribahan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                req_bahan_caribahanMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout req_bahan_caribahanLayout = new javax.swing.GroupLayout(req_bahan_caribahan);
        req_bahan_caribahan.setLayout(req_bahan_caribahanLayout);
        req_bahan_caribahanLayout.setHorizontalGroup(
            req_bahan_caribahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        req_bahan_caribahanLayout.setVerticalGroup(
            req_bahan_caribahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        req_bahan_namabahan.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        req_bahan_namabahan.setPreferredSize(new java.awt.Dimension(59, 35));

        req_bahan_qtybahan.setPreferredSize(new java.awt.Dimension(6, 35));

        jLabel73.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel73.setText("Satuan");

        req_bahan_satuan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih", "Item 1", "Item 2", "Item 3", "Item 4" }));
        req_bahan_satuan.setPreferredSize(new java.awt.Dimension(56, 35));

        req_bahan_idsupp.setEnabled(false);
        req_bahan_idsupp.setPreferredSize(new java.awt.Dimension(59, 35));

        req_bahan_namasup.setEnabled(false);
        req_bahan_namasup.setPreferredSize(new java.awt.Dimension(59, 35));

        req_bahan_telp.setEnabled(false);
        req_bahan_telp.setPreferredSize(new java.awt.Dimension(59, 35));

        req_bahan_batal.setBackground(new java.awt.Color(204, 0, 0));
        req_bahan_batal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel74.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel74.setForeground(new java.awt.Color(255, 255, 255));
        jLabel74.setText("Batal");

        javax.swing.GroupLayout req_bahan_batalLayout = new javax.swing.GroupLayout(req_bahan_batal);
        req_bahan_batal.setLayout(req_bahan_batalLayout);
        req_bahan_batalLayout.setHorizontalGroup(
            req_bahan_batalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 90, Short.MAX_VALUE)
            .addGroup(req_bahan_batalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(req_bahan_batalLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel74)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        req_bahan_batalLayout.setVerticalGroup(
            req_bahan_batalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
            .addGroup(req_bahan_batalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(req_bahan_batalLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel74)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        req_bahan_simpan.setBackground(new java.awt.Color(0, 153, 204));
        req_bahan_simpan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        req_bahan_simpan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                req_bahan_simpanMouseClicked(evt);
            }
        });

        jLabel75.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel75.setForeground(new java.awt.Color(255, 255, 255));
        jLabel75.setText("Simpan");

        javax.swing.GroupLayout req_bahan_simpanLayout = new javax.swing.GroupLayout(req_bahan_simpan);
        req_bahan_simpan.setLayout(req_bahan_simpanLayout);
        req_bahan_simpanLayout.setHorizontalGroup(
            req_bahan_simpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 90, Short.MAX_VALUE)
            .addGroup(req_bahan_simpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(req_bahan_simpanLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel75)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        req_bahan_simpanLayout.setVerticalGroup(
            req_bahan_simpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(req_bahan_simpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(req_bahan_simpanLayout.createSequentialGroup()
                    .addGap(0, 11, Short.MAX_VALUE)
                    .addComponent(jLabel75)
                    .addGap(0, 12, Short.MAX_VALUE)))
        );

        jLabel78.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel78.setText("Jumlah Item :");

        jLabel79.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel79.setText("Total Item :");

        txt_jumlah_item.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_jumlah_item.setText("0");

        txt_total_ite.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_total_ite.setText("0");

        req_bahan_lihat.setBackground(new java.awt.Color(0, 153, 204));
        req_bahan_lihat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        req_bahan_lihat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                req_bahan_lihatMouseClicked(evt);
            }
        });

        jLabel85.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel85.setForeground(new java.awt.Color(255, 255, 255));
        jLabel85.setText("Lihat");

        javax.swing.GroupLayout req_bahan_lihatLayout = new javax.swing.GroupLayout(req_bahan_lihat);
        req_bahan_lihat.setLayout(req_bahan_lihatLayout);
        req_bahan_lihatLayout.setHorizontalGroup(
            req_bahan_lihatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 90, Short.MAX_VALUE)
            .addGroup(req_bahan_lihatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(req_bahan_lihatLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel85)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        req_bahan_lihatLayout.setVerticalGroup(
            req_bahan_lihatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
            .addGroup(req_bahan_lihatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(req_bahan_lihatLayout.createSequentialGroup()
                    .addGap(0, 12, Short.MAX_VALUE)
                    .addComponent(jLabel85)
                    .addGap(0, 11, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout body_req_bahanLayout = new javax.swing.GroupLayout(body_req_bahan);
        body_req_bahan.setLayout(body_req_bahanLayout);
        body_req_bahanLayout.setHorizontalGroup(
            body_req_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(body_req_bahanLayout.createSequentialGroup()
                .addContainerGap(49, Short.MAX_VALUE)
                .addGroup(body_req_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel65)
                    .addComponent(req_bahan_tgl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(req_bahan_id, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel67)
                    .addGroup(body_req_bahanLayout.createSequentialGroup()
                        .addComponent(req_bahan_batal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(req_bahan_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 99, Short.MAX_VALUE)
                .addGroup(body_req_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel70)
                    .addComponent(jLabel68)
                    .addGroup(body_req_bahanLayout.createSequentialGroup()
                        .addComponent(req_bahan_idsupp, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(req_bahan_carisup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel72)
                    .addComponent(req_bahan_namasup, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(req_bahan_telp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 107, Short.MAX_VALUE)
                .addGroup(body_req_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel69, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel66, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, body_req_bahanLayout.createSequentialGroup()
                        .addGroup(body_req_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel71)
                            .addComponent(req_bahan_qtybahan, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(body_req_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(body_req_bahanLayout.createSequentialGroup()
                                .addComponent(req_bahan_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(req_bahan_tambah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(body_req_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel73)
                                .addComponent(req_bahan_satuan, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(req_bahan_namabahan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(body_req_bahanLayout.createSequentialGroup()
                        .addComponent(req_bahan_idbahan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(req_bahan_caribahan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)))
                .addContainerGap(39, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, body_req_bahanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane8)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, body_req_bahanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(req_bahan_lihat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel78)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_jumlah_item)
                .addGap(18, 18, 18)
                .addComponent(jLabel79)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_total_ite)
                .addContainerGap())
        );
        body_req_bahanLayout.setVerticalGroup(
            body_req_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(body_req_bahanLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(body_req_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(body_req_bahanLayout.createSequentialGroup()
                        .addComponent(jLabel68)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(body_req_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(req_bahan_idsupp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(req_bahan_carisup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel70)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(body_req_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(req_bahan_namasup, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(req_bahan_namabahan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(body_req_bahanLayout.createSequentialGroup()
                        .addComponent(jLabel65)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(req_bahan_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel67)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(req_bahan_tgl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel69)
                    .addGroup(body_req_bahanLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(body_req_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(req_bahan_caribahan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(req_bahan_idbahan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel66)))
                .addGroup(body_req_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(body_req_bahanLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel73))
                    .addGroup(body_req_bahanLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel72))
                    .addGroup(body_req_bahanLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel71)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(body_req_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(body_req_bahanLayout.createSequentialGroup()
                        .addGroup(body_req_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(req_bahan_telp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(req_bahan_satuan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(body_req_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(body_req_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(req_bahan_hapus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(req_bahan_simpan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(req_bahan_tambah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(req_bahan_batal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(req_bahan_qtybahan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(body_req_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, body_req_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel78)
                        .addComponent(jLabel79)
                        .addComponent(txt_jumlah_item)
                        .addComponent(txt_total_ite))
                    .addComponent(req_bahan_lihat, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        Body.add(body_req_bahan, "card3");

        body_req_kirim.setBackground(new java.awt.Color(0, 204, 204));

        jPanel3.setBackground(new java.awt.Color(0, 204, 204));

        jLabel86.setText("Invoice");

        req_kirim_id.setEnabled(false);

        req_kirim_btCust.setBackground(new java.awt.Color(0, 204, 0));
        req_kirim_btCust.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        req_kirim_btCust.setText("Pilih");
        req_kirim_btCust.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                req_kirim_btCustActionPerformed(evt);
            }
        });

        jLabel87.setText("Customer ID");

        req_kirim_custid.setEnabled(false);

        jLabel88.setText("Nama Customer");

        req_kirim_nama.setEnabled(false);

        jLabel89.setText("No Telepon");

        req_kirim_telp.setEnabled(false);

        jLabel90.setText("Alamat");

        req_kirim_alamat.setColumns(20);
        req_kirim_alamat.setRows(5);
        jScrollPane9.setViewportView(req_kirim_alamat);

        jLabel91.setText("Produk ID");

        req_kirim_produkid.setEnabled(false);
        req_kirim_produkid.setPreferredSize(new java.awt.Dimension(59, 35));

        jLabel92.setText("Nama");

        req_kirim_produknama.setEnabled(false);

        jLabel93.setText("Harga");

        req_kirim_harga.setEnabled(false);
        req_kirim_harga.setPreferredSize(new java.awt.Dimension(59, 35));

        jLabel94.setText("Qty");

        req_kirim_qty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                req_kirim_qtyKeyTyped(evt);
            }
        });

        req_kirim_btProduk.setBackground(new java.awt.Color(0, 204, 0));
        req_kirim_btProduk.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        req_kirim_btProduk.setText("Pilih");
        req_kirim_btProduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                req_kirim_btProdukActionPerformed(evt);
            }
        });

        jLabel96.setText("Sub-Total");

        req_kirim_subtot.setEnabled(false);

        jButton1.setText("Hitung");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel87)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(req_kirim_btCust)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel86)
                                .addComponent(req_kirim_id, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(79, 79, 79)))
                    .addComponent(req_kirim_custid, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel88)
                    .addComponent(req_kirim_nama, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel89)
                    .addComponent(req_kirim_telp, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel90)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel91)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(req_kirim_produkid, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(req_kirim_btProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel96)
                            .addComponent(req_kirim_subtot, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel92)
                        .addComponent(req_kirim_produknama, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel93)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(req_kirim_harga, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(req_kirim_qty, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel94))))
                .addGap(35, 35, 35))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel86)
                    .addComponent(jLabel89)
                    .addComponent(jLabel91))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(req_kirim_produkid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(req_kirim_btProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(req_kirim_telp)
                    .addComponent(req_kirim_id))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel92)
                            .addComponent(jLabel90))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(req_kirim_produknama, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel93)
                                    .addComponent(jLabel94))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(req_kirim_qty, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(req_kirim_harga, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jScrollPane9)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel87)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(req_kirim_custid, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(req_kirim_btCust, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel88)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(req_kirim_nama, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel96)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(req_kirim_subtot, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        req_kirim_btTambah.setBackground(new java.awt.Color(51, 153, 0));
        req_kirim_btTambah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                req_kirim_btTambahMouseClicked(evt);
            }
        });

        jLabel102.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel102.setForeground(new java.awt.Color(255, 255, 255));
        jLabel102.setText("Tambah");

        javax.swing.GroupLayout req_kirim_btTambahLayout = new javax.swing.GroupLayout(req_kirim_btTambah);
        req_kirim_btTambah.setLayout(req_kirim_btTambahLayout);
        req_kirim_btTambahLayout.setHorizontalGroup(
            req_kirim_btTambahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
            .addGroup(req_kirim_btTambahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(req_kirim_btTambahLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel102)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        req_kirim_btTambahLayout.setVerticalGroup(
            req_kirim_btTambahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
            .addGroup(req_kirim_btTambahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(req_kirim_btTambahLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel102)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        req_kirim_btHapus.setBackground(new java.awt.Color(204, 0, 0));

        jLabel101.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel101.setForeground(new java.awt.Color(255, 255, 255));
        jLabel101.setText("Hapus");

        javax.swing.GroupLayout req_kirim_btHapusLayout = new javax.swing.GroupLayout(req_kirim_btHapus);
        req_kirim_btHapus.setLayout(req_kirim_btHapusLayout);
        req_kirim_btHapusLayout.setHorizontalGroup(
            req_kirim_btHapusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
            .addGroup(req_kirim_btHapusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(req_kirim_btHapusLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel101)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        req_kirim_btHapusLayout.setVerticalGroup(
            req_kirim_btHapusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
            .addGroup(req_kirim_btHapusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(req_kirim_btHapusLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel101)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        req_kirim_btBatal.setBackground(new java.awt.Color(204, 0, 0));
        req_kirim_btBatal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                req_kirim_btBatalMouseClicked(evt);
            }
        });

        jLabel98.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel98.setForeground(new java.awt.Color(255, 255, 255));
        jLabel98.setText("Batal");

        javax.swing.GroupLayout req_kirim_btBatalLayout = new javax.swing.GroupLayout(req_kirim_btBatal);
        req_kirim_btBatal.setLayout(req_kirim_btBatalLayout);
        req_kirim_btBatalLayout.setHorizontalGroup(
            req_kirim_btBatalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
            .addGroup(req_kirim_btBatalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(req_kirim_btBatalLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel98)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        req_kirim_btBatalLayout.setVerticalGroup(
            req_kirim_btBatalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
            .addGroup(req_kirim_btBatalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(req_kirim_btBatalLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel98)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        req_kirim_btSimpan.setBackground(new java.awt.Color(0, 153, 204));
        req_kirim_btSimpan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                req_kirim_btSimpanMouseClicked(evt);
            }
        });

        jLabel100.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel100.setForeground(new java.awt.Color(255, 255, 255));
        jLabel100.setText("Simpan");

        javax.swing.GroupLayout req_kirim_btSimpanLayout = new javax.swing.GroupLayout(req_kirim_btSimpan);
        req_kirim_btSimpan.setLayout(req_kirim_btSimpanLayout);
        req_kirim_btSimpanLayout.setHorizontalGroup(
            req_kirim_btSimpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
            .addGroup(req_kirim_btSimpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(req_kirim_btSimpanLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel100)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        req_kirim_btSimpanLayout.setVerticalGroup(
            req_kirim_btSimpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
            .addGroup(req_kirim_btSimpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(req_kirim_btSimpanLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel100)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        req_kirim_tabel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Invoice", "Produk ID", "Produk Nama", "Harga", "Qty", "Sub-Total"
            }
        ));
        jScrollPane10.setViewportView(req_kirim_tabel);

        jLabel95.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel95.setText("Jumlah Item");

        txt_kirim_item.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_kirim_item.setText("0");

        jLabel97.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel97.setText("Total Qty");

        txt_kirim_total.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_kirim_total.setText("0");

        jLabel99.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel99.setText("Total Harga");

        txt_kirim_totharga.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_kirim_totharga.setText("0");

        javax.swing.GroupLayout body_req_kirimLayout = new javax.swing.GroupLayout(body_req_kirim);
        body_req_kirim.setLayout(body_req_kirimLayout);
        body_req_kirimLayout.setHorizontalGroup(
            body_req_kirimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, body_req_kirimLayout.createSequentialGroup()
                .addGroup(body_req_kirimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(body_req_kirimLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane10))
                    .addGroup(body_req_kirimLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(req_kirim_btBatal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(req_kirim_btSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(req_kirim_btHapus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(req_kirim_btTambah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, body_req_kirimLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel95)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_kirim_item)
                .addGap(28, 28, 28)
                .addComponent(jLabel97)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_kirim_total)
                .addGap(26, 26, 26)
                .addComponent(jLabel99)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_kirim_totharga)
                .addGap(22, 22, 22))
        );
        body_req_kirimLayout.setVerticalGroup(
            body_req_kirimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(body_req_kirimLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(body_req_kirimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(req_kirim_btSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(req_kirim_btHapus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(req_kirim_btBatal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(req_kirim_btTambah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(body_req_kirimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel95)
                    .addComponent(txt_kirim_item)
                    .addComponent(jLabel97)
                    .addComponent(txt_kirim_total)
                    .addComponent(jLabel99)
                    .addComponent(txt_kirim_totharga))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Body.add(body_req_kirim, "card12");

        body_master_produk.setBackground(new java.awt.Color(0, 204, 204));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        produk_tabel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        produk_tabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                produk_tabelMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(produk_tabel);

        produk_tambah.setBackground(new java.awt.Color(204, 0, 0));
        produk_tambah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                produk_tambahMouseClicked(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Tambah");

        javax.swing.GroupLayout produk_tambahLayout = new javax.swing.GroupLayout(produk_tambah);
        produk_tambah.setLayout(produk_tambahLayout);
        produk_tambahLayout.setHorizontalGroup(
            produk_tambahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
            .addGroup(produk_tambahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(produk_tambahLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel20)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        produk_tambahLayout.setVerticalGroup(
            produk_tambahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
            .addGroup(produk_tambahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(produk_tambahLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel20)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel4.setBackground(new java.awt.Color(0, 204, 204));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel31.setText("ID Produk");

        produk_id.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        produk_id.setPreferredSize(new java.awt.Dimension(6, 35));

        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel32.setText("Nama Produk");

        produk_nama.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        produk_nama.setMinimumSize(new java.awt.Dimension(6, 35));

        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel33.setText("Kategori");

        produk_kategori.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        produk_kategori.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih", "kategori1", "kategori2", "kategori3" }));
        produk_kategori.setPreferredSize(new java.awt.Dimension(56, 35));

        jLabel34.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel34.setText("Jenis");

        produk_jenis.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        produk_jenis.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih", "jenis1", "jenis2", "jenis3" }));
        produk_jenis.setPreferredSize(new java.awt.Dimension(56, 35));

        jLabel36.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel36.setText("Satuan");

        produk_satuan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        produk_satuan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih", "satuan1", "satuan2", "satuan3" }));
        produk_satuan.setPreferredSize(new java.awt.Dimension(56, 35));

        jLabel37.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel37.setText("Harga");

        produk_harga.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        produk_harga.setPreferredSize(new java.awt.Dimension(6, 35));

        produk_simpan.setBackground(new java.awt.Color(0, 153, 0));
        produk_simpan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                produk_simpanMouseClicked(evt);
            }
        });

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 255, 255));
        jLabel38.setText("Simpan");

        javax.swing.GroupLayout produk_simpanLayout = new javax.swing.GroupLayout(produk_simpan);
        produk_simpan.setLayout(produk_simpanLayout);
        produk_simpanLayout.setHorizontalGroup(
            produk_simpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
            .addGroup(produk_simpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(produk_simpanLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel38)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        produk_simpanLayout.setVerticalGroup(
            produk_simpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
            .addGroup(produk_simpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(produk_simpanLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel38)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        produk_edit.setBackground(new java.awt.Color(0, 102, 204));
        produk_edit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                produk_editMouseClicked(evt);
            }
        });

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setText("Simpan");

        javax.swing.GroupLayout produk_editLayout = new javax.swing.GroupLayout(produk_edit);
        produk_edit.setLayout(produk_editLayout);
        produk_editLayout.setHorizontalGroup(
            produk_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
            .addGroup(produk_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(produk_editLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel39)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        produk_editLayout.setVerticalGroup(
            produk_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
            .addGroup(produk_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(produk_editLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel39)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        produk_delete.setBackground(new java.awt.Color(204, 0, 0));

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(255, 255, 255));
        jLabel40.setText("Hapus");

        javax.swing.GroupLayout produk_deleteLayout = new javax.swing.GroupLayout(produk_delete);
        produk_delete.setLayout(produk_deleteLayout);
        produk_deleteLayout.setHorizontalGroup(
            produk_deleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(produk_deleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(produk_deleteLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel40)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        produk_deleteLayout.setVerticalGroup(
            produk_deleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
            .addGroup(produk_deleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(produk_deleteLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel40)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel32, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel31, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(produk_id, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(produk_nama, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(produk_harga, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(produk_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(produk_edit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(produk_delete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jLabel36, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(produk_satuan, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(produk_kategori, javax.swing.GroupLayout.Alignment.LEADING, 0, 150, Short.MAX_VALUE)
                            .addComponent(jLabel33, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel37, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(produk_jenis, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel34)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(produk_id, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jLabel32)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(produk_nama, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(jLabel34))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(produk_jenis, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(produk_kategori, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel36)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(produk_satuan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel37)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(produk_harga, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(produk_edit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(produk_simpan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(produk_delete, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout body_master_produkLayout = new javax.swing.GroupLayout(body_master_produk);
        body_master_produk.setLayout(body_master_produkLayout);
        body_master_produkLayout.setHorizontalGroup(
            body_master_produkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, body_master_produkLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(body_master_produkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(body_master_produkLayout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(20, 20, 20))
                    .addGroup(body_master_produkLayout.createSequentialGroup()
                        .addComponent(produk_tambah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(266, 266, 266)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 568, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );
        body_master_produkLayout.setVerticalGroup(
            body_master_produkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(body_master_produkLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(body_master_produkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(body_master_produkLayout.createSequentialGroup()
                        .addComponent(produk_tambah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 559, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Body.add(body_master_produk, "card4");

        body_master_bahan.setBackground(new java.awt.Color(0, 204, 204));

        bahan_tambah.setBackground(new java.awt.Color(204, 0, 0));
        bahan_tambah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bahan_tambahMouseClicked(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Tambah");

        javax.swing.GroupLayout bahan_tambahLayout = new javax.swing.GroupLayout(bahan_tambah);
        bahan_tambah.setLayout(bahan_tambahLayout);
        bahan_tambahLayout.setHorizontalGroup(
            bahan_tambahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
            .addGroup(bahan_tambahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bahan_tambahLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel21)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        bahan_tambahLayout.setVerticalGroup(
            bahan_tambahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
            .addGroup(bahan_tambahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bahan_tambahLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel21)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jScrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        bahan_tabel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        bahan_tabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bahan_tabelMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(bahan_tabel);

        jPanel5.setBackground(new java.awt.Color(0, 204, 204));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel23.setText("ID Bahan");

        bahan_id.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        bahan_id.setPreferredSize(new java.awt.Dimension(6, 35));

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel24.setText("Nama Bahan");

        bahan_nama.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        bahan_nama.setPreferredSize(new java.awt.Dimension(6, 35));

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel25.setText("Jenis");

        bahan_jenis.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        bahan_jenis.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih", "jenis1", "jenis2", "jenis3" }));
        bahan_jenis.setPreferredSize(new java.awt.Dimension(56, 35));

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel26.setText("Satuan");

        bahan_satuan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        bahan_satuan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih", "Satuan1", "Satuan2", "Satuan3" }));
        bahan_satuan.setPreferredSize(new java.awt.Dimension(56, 35));

        bahan_simpan.setBackground(new java.awt.Color(0, 153, 0));
        bahan_simpan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bahan_simpanMouseClicked(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("Simpan");

        javax.swing.GroupLayout bahan_simpanLayout = new javax.swing.GroupLayout(bahan_simpan);
        bahan_simpan.setLayout(bahan_simpanLayout);
        bahan_simpanLayout.setHorizontalGroup(
            bahan_simpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 102, Short.MAX_VALUE)
            .addGroup(bahan_simpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bahan_simpanLayout.createSequentialGroup()
                    .addGap(0, 25, Short.MAX_VALUE)
                    .addComponent(jLabel28)
                    .addGap(0, 26, Short.MAX_VALUE)))
        );
        bahan_simpanLayout.setVerticalGroup(
            bahan_simpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
            .addGroup(bahan_simpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bahan_simpanLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel28)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        bahan_edit.setBackground(new java.awt.Color(0, 102, 204));
        bahan_edit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bahan_editMouseClicked(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("Simpan");

        javax.swing.GroupLayout bahan_editLayout = new javax.swing.GroupLayout(bahan_edit);
        bahan_edit.setLayout(bahan_editLayout);
        bahan_editLayout.setHorizontalGroup(
            bahan_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 80, Short.MAX_VALUE)
            .addGroup(bahan_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bahan_editLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel29)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        bahan_editLayout.setVerticalGroup(
            bahan_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
            .addGroup(bahan_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bahan_editLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel29)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        bahan_delete.setBackground(new java.awt.Color(204, 0, 0));

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("Hapus");

        javax.swing.GroupLayout bahan_deleteLayout = new javax.swing.GroupLayout(bahan_delete);
        bahan_delete.setLayout(bahan_deleteLayout);
        bahan_deleteLayout.setHorizontalGroup(
            bahan_deleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 80, Short.MAX_VALUE)
            .addGroup(bahan_deleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bahan_deleteLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel30)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        bahan_deleteLayout.setVerticalGroup(
            bahan_deleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
            .addGroup(bahan_deleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bahan_deleteLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel30)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel26)
                            .addComponent(bahan_satuan, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(bahan_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(bahan_edit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bahan_delete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bahan_jenis, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25)
                            .addComponent(bahan_nama, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23)
                            .addComponent(jLabel24)
                            .addComponent(bahan_id, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(40, Short.MAX_VALUE))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bahan_id, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bahan_nama, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bahan_jenis, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(bahan_satuan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel26))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bahan_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bahan_edit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bahan_delete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout body_master_bahanLayout = new javax.swing.GroupLayout(body_master_bahan);
        body_master_bahan.setLayout(body_master_bahanLayout);
        body_master_bahanLayout.setHorizontalGroup(
            body_master_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, body_master_bahanLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(body_master_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(body_master_bahanLayout.createSequentialGroup()
                        .addComponent(bahan_tambah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(20, 20, 20)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 558, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );
        body_master_bahanLayout.setVerticalGroup(
            body_master_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(body_master_bahanLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(body_master_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(body_master_bahanLayout.createSequentialGroup()
                        .addComponent(bahan_tambah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Body.add(body_master_bahan, "card5");

        body_master_supplier.setBackground(new java.awt.Color(0, 204, 204));

        supplier_tambah.setBackground(new java.awt.Color(204, 0, 0));
        supplier_tambah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                supplier_tambahMouseClicked(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Tambah");

        javax.swing.GroupLayout supplier_tambahLayout = new javax.swing.GroupLayout(supplier_tambah);
        supplier_tambah.setLayout(supplier_tambahLayout);
        supplier_tambahLayout.setHorizontalGroup(
            supplier_tambahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
            .addGroup(supplier_tambahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(supplier_tambahLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel14)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        supplier_tambahLayout.setVerticalGroup(
            supplier_tambahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
            .addGroup(supplier_tambahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(supplier_tambahLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel14)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel7.setBackground(new java.awt.Color(0, 204, 204));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel41.setText("ID Supplier");

        supplier_id.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel42.setText("Nama");

        supplier_nama.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel43.setText("Email");

        supplier_email.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel44.setText("No Telp");

        supplier_telp.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel45.setText("Jenis Produk");

        supplier_jenis.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        supplier_alamat.setColumns(20);
        supplier_alamat.setRows(5);
        jScrollPane5.setViewportView(supplier_alamat);

        jLabel46.setText("Alamat");

        supplier_edit.setBackground(new java.awt.Color(0, 153, 204));
        supplier_edit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                supplier_editMouseClicked(evt);
            }
        });

        jLabel47.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(255, 255, 255));
        jLabel47.setText("Simpan");

        javax.swing.GroupLayout supplier_editLayout = new javax.swing.GroupLayout(supplier_edit);
        supplier_edit.setLayout(supplier_editLayout);
        supplier_editLayout.setHorizontalGroup(
            supplier_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
            .addGroup(supplier_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(supplier_editLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel47)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        supplier_editLayout.setVerticalGroup(
            supplier_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
            .addGroup(supplier_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(supplier_editLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel47)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        supplier_simpan.setBackground(new java.awt.Color(0, 153, 0));
        supplier_simpan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                supplier_simpanMouseClicked(evt);
            }
        });

        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(255, 255, 255));
        jLabel49.setText("Simpan");

        javax.swing.GroupLayout supplier_simpanLayout = new javax.swing.GroupLayout(supplier_simpan);
        supplier_simpan.setLayout(supplier_simpanLayout);
        supplier_simpanLayout.setHorizontalGroup(
            supplier_simpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 120, Short.MAX_VALUE)
            .addGroup(supplier_simpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(supplier_simpanLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel49)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        supplier_simpanLayout.setVerticalGroup(
            supplier_simpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
            .addGroup(supplier_simpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(supplier_simpanLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel49)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        supplier_delete.setBackground(new java.awt.Color(204, 0, 0));

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(255, 255, 255));
        jLabel48.setText("Hapus");

        javax.swing.GroupLayout supplier_deleteLayout = new javax.swing.GroupLayout(supplier_delete);
        supplier_delete.setLayout(supplier_deleteLayout);
        supplier_deleteLayout.setHorizontalGroup(
            supplier_deleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
            .addGroup(supplier_deleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(supplier_deleteLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel48)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        supplier_deleteLayout.setVerticalGroup(
            supplier_deleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
            .addGroup(supplier_deleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(supplier_deleteLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel48)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        supplier_txt.setText("Status");

        supplier_status.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        supplier_status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih", "Aktiv", "Non-Aktiv" }));
        supplier_status.setPreferredSize(new java.awt.Dimension(56, 35));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jScrollPane5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(supplier_edit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(supplier_delete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(supplier_nama, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
                            .addComponent(supplier_email, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel46, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel43, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel42, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel41, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(supplier_id, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel45, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(supplier_simpan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(supplier_jenis, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(supplier_telp, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel44))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(supplier_txt)
                                    .addComponent(supplier_status, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel41)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(supplier_id, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel42)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(supplier_nama, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel43)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(supplier_email, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44)
                    .addComponent(supplier_txt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(supplier_telp, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(supplier_status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel45)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(supplier_jenis, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel46)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(supplier_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(supplier_edit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(supplier_delete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        supplier_tabel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        supplier_tabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                supplier_tabelMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(supplier_tabel);

        javax.swing.GroupLayout body_master_supplierLayout = new javax.swing.GroupLayout(body_master_supplier);
        body_master_supplier.setLayout(body_master_supplierLayout);
        body_master_supplierLayout.setHorizontalGroup(
            body_master_supplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(body_master_supplierLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(body_master_supplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(body_master_supplierLayout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(20, 20, 20))
                    .addGroup(body_master_supplierLayout.createSequentialGroup()
                        .addComponent(supplier_tambah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(268, 268, 268)))
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 559, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );
        body_master_supplierLayout.setVerticalGroup(
            body_master_supplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(body_master_supplierLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(body_master_supplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane3)
                    .addGroup(body_master_supplierLayout.createSequentialGroup()
                        .addComponent(supplier_tambah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Body.add(body_master_supplier, "card6");

        body_master_customer.setBackground(new java.awt.Color(0, 204, 204));

        customer_tambah.setBackground(new java.awt.Color(204, 0, 0));
        customer_tambah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                customer_tambahMouseClicked(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Tambah");

        javax.swing.GroupLayout customer_tambahLayout = new javax.swing.GroupLayout(customer_tambah);
        customer_tambah.setLayout(customer_tambahLayout);
        customer_tambahLayout.setHorizontalGroup(
            customer_tambahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
            .addGroup(customer_tambahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(customer_tambahLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel22)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        customer_tambahLayout.setVerticalGroup(
            customer_tambahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
            .addGroup(customer_tambahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(customer_tambahLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel22)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel10.setBackground(new java.awt.Color(0, 204, 204));
        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        customer_simpan.setBackground(new java.awt.Color(0, 153, 0));
        customer_simpan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                customer_simpanMouseClicked(evt);
            }
        });

        jLabel56.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(255, 255, 255));
        jLabel56.setText("Simpan");

        javax.swing.GroupLayout customer_simpanLayout = new javax.swing.GroupLayout(customer_simpan);
        customer_simpan.setLayout(customer_simpanLayout);
        customer_simpanLayout.setHorizontalGroup(
            customer_simpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
            .addGroup(customer_simpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(customer_simpanLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel56)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        customer_simpanLayout.setVerticalGroup(
            customer_simpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
            .addGroup(customer_simpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(customer_simpanLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel56)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        customer_hapus.setBackground(new java.awt.Color(204, 0, 0));

        jLabel57.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(255, 255, 255));
        jLabel57.setText("Hapus");

        javax.swing.GroupLayout customer_hapusLayout = new javax.swing.GroupLayout(customer_hapus);
        customer_hapus.setLayout(customer_hapusLayout);
        customer_hapusLayout.setHorizontalGroup(
            customer_hapusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
            .addGroup(customer_hapusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(customer_hapusLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel57)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        customer_hapusLayout.setVerticalGroup(
            customer_hapusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
            .addGroup(customer_hapusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(customer_hapusLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel57)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        customer_edit.setBackground(new java.awt.Color(0, 153, 204));
        customer_edit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                customer_editMouseClicked(evt);
            }
        });

        jLabel58.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(255, 255, 255));
        jLabel58.setText("Simpan");

        javax.swing.GroupLayout customer_editLayout = new javax.swing.GroupLayout(customer_edit);
        customer_edit.setLayout(customer_editLayout);
        customer_editLayout.setHorizontalGroup(
            customer_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 101, Short.MAX_VALUE)
            .addGroup(customer_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(customer_editLayout.createSequentialGroup()
                    .addGap(0, 25, Short.MAX_VALUE)
                    .addComponent(jLabel58)
                    .addGap(0, 25, Short.MAX_VALUE)))
        );
        customer_editLayout.setVerticalGroup(
            customer_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
            .addGroup(customer_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(customer_editLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel58)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jLabel50.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel50.setText("ID Customer");

        customer_id.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel51.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel51.setText("Nama");

        customer_nama.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel52.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel52.setText("Jenis");

        customer_jenis.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        customer_jenis.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih", "Unit", "Dropshipper", "Company" }));

        jLabel53.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel53.setText("Email");

        customer_email.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel54.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel54.setText("No.Telp");

        customer_telp.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        customer_telp.setPreferredSize(new java.awt.Dimension(59, 35));

        jLabel55.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel55.setText("Alamat");

        customer_alamat.setColumns(20);
        customer_alamat.setRows(5);
        jScrollPane6.setViewportView(customer_alamat);

        customer_txt.setText("Status");

        customer_status.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        customer_status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih", "Aktiv", "Non-Aktiv" }));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(customer_nama)
                    .addComponent(customer_email)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(customer_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(customer_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(customer_edit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel50)
                                .addComponent(jLabel51)
                                .addComponent(jLabel53)
                                .addComponent(customer_id, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel10Layout.createSequentialGroup()
                                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(customer_jenis, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel52))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel54)
                                        .addComponent(customer_telp, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel55))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(customer_txt)
                                    .addComponent(customer_status, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(21, 21, 21))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jLabel50)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(customer_id, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 19, Short.MAX_VALUE)
                .addComponent(jLabel51)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(customer_nama, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 19, Short.MAX_VALUE)
                .addComponent(jLabel53)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(customer_email, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 19, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel52)
                    .addComponent(jLabel54))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(customer_jenis, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(customer_telp, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 19, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel55)
                    .addComponent(customer_txt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(customer_status, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(customer_edit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(customer_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(customer_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29))
        );

        jScrollPane4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        customer_tabel.setModel(new javax.swing.table.DefaultTableModel(
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
        customer_tabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                customer_tabelMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(customer_tabel);

        javax.swing.GroupLayout body_master_customerLayout = new javax.swing.GroupLayout(body_master_customer);
        body_master_customer.setLayout(body_master_customerLayout);
        body_master_customerLayout.setHorizontalGroup(
            body_master_customerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(body_master_customerLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(body_master_customerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(customer_tambah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );
        body_master_customerLayout.setVerticalGroup(
            body_master_customerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(body_master_customerLayout.createSequentialGroup()
                .addContainerGap(90, Short.MAX_VALUE)
                .addGroup(body_master_customerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
                    .addGroup(body_master_customerLayout.createSequentialGroup()
                        .addComponent(customer_tambah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(60, Short.MAX_VALUE))
        );

        Body.add(body_master_customer, "card7");

        body_stok_produk.setBackground(new java.awt.Color(0, 204, 204));

        javax.swing.GroupLayout body_stok_produkLayout = new javax.swing.GroupLayout(body_stok_produk);
        body_stok_produk.setLayout(body_stok_produkLayout);
        body_stok_produkLayout.setHorizontalGroup(
            body_stok_produkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 974, Short.MAX_VALUE)
        );
        body_stok_produkLayout.setVerticalGroup(
            body_stok_produkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 733, Short.MAX_VALUE)
        );

        Body.add(body_stok_produk, "card8");

        body_stok_bahan.setBackground(new java.awt.Color(0, 204, 204));

        javax.swing.GroupLayout body_stok_bahanLayout = new javax.swing.GroupLayout(body_stok_bahan);
        body_stok_bahan.setLayout(body_stok_bahanLayout);
        body_stok_bahanLayout.setHorizontalGroup(
            body_stok_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 974, Short.MAX_VALUE)
        );
        body_stok_bahanLayout.setVerticalGroup(
            body_stok_bahanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 733, Short.MAX_VALUE)
        );

        Body.add(body_stok_bahan, "card9");

        body_laporan.setBackground(new java.awt.Color(0, 204, 204));

        javax.swing.GroupLayout body_laporanLayout = new javax.swing.GroupLayout(body_laporan);
        body_laporan.setLayout(body_laporanLayout);
        body_laporanLayout.setHorizontalGroup(
            body_laporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 974, Short.MAX_VALUE)
        );
        body_laporanLayout.setVerticalGroup(
            body_laporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 733, Short.MAX_VALUE)
        );

        Body.add(body_laporan, "card10");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Main_Menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Body, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(Body, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(Main_Menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    protected void ClearALLReq() {
//        ((DefaultTableModel)req_produk_tabel.getModel()).setNumRows(0);
        req_produk_idproduk.setText("");
        req_produk_jenis.setText("");
        req_produk_jumlah.setText("");
        req_produk_nama.setText("");
        req_produk_tgl_target.setCalendar(null);
        req_produk_satuan.setSelectedItem("Pilih");

        ((DefaultTableModel) req_bahan_tabel.getModel()).setNumRows(0);
        req_bahan_idbahan.setText("");
        req_bahan_namabahan.setText("");
        req_bahan_qtybahan.setText("");
        req_bahan_idsupp.setText("");
        req_bahan_namasup.setText("");
        req_bahan_telp.setText("");
        req_bahan_satuan.setSelectedItem("Pilih");
    }

    protected void ProdukID() {
        try {
            String query = "select MAX(RIGHT(id,8)) AS NO  from produk order by id desc";
            ps = Config.config.getConnection().prepareStatement(query);
            rs = ps.executeQuery(query);
            while (rs.next()) {
                if (rs.first() == false) {
                    produk_id.setText("PID10000001");
                } else {
                    rs.last();
                    int auto_id = rs.getInt(1) + 1;
                    String no = String.valueOf(auto_id);
                    int Nomor = no.length();
                    //MENGATUR jumlah 0
                    for (int j = 0; j < 8 - Nomor; j++) {
                        no = "0" + no;
                    }
                    produk_id.setText("PID" + no);
                }
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();//penanganan masalah
        }
    }

    protected void BahanID() {
        try {
            String query = "select MAX(RIGHT(id,8)) AS NO  from bahan order by id desc";
            ps = Config.config.getConnection().prepareStatement(query);
            rs = ps.executeQuery(query);
            while (rs.next()) {
                if (rs.first() == false) {
                    bahan_id.setText("BID10000001");
                } else {
                    rs.last();
                    int auto_id = rs.getInt(1) + 1;
                    String no = String.valueOf(auto_id);
                    int Nomor = no.length();
                    //MENGATUR jumlah 0
                    for (int j = 0; j < 8 - Nomor; j++) {
                        no = "0" + no;
                    }
                    bahan_id.setText("BID" + no);
                }
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();//penanganan masalah
        }
    }

    protected void SuppID() {
        try {
            String query = "select MAX(RIGHT(id,8)) AS NO  from supplier order by id desc";
            ps = Config.config.getConnection().prepareStatement(query);
            rs = ps.executeQuery(query);
            while (rs.next()) {
                if (rs.first() == false) {
                    supplier_id.setText("IDS10000001");
                } else {
                    rs.last();
                    int auto_id = rs.getInt(1) + 1;
                    String no = String.valueOf(auto_id);
                    int Nomor = no.length();
                    //MENGATUR jumlah 0
                    for (int j = 0; j < 8 - Nomor; j++) {
                        no = "0" + no;
                    }
                    supplier_id.setText("IDS" + no);
                }
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();//penanganan masalah
        }
    }

    protected void CustID() {
        try {
            String query = "select MAX(RIGHT(id,8)) AS NO  from customer order by id desc";
            ps = Config.config.getConnection().prepareStatement(query);
            rs = ps.executeQuery(query);
            while (rs.next()) {
                if (rs.first() == false) {
                    customer_id.setText("IDC10000001");
                } else {
                    rs.last();
                    int auto_id = rs.getInt(1) + 1;
                    String no = String.valueOf(auto_id);
                    int Nomor = no.length();
                    //MENGATUR jumlah 0
                    for (int j = 0; j < 8 - Nomor; j++) {
                        no = "0" + no;
                    }
                    customer_id.setText("IDC" + no);
                }
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();//penanganan masalah
        }
    }

    protected void ReqBahanID() {
        try {
            String query = "select MAX(RIGHT(id,8)) AS NO  from order_bahan order by id desc";
            ps = Config.config.getConnection().prepareStatement(query);
            rs = ps.executeQuery(query);
            while (rs.next()) {
                if (rs.first() == false) {
                    produk_id.setText("IDO10000001");
                } else {
                    rs.last();
                    int auto_id = rs.getInt(1) + 1;
                    String no = String.valueOf(auto_id);
                    int Nomor = no.length();
                    //MENGATUR jumlah 0
                    for (int j = 0; j < 8 - Nomor; j++) {
                        no = "0" + no;
                    }
                    req_bahan_id.setText("IDO" + no);
                }
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();//penanganan masalah
        }
    }

    protected void ReqProdukID() {
        try {
            String query = "select MAX(RIGHT(id,8)) AS NO  from order_produk order by id desc";
            ps = Config.config.getConnection().prepareStatement(query);
            rs = ps.executeQuery(query);
            while (rs.next()) {
                if (rs.first() == false) {
                    produk_id.setText("RID10000001");
                } else {
                    rs.last();
                    int auto_id = rs.getInt(1) + 1;
                    String no = String.valueOf(auto_id);
                    int Nomor = no.length();
                    //MENGATUR jumlah 0
                    for (int j = 0; j < 8 - Nomor; j++) {
                        no = "0" + no;
                    }
                    req_produk_id.setText("RID" + no);
                }
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();//penanganan masalah
        }
    }

    protected void ReqKirimID() {
        try {
            String query = "select MAX(RIGHT(id,8)) AS NO  from kirim_barang order by id desc";
            ps = Config.config.getConnection().prepareStatement(query);
            rs = ps.executeQuery(query);
            while (rs.next()) {
                if (rs.first() == false) {
                    req_kirim_id.setText("INV10000001");
                } else {
                    rs.last();
                    int auto_id = rs.getInt(1) + 1;
                    String no = String.valueOf(auto_id);
                    int Nomor = no.length();
                    //MENGATUR jumlah 0
                    for (int j = 0; j < 8 - Nomor; j++) {
                        no = "0" + no;
                    }
                    req_kirim_id.setText("INV" + no);
                }
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();//penanganan masalah
        }
    }

    protected void DisableField() {
        produk_harga.setEnabled(false);
        produk_id.setEnabled(false);
        produk_jenis.setEnabled(false);
        produk_kategori.setEnabled(false);
        produk_nama.setEnabled(false);
        produk_satuan.setEnabled(false);
        bahan_id.setEnabled(false);
        bahan_jenis.setEnabled(false);
        bahan_nama.setEnabled(false);
        bahan_satuan.setEnabled(false);
        supplier_alamat.setEnabled(false);
        supplier_email.setEnabled(false);
        supplier_id.setEnabled(false);
        supplier_jenis.setEnabled(false);
        supplier_nama.setEnabled(false);
        supplier_telp.setEnabled(false);
        customer_alamat.setEnabled(false);
        customer_email.setEnabled(false);
        customer_id.setEnabled(false);
        customer_jenis.setEnabled(false);
        customer_nama.setEnabled(false);
        customer_telp.setEnabled(false);
        produk_simpan.setVisible(false);
        customer_simpan.setVisible(false);
        bahan_simpan.setVisible(false);
        supplier_simpan.setVisible(false);
        produk_edit.setVisible(false);
        produk_delete.setVisible(false);
        bahan_delete.setVisible(false);
        bahan_edit.setVisible(false);
        customer_edit.setVisible(false);
        customer_hapus.setVisible(false);
        supplier_delete.setVisible(false);
        supplier_edit.setVisible(false);
        customer_status.setVisible(false);
        supplier_status.setVisible(false);
        supplier_txt.setVisible(false);
        customer_txt.setVisible(false);
        req_bahan_hapus.setVisible(false);
        req_bahan_simpan.setVisible(false);
        req_bahan_batal.setVisible(false);
        ClearField();
    }

    protected void ClearField() {
        produk_harga.setText("");
        produk_jenis.setSelectedItem("Pilih");
        produk_kategori.setSelectedItem("Pilih");
        produk_nama.setText("");
        produk_satuan.setSelectedItem("Pilih");
        bahan_jenis.setSelectedItem("Pilih");
        bahan_nama.setText("");
        bahan_satuan.setSelectedItem("Pilih");
        supplier_alamat.setText("");
        supplier_email.setText("");
        supplier_jenis.setText("");
        supplier_nama.setText("");
        supplier_telp.setText("");
        customer_alamat.setText("");
        customer_email.setText("");
        customer_jenis.setSelectedItem("Pilih");
        customer_nama.setText("");
        customer_telp.setText("");
    }

    protected void MasterMenuHide() {
        master_bahan.setVisible(false);
        master_produk.setVisible(false);
        master_supplier.setVisible(false);
        master_customer.setVisible(false);
    }

    protected void MasterMenuShow() {
        master_bahan.setVisible(true);
        master_produk.setVisible(true);
        master_supplier.setVisible(true);
        master_customer.setVisible(true);
    }

    protected void RequestMenuHide() {
        request_bahan.setVisible(false);
        request_produk.setVisible(false);
        request_kirim.setVisible(false);
    }

    protected void RequestMenuShow() {
        request_bahan.setVisible(true);
        request_produk.setVisible(true);
        request_kirim.setVisible(true);
    }

    protected void StockMenuHide() {
        stock_bahan_baku.setVisible(false);
        stock_produk_jadi.setVisible(false);
    }

    protected void StockMenuShow() {
        stock_bahan_baku.setVisible(true);
        stock_produk_jadi.setVisible(true);
    }

    protected void HideMenu() {
        req_up.setVisible(false);
        master_up.setVisible(false);
        stock_up.setVisible(false);
        request_bahan.setVisible(false);
        request_produk.setVisible(false);
        request_kirim.setVisible(false);
        master_bahan.setVisible(false);
        master_produk.setVisible(false);
        master_supplier.setVisible(false);
        master_customer.setVisible(false);
        stock_bahan_baku.setVisible(false);
        stock_produk_jadi.setVisible(false);
    }

    protected void BtHide() {
        produk_delete.setVisible(false);
        produk_simpan.setVisible(false);
        produk_edit.setVisible(false);
        bahan_delete.setVisible(false);
        bahan_edit.setVisible(false);
        bahan_simpan.setVisible(false);
        supplier_delete.setVisible(false);
        supplier_edit.setVisible(false);
        supplier_simpan.setVisible(false);
        customer_edit.setVisible(false);
        customer_hapus.setVisible(false);
        customer_simpan.setVisible(false);
    }

    private void SimpanProduk() {
        String stok = "0";
        String query = "INSERT INTO `produk`(`id`, `nama`, `kategori`, `jenis`, `satuan`, `qty`, `harga`) VALUES (?,?,?,?,?,?,?)";
        try {
            ps = Config.config.getConnection().prepareStatement(query);
            ps.setString(1, produk_id.getText().trim());
            ps.setString(2, produk_nama.getText().trim());
            ps.setString(3, produk_kategori.getSelectedItem().toString());
            ps.setString(4, produk_jenis.getSelectedItem().toString());
            ps.setString(5, produk_satuan.getSelectedItem().toString());
            ps.setString(6, stok);
            ps.setString(7, produk_harga.getText());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan..", "Berhasil!", HEIGHT, sucess);
            DisableField();
            ClearField();
            RunID();
            RunTable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan..", "Berhasil!", HEIGHT, sucess);
//            DisableField();
        }
    }

    private void SimpanBahan() {
        String stok = "";
        String query = "INSERT INTO `bahan`(`id`, `nama`, `jenis`, `satuan`, `stok`) VALUES (?,?,?,?,?)";
        try {
            ps = Config.config.getConnection().prepareStatement(query);
            ps.setString(1, bahan_id.getText().trim());
            ps.setString(2, bahan_nama.getText().trim());
            ps.setString(3, bahan_jenis.getSelectedItem().toString());
            ps.setString(4, bahan_satuan.getSelectedItem().toString());
            ps.setString(5, stok);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan..", "Berhasil!", HEIGHT, sucess);
            DisableField();
            ClearField();
            RunID();
            RunTable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal", "Alert Message!", HEIGHT, warning);
//            DisableField();
        }
    }

    private void SimpanSupplier() {
        String status = "Aktiv";
        String query = "INSERT INTO `supplier`(`id`, `nama`, `email`, `telp`, `alamat`, `jenis`, `status`) VALUES (?,?,?,?,?,?,?)";
        try {
            ps = Config.config.getConnection().prepareStatement(query);
            ps.setString(1, supplier_id.getText().trim());
            ps.setString(2, supplier_nama.getText().trim());
            ps.setString(3, supplier_email.getText());
            ps.setString(4, supplier_telp.getText());
            ps.setString(5, supplier_alamat.getText());
            ps.setString(6, supplier_jenis.getText().trim());
            ps.setString(7, status);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan..", "Berhasil!", HEIGHT, sucess);
            DisableField();
            ClearField();
            RunID();
            RunTable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal", "Alert Message!", HEIGHT, sucess);
        }
    }

    private void SimpanCustomer() {
        String status = "Aktiv";
        String query = "INSERT INTO `customer`(`id`, `nama`, `jenis`, `email`, `telp`, `alamat`, `status`) VALUES (?,?,?,?,?,?,?)";
        try {
            ps = Config.config.getConnection().prepareStatement(query);
            ps.setString(1, customer_id.getText().trim());
            ps.setString(2, customer_nama.getText().trim());
            ps.setString(3, customer_jenis.getSelectedItem().toString());
            ps.setString(4, customer_email.getText());
            ps.setString(5, customer_telp.getText());
            ps.setString(6, customer_alamat.getText().trim());
            ps.setString(7, status);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan..", "Berhasil!", HEIGHT, sucess);
            DisableField();
            ClearField();
            RunID();
            RunTable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal" + e.getMessage(), "Alert Message!", HEIGHT);
        }
    }

    private void OrderProduk() {
        String status = "Request";
        String Selesai = "";
        String date1 = ((JTextField) req_produk_tgl_target.getDateEditor().getUiComponent()).getText();
        String query = "INSERT INTO `order_produk`(`id`, `id_produk`, `nama_produk`, `jenis_produk`, `tgl_order`, `tgl_target`, `tgl_selesai`, `qty`, `status`) VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            ps = Config.config.getConnection().prepareStatement(query);
            ps.setString(1, req_produk_id.getText().trim());
            ps.setString(2, req_produk_idproduk.getText().trim());
            ps.setString(3, req_produk_nama.getText().trim());
            ps.setString(4, req_produk_jenis.getText().trim());
            ps.setString(5, req_produk_tgl.getText());
            ps.setString(6, date1);
            ps.setString(7, Selesai);
            ps.setString(8, req_produk_jumlah.getText());
            ps.setString(9, status);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan..", "Berhasil!", HEIGHT, sucess);
            DisableField();
            ClearField();
            RunID();
            RunTable();
            ClearALLReq();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal" + e.getMessage(), "Alert Message!", HEIGHT);
        }
    }

    private void OrderBahan() {
        String status = "Proses Order";
        String tglTerima = "";
        String query = "INSERT INTO `order_bahan`(`id`, `id_supplier`, `nama_supplier`, `jenis_item`, `jumlah_item`, `tanggal`, `tanggal_terima`, `status`) VALUES (?,?,?,?,?,?,?,?)";
        String query2 = "INSERT INTO `item_po_bahan`(`id_order_bahan`, `id_supplier`, `nama_supplier`, `id_bahan`, `nama_bahan`, `satuan`, `qty_item`) VALUES (?,?,?,?,?,?,?)";
        try {
            ps = Config.config.getConnection().prepareStatement(query);
            ps.setString(1, req_bahan_id.getText().trim());
            ps.setString(2, req_bahan_idsupp.getText().trim());
            ps.setString(3, req_bahan_namasup.getText().trim());
            ps.setString(4, txt_jumlah_item.getText().trim());
            ps.setString(5, txt_total_ite.getText());
            ps.setString(6, req_bahan_tgl.getText());
            ps.setString(7, tglTerima);
            ps.setString(8, status);
            ps.executeUpdate();

            int t = req_bahan_tabel.getRowCount();
            for (int i = 0; i < t; i++) {
                String idbahan = req_bahan_tabel.getValueAt(i, 3).toString();
                String namabahan = req_bahan_tabel.getValueAt(i, 4).toString();
                String jumlah = req_bahan_tabel.getValueAt(i, 5).toString();
                String satuan = req_bahan_tabel.getValueAt(i, 6).toString();

                PreparedStatement stat2 = Config.config.getConnection().prepareStatement(query2);
                stat2.setString(1, req_bahan_id.getText());
                stat2.setString(2, req_bahan_idsupp.getText());
                stat2.setString(3, req_bahan_namasup.getText());
                stat2.setString(4, idbahan);
                stat2.setString(5, namabahan);
                stat2.setString(6, jumlah);
                stat2.setString(7, satuan);
                stat2.executeUpdate();
            }
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan..", "Berhasil!", HEIGHT, sucess);
            DisableField();
            ClearField();
            RunID();
            RunTable();
            ClearALLReq();
            txt_jumlah_item.setText("0");
            txt_total_ite.setText("0");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal" + e.getMessage(), "Alert Message!", HEIGHT);
        }
    }
    
    private void KirimBarang() {
        String status = "Kirim Barang";
        String tglKirim = "";
        String query = "INSERT INTO `kirim_barang`(`id`, `id_customer`, `nama_customer`, `telp`, `alamat`, `qty_item`, `jumlah`, `harga`, `tanggal`, `status`) VALUES (?,?,?,?,?,?,?,?,?,?)";
        String query2 = "INSERT INTO `item_kirim_barang`(`id_kirim_barang`, `id_produk`, `nama_produk`, `qty`, `harga`) VALUES (?,?,?,?,?)";
        try {
            ps = Config.config.getConnection().prepareStatement(query);
            ps.setString(1, req_kirim_id.getText().trim());
            ps.setString(2, req_kirim_custid.getText().trim());
            ps.setString(3, req_kirim_nama.getText().trim());
            ps.setString(4, req_kirim_telp.getText().trim());
            ps.setString(5, req_kirim_alamat.getText());
            ps.setString(6, txt_kirim_item.getText());
            ps.setString(7, txt_kirim_total.getText());
            ps.setString(8, txt_kirim_totharga.getText());
            ps.setString(9, tglKirim);
            ps.setString(10, status);
            ps.executeUpdate();

            int t = req_kirim_tabel.getRowCount();
            for (int i = 0; i < t; i++) {
                String idProduk = req_kirim_tabel.getValueAt(i, 1).toString();
                String namaProduk = req_kirim_tabel.getValueAt(i, 2).toString();
                String qty = req_kirim_tabel.getValueAt(i, 4).toString();
                String harga = req_kirim_tabel.getValueAt(i, 3).toString();

                PreparedStatement stat2 = Config.config.getConnection().prepareStatement(query2);
                stat2.setString(1, req_kirim_id.getText());
                stat2.setString(2, idProduk);
                stat2.setString(3, namaProduk);
                stat2.setString(4, qty);
                stat2.setString(5, harga);
                stat2.executeUpdate();
            }
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan..", "Berhasil!", HEIGHT, sucess);
            DisableField();
            ClearField();
            RunID();
            RunTable();
            ClearALLReq();
            txt_kirim_item.setText("0");
            txt_kirim_total.setText("0");
            txt_kirim_totharga.setText("0");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal" + e.getMessage(), "Alert Message!", HEIGHT);
        }
    }

    protected void ProdukTable() {

        Object[] Baris = {"ID", "Nama", "Kategori", "Jenis", "Satuan", "Harga"};
        tablemodelProduk = new DefaultTableModel(null, Baris);

        try {
            String sql = "SELECT id, nama, kategori, jenis, satuan, harga FROM produk order by id asc";
            Statement stat = Config.config.getConnection().createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()) {
                tablemodelProduk.addRow(new Object[]{
                    hasil.getString(1),
                    hasil.getString(2),
                    hasil.getString(3),
                    hasil.getString(4),
                    hasil.getString(5),
                    hasil.getString(6)
                });
            }
            produk_tabel.setModel(tablemodelProduk);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal Pelamar: " + e);
        }
    }

    protected void BahanTable() {

        Object[] Baris = {"ID", "Nama", "Jenis", "Satuan"};
        tablemodelBahan = new DefaultTableModel(null, Baris);

        try {
            String sql = "SELECT id, nama, jenis, satuan FROM bahan order by id asc";
            Statement stat = Config.config.getConnection().createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()) {
                tablemodelBahan.addRow(new Object[]{
                    hasil.getString(1),
                    hasil.getString(2),
                    hasil.getString(3),
                    hasil.getString(4)
                });
            }
            bahan_tabel.setModel(tablemodelBahan);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal Memuat: " + e);
        }
    }

    protected void SupplierTable() {

        Object[] Baris = {"ID", "Nama", "Email", "Telp", "Alamat", "Jenis", "Status"};
        tablemodelSupplier = new DefaultTableModel(null, Baris);

        try {
            String sql = "SELECT * FROM supplier order by id asc";
            Statement stat = Config.config.getConnection().createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()) {
                tablemodelSupplier.addRow(new Object[]{
                    hasil.getString(1),
                    hasil.getString(2),
                    hasil.getString(3),
                    hasil.getString(4),
                    hasil.getString(5),
                    hasil.getString(6),
                    hasil.getString(7)
                });
            }
            supplier_tabel.setModel(tablemodelSupplier);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal Memuat: " + e);
        }
    }

    protected void CustomerTable() {

        Object[] Baris = {"ID", "Nama", "Jenis", "Email", "Telp", "Alamat", "Status"};
        tablemodelCustomer = new DefaultTableModel(null, Baris);

        try {
            String sql = "SELECT * FROM customer order by id asc";
            Statement stat = Config.config.getConnection().createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()) {
                tablemodelCustomer.addRow(new Object[]{
                    hasil.getString(1),
                    hasil.getString(2),
                    hasil.getString(3),
                    hasil.getString(4),
                    hasil.getString(5),
                    hasil.getString(6),
                    hasil.getString(7)
                });
            }
            customer_tabel.setModel(tablemodelCustomer);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal Pelamar: " + e);
        }
    }

    protected void ReqProdukTable() {

        Object[] Baris = {"ID", "Produk ID", "Produk Nama", "Jenis Produk", "Tanggal Request", "Jumlah", "Status"};
        tabmodeOrderProduk = new DefaultTableModel(null, Baris);

        try {
            String sql = "SELECT id, id_produk, nama_produk, jenis_produk, tgl_order, qty, status FROM order_produk order by tgl_order asc";
            Statement stat = Config.config.getConnection().createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()) {
                tabmodeOrderProduk.addRow(new Object[]{
                    hasil.getString(1),
                    hasil.getString(2),
                    hasil.getString(3),
                    hasil.getString(4),
                    hasil.getString(5),
                    hasil.getString(6),
                    hasil.getString(7)
                });
            }
            req_produk_tabel.setModel(tabmodeOrderProduk);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal Memuat: " + e);
        }
    }

    private void OpenSubMenu(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OpenSubMenu
        if (evt.getSource() == req_down) {
            req_down.setVisible(false);
            req_up.setVisible(true);
            RequestMenuShow();
            MasterMenuHide();
            StockMenuHide();
            ClearALLReq();
            master_down.setVisible(true);
            master_up.setVisible(false);
            stock_down.setVisible(true);
            stock_up.setVisible(false);
            DisableField();
        } else if (evt.getSource() == req_up) {
            req_down.setVisible(true);
            req_up.setVisible(false);
            RequestMenuHide();
            MasterMenuHide();
            StockMenuHide();
            ClearALLReq();
            master_down.setVisible(true);
            master_up.setVisible(false);
            stock_down.setVisible(true);
            stock_up.setVisible(false);
            DisableField();

        } else if (evt.getSource() == master_down) {
            master_down.setVisible(false);
            master_up.setVisible(true);
            MasterMenuShow();
            RequestMenuHide();
            StockMenuHide();
            ClearALLReq();
            stock_down.setVisible(true);
            stock_up.setVisible(false);
            req_down.setVisible(true);
            req_up.setVisible(false);
            DisableField();
        } else if (evt.getSource() == master_up) {
            master_down.setVisible(true);
            master_up.setVisible(false);
            MasterMenuHide();
            RequestMenuHide();
            StockMenuHide();
            ClearALLReq();
            stock_down.setVisible(true);
            stock_up.setVisible(false);
            req_down.setVisible(true);
            req_up.setVisible(false);
            DisableField();
        } else if (evt.getSource() == stock_down) {
            stock_down.setVisible(false);
            stock_up.setVisible(true);
            StockMenuShow();
            RequestMenuHide();
            MasterMenuHide();
            ClearALLReq();
            req_down.setVisible(true);
            req_up.setVisible(false);
            master_down.setVisible(true);
            master_up.setVisible(false);
            DisableField();
        } else if (evt.getSource() == stock_up) {
            stock_down.setVisible(true);
            stock_up.setVisible(false);
            StockMenuHide();
            RequestMenuHide();
            MasterMenuHide();
            ClearALLReq();
            req_down.setVisible(true);
            req_up.setVisible(false);
            master_down.setVisible(true);
            master_up.setVisible(false);
            DisableField();
        }
    }//GEN-LAST:event_OpenSubMenu

    private void MenuClick(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MenuClick
//        0,153,153
//        0,204,204
        if (evt.getSource() == request_produk) {
            body_home.setVisible(false);
            body_req_bahan.setVisible(false);
            body_req_produk.setVisible(true);
            body_req_kirim.setVisible(false);
            body_master_bahan.setVisible(false);
            body_master_customer.setVisible(false);
            body_master_produk.setVisible(false);
            body_master_supplier.setVisible(false);
            body_stok_bahan.setVisible(false);
            body_stok_produk.setVisible(false);
            body_laporan.setVisible(false);
            DisableField();
            ClearALLReq();

            title_page.setText("Request Order Produk");

            request_produk.setBackground(new Color(0, 153, 153));//dark
            request_bahan.setBackground(new Color(0, 204, 204));//dark
            request_kirim.setBackground(new Color(0, 204, 204));//dark
            master_bahan.setBackground(new Color(0, 204, 204));//dark
            master_customer.setBackground(new Color(0, 204, 204));//dark
            master_produk.setBackground(new Color(0, 204, 204));//dark
            master_supplier.setBackground(new Color(0, 204, 204));//dark
            stock_bahan_baku.setBackground(new Color(0, 204, 204));//dark
            stock_produk_jadi.setBackground(new Color(0, 204, 204));//dark
            laporan.setBackground(new Color(0, 204, 204));//dark
        } else if (evt.getSource() == request_bahan) {
            body_home.setVisible(false);
            body_req_bahan.setVisible(true);
            body_req_produk.setVisible(false);
            body_req_kirim.setVisible(false);
            body_master_bahan.setVisible(false);
            body_master_customer.setVisible(false);
            body_master_produk.setVisible(false);
            body_master_supplier.setVisible(false);
            body_stok_bahan.setVisible(false);
            body_stok_produk.setVisible(false);
            body_laporan.setVisible(false);
            DisableField();
            ClearALLReq();

            title_page.setText("Request Order Bahan");

            request_produk.setBackground(new Color(0, 204, 204));//dark
            request_bahan.setBackground(new Color(0, 153, 153));//dark
            request_kirim.setBackground(new Color(0, 204, 204));//dark
            master_bahan.setBackground(new Color(0, 204, 204));//dark
            master_customer.setBackground(new Color(0, 204, 204));//dark
            master_produk.setBackground(new Color(0, 204, 204));//dark
            master_supplier.setBackground(new Color(0, 204, 204));//dark
            stock_bahan_baku.setBackground(new Color(0, 204, 204));//dark
            stock_produk_jadi.setBackground(new Color(0, 204, 204));//dark
            laporan.setBackground(new Color(0, 204, 204));//dark
        } else if (evt.getSource() == request_kirim) {
            body_home.setVisible(false);
            body_req_bahan.setVisible(false);
            body_req_produk.setVisible(false);
            body_req_kirim.setVisible(true);
            body_master_bahan.setVisible(false);
            body_master_customer.setVisible(false);
            body_master_produk.setVisible(false);
            body_master_supplier.setVisible(false);
            body_stok_bahan.setVisible(false);
            body_stok_produk.setVisible(false);
            body_laporan.setVisible(false);
            DisableField();
            ClearALLReq();

            title_page.setText("Kirim Barang");

            request_produk.setBackground(new Color(0, 204, 204));//dark
            request_bahan.setBackground(new Color(0, 204, 204));//dark
            request_kirim.setBackground(new Color(0, 153, 153));//dark
            master_bahan.setBackground(new Color(0, 204, 204));//dark
            master_customer.setBackground(new Color(0, 204, 204));//dark
            master_produk.setBackground(new Color(0, 204, 204));//dark
            master_supplier.setBackground(new Color(0, 204, 204));//dark
            stock_bahan_baku.setBackground(new Color(0, 204, 204));//dark
            stock_produk_jadi.setBackground(new Color(0, 204, 204));//dark
            laporan.setBackground(new Color(0, 204, 204));//dark
        } else if (evt.getSource() == master_produk) {
            body_home.setVisible(false);
            body_req_bahan.setVisible(false);
            body_req_produk.setVisible(false);
            body_req_kirim.setVisible(false);
            body_master_bahan.setVisible(false);
            body_master_customer.setVisible(false);
            body_master_produk.setVisible(true);
            body_master_supplier.setVisible(false);
            body_stok_bahan.setVisible(false);
            body_stok_produk.setVisible(false);
            body_laporan.setVisible(false);
            DisableField();
            ClearALLReq();

            title_page.setText("Data Produk");

            request_produk.setBackground(new Color(0, 204, 204));//dark
            request_bahan.setBackground(new Color(0, 204, 204));//dark
            request_kirim.setBackground(new Color(0, 204, 204));//dark
            master_bahan.setBackground(new Color(0, 204, 204));//dark
            master_customer.setBackground(new Color(0, 204, 204));//dark
            master_produk.setBackground(new Color(0, 153, 153));//dark
            master_supplier.setBackground(new Color(0, 204, 204));//dark
            stock_bahan_baku.setBackground(new Color(0, 204, 204));//dark
            stock_produk_jadi.setBackground(new Color(0, 204, 204));//dark
            laporan.setBackground(new Color(0, 204, 204));//dark
        } else if (evt.getSource() == master_bahan) {
            body_home.setVisible(false);
            body_req_bahan.setVisible(false);
            body_req_produk.setVisible(false);
            body_req_kirim.setVisible(false);
            body_master_bahan.setVisible(true);
            body_master_customer.setVisible(false);
            body_master_produk.setVisible(false);
            body_master_supplier.setVisible(false);
            body_stok_bahan.setVisible(false);
            body_stok_produk.setVisible(false);
            body_laporan.setVisible(false);
            DisableField();
            ClearALLReq();

            title_page.setText("Data Bahan Baku");

            request_produk.setBackground(new Color(0, 204, 204));//dark
            request_bahan.setBackground(new Color(0, 204, 204));//dark
            request_kirim.setBackground(new Color(0, 204, 204));//dark
            master_bahan.setBackground(new Color(0, 153, 153));//dark
            master_customer.setBackground(new Color(0, 204, 204));//dark
            master_produk.setBackground(new Color(0, 204, 204));//dark
            master_supplier.setBackground(new Color(0, 204, 204));//dark
            stock_bahan_baku.setBackground(new Color(0, 204, 204));//dark
            stock_produk_jadi.setBackground(new Color(0, 204, 204));//dark
            laporan.setBackground(new Color(0, 204, 204));//dark
        } else if (evt.getSource() == master_supplier) {
            body_home.setVisible(false);
            body_req_bahan.setVisible(false);
            body_req_produk.setVisible(false);
            body_req_kirim.setVisible(false);
            body_master_bahan.setVisible(false);
            body_master_customer.setVisible(false);
            body_master_produk.setVisible(false);
            body_master_supplier.setVisible(true);
            body_stok_bahan.setVisible(false);
            body_stok_produk.setVisible(false);
            body_laporan.setVisible(false);
            DisableField();
            ClearALLReq();

            title_page.setText("Data Supplier");

            request_produk.setBackground(new Color(0, 204, 204));//dark
            request_bahan.setBackground(new Color(0, 204, 204));//dark
            request_kirim.setBackground(new Color(0, 204, 204));//dark
            master_bahan.setBackground(new Color(0, 204, 204));//dark
            master_customer.setBackground(new Color(0, 204, 204));//dark
            master_produk.setBackground(new Color(0, 204, 204));//dark
            master_supplier.setBackground(new Color(0, 153, 153));//dark
            stock_bahan_baku.setBackground(new Color(0, 204, 204));//dark
            stock_produk_jadi.setBackground(new Color(0, 204, 204));//dark
            laporan.setBackground(new Color(0, 204, 204));//dark
        } else if (evt.getSource() == master_customer) {
            body_home.setVisible(false);
            body_req_bahan.setVisible(false);
            body_req_produk.setVisible(false);
            body_req_kirim.setVisible(false);
            body_master_bahan.setVisible(false);
            body_master_customer.setVisible(true);
            body_master_produk.setVisible(false);
            body_master_supplier.setVisible(false);
            body_stok_bahan.setVisible(false);
            body_stok_produk.setVisible(false);
            body_laporan.setVisible(false);
            DisableField();
            ClearALLReq();

            title_page.setText("Data Customer");

            request_produk.setBackground(new Color(0, 204, 204));//dark
            request_bahan.setBackground(new Color(0, 204, 204));//dark
            request_kirim.setBackground(new Color(0, 204, 204));//dark
            master_bahan.setBackground(new Color(0, 204, 204));//dark
            master_customer.setBackground(new Color(0, 153, 153));//dark
            master_produk.setBackground(new Color(0, 204, 204));//dark
            master_supplier.setBackground(new Color(0, 204, 204));//dark
            stock_bahan_baku.setBackground(new Color(0, 204, 204));//dark
            stock_produk_jadi.setBackground(new Color(0, 204, 204));//dark
            laporan.setBackground(new Color(0, 204, 204));//dark
        } else if (evt.getSource() == stock_produk_jadi) {
            body_home.setVisible(false);
            body_req_bahan.setVisible(false);
            body_req_produk.setVisible(false);
            body_req_kirim.setVisible(false);
            body_master_bahan.setVisible(false);
            body_master_customer.setVisible(false);
            body_master_produk.setVisible(false);
            body_master_supplier.setVisible(false);
            body_stok_bahan.setVisible(false);
            body_stok_produk.setVisible(true);
            body_laporan.setVisible(false);
            DisableField();
            ClearALLReq();

            title_page.setText("Stok Produk Jadi");

            request_produk.setBackground(new Color(0, 204, 204));//dark
            request_bahan.setBackground(new Color(0, 204, 204));//dark
            request_kirim.setBackground(new Color(0, 204, 204));//dark
            master_bahan.setBackground(new Color(0, 204, 204));//dark
            master_customer.setBackground(new Color(0, 204, 204));//dark
            master_produk.setBackground(new Color(0, 204, 204));//dark
            master_supplier.setBackground(new Color(0, 204, 204));//dark
            stock_bahan_baku.setBackground(new Color(0, 204, 204));//dark
            stock_produk_jadi.setBackground(new Color(0, 153, 153));//dark
            laporan.setBackground(new Color(0, 204, 204));//dark
        } else if (evt.getSource() == stock_bahan_baku) {
            body_home.setVisible(false);
            body_req_bahan.setVisible(false);
            body_req_produk.setVisible(false);
            body_req_kirim.setVisible(false);
            body_master_bahan.setVisible(false);
            body_master_customer.setVisible(false);
            body_master_produk.setVisible(false);
            body_master_supplier.setVisible(false);
            body_stok_bahan.setVisible(true);
            body_stok_produk.setVisible(false);
            body_laporan.setVisible(false);
            DisableField();
            ClearALLReq();

            title_page.setText("Stok Bahan Baku");

            request_produk.setBackground(new Color(0, 204, 204));//dark
            request_bahan.setBackground(new Color(0, 204, 204));//dark
            request_kirim.setBackground(new Color(0, 204, 204));//dark
            master_bahan.setBackground(new Color(0, 204, 204));//dark
            master_customer.setBackground(new Color(0, 204, 204));//dark
            master_produk.setBackground(new Color(0, 204, 204));//dark
            master_supplier.setBackground(new Color(0, 204, 204));//dark
            stock_bahan_baku.setBackground(new Color(0, 153, 153));//dark
            stock_produk_jadi.setBackground(new Color(0, 204, 204));//dark
            laporan.setBackground(new Color(0, 204, 204));//dark
        } else if (evt.getSource() == laporan) {
            body_home.setVisible(false);
            body_req_bahan.setVisible(false);
            body_req_produk.setVisible(false);
            body_req_kirim.setVisible(false);
            body_master_bahan.setVisible(false);
            body_master_customer.setVisible(false);
            body_master_produk.setVisible(false);
            body_master_supplier.setVisible(false);
            body_stok_bahan.setVisible(false);
            body_stok_produk.setVisible(false);
            body_laporan.setVisible(true);
            DisableField();
            ClearALLReq();

            title_page.setText("Laporan");

            request_produk.setBackground(new Color(0, 204, 204));//dark
            request_bahan.setBackground(new Color(0, 204, 204));//dark
            request_kirim.setBackground(new Color(0, 204, 204));//dark
            master_bahan.setBackground(new Color(0, 204, 204));//dark
            master_customer.setBackground(new Color(0, 204, 204));//dark
            master_produk.setBackground(new Color(0, 204, 204));//dark
            master_supplier.setBackground(new Color(0, 204, 204));//dark
            stock_bahan_baku.setBackground(new Color(0, 204, 204));//dark
            stock_produk_jadi.setBackground(new Color(0, 204, 204));//dark
            laporan.setBackground(new Color(0, 153, 153));//dark
        } else if (evt.getSource() == img_home) {
            body_home.setVisible(true);
            body_req_bahan.setVisible(false);
            body_req_produk.setVisible(false);
            body_req_kirim.setVisible(false);
            body_master_bahan.setVisible(false);
            body_master_customer.setVisible(false);
            body_master_produk.setVisible(false);
            body_master_supplier.setVisible(false);
            body_stok_bahan.setVisible(false);
            body_stok_produk.setVisible(false);
            body_laporan.setVisible(false);
            DisableField();
            MasterMenuHide();
            RequestMenuHide();
            StockMenuHide();
            ClearALLReq();
            req_up.setVisible(false);
            req_down.setVisible(true);
            master_down.setVisible(true);
            master_up.setVisible(false);
            stock_down.setVisible(true);
            stock_up.setVisible(false);

            title_page.setText("Halaman Utama");

            request_produk.setBackground(new Color(0, 204, 204));//dark
            request_bahan.setBackground(new Color(0, 204, 204));//dark
            request_kirim.setBackground(new Color(0, 204, 204));//dark
            master_bahan.setBackground(new Color(0, 204, 204));//dark
            master_customer.setBackground(new Color(0, 204, 204));//dark
            master_produk.setBackground(new Color(0, 204, 204));//dark
            master_supplier.setBackground(new Color(0, 204, 204));//dark
            stock_bahan_baku.setBackground(new Color(0, 204, 204));//dark
            stock_produk_jadi.setBackground(new Color(0, 204, 204));//dark
            laporan.setBackground(new Color(0, 204, 204));//dark
        }
    }//GEN-LAST:event_MenuClick

    private void produk_simpanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_produk_simpanMouseClicked
        if (produk_id.getText().isEmpty() || produk_nama.getText().isEmpty() || produk_harga.getText().isEmpty()
                || produk_kategori.getSelectedItem().equals("Pilih")
                || produk_jenis.getSelectedItem().equals("Pilih") || produk_satuan.getSelectedItem().equals("pilih")) {
            JOptionPane.showMessageDialog(null, "Data Belum Terpenuhi!", "Peringatan!", HEIGHT, invalid);

        } else {
            SimpanProduk();
        }
    }//GEN-LAST:event_produk_simpanMouseClicked

    private void bahan_simpanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bahan_simpanMouseClicked
        if (bahan_nama.getText().isEmpty() || bahan_jenis.getSelectedItem().equals("Pilih")
                || bahan_satuan.getSelectedItem().equals("Pilih")) {
            JOptionPane.showMessageDialog(null, "Data Belum Terpenuhi!", "Peringatan!", HEIGHT, invalid);

        } else {
            SimpanBahan();
        }
    }//GEN-LAST:event_bahan_simpanMouseClicked

    private void supplier_simpanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_supplier_simpanMouseClicked
        if (supplier_alamat.getText().isEmpty() || supplier_email.getText().isEmpty() || supplier_jenis.getText().isEmpty()
                || supplier_nama.getText().isEmpty() || supplier_telp.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Data Belum Terpenuhi!", "Peringatan!", HEIGHT, invalid);

        } else {
            SimpanSupplier();
        }
    }//GEN-LAST:event_supplier_simpanMouseClicked

    private void customer_simpanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_customer_simpanMouseClicked
        if (customer_alamat.getText().isEmpty() || customer_email.getText().isEmpty() || customer_nama.getText().isEmpty()
                || customer_telp.getText().isEmpty() || customer_jenis.getSelectedItem().equals("Pilih")) {
            JOptionPane.showMessageDialog(null, "Data Belum Terpenuhi!", "Peringatan!", HEIGHT, invalid);

        } else {
            SimpanCustomer();
        }
    }//GEN-LAST:event_customer_simpanMouseClicked

    private void produk_tambahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_produk_tambahMouseClicked
        produk_simpan.setVisible(true);
        produk_harga.setEnabled(true);
        produk_id.setEnabled(false);
        produk_jenis.setEnabled(true);
        produk_kategori.setEnabled(true);
        produk_nama.setEnabled(true);
        produk_satuan.setEnabled(true);
    }//GEN-LAST:event_produk_tambahMouseClicked

    private void bahan_tambahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bahan_tambahMouseClicked
        bahan_id.setEnabled(false);
        bahan_jenis.setEnabled(true);
        bahan_nama.setEnabled(true);
        bahan_satuan.setEnabled(true);
        bahan_simpan.setVisible(true);
    }//GEN-LAST:event_bahan_tambahMouseClicked

    private void supplier_tambahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_supplier_tambahMouseClicked
        supplier_alamat.setEnabled(true);
        supplier_email.setEnabled(true);
        supplier_id.setEnabled(false);
        supplier_jenis.setEnabled(true);
        supplier_nama.setEnabled(true);
        supplier_telp.setEnabled(true);
        supplier_simpan.setVisible(true);
    }//GEN-LAST:event_supplier_tambahMouseClicked

    private void customer_tambahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_customer_tambahMouseClicked
        customer_alamat.setEnabled(true);
        customer_email.setEnabled(true);
        customer_id.setEnabled(false);
        customer_jenis.setEnabled(true);
        customer_nama.setEnabled(true);
        customer_telp.setEnabled(true);
        customer_simpan.setVisible(true);
    }//GEN-LAST:event_customer_tambahMouseClicked

    private void customer_tabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_customer_tabelMouseClicked
        customer_simpan.setVisible(false);
        customer_edit.setVisible(true);
        customer_hapus.setVisible(true);
        customer_nama.setEnabled(true);
        customer_jenis.setEnabled(true);
        customer_email.setEnabled(true);
        customer_telp.setEnabled(true);
        customer_alamat.setEnabled(true);
        customer_status.setVisible(true);
        customer_txt.setVisible(true);
        int bar = customer_tabel.getSelectedRow();
        String a = tablemodelCustomer.getValueAt(bar, 0).toString();
        String b = tablemodelCustomer.getValueAt(bar, 1).toString();
        String c = tablemodelCustomer.getValueAt(bar, 2).toString();
        String d = tablemodelCustomer.getValueAt(bar, 3).toString();
        String e = tablemodelCustomer.getValueAt(bar, 4).toString();
        String f = tablemodelCustomer.getValueAt(bar, 5).toString();
        String g = tablemodelCustomer.getValueAt(bar, 6).toString();

        customer_id.setText(a);
        customer_nama.setText(b);
        customer_jenis.setSelectedItem(c);
        customer_email.setText(d);
        customer_telp.setText(e);
        customer_alamat.setText(f);
        customer_status.setSelectedItem(g);
    }//GEN-LAST:event_customer_tabelMouseClicked

    private void supplier_tabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_supplier_tabelMouseClicked
        supplier_simpan.setVisible(false);
        supplier_delete.setVisible(true);
        supplier_edit.setVisible(true);
        supplier_nama.setEnabled(true);
        supplier_jenis.setEnabled(true);
        supplier_email.setEnabled(true);
        supplier_telp.setEnabled(true);
        supplier_alamat.setEnabled(true);
        supplier_jenis.setEnabled(true);
        supplier_status.setVisible(true);
        supplier_txt.setVisible(true);
        int bar = supplier_tabel.getSelectedRow();
        String a = tablemodelSupplier.getValueAt(bar, 0).toString();
        String b = tablemodelSupplier.getValueAt(bar, 1).toString();
        String c = tablemodelSupplier.getValueAt(bar, 2).toString();
        String d = tablemodelSupplier.getValueAt(bar, 3).toString();
        String e = tablemodelSupplier.getValueAt(bar, 4).toString();
        String f = tablemodelSupplier.getValueAt(bar, 5).toString();
        String g = tablemodelSupplier.getValueAt(bar, 6).toString();

        supplier_id.setText(a);
        supplier_nama.setText(b);
        supplier_email.setText(c);
        supplier_telp.setText(d);
        supplier_alamat.setText(e);
        supplier_jenis.setText(f);
        supplier_status.setSelectedItem(g);
    }//GEN-LAST:event_supplier_tabelMouseClicked

    private void bahan_tabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bahan_tabelMouseClicked
        bahan_simpan.setVisible(false);
        bahan_delete.setVisible(true);
        bahan_edit.setVisible(true);
        bahan_nama.setEnabled(true);
        bahan_jenis.setEnabled(true);
        bahan_satuan.setEnabled(true);
        int bar = bahan_tabel.getSelectedRow();
        String a = tablemodelBahan.getValueAt(bar, 0).toString();
        String b = tablemodelBahan.getValueAt(bar, 1).toString();
        String c = tablemodelBahan.getValueAt(bar, 2).toString();
        String d = tablemodelBahan.getValueAt(bar, 3).toString();

        bahan_id.setText(a);
        bahan_nama.setText(b);
        bahan_jenis.setSelectedItem(c);
        bahan_satuan.setSelectedItem(d);
    }//GEN-LAST:event_bahan_tabelMouseClicked

    private void produk_tabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_produk_tabelMouseClicked
        produk_simpan.setVisible(false);
        produk_delete.setVisible(true);
        produk_edit.setVisible(true);
        produk_nama.setEnabled(true);
        produk_kategori.setEnabled(true);
        produk_jenis.setEnabled(true);
        produk_satuan.setEnabled(true);
        produk_harga.setEnabled(true);
        int bar = produk_tabel.getSelectedRow();
        String a = tablemodelProduk.getValueAt(bar, 0).toString();
        String b = tablemodelProduk.getValueAt(bar, 1).toString();
        String c = tablemodelProduk.getValueAt(bar, 2).toString();
        String d = tablemodelProduk.getValueAt(bar, 3).toString();
        String e = tablemodelProduk.getValueAt(bar, 4).toString();
        String f = tablemodelProduk.getValueAt(bar, 5).toString();

        produk_id.setText(a);
        produk_nama.setText(b);
        produk_kategori.setSelectedItem(c);
        produk_jenis.setSelectedItem(d);
        produk_satuan.setSelectedItem(e);
        produk_harga.setText(f);
    }//GEN-LAST:event_produk_tabelMouseClicked

    private void supplier_editMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_supplier_editMouseClicked
        if (supplier_id.getText().isEmpty() || supplier_nama.getText().isEmpty() || supplier_email.getText().isEmpty()
                || supplier_telp.getText().isEmpty() || supplier_jenis.getText().isEmpty()
                || supplier_status.getSelectedItem().equals("Pilih")) {

            JOptionPane.showMessageDialog(null, "Periksa Data!", "Perhatian!", HEIGHT, warning);
        } else {
            try {
                String sql = "update supplier set nama=?,email=?,telp=?,alamat=?,jenis=?,status=? where id='" + supplier_id.getText() + "'";
                PreparedStatement stat = Config.config.getConnection().prepareStatement(sql);
                stat.setString(1, supplier_nama.getText());
                stat.setString(2, supplier_email.getText());
                stat.setString(3, supplier_telp.getText());
                stat.setString(4, supplier_alamat.getText());
                stat.setString(5, supplier_jenis.getText());
                stat.setString(6, supplier_status.getSelectedItem().toString());

                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil Di Ubah!", "Berhasil!", HEIGHT, sucess);
                DisableField();
                RunTable();
                RunID();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Terjadi Kesalahan!", "Perhatian!", HEIGHT, invalid);
            }
        }
    }//GEN-LAST:event_supplier_editMouseClicked

    private void customer_editMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_customer_editMouseClicked
        if (customer_alamat.getText().isEmpty() || customer_email.getText().isEmpty() || customer_nama.getText().isEmpty()
                || customer_telp.getText().isEmpty() || customer_jenis.getSelectedItem().equals("Pilih") || customer_id.getText().isEmpty()
                || customer_status.getSelectedItem().equals("Pilih")) {
            JOptionPane.showMessageDialog(null, "Periksa Data!", "Peringatan!", HEIGHT, warning);
        } else {
            try {
                String sql = "update customer set nama=?,jenis=?,email=?,telp=?,alamat=?,status=? where id='" + customer_id.getText() + "'";
                PreparedStatement stat = Config.config.getConnection().prepareStatement(sql);
                stat.setString(1, customer_nama.getText());
                stat.setString(2, customer_jenis.getSelectedItem().toString());
                stat.setString(3, customer_email.getText());
                stat.setString(4, customer_telp.getText());
                stat.setString(5, customer_alamat.getText());
                stat.setString(6, customer_status.getSelectedItem().toString());

                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil Di Ubah!", "Berhasil!", HEIGHT, sucess);
                DisableField();
                RunTable();
                RunID();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Terjadi Kesalahan!", "Perhatian!", HEIGHT, invalid);
            }
        }
    }//GEN-LAST:event_customer_editMouseClicked

    private void bahan_editMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bahan_editMouseClicked
        if (bahan_nama.getText().isEmpty() || bahan_jenis.getSelectedItem().equals("Pilih")
                || bahan_satuan.getSelectedItem().equals("Pilih") || bahan_id.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Periksa Data!", "Peringatan!", HEIGHT, warning);
        } else {
            try {
                String sql = "update bahan set nama=?,jenis=?,satuan=? where id='" + bahan_id.getText() + "'";
                PreparedStatement stat = Config.config.getConnection().prepareStatement(sql);
                stat.setString(1, bahan_nama.getText());
                stat.setString(2, bahan_jenis.getSelectedItem().toString());
                stat.setString(3, bahan_satuan.getSelectedItem().toString());

                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil Di Ubah!", "Berhasil!", HEIGHT, sucess);
                DisableField();
                RunTable();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Terjadi Kesalahan!", "Perhatian!", HEIGHT, invalid);
            }
        }
    }//GEN-LAST:event_bahan_editMouseClicked

    private void produk_editMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_produk_editMouseClicked
        if (produk_id.getText().isEmpty() || produk_nama.getText().isEmpty() || produk_harga.getText().isEmpty()
                || produk_kategori.getSelectedItem().equals("Pilih") || produk_jenis.getSelectedItem().equals("Pilih")
                || produk_satuan.getSelectedItem().equals("pilih")) {
            JOptionPane.showMessageDialog(null, "Data Belum Terpenuhi!", "Peringatan!", HEIGHT, warning);
        } else {
            try {
                String sql = "update produk set nama=?,kategori=?,jenis=?,satuan=?, harga=? where id='" + produk_id.getText() + "'";
                PreparedStatement stat = Config.config.getConnection().prepareStatement(sql);
                stat.setString(1, produk_nama.getText());
                stat.setString(2, produk_kategori.getSelectedItem().toString());
                stat.setString(3, produk_jenis.getSelectedItem().toString());
                stat.setString(4, produk_satuan.getSelectedItem().toString());
                stat.setString(5, produk_harga.getText());

                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil Di Ubah!", "Berhasil!", HEIGHT, sucess);
                DisableField();
                RunTable();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Terjadi Kesalahan!", "Perhatian!", HEIGHT, invalid);
            }
        }
    }//GEN-LAST:event_produk_editMouseClicked

    private void req_bahan_tambahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_req_bahan_tambahMouseClicked
        if (req_bahan_id.getText().isEmpty() || req_bahan_idsupp.getText().isEmpty() || req_bahan_idbahan.getText().isEmpty()
                || req_bahan_namabahan.getText().isEmpty() || req_bahan_qtybahan.getText().isEmpty() || req_bahan_satuan.getSelectedItem().equals("Pilih")) {
            JOptionPane.showMessageDialog(null, "Data Belum Terpenuhi!", "Alert Message", HEIGHT, warning);
        } else {
            DefaultTableModel model = (DefaultTableModel) req_bahan_tabel.getModel();
            model.addRow(new Object[]{
                req_bahan_id.getText(), req_bahan_tgl.getText(), req_bahan_idsupp.getText(),
                req_bahan_idbahan.getText(), req_bahan_namabahan.getText(), req_bahan_qtybahan.getText(),
                req_bahan_satuan.getSelectedItem().toString()});
            req_bahan_tabel.setModel(model);
            req_bahan_simpan.setVisible(true);
            req_bahan_hapus.setVisible(true);
            req_bahan_batal.setVisible(true);
            req_bahan_carisup.setVisible(false);
            req_bahan_idbahan.setText("");
            req_bahan_namabahan.setText("");
            req_bahan_qtybahan.setText("");
            req_bahan_satuan.setSelectedItem("Pilih");
            int row = model.getRowCount();
            txt_jumlah_item.setText("" + row);
            //menjumlahkan isi colom ke 4 dalam tabel
            int total = 0;
            for (int i = 0; i < req_bahan_tabel.getRowCount(); i++) {
                int amount = Integer.parseInt((String) req_bahan_tabel.getValueAt(i, 5));
                total += amount;
            }
            txt_total_ite.setText("" + total);
        }

    }//GEN-LAST:event_req_bahan_tambahMouseClicked

    private void req_bahan_carisupMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_req_bahan_carisupMouseClicked
        ShowSupplier ssp = new ShowSupplier();
        ssp.hmSupplier = this;
        ssp.setVisible(true);
        ssp.setResizable(false);
    }//GEN-LAST:event_req_bahan_carisupMouseClicked

    private void req_bahan_caribahanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_req_bahan_caribahanMouseClicked
        ShowBahan sb = new ShowBahan();
        sb.hmBahan = this;
        sb.setVisible(true);
        sb.setResizable(false);
    }//GEN-LAST:event_req_bahan_caribahanMouseClicked

    private void req_bahan_hapusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_req_bahan_hapusMouseClicked
        DefaultTableModel model = (DefaultTableModel) req_bahan_tabel.getModel();
        int row = req_bahan_tabel.getSelectedRow();
        if (row >= 0) {
            int ok = JOptionPane.showConfirmDialog(null, "Yakin Mau Hapus?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (ok == 0) {
                model.removeRow(row);

                int rows = model.getRowCount();
                txt_jumlah_item.setText("" + rows);

                int total = 0;
                for (int i = 0; i < req_bahan_tabel.getRowCount(); i++) {
                    int amount = Integer.parseInt((String) req_bahan_tabel.getValueAt(i, 5));
                    txt_total_ite.setText("" + amount);
                }
            }
        }
    }//GEN-LAST:event_req_bahan_hapusMouseClicked

    private void req_produk_cariMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_req_produk_cariMouseClicked
        ShowProduk sp = new ShowProduk();
        sp.hmProduk = this;
        sp.setVisible(true);
        sp.setResizable(false);
    }//GEN-LAST:event_req_produk_cariMouseClicked

    private void req_produk_batalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_req_produk_batalMouseClicked
        ClearALLReq();
    }//GEN-LAST:event_req_produk_batalMouseClicked

    private void req_produk_simpanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_req_produk_simpanMouseClicked
        if (req_produk_id.getText().isEmpty() || req_produk_idproduk.getText().isEmpty() || req_produk_nama.getText().isEmpty()
                || req_produk_jenis.getText().isEmpty() || req_produk_jumlah.getText().isEmpty() || req_produk_satuan.getSelectedItem().equals("Pilih")) {
            JOptionPane.showMessageDialog(null, "Data Belum Terpenuhi..", "Peringatan!", HEIGHT);
        } else {
            OrderProduk();
        }
    }//GEN-LAST:event_req_produk_simpanMouseClicked

    private void req_bahan_lihatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_req_bahan_lihatMouseClicked

    }//GEN-LAST:event_req_bahan_lihatMouseClicked

    private void req_bahan_simpanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_req_bahan_simpanMouseClicked
        if (req_bahan_id.getText().isEmpty() || req_bahan_idsupp.getText().isEmpty() || req_bahan_namasup.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Data Belum Terpenuhi..", "Peringatan!", HEIGHT);
        } else {
            OrderBahan();
        }
    }//GEN-LAST:event_req_bahan_simpanMouseClicked

    private void req_kirim_btTambahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_req_kirim_btTambahMouseClicked
//        String qtyDb = "";
        if (req_kirim_id.getText().isEmpty() || req_kirim_custid.getText().isEmpty() || req_kirim_nama.getText().isEmpty()
                || req_kirim_telp.getText().isEmpty() || req_kirim_alamat.getText().isEmpty() || req_kirim_produkid.getText().isEmpty()
                || req_kirim_produknama.getText().isEmpty() || req_kirim_harga.getText().isEmpty() || req_kirim_qty.getText().isEmpty()
                || req_kirim_subtot.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Data Belum Terpenuhi!", "Alert Message", HEIGHT, warning);
        } else {

            DefaultTableModel dmodel = (DefaultTableModel) req_kirim_tabel.getModel();
            dmodel.addRow(new Object[]{
                req_kirim_id.getText(), req_kirim_produkid.getText(), req_kirim_produknama.getText(),
                req_kirim_harga.getText(), req_kirim_qty.getText(), req_kirim_subtot.getText()});
            req_kirim_tabel.setModel(dmodel);
            req_kirim_btSimpan.setVisible(true);
            req_kirim_btBatal.setVisible(true);
            req_kirim_btHapus.setVisible(true);
            req_kirim_btCust.setVisible(false);
            req_kirim_produkid.setText("");
            req_kirim_btCust.setText("");
            req_kirim_produknama.setText("");
            req_kirim_harga.setText("");
            req_kirim_qty.setText("");
            req_kirim_subtot.setText("");
            int row = dmodel.getRowCount();
            txt_kirim_item.setText("" + row);
            //menjumlahkan isi colom ke 4 dalam tabel
            int total = 0;
            for (int i = 0; i < req_kirim_tabel.getRowCount(); i++) {
                int amount = Integer.parseInt((String) req_kirim_tabel.getValueAt(i, 4));
                total += amount;
            }
            txt_kirim_total.setText("" + total);
            int hartot = 0;
            for (int i = 0; i < req_kirim_tabel.getRowCount(); i++) {
                int amount2 = Integer.parseInt((String) req_kirim_tabel.getValueAt(i, 5));
                hartot += amount2;
            }
            txt_kirim_totharga.setText("" + hartot);
        }

    }//GEN-LAST:event_req_kirim_btTambahMouseClicked

    private void req_kirim_btCustActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_req_kirim_btCustActionPerformed
        ShowCustomer sc = new ShowCustomer();
        sc.hmCustomer = this;
        sc.setVisible(true);
        sc.setResizable(false);
    }//GEN-LAST:event_req_kirim_btCustActionPerformed

    private void req_kirim_btProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_req_kirim_btProdukActionPerformed
        ShowStokProduk stp = new ShowStokProduk();
        stp.hmStok = this;
        stp.setVisible(true);
        stp.setResizable(false);
    }//GEN-LAST:event_req_kirim_btProdukActionPerformed

    private void req_kirim_qtyKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_req_kirim_qtyKeyTyped

    }//GEN-LAST:event_req_kirim_qtyKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String qtyDb = "";
        int price = Integer.parseInt(req_kirim_harga.getText());
        int qty = Integer.parseInt(req_kirim_qty.getText());
        int total = price * qty;
        try {
            String cekStock = "select qty from produk where id='" + req_kirim_produkid.getText() + "'";
            Statement stat = Config.config.getConnection().createStatement();
            ResultSet hasil = stat.executeQuery(cekStock);
            while (hasil.next()) {
                qtyDb = hasil.getString("qty");
            }
            int qtyDbint = Integer.parseInt(qtyDb);
            int txtQty = Integer.parseInt(req_kirim_qty.getText());
            if (qtyDbint < txtQty) {
                JOptionPane.showMessageDialog(null, "Stok Kurang Dari Permintaan!", "Alert Message", HEIGHT, warning);
                req_kirim_qty.requestFocus();
            } else {
                req_kirim_subtot.setText(String.valueOf(total));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Bermasalah: "+e.getMessage(), "Alert Message", HEIGHT, warning);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void req_produk_jumlahKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_req_produk_jumlahKeyTyped
        FilterAngka(evt);
    }//GEN-LAST:event_req_produk_jumlahKeyTyped

    private void req_kirim_btSimpanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_req_kirim_btSimpanMouseClicked
        
    }//GEN-LAST:event_req_kirim_btSimpanMouseClicked

    private void req_kirim_btBatalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_req_kirim_btBatalMouseClicked
        
    }//GEN-LAST:event_req_kirim_btBatalMouseClicked


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
            java.util.logging.Logger.getLogger(Home.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Body;
    private javax.swing.JPanel Header;
    private javax.swing.JPanel Main_Menu;
    private javax.swing.JPanel bahan_delete;
    private javax.swing.JPanel bahan_edit;
    private javax.swing.JTextField bahan_id;
    private javax.swing.JComboBox<String> bahan_jenis;
    private javax.swing.JTextField bahan_nama;
    private javax.swing.JComboBox<String> bahan_satuan;
    private javax.swing.JPanel bahan_simpan;
    private javax.swing.JTable bahan_tabel;
    private javax.swing.JPanel bahan_tambah;
    private javax.swing.JPanel body_home;
    private javax.swing.JPanel body_laporan;
    private javax.swing.JPanel body_master_bahan;
    private javax.swing.JPanel body_master_customer;
    private javax.swing.JPanel body_master_produk;
    private javax.swing.JPanel body_master_supplier;
    private javax.swing.JPanel body_req_bahan;
    private javax.swing.JPanel body_req_kirim;
    private javax.swing.JPanel body_req_produk;
    private javax.swing.JPanel body_stok_bahan;
    private javax.swing.JPanel body_stok_produk;
    private javax.swing.JTextArea customer_alamat;
    private javax.swing.JPanel customer_edit;
    private javax.swing.JTextField customer_email;
    private javax.swing.JPanel customer_hapus;
    private javax.swing.JTextField customer_id;
    private javax.swing.JComboBox<String> customer_jenis;
    private javax.swing.JTextField customer_nama;
    private javax.swing.JPanel customer_simpan;
    private javax.swing.JComboBox<String> customer_status;
    private javax.swing.JTable customer_tabel;
    private javax.swing.JPanel customer_tambah;
    private javax.swing.JTextField customer_telp;
    private javax.swing.JLabel customer_txt;
    private javax.swing.JLabel img_home;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
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
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
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
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JPanel laporan;
    private javax.swing.JLabel lbl_nama;
    private javax.swing.JPanel master_bahan;
    private javax.swing.JPanel master_customer;
    private javax.swing.JLabel master_down;
    private javax.swing.JPanel master_produk;
    private javax.swing.JPanel master_supplier;
    private javax.swing.JLabel master_up;
    private javax.swing.JPanel nul_master;
    private javax.swing.JPanel null_request;
    private javax.swing.JPanel null_stock;
    private javax.swing.JPanel produk_delete;
    private javax.swing.JPanel produk_edit;
    private javax.swing.JTextField produk_harga;
    private javax.swing.JTextField produk_id;
    private javax.swing.JComboBox<String> produk_jenis;
    private javax.swing.JComboBox<String> produk_kategori;
    private javax.swing.JTextField produk_nama;
    private javax.swing.JComboBox<String> produk_satuan;
    private javax.swing.JPanel produk_simpan;
    public javax.swing.JTable produk_tabel;
    private javax.swing.JPanel produk_tambah;
    private javax.swing.JPanel req_bahan_batal;
    private javax.swing.JPanel req_bahan_caribahan;
    private javax.swing.JPanel req_bahan_carisup;
    private javax.swing.JPanel req_bahan_hapus;
    private javax.swing.JTextField req_bahan_id;
    private javax.swing.JTextField req_bahan_idbahan;
    private javax.swing.JTextField req_bahan_idsupp;
    private javax.swing.JPanel req_bahan_lihat;
    private javax.swing.JTextField req_bahan_namabahan;
    private javax.swing.JTextField req_bahan_namasup;
    private javax.swing.JTextField req_bahan_qtybahan;
    private javax.swing.JComboBox<String> req_bahan_satuan;
    private javax.swing.JPanel req_bahan_simpan;
    private javax.swing.JTable req_bahan_tabel;
    private javax.swing.JPanel req_bahan_tambah;
    private javax.swing.JTextField req_bahan_telp;
    private javax.swing.JTextField req_bahan_tgl;
    private javax.swing.JLabel req_down;
    public javax.swing.JTextArea req_kirim_alamat;
    public javax.swing.JPanel req_kirim_btBatal;
    public javax.swing.JButton req_kirim_btCust;
    public javax.swing.JPanel req_kirim_btHapus;
    public javax.swing.JButton req_kirim_btProduk;
    public javax.swing.JPanel req_kirim_btSimpan;
    private javax.swing.JPanel req_kirim_btTambah;
    public javax.swing.JTextField req_kirim_custid;
    public javax.swing.JTextField req_kirim_harga;
    public javax.swing.JTextField req_kirim_id;
    public javax.swing.JTextField req_kirim_nama;
    public javax.swing.JTextField req_kirim_produkid;
    public javax.swing.JTextField req_kirim_produknama;
    public javax.swing.JTextField req_kirim_qty;
    public javax.swing.JTextField req_kirim_subtot;
    private javax.swing.JTable req_kirim_tabel;
    public javax.swing.JTextField req_kirim_telp;
    private javax.swing.JPanel req_produk_batal;
    private javax.swing.JPanel req_produk_cari;
    private javax.swing.JTextField req_produk_id;
    private javax.swing.JTextField req_produk_idproduk;
    private javax.swing.JTextField req_produk_jenis;
    private javax.swing.JTextField req_produk_jumlah;
    private javax.swing.JTextField req_produk_nama;
    private javax.swing.JComboBox<String> req_produk_satuan;
    private javax.swing.JPanel req_produk_simpan;
    private javax.swing.JTable req_produk_tabel;
    private javax.swing.JTextField req_produk_tgl;
    private com.toedter.calendar.JDateChooser req_produk_tgl_target;
    private javax.swing.JLabel req_up;
    private javax.swing.JPanel request_bahan;
    private javax.swing.JPanel request_kirim;
    private javax.swing.JPanel request_produk;
    private javax.swing.JPanel stock_bahan_baku;
    private javax.swing.JLabel stock_down;
    private javax.swing.JPanel stock_produk_jadi;
    private javax.swing.JLabel stock_up;
    private javax.swing.JPanel sub_master;
    private javax.swing.JPanel sub_request;
    private javax.swing.JPanel sub_stock;
    private javax.swing.JTextArea supplier_alamat;
    private javax.swing.JPanel supplier_delete;
    private javax.swing.JPanel supplier_edit;
    private javax.swing.JTextField supplier_email;
    private javax.swing.JTextField supplier_id;
    private javax.swing.JTextField supplier_jenis;
    private javax.swing.JTextField supplier_nama;
    private javax.swing.JPanel supplier_simpan;
    private javax.swing.JComboBox<String> supplier_status;
    private javax.swing.JTable supplier_tabel;
    private javax.swing.JPanel supplier_tambah;
    private javax.swing.JTextField supplier_telp;
    private javax.swing.JLabel supplier_txt;
    private javax.swing.JLabel title_page;
    private javax.swing.JLabel txt_jumlah_item;
    public javax.swing.JLabel txt_kirim_item;
    public javax.swing.JLabel txt_kirim_total;
    public javax.swing.JLabel txt_kirim_totharga;
    private javax.swing.JLabel txt_total_ite;
    // End of variables declaration//GEN-END:variables
}
