create table if not exists actors (
  id uuid primary key,
  name text not null,
  full_name text,
  dob date,
  dod date
);

create unique index if not exists uq_actors_name
on actors (name);
