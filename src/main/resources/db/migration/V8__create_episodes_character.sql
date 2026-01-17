-- =========================================
-- N:N entre episodes e characters
-- =========================================

create table if not exists episode_characters (
  episode_id uuid not null references episodes(id) on delete cascade,
  character_id uuid not null references characters(id) on delete cascade,

  primary key (episode_id, character_id)
);

create index if not exists idx_episode_characters_episode
  on episode_characters (episode_id);

create index if not exists idx_episode_characters_character
  on episode_characters (character_id);



-- INSERTS EXEMPLOS

-- Episódio 1: A renda do aluguel
insert into episode_characters (episode_id, character_id)
select e.id, c.id
from episodes e
join characters c on c.name in ('Chaves', 'Seu Madruga', 'Dona Florinda', 'Quico', 'Professor Girafales')
where e.show_id = (select id from shows where name = 'Chaves') and e.season = 1 and e.episode_number = 1
on conflict do nothing;

-- Episódio 2: O sanduíche de presunto
insert into episode_characters (episode_id, character_id)
select e.id, c.id
from episodes e
join characters c on c.name in ('Chaves', 'Quico', 'Dona Florinda')
where e.show_id = (select id from shows where name = 'Chaves') and e.season = 1 and e.episode_number = 2
on conflict do nothing;

-- Episódio 3: A escolinha
insert into episode_characters (episode_id, character_id)
select e.id, c.id
from episodes e
join characters c on c.name in ('Chaves', 'Quico', 'Professor Girafales', 'Dona Florinda')
where e.show_id = (select id from shows where name = 'Chaves') and e.season = 1 and e.episode_number = 3
on conflict do nothing;

--Force Update
--123