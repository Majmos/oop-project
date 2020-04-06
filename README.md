# Propozycje projektu

## Symulacja ekosystemu

Symulacja środowiska naturalnego, składającego się ze zwierząt, roślin, etc.

Świat będzie dwuwymiarową siatką. Każda z komórek siatki będzie posiadała pewne właściwości a także będzie mogła
zawierać jakiś organizm (roślinę lub zwierzę).

### Rośliny

Rośliny będą ogranizmami bardziej pasywnymi, tzn. będą reagowały na zmiany środowiska w pewnym, tudzież ograniczonym,
stopniu, np. jeżeli środowisko będzie miało odpowiednie nasłonecznienie i wilgotność, będą rosnąć; w przeciwnym wypadku,
będą umierać. Będą miały również bardzo ograniczoną możliwość wpływania na środowisko czy inne organizmy.

### Zwierzęta

Elementami wyróżniającymi organizmy zwierzęce będzie przede wszystkim możliwość zmiany swojej pozycji oraz większy
zakres wpływu na swoje środowisko. Zwierzę będzie musiało w sposób aktywny zaspokajać swoje potrzeby fizjologiczne:
głód, odpoczynek, etc. W sposób aktywny, tj. podejmować kroki do osiągnięcia celu, np. szukając pożywienia, unikać
potencjalnych zagrożeń, etc.

Każdy gatunek będzie miał określone statystyki: wytrzymałość, siła i szybkość. Statystyki zwierząt tego samego gatunku
mogą się delikatnie różnić. Jeśli wytrzymałość spadnie do 0 to osobnik umiera. Wytrzymałość można stracić podczas
konfrontacji z innymi zwierzętami oraz można ją odzyskać odpoczywając i jedząc. Siła odpowiada za to jak dobrze zwierze
radzi sobie w walce, a szybkość jak sprawnie przemieszcza się po świecie.

Będą różne typy zwierząt różniące się pod wieloma względami: roślinożercy i mięsożercy, samiec i samica, młode i
dorosłe, jak dużo potomstwa będą produkować, jakich strategii będą używać aby przeżyć, etc.

Ze względu na rozdzielczość symulacji (jedna komórka reprezentująca powierzchnię 10-100m^2) nie będą symulowane
zwierzęta małe jak np. owady lub będą one symulowane w sposób pasywny (np. jak rośliny lub jako właściwość komórki).

## System POP

> Symulacja populacji w stylu gry [Stellaris](https://store.steampowered.com/app/281990/Stellaris/)

Szczegółowy opis systemu populacji znajduje się na [stronie wiki gry](https://stellaris.paradoxwikis.com/Population).
Niniejszy opis definiuje zakres systemu który będzie zaimplementowany.

Symulacja będzie się składać ze światów, które będą zawierać jednostki populacji, które będą wykonywać prace i
produkować zasoby.

### Zakres symulacji

Ponieważ system znajdujący się w grze jest dość duży, zaimplementowany zostanie tylko jej podzbiór.

Zaimplementowane będą:

- [Demographics](https://stellaris.paradoxwikis.com/Population#Demographics) (pop growth, pop assembly, pop decline,
  migration)
- [Happiness](https://stellaris.paradoxwikis.com/Population#Demographics)
- [Upkeep](https://stellaris.paradoxwikis.com/Population#Demographics)
- [Stratum](https://stellaris.paradoxwikis.com/Population#Stratum) (Ruler, Specialist, Worker, Criminal)
- [Slavery](https://stellaris.paradoxwikis.com/Population#Slavery) (tylko roboty)

Poza zakresem (nie będą zaimplementowane):

- [Purges](https://stellaris.paradoxwikis.com/Population#Purges)
- [Refugees](https://stellaris.paradoxwikis.com/Population#Refugees)

### Modelowanie zewnętrznych elementów

System populacji działa w grupie z innymi elementami i mechanikami gry, takimi jak:

- kolonizacja (tworzenie nowych światów)
- wynajdywanie nowych technologii (zmiany właściwości dystryktów, nowe opcje ulepszania budynków, zmiany właściwości
  popów, nowe modyfikatory, etc.)
- budowanie i ulepszanie budynków przez gracza (nowe miejsca pracy)
- wyprodukowane dobra zwiększają globalny (jeden dla wielu światów) licznik zasobów (mogą to być fizyczne dobra, jak
  materiały konsumpcyjne lub niefizyczne, jak punkty badań)

Niektóre z tych elementów są "wejściem" (interakcje gracza) a inne "wyjściem" (otrzymane zasoby). Aby zapewnić możliwość
interakcji z symulacją, wykonany zostanie prosty, tekstowy interfejs.
