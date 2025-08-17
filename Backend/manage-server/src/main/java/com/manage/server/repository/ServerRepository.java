package com.manage.server.repository;

import com.manage.server.model.Server;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServerRepository  extends JpaRepository<Server , Long> {

    public Server findByIpAddress();

}
