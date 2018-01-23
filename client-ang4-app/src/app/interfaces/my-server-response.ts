export interface MyServerResponse {
  data:Page;
  status:string;
  error:boolean;
  msg:string;
  explanation:string;
}

interface Page {
  content:any;
  totalPages:number,
  totalElements:number;
  last:boolean;
  numberOfElements:number;
  first:boolean;
  sort:string;
  size:number;
  pageNumber:number
}
