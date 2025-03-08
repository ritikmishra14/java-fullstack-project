import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Poll } from '../poll.models';
import { PollService } from '../services/poll.service';

@Component({
  selector: 'app-poll',
  standalone: true,
  imports: [CommonModule , FormsModule],
  templateUrl: './poll.component.html',
  styleUrl: './poll.component.css'
})
export class PollComponent implements OnInit {
  newPoll: Poll = {
   id : 0,
  question: '',
  options: [
    {voteOption: '' , voteCount: 0},
    {voteOption: '' , voteCount: 0}
  ]
  }
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

  createPoll(){
    this.pollService.createPoll(this.newPoll)
    .subscribe(
      {
        next: (createdPoll)=>{
       this.polls.push(createdPoll);
       this.resetPoll();
        },
        error: (error)=>{
          console.log('Error fetching polls' + error);
        }

      });
     //this.resetPoll();
  }

  resetPoll(){
    this.newPoll = {
      id: 0,
      question: '',
      options: [
        {voteOption: '' , voteCount: 0},
        {voteOption: '' , voteCount: 0}
      ]
      }
  }

vote(pollId : number , optionIndex: number){
 this.pollService.vote(pollId , optionIndex)
 .subscribe(
  {
    next: ()=>{
      const poll = this.polls.find((p) => p.id === pollId);
      
      if(poll){
        poll.options[optionIndex].voteCount++;
      }
       },
       error: (error)=>{
         console.log('Error voting on poll' + error);
       }
  });
  }

  trackByIndex(index: number): number {
    return index;
  }
}
