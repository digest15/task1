# task1

## Занятие 1 Требуется охватить и изучить следующие части
1. Core Java. 
	- Работа с объектами,
	- Использование коллекций, знакомство с  Guava
	- Работа с потоками,
	- Java Reflection API
2. Работа с системой ввода вывода, работа с файлами,
	- Работа с XML. Создание файлов XML и разбор файлов.
	- Знакомство с Json
	- Сериализация.
3. Работа со сборщиком проектов Maven, подключение сторонних jar файлов.
4. Паттерны программирования.

## Практическая работа.

### Требуется написать консольное приложение для автоматизации работы Автосервисов по ремонту автомобилей. Оно должно включать следующие объекты которые представляют собой простые Java объекты POJO: 
1. Автосервис
	- Наименование
	- Адрес
	- Время открытия
	- Время закрытия
2. Автомобиль (Грузовой, Легковой, Пассажирский)
	- Наименование комплектации
	- VIN 
	- Год выпуска
	- Цвет
		- Код цвета
		- Наименование
	- Тип кузова (Перечисление)
	- Марка
		- Производитель
	- Для грузового - количество мостов, грузоподъемность +
	- Для пассажирского - количество мест +
3. Ремонт
	- Дата и время
	- Автосервис
	- Автомобиль
	- Стоимость
	- Автослесарь
		- Имя
		- Фамилия
		- Зарплата 

### При разработки нужно охватить следующие аспекты:
1. Приложение консольное. Для работы с подключаемыми библиотеками используем Apache Maven.
2. В итоге должно получится приложение которое можно запустить из командной строки.
3. При проектировании классов по возможности использовать все возможности языка и ООП.
4. При первом запуске приложения необходимо заполнить справочные данные для программы с использованием консоли.
	- Сохранить эти данные и при следующем запуске их загружать. Хранение данных следует реализовать следующими методами:
		- В простых  текстовых файлах
		- С использованием Сериализации
		- В формате XML. Создание с использованием DOM, разбор с использованием SAX.
		- В формате JSON
		- Продумать архитектору классов выгрузки что бы использовать только интерфейсы, реализация каждого способа должна быть прозрачна для программы и взаимно заменяема.
		- Загрузку и выгрузку осуществляем с использованием потоков. Каждый справочник загружаем отдельно, в отдельном потоке.
	- Загрузку и выгрузку в простые файлы реализуем с помощью Java Reflection API.
5. Объекты ремонты следует создавать с использованием генератора случайных чисел, то есть заполнять все поля случайными числами.
6. После заполнения тестовыми данными как требуется вывести на консоль следующие данные (работа с коллекциями):
	- Ремонты отсортированные по дате, по стоимости. Как по возрастанию так и по убыванию, 
	- Найти и вывести самый часто ломающийся автомобиль,
	- Вывести марки автомобилей и количество поломок,
	- Подсчитать сумму затрат по маркам автомобиля и посчитать доходы автосервисов по кварталам,
	- Создать Excel файл со списком сотрудников по автосервисам и их зарплатой,
	- Дополнительно: Создать PDF файл с информацией об автосервисе и его сотрудниках


	


  
