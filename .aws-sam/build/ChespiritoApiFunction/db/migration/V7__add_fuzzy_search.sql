create extension if not exists pg_trgm;


create index if not exists idx_episodes_trgm_pt
on episodes using gin (synopsis_pt gin_trgm_ops);

create index if not exists idx_episodes_trgm_es
on episodes using gin (synopsis_es gin_trgm_ops);

create index if not exists idx_episodes_title_trgm
on episodes using gin (title gin_trgm_ops);

create index if not exists idx_episodes_title_es_trgm
on episodes using gin (title_es gin_trgm_ops);
