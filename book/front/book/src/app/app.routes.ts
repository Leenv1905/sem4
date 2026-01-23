import { Routes } from '@angular/router';
import {HomeLayoutComponent} from './layouts/home/home-layout.component';
import {AdminLayoutComponent} from './layouts/admin/admin-layout.component';

// app.routes.ts
export const routes: Routes = [

  // ===== USER =====
  {
    path: '',
    component: HomeLayoutComponent,
    children: [
      { path: '', redirectTo: 'books', pathMatch: 'full' },
      {
        path: 'books',
        loadChildren: () =>
          import('./features/books/book.routes')
            .then(m => m.BOOK_ROUTES)
      }
    ]
  },

  // ===== ADMIN =====
  {
    path: 'admin',
    component: AdminLayoutComponent,
    // canActivate: [adminGuard], // bật sau
    children: [
      {
        path: 'dashboard',
        loadChildren: () =>
          import('./features/dashboard/dashboard.routes')
            .then(m => m.DASHBOARD_ROUTES)
      },
      {
        path: 'books',
        loadChildren: () =>
          import('./features/books/book.routes')
            .then(m => m.BOOK_ROUTES)
      }
    ]
  }
];
