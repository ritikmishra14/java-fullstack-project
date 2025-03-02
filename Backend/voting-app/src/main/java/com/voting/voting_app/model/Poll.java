package com.voting.voting_app.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Poll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String question;
    @ElementCollection
    private List<OptionVote> options = new ArrayList<>();
//    @ElementCollection
//    private List<Long> votes = new ArrayList<>();

    public Poll(){

    }
    public Poll(Long id, String question, List<OptionVote> options, List<Long> votes) {
        this.id = id;
        this.question = question;
        this.options = options;
//        this.votes = votes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<OptionVote> getOptions() {
        return options;
    }

    public void setOptions(List<OptionVote> options) {
        this.options = options;
    }

//    public List<Long> getVotes() {
//        return votes;
//    }

    public void setVotes(List<Long> votes) {
       // this.votes = votes;
    }

//    @Override
//    public String toString() {
//        return "Poll{" +
//                "id=" + id +
//                ", question='" + question + '\'' +
//                ", options=" + options +
//                ", votes=" + votes +
//                '}';
//    }

    @Override
    public String toString() {
        return "Poll{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", options=" + options +
                '}';
    }
}
