export class ResultItem {
  url:string;
  title:string;
  matches:number;

  constructor(url:string, title:string, matches:number) {
    this.url = url;
    this.title = title;
    this.matches = matches;
  }

}
