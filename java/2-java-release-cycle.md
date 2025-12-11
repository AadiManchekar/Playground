# Java Release Cycle and Ownership

| Version   | Year | Key Features Added                                   | Owner(s)                |
|-----------|------|------------------------------------------------------|-------------------------|
| Java 1.0  | 1996 | Initial release, basic OOP, applets                  | Sun Microsystems        |
| Java 5    | 2004 | Generics, annotations, enums, enhanced for loop      | Sun Microsystems        |
| Java 6    | 2006 | Scripting API, improvements, web services            | Sun Microsystems        |
| Java 7    | 2011 | try-with-resources, diamond operator, NIO.2          | Oracle (acquired Sun in 2010) |
| Java 8    | 2014 | LTS, Lambdas, Streams API, Functional Interfaces, Optional, parallelStream | Oracle / OpenJDK        |
| Java 9    | 2017 | Module system (Project Jigsaw), JShell (REPL), improved modularity | Oracle / OpenJDK        |
| Java 11   | 2018 | LTS, HTTP Client (HTTP/2), Flight Recorder, Local-variable syntax for lambdas `(var x, var y) -> x + y`, String methods: `isBlank()`, `lines()`, `strip()`, `repeat()` | Oracle / OpenJDK        |
| Java 17   | 2021 | LTS, sealed classes, pattern matching for switch, records, text blocks, switch expressions, enhanced random generators | Oracle / OpenJDK        |
| Java 21   | 2023 | LTS, record patterns, virtual threads (Project Loom), string templates, sequenced collections, unnamed classes, instance main methods | Oracle / OpenJDK        |

- **Sun Microsystems** originally developed Java and released the first versions.
- **Oracle** acquired Sun Microsystems in 2010 and now leads Java development.
- **OpenJDK** is the official open-source reference implementation of the Java platform, with contributions from Oracle and the wider community.

> **Note:** Java now follows a predictable release cycle, with new feature releases every six months and Long-Term Support (LTS) versions every three years.

---

## Code Examples of Major Java Features

```java
// Before Java 8
List<String> names = new ArrayList<>();
for (Person person : people) {
    if (person.getAge() > 18) {
        names.add(person.getName());
    }
}
Collections.sort(names);

// Java 8 way: Lambdas and Streams
List<String> names = people.stream()
    .filter(p -> p.getAge() > 18)
    .map(Person::getName)
    .sorted()
    .collect(Collectors.toList());

// Java 8: Optional
Optional<Person> personOpt = people.stream().findFirst();
personOpt.ifPresent(p -> System.out.println(p.getName()));

// Java 14: Pattern matching for instanceof
if (obj instanceof String s) {
    System.out.println(s.toUpperCase()); // No cast needed!
}

// Java 14: Records
record Point(int x, int y) { }
Point p = new Point(3, 4);
System.out.println(p.x()); // Auto-generated accessors

// Java 17: Sealed classes
public sealed interface Shape 
    permits Circle, Rectangle, Square {}

// Java 17: Pattern matching in switch
String formatted = switch (obj) {
    case Integer i -> String.format("int %d", i);
    case Long l    -> String.format("long %d", l);
    case Double d  -> String.format("double %f", d);
    case String s  -> String.format("String %s", s);
    default        -> obj.toString();
};

// Java 21: Virtual threads (Project Loom)
// Before
ExecutorService executor = Executors.newFixedThreadPool(100);
for (int i = 0; i < 10000; i++) {
    executor.submit(() -> handleRequest());
}
// After
try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
    for (int i = 0; i < 10000; i++) {
        executor.submit(() -> handleRequest());
    }
}
```