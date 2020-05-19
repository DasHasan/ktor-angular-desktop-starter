import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../environments/environment";

export interface System {
  osName: String;
  javaHome: String;
  userName: String;
  totalMemory: Number;
  freeMemory: Number;
}

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  public system$: Observable<System> = this.http.get<System>(`${environment.host}/api/system`)

  constructor(private http: HttpClient) {
  }

}
