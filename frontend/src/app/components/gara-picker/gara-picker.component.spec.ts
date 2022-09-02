import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { GaraPickerComponent } from './gara-picker.component';

describe('GaraPickerComponent', () => {
  let component: GaraPickerComponent;
  let fixture: ComponentFixture<GaraPickerComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ GaraPickerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GaraPickerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
