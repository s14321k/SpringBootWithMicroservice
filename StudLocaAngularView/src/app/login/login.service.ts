import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs'
@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private flightLoginURL = "http://localhost:8081/SarathFlight/userControl/loginAuthenticate";

  constructor(private httpClient: HttpClient) { }

  // register(firstName : string, lastName : string, email : string, password : string) : Observable<boolean>
  authenticate( email : string, password : string) : Observable<boolean>
  {
    // const body = {firstName, lastName, email, password};
    const body = {email, password};
    // const headers = new HttpHeaders({'Content-Type' : 'application/json'});

    return this.httpClient.post<boolean>(this.flightLoginURL, body);
  }
}
