package com.sagarthyme.brs.repository;

import com.sagarthyme.brs.dto.AuthorDto;
import com.sagarthyme.brs.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Integer> {

    /*@Query(value = "delete from tbl_book_author where author_id = ?1", nativeQuery = true)
    void deleteBookByAuthorId(Integer id);*/
}
