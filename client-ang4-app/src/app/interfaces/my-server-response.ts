export interface MyServerResponse<T> {
  data: T;
  status:string;
  error:boolean;
  msg:string;
  explanation:string;
}
