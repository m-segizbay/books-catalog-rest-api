# Краткое описание

Репозиторий содержит исходный код тестового задания для стажировки в *U-FUTURE* на позицию Java Developer(Стажер).

По техническому заданию необходимо было разработать сервис, который сможет получать, хранить и отдавать информацию о книгах.

* Трехслойная архетиктура [Controller](src/main/java/kz/segizbay/bookscatalog/controllers) - [Service](src/main/java/kz/segizbay/bookscatalog/service) - [Repository](src/main/java/kz/segizbay/bookscatalog/repository)

# Стек

* Java 17
* Spring Boot, Data Jpa, Validator, FlyWay
* Docker
* PostgreSQL

Приложение упаковано в **Docker** контейнер. Как запустить, см. ниже.

# База данныхых 

Используется [**FlyWay**](src/main/resources/db/migration/V1__Initial_setup.sql) который автоматический создает таблицу и заполняет их начальными данными.

# Как запустить?

Копируем репозиторий 

``` bash
git clone https://github.com/nisib/bookstore.git
```

## Без Docker

У вас должен быть установлен **PostgreSQL**. В файле конфигураций [application.yaml](src/main/resources/application.yaml) записываем свои данные: 
* ${DB_URL} - Адрес для подключение к базе данных(jdbc:postgresql://localhost:5432/postgres (postgres-автоматический создается после установки))
* ${DB_USERNAME} - Свой логин(при установке или же создать его) 
* ${DB_PASSWORD} - Пароль для базы данных

Сначала нужно собрать проект с помощью Maven(Для такого что бы не выбрасывало исключение нужно добавить -DskipTests. Потому что в проекте отсуствует Тесты)
```shell 
maven package -DskipTests
``` 
После запускаем собранный проект через терминал(Мы должны находиться в папке с проектом) 
```shell 
# booksCatalog-0.0.1-SNAPSHOT.jar - название файла который уже собрался. Он находится в файле target
java -jar .\target\booksCatalog-0.0.1-SNAPSHOT.jar 
```

### Запуск в Docker
У вас должен установлен Docker на ПК и запушен

Через Maven так же нужно собрать проект(Если вы не собирали):
```shell 
maven package -DskipTests
``` 
Запускаем docker-compose

```shell
#-d для того что бы они работали в фоновом режиме(логи не флудили в терминал)
docker-compose up -d
```

# Реализованные API

### POST /books

Endpoint для получение всех книг которые имеются. В базе данных уже имеется некоторое количество книг 

* Метод: `POST`
* Дополнительные параметры стартовой строки: нет
* Тело запроса: должно содержать данные об операции в формате json:

```json
{
  "title": "Великий Гэтсби",
  "author": "Фрэнсис Скотт Фицджеральд",
  "price": 800
}
```

Response при успешной обработке:

* HTTP status: 201 Created
* Тело: объект транзакции

```json
{
  "id": 1,
  "title": "Великий Гэтсби",
  "author": "Фрэнсис Скотт Фицджеральд",
  "price": 800
}
```

Если такая книга существует кидает ошибку:

```json
{
  "message": "title - Книга с таким названием уже существует!; ",
  "timestamp": 1727313932349
}
```

### GET /books

Endpoint для получения сведений о всех книгах. *В ТЗ не было требований по сортировке, но все таки сделал что бы удобно было искать книги(Сортировка через id).*

* Метод: `GET`
* Дополнительные параметры стартовой строки: нет
* Тело запроса: нет

Response при успешной обработке:

* HTTP status: 200 Ok
* Тело: массив транзакций

```json
[
  {
    "id": 1,
    "title": "Великий Гэтсби",
    "author": "Фрэнсис Скотт Фицджеральд",
    "price": 800
  },
  {
    "id": 2,
    "title": "1984",
    "author": "Джордж Оруэлл",
    "price": 600
  },
  {
    "id": 3,
    "title": "Убить пересмешника",
    "author": "Харпер Ли",
    "price": 750
  }
]
```

Если в базе данных нету книг передает пустой массив:
```json
[ ]
```

### GET /books/id

Endpoint для получения сведений о конкретной книге по её id.

* Метод: `GET`
* Дополнительные параметры стартовой строки: id(например-1)
* Тело запроса: нет

Response при успешной обработке:

* HTTP status: 200 ok
* Тело: объект транзакции 

```json
{
  "id": 1,
  "title": "Великий Гэтсби",
  "author": "Фрэнсис Скотт Фицджеральд",
  "price": 800
}
```

Если дать id который не существует кидает ошибку:

```json
{
  "message": "Книга с таким id(100) не найдено",
  "timestamp": 1727313843914
}
```

### PUT /id

Endpoint для получения сведений о всех транзакциях, по которым превышен лимит.

* Метод: `PUT`
* Дополнительные параметры стартовой строки: id(например 1)
* Тело запроса: 

```json
{
  "title": "1984",
  "author": "Джордж Оруэлл",
  "price": 600
}
```

Response при успешной обработке:

* HTTP status: 200 OK
* Тело: Объект который был обновлен

```json
{ 
  "id": 1,
  "title": "1984",
  "author": "Джордж Оруэлл",
  "price": 600
}
```

Если такая книга существует кидает ошибку:

```json
{
  "message": "title - Книга с таким названием уже существует!; ",
  "timestamp": 1727313932349
}
```

### DELETE /id

Endpoint для получения сведений о всех транзакциях, по которым превышен лимит.

* Метод: `DELETE`
* Дополнительные параметры стартовой строки: id(например 1)
* Тело запроса: нету

Response при успешной обработке:

* HTTP status: 200 OK
* Тело: Объект который был удален

```json 
{
    "id": 1,
    "title": "Великий Гэтсби 2",
    "author": "Фрэнсис Скотт Фицджеральд",
    "price": 800
}
```

## Swagger-UI: http://localhost:8080/swagger-ui/index.html




