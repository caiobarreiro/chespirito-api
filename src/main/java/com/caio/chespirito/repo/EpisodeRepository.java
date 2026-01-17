package com.caio.chespirito.repo;

import com.caio.chespirito.model.EpisodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EpisodeRepository extends JpaRepository<EpisodeEntity, UUID> {

    // Lista (com show + characters) e filtro opcional de showId
    @Query("select distinct e " +
            "from EpisodeEntity e " +
            "left join fetch e.show " +
            "left join fetch e.characters " +
            "where (:showId is null or e.show.id = :showId) " +
            "order by e.season asc, e.episodeNumber asc")
    List<EpisodeEntity> findAllWithCharactersAndShow(@Param("showId") UUID showId);
    
    @Query("select distinct e " +
        "from EpisodeEntity e " +
        "left join fetch e.show " +
        "left join fetch e.characters " +
        "where (:epId is null or e.id = :epId) " +
        "order by e.season asc, e.episodeNumber asc")
    Optional<EpisodeEntity> findOneWithCharactersAndShow(@Param("epId") UUID epId);

    /**
     * Busca híbrida:
     * - Full-text (PT + ES): search_vector @@ tsquery
     * - Fuzzy (typos): operador pg_trgm (%) + similarity()
     *
     * Observação: essa query retorna e.* (entities). No service você pega os IDs e
     * depois carrega show+characters com join fetch.
     */

    @Query(value = "with inp as ( " +
            "  select nullif(trim(:q), '') as raw " +
            "), ts as ( " +
            "  select " +
            "    inp.raw, " +
            "    unaccent(lower(inp.raw)) as raw_norm, " +
            "    case when inp.raw is null then null else websearch_to_tsquery('portuguese', unaccent(lower(inp.raw))) end as q_pt, " +
            "    case when inp.raw is null then null else websearch_to_tsquery('spanish',    unaccent(lower(inp.raw))) end as q_es " +
            "  from inp " +
            "), ranked as ( " +
            "  select " +
            "    e.id as id, " +
            "    e.season as season, " +
            "    e.episode_number as episode_number, " +
            "    greatest( " +
            "      coalesce(ts_rank(e.search_vector, ts.q_pt), 0), " +
            "      coalesce(ts_rank(e.search_vector, ts.q_es), 0), " +
            "      similarity(unaccent(lower(e.title)),       ts.raw_norm), " +
            "      similarity(unaccent(lower(e.title_es)),    ts.raw_norm), " +
            "      similarity(unaccent(lower(e.synopsis_pt)), ts.raw_norm), " +
            "      similarity(unaccent(lower(e.synopsis_es)), ts.raw_norm) " +
            "    ) as relevance " +
            "  from episodes e " +
            "  cross join ts " +
            "  where ts.raw is not null " +
            "    and (:showId is null or e.show_id = :showId) " +
            "    and ( " +
            "         (e.search_vector @@ ts.q_pt) " +
            "      or (e.search_vector @@ ts.q_es) " +
            "      or (unaccent(lower(e.title))       % ts.raw_norm) " +
            "      or (unaccent(lower(e.title_es))    % ts.raw_norm) " +
            "      or (unaccent(lower(e.synopsis_pt)) % ts.raw_norm) " +
            "      or (unaccent(lower(e.synopsis_es)) % ts.raw_norm) " +
            "    ) " +
            ") " +
            "select r.id " +
            "from ranked r " +
            "order by r.relevance desc, r.season, r.episode_number", nativeQuery = true)
    List<UUID> searchIdsByTextAndShow(@Param("q") String q, @Param("showId") UUID showId);

    // Carrega episódios por ids + show + characters em uma tacada
    @Query("select distinct e " +
            "from EpisodeEntity e " +
            "left join fetch e.show " +
            "left join fetch e.characters " +
            "where e.id in :ids " +
            "order by e.season asc, e.episodeNumber asc")
    List<EpisodeEntity> findByIdInWithCharactersAndShow(@Param("ids") List<UUID> ids);
}
