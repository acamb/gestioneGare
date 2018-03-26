import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreazioneGaraContainerComponent } from './creazione-gara-container.component';

describe('CreazioneGaraContainerComponent', () => {
  let component: CreazioneGaraContainerComponent;
  let fixture: ComponentFixture<CreazioneGaraContainerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreazioneGaraContainerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreazioneGaraContainerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
