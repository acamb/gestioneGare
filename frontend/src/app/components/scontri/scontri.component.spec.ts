import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { ScontriComponent } from './scontri.component';

describe('ScontriComponent', () => {
  let component: ScontriComponent;
  let fixture: ComponentFixture<ScontriComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ ScontriComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ScontriComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
