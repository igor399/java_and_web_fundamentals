### 1. На какие две группы разделяются классы, объявленные внутри другого класса? Как они называются на инглише?

**Ответ.**  
Вложенные классы делятся на два вида: статические и не статические. Вложенные классы, объявленные как статические
называются вложенными статическими (static nested classes). Нестатические называются внутренними (inner classes).
Вложенные классы — элементы содержащего их класса.  
**Источник.** <http://easy-code.ru/lesson/java-nested-classes>

### 2. Для каких целей они используются?

**Ответ.**  
Вложенные классы применяются в тех случаях, когда нужно написать небольшой вспомогательный код для другого класса.
Вложенный класс создают также, чтобы скрыть его переменные и методы от внешнего мира. Таким образом, вложенный класс еще
один элегантный способ ограничения области видимости. Внутренние классы также есть смысл использовать, если
предполагается, что они будут использовать элементы родителя, чтобы не передавать лишнего в конструкторах.  
**Источник.** <https://habr.com/ru/post/439648/>

### 3. Какие уровни доступа применяются к таким классам?

**Ответ.**  
вложенный класс может быть объявлен `private`, `public`, `protected` или `package private`. (Напомним, что внешние
классы могут быть объявлены только как `public` или `package private`.)  
**Источник.** <https://habr.com/ru/post/439648/>

### 4. Какие существуют варианты внутренних классов?

**Ответ.**  
* Статические вложенные классы;
* Внутренние классы;
* Локальные классы;
* Анонимные (безымянные) классы.  
**Источник.** <https://habr.com/ru/post/439648/>

### 5. Пусть объявлен класс Outer, а внутри него публичный вложенный класс NestedPublic и публичный внутренний класс InnerPublic. Создайте экземпляры каждого класса:
### а) внутри класса Outer,  
### б) извне класса Outer?  

**Ответ.**  
а) внутри класса Outer:
NestedPublic nestedPublic = new NestedPublic();
InnerPublic innerPublic=new InnerPublic();  

б) извне класса Outer:  
Outer.NestedPublic nestedPublic=new Outer.NestedPublic();
Outer.InnerPublic innerPublic=new Outer().new InnerPublic();

### 6. Пусть объявлен класс Outer, а внутри него приватный вложенный класс NestedPrivate и приватный внутренний класс InnerPrivate. Создайте экземпляры каждого класса:
### а) внутри класса Outer,  
### б) извне класса Outer?  

**Ответ.**  
а) внутри класса Outer:
NestedPublic nestedPublic=new NestedPublic();
InnerPublic innerPublic=new InnerPublic();

б) извне класса Outer:  
Внутренние private-классы используюься для сокрытия реализации как таковой.  
**Источник.** <https://elearn.epam.com/courses/course-v1:EPAM+JC+ext1/courseware/>

### 7. Пусть объявлен класс Outer, а внутри него внутренний класс Inner. Как обратиться внутри класса Inner:
### а) к экземпляру класса Inner,  
### б) к объемлющему экземпляру класса Outer?

**Ответ.**  
а) к экземпляру класса Inner: при помощи ключевого слова this; б) к объемлющему экземпляру класса Outer: Outer.this.(
some field of of Outer class);  
**Источник.** <https://elearn.epam.com/courses/course-v1:EPAM+JC+ext1/courseware/>

### 8. Пусть объявлен класс Outer, а внутри него вложенный класс Nested. Как обратиться внутри класса Nested:
### а) к экземпляру класса Nested,
### б) к объемлющему экземпляру класса Outer?

**Ответ.**   
а) При помощи ключевого слова this; б) Нельзя, т.к. статический вложенный класс имеет доступ только к статическим
методам и полям объемлющего класса;  
**Источник.** <https://elearn.epam.com/courses/course-v1:EPAM+JC+ext1/courseware/>

### 9. Можно ли из вложенного класса обратиться к членам внешнего класса? Если да, то приведите пример.

**Ответ.**  
```java
public class Owner {
    private int value = 1;
    static int statValue = 3;

    public static class Nested {
        static int param = statValue;
        static int finalValue = value; // ошибка компиляции
    }
}
```  
**Источник.** <https://elearn.epam.com/courses/course-v1:EPAM+JC+ext1/courseware/>

### 10. Можно ли из внутреннего класса обратиться к экземпляру внешнего класса? Если да, то приведите пример.

**Ответ.**  
```java
public class Owner {
    private int value = 1;
    static int statValue = 3;
    public static void ownerMethod(){
    }
    public void nextOwnerMethod(){
        
    }

    public static class Inner {
        private int param = value;
        private int finalValue = statValue;
        public void innerMethod(){
            ownerMethod();
            nextOwnerMethod();
        }
    }
}
``` 
**Источник.** <https://elearn.epam.com/courses/course-v1:EPAM+JC+ext1/courseware/>

### 11. Можно ли определить экземпляр вложенного класса, не определяя экземпляры внешнего класса? Если да, то приведите пример.

**Ответ.**  
```java
public class Owner{
    
    public static class Nested{
        
    }
}

public class Main{
    public static void main(String[] args){
      Owner.Nested nested = new Owner.Nested();  
    }
}
```  
**Источник.** <https://elearn.epam.com/courses/course-v1:EPAM+JC+ext1/courseware/>

###12. Есть ли ограничения на объявление локальных переменных в локальных внутренних классах? Есть ли да, то какие?  

**Ответ.**  
Как и member классы, локальные классы ассоциируются с экземпляром обрамляющего класса и имеют доступ к его полям и 
методам. Кроме этого, локальный класс может обращаться к локальным переменным и параметрам метода, если они объявлены с модификатором final.
Локальные классы не могут иметь внутри себя статических объявлений (полей, методов, классов); исключением являются константы (static final).  
**Источник.** <http://www.quizful.net/post/inner-classes-java>

### 13. Можно ли наследовать вложенные классы? Если да, то приведите пример.

**Ответ.**  
Статический вложенный класс может быть унаследован от:
* обычного класса
* статического вложенного класса, который объявлен во внешнем классе или его предках
```java
public class Boeing737 {

   private int manufactureYear;
   private static int maxPassengersCount = 300;

   public Boeing737(int manufactureYear) {
       this.manufactureYear = manufactureYear;
   }
   
   public int getManufactureYear() {
       return manufactureYear;
   }
   
   public static class Drawing {
   }
   
   public static class Boeing737Drawing extends Drawing {

       public static int getMaxPassengersCount() {
           return maxPassengersCount;
       }
   }
}
```  
**Источник.** <https://javarush.ru/groups/posts/2199-primerih-nasledovanija-vnutrennikh-klassov> 

### 14. Можно ли из подкласса обратиться к методу вложенного суперкласса? Если да, то приведите пример.

**Ответ.**  
Можно, обладая объектом внешнего класса.  
```java
public class Student {
    private int studentId;
    private String name;
    
    public class Address{
        private long telephoneNumber;
    }
}
public class SubAddress extends Student.Address{
    public SubAddress{
        new Student().super();
    }
}
```  
**Источник.** <https://elearn.epam.com/courses/course-v1:EPAM+JC+ext1>

### 15. Какие существуют варианты внутренних интерфейсов?

**Ответ.**  
Интерфейс может быть объявлен членом класса или другого интерфейса. Такой интерфейс называется иитерфейсом-членом или 
вложенным иитерфейсом.  
На вложенный интерфейс должен ссылаться внешний интерфейс или класс. К нему нельзя получить доступ напрямую.
**Источник.** <https://pro-java.ru/java-dlya-nachinayushhix/vlozhennye-interfejsy-v-yazyke-java/>

### 16. Можно ли объявить класс внутри интерфейса? Если да, то есть ли ограничения? Приведите пример.

**Ответ.**  
```java
public interface Foo {
void foo(Bar bar);

static class Bar {
private final String id;
public Bar(Sting id) { this.id = id; }
public String getId() { return id; }
}
}
```  
**Источник.** <https://org.hibernate.persister.entity.Queryable>

### 17. Можно ли создать экземпляр анонимного класса на основе:  
### а) абстрактного класса?  
### б) интерфейса?  
### в) неабстрактного класса?  
### г) финального класса?  
### Если да, то приведите пример.  

**Ответ.**  
### а)
```java
public abstract class Action {
	abstract void doAction();
}
public class Anon{
	public static Action action = new Action() {
		@Override
		public void doAction() {
		}
	};
	
	public static void main(String[] args) {
		Action action = new Action() {
			@Override
			public void doAction() {
			}
		};
		action.doAction();
		Anon.action.doAction();
	}
}
```  
### б)  
```java
public interface  Action {
	 void doAction();
}
public class Anon{
	public static Action action = new Action() {
		@Override
		public void doAction() {
		}
	};
	
	public static void main(String[] args) {
		Action action = new Action() {
			@Override
			public void doAction() {
			}
		};
		action.doAction();
		Anon.action.doAction();
	}
}
```    
### в)
```java
public class Action {
	abstract void doAction();
}
public class Anon{
	public static Action action = new Action() {
		@Override
		public void doAction() {
		}
	};
	public static void main(String[] args) {
		Action action = new Action() {
			@Override
			public void doAction() {
			}
		};
		action.doAction();
		Anon.action.doAction();
	}
}
```  
### г) нет возможности
**Источник.** <https://habr.com/ru/company/otus/blog/456010/>

### 18.
```java
//-------------- begin --------------
class Runner {
    public static void main(String[] args) {
        Something something = new Something();
        something.doSomething(...);        //1
    }
}

interface Smthable {
    void doSmth();
}

class Something {
    void doSomething(...) {            //2
        smth.doSmth();
    }
}
//--------------- end ---------------
```  

### 1. Замените многоточия в строках 1 и 2 на такой код, чтобы приложение после запуска с помощью экземпляра анонимного класса, порожденного от интерфейса Smthable, вывело на консоль текст Hello, World.

### 2. Получите тот же результат, переместив:  
### а) интерфейс Smthable внутрь класса Something,  
### б) класс Something внутрь интерфейса Smthable.  

**Ответ.**  
### 1.  
```java
//-------------- begin --------------
class Runner {
    public static void main(String[] args) {
        Something something = new Something();
        something.doSomething(new Something(){
            @Override
                public void doSmth(){
                System.out.println("Hello. World!");
            }
        });
    }
}
interface Smthable {
    void doSmth();
}
class Something {
    void doSomething(Smthable doSmth) {            
        smth.doSmth();
    }
}
//--------------- end ---------------
``` 
### 2.  
### а)  
```java
//-------------- begin --------------
class Runner {
    public static void main(String[] args) {
        Something something = new Something();
        something.doSomething(new Something(){
            @Override
            public void doSmth(){
                System.out.println("Hello. World!");
            }
        });
    }
}
class Something {
    interface Smthable {
        void doSmth();
    }
    void doSomething(Smthable smthable) {            //2
        smth.doSmth();
    }
}
//--------------- end ---------------
```    
### б)  
```java
//-------------- begin --------------
class Runner {
    public static void main(String[] args) {
        Something something = new Something();
        something.doSomething(new Smthable(){
            @Override
            public void doSmth(){
                System.out.println("Hello. World!");
            }
        });        //1
    }
}
interface Smthable {
    void doSmth();
    class Something {
        void doSomething(Smthable smthable) {            //2
            smth.doSmth();
        }
    } 
}
//--------------- end ---------------
```  

### 19.  
```java
//-------------- begin --------------
abstract class AbstractRunner {
    abstract int getYear();

    abstract class AbstarctInner {
        abstract int getYear();
    }

    public static void main(String[] args) {
	... //1
	... //2
	... //3
    }
}
//--------------- end ---------------
```  

### Создайте в строке 1 ссылку runner на экземпляр подкласса класса AbstractRunner.  
### Создайте в строке 2 ссылку inner на экземпляр подкласса класса AbstractInner.  
### Выведите на консоль в строке 3 текст 2010;2015, используя данные ссылки.  

**Ответ.**  
```java
//-------------- begin --------------
abstract class AbstractRunner {
    abstract int getYear();

    abstract class AbstarctInner {
        abstract int getYear();
    }

    public static void main(String[] args) {
        AbstractRunner runner = new AbstractRunner() {
            int getYear() {
                return 2010;
            }
        };
        AbstarctInner inner = new runner.AbstarctInner() {
            int getYear() {
                return 2015;
            }
        };
        System.out.println(runner.getYear() + ";" + inner.getYear());
    }
}
//--------------- end ---------------
```  

### 20.  
```java
//-------------- begin --------------
class Runner {
    public static void main(String[] args) {
		...    //1
    }
}

class Outer {
    class Inner {
        void go() {
            System.out.println("Gone!");
        }
    }
}
//--------------- end ---------------
```  
### 1. С помощью функционала классов Outer и Inner выведите на консоль в строке 1 текст Gone!.  
### 2. Переместив класс Outer внутрь класса Runner, получите тот же результат:  
### а) не изменяя строку 1.  
### б) изменяя строку 1,  

**Ответ.**  
### 1.  
```java
//-------------- begin --------------
class Runner {
    public static void main(String[] args) {
        Outer.Inner go = new Outer().Inner();
        go.go();
    }
}
class Outer {
    class Inner {
        void go() {
            System.out.println("Gone!");
        }
    }
}
//--------------- end ---------------
```  
### 2.  
### а)  
```java
//-------------- begin --------------
class Runner {
   static class Outer {
        class Inner {
            void go() {
                System.out.println("Gone!");
            }
        }
    } 
    public static void main(String[] args) {
        go.go();
    }
}
//--------------- end ---------------
```   
### б)  
```java
//-------------- begin --------------
class Runner {
   class Outer {
        class Inner {
            void go() {
                System.out.println("Gone!");
            }
        }
    } 
    public static void main(String[] args) {
        Outer.Inner go = new Runner().new Outer().new Inner();
        go.go();
    }
}
//--------------- end ---------------
```   

### 21. Что представляют собой элементы перечисления? Подсказка. Откомпилируйте фабричный класс из задачи inheritance1 и посмотрите, какие получились .class-файлы  

**Ответ.**  
Перечисление может, как и класс, иметь атрибуты и методы. Единственное отличие состоит в том, что константы перечисления 
являются общедоступными, статическими и окончательными (неизменяемыми — не могут быть переопределены). 
Перечисление нельзя использовать для создания объектов, и оно не может расширять другие классы (но может реализовывать интерфейсы).
Константы `enum` неявно объявляет анонимный класс, который является прямым подклассом непосредственно включающего enum class и является final.
**Источник.** <https://www.w3schools.com/java/java_enums.asp>

### 22. Как образуются имена вложенных и внутренних .class-файлов после компиляции? Приведите примеры.

**Ответ.**  
```java
public class Owne{
    public class Inner{
        
    }
}
```
Результатом компиляции будут являтся объктные модули: Owner.class и Owner$Inner.class.
Java создает каждому классу конкретный объктный модуль.
**Источник.** <https://elearn.epam.com/courses/course-v1:EPAM+JC+ext1/>

### 23. Может ли вложенный класс быть раннер-классом? Если да, то приведите пример, иначе поясните, почему нет.

**Ответ.**  
```java
public class Owner{
    
    public static class NestedMain{
        
        public static void main(String[] args) {
            System.out.println("Hello from NestedMain");
        }
    }
}
```

### 24. Может ли внутренний класс быть раннер-классом? Если да, то приведите пример, иначе поясните, почему нет.

**Ответ.**  
Не может. Как и в случае с методами и переменными экземпляра, внутренний класс связан с экземпляром окружающего его класса и имеет
прямой доступ к методам и полям этого объекта. Кроме того, поскольку внутренний класс связан с экземпляром, он не может 
сам определять какие-либо статические члены.  
**Источник.** <https://docs.oracle.com/javase/tutorial/java/javaOO/nested.html>

### 25. Может ли интерфейс иметь раннер-класс? Если да, то приведите пример, иначе поясните, почему нет.

**Ответ.**  
```java
public interface RunnerInt {
	
	public class Runner{
		public static void main(String[]args) {
			System.out.println("Hello");
		}
	}
}
```
