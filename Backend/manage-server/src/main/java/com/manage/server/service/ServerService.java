package com.manage.server.service;

import com.manage.server.model.Server;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Collection;

public interface ServerService {

    Server create(Server server);
    Server ping(String ipAddress) throws UnknownHostException, IOException;
    Collection<Server> list(int limit);
    Server get(Long id);
    Server Update(Server server);
    Boolean delete(Long id);



}
