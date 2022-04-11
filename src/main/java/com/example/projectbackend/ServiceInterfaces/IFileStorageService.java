package com.example.projectbackend.ServiceInterfaces;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;

import java.io.File;
import java.nio.file.Path;
import java.util.stream.Stream;

public interface IFileStorageService {

    public void init();
    public void save(MultipartFile file);
    public Stream<Path> loadAll();
    public File load(String filename);

}
