import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WeatherGraph } from './weather-graph';

describe('WeatherGraph', () => {
  let component: WeatherGraph;
  let fixture: ComponentFixture<WeatherGraph>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [WeatherGraph],
    }).compileComponents();

    fixture = TestBed.createComponent(WeatherGraph);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
