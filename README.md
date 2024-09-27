Patch Notes: Springboot

-0.9: Phil
-Created the base Springboot setup. This currently can be toggled to read from a DB or from local JSON, and delivers that to localhost:8080/users
--Connects to the DB, but notice DB-specific details should be toggled in src/main/resources/application.properties
--Does not yet work front-end to back-end
-This is still boilerplate code. This is meant to get things running, not yet actively representing our babysitter app.