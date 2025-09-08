package com.manage.server.service;

import com.manage.server.model.Server;
import com.manage.server.model.ServerDto;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Collection;

public interface ServerService {

    ServerDto create(ServerDto serverDto);
    Server ping(String ipAddress) throws UnknownHostException, IOException;
    Collection<ServerDto> getAllServers(int limit);
    ServerDto get(Long id);
    ServerDto Update(ServerDto serverDto , Long id);
    Boolean delete(Long id);



}
