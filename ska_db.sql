-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 21, 2020 at 08:33 AM
-- Server version: 10.1.38-MariaDB
-- PHP Version: 7.2.17

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ska_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `bahan`
--

CREATE TABLE `bahan` (
  `id` varchar(30) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `jenis` varchar(30) NOT NULL,
  `satuan` varchar(20) NOT NULL,
  `stok` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bahan`
--

INSERT INTO `bahan` (`id`, `nama`, `jenis`, `satuan`, `stok`) VALUES
('BID00000001', 'Minyak', 'jenis3', 'Satuan2', 325),
('BID00000002', 'sabun', 'jenis2', 'Satuan2', 150),
('BID00000003', 'telor', 'jenis3', 'Satuan1', 100);

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `id` varchar(30) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `jenis` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `telp` varchar(30) NOT NULL,
  `alamat` varchar(50) NOT NULL,
  `status` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`id`, `nama`, `jenis`, `email`, `telp`, `alamat`, `status`) VALUES
('IDC00000002', 'Marlinah', 'Dropshipper', 'marlinah@gmail.com', '0192382103182', 'Dijual', 'Non-Aktiv'),
('IDC00000003', 'asdasdasdas', 'Unit', 'asdasdasdas', 'asdas', 'asdasdas', 'Aktiv'),
('IDC00000004', 'dasdnasdkas', 'Unit', 'asdasdsak', 'asdas', 'asdasd asdas', 'Aktiv'),
('IDC00000005', 'susi', 'Dropshipper', 'susi@gmail.com', '083909042010', 'jl.cipinang raya', 'Aktiv');

-- --------------------------------------------------------

--
-- Table structure for table `item_kirim_barang`
--

CREATE TABLE `item_kirim_barang` (
  `id_kirim_barang` varchar(20) NOT NULL,
  `id_produk` varchar(20) NOT NULL,
  `nama_produk` varchar(30) NOT NULL,
  `harga` varchar(10) NOT NULL,
  `qty` varchar(20) NOT NULL,
  `total` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `item_kirim_barang`
--

INSERT INTO `item_kirim_barang` (`id_kirim_barang`, `id_produk`, `nama_produk`, `harga`, `qty`, `total`) VALUES
('INV00000001', 'PID00000006', 'Kopi', '500000', '100', '50000000'),
('INV00000001', 'PID00000006', 'Kopi', '500000', '50', '25000000'),
('INV00000002', 'PID00000006', 'Kopi', '500000', '100', '50000000'),
('INV00000002', 'PID00000006', 'Kopi', '500000', '100', '50000000'),
('INV00000004', 'PID00000005', 'Sedotan', '100000', '500', '50000000'),
('INV00000005', 'PID00000005', 'Sedotan', '100000', '500', '50000000'),
('INV00000006', 'PID00000005', 'Sedotan', '100000', '200', '20000000'),
('INV00000007', 'PID00000005', 'Sedotan', '100000', '100', '10000000');

-- --------------------------------------------------------

--
-- Table structure for table `item_po_bahan`
--

CREATE TABLE `item_po_bahan` (
  `id_order_bahan` varchar(20) NOT NULL,
  `id_supplier` varchar(20) NOT NULL,
  `nama_supplier` varchar(30) NOT NULL,
  `id_bahan` varchar(20) NOT NULL,
  `nama_bahan` varchar(30) NOT NULL,
  `satuan` varchar(10) NOT NULL,
  `qty_item` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `item_po_bahan`
--

INSERT INTO `item_po_bahan` (`id_order_bahan`, `id_supplier`, `nama_supplier`, `id_bahan`, `nama_bahan`, `satuan`, `qty_item`) VALUES
('IDO00000001', 'IDS00000001', 'Septian', 'BID00000003', 'telor', '200', 'Item 2');

-- --------------------------------------------------------

--
-- Table structure for table `keluar_bahan`
--

CREATE TABLE `keluar_bahan` (
  `no` varchar(20) NOT NULL,
  `nama` varchar(30) NOT NULL,
  `tanggal` varchar(40) NOT NULL,
  `jumlah` varchar(10) NOT NULL,
  `total` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `keluar_bahan`
--

INSERT INTO `keluar_bahan` (`no`, `nama`, `tanggal`, `jumlah`, `total`) VALUES
('NOD00000001', 'septian', '21 Agu 20', '3', '176'),
('NOD00000002', 'asoy', '08 Agu 20', '2', '280'),
('NOD00000003', 'asep', '27 Agu 20', '2', '175');

-- --------------------------------------------------------

--
-- Table structure for table `keluar_bahan_item`
--

CREATE TABLE `keluar_bahan_item` (
  `no_faktur` varchar(30) NOT NULL,
  `id_bahan` varchar(30) NOT NULL,
  `nama` varchar(30) NOT NULL,
  `qty` int(11) NOT NULL,
  `satuan` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `keluar_bahan_item`
--

INSERT INTO `keluar_bahan_item` (`no_faktur`, `id_bahan`, `nama`, `qty`, `satuan`) VALUES
('NOD00000002', 'BID00000003', 'telor', 200, 'Satuan1'),
('NOD00000002', 'BID00000003', 'telor', 80, 'Satuan1'),
('NOD00000003', 'BID00000001', 'Minyak', 75, 'Satuan2'),
('NOD00000003', 'BID00000003', 'telor', 100, 'Satuan1');

--
-- Triggers `keluar_bahan_item`
--
DELIMITER $$
CREATE TRIGGER `keluar_bahan` AFTER INSERT ON `keluar_bahan_item` FOR EACH ROW BEGIN
	UPDATE bahan SET stok = stok - NEW.qty
    WHERE id = NEW.id_bahan;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `keluar_produk`
--

CREATE TABLE `keluar_produk` (
  `inv` varchar(30) NOT NULL,
  `id_produk` varchar(30) NOT NULL,
  `nama` varchar(30) NOT NULL,
  `qty` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `keluar_produk`
--

INSERT INTO `keluar_produk` (`inv`, `id_produk`, `nama`, `qty`) VALUES
('INV00000001', 'PID00000006', 'Kopi', 100),
('INV00000001', 'PID00000006', 'Kopi', 50),
('INV00000004', 'PID00000005', 'Sedotan', 500),
('INV00000007', 'PID00000005', 'Sedotan', 100);

--
-- Triggers `keluar_produk`
--
DELIMITER $$
CREATE TRIGGER `keluar_barang` AFTER INSERT ON `keluar_produk` FOR EACH ROW BEGIN
	UPDATE produk SET qty = qty - NEW.qty
    WHERE id = NEW.id_produk;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `kirim_barang`
--

CREATE TABLE `kirim_barang` (
  `id` varchar(20) NOT NULL,
  `id_customer` varchar(30) NOT NULL,
  `nama_customer` varchar(50) NOT NULL,
  `telp` varchar(15) NOT NULL,
  `alamat` varchar(50) NOT NULL,
  `qty_item` varchar(10) NOT NULL,
  `jumlah` varchar(10) NOT NULL,
  `harga` varchar(10) NOT NULL,
  `tanggal` varchar(30) NOT NULL,
  `status` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `kirim_barang`
--

INSERT INTO `kirim_barang` (`id`, `id_customer`, `nama_customer`, `telp`, `alamat`, `qty_item`, `jumlah`, `harga`, `tanggal`, `status`) VALUES
('INV00000001', 'IDC00000004', 'dasdnasdkas', 'asdas', 'asdasd asdas', '2', '150', '75000000', 'Jumat, 21 Agustus 2020', 'Di Kirimkan'),
('INV00000002', 'IDC00000005', 'susi', '083909042010', 'jl.cipinang raya', '1', '100', '50000000', 'Kamis, 20 Agustus 2020', 'Request'),
('INV00000003', 'IDC00000005', 'susi', '083909042010', 'jl.cipinang raya', '1', '100', '50000000', 'Kamis, 20 Agustus 2020', 'Request'),
('INV00000004', 'IDC00000004', 'dasdnasdkas', 'asdas', 'asdasd asdas', '1', '500', '50000000', 'Jumat, 14 Agustus 2020', 'Di Kirimkan'),
('INV00000005', 'IDC00000005', 'susi', '083909042010', 'jl.cipinang raya', '1', '500', '50000000', '', 'Request'),
('INV00000006', 'IDC00000005', 'susi', '083909042010', 'jl.cipinang raya', '1', '200', '20000000', 'Selasa, 11 Agustus 2020', 'Request'),
('INV00000007', 'IDC00000003', 'asdasdasdas', 'asdas', 'asdasdas', '1', '100', '10000000', 'Rabu, 19 Agustus 2020', 'Di Kirimkan');

-- --------------------------------------------------------

--
-- Table structure for table `masuk_bahan`
--

CREATE TABLE `masuk_bahan` (
  `id_bahan` varchar(30) NOT NULL,
  `nama` varchar(40) NOT NULL,
  `qty` int(11) NOT NULL,
  `satuan` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `masuk_bahan`
--

INSERT INTO `masuk_bahan` (`id_bahan`, `nama`, `qty`, `satuan`) VALUES
('BID00000003', 'telor', 200, 'Item 2');

--
-- Triggers `masuk_bahan`
--
DELIMITER $$
CREATE TRIGGER `masuk` AFTER INSERT ON `masuk_bahan` FOR EACH ROW BEGIN
	UPDATE bahan SET stok = stok + NEW.qty
    WHERE id = NEW.id_bahan;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `order_bahan`
--

CREATE TABLE `order_bahan` (
  `id` varchar(30) NOT NULL,
  `id_supplier` varchar(30) NOT NULL,
  `nama_supplier` varchar(50) NOT NULL,
  `jenis_item` varchar(10) NOT NULL,
  `jumlah_item` varchar(10) NOT NULL,
  `tanggal` varchar(30) NOT NULL,
  `tanggal_terima` varchar(20) NOT NULL,
  `status` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `order_bahan`
--

INSERT INTO `order_bahan` (`id`, `id_supplier`, `nama_supplier`, `jenis_item`, `jumlah_item`, `tanggal`, `tanggal_terima`, `status`) VALUES
('IDO00000001', 'IDS00000001', 'Septian', '1', '200', 'Rabu, 19 Agustus 2020', '', 'Sesuai');

-- --------------------------------------------------------

--
-- Table structure for table `order_produk`
--

CREATE TABLE `order_produk` (
  `id` varchar(30) NOT NULL,
  `id_produk` varchar(30) NOT NULL,
  `nama_produk` varchar(50) NOT NULL,
  `jenis_produk` varchar(50) NOT NULL,
  `tgl_order` varchar(30) NOT NULL,
  `tgl_target` varchar(30) NOT NULL,
  `tgl_selesai` varchar(30) NOT NULL,
  `qty` varchar(10) NOT NULL,
  `status` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `order_produk`
--

INSERT INTO `order_produk` (`id`, `id_produk`, `nama_produk`, `jenis_produk`, `tgl_order`, `tgl_target`, `tgl_selesai`, `qty`, `status`) VALUES
('RID00000001', 'PID00000005', 'Sedotan', 'jenis2', 'Rabu, 19 Agustus 2020', 'Rabu, 26 Agustus 02020', '', '1000', 'Selesai');

-- --------------------------------------------------------

--
-- Table structure for table `produk`
--

CREATE TABLE `produk` (
  `id` varchar(30) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `kategori` varchar(30) NOT NULL,
  `jenis` varchar(20) NOT NULL,
  `satuan` varchar(20) NOT NULL,
  `qty` varchar(10) NOT NULL,
  `harga` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `produk`
--

INSERT INTO `produk` (`id`, `nama`, `kategori`, `jenis`, `satuan`, `qty`, `harga`) VALUES
('PID00000001', 'sofel', 'Item 2', 'Item 2', 'Item 2', '0', '500000'),
('PID00000002', 'Obat Prusak Wajah', 'kategori1', 'jenis1', 'satuan1', '0', '500000'),
('PID00000003', 'Pelembab Kepala', 'kategori2', 'jenis1', 'satuan1', '0', '200000'),
('PID00000004', 'Lulur Kepala', 'kategori2', 'jenis1', 'satuan1', '0', '100000'),
('PID00000005', 'Sedotan', 'kategori2', 'jenis2', 'satuan2', '1400', '100000'),
('PID00000006', 'Kopi', 'kategori2', 'jenis2', 'satuan2', '250', '500000');

-- --------------------------------------------------------

--
-- Table structure for table `produk_jadi`
--

CREATE TABLE `produk_jadi` (
  `id` int(11) NOT NULL,
  `order_id` varchar(30) NOT NULL,
  `id_produk` varchar(30) NOT NULL,
  `qty` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `produk_jadi`
--

INSERT INTO `produk_jadi` (`id`, `order_id`, `id_produk`, `qty`) VALUES
(1, 'RID00000001', 'PID00000005', 1000);

--
-- Triggers `produk_jadi`
--
DELIMITER $$
CREATE TRIGGER `produk_masuk` AFTER INSERT ON `produk_jadi` FOR EACH ROW BEGIN
	UPDATE produk SET qty = qty + NEW.qty
    WHERE id = NEW.id_produk;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `supplier`
--

CREATE TABLE `supplier` (
  `id` varchar(30) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `telp` varchar(15) NOT NULL,
  `alamat` varchar(50) NOT NULL,
  `jenis` varchar(30) NOT NULL,
  `status` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `supplier`
--

INSERT INTO `supplier` (`id`, `nama`, `email`, `telp`, `alamat`, `jenis`, `status`) VALUES
('IDS00000001', 'Septian', 'septian@gmail.com', 'Septian', 'Di Jual', 'Ga Punya', 'Aktiv'),
('IDS00000002', 'asdasda', 'assdas', 'asdas', 'asdasdasdasda', 'asdas', 'Aktiv'),
('IDS00000003', 'anjay', 'anjay@mail.com', '09909839028', 'daskdmaskdmas', 'kjandakndak', 'Aktiv');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id_user` int(11) NOT NULL,
  `nama` varchar(50) DEFAULT NULL,
  `alamat` varchar(50) DEFAULT NULL,
  `telepon` varchar(13) DEFAULT NULL,
  `status` enum('1','0') DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `akses` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id_user`, `nama`, `alamat`, `telepon`, `status`, `email`, `password`, `akses`) VALUES
(1, 'Dian', 'Jakarta Selatan', '0987654321', '1', 'dian@gmail.com', 'dian123', 'ppic'),
(2, 'Sipa', 'Jakarta Timur', '01923912031', '1', 'sipa@gmail.com', 'sipa123', 'produksi'),
(3, 'Septian', 'Tangerang', '019210019231', '1', 'septian@gmail.com', 'septian123', 'gudang');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bahan`
--
ALTER TABLE `bahan`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `keluar_bahan`
--
ALTER TABLE `keluar_bahan`
  ADD PRIMARY KEY (`no`);

--
-- Indexes for table `kirim_barang`
--
ALTER TABLE `kirim_barang`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `order_bahan`
--
ALTER TABLE `order_bahan`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `order_produk`
--
ALTER TABLE `order_produk`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `produk`
--
ALTER TABLE `produk`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `produk_jadi`
--
ALTER TABLE `produk_jadi`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `supplier`
--
ALTER TABLE `supplier`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_user`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `produk_jadi`
--
ALTER TABLE `produk_jadi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
