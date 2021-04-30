import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Truck } from '../models/truck';
import { Observable } from 'rxjs/Observable';
import { API_LINK } from 'src/assets/connection';
 
@Injectable()
export class TruckServiceService {
  
  constructor(private http: HttpClient) {
  }
 
  public findAll(): Observable<Truck[]> {
    return this.http.get<Truck[]>(API_LINK + 'trucks');
  }

  getTrucks() : Observable<Truck> {
    return this.http.get<Truck>(API_LINK + 'trucks');
  }

  getTruckById(id: number): Observable<any> {
    return this.http.get(API_LINK + 'trucks/' + id);
  }
 
  public save(truck: Truck): Observable<Truck> {
    return this.http.post<Truck>(API_LINK + 'trucks/save', truck);
  }

  public updateTruck(id: number, truck: Truck): Observable<Truck> {
    return this.http.put<Truck>(API_LINK + 'trucks/update/' + truck.id, truck);
  }

  public deleteTruck(id: number): Observable<Truck> {
    return this.http.delete<Truck>(API_LINK + 'trucks/delete/' + id);
  }

  public resetWheelsCouter(id: number, truck: Truck): Observable<Truck> {
    return this.http.patch<Truck>(API_LINK + 'trucks/resetWh/' + id, truck);
  }

  public resetOilCouter(id: number, truck: Truck): Observable<Truck> {
    return this.http.patch<Truck>(API_LINK + 'trucks/resetOil/' + id, truck);
  }

}