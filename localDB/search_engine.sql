-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 15, 2020 at 03:22 AM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `search_engine`
--

-- --------------------------------------------------------

--
-- Table structure for table `indexer`
--

CREATE TABLE `indexer` (
  `ID` int(11) NOT NULL,
  `Word` varchar(45) NOT NULL,
  `IDF` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `linking`
--

CREATE TABLE `linking` (
  `ID` int(11) NOT NULL,
  `ID_URL` int(11) NOT NULL,
  `ID_Indexer` int(11) NOT NULL,
  `position` int(11) NOT NULL,
  `TF` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `urls`
--

CREATE TABLE `urls` (
  `ID` int(11) NOT NULL,
  `URL` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `urls`
--

INSERT INTO `urls` (`ID`, `URL`) VALUES
(1, 'http://tankoedward.wordpress.com/'),
(2, 'https://tankoedward.wordpress.com/'),
(3, 'https://tankoedward.wordpress.com/'),
(4, '#content'),
(5, 'https://tankoedward.wordpress.com/'),
(6, 'https://tankoedward.wordpress.com/about/'),
(7, 'https://tankoedward.wordpress.com/2019/06/26/generate-and-validate-jwt-token-java-example-code/'),
(8, 'https://tankoedward.wordpress.com/2019/06/26/generate-and-validate-jwt-token-java-example-code/'),
(9, 'https://tankoedward.wordpress.com/author/eddytnk/'),
(10, 'https://tankoedward.wordpress.com/2019/06/26/generate-and-validate-jwt-token-java-example-code/'),
(11, 'https://tankoedward.wordpress.com/2018/12/01/docker-commands-explained-with-cheatsheet/'),
(12, 'https://tankoedward.wordpress.com/2018/12/01/docker-commands-explained-with-cheatsheet/'),
(13, 'https://tankoedward.wordpress.com/author/eddytnk/'),
(14, 'https://tankoedward.wordpress.com/2018/12/01/docker-commands-explained-with-cheatsheet/'),
(15, 'https://tankoedward.wordpress.com/2018/12/01/spring-boot-application-deployed-using-concourse-ci/'),
(16, 'https://tankoedward.wordpress.com/2018/12/01/spring-boot-application-deployed-using-concourse-ci/'),
(17, 'https://tankoedward.wordpress.com/author/eddytnk/'),
(18, 'https://tankoedward.wordpress.com/2018/12/01/spring-boot-application-deployed-using-concourse-ci/'),
(19, 'https://tankoedward.wordpress.com/2018/11/14/how-to-create-a-strong-password/'),
(20, 'https://tankoedward.wordpress.com/2018/11/14/how-to-create-a-strong-password/'),
(21, 'https://tankoedward.wordpress.com/author/eddytnk/'),
(22, 'https://tankoedward.wordpress.com/2018/11/14/how-to-create-a-strong-password/'),
(23, 'https://tankoedward.wordpress.com/2018/03/30/jave-game-loop-function/'),
(24, 'https://tankoedward.wordpress.com/2018/03/30/jave-game-loop-function/'),
(25, 'https://tankoedward.wordpress.com/author/eddytnk/'),
(26, 'https://tankoedward.wordpress.com/2018/03/30/jave-game-loop-function/'),
(27, 'https://tankoedward.wordpress.com/2018/03/26/symmetric-and-asymmetric-encryption/'),
(28, 'https://tankoedward.wordpress.com/2018/03/26/symmetric-and-asymmetric-encryption/'),
(29, 'https://tankoedward.wordpress.com/author/eddytnk/'),
(30, 'https://tankoedward.wordpress.com/2018/03/26/symmetric-and-asymmetric-encryption/'),
(31, 'https://tankoedward.wordpress.com/2018/03/13/what-is-the-difference-of-a-web-server-a-web-container-and-application-server/'),
(32, 'https://tankoedward.wordpress.com/2018/03/13/what-is-the-difference-of-a-web-server-a-web-container-and-application-server/'),
(33, 'https://tankoedward.wordpress.com/author/eddytnk/'),
(34, 'https://tankoedward.wordpress.com/2018/03/13/what-is-the-difference-of-a-web-server-a-web-container-and-application-server/'),
(35, 'https://tankoedward.wordpress.com/page/2/'),
(36, '//eddytnk.com'),
(37, 'https://tankoedward.wordpress.com/2019/06/26/generate-and-validate-jwt-token-java-example-code/'),
(38, 'https://tankoedward.wordpress.com/2018/12/01/docker-commands-explained-with-cheatsheet/'),
(39, 'https://tankoedward.wordpress.com/2018/12/01/spring-boot-application-deployed-using-concourse-ci/'),
(40, 'https://tankoedward.wordpress.com/2018/11/14/how-to-create-a-strong-password/'),
(41, 'https://tankoedward.wordpress.com/2018/03/30/jave-game-loop-function/'),
(42, 'https://tankoedward.wordpress.com/2018/03/26/symmetric-and-asymmetric-encryption/'),
(43, 'https://tankoedward.wordpress.com/2018/03/13/what-is-the-difference-of-a-web-server-a-web-container-and-application-server/'),
(44, 'https://tankoedward.wordpress.com/2018/01/12/how-to-develop-a-search-engine-with-php/'),
(45, 'https://tankoedward.wordpress.com/2018/01/09/most-used-softwares-by-it-professional/'),
(46, 'https://tankoedward.wordpress.com/2018/01/03/full-time-java-developers-skills/'),
(47, 'https://tankoedward.wordpress.com/2019/06/'),
(48, 'https://wordpress.com/?ref=footer_website'),
(49, 'https://tankoedward.wordpress.com/'),
(50, 'https://wordpress.com/?ref=footer_blog'),
(51, 'https://automattic.com/cookies'),
(52, '#');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `indexer`
--
ALTER TABLE `indexer`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `linking`
--
ALTER TABLE `linking`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ID_Indexer` (`ID_Indexer`),
  ADD KEY `ID_URL` (`ID_URL`);

--
-- Indexes for table `urls`
--
ALTER TABLE `urls`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `indexer`
--
ALTER TABLE `indexer`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `linking`
--
ALTER TABLE `linking`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `urls`
--
ALTER TABLE `urls`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=53;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `linking`
--
ALTER TABLE `linking`
  ADD CONSTRAINT `linking_ibfk_1` FOREIGN KEY (`ID_Indexer`) REFERENCES `indexer` (`ID`),
  ADD CONSTRAINT `linking_ibfk_2` FOREIGN KEY (`ID_URL`) REFERENCES `urls` (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
