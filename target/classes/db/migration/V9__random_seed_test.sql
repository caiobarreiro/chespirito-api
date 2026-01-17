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

insert into
    characters (id, actor_id, name, original_name)
select
    gen_random_uuid (),
    a.id,
    'Alma Negra',
    'Alma Negra'
from
    actors a
where
    a.name = 'Ramón Valdés' on conflict do nothing;

insert into
    characters (id, actor_id, name, original_name)
select
    gen_random_uuid (),
    a.id,
    'Quase Nada',
    'El Cuajinais'
from
    actors a
where
    a.name = 'Carlos Villagrán' on conflict do nothing;






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
      4, 1, date '1976-01-01',
      'Quem perde a guerra, pede água!', ' las guerras médicas, la bayoneta se llamaba bisturí',
      'No meio de uma guerra, o capitão toma a garrafa dágua do sargento, a única restante em todo o campo de batalha. Enquanto isso, o sargento enlouquece e o Chapolin é chamado.',
      'En medio de una guerra, el capitán le quita la cantimplora de agua al sargento, la única que queda en todo el campo de batalla. Mientras tanto, el sargento enloquece y se llama al Chapulín.'
    ),
    (
      1, 19, date '1973-07-04',
      'Uma Investigação Perigosa', 'La falsa muerte del malhechor',
      'Quase Nada se finge de morto, para poder cometer crimes sem que a polícia desconfie. E o único que acaba com seus planos é o Chapolin Colorado que é burro mas sempre acaba desvendando todos os crimes com sua frase de vitória "Não contavam com minha astucia".',
      'El Chavo sueña con una torta de jamón y provoca un caos en la vecindad.'
    ),
    (
      3, 13, date '1975-03-27',
      'Os Piratas - Parte 1', 'Los Antiguos Piratas Del Caribe, Solo De Vez En Cuando Desviaban a Cuba - Parte 1',
      'Um grupo de piratas canta uma canção até a chegada de seu líder, o pirata Alma Negra (Ramón Valdez), que convoca os piratas Pança Louca (Edgar Vivar) e Lagartixa (Carlos Villagrán) para ajudá-lo a enterrar seu tesouro. Os dois chamam o Chapolin Colorado. Eles explicam que, para que ninguém saiba onde o tesouro foi enterrado, Alma Negra mata quem o ajudou. A garçonete (Florinda Meza) oferece uma mesa ao herói. Eles dizem que acabaram de regressar de um saque e que mataram 40 prisioneiros. Eles prometem que viverão uma vida limpa. Matadouro (Ruben Aguirre), o braço direito do Alma Negra aparece. Ele é o responsável por matar quem enterra o tesouro. Ele diz que Alma Negra quer vê-lo. Chapolin decide ir embora, mas Alma Negra aparece. Ele ordena que o herói encha um copo de vinho. Ele o faz, mas acaba derrubando vinho pelo chão. Enquanto Alma bebe, Chapolin quebra uma cadeira em sua cabeça, mas ele não cai. Chapolin tira o copo de sua mão e ele cai. Depois de falar com Matadouro, Chapolin percebe que Alma Negra acordou e o está encarando. Ele diz que o matará. Matadouro intimida o herói. Carlos diz que, junto com Chapolin, eles podem bater no Matadouro. Chapolin diz que apenas Carlos o fará. Enquanto Alma Negra bate em Matadouro para mostrar o quanto ele é resistente, Chapolin bate em Carlos. O herói engana Alma Negra e rouba sua arma, tendo-a tomada instantes depois. Chapolin, Lagartixa e Pança Louca são presos numa cela, com bolas de aço presas à suas pernas. Florinda aparece e dá um martelo para quebrar as correntes, já que está apaixonada por Pança Louca. Depois de martelar os próprios dedos, Chapolin consegue soltar a todos. Eles acham uma pedra falsa e todos fogem pelo buraco, apenas para se verem na mira da arma de Alma Negra. Florinda diz que devem esperar a continuação.',
      'Un grupo de piratas canta una canción hasta la llegada de su líder, el pirata Alma Negra (Ramón Valdés), quien convoca a los piratas Panza Loca (Edgar Vivar) y Lagartija (Carlos Villagrán) para ayudarlo a enterrar su tesoro. Ambos llaman al Chapulín Colorado. Explican que, para que nadie sepa dónde fue enterrado el tesoro, Alma Negra mata a quienes lo ayudan. La mesera (Florinda Meza) le ofrece una mesa al héroe. Ellos dicen que acaban de regresar de un saqueo y que mataron a 40 prisioneros, y prometen que llevarán una vida limpia. Aparece Matadero (Rubén Aguirre), la mano derecha de Alma Negra, encargado de matar a quienes entierran el tesoro. Él dice que Alma Negra quiere verlo. Chapulín decide irse, pero Alma Negra aparece y le ordena al héroe que llene una copa de vino. Chapulín lo hace, pero termina derramando el vino en el suelo. Mientras Alma bebe, Chapulín rompe una silla en su cabeza, pero este no cae. Chapulín le quita la copa de la mano y entonces Alma cae. Después de hablar con Matadero, Chapulín se da cuenta de que Alma Negra ha despertado y lo está mirando. Él dice que lo matará, mientras Matadero intimida al héroe. Carlos dice que, junto con Chapulín, pueden vencer a Matadero, pero Chapulín responde que solo Carlos lo hará. Mientras Alma Negra golpea a Matadero para demostrar lo resistente que es, Chapulín golpea a Carlos. El héroe engaña a Alma Negra y le roba su arma, aunque esta le es arrebatada instantes después. Chapulín, Lagartija y Panza Loca son encerrados en una celda, con bolas de acero atadas a sus piernas. Florinda aparece y le entrega un martillo para romper las cadenas, ya que está enamorada de Panza Loca. Tras golpearse los propios dedos, Chapulín logra liberarlos a todos. Encuentran una piedra falsa y todos huyen por el agujero, solo para quedar a merced del arma de Alma Negra. Florinda dice que deberán esperar la continuación.'
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
where s.name = 'Chapolin'
on conflict (show_id, season, episode_number) do nothing;



insert into episode_characters (episode_id, character_id)
select e.id, c.id
from episodes e
join characters c on c.name in ('Chapolin Colorado')
where e.show_id = (select id from shows where name = 'Chapolin') and e.season = 4 and e.episode_number = 1
on conflict do nothing;

insert into episode_characters (episode_id, character_id)
select e.id, c.id
from episodes e
join characters c on c.name in ('Chapolin Colorado', 'Quase Nada')
where e.show_id = (select id from shows where name = 'Chapolin') and e.season = 4 and e.episode_number = 19
on conflict do nothing;

insert into episode_characters (episode_id, character_id)
select e.id, c.id
from episodes e
join characters c on c.name in ('Chapolin Colorado', 'Alma Negra')
where e.show_id = (select id from shows where name = 'Chapolin') and e.season = 3 and e.episode_number = 13
on conflict do nothing;