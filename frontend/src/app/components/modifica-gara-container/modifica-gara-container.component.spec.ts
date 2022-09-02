import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { ModificaGaraContainerComponent } from './modifica-gara-container.component';

describe('ModificaGaraContainerComponent', () => {
  let component: ModificaGaraContainerComponent;
  let fixture: ComponentFixture<ModificaGaraContainerComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ ModificaGaraContainerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ModificaGaraContainerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
