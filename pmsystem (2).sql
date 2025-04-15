-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 15, 2025 at 07:13 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pmsystem`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `admin_Id` int(11) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(10) NOT NULL,
  `sec_question` text NOT NULL,
  `sec_answer` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`admin_Id`, `username`, `password`, `sec_question`, `sec_answer`) VALUES
(1, 'lemma', 'lemma12', 'What is your favorite food?', 'kitfo');

-- --------------------------------------------------------

--
-- Table structure for table `guard`
--

CREATE TABLE `guard` (
  `badge_ID` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `age` int(11) NOT NULL,
  `gender` char(1) NOT NULL,
  `role` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `guard`
--

INSERT INTO `guard` (`badge_ID`, `name`, `age`, `gender`, `role`) VALUES
(1, 'sami', 20, 'M', 'guard'),
(3, 'kumsa', 46, 'M', 'guard at block 4'),
(4, 'habtish', 45, 'M', '10th manager'),
(5, 'kumsa dalasa', 45, 'M', 'chief guard'),
(7, 'Sami boss', 23, 'M', 'guard at 12 block'),
(8, 'kumsa Diribsa', 47, 'M', 'guard at block 4 zone 2'),
(9, 'lemma', 20, 'M', 'manager at zone 2');

-- --------------------------------------------------------

--
-- Table structure for table `prisoner`
--

CREATE TABLE `prisoner` (
  `p_ID` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `age` int(11) DEFAULT NULL,
  `gender` char(1) DEFAULT NULL,
  `crime` varchar(30) DEFAULT NULL,
  `Sentence_year` int(11) DEFAULT NULL,
  `start_date` varchar(50) DEFAULT NULL,
  `end_date` varchar(50) DEFAULT NULL,
  `visitor` varchar(30) DEFAULT NULL,
  `relationship` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `prisoner`
--

INSERT INTO `prisoner` (`p_ID`, `name`, `age`, `gender`, `crime`, `Sentence_year`, `start_date`, `end_date`, `visitor`, `relationship`) VALUES
(2, 'kumsa Diriba', 20, 'M', 'cheat bich', 6, '2020-02-13', '2026-02-13', 'lalisa', 'friend'),
(3, 'hawi', 45, 'M', 'demsash', 10, '1996-02-23', '2025-06-12', NULL, NULL),
(6, 'Wada mulata', 68, 'M', 'cheat', 5, '2023-03-12', '2029-03-12', 'mulata', 'father'),
(9, 'debela', 25, 'M', 'cheat', 23, '2021-12-4', '2029-12-4', 'Galata', 'Brother'),
(10, 'gemechis', 56, 'M', 'fraud', 5, '2024-04-12', '2029-04-12', 'wakuma', 'father');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`admin_Id`);

--
-- Indexes for table `guard`
--
ALTER TABLE `guard`
  ADD PRIMARY KEY (`badge_ID`);

--
-- Indexes for table `prisoner`
--
ALTER TABLE `prisoner`
  ADD PRIMARY KEY (`p_ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `admin_Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `guard`
--
ALTER TABLE `guard`
  MODIFY `badge_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `prisoner`
--
ALTER TABLE `prisoner`
  MODIFY `p_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
