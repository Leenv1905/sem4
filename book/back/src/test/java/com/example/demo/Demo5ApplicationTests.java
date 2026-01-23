//package com.example.demo;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@SpringBootTest
//class Demo5ApplicationTests {
//
//    @Test
//    void contextLoads() {
//    }
//
//}

// TEST KHI SESION VẪN MỞ ĐỂ KIỂM TRA LAZY VÀ EAGER
//package com.example.demo;
//
//import com.example.demo.entity.Book;
//import com.example.demo.repository.BookRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//
//@SpringBootTest
//class Demo5ApplicationTests {
//
//    @Autowired
//    private BookRepository bookRepository;
//
//    @Test
//    void testFindAllBooks() {
//        // Gọi findAll
//        List<Book> books = bookRepository.findAll();
//
//        // In ra console để kiểm tra
//        books.forEach(book -> {
//            System.out.println("Book ID: " + book.getId());
//            System.out.println("Title: " + book.getTitle());
//
//            // Nếu muốn kiểm tra publisher (EAGER hoặc Lazy nhưng trong transaction)
//            if (book.getPublisher() != null) {
//                System.out.println("Publisher: " + book.getPublisher().getName());
//            }
//
//            // Nếu muốn kiểm tra authors (Lazy)
//            if (book.getAuthors() != null) {
//                book.getAuthors().forEach(author ->
//                        System.out.println("Author: " + author.getName())
//                );
//            }
//
//            System.out.println("-----");
//        });
//    }
//}


//package com.example.demo;
//
//import com.example.demo.entity.Book;
//import com.example.demo.repository.BookRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@SpringBootTest
//class BookRepositoryTest {
//
//    @Autowired
//    private BookRepository bookRepository;
//
//    @Test
//    @Transactional // giữ session mở để Lazy có thể load
//    void testNPlusOneProblem() {
//        // Query đầu tiên: lấy tất cả Book
//        List<Book> books = bookRepository.findAll();
//
//        // Khi duyệt qua từng Book và gọi getAuthors(),
//        // Hibernate sẽ chạy thêm 1 query cho mỗi Book
//        books.forEach(book -> {
//            System.out.println("Book: " + book.getTitle());
//
//            // Đây chính là chỗ gây N+1 queries
//            book.getAuthors().forEach(author ->
//                    System.out.println("   Author: " + author.getName())
//            );
//        });
//    }
//}

// Giải pháp dùng JOIN FETCH để tránh N+1 problem
package com.example.demo;

import com.example.demo.entity.Author;
import com.example.demo.entity.Book;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;

    @Test
    @Transactional
    void testFindAllLazyNPlusOne() {
        System.out.println("=== Test Lazy findAll (N+1 problem) ===");
        List<Book> books = bookRepository.findAll();

        books.forEach(book -> {
            System.out.println("Book: " + book.getTitle());
            // Mỗi lần gọi getAuthors() sẽ sinh thêm query
            book.getAuthors().forEach(author ->
                    System.out.println("   Author: " + author.getName())
            );
        });
    }

    @Test
    @Transactional
    void testFindAllWithAuthorsJoinFetch() {
        System.out.println("=== Test JOIN FETCH findAllWithAuthors ===");
        List<Book> books = bookRepository.findAllWithAuthors();
        List<Book> books2 = bookRepository.findAllWithAuthors2();
        List<Author> authors = authorRepository.findAllWithBooks();
        List<Author> authors2 = authorRepository.findAllWithBooks2();

//
//        books.forEach(book -> {
//            System.out.println("Book: " + book.getTitle());
//            // Authors đã được load sẵn, không sinh thêm query
//            book.getAuthors().forEach(author ->
//                    System.out.println("   Author: " + author.getName())
//            );
//        });
//// KHÔNG CÓ DISTINCT NÊN BỊ LẶP SÁCH KHI CÓ NHIỀU TÁC GIẢ
//        books2.forEach(book -> {
//            System.out.println("Book 2: " + book.getTitle());
//            // Authors đã được load sẵn, không sinh thêm query
//            book.getAuthors().forEach(author ->
//                    System.out.println("   Author 2: " + author.getName())
//            );
//        });


        authors.forEach(author -> {
            System.out.println("Author: " + author.getName());
            // Authors đã được load sẵn, không sinh thêm query
            author.getBooks().forEach(book ->
                    System.out.println("   Book: " + book.getTitle())
            );
        });
        System.out.println(" ========================== " );
// KHÔNG CÓ DISTINCT NÊN BỊ LẶP SÁCH KHI CÓ NHIỀU TÁC GIẢ
        authors2.forEach(author -> {
            System.out.println("Author2: " + author.getName());
            // Authors đã được load sẵn, không sinh thêm query
            author.getBooks().forEach(book ->
                    System.out.println("Book 2: " + book.getTitle())
            );
        });
    }
}




