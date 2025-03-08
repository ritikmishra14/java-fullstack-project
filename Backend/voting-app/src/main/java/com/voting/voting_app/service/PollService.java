package com.voting.voting_app.service;

import com.voting.voting_app.model.OptionVote;
import com.voting.voting_app.model.Poll;
import com.voting.voting_app.repository.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PollService {

    @Autowired
    private PollRepository pollRepository;
    // create poll
    public Poll createPoll(Poll poll){
        return this.pollRepository.save(poll);
    }

    public List<Poll> getAllPolls() {
        List<Poll> allPolls = this.pollRepository.findAll();
        return allPolls;
    }

    public Poll getPollById(Long id){
        Poll poll = this.pollRepository.findById(id).get();
        return poll;
    }

    public void vote(Long pollId , int optionIndex){
        // get poll from DB
        Poll poll = this.pollRepository.findById(pollId)
                .orElseThrow(()->new RuntimeException("Poll Not found"));
        // get all the options
        List<OptionVote> optionVotes = poll.getOptions();
        // Add validation for wrong index
        if(optionIndex < 0 || optionIndex >= optionVotes.size()){
            throw new IllegalArgumentException("Option index not valid");
        }
        // Get selected option and increment vote count
       OptionVote selectedOption = optionVotes.get(optionIndex);
        selectedOption.setVoteCount(selectedOption.getVoteCount() + 1);
        // save the poll
        this.pollRepository.save(poll);

    }
}
