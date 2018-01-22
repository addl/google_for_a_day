import {Injectable} from '@angular/core';
import {Http, Response} from '@angular/http';
import 'rxjs/add/operator/map';

import {ResultItem} from "../model/result-item";

@Injectable()
export class SearchService {

  constructor(private http:Http) {

  }

  search(query:string):Array<ResultItem> {
    // return [
    //   {url: 'http://www.example.com', title: 'Example', matches: 5},
    //   {url: 'http://www.example.com', title: 'London', matches: 15},
    //   {url: 'http://www.example.com', title: 'ESPN Sports', matches: 34}
    // ];
    let list:Array<ResultItem> = [];
    var url = 'http://localhost:8000/api/search/query?query=' + query;
    this.http.get(url).subscribe((res:Response) => {
      console.log(res);
    });
    return list;
  }

}
