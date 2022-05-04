package com.glebklim.examplebusiness.api;

import com.glebklim.examplebusiness.dto.ChangeDataRequestDto;
import com.glebklim.examplebusiness.entity.Document;
import com.glebklim.examplebusiness.service.DocumentService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/documents")
public class DocumentController {

    private final DocumentService docService;

    @Autowired
    public DocumentController(DocumentService photoService) {
        this.docService = photoService;
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<Document> findAll() {
        return docService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Document findById(@PathVariable String id) {
        return docService.findById(id);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.OK)
    @SneakyThrows
    public ResponseEntity<Document> create(@RequestPart MultipartFile file) {
        return new ResponseEntity<>(docService.save(file.getOriginalFilename(), file.getBytes()), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable String id) {
        docService.deleteById(id);
    }

    @PutMapping("/rewrite/")
    @ResponseStatus(HttpStatus.OK)
    public Document rewriteData(@RequestBody ChangeDataRequestDto dto) {
        return docService.rewriteData(dto.getId(), dto.getData());
    }

    @PutMapping("/add/")
    @ResponseStatus(HttpStatus.OK)
    public Document addData(@RequestBody ChangeDataRequestDto dto) {
        return docService.addData(dto.getId(), dto.getData());
    }

}