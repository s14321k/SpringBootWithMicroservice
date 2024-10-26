import { Time } from "@angular/common";

export interface Flight
{
    id:number,
    flight_number:string,
    operating_airlines:string,
    departure_city:string,
    arrival_city:string,
    date_of_departure:Date,
    estimate_departure_time:Time

}

export class FlightClass implements Flight {
    constructor(
        public id:number,
        public flight_number:string,
        public operating_airlines:string,
        public departure_city:string,
        public arrival_city:string,
        public date_of_departure:Date,
        public estimate_departure_time:Time
    ) {}
  
    // Add behavior/methods if needed
    public calculateDuration(): number {
      // Implementation
      return 0;
    }
  }