import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {AppStats} from "../interfaces/app-stats";
import {MyServerResponse} from "../interfaces/my-server-response";

@Injectable()
export class IndexService {

  constructor(private httpClient:HttpClient) {
  }

  sendURLToServer(newURL:string):void {
    console.log(`Sending Post Request with new URL: ${newURL}`);
    var apiUrl = 'http://localhost:8000/api/index/add/url';
    this.httpClient.post<MyServerResponse>(apiUrl,
      {
        url: newURL
      }).subscribe(
      (response) => {
        console.log(`Add link response is: ${response}`);
      },
      (err) => {
        console.log(err.error);
        console.log(`Backend returned code: ${err.status}, error: ${err.error}`);
      });
  }

  getGeneralStats():AppStats {
    var stats = {totalIndexedPages: 0, totalIndexedWords: 0};
    console.log("Requesting Indexer Statistics");
    this.httpClient.get("http://localhost:8000/api/index/stats").subscribe(
      response => {
        console.log(response);
        return {
          totalIndexedPages: response['data']['totalPages'],
          totalIndexedWords: response['data']['totalWords']
        };
      }, err => {
        console.log(`Backend returned code: ${err.status}, error: ${err.error}`);
      }
    );
    return stats;
  }

  sendClearIndexRequest():void {
    console.log("Sending Clear Index Signal");
    this.httpClient.get("http://localhost:8000/api/index/clear").subscribe(
      response => {
        console.log("Index has been cleaned");
        console.log(response);
      }, err => {
        console.log(`Backend returned code: ${err.status}, error: ${err.error}`);
      }
    )
  }

}
