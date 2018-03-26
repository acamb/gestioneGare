import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ClassificaPerDivisioniComponent } from './classifica-per-divisioni.component';

describe('ClassificaPerDivisioniComponent', () => {
  let component: ClassificaPerDivisioniComponent;
  let fixture: ComponentFixture<ClassificaPerDivisioniComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ClassificaPerDivisioniComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ClassificaPerDivisioniComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
