import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {AppStats} from "../interfaces/app-stats";
import {MyServerResponse} from "../interfaces/my-server-response";
import {Observable} from "rxjs/Rx";

@Injectable()
export class IndexService {

  constructor(private httpClient:HttpClient) {
  }

  sendURLToServer(newURL:string):void {
    console.log(`Sending Post Request with new URL: ${newURL}`);
    var apiUrl = 'http://localhost:8000/api/index/add/url';
    this.httpClient.post<MyServerResponse<any>>(apiUrl,
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

  getGeneralStats():Observable<MyServerResponse<AppStats>> {
    console.log("Requesting Indexer Statistics");
    return this.httpClient.get("http://localhost:8000/api/index/stats").map((response: Response) => {
      console.log(response);
      return response;
    }).catch(this.handleError);
  }

  private handleError(error: Response) {
    return Observable.throw(error.statusText);
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
