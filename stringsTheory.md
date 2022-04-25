### 1. Что появится в консоли в результате работы фрагмента программы?
```java
String a = "java";
a.toUpperCase();
System.out.println(a);
```
**Ответ.**  
`java`  
Т.к. класс String помечен как final, то любая манипуляция со строкой приведет к
созданию новой. После создания объекта его состояние изменить нельзя. Операцию
можно выполнить присвоив действие новому объекту: String b = a.toUpperCase();

### 2. Что появится в консоли в результате работы фрагмента программы?
###Поясните ответ.  
````java
String s1 = "Java";
String s2 = "Java";
String s3 = new String("Java");
System.out.println("s1 == s2 : " + (s1 == s2));
System.out.println("s1 == s3 : " + (s1 == s3));
System.out.println(s1.equals(s3));
````
**Ответ.**  
s1 == s2 : true
s1 == s3 : false
true
При создании объекта с помощью класса String, он помещается в пул литералов. При
создании такого же объекта происходит сравнение с уже помещенным, и поскольку объекты 
эквивалентны, то две ссылки ссылаются на один объект, что и происходит при сравнении
ссылок при помощи оператора `==`. 
C помощью оператора `new` происходит создание нового объекта. Поэтому при сравнении
двух объектов оператором `==` на консоль выводится false. 
Метод `equals()` производит сравнение двух объектов по-содержимому.   

### 3. Можно ли выполнить наследование от класса String? Почему?

**Ответ.**  
Класс `String` объявлен как `final`, что означает невозможность создания собственных порожденных
классов со свойствами строки.  
**Источник.** <Java from EPAM : учеб.-метод. пособие / И. Н. Блинов, В. С. Романчик.>

### 4. Назовите основные, на ваш взгляд, методы класса String.

**Ответ.**   
String concat(String s) или «+» — слияние строк;
boolean equals(Object ob) и equalsIgnoreCase(String s) — сравнение строк
с учетом и без учета регистра соответственно;
int compareTo(String s) и compareToIgnoreCase(String s) — лексикографи-
ческое сравнение строк с учетом и без учета их регистра. Метод осуществляет
вычитание кодов первых различных символов вызывающей и передаваемой
строки в метод строк и возвращает целое значение. Метод возвращает значе-
ние нуль в случае, когда equals() возвращает значение true;
boolean contentEquals(StringBuffer ob) — сравнение строки и содержимо-
го объекта типа StringBuffer;
String substring(int n, int m) — извлечение из строки подстроки длины
m-n, начиная с позиции n. Нумерация символов в строке начинается с нуля;
String substring(int n) — извлечение из строки подстроки, начиная с позиции n;
int length() — определение длины строки;
int indexOf(char ch) — определение позиции символа в строке;
static String valueOf(значение) — преобразование переменной базового
типа к строке;
String toUpperCase()/toLowerCase() — преобразование всех символов вы-
зывающей строки в верхний/нижний регистр;
String replace(char с1, char с2) — замена в строке всех вхождений первого
символа вторым символом;
String intern() — заносит строку в «пул» литералов и возвращает ее объект-
ную ссылку;
String trim() — удаление всех пробелов в начале и конце строки;
char charAt(int position) — возвращение символа из указанной позиции
(нумерация с нуля);
boolean isEmpty() — возвращает true, если длина строки равна 0;
char[] getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin) — извле-
чение символов строки в массив символов;
static String format(String format, Object… args), format(Locale l, String
format, Object… args) — генерирует форматированную строку, полученную
с использованием формата, интернационализации и др.;
String[] split(String regex), String[] split(String regex, int limit) — поиск
вхождения в строку заданного регулярного выражения (разделителя) и деле-
ние исходной строки в соответствии с этим на массив строк.
Во всех случаях вызова методов, изменяющих строку, создается  
**Источник.** <Java from EPAM : учеб.-метод. пособие / И. Н. Блинов, В. С. Романчик.>

### 5. Какие разновидности конструкторов использует класс String? 

**Ответ.**  
Класс String поддерживает несколько конструкторов, например: String(),
String(String str), String(byte[] asciichar), String(char[] unicodechar),
String(StringBuffer sbuf), String(StringBuilder sbuild) и др. Эти конструкторы
используются для создания объектов класса String на основе инициализации
значениями из массива типа char, byte и др.  
**Источник.** <Java from EPAM : учеб.-метод. пособие / И. Н. Блинов, В. С. Романчик.>

### 6. Какие классы в стандартной библиотеке Java работают со строками?

**Ответ.**  
Системная библиотека Java содержит классы String, StringBuilder
и StringBuffer, поддерживающие хранение строк, их обработку и определенные
в пакете java.lang, подключаемом к приложению автоматически. Для форматирования 
и обработки строк применяются также классы Formatter, Pattern, Matcher, 
StringJoiner и другие.  
**Источник.** <Java from EPAM : учеб.-метод. пособие / И. Н. Блинов, В. С. Романчик.>

### 7. Почему экземпляры класса String в Java неизменные и финализированные?

**Ответ.**  
Строка неизменяема в Java из-за безопасности, синхронизации и параллелизма, 
кэширования и загрузки классов. Причина создания строки final состоит в том, 
чтобы разрушить неизменность и не позволить другим расширять ее. Объекты String 
кэшируются в пуле String, и это делает String неизменяемым. К кэшированным строковым 
литералам обращаются несколько клиентов. Таким образом, всегда существует риск, когда действие, 
выполняемое одним клиентом, влияет на всех остальных клиентов. Например, если 
один клиент выполняет действие и меняет строковое значение с Давление на ДАВЛЕНИЕ, 
все остальные клиенты также увидят это значение. По соображениям производительности
кэширование объектов String было важно, поэтому, чтобы устранить этот риск, 
мы должны сделать String неизменяемым.  
**Источник.**<https://www.javatpoint.com/why-string-is-immutable-or-final-in-java#:~:text=The%20String%20is%20immutable%20in,it%20makes%20the%20String%20immutable.>

### 8. Заполните ячейки таблицы (Да/Нет).

**Ответ.**  
```xls
Characteristic                 String        StringBuilder        StringBuffer
Неизменяемый (Immutable)?        Да              Нет                  Нет
Имеет пул (Pooled)?              Да              Нет                  Нет
Потокобезопасный (Thread-safe)?  Да              Нет                  Да
Может изменять размер?           Нет             Да                   Да 
```

### 9. В чем разница и что общего между StringBuffer и StringBuilder?

**Ответ.**  
Основным отличием StringBuilder от StringBuffer является потокобезопас-
ность последнего. Более высокая скорость обработки есть следствие отсутст-
вия потокобезопасности класса StringBuilder.  
**Источник.** <Java from EPAM : учеб.-метод. пособие / И. Н. Блинов, В. С. Романчик.>

### 10. Когда лучше использовать StringBuffer, а когда StringBuilder?

**Ответ.**  
StringBuilder следует применять, если не существует вероятности использования
объекта в конкурирующих потоках.  
**Источник.** <Java from EPAM : учеб.-метод. пособие / И. Н. Блинов, В. С. Романчик.>

### 11. Какие методы имеются в классах StringBuffer и StringBuilder, которые отсутствуют в классе String?

**Ответ.**  
void setLength(int n) — установка размера буфера;  
void ensureCapacity(int minimum) — установка гарантированного минимального размера буфера;  
int capacity() — возвращение текущего размера буфера;  
StringBuffer append(параметры) — добавление к содержимому объекта  
строкового представления аргумента, который может быть символом, значением базового типа, массивом и строкой;  
StringBuffer insert(параметры) — вставка символа, объекта или строки в указанную позицию;  
StringBuffer deleteCharAt(int index) — удаление символа;  
StringBuffer delete(int start, int end) — удаление подстроки;  
StringBuffer reverse() — обращение содержимого объекта.  
**Источник.** <Java from EPAM : учеб.-метод. пособие / И. Н. Блинов, В. С. Романчик.>

### 12. Какие методы сравнения строк имеются в строковых классах?
**Ответ.**  
boolean equals(Object ob) и equalsIgnoreCase(String s) — сравнение строк с учетом и без учета регистра соответственно;  

int compareTo(String s) и compareToIgnoreCase(String s) — лексикографическое сравнение строк с учетом и без учета их регистра. Метод осуществляет
вычитание кодов первых различных символов вызывающей и передаваемой строки в метод строк и возвращает целое значение. Метод возвращает значение нуль 
в случае, когда equals() возвращает значение true;  

boolean contentEquals(StringBuffer ob) — сравнение строки и содержимого объекта типа StringBuffer;
**Источник.** <Java from EPAM : учеб.-метод. пособие / И. Н. Блинов, В. С. Романчик.>

### 13. Используя функции строковых классов, написать фрагмент программы, которая будет определять, является ли строка палиндромом.
**Ответ.**  
```java
public static boolean isPalindrome(String text){
        String newWord = new StringBuilder(text).reverse().toString();
        return newWord.equalsIgnoreCase(text);
        }
```

### 14. Что появится в консоли в результате работы фрагмента программы?  
### Поясните ответ.
```java
final String ZA = " за ";
String value = "ОТЧЕТ о перевозках пассажиров за январь 2019 г.";
String[] monthYear = value.split(ZA)[1].split(" ",3);
System.out.println(Arrays.toString(monthYear));
```  
**Ответ.**
`[январь, 2019, г.]` 
Строка value разбивается выражением " за " на два массива с индексом 0 и 1 т.к. 
найдено одно совпадение и сразу же массив с индексом 1 разбивается выражением " " 3 раза.
В итоге получаем массив из трех слов.  

### 15. Что появится в консоли в результате работы фрагмента программы?
### Поясните ответ.
```java
String s4 = "1" + 2 + 3;
String s5 = 1 + 2 + "3";
System.out.println(s4);
System.out.println(s5);
```
**Ответ.**
`123`
`33`  
Этот непредсказуемый результат связан с тем, что компилятор оценивает данное 
выражение слева направо, учитывая, что операторы имеют одинаковый приоритет. 
Как только он встречает строку, он рассматривает остальную часть выражения как 
строку (опять же на основе порядка приоритета выражения).  
**Источник.** <https://www.geeksforgeeks.org/addition-and-concatenation-using-plus-operator-in-java/>

### 16. Что появится в консоли в результате работы фрагмента программы?
### Поясните ответ.
````java
String s = "abcde ";
        System.out.println(s.trim().length());
        System.out.println(s.charAt(4));
        System.out.println(s.indexOf('e'));
        System.out.println(s.indexOf("de"));
        System.out.println(s.substring(2, 4).toUpperCase());
        System.out.println(s.replace('a', '1'));
        System.out.println(s.contains("DE"));
        System.out.println(s.startsWith("a"));
````
**Ответ.**
```java
5
e
4
3
CD
1bcde
false
true
```  

### 17. Что появится в консоли в результате работы фрагмента программы?
### Поясните ответ.
```java
StringBuilder b = new StringBuilder();
b.append(12345).append('-');
System.out.println(b.length());
System.out.println(b.indexOf("-"));
System.out.println(b.charAt(2));
StringBuilder b2 = b.reverse();
System.out.println(b.toString());
System.out.println(b == b2);
```  
**Ответ.**
```java
6
5
3
-54321
true
``` 
12345- - добавляем к строке символ "-";
6 - длина строки составит 6 знаков;
5 - индекс симола "-";
3 - число под индексом 2;
-54321 - реверс строки.

### 18. Что появится в консоли в результате работы фрагмента программы?
### Поясните ответ.
```java  
StringBuilder s = new StringBuilder("abcde");
s.insert(1, '-').delete(3, 4);
System.out.println(s);
System.out.println(s.substring(2, 4));
```
**Ответ.**
```java
a-bde
bd
```
a-bde - вставка символа и удаление символов с соответствующими индексами, не включая;
bd - вывод подстроки с соответствующими индексами, не включая последний.

### 19. Что появится в консоли в результате работы фрагмента программы?
### Поясните ответ.
```java
StringBuffer sb = new StringBuffer("abcde");
sb.insert(2,"123");
System.out.println(sb);
sb.append("456");
System.out.println(sb);
sb.reverse();
System.out.println(sb);
```
**Ответ.**  
```java
ab123cde
ab123cde456
654edc321ba
```  
sb.insert(2,"123") - вставляем на позицию с индексом 2 символы 123;
sb.append("456") - вставляет в конец строки символы 456;
sb.reverse() - операция позволяет развернуть выражение в обратном порядке;    

### 20. Каким образом можно сцепить строки Java? Назовите не менее 3 способов.  
**Ответ.**
Конкатенация строк оператором + (конкатенация строк):  
```java
String hello = "Hello";
String world = " World";
System.out.println(hello + world);
```
Добавление строк в StringBuilder: 
```java
StringBuilder s = new StringBuilder();
s.append("Hello");
s.append(" World");
s.append(" in Java!");
System.out.println(s.toString());
```
Конкатенация строк методом concat():
```java
   String s1="Sachin ";  
   String s2="Tendulkar";  
   String s3=s1.concat(s2);  
   System.out.println(s3);//Sachin Tendulkar 
```  
Конкатенация строк с использованием метода format():
```java
String s1 = new String("Hello");    //String 1  
String s2 = new String(" World");    //String 2  
String s = String.format("%s%s",s1,s2);   //String 3 to store the result  
System.out.println(s.toString());  //Displays result  
```  
Объединение строк с использованием метода String.join() (Java Version 8+):  
```java
String s1 = new String("Hello");    //String 1  
String s2 = new String(" World");    //String 2  
String s = String.join("",s1,s2);   //String 3 to store the result  
System.out.println(s.toString());  //Displays result  
```

**Источник.** <https://www.javatpoint.com/string-concatenation-in-java#:~:text=There%20are%20two%20ways%20to,By%20concat()%20method>

### 21. Чем отличаются пустая и нулевая строки?
**Ответ.**  
Язык программирования Java различает нулевые и пустые строки. Пустая строка — это 
экземпляр строки нулевой длины, тогда как нулевая строка вообще не имеет значения. 
Пустая строка представляется как "". Это последовательность нулевых символов.  
**Источник.** <https://docs.oracle.com/javaee/7/tutorial/bean-validation002.htm#:~:text=The%20Java%20programming%20language%20distinguishes,character%20sequence%20of%20zero%20characters.>

### 22. В какой кодировке хранятся символы в строке?  
**Ответ.**  
Java хранит строки как UTF-16 внутри. "кодировка по умолчанию" не совсем верна. 
Java хранит строки как UTF-16 внутри, но кодировка, используемая извне, «системная 
кодировка по умолчанию», варьируется от платформы к платформе и даже может быть 
изменена такими вещами, как переменные среды на некоторых платформах. ASCII — это 
подмножество Latin 1, которое является подмножеством Unicode. UTF-16 — это способ 
кодирования Unicode. Поэтому, если вы выполните тест int i = 'x' для любого символа, 
попадающего в диапазон ASCII, вы получите значение ASCII. Однако UTF-16 может 
представлять намного больше символов, чем ASCII.  
**Источник.** <https://stackoverflow.com/questions/4453269/what-is-the-character-encoding-of-string-in-java#:~:text=Java%20stores%20strings%20as%20UTF%2D16%20internally.>
