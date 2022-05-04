package com.glebklim.examplebusiness.api;

import com.glebklim.examplebusiness.entity.Document;
import com.glebklim.examplebusiness.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/download")
public class DownloadController {

    private final DocumentService docService;

    @Autowired
    public DownloadController(DocumentService documentService) {
        this.docService = documentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> download(@PathVariable String id) {
        Document document = docService.findById(id);
        byte [] data = document.getData();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(document.getContentType()));
        ContentDisposition disposition = ContentDisposition.builder("attachment")
                .filename(document.getFileName()).build();
        headers.setContentDisposition(disposition);
        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }

    @
}
