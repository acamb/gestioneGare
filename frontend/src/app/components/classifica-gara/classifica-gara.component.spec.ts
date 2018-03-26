import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ClassificaGaraComponent } from './classifica-gara.component';

describe('ClassificaGaraComponent', () => {
  let component: ClassificaGaraComponent;
  let fixture: ComponentFixture<ClassificaGaraComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ClassificaGaraComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ClassificaGaraComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
