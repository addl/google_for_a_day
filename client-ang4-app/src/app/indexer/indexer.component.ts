import {Component, OnInit} from '@angular/core';
import {IndexService} from "../services/index.service";
import {AppStats} from "../interfaces/app-stats";

@Component({
  selector: 'app-indexer',
  templateUrl: './indexer.component.html',
  styleUrls: ['./indexer.component.css']
})
export class IndexerComponent implements OnInit {

  private totalIndexedPages:number;
  private totalIndexedWords;

  constructor(private indexService:IndexService) {

  }

  ngOnInit() {
    let stats:AppStats = this.indexService.getGeneralStats();
    this.totalIndexedPages = stats.totalIndexedPages;
    this.totalIndexedWords= stats.totalIndexedWords;
  }

  addUrl(url:string):void {
    console.log(`Adding new URL: ${url}`);
    this.indexService.sendURLToServer(url);
  }

  clearIndex():void {
    console.log("Clearing index");
    this.indexService.sendClearIndexRequest();
  }

}
