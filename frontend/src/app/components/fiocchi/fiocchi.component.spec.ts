import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { FiocchiComponent } from './fiocchi.component';

describe('FiocchiComponent', () => {
  let component: FiocchiComponent;
  let fixture: ComponentFixture<FiocchiComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ FiocchiComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FiocchiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
