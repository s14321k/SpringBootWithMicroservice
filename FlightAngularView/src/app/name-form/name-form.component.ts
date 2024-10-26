import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-name-form',
  templateUrl: './name-form.component.html',
  styleUrls: ['./name-form.component.scss']
})
export class NameFormComponent 
{

  isFirstNameFocused =false;
  isSecodtNameFocused = false;

  nameFormGroup !: FormGroup;
 
  

  constructor(private fb : FormBuilder)
  {
    this.nameFormGroup = this.fb.group({
      firstName: ['', [Validators.required]],
      lastName : ['', [Validators.required]]
    })
  }


  get firstName() {
    return this.nameFormGroup.get('email');
  }

  get lastName() {
    return this.nameFormGroup.get('password');
  }
}
