import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Book} from '../models/book.model';

@Injectable({ providedIn: 'root' })
export class BookService {
  private api = 'http://localhost:8080/api/books';

  constructor(private http: HttpClient) {}

  getAll() {
    return this.http.get<Book[]>(this.api);
  }

  getById(id: number) {
    return this.http.get<Book>(`${this.api}/${id}`);
  }
}
