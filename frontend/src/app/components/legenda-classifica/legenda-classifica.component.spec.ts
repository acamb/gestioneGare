import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { LegendaClassificaComponent } from './legenda-classifica.component';

describe('LegendaClassificaComponent', () => {
  let component: LegendaClassificaComponent;
  let fixture: ComponentFixture<LegendaClassificaComponent>;

  beforeEach(waitForAsync(() => {
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
