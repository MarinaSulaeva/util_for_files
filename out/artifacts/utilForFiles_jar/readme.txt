﻿_____________________________________
INFO:

Имена файлов можно указывать как до опций, так и после. Расширение входных файлов: ".txt". 
!!! Если в имени входных файлов существуют пробелы, в командной строке требуется заключить название файла в двойные кавычки (Например: "in 1.txt"), иначе считывание произойдёт некорректно.
Чтение и запись файлов по умолчанию производится из корневой папки jar-файла, для изменения папки воспользуйтесь соответствующими опциями -ip и -o.

Доступные опции:
-o Задание директории для результатов. 
	!!! Если в пути существуют пробелы, требуется заключить путь в двойные кавычки, иначе считывание произойдёт некорректно.
	Пример 1: -o "a\a 1\a11" (если внутри проекта). Файлы будут сохранены в ...\[корневой каталог jar-файла]\a\a 1\a11
	Пример 2: -o C:\b\b1 (для любого места на диске)
-p Задание префикса имен выходных файлов (по умолчанию отсутствует)
	!!! Если в префиксе существуют пробелы, требуется заключить их в двойные кавычки, иначе считывание произойдёт некорректно.
	Пример1: -p demo1_ Имя выходного файла: demo1_floats.txt
	Пример2: -p "demo 3_" Имя выходного файла: demo  3_floats.txt
-a режим добавления в существующие файлы (по умолчанию перезаписывает существующие файлы)
-s краткая статистика (количество элементов в созданных файлах)
-f полная статистика [дополняет краткую статистику] (для чисел: кол-во, минимум, максимум, сумма, среднее, для строк: кол-во, максимальная длина строки, минимальная длина строки)
-ip расположение входных файлов (дополнительная опция, нет в задании)
	!!! Если в пути существуют пробелы, требуется заключить путь в двойные кавычки, иначе считывание произойдёт некорректно.
	Пример 1: -ip "i\i 1\i11" (если внутри проекта). Файлы будут взяты из ...\[корневой каталог jar-файла]\i\i 1\i11
	Пример 2: -ip C:\с\с1 (для любого места на диске)
______________________________________