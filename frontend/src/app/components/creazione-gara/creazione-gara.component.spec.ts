import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { CreazioneGaraComponent } from './creazione-gara.component';

describe('CreazioneGaraComponent', () => {
  let component: CreazioneGaraComponent;
  let fixture: ComponentFixture<CreazioneGaraComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ CreazioneGaraComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreazioneGaraComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
