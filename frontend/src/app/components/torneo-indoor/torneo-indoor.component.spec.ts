import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TorneoIndoorComponent } from './torneo-indoor.component';

describe('TorneoIndoorComponent', () => {
  let component: TorneoIndoorComponent;
  let fixture: ComponentFixture<TorneoIndoorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TorneoIndoorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TorneoIndoorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
