package com.caio.chespirito.repo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.caio.chespirito.model.ActorEntity;
import com.caio.chespirito.model.CharacterEntity;

public interface CharacterRepository extends JpaRepository<CharacterEntity, UUID> {
    
    @EntityGraph(attributePaths = "episodes")
    List<CharacterEntity> findAll();
    
    @Query("""
      select a
      from CharacterEntity a
      left join fetch a.actor
      where a.id = :id
    """)
    Optional<CharacterEntity> findWithActorById(@Param("id") UUID id);
    
}
