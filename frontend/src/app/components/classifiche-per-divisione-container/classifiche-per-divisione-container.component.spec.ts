import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ClassifichePerDivisioneContainerComponent } from './classifiche-per-divisione-container.component';

describe('ClassifichePerDivisioneContainerComponent', () => {
  let component: ClassifichePerDivisioneContainerComponent;
  let fixture: ComponentFixture<ClassifichePerDivisioneContainerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ClassifichePerDivisioneContainerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ClassifichePerDivisioneContainerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
