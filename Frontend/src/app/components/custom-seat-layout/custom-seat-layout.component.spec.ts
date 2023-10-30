import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomSeatLayoutComponent } from './custom-seat-layout.component';

describe('CustomSeatLayoutComponent', () => {
  let component: CustomSeatLayoutComponent;
  let fixture: ComponentFixture<CustomSeatLayoutComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CustomSeatLayoutComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CustomSeatLayoutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
