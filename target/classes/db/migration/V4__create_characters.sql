create table
  if not exists characters (
    id uuid primary key,
    name text not null,
    original_name text,
    actor_id uuid not null
  );

create index if not exists idx_character_name on characters (name);

create index if not exists idx_characters_actor_id on characters (actor_id);

create unique index if not exists uq_characters_actor_name on characters (actor_id, name);