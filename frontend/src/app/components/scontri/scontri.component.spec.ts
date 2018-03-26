import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ScontriComponent } from './scontri.component';

describe('ScontriComponent', () => {
  let component: ScontriComponent;
  let fixture: ComponentFixture<ScontriComponent>;

  beforeEach(async(() => {
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
