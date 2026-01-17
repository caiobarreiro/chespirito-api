package com.caio.chespirito.repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.caio.chespirito.model.ShowEntity;

public interface ShowRepository extends JpaRepository<ShowEntity, UUID> {
}
