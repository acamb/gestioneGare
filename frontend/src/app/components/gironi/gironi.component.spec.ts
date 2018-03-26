import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GironiComponent } from './gironi.component';

describe('GironiComponent', () => {
  let component: GironiComponent;
  let fixture: ComponentFixture<GironiComponent>;

  beforeEach(async(() => {
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
