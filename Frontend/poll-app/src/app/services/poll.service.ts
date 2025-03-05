import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Poll } from '../poll.models';

@Injectable({
  providedIn: 'root'
})
export class PollService {

  private baseUrl = 'http://localhost:8081/api/polls';


  constructor(private http: HttpClient) {
   }

   //CREATE POLL
   createPoll(poll: Poll): Observable<Poll>{
    return this.http.post<Poll>(this.baseUrl , poll);
   }

   // GET POLL
   getPolls(): Observable<Poll[]>{
    return this.http.get<Poll[]>(this.baseUrl);
   }

   vote(pollId: number , optionIndex: number): Observable<void>{
    const url = `${this.baseUrl}/vote`;
    return this.http.post<void>(url , { pollId , optionIndex })
   }
}
