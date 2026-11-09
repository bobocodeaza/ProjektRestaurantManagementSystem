package com.example.restaurantmanagement.service;
import com.example.restaurantmanagement.model.Server; import com.example.restaurantmanagement.repository.ServerRepository; import org.springframework.stereotype.Service; import java.util.List;
@Service public class ServerService {
    private final ServerRepository repo; public ServerService(ServerRepository repo) { this.repo = repo; }
    public Server save(Server s) { return repo.save(s); } public Server findById(String id) { return repo.findById(id); }
    public List<Server> findAll() { return repo.findAll(); } public void delete(String id) { repo.delete(id); }
}
