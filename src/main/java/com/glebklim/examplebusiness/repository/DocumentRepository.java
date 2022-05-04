package com.glebklim.examplebusiness.repository;

import com.glebklim.examplebusiness.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, String> {
}
