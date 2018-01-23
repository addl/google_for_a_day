import {Component, OnInit, Output, EventEmitter} from '@angular/core';

@Component({
  selector: 'app-url-input-box',
  templateUrl: './url-input-box.component.html',
  styleUrls: ['./url-input-box.component.css']
})
export class UrlInputBoxComponent implements OnInit {

  @Output() addNewLinkEvent:EventEmitter<string> = new EventEmitter<string>();
  @Output() clearIndexEvent:EventEmitter<any> = new EventEmitter<any>();

  constructor() {
  }

  ngOnInit() {
  }

  addNewLinkFunction(inputLink:HTMLInputElement):boolean {
    this.addNewLinkEvent.emit(inputLink.value);
    return false;
  }

  clearIndexFunction():boolean{
    this.clearIndexEvent.emit();
    return false;
  }

}
