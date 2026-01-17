-- Chaves — Roberto Gómez Bolaños
insert into
    characters (id, actor_id, name, original_name)
select
    gen_random_uuid (),
    a.id,
    'Chaves',
    'El Chavo del Ocho'
from
    actors a
where
    a.name = 'Roberto Gómez Bolaños' on conflict do nothing;

insert into
    characters (id, actor_id, name, original_name)
select
    gen_random_uuid (),
    a.id,
    'Chapolin Colorado',
    'El Chapulín Colorado'
from
    actors a
where
    a.name = 'Roberto Gómez Bolaños' on conflict do nothing;

-- Dona Florinda — Florinda Meza
insert into
    characters (id, actor_id, name, original_name)
select
    gen_random_uuid (),
    a.id,
    'Dona Florinda',
    'Doña Florinda'
from
    actors a
where
    a.name = 'Florinda Meza' on conflict do nothing;

-- Quico — Carlos Villagrán
insert into
    characters (id, actor_id, name, original_name)
select
    gen_random_uuid (),
    a.id,
    'Quico',
    'Quico'
from
    actors a
where
    a.name = 'Carlos Villagrán' on conflict do nothing;

-- Senhor Barriga — Edgar Vivar
insert into
    characters (id, actor_id, name, original_name)
select
    gen_random_uuid (),
    a.id,
    'Senhor Barriga',
    'Señor Barriga'
from
    actors a
where
    a.name = 'Edgar Vivar' on conflict do nothing;

-- Professor Girafales — Rubén Aguirre
insert into
    characters (id, actor_id, name, original_name)
select
    gen_random_uuid (),
    a.id,
    'Professor Girafales',
    'Profesor Jirafales'
from
    actors a
where
    a.name = 'Rubén Aguirre' on conflict do nothing;

-- Chiquinha — María Antonieta de las Nieves
insert into
    characters (id, actor_id, name, original_name)
select
    gen_random_uuid (),
    a.id,
    'Chiquinha',
    'La Chilindrina'
from
    actors a
where
    a.name = 'María Antonieta de las Nieves' on conflict do nothing;

-- Seu Madruga — Ramón Valdés
insert into
    characters (id, actor_id, name, original_name)
select
    gen_random_uuid (),
    a.id,
    'Seu Madruga',
    'Don Ramón'
from
    actors a
where
    a.name = 'Ramón Valdés' on conflict do nothing;

-- Dona Clotilde — Angelines Fernández
insert into
    characters (id, actor_id, name, original_name)
select
    gen_random_uuid (),
    a.id,
    'Dona Clotilde',
    'Dona Clotilde'
from
    actors a
where
    a.name = 'Angelines Fernández' on conflict do nothing;