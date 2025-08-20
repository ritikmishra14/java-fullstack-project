package com.manage.server.service.implementation;

import com.manage.server.enumerations.Status;
import com.manage.server.model.Server;
import com.manage.server.repository.ServerRepository;
import com.manage.server.service.ServerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.Random;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ServerServiceImpl implements ServerService {

    private final ServerRepository serverRepository;
    @Override
    public Server create(Server server) {
        log.info("Server name is: {}" , server.getName());
        server.setImageUrl(setServerImageUrl());
        return serverRepository.save(server);
    }



    @Override
    public Server ping(String ipAddress) throws IOException {
        log.info("Server name is: {}" , ipAddress);
        Server server = this.serverRepository.findByIpAddress(ipAddress);
        InetAddress address = InetAddress.getByName(ipAddress);
        server.setStatus(address.isReachable(10000) ? Status.SERVER_UP : Status.SERVER_DOWN);
        this.serverRepository.save(server);
        return server;
    }



    @Override
    public Collection<Server> list(int limit) {
        log.info("fetching all Servers");

        return this.serverRepository
                .findAll(PageRequest.of(0 , limit)).toList();
    }

    @Override
    public Server get(Long id) {
        log.info("fetching server by id: {}" , id);
        return this.serverRepository.findById(id).get();
    }

    @Override
    public Server Update(Server server) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        log.info("delete server by id: {}" , id);
        Server server = this.serverRepository.findById(id).get();
        if(server != null) {
            this.serverRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private String setServerImageUrl() {
        String[] randomNames = {"server1" , "server2" , "server3" , "server3"};
        int randomIndex = new Random().nextInt(0,4);
        return randomNames[randomIndex];
    }
}
