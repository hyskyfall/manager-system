package com.example.ManagerSystem.repository;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;

import com.example.ManagerSystem.dto.AddUserCommand;
import com.example.ManagerSystem.dto.GetResourceQuery;
import com.example.ManagerSystem.dto.Resource;
import com.example.ManagerSystem.dto.UserInfo;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class ManagerSystemRepositoryImpl implements ManagerSystemRepository {
    private Set<Resource> resources;
    private static final String fileName = "resources.json";

    @PostConstruct
    public void init() {
        // 在这里执行读取文件的操作
        File file = Paths.get(fileName).toFile();
        if (!file.exists()){
            resources = new HashSet<>();
        } else {
            byte[] bytes = new byte[(int) file.length()];
            try (FileInputStream fis = new FileInputStream(file)) {
                fis.read(bytes);
                resources = JSON.parseObject(new String(bytes), new TypeReference<Set<Resource>>(){});
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void addUser(AddUserCommand command) {
        command.getEndpoint().stream().map(
                resource ->new Resource(command.getUserId(), resource)
        ).forEach(resources::add);
        try(OutputStream outputStream = Files.newOutputStream(Paths.get(fileName))) {
            outputStream.write(JSON.toJSONBytes(resources));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getResource(GetResourceQuery query, UserInfo queryBy) {
        List<Resource> resourcePos = resources.stream().filter(
                resourcePo -> resourcePo.getResource().equals(query.getResource()) &&
                        resourcePo.getuserId().equals(queryBy.getUserId())
        ).collect(Collectors.toList());
        if(resourcePos.isEmpty()){
            return null;
        }
        return resourcePos.get(0).getResource();
    }
}
