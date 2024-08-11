package librarymanagementsystem;

import librarymanagementsystem.model.Book;
import librarymanagementsystem.repository.BookRepository;
import org.mapstruct.MapperConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.time.Year;

@SpringBootApplication
public class LibraryManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryManagementSystemApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner run(BookRepository repository) {
//        return (args -> insertJavaAdvocates(repository));
//    }
//
//    private void insertJavaAdvocates(BookRepository repository) {
//        repository.deleteAll();
//        repository.save(new Book("Cracking the Coding Interview", "Gayle Laakmann McDowell", Year.of(2008), "0984782869",10));
//    }
}
