import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DataService {
  data: any = {};
  constructor() { }
  getData(key) {
    return this.data[key];
  }

  setData(key, value) {
    this.data[key] = value;
  }
}
