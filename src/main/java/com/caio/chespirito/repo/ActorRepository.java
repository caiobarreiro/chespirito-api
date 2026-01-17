package com.caio.chespirito.repo;

import com.caio.chespirito.model.ActorEntity;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ActorRepository extends JpaRepository<ActorEntity, UUID> {
    
    @EntityGraph(attributePaths = "characters")
    List<ActorEntity> findAll();
    
    @Query("""
      select a
      from ActorEntity a
      left join fetch a.characters
      where a.id = :id
    """)
    Optional<ActorEntity> findWithCharactersById(@Param("id") UUID id);
    
}
