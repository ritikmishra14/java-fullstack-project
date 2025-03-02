package com.voting.voting_app.controller;

import com.voting.voting_app.model.Poll;
import com.voting.voting_app.request.Vote;
import com.voting.voting_app.service.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/polls")
public class PollController {

    @Autowired
    private PollService pollService;

    //CREATE POLL
    @PostMapping
    public Poll createPoll(@RequestBody Poll poll){
        return this.pollService.createPoll(poll);
    }

    // GET POLL
    @GetMapping
    public List<Poll> getAllPolls(){
        List<Poll> allPolls = this.pollService.getAllPolls();
        return allPolls;
    }

    @GetMapping("/{id}")
    public Poll getPollById(@PathVariable Long id){
        return this.pollService.getPollById(id);
    }

   // Count of Votes / User can vote
  @PostMapping("/vote")
    public void vote(@RequestBody Vote vote){
        pollService.vote(vote.getPollId() , vote.getOptionIndex());
  }
}
