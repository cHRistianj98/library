# library

Aplikacja serwerowa obsługująca klientów biblioteki z centralą i dwoma oddziałami.

# Endpointy w swagger-ui w środowisku lokalnym

http://localhost:8080/swagger-ui/#/

# Uruchomienie aplikacji w środowisku lokalnym
1. pobierz bazę danych PostgreSQL 14 https://www.postgresql.org/download/
2. utwórz bazę danych o nazwie "library" działającej na porcie 5432
3. utwórz uzytkownika o nazwie "library" mającego prawa do zarządzania stworzoną wcześniej bazą danych
4. dodaj zmienną do VM options: -Dpostgres.password=<library_password>, jako <library_password> wpisz hasło utworzonego wcześniej użytkownika
5. Uruchom aplikację serwerową
