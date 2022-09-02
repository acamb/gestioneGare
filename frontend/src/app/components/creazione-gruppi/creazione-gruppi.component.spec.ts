import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { CreazioneGruppiComponent } from './creazione-gruppi.component';

describe('CreazioneGruppiComponent', () => {
  let component: CreazioneGruppiComponent;
  let fixture: ComponentFixture<CreazioneGruppiComponent>;

  beforeEach(waitForAsync(() => {
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
