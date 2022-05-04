package com.glebklim.examplebusiness.service;

import com.glebklim.examplebusiness.entity.Document;
import com.glebklim.examplebusiness.repository.DocumentRepository;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class DocumentService {

    private final DocumentRepository docRepository;

    @Autowired
    public DocumentService(DocumentRepository docRepository) {
        this.docRepository = docRepository;
    }

    public List<Document> findAll() {
        return docRepository.findAll();
    }

    public Document findById(String id) {
        return docRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(String.format("No document with id %s", id)));
    }

    public void deleteById(String id) {
        docRepository.deleteById(id);
    }

    public Document save(String fileName, byte[] data) throws IOException {
        Document document = new Document();
        document.setId(UUID.randomUUID().toString());
        document.setFileName(fileName);
        document.setData(data);
        return document;
    }

    public Document rewriteData(String id, byte [] data) {
        Document document = findById(id);
        document.setData(data);
        return docRepository.save(document);
    }

    public Document addData(String id, byte [] data) {
        Document document = findById(id);
        String contentType = document.getContentType();
        if(contentType.contains("image")) {
            throw new UnsupportedOperationException(String.format("You cannot add data to file with %s", contentType));
        }
        byte [] initialData = document.getData();
        Byte [] endData = mergeArrays(initialData, data);
        document.setData(ArrayUtils.toPrimitive(endData));
        return docRepository.save(document);
    }

    //utill
    private Byte [] mergeArrays(byte [] arr1, byte [] arr2) {
        return Stream.concat(Arrays.stream(ArrayUtils.toObject(arr1)), Arrays.stream(ArrayUtils.toObject(arr2)))
                .toArray(Byte[]::new);
    }

}
