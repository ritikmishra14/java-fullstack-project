package com.manage.server.controller;

import com.manage.server.exceptions.ResourceNotFoundException;
import com.manage.server.model.Server;
import com.manage.server.model.ServerDto;
import com.manage.server.service.implementation.ServerServiceImpl;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/servers")
public class ServerController {

    // Controller Layer code goes here.
    Logger logger = LoggerFactory.getLogger(ServerController.class);

    private final ServerServiceImpl serverService;

    public ServerController(ServerServiceImpl serverService) {
        this.serverService = serverService;
    }

    // sayHello
    @GetMapping("/hello-world")
    public String sayHelloWorld() {
        return "Hello World";
    }

    // Get list of servers
    @GetMapping("/list")
    public ResponseEntity<List<ServerDto>> getAllServers() {
        int limit = 30;
        List<ServerDto> serverList = this.serverService.getAllServers(limit).stream().toList();
        if(serverList.size() < 0 ) {
            throw new ResourceNotFoundException("No servers found");
        }
        return ResponseEntity.ok(serverList);
 }

    // Get Server By Id
    @GetMapping("/{id}")
    public ResponseEntity<ServerDto> getServerById(@PathVariable Long id) {
        ServerDto server = this.serverService.get(id);
        if(server == null) {
            throw new ResourceNotFoundException("Server not found");
        }
        return ResponseEntity.ok(server);

    }

 // create a server
    @PostMapping("/create")
    public ResponseEntity<ServerDto> createServer(@RequestBody @Valid ServerDto serverDto) {
        ServerDto serverDtoCreated = this.serverService.create(serverDto);
        if(serverDtoCreated == null) {
            throw new ResourceNotFoundException("Not Created");
        }
        return ResponseEntity.ok(serverDtoCreated);
    }





    // update a server
    @PutMapping("/update/{id}")
    public ResponseEntity<ServerDto> updateServerDto(@RequestBody ServerDto serverDto ,
                                     @PathVariable Long id) {
        ServerDto updatedServer = this.serverService.Update(serverDto , id);
        if(updatedServer == null) {
            throw new ResourceNotFoundException("Server not found");
        }
        return ResponseEntity.ok(updatedServer);
    }

    // Ping a server
    @PostMapping("/ping/{ipAddress}")
    public ResponseEntity<Server> pingServer(@PathVariable String ipAddress) throws IOException {
        Server pingServer = this.serverService.ping(ipAddress);
        if(pingServer == null) {
            throw new ResourceNotFoundException("Server not found");
        }
        return ResponseEntity.ok(pingServer);
    }

    // delete a server
    @DeleteMapping("/delete/{id}")
    public Boolean deleteServer(@PathVariable Long id) {
        return this.serverService.delete(id);
    }
}
