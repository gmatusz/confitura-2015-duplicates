## Solution

Simple solution involving use of HashSet to detect duplication.

Solution should works in o(n) time for almost all input(unless hash function hits to many conflicts). It also takes additional o(n) memory, which is a little cons.

On the hash function is selected 131 * name.hashCode() + age

Assuming that records of negative or greater than 130 age will be incidental, the prime number 131 should provide a uniform distribution of hash values.

Major benefits of this method are:
1) Input list can be read as stream. We do not need keep it in memory.
2) We can easily keep order items from the original list
3) Alternatively to 2) - We can easily make concurrent solution, replacing HashSet with ConcurrentHashMap


# How to build

```
mvn clean install
```

# How to run

```
$ java -jar uniq.jar
java -jar uniq.jar -f <format> -i <inputFile> -o <outputFile>
format :== rfc4180|excel|tdf|mysql|default

$ java -jar uniq.jar -i example/personList.csv
John Smiths,28
John Doe,54
John Smiths,13
John Doe,28
John Doe,53
```

# [Confitura 2015](http://tech.viacom.com/warsawsdc/confitura2015/)
## Duplicates

You have *very big* list of elements. Please provide best solution to detect and remove duplicated elements.

Please provide a solution and **comments** about its benefits and drawbacks. Please give us complexity (`O(n)`, `O(n^2)`, `O(ln(n))`, ...). Please think about custom classes like:

```java
class Person {
    String name;
    int age;
}
```

You can check contest bye-laws [here](http://tech.viacom.com/warsawsdc/confitura2015/Regulamin_konkurs_Viacom_programmer_adventure_2015.pdf).

Check out our Confitura 2015 site [here](http://tech.viacom.com/warsawsdc/confitura2015/)

We are hiring! Visit our [career site](http://tech.viacom.com/careers/).
