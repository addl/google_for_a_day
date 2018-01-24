import {Injectable} from '@angular/core';
import 'rxjs/add/operator/map';

import {ResultItem} from "../model/result-item";
import {forEach} from "@angular/router/src/utils/collection";
import {MyServerResponse} from "../interfaces/my-server-response";
import {HttpClient} from "@angular/common/http";
import {Page} from "../interfaces/page";

@Injectable()
export class SearchService {

  constructor(private http:HttpClient) {}

  search(query:string):Array<ResultItem> {
    let list:Array<ResultItem> = [];
    var url = 'http://localhost:8000/api/search/query?query=' + query;
    this.http.get<MyServerResponse<Page>>(url).subscribe(
      (response) => {
        console.log(response);
        if (response['data']) {
          for (let element of response['data']['content']) {
            // console.log(element);
            list.push(new ResultItem(element['url'], element['title'], element['matches']));
          }
        }
      },
      (err) => {
        console.log(`Backend returned code: ${err.status}, error: ${err.error}`);
      });
    return list;
  }

}
