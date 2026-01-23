import {Component} from '@angular/core';
import {RouterOutlet} from '@angular/router';

@Component({
  standalone: true,
  selector: 'app-admin-layout',
  templateUrl: './admin-layout.component.html',
  imports: [RouterOutlet]
})
export class AdminLayoutComponent {}
