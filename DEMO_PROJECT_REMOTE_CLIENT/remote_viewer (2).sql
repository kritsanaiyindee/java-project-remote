-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 23, 2021 at 06:20 AM
-- Server version: 10.4.18-MariaDB
-- PHP Version: 7.4.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `remote_viewer`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_user`
--

CREATE TABLE `tbl_user` (
  `c_id` int(11) NOT NULL,
  `c_username` varchar(255) NOT NULL,
  `c_password` varchar(255) NOT NULL,
  `c_email` varchar(255) NOT NULL,
  `c_pass` varchar(32) NOT NULL,
  `c_ip` varchar(32) NOT NULL,
  `c_status` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tbl_user`
--

INSERT INTO `tbl_user` (`c_id`, `c_username`, `c_password`, `c_email`, `c_pass`, `c_ip`, `c_status`) VALUES
(100001, 'Kritsanai', 'Krit1234', 'kritsanai@harmonious.co.th', '', '127.0.0.1', 0),
(100002, 'Kritsanai2', 'Krit1234', 'kritsanai@harmonious.co.th', '', '127.0.0.1', 0),
(100003, 'krit1', '1234', '', '', '', 0),
(100004, '3123', '3111', '23123', '', '', 0),
(100005, '3123', '11', '313123', '', '', 0),
(100006, '3123', '33', '123', '', '', 0),
(100007, '2', '22', '2', '', '', 0),
(100008, '', '', '', '', '', 0),
(100009, '', '', '', '', '', 0),
(100010, '', '', '', '', '', 0),
(100011, '', '', '', '', '', 0),
(100012, '', '', '', '', '', 0),
(100013, '', '', '', '', '', 0),
(100014, '', '', '', '', '', 0),
(100015, '', '', '', '', '', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_user`
--
ALTER TABLE `tbl_user`
  ADD PRIMARY KEY (`c_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_user`
--
ALTER TABLE `tbl_user`
  MODIFY `c_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=100016;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
