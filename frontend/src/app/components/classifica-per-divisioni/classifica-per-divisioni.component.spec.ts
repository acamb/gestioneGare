import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { ClassificaPerDivisioniComponent } from './classifica-per-divisioni.component';

describe('ClassificaPerDivisioniComponent', () => {
  let component: ClassificaPerDivisioniComponent;
  let fixture: ComponentFixture<ClassificaPerDivisioniComponent>;

  beforeEach(waitForAsync(() => {
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
