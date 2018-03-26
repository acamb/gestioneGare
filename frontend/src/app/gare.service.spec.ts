import { TestBed, inject } from '@angular/core/testing';

import { GareService } from './gare.service';

describe('GareService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [GareService]
    });
  });

  it('should be created', inject([GareService], (service: GareService) => {
    expect(service).toBeTruthy();
  }));
});
