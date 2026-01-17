insert into
  actors (id, name, full_name, dob, dod)
values
  (
    gen_random_uuid (),
    'Roberto Gómez Bolaños',
    'Roberto Gómez Bolaños',
    date '1929-02-21',
    date '2014-11-28'
  ),
  (
    gen_random_uuid (),
    'Florinda Meza',
    'Florinda Meza García',
    date '1949-02-08',
    null
  ),
  (
    gen_random_uuid (),
    'Carlos Villagrán',
    'Carlos Villagrán Eslava',
    date '1944-01-12',
    null
  ),
  (
    gen_random_uuid (),
    'Edgar Vivar',
    'Edgar Ángel Vivar Villanueva',
    date '1948-07-28',
    null
  ),
  (
    gen_random_uuid (),
    'Rubén Aguirre',
    'Rubén Aguirre Fuentes',
    date '1934-06-15',
    date '2016-06-17'
  ),
  (
    gen_random_uuid (),
    'María Antonieta de las Nieves',
    'María Antonieta de las Nieves',
    date '1950-12-22',
    null
  ),
  (
    gen_random_uuid (),
    'Ramón Valdés',
    'Ramón Antonio Esteban Gómez Valdés y Castillo',
    date '1924-09-02',
    date '1988-08-09'
  ),
  (
    gen_random_uuid (),
    'Angelines Fernández',
    'María de los Ángeles Fernández Abad',
    date '1922-07-09',
    date '1994-03-25'
  ) on conflict (name) do nothing;