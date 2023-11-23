# Trip planner

### Startup
* Need to have maven installed
* To verify application tests run command `mvn clean verify`
* To start application run command `mvn spring-boot:run`

### Usage
* Application preloads data to in memory H2 database from [schema.sql](src/main/resources/schema.sql) and [data.sql](src/main/resources/data.sql) 
* You can test API requests in file [requests.http](src/http/requests.http) or use [swagger](http://localhost:8080/swagger-ui/index.html) 
* API accepts query parameters:
  * kilometers, if not provided default value of 20 is used
  * days, if not provided default value of 1 is used
  * month, if not provided current month is used
* API returns trip plan what items you have to take and what amount

### Todo
* Security
* API for items CRUD operations

### Original task 

Labas,
mano vardas Tadas. Aš turiu draugą Matą, kuris yra užkietėjęs keliautojas pėsčiomis. Viskas, ką jis pasiima į kelionę, yra jo šuo Uoga ir
jo kuprinė. Kuprinė būna prigrūsta visokių niekniekių, todėl jam dažnai tenka keliauti tuščiu pilvu. Matas pakvietė mane į 100 km žygį
pėsčiomis. Aš visiškai nenutuokiu, kokių daiktų ir kiek reikėtų pasiimti į šį žygį. Todėl man reikalinga Tavo pagalba. Reikėtų universalios
programėlės/skripto ar ko nors panašaus, kad, įvedęs kilometrų skaičių ir kitą reikalingą informaciją, aš galėčiau gauti kelionei reikalingų
daiktų ir jų kiekių sąrašą. Turbūt pats supranti, kad kelionės metu reikės maitintis, o jei kilometrų skaičius bus didelis, ir kur nors
nakvoti. Be to, programėlė galėtų atsižvelgti ir į metų laiką – vasarą gal reikėtų nepamiršti kepurės nuo saulės, o rudenį skėčio.
Techninių reikalavimų nėra. Visiška laisvė. Jei Tu programuoji front-end technologijomis, rezultatas gali būti web puslapis.
Jei Tu programuoji back-end technologijomis, rezultatas gali būti endpoint'as, kuris priimtų kilometrų skaičių ir kitą reikalingą informaciją,
o atsakymą grąžintų JSON formatu. Jei pasirinksi rašyti skriptą, pirmyn. Jei Tau patinka duomenų bazės, gali panaudoti ir jas. Svarbu tik,
kad galėčiau įvesti pradinius duomenis ir suprasčiau atsakymą.

Aš norėčiau gauti nuorodą į Tavo sukurtos programėlės github ar bitbucket kodo repozitoriją. Bet priimsiu visokiais būdais atsiųstą kodą.
Tik pridėk README failą, kad suprasčiau, kaip naudotis programėle.

Labai Tau ačiū,

Tadas 

