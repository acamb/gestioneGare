import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { GironiComponent } from './gironi.component';

describe('GironiComponent', () => {
  let component: GironiComponent;
  let fixture: ComponentFixture<GironiComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ GironiComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GironiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
