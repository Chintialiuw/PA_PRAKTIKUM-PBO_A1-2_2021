-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 27, 2023 at 05:56 AM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `reservasi_hotel`
--

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `username` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `tglLahir` date NOT NULL,
  `noTelp` varchar(13) NOT NULL,
  `alamat` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`username`, `password`, `tglLahir`, `noTelp`, `alamat`) VALUES
('azhar11', 'azhar11', '1997-02-21', '081267198221', 'Jl. APT Pranoto'),
('aziizah', '004', '2002-11-23', '081250538539', 'Jl. H. Anang Hasyim'),
('budi99', 'budi99', '1999-10-10', '085267118920', 'Perum Bukit Pinang Bahari'),
('ghanaAsr', 'ghanaAsr', '1993-11-02', '085211890082', 'Jl. KS Tubun Dalam'),
('juliana', 'juliana', '1999-08-08', '081267892298', 'Jl. Perjuangan 7'),
('karmila124', '124', '1989-12-01', '082122456290', 'Jl. Antasari');

-- --------------------------------------------------------

--
-- Table structure for table `pesanan`
--

CREATE TABLE `pesanan` (
  `id` varchar(10) NOT NULL,
  `tglPesan` date NOT NULL,
  `tglCheckIn` date NOT NULL,
  `tglCheckOut` date NOT NULL,
  `idRuangan` int(11) NOT NULL,
  `namaTamu` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `status` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pesanan`
--

INSERT INTO `pesanan` (`id`, `tglPesan`, `tglCheckIn`, `tglCheckOut`, `idRuangan`, `namaTamu`, `username`, `status`) VALUES
('au2504231', '2023-04-25', '2023-04-28', '2023-05-01', 9, 'Juliana Sari', 'juliana', 'Definite'),
('km0804231', '2023-04-08', '2023-04-17', '2023-04-20', 2, 'Budi Abdullah', 'budi99', 'Check Out'),
('km0804232', '2023-04-08', '2023-04-19', '2023-04-21', 6, 'Juliana Sari', 'juliana', 'Check Out'),
('km1504232', '2023-04-15', '2023-04-22', '2023-04-24', 2, 'Karmila Permatasari ', 'karmila124', 'Check Out'),
('km2004231', '2023-04-20', '2023-04-25', '2023-04-30', 1, 'Ghana Asari', 'ghanaAsr', 'In-House'),
('km2004232', '2023-04-20', '2023-04-29', '2023-05-02', 6, 'Aziizah Oki', 'aziizah', 'Definite'),
('km2604232', '2023-04-26', '2023-05-04', '2023-05-07', 1, 'Muhammad Azhar', 'azhar11', 'Definite'),
('km2704232', '2023-04-27', '2023-12-11', '2023-12-12', 1, 'Aziizah', 'aziizah', 'Definite'),
('rp2204231', '2023-04-22', '2023-04-29', '2023-05-30', 8, 'Santi Ahmad', 'juliana', 'Definite'),
('rp2604231', '2023-04-26', '2023-05-04', '2023-05-05', 7, 'Aziizah Oki S.', 'aziizah', 'Definite'),
('rp2704232', '2023-04-27', '2023-04-28', '2023-05-30', 3, 'Aziizah Oki', 'aziizah', 'Definite'),
('rs2204232', '2023-04-22', '2023-04-29', '2023-05-30', 10, 'Hisyam Anhar', 'budi99', 'Definite'),
('rs2803233', '2023-03-28', '2023-04-02', '2023-04-03', 10, 'Budi Abdullah', 'budi99', 'Check Out');

-- --------------------------------------------------------

--
-- Table structure for table `ruangan`
--

CREATE TABLE `ruangan` (
  `id` int(11) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `harga` int(11) NOT NULL,
  `kapasitas` int(11) NOT NULL,
  `fasilitas` varchar(100) NOT NULL,
  `lantai` int(11) NOT NULL,
  `jenis` varchar(50) NOT NULL,
  `status` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `ruangan`
--

INSERT INTO `ruangan` (`id`, `nama`, `harga`, `kapasitas`, `fasilitas`, `lantai`, `jenis`, `status`) VALUES
(1, 'Superior Twin Room Only', 499999, 2, 'Free WiFi, Non smoking', 2, 'Kamar', 'Penuh'),
(2, 'Deluxe Double Room Only', 599999, 2, 'Free WiFi, Non smoking, Free Breakfast', 3, 'Kamar', 'Tersedia'),
(3, 'Office 304', 300000, 4, 'Free WiFi, Smart TV', 3, 'Ruang Rapat', 'Tersedia'),
(4, 'Deluxe Room', 698000, 3, 'Free WiFi, Free Breakfast', 4, 'Kamar', 'Tersedia'),
(5, 'Adenium Room', 650000, 25, 'Free WiFi, LCD, Sound System', 3, 'Ruang Rapat', 'Tersedia'),
(6, 'Grania Executive', 527000, 2, 'Free Breakfast, Free WiFi', 4, 'Kamar', 'Tersedia'),
(7, 'Mahogany Room', 959199, 50, 'Free WiFi, Sound System, Projector', 3, 'Ruang Rapat', 'Tersedia'),
(8, 'Rafflessia Room', 788990, 35, 'Free WiFi, Projector, White Board', 4, 'Ruang Rapat', 'Tersedia'),
(9, 'Aquamarine Ballroom', 14580777, 200, 'Free WiFi, Microphone, Mineral Water', 5, 'Aula', 'Tersedia'),
(10, 'BnB Restaurant', 899000, 70, 'Free WiFi, Screen', 3, 'Restaurant', 'Tersedia');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `pesanan`
--
ALTER TABLE `pesanan`
  ADD PRIMARY KEY (`id`),
  ADD KEY `pesan_ruangan_fk` (`idRuangan`),
  ADD KEY `pesan_cust_fk` (`username`);

--
-- Indexes for table `ruangan`
--
ALTER TABLE `ruangan`
  ADD PRIMARY KEY (`id`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `pesanan`
--
ALTER TABLE `pesanan`
  ADD CONSTRAINT `pesan_cust_fk` FOREIGN KEY (`username`) REFERENCES `customer` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `pesan_ruangan_fk` FOREIGN KEY (`idRuangan`) REFERENCES `ruangan` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
