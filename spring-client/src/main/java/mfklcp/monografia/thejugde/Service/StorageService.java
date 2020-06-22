/*
 * @Autor: Márcio Franklin
 * @E-mail: mfklcp@gmail.com
 * @License: MIT
 *
 */

package mfklcp.monografia.thejugde.Service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {

    void init() throws Exception;

    void storeWithOriginalName(MultipartFile file) throws Exception;

    void store(MultipartFile file,String nome) throws Exception;

    Stream<Path> loadAll() throws Exception;

    Path load(String filename);

    Resource loadAsResource(String filename) throws Exception;

    void deleteAll();

}