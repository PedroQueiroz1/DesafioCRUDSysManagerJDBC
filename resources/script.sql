CREATE TABLE IF NOT EXISTS `product` (
  `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
  `name` VARCHAR(255) NOT NULL,
  `price` DOUBLE NOT NULL
);

CREATE TABLE IF NOT EXISTS `author` (
  `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
  `name` VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS `movie` (
  `id` BIGINT,
  `duration` INT,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_movie_product` FOREIGN KEY (`id`) REFERENCES `product` (`id`)
);

CREATE TABLE IF NOT EXISTS `review` (
  `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
  `stars` INT,
  `author_id` BIGINT,
  `product_id` BIGINT,
  CONSTRAINT `fk_review_author` FOREIGN KEY (`author_id`) REFERENCES `author` (`id`),
  CONSTRAINT `fk_review_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
);

CREATE TABLE IF NOT EXISTS `book` (
  `id` BIGINT,
  `author_id` BIGINT,
  `genre` VARCHAR(255),
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_book_author` FOREIGN KEY (`author_id`) REFERENCES `author` (`id`),
  CONSTRAINT `fk_book_product` FOREIGN KEY (`id`) REFERENCES `product` (`id`)
);

CREATE TABLE IF NOT EXISTS `movie_author` (
  `movie_id` BIGINT,
  `author_id` BIGINT,
  CONSTRAINT `pk_movie_author` PRIMARY KEY (`movie_id`, `author_id`),
  CONSTRAINT `fk_movie_author_movie` FOREIGN KEY (`movie_id`) REFERENCES `movie` (`id`),
  CONSTRAINT `fk_movie_author_author` FOREIGN KEY (`author_id`) REFERENCES `author` (`id`)
);