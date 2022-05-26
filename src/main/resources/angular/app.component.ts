import { Component } from '@angular/core';
import { HelloWorldService } from './core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent   {
  title = 'app2';

  constructor(private helloWorldService: HelloWorldService){
    helloWorldService.helloWorld().subscribe(message=>(alert(message)));

  }
}
