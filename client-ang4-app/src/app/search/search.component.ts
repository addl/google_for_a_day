import {Component, OnInit} from '@angular/core';
import {SearchService} from "../services/search.service";
import {ResultItem} from "../model/result-item";

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})

export class SearchComponent implements OnInit {

  results:Array<ResultItem>;

  constructor(private searchService:SearchService) {
  }

  executeSearch(query:string):void {
    console.log(`Looking for ${query}`);
    this.results = this.searchService.search(query);
  }

  ngOnInit() {
  }

}
