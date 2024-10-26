import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Flight } from '../interfaceHybrid/Flight';

@Injectable({
  providedIn: 'root'
})
export class FindFlightService {

  constructor(private httpClient : HttpClient) { }

  private findFlightURL = "http://localhost:8081/SarathFlight/flightControl/findFlight";

  findFlight(departure : string, arrival : string, date : Date)
  {
    // const body = { departure, arrival, date };
    // const headers = new HttpHeaders({'Conten-Type' : 'application/json'});

    const params = new HttpParams().set('from', departure)
                                   .set('to', arrival)
                                   .set('departureDate', date.toISOString());
    return this.httpClient.get<Flight[]>(this.findFlightURL, { params });
  }
}
