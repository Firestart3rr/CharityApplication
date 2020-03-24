## Projekt "Oddam w dobre ręce".

Celem projektu jest stworzenie miejsca, w którym każdy będzie mógł oddać niepotrzebne rzeczy zaufanym instytucjom.

## Skąd pomysł na projekt?

-  Użytkownik ma w domu rzeczy, których nie używa, ale są  one w dobrym stanie i chce przekazać je osobom, którym się mogą przydać - nie wie jednak jak w prosty sposób to zrobić.
- Jest wiele dostępnych rozwiązań, ale wiele z nich wymaga dodatkowego wysiłku lub nie budzą one zaufania.
W zweryfikowane miejsca trzeba pojechać, a nie ma na to czasu lub nie ma jak tam pojechać. Natomiast kontenery pod domem lub lokalne zbiórki są niezweryfikowane i nie wiadomo czy te rzeczy faktycznie trafią do potrzebujących. 

#### Uruchamianie aplikacji

Domyślny adres to: localhost:8082

Aby zalogować się jako administrator użyj:
login: admin@admin.com
hasło: zxc

Aby zalogować się jako użytkownik użyj:
login: user@user.com
hasło: asdf

### Wykorzystane technologie

    Spring Boot
    Spring Security
    Spring Data/Jpa
    Spring Form
    MySQL
    Thymeleaf
    Lombok

Już zaimplementowane funkcjonalności

    Landing page, który ma zachęcać do skorzystania z aplikacji.
    role użytkowników (ROLE_USER, ROLE_ADMIN)

    Profil administratora:

    panel administracyjny
    logowanie,
    zarządzanie (CRUD) administratorami,
    zarządzanie (CRUD) zaufanymi instytucjami,
    przeglądanie, blokowanie i usuwanie zarejestrowanych użytkowników,
    przegląd przekazanych darów.
    możliwość określenia statusów darów (czy odebrane)

    Profil użytkownika:

    rejestracja,
    walidacja poprawnego podania dwóch identycznych haseł,
    logowanie,
    edycja własnego profilu,
    dodawanie darów tylko po zalogowaniu,
    przeglądanie, edycja, kasowanie przekazanych darów,
    zaznaczenie, że dar został komuś oddany (archiwizacja),
    potwierdzenie aktywacji konta poprzez wiadomość przesłaną na email podany przy rejestracji.
    
    W trakcie realizacji
    
        obsługa zapomnianych haseł,
        walidacja obsługi administratorów (np. brak możliwości skasowania ostatniego istniejącego administratora),
        Walidacja haseł,
        Testy.

