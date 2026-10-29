import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { WeatherService } from './services/weather.service';
import { WeatherGraphComponent } from './components/weather-graph/weather-graph';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, WeatherGraphComponent],
  templateUrl: './app.html',
  styleUrls: ['./app.css']
})
export class App implements OnInit { // <-- Changed to App to match main.ts
  weatherData: any = null;
  errorMessage: string = '';

  constructor(private weatherService: WeatherService) {}

  ngOnInit() {
    console.log('WAKE UP APP COMPONENT!');
    this.loadWeather('Budapest');
  }

  loadWeather(city: string) {
    this.weatherService.getWeather(city).subscribe({
      next: (data) => {
        console.log('RAW JSON FROM JAVA:', data);
        this.weatherData = data;
        this.errorMessage = '';
      },
      error: (err) => {
        console.error('Failed to fetch weather:', err);
        this.errorMessage = 'Could not connect to weather server!';
      }
    })}
    isCurrentHour(timeStr: string): boolean {
      const now = new Date();
      const year = now.getFullYear();
      const month = String(now.getMonth() + 1).padStart(2, '0');
      const day = String(now.getDate()).padStart(2, '0');
      const hour = String(now.getHours()).padStart(2, '0');

      // Matches format: "2026-10-27T17"
      const currentPrefix = `${year}-${month}-${day}T${hour}`;
      return timeStr.startsWith(currentPrefix);
  }}
