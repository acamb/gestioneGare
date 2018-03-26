import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreazioneGruppiComponent } from './creazione-gruppi.component';

describe('CreazioneGruppiComponent', () => {
  let component: CreazioneGruppiComponent;
  let fixture: ComponentFixture<CreazioneGruppiComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreazioneGruppiComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreazioneGruppiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
