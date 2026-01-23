
import { Routes } from '@angular/router';
import { BookListPage } from './pages/book-list.page';
// @ts-ignore
import { BookDetailPage } from './pages/book-detail.page';

export const BOOK_ROUTES: Routes = [
  { path: '', component: BookListPage },
  { path: ':id', component: BookDetailPage }
];
