import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { GestioneGareComponent } from './gestione-gare.component';

describe('GestioneGareComponent', () => {
  let component: GestioneGareComponent;
  let fixture: ComponentFixture<GestioneGareComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ GestioneGareComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GestioneGareComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
