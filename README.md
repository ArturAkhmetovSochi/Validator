### Hexlet tests and linter status:
[![Actions Status](https://github.com/ArturAkhmetovSochi/java-project-78/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/ArturAkhmetovSochi/java-project-78/actions)
[![Java CI with Gradle](https://github.com/ArturAkhmetovSochi/java-project-78/actions/workflows/main.yml/badge.svg)](https://github.com/ArturAkhmetovSochi/java-project-78/actions/workflows/main.yml)
[![Maintainability](https://api.codeclimate.com/v1/badges/33ecf3234c76b37fae78/maintainability)](https://codeclimate.com/github/ArturAkhmetovSochi/java-project-78/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/33ecf3234c76b37fae78/test_coverage)](https://codeclimate.com/github/ArturAkhmetovSochi/java-project-78/test_coverage)

Библиотека для проверки корректности данных.  

## Описание
Валидатор функционирует следующим образом: 
* создается объект валидатора;
* создается и настраивается схема проверки данных. Схема представляет собой объект, который содержит правила и ограничения для данных, такие как обязательность заполнения, минимальная и максимальная длина строки и так далее.
Настройка схемы валидации может быть произведена при помощи различных методов, которые предоставляет схема. Разные типы данных проверяются при помощи своих схем;
* проводится проверка данных по настроенным ранее правилам. 

```
var v = new Validator();
var schema = v.string();
schema.isValid("Hello world");
```

## Реализованные схемы проверки данных

###  StringSchema
Вызов метода string() создает схему StringSchema. Эта схема используется для валидации строковых данных. Схема содержит следующий набор методов:
* required() — делает данные обязательными для заполнения. Иными словами добавляет в схему ограничение, которое не позволяет использовать null или пустую строку в качестве значения
* minLength() — добавляет в схему ограничение минимальной длины для строки. Строка должна быть равна или длиннее указанного числа
* contains() — добавляет в схему ограничение по содержимому строки. Строка должна содержать определённую подстроку

```
var v = new Validator();
var schema = v.string().required().minLength(5).contains("Hex");
schema.isValid("Hexlet");
```

### NumberSchema
Вызов метода number() определяет схему NumberSchema. Эта схема используется для валидации чисел.

Схема содержит такой набор методов:
* required() — добавляет в схему ограничение, которое не позволяет использовать null в качестве значения
* positive() — добавляет ограничение на знак числа. Число должно быть положительным
* range() — добавляет допустимый диапазон, в который должно попадать значение числа, включая границы

```
var v = new Validator();
var schema = v.number().required().positive().range(5, 30);
schema.isValid(20);
```

### MapSchema
Вызов метода map() определяет схему MapSchema. Эта схема используется для валидации объектов типа Map.

Схема содержит следующие методы:
* required() — добавляет в схему ограничение, которое не позволяет использовать null в качестве значения. Требуется тип данных Map
* sizeof() — добавляет ограничение на размер мапы. Количество пар ключ-значений в объекте Map должно быть равно заданному
* shape() — используется для определения свойств объекта Map и создания схемы для валидации их значений. Каждому свойству объекта Map привязывается свой набор ограничений (своя схема), что позволяет более точно контролировать данные

```
var v = new Validator();
var schema = v.map().required().sizeof(2);

// shape позволяет описывать валидацию для значений каждого ключа объекта Map
Map<String, BaseSchema<String>> schemas = new HashMap<>();
schemas.put("firstName", v.string().required());
schemas.put("lastName", v.string().required().minLength(2));
schema.shape(schemas);

Map<String, String> human1 = new HashMap<>();
human1.put("firstName", "John");
human1.put("lastName", "Smith");
schema.isValid(human1);
```    
