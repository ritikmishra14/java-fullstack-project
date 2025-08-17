package com.manage.server.service;

import com.manage.server.model.Server;

import java.util.Collection;

public interface ServerService {

    Server create(Server server);
    Collection<Server> list(int limit);
    Server get(Long id);
    Server Update(Server server);
    Boolean delete(Long id);



}
