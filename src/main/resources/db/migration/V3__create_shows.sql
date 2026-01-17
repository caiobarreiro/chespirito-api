-- =========================================
-- Tabela de séries (Chaves, Chapolin, etc)
-- =========================================
create table
  if not exists shows (
    id uuid primary key default gen_random_uuid (),
    name text not null, -- português
    name_es text not null, -- espanhol
    start_date date not null,
    end_date date
  );

-- evita duplicar o mesmo show
alter table shows add constraint uq_shows_name unique (name, name_es);

insert into
  shows (name, name_es, start_date, end_date)
values
  (
    'Chaves',
    'El Chavo del Ocho',
    date '1973-01-01',
    date '1980-12-31'
  ),
  (
    'Chapolin',
    'El Chapulín Colorado',
    date '1970-01-01',
    date '1979-12-31'
  ),
  (
    'Chespirito',
    'Chespirito',
    date '1980-01-01',
    date '1995-12-31'
  ) on conflict (name, name_es) do nothing;