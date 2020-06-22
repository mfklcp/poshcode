/*
 * @Autor: Márcio Franklin
 * @E-mail: mfklcp@gmail.com
 * @License: MIT
 *
 */

package mfklcp.monografia.thejugde.Controller;

import mfklcp.monografia.thejugde.Service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@Controller
@RequestMapping("/questao")
public class QuestaoController {

    @Autowired
    private StorageService storageService;


    @RequestMapping("/get/{fileName}")
    ResponseEntity<Resource> view(@PathVariable("fileName") String fileName) throws FileNotFoundException {

        File file = storageService.load(fileName).toFile();
        InputStreamResource  resource = new InputStreamResource( new FileInputStream(file));

        return ResponseEntity.ok()
                .header("Content-Disposition","inline; filename="+fileName+".pdf")
                .contentType(MediaType.parseMediaType("application/pdf"))
                .cacheControl(CacheControl.noCache())
                .body(resource);

    }

}


