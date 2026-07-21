import { InjectionToken } from '@angular/core';

export const API_BASE_URL = new InjectionToken<string>('API_BASE_URL', {
  providedIn: 'root',
  factory: () => {
    const hostname = window.location.hostname;
    const port = window.location.port;
    if (port === '4200') {
      return `http://${hostname}:8080`;
    }
    return '';
  }
});
