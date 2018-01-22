import {Component, OnInit, Input} from '@angular/core';
import {ResultItem} from "../model/result-item";

@Component({
  selector: 'app-search-result-list',
  templateUrl: './search-result-list.component.html',
  styleUrls: ['./search-result-list.component.css']
})

export class SearchResultListComponent implements OnInit {

  @Input() results:Array<ResultItem>;

  constructor() {
  }

  ngOnInit() {
  }

}
