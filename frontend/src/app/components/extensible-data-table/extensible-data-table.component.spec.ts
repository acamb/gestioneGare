import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ExtensibleDataTableComponent } from './extensible-data-table.component';

describe('ExtensibleDataTableComponent', () => {
  let component: ExtensibleDataTableComponent;
  let fixture: ComponentFixture<ExtensibleDataTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ExtensibleDataTableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ExtensibleDataTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
