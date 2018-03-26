import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ClassificaPerGruppiComponent } from './classifica-per-gruppi.component';

describe('ClassificaPerGruppiComponent', () => {
  let component: ClassificaPerGruppiComponent;
  let fixture: ComponentFixture<ClassificaPerGruppiComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ClassificaPerGruppiComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ClassificaPerGruppiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
