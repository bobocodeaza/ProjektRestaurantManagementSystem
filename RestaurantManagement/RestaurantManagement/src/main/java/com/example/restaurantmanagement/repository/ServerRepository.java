package com.example.restaurantmanagement.repository;

import com.example.restaurantmanagement.model.Server;
import org.springframework.stereotype.Repository;

@Repository
public class ServerRepository extends InFileRepository<Server> {
    public ServerRepository() {
        super("server.json", Server.class);
    }

    @Override
    protected String getId(Server entity) {
        return entity.getId();
    }

    @Override
    protected void setId(Server entity, String id) {
        entity.setId(id);
    }
}
