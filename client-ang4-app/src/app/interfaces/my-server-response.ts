/*
* Generic Interface binding Server Response
*/
export interface MyServerResponse<T> {
  data: T;
  status:string;
  error:boolean;
  msg:string;
  explanation:string;
}
