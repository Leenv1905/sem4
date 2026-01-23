import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BookService } from '../services/book.service';
import { Book } from '../models/book.model';
import {Observable, of} from 'rxjs';
import { BOOKS_MOCK } from '../mock/books.mock';
import {Router} from '@angular/router';
@Component({
  standalone: true,
  templateUrl: './book-list.page.html',
  imports: [CommonModule]
})
//TEST DATA
export class BookListPage {
  books$ = of(BOOKS_MOCK);
  selectedBook?: Book;
  constructor(private router: Router) {}

  goDetail(book: Book) {
    this.router.navigate(['/books', book.id]);
  }
}

// export class BookListPage {
//   private bookService = inject(BookService);
//
//   books$!: Observable<Book[]>;
//   selectedBook?: Book;
//
//   constructor() {
//     this.books$ = this.bookService.getAll();
//   }
// }

// Cách 2
// export class BookListPage {
//   private bookService = inject(BookService);
//   books$ = this.bookService.getAll();
// }
// Không cần constructor
// Angular khuyến nghị
// Rất hợp standalone

