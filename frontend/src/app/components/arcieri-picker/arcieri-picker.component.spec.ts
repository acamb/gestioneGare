import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ArcieriPickerComponent } from './arcieri-picker.component';

describe('ArcieriPickerComponent', () => {
  let component: ArcieriPickerComponent;
  let fixture: ComponentFixture<ArcieriPickerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ArcieriPickerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ArcieriPickerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
