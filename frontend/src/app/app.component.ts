import { Component } from '@angular/core';
import {GareService} from "./gare.service";
import * as FileSaver from 'file-saver';
import { AuthenticationService } from './authentication.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app';

  constructor(private gareService: GareService,private authService: AuthenticationService){

  }

  async backupDb(){
    let payload = await this.gareService.backupDb().toPromise();
    let data = new Blob([payload.payload],{ type: 'text/plain;charset=utf-8' });
    FileSaver.saveAs(data, 'backup.sql');
  }

  logout(){
    this.authService.logout();
  }
}
