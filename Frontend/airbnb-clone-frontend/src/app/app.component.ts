import { Component, inject, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { FaIconLibrary, FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { ButtonModule } from 'primeng/button';
import { fontAwesomeIcons } from './shared/font-awesome-icon';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet,ButtonModule, FontAwesomeModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent implements OnInit {
  title = 'airbnb-clone-frontend';
  faIconLibrary: FaIconLibrary = inject(FaIconLibrary);
  ngOnInit() {
   this.initFontAwesome()
  }
  
 private initFontAwesome(): void {
this.faIconLibrary.addIcons(...fontAwesomeIcons)
 }
}
