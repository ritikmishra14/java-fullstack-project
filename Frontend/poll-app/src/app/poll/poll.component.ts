import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Poll } from '../poll.models';
import { PollService } from '../services/poll.service';

@Component({
  selector: 'app-poll',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './poll.component.html',
  styleUrl: './poll.component.css'
})
export class PollComponent implements OnInit {
   polls: Poll[] = [];

  constructor(private pollService: PollService){}
  ngOnInit(): void {
    this.loadAllPolls();
  }

  loadAllPolls(){
   this.pollService.getPolls().subscribe(
    {
      next: (data)=>{
      this.polls = data;
      }
      ,
      error: (error)=>{
        console.log('Error fetching polls' + error);
        
      }
    });
  }
}
