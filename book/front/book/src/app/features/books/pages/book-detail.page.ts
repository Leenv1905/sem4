import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { BOOKS_MOCK } from '../mock/books.mock';
import { Book } from '../models/book.model';

@Component({
  standalone: true,
  templateUrl: './book-detail.page.html',
  imports: [CommonModule]
})
export class BookDetailPage {
  private route = inject(ActivatedRoute);

  book?: Book;

  constructor() {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.book = BOOKS_MOCK.find(b => b.id === id);
  }
}
