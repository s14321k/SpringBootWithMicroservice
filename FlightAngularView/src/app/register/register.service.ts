import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs'

@Injectable({
  providedIn: 'root'
})
export class RegisterService 
{
  private registerURL = "http://localhost:8081/SarathFlight/userControl/registerUser";

  constructor(private httpClient : HttpClient) { }

  registerUser(firstName : string, lastName : string, email : string, password : string): Observable<any>
  {
    const body = { firstName, lastName, email, password};
    const headers = new HttpHeaders({'Content-Type':'application/json'})
    // return this.httpClient.post<string>(this.registerURL, body)
    return this.httpClient.post<string>(this.registerURL, body, {headers, responseType: 'text' as 'json'}); // By setting responseType: 'text' as 'json', you inform Angular to treat the response as a text and avoid attempting to parse it as JSON.
  }
}
