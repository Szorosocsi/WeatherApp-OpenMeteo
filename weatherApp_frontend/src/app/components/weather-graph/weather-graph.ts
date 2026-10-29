import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-weather-graph',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './weather-graph.html'
})
export class WeatherGraphComponent {
  @Input() hourlyTemps: number[] = [];
  @Input() hourlyTimes: string[] = [];

  getAbsoluteMax(): number {
    if (!this.hourlyTemps || this.hourlyTemps.length === 0) return 10;
    const absValues = this.hourlyTemps.map(t => Math.abs(t));
    const max = Math.max(...absValues);
    return max === 0 ? 10 : max;
  }

  getMinTemp(): number {
    if (!this.hourlyTemps || this.hourlyTemps.length === 0) return 0;
    return Math.min(...this.hourlyTemps);
  }

  getMaxTemp(): number {
    if (!this.hourlyTemps || this.hourlyTemps.length === 0) return 0;
    return Math.max(...this.hourlyTemps);
  }

  calculateBarY(temp: number): number {
    const zeroY = 100; // Dead center zero line in SVG
    const maxAbs = this.getAbsoluteMax();
    const scale = 70 / maxAbs; // 70px available space up and down from center

    if (temp >= 0) {
      return zeroY - (temp * scale);
    } else {
      return zeroY; // Negative bar starts at zero line and extends down
    }
  }

  calculateBarHeight(temp: number): number {
    const maxAbs = this.getAbsoluteMax();
    const scale = 70 / maxAbs;
    const height = Math.abs(temp) * scale;
    return Math.max(height, 2); // Minimum 2px height so zero/low values visible
  }

  getFormattedHour(timeString: string): string {
    if (!timeString) return '';
    const parts = timeString.split('T');
    return parts.length > 1 ? parts[1].substring(0, 5) : timeString;
  }
}
