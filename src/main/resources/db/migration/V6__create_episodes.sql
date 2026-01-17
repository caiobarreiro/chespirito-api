-- extensões
create extension if not exists pgcrypto;
create extension if not exists unaccent;

-- =========================================
-- TABELA EPISODES
-- =========================================

create table if not exists episodes (
  id uuid primary key default gen_random_uuid(),

  show_id uuid not null,

  season int,
  episode_number int,
  air_date date,

  -- títulos
  title text not null,        -- português
  title_es text not null,     -- espanhol

  -- sinopses
  synopsis_pt text not null,
  synopsis_es text not null,

  -- busca full-text bilíngue (coluna normal; trigger preenche)
  search_vector tsvector not null default ''::tsvector,

  constraint fk_episodes_show
    foreign key (show_id)
    references shows (id)
    on delete cascade
);

alter table episodes
  add constraint uq_episodes_show_season_episode
  unique (show_id, season, episode_number);

-- =========================================
-- TRIGGER: monta search_vector (PT + ES)
-- - ignora acento: unaccent()
-- - ignora caixa: lower()
-- =========================================

create or replace function episodes_set_search_vector()
returns trigger
language plpgsql
as $$
begin
  new.search_vector :=
      setweight(to_tsvector('portuguese', unaccent(lower(coalesce(new.title, '')))), 'A')
   || setweight(to_tsvector('spanish',    unaccent(lower(coalesce(new.title_es, '')))), 'A')
   || setweight(to_tsvector('portuguese', unaccent(lower(coalesce(new.synopsis_pt, '')))), 'B')
   || setweight(to_tsvector('spanish',    unaccent(lower(coalesce(new.synopsis_es, '')))), 'B');

  return new;
end;
$$;

drop trigger if exists trg_episodes_search_vector on episodes;

create trigger trg_episodes_search_vector
before insert or update of title, title_es, synopsis_pt, synopsis_es
on episodes
for each row
execute function episodes_set_search_vector();

-- =========================================
-- ÍNDICES
-- =========================================

-- evita duplicar episódio por temporada (se quiser manter o partial, ok)
create unique index if not exists uq_episodes_season_number
  on episodes (season, episode_number)
  where season is not null and episode_number is not null;

-- índice de busca (FTS)
create index if not exists idx_episodes_search
  on episodes using gin (search_vector);

-- índices auxiliares
create index if not exists idx_episodes_season on episodes (season);
create index if not exists idx_episodes_air_date on episodes (air_date);

-- =========================================
-- INSERTS DE EXEMPLO
-- =========================================

insert into episodes (
  show_id,
  season,
  episode_number,
  air_date,
  title,
  title_es,
  synopsis_pt,
  synopsis_es
)
select
  s.id,
  v.season,
  v.episode_number,
  v.air_date,
  v.title,
  v.title_es,
  v.synopsis_pt,
  v.synopsis_es
from shows s
join (
  values
    (
      1, 1, date '1973-01-01',
      'A renda do aluguel', 'La renta',
      'Seu Madruga está devendo o aluguel e o Senhor Barriga aparece para cobrar. Confusão na vila.',
      'Don Ramón debe la renta y el Señor Barriga llega a cobrar. Se arma un lío en la vecindad.'
    ),
    (
      1, 2, date '1973-01-08',
      'O sanduíche de presunto', 'La torta de jamón',
      'Chaves sonha com um sanduíche de presunto e acaba causando confusão na vila.',
      'El Chavo sueña con una torta de jamón y provoca un caos en la vecindad.'
    ),
    (
      1, 3, date '1973-01-15',
      'A escolinha do Professor Girafales', 'La escuelita del Profesor Jirafales',
      'O Professor Girafales tenta dar aula, mas os alunos e Dona Florinda não facilitam.',
      'El Profesor Girafales intenta dar clase, pero los alumnos y Doña Florinda no lo dejan.'
    )
) as v (
  season,
  episode_number,
  air_date,
  title,
  title_es,
  synopsis_pt,
  synopsis_es
) on true
where s.name = 'Chaves'
on conflict (show_id, season, episode_number) do nothing;

create index if not exists idx_episodes_show_id on episodes (show_id);