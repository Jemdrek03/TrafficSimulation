# Symulacja Skrzyżowania – Projekt Java

## Opis

Ten projekt w Javie to graficzna symulacja skrzyżowania drogowego z dynamicznymi pieszymi, samochodami oraz sygnalizacją świetlną. Wykorzystuje biblioteki Swing oraz AWT do renderowania grafiki i zarządzania zdarzeniami. Główne komponenty projektu odpowiadają za modelowanie ruchu pojazdów i pieszych oraz kontrolowanie świateł na skrzyżowaniu.

## Funkcje

- Symulacja skrzyżowania z pasami, chodnikami, przejściami dla pieszych i światłami.
- Dynamicznie poruszające się pojazdy i piesi.
- Reagowanie na sygnalizację świetlną.
- Kolizje między obiektami są wykrywane i obsługiwane.
- Cykl świateł zmienia się co 7 sekund.
- Losowe pojawianie się pojazdów i pieszych co 5 sekund.

## Struktura katalogów

```
├── app
│   └── App.java                # Punkt wejścia do aplikacji
├── drawable
│   ├── Kanwa.java             # Panel rysujący wszystkie elementy graficzne
│   └── MainFrame.java         # Główna ramka aplikacji
├── immovable
│   ├── Crosswalk.java         # Przejście dla pieszych ze światłami
│   ├── Intersection.java      # Logika i rysowanie skrzyżowania
│   ├── Lane.java              # Pas ruchu z informacją o kierunku i światłach
│   ├── Lights.java            # Sygnalizacja świetlna
│   ├── Sidewalk.java          # Chodnik dla pieszych
│   └── Spawn.java             # Generator pozycji startowych
├── movable
│   ├── Car.java               # Logika poruszania się samochodu
│   ├── Pedestrian.java        # Logika poruszania się pieszego
│   └── MovingObjects.java     # Klasa bazowa dla obiektów ruchomych
```

## Autor

Projekt demonstracyjny symulacji ruchu drogowego na potrzeby kursu Języki Programowania na 3 semestrze kierunku Informatyka Techniczna na PWR.

Jemdrek03