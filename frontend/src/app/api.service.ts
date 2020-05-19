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

  constructor(private http: HttpClient) {
  }

  getSystem(): Observable<System> {
    return this.http.get<System>(`${environment.host}/api/system`)
  }
}
