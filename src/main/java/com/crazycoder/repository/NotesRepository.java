package com.crazycoder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crazycoder.model.Notes;

@Repository
public interface NotesRepository extends JpaRepository<Notes, Integer>{

}
