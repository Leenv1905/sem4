import {Component} from '@angular/core';
import {RouterOutlet} from '@angular/router';

@Component({
  standalone: true,
  selector: 'app-home-layout',
  templateUrl: './home-layout.component.html',
  imports: [RouterOutlet]
})
export class HomeLayoutComponent {}
