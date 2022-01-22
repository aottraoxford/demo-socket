import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { data } from '../app-constants';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  url = data.url

  constructor(private httpClient: HttpClient) { }

  register(obj: any) {
    return this.httpClient.post<any>(`${this.url}/user`, obj)
  }

  login(obj: any) {
    return this.httpClient.post<any>(`${this.url}/auth`, obj)
  }
}
