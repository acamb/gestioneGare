import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GestioneDivisioniComponent } from './gestione-divisioni.component';

describe('GestioneDivisioniComponent', () => {
  let component: GestioneDivisioniComponent;
  let fixture: ComponentFixture<GestioneDivisioniComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GestioneDivisioniComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GestioneDivisioniComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
