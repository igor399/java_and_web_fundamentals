### 1. Что такое лямбда-выражение?  
**Ответ.**  
Лямбда-выражение — это короткий блок кода, который принимает параметры и 
возвращает значение. Лямбда-выражения похожи на методы, но им не нужно имя, и 
их можно реализовать прямо в теле метода.  
Выражения ограничены. Они должны немедленно возвращать значение и не могут 
содержать переменных, присваиваний или утверждений, таких как if или for. Для 
выполнения более сложных операций можно использовать блок кода с фигурными 
скобками. Если лямбда-выражение должно возвращать значение, то в блоке кода 
должен быть оператор возврата.  
Лямбда-выражения можно хранить в переменных, если тип переменной представляет 
собой интерфейс, который имеет только один метод. Лямбда-выражение должно иметь 
то же количество параметров и тот же тип возвращаемого значения, что и этот метод. 
Java имеет множество встроенных интерфейсов такого типа, например интерфейс 
Consumer (находящийся в пакете java.util), используемый списками.  
**Источник.** <https://www.w3schools.com/java/java_lambda.asp>


### 2. Из каких элементов состоит объявление лямбда-выражения?  
**Ответ.**  
Основу лямбда-выражения составляет лямбда-оператор, который представляет стрелку ->. 
Этот оператор разделяет лямбда-выражение на две части: левая часть содержит список 
параметров выражения, а правая собственно представляет тело лямбда-выражения, 
где выполняются все действия.  
**Источник.** <https://metanit.com/java/tutorial/9.1>


### 3. При объявлении лямбда-выражения какие структуры можно опустить?  
**Ответ.**  
* Вы можете опустить имя этого метода при его реализации.  
* Вы можете опустить тип данных параметров в лямбда-выражении.  
* Вы можете опустить скобки, если есть только один параметр.  
**Источник.** <https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax>


### 4. Что такое поток и конвейер в контексте лямбда-выражения?  
**Ответ.**  
Поток - это ключевая абстрактная концепция для обработки коллекций в Java 8. 
Он может указывать операции, которые вы хотите выполнить с коллекцией, и может 
выполнять очень сложные операции, такие как поиск, фильтрация и сопоставление 
данные. Использование Stream API для управления данными коллекции аналогично 
использованию SQL для выполнения запросов к базе данных. Вы также можете 
использовать Stream API для параллельного выполнения операций. Короче говоря, 
Stream API обеспечивает эффективный и простой в использовании способ обработки данных.  
**Источник.** <https://russianblogs.com/article/30281576017/>


### 5. Какие компоненты содержит конвейер?  
**Ответ.**  
У полного потокового конвейера есть несколько компонентов: источник (который 
может быть a Collection, массив, функция генератора, или канал IO); нуль или 
больше промежуточных операций такой как Stream.filter или Stream.map; и 
терминальная работа такой как Stream.forEach или java.util.stream.Stream.reduce  
**Источник.** <https://spec-zone.ru/RU/Java/Docs/8/api/java/util/stream/package-summary.html>
  

### 6. Что такое агрегатные операции? Приведите примеры агрегатных операций.  
**Ответ.**  
Агрегатные операции обрабатывают элементы из потока.  
Агрегатные операции – Stream поддерживает агрегатные операции, такие как 
фильтрация, отображение, ограничение, уменьшение, поиск, сопоставление и т.д.  

* Получить источник объектов:	Stream<E> stream()  
* Фильтровать объекты, соответствующие объекту Predicate:	Stream<T> filter(Predicate<? super T> predicate)  
* Сопоставьте объекты с другим значением, указанным в объекте Function:	<R> Stream<R> map(Function<? super T,? extends R> mapper)  
* Выполнить действие, указанное объектом Consumer:	void forEach(Consumer<? super T> action)   
**Источник.** <https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html>


### 7. Какие различия между агрегатными операциями и итераторами?  
**Ответ.**  
Агрегатные операции, такие как forEach, кажутся итераторами. 
Однако они имеют несколько принципиальных отличий:  
* Они используют внутреннюю итерацию: агрегатные операции не содержат такого метода, 
  как next, который дает им указание обработать следующий элемент коллекции. При 
  внутреннем делегировании ваше приложение определяет, какую коллекцию оно итерирует, 
  но JDK определяет, как итерировать коллекцию. При внешней итерации ваше приложение 
  определяет и то, какую коллекцию оно итерирует, и то, как оно это итерирует. 
  Однако внешняя итерация может выполнять итерацию только по элементам коллекции 
  последовательно. Внутренняя итерация не имеет этого ограничения. Он может легче 
  использовать преимущества параллельных вычислений, которые включают разделение 
  проблемы на подзадачи, одновременное решение этих проблем и последующее объединение 
  результатов решений подзадач.  
* Они обрабатывают элементы из потока: агрегатные операции обрабатывают элементы 
  из потока, а не непосредственно из коллекции. Следовательно, они также называются 
  потоковыми операциями.  
* Они поддерживают поведение в качестве параметров: вы можете указать 
  лямбда-выражения в качестве параметров для большинства агрегатных операций. 
  Это позволяет настроить поведение конкретной агрегатной операции.  
**Источник.** <https://docs.oracle.com/javase/tutorial/collections/streams/index.html#differences>
  

### 8. Какие имеются ограничения на локальные переменные, которые используются в лямбда-выражениях?  
**Ответ.**  
Однако, подобно локальным и анонимным классам, лямбда-выражение может обращаться 
только к локальным переменным и параметрам включающего блока, которые являются 
final или effectively final.  
**Источник.** <https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#accessing-local-variables>


### 9. Что такое целевой тип лямбда-выражения и как компилятор определяет целевой тип?  
**Ответ.**  
Целевым типом лямбда-выражения называется тип контекста, в котором это выражение встречается.
Чтобы определить тип лямбда-выражения, компилятор Java использует целевой тип 
контекста или ситуации, в которой было найдено лямбда-выражение. Из этого следует,
что вы можете использовать лямбда-выражения только в ситуациях, когда компилятор 
Java может определить целевой тип.  
**Источник.** <https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#target-typing>


### 10. В каких ситуациях может использоваться лямбда-выражение?  
**Ответ.**  
* Variable declarations  
* Assignments  
* Return statements  
* Array initializers  
* Method or constructor arguments  
* Lambda expression bodies  
* Conditional expressions, ?:  
* Cast expressions  
**Источник.** <https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#target-typing>
  

### 11. Могут ли лямбда-выражения ссылаться на другие существующие методы? Если да - приведите пример.  
**Ответ.**  
лямбда-выражение не делает ничего, кроме вызова существующего метода. 
В таких случаях часто проще обратиться к существующему методу по имени. 
Ссылки на методы позволяют это сделать; это компактные, легко читаемые 
лямбда-выражения для методов, у которых уже есть имя.  
````java
public class Person {

    // ...

    LocalDate birthday;

    public int getAge() {
        // ...
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public static int compareByAge(Person a, Person b) {
        return a.birthday.compareTo(b.birthday);
    }

    // ...
}
Arrays.sort(rosterAsArray,
        (Person a, Person b) -> {
        return a.getBirthday().compareTo(b.getBirthday());
        }
        );
````  
**Источник.** <https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html>


### 12. Укажите виды ссылок на методы в контексте лямбда-выражений и приведите соответствующие пример.  
**Ответ.**  
* Ссылка на статический метод - 	ContainingClass::staticMethodName - Person::compareByAge MethodReferencesExamples::appendStrings
* Ссылка на метод экземпляра конкретного объекта -	containingObject::instanceMethodName	myComparisonProvider::compareByName  myApp::appendStrings2
* Ссылка на метод экземпляра произвольного объекта определенного типа -	ContainingType::methodName	String::compareToIgnoreCase String::concat
* Ссылка на конструктор - ClassName::new -	HashSet::new  
**Источник.** <https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html>


### 13. Что такое операции сокращения в контексте лямбда-выражений?  
**Ответ.**  
JDK содержит множество терминальных операций (таких как среднее, сумма, 
минимальное, максимальное и подсчет), которые возвращают одно значение путем 
объединения содержимого потока. Эти операции называются операциями редукции. 
JDK также содержит операции сокращения, которые возвращают набор вместо одного 
значения. Многие операции редукции выполняют определенную задачу, например 
нахождение среднего значения или группировку элементов по категориям.  
**Источник.** <https://docs.oracle.com/javase/tutorial/collections/streams/reduction.html>


### 14. Чем метод reduce отличается от метода collect в контексте лямбда-выражений?  
**Ответ.**  
В отличие от метода reduce, который всегда создает новое значение при обработке 
элемента, метод collect изменяет или видоизменяет существующее значение.  
**Источник.** <https://docs.oracle.com/javase/tutorial/collections/streams/reduction.html>


### 15. Укажите, какие преимущества дает использование класса Optional?  
**Ответ.**  
Java 8 представила новый класс Optional в пакете java.util. Он используется для 
обозначения наличия или отсутствия значения. Основное преимущество этой новой 
конструкции заключается в том, что больше нет слишком большого количества 
проверок null и исключений NullPointerException. Это позволяет избежать любых 
исключений NullPointerException во время выполнения и помогает нам разрабатывать 
чистые и аккуратные Java API или приложения. Подобно коллекциям и массивам, 
это также контейнер, в котором может храниться не более одного значения.  
Advantages of Java 8 Optional:  
* Null checks are not required.  
* No more NullPointerException at run-time.  
* We can develop clean and neat APIs.  
* No more Boiler plate code  
**Источник.** <https://mkyong.com/java8/java-8-optional-in-depth/>


### 16. Выполните вывод каждого элемента коллекции collect с помощью метода forEach (), применяя:итератор, поток  
**Ответ.**  
````java
collect.iterator().forEachRemaining(System.out::println);
collect.forEach(System.out::println);
````


### 17. Выполните вывод каждого элемента Map collect с помощью java 8.  
**Ответ.**  
```java
collect.entrySet().forEach(System.out::println);
```

### 18. Допишите сортировку коллекции users по имени с помощью метода getName() и вывод всех элементов коллекции в консоль (класс User приводить не обязательно).  
```java
public class Main {
public static void main(String[] args) {
List<User> users = new ArrayList<>();
users.add(new User("Nick", "Boll"));
users.add(new User("Jan", "Nicky"));
users.add(new User("Bot", "Smart"));
...
}
}
```
**Ответ.**  
```java
users.stream()
        .sorted(Comparator.comparing(User::getName))
        .forEach(System.out::println);
```


### 19. Допишите код, чтобы вывести только четные элементы коллекции, используя метод filter().  
```java
public class Main {
public static void main(String[] args) {
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
…
}
}
```
**Ответ.**  
```java
numbers.stream()
        .filter(integer -> integer % 2 == 0)
        .forEach(System.out::println);
```


### 20. Допишите код, чтобы вывести количество элементов коллекции, длина которых больше 4, используя методы filter() и count().  
```java
public class Main {
public static void main(String[] args) {
List<String> names = Arrays.asList("John", "Jan", "Tirion", "Marry", "Nikolas");
…
}
}
```
**Ответ.**  
```java
public final static int MAX_NUM_CH = 4;
names.stream()
        .filter(s -> s.chars().count() > MAX_NUM)
        .forEach(System.out::println);
```


### 21. Допишите код, чтобы вывести каждый элемент коллекции, умножив его на 2, используя метод map().  
```java
public class Main {
public static void main(String[] args) {
List<Integer> numbers = Arrays.asList(1, 3, 5, 7);
…
}
}
```
**Ответ.**  
```java
public final static int FACTOR = 2;
numbers.stream()
        .map(integer -> integer * 2)
        .forEach(System.out::println);
```


### 22. Создайте новую коллекцию ArrayList и выведите в консоль список четных чисел из коллекции numbers с использованием методов filter() и collect().  
```java
public class Main {
public static void main(String[] args) {
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
List<Integer> evenNumbers = ...
…
}
}
```
**Ответ.**  
```java
public final static int FACTOR = 2;
List<Integer> newNumb = numbers.stream()
        .filter(integer -> integer % 2 == 0)
        .peek(System.out::println)
        .collect(Collectors.toCollection(ArrayList::new));
```

 
### 23. Создайте новую коллекцию LinkedList (имплементация Queue) и выведите в консоль НЕ пустые строки из коллекции ArrayList names с использованием методов filter() и collect().  
```java
public class Main {
public static void main(String[] args) {
List<String> names = Arrays.asList("Jaime", "Daenerys", "", "Tyrion", "");
Queue<String> queue = ….
…
}
}
```
**Ответ.**  
```java
public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jaime", "Daenerys", "", "Tyrion", "");
         Queue<String> queue =names.stream()
        .filter(s -> !s.isEmpty())
        .peek(System.out::println)
        .collect(Collectors.toCollection(LinkedList::new));
```


### 24. Выведите имена домашних животных, объединив их в новую коллекцию List<String> petNames из коллекции их хозяев humans, у которых имена домашних животных являются полями класса (в виде отдельных коллекций), используя метод flatMap().  
```java
public class Main {
public static void main(String[] args) {
List<Human> humans = asList(
new Human("Sam", asList("Buddy", "Lucy")),
new Human("Bob", asList("Frankie", "Rosie")),
new Human("Marta", asList("Simba", "Tilly")));
List<String> petNames = ...
…
}
}
```
**Ответ.**  
```java
public class Main {
public static void main(String[] args) {
List<Human> humans = asList(
new Human("Sam", asList("Buddy", "Lucy")),
new Human("Bob", asList("Frankie", "Rosie")),
new Human("Marta", asList("Simba", "Tilly")));
List<String> petNames = humans.stream()
            .flatMap(human -> human.getPets().stream())
            .peek(System.out::println).toList();
```


### 25. Найдите и выведите первое по счету число, которое больше 10, используя методы filter() и findFirst().  
```java
public class Main {
public static void main(String[] args) {
List<Integer> numbers = Arrays.asList(1, 5, 8, 10, 12, 15);
Optional<Integer> first = ...
…
}
}
```
**Ответ.**  
```java
public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 5, 8, 10, 12, 15);
Optional<Integer> first = numbers.stream()
                  .filter(integer -> integer > 10)
                  .findFirst();
first.ifPresent(System.out::println);
```


### 26. Найдите и выведите первую попавшуюся фразу (с учетом возможного многопоточного процесса), которая содержит фрагмент "Java", используя методы filter() и findAny().  
```java
public class Main {
public static void main(String[] args) {
List<String> strings = Arrays.asList("Java is the best", "Java 8", "Java 9", "Jacoco");
Optional<String> java = …
...
}
}
```
**Ответ.**  
```java
List<String> strings = Arrays.asList("Java is the best", "Java 8", "Java 9", "Jacoco");
Optional<String> firstExpression = strings.stream()
                 .filter(s -> s.contains("Java"))
                 .findFirst();
firstExpression.ifPresent(System.out::println);
```


### 27. Выведите boolean, имеется ли в коллекции хотя бы одно четное значение, используя метод anyMatch().  
```java
public class Main {
public static void main(String[] args) {
List<Integer> numbers = Arrays.asList(1, 4, 5, 7);
boolean match = ...
...
}
}
```
**Ответ.**  
```java
List<Integer> numbers = Arrays.asList(1, 4, 5, 7);
boolean match = numbers.stream().anyMatch(integer -> integer % 2 == 0);
System.out.println(match);
```


### 28. Выведите boolean, являются ли все числа коллекции положительным, используя метод allMatch().  
```java
public class Main {
public static void main(String[] args) {
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
boolean match = ...
...
}
}
```
**Ответ.**  
```java
public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        boolean bool = numbers.stream()
                       .allMatch(integer -> integer > 0);
        System.out.println(bool);
    }
}
```


### 29. Выведите boolean, НЕ являются ли все числа коллекции четными, используя метод noneMatch().  
```java
public class Main {
public static void main(String[] args) {
List<Integer> numbers = Arrays.asList(1, 3, 5, 7, 9);
boolean match = ...
...
}
}
```
**Ответ.**  
```java
List<Integer> numbers = Arrays.asList(1, 3, 5, 7, 9);
boolean match = numbers.stream()
        .noneMatch(integer -> integer % 2 == 0);
System.out.println(match);
```


### 30. Какие из ниже приведенных лямбда-выражений не скомпилируются и почему?  
1. (int x, int y) -> x+y  
2. (x, y) -> x+y          
3. (x, int y) -> x+y   
4. (x, final y) -> x+y
5. int x -> x;
6. x -> y -> x + y;
7. x -> (final int y) -> y + x;
8. x -> x -> 5;
**Ответ.**  
**Источник.** <>


### 31. Скомпилируется ли следующий код и почему?  
```java
for (byte b : "Java".getBytes()) {
foo(() -> b);
}
```
**Ответ.**  
Скомпилируется. Выведется байт-код слова "Java".  
**Источник.** <https://stackoverflow.com/questions/24588068/how-to-convert-text-on-a-jtextarea-to-acsii-binary-and-back-on-java>


### 32. Дана матрица 3х3 используя Java 8 преобразуйте ее в одномерный массив.  
```java
int[][] matrix = {   {1, 2, 3}
, {4, 5, 6}
, {7, 8, 9}};
int[] array = ….
```
**Ответ.**  
```java
int[] array = Arrays.stream(matrix)
        .flatMapToInt(Arrays::stream)
        .toArray();
System.out.println(Arrays.toString(array));
```


### 33. Даны классы:  
```java
class BlogPost {
String title;
String author;
BlogPostType type;
int likes;
}
enum BlogPostType {
NEWS,
REVIEW,
GUIDE
}
List<BlogPost> posts = Arrays.asList( ... );
```
Определите:  
1. Все уникальные статьи относящиеся к каждому типу статей.  
2. Для каждого типа статьи определите статью с максимальным количеством лайков.  
3. Все статьи относящиеся к каждому типу статей, список статей должен представлять собой строку формата: “Post titles: [title1, title2, ...] “  
**Ответ.**  
```java
Map<BlogPostType, Set<BlogPost>> allUniquePosts = posts.stream()
.collect(Collectors.groupingBy(BlogPost::getType, Collectors.toSet()));
allUniquePosts.forEach((k, v) -> System.out.println(k + "=" + v));

Map<BlogPostType, Optional<BlogPost>> mostLikedPost = posts.stream()
.collect(Collectors.groupingBy(BlogPost::getType,Collectors.maxBy(Comparator.comparingInt(BlogPost::getLikes))));
mostLikedPost.forEach((key, value) -> System.out.println(key + "=" + value.orElse(null)));

Map<BlogPostType, List<BlogPost>> postsGropByType = posts.stream()
.collect(Collectors.groupingBy(BlogPost::getType));
postsGropByType.forEach((k, v) -> System.out.println(k + ": " + v.stream()
.map(BlogPost::getTitle).toList()));
```


### 34. Приведите два способа получения последнего элемента в потоке, в чем особенности вычисления этого значения в потоках.  
**Ответ.**  
**Источник.**


### 35. Дан код, можно ли его как-то отрефакторить? Если да, то сделайте это.  
```java
class Book {
private String name;
private String author;

// getters and setters
…
}

List<Book> books = new ArrayList<>();

books.add(new Book("Effective Java", "Joshua Bloch"));
books.add(new Book("Thinking in Java", "Bruce Eckel"));
books.add(new Book("Java: The Complete Reference", "Herbert Schildt"));

Map<String, String> bookMap = books.stream().collect(
Collectors.toMap(Book::getAuthor, Book::getName));
bookMap.forEach((author, book) ->
System.out.println("Author: " + author + " Books: " + book));
```
**Ответ.**  


### 36. Дан код. Замените многоточие, чтобы определить сотрудников находящихся в отделе “sales”  
```java
class Employee {
    Integer employeeId;
    String employeeName;

    // getters and setters
}

class Department {
    Integer employeeId;
    String department;

    // getters and setters
}

public class Main {
public static void main(String[] args) {
    List<Employee> employees = new ArrayList<>();
    List<Department> departments = new ArrayList<>();

    populate(employees, departments);

    List<Employee> salesEmployees = ...;
    }
}
```
**Ответ.**  
**Источник.** <>


### 37. Дан код. Выполните операцию ‘zip’ для коллекций ages и names.  
```java
class Tuple<T1, T2> {
private T1 item1;
private T2 item2;
// getters and setters
}
List<String> names = new ArrayList<>(Arrays.asList("John", "Jane", "Jack", "Dennis"));
List<Integer> ages = new ArrayList<>(Arrays.asList(24, 25, 27));
List<Tuple<String, Integer>> namesAndAges = …
```
**Ответ.**  
```java
class Tuple<T1, T2> {
  private T1 item1;
  private T2 item2;
// getters and setters
}
List<String> names = new ArrayList<>(Arrays.asList("John", "Jane", "Jack", "Dennis"));
List<Integer> ages = new ArrayList<>(Arrays.asList(24, 25, 27));

List<Tuple<String, Integer>> namesAndAges = IntStream
        .range(0, Math.min(names.size(), ages.size()))
        .mapToObj(i -> new Tuple<>(names.get(i), ages.get(i)));
```  
**Источник**<https://www.baeldung.com/java-collections-zip>


### 38. Дан код, замените  {code} и {type} так, чтобы получить нужные результаты  
Collection<String> strings = Arrays.asList("a1", "b2", "c3", "a1");  
1.	// Удалить все дубликаты  
      List<String> unique= strings.stream().{code}  
      // напечатает unique= [a1, b2, c3]  
      System.out.println("unique = " + unique);  
2.	// Объединить все элементы в одну строку через разделитель : и обернуть тегами <b> ... </b>  
      String join = strings.stream().collect({code});  
      // напечатает <b> a1 : b2 : c3 : a1 </b>  
      System.out.println("join = " + join);  
3.	// Преобразовать в map, сгруппировав по первому символу строки  
      Map<String, List<String>> groups = strings.stream().collect({code});  
      // напечатает groups = {a=[a1, a1], b=[b2], c=[c3]}  
      System.out.println("groups = " + groups);  
4.	// Преобразовать в map, сгруппировав по первому символу строки и в качестве значения взять второй символ, если ключ повторяется, то значения объединить через “:”  
      Map<String, String> groupJoin = strings.stream()  
      .collect(Collectors.groupingBy({code}));  
      // напечатает groupJoin = groupJoin = {a=1:1, b=2, c=3}  
      System.out.println("groupJoin = " + groupJoin);  
      
Collection<Integer> numbers = Arrays.asList(1, 2, 3, 4);  
1.	// Получить сумму нечетных чисел  
      {type} sumOdd = numbers.stream().collect({code});  
      // напечатает sumEven = 4   
      System.out.println("sumOdd = " + sumOdd);  
2.	 // Вычесть из каждого элемента 1 и получить среднее  
       double average = numbers.stream().collect({code});  
       // напечатает average = 1.5  
       System.out.println("average = " + average);  
3.	// Прибавить к числам 3 и получить статистику: количество элементов, их сумму, макс и мин. значения, а также их среднее.  
      {type} statistics = numbers.stream().collect({code});  
      // напечатает statistics = … {count=4, sum=22, min=4, average=5.500000, max=7}  
      System.out.println("statistics = " + statistics);  
4.	// Разделить числа на четные и нечетные  
      Map<Boolean, List<Integer>> parts = numbers.stream().collect({code});  
      // напечатает parts = {false=[1, 3], true=[2, 4]}  
      System.out.println("parts = " + parts);  

**Ответ.**
Collection<String> strings = Arrays.asList("a1", "b2", "c3", "a1");  
1. // Удалить все дубликаты  
   List<String> unique = strings.stream()  
   .distinct().toList();  
// напечатает unique= [a1, b2, c3]  
        System.out.println("unique = " + unique);  
   
2. // Объединить все элементы в одну строку через разделитель : и обернуть тегами <b> ... </b>  
   String join = strings.stream()  
   .collect(Collectors
   .joining(" : ", "<b> ", " </b>"));  
// напечатает <b> a1 : b2 : c3 : a1 </b>  
        System.out.println("join = " + join);  
   
3. // Преобразовать в map, сгруппировав по первому символу строки  
   Map<String, List<String>> groups = strings.stream()
   .collect(Collectors 
   .groupingBy(s -> s.substring(0, 1)));  
// напечатает groups = {a=[a1, a1], b=[b2], c=[c3]}  
        System.out.println("groups = " + groups);  
   
4. // Преобразовать в map, сгруппировав по первому символу строки и в качестве значения взять второй символ, если ключ повторяется, то значения объединить через “:”  
   Map<String, String> groupJoin = strings.stream()  
   .collect(Collectors.groupingBy(s -> s.substring(0, 1),Collectors
   .mapping(s -> s.substring(1, 2), Collectors.joining(":"))));  
// напечатает groupJoin = groupJoin = {a=1:1, b=2, c=3}  
        System.out.println("groupJoin = " + groupJoin);  

Collection<Integer> numbers = Arrays.asList(1, 2, 3, 4);  
1. // Получить сумму нечетных чисел  
   Integer sumOdd = numbers.stream()
   .collect(Collectors
   .summingInt(value -> value % 2 == 0 ? 0 : value));  
// напечатает sumEven = 4  
        System.out.println("sumOdd = " + sumOdd);  
   
2. // Вычесть из каждого элемента 1 и получить среднее  
   double average = numbers.stream()  
   .collect(Collectors  
   .averagingInt(value -> value - 1));    
// напечатает average = 1.5    
        System.out.println("average = " + average);    
     
3. // Прибавить к числам 3 и получить статистику: количество элементов, их сумму, макс и мин. значения, а также их среднее.    
   IntSummaryStatistics statistics = numbers.stream()  
   .collect(Collectors  
   .summarizingInt(value -> value + 3));    
// напечатает statistics = … {count=4, sum=22, min=4, average=5.500000, max=7}    
        System.out.println("statistics = " + statistics);    
     
4. // Разделить числа на четные и нечетные    
   Map<Boolean, List<Integer>> parts = numbers.stream()  
   .collect(Collectors  
   .groupingBy(integer -> integer % 2 == 0));    
// напечатает parts = {false=[1, 3], true=[2, 4]}    
        System.out.println("parts = " + parts);    


### 39. Дан поток, определите количество вхождений каждого из символов, составляющих поток.  
```java
Stream<String> words = Stream.of("Java", "Magazine", "is", "the", "best");
```
**Ответ.**
**Источник**<>  


### 40. Дан код, как он будет выглядеть если modem обернуть в Optional?  
```java
boolean isInRange = false;
if (modem != null && modem.getPrice() != null
&& (modem.getPrice() >= 10
&& modem.getPrice() <= 15)) {
isInRange = true;
}
return isInRange;
```
**Ответ.**  
**Источник**<>  


### 41. Дан код, замените {code}, чтобы получить первый объект, которые не null, если такого нет вернуть "default"   
```java
private Optional<String> getEmpty() {
    return Optional.empty();
}

private Optional<String> getHello() {
    return Optional.of("hello");
}

private Optional<String> getBye() {
    return Optional.of("bye");
}
String firstNonNull = Stream.of(getEmpty(), getHello(), getBye()).{code};
```
**Ответ.**  
```java
String notNullObject = Stream.of(getEmpty(), getHello(), getBye())
        .filter(Optional::isPresent)
        .map(Optional::get)
        .findFirst()
        .orElse("default");
System.out.println(firstNonNull);
```
