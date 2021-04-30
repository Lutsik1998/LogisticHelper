import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Trip } from '../models/trip';
import { Observable } from 'rxjs/Observable';
import { API_LINK } from 'src/assets/connection';

@Injectable({
  providedIn: 'root'
})
export class TripServiceService {


  
  constructor(private http: HttpClient) {
    
  }
 
  public findAll(): Observable<Trip[]> {
    return this.http.get<Trip[]>(API_LINK + 'trips');
  }

  getTrips() : Observable<Trip> {
    return this.http.get<Trip>(API_LINK + 'trips');
  }

  getTripById(id: number): Observable<any> {
    return this.http.get(API_LINK + 'trips/' + id);
  }
 
  public save(trip: Trip): Observable<Trip> {
    return this.http.post<Trip>(API_LINK + 'trips/save', trip);
  }

  public updateTrip(id: number, trip: Trip): Observable<Trip> {
    return this.http.put<Trip>(API_LINK + 'trips/update/' + trip.id, trip);
  }

  public deleteTrip(id: number): Observable<Trip> {
    return this.http.delete<Trip>(API_LINK + 'trips/delete/' + id);
  }

}
