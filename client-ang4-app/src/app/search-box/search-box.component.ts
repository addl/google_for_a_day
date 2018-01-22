import {Component, OnInit, Output, EventEmitter} from '@angular/core';

@Component({
  selector: 'app-search-box',
  templateUrl: './search-box.component.html',
  styleUrls: ['./search-box.component.css']
})
export class SearchBoxComponent implements OnInit {

  @Output() executeSearchEvent:EventEmitter<string> = new EventEmitter<string>();

  constructor() {
  }

  ngOnInit() {
  }

  executeSearch(inputQuery:HTMLInputElement):boolean {
    this.executeSearchEvent.emit(inputQuery.value);
    return false;
  }

}
