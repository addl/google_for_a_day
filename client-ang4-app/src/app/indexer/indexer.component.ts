import {Component, OnInit} from '@angular/core';
import {IndexService} from "../services/index.service";
import {IntervalObservable} from "rxjs/observable/IntervalObservable";
import {TimerObservable} from "rxjs/observable/TimerObservable";

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
    this.updateAppStats();
    TimerObservable.create(0, 5000).subscribe(()=> {
      this.updateAppStats();
    });
  }

  private updateAppStats() {
    this.indexService.getGeneralStats().subscribe(response => {
      console.log(response);
      this.totalIndexedPages = response.data.totalPages;
      this.totalIndexedWords = response.data.totalWords;
    }, error => {
      console.log(`Error on service subscription, error is: ${error.message}`)
    });
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
