import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { FindFlightService } from './find-flight.service';
import { Router } from '@angular/router';
import { Flight } from '../interfaceHybrid/Flight';

@Component({
  selector: 'app-find-flight',
  templateUrl: './find-flight.component.html',
  styleUrls: ['./find-flight.component.scss']
})
export class FindFlightComponent implements OnInit
{

  findFlightFormGroup !: FormGroup;

  constructor(private fb : FormBuilder, private findFlightService : FindFlightService, private router : Router){};

  ngOnInit(): void 
  {
      this.findFlightFormGroup = this.fb.group({
        departure : ['', [Validators.required]],
        arrival   : ['', [Validators.required]],
        date      : ['', [Validators.required]]
      })
  }

  onSubmit(): void
  {
    if(this.findFlightFormGroup.valid)
    {
      const departure = this.findFlightFormGroup.get("departure")?.value;
      const arrival   = this.findFlightFormGroup.get("arrival")?.value;
      const date      = this.findFlightFormGroup.get("date")?.value;

      this.findFlightService.findFlight(departure, arrival, date)
                            .subscribe({
                              next : (flightList : Flight[]) => {
                                // this.router.navigate(['available-flights'], { state: {flights : flightList}})
                                console.log(flightList);
                              },
                              error: (error) =>
                              {
                                console.error('Error fetching the flight: ', error);
                              }
                            });
    }
  }
}
