package com.voting.voting_app.repository;

import com.voting.voting_app.model.Poll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PollRepository extends JpaRepository<Poll , Long> {
}
