import { Component, OnInit, Input } from '@angular/core';
import {ResultItem} from "../model/result-item";

@Component({
  selector: 'app-search-item-result',
  templateUrl: './search-item-result.component.html',
  styleUrls: ['./search-item-result.component.css']
})
export class SearchItemResultComponent implements OnInit {

  @Input() item: ResultItem;

  constructor() { }

  ngOnInit() {
  }

}
