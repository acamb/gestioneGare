import { Directive } from '@angular/core';
import {AbstractControl, NG_VALIDATORS, ValidationErrors, Validator} from "@angular/forms";

@Directive({
  selector: '[AnnoValidator]',
  providers: [{provide: NG_VALIDATORS, useExisting: AnnoValidatorDirective, multi: true}]
})
export class AnnoValidatorDirective implements Validator{

  constructor() { }


  validate(c: AbstractControl): ValidationErrors | any {
    if(isNaN(Number(c.value))){
      return "NaNError";
    }
    let n = Number(c.value);
    if(n < 2017){
      return "minValueError"
    }
    return null;
  }
}
