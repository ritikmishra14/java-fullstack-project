package com.manage.server.service.implementation;

import com.manage.server.enumerations.Status;
import com.manage.server.model.Server;
import com.manage.server.model.ServerDto;
import com.manage.server.repository.ServerRepository;
import com.manage.server.service.ServerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ServerServiceImpl implements ServerService {

    private final ServerRepository serverRepository;

    private final ModelMapper modelMapper;

    // convert a DTO object to Entity
    private Server convertToEntity(ServerDto serverDto) {
        Server server = modelMapper.map(serverDto , Server.class);
        return server;
    }

    // convert an Entity to DTO
    private ServerDto convertToServerDTo(Server server) {
        ServerDto serverDto = this.modelMapper.map(server , ServerDto.class);
        return serverDto;
    }


    @Override
    public ServerDto create(ServerDto serverDto) {
        log.info("Server name is: {}" , serverDto.getName());

        serverDto.setImageUrl(setServerImageUrl());
       Server server = convertToEntity(serverDto);
      Server createdServer = this.serverRepository.save(server);
     return convertToServerDTo(createdServer);

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
    public Collection<ServerDto> getAllServers(int limit) {
        log.info("fetching all Servers");
        List<Server> listOfServer = this.serverRepository.findAll(
                PageRequest.of(0 , limit)).toList();
       List<ServerDto> listOfServerDtos = new ArrayList<>();
               listOfServer.forEach(
                server -> {
                    listOfServerDtos.add(convertToServerDTo(server));
                }
        );

        return listOfServerDtos;

    }

    @Override
    public ServerDto get(Long id) {
        log.info("fetching server dto by id: {}" , id);
        Server server = this.serverRepository.findById(id).get();
        ServerDto servertDto = convertToServerDTo(server);
        return servertDto;
    }

    @Override
    public ServerDto Update(ServerDto serverDto , Long id)
    {
      Server updatedUser = this.serverRepository.findById(id)
              .map(
                      server -> {
                          server.setId(id);
                          server.setName(serverDto.getName());
                          server.setMemory(serverDto.getMemory());
                          server.setIpAddress(serverDto.getIpAddress());
                          server.setStatus(serverDto.getStatus());
                          server.setImageUrl(serverDto.getImageUrl());
                          return server;
                      }
              ).get();
     return convertToServerDTo(updatedUser);
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
