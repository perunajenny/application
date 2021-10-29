package BookStore.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import BookStore.application.domain.Book;
import BookStore.application.domain.BookRepository;
import BookStore.application.domain.Category;
import BookStore.application.domain.CategoryRepository;
import BookStore.application.domain.User;
import BookStore.application.domain.UserRepository;

@SpringBootApplication
public class Application {
	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public CommandLineRunner bookStore(BookRepository brepository, CategoryRepository crepository, UserRepository urepository) {
		return (args) -> {
			log.info("save a couple of books");
			crepository.save(new Category("Jännitys"));
			crepository.save(new Category("Poliittinen"));
			crepository.save(new Category("Romantiikka"));
			
			
			brepository.save(new Book("Joka veljeään vihaa", "Kamila Shamsie", 2017, "978-951-24-0753-8", 29.90, crepository.findByName("Poliittinen").get(0)));
			brepository.save(new Book("Testamentit", "Margaret Atwood", 2019, "978-951-1-34624-1", 25.90, crepository.findByName("Poliittinen").get(0)));
			brepository.save(new Book("Tyttö joka etsi varjoaan", "David Lagercrantz", 2017, "978-951-0-41897-0", 21.90, crepository.findByName("Jännitys").get(0)));
			
			User user1 = new User("user", "$2a$10$u2c3z1Lz2u3Ztm4QJtjbKu2isx4xxZkHmP4TjhhYljgIrjdn.AkLi", "USER");
			User user2 = new User("admin", "$2a$10$Un4K7P.3DfFivcQ/OskZhuQBw7tGoC8SZu0W5w3BvqEi6lcFyUPpe", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);
			
			log.info("fetch all books");
			for (Book book : brepository.findAll()) {
				log.info(book.toString());
			}

		};
	}
	

}
