# electroservice
Electronics service moved to gradle.

### Task
Zadanie polega na przygotowaniu modułu serwisowania sprzętu elektronicznego.
###### Wytyczne:
   - Sprzęty należą do ściśle zdefiniowanej kategorii - lodówka, telewizor, ...
   - Każdy sprzęt może posiadać dowolną liczbę parametrów - marka, kolor, rozdzielczość itp.
   - Parametry są przypisane do konkretnych sprzętów, a nie do kategorii
   - Dla dowolnego sprzętu na magazynie można zgłosić jego awarię - posiada status sprawny/zepsuty
   - Do sprzętu dodajemy komentarze
###### Zalecane technologie:
   - Spring MVC - typowe operacje CRUD na wyżej wymienionych typach
   - Postgresql 9.4+
   - Spring Boot wiele tu ułatwi.
   - Spring security do zabezpieczenia endpointów aplikacji

#### Postgresql db configration:
- Create database user `greuser` with password `gre123`
- Create database:
    `createdb -h localhost -p 5432 -U greuser electronicsdb`  
    or specify custom database url and user in application.properties.