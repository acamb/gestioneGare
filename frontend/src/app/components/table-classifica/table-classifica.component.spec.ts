import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TableClassificaComponent } from './table-classifica.component';

describe('TableClassificaComponent', () => {
  let component: TableClassificaComponent;
  let fixture: ComponentFixture<TableClassificaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TableClassificaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TableClassificaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
