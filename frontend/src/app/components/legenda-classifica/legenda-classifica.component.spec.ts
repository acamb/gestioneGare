import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LegendaClassificaComponent } from './legenda-classifica.component';

describe('LegendaClassificaComponent', () => {
  let component: LegendaClassificaComponent;
  let fixture: ComponentFixture<LegendaClassificaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LegendaClassificaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LegendaClassificaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
