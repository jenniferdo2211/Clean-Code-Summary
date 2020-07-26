# CHAPTER 2: MEANINGFUL NAMES

## 2.1. USE INTENTION-REVEALING NAMES

#### DON'T: 
```
int d; // elapsed time in days
```
Name of variable should reveal its intention and tell what the variable is for.__
Avoid single-letter variable because it can cause confusion.

#### DO: 
```
int elapsedTimeInDays;
int daySinceCreation;
int daysSinceModification;
int fileAgeInDays;
```
Names are more clear.
___
#### DON'T: 
```
public List<int[]> getThem() {
    List<int[]> list1 = new ArrayList<int[]>();
    
    for (int[] x : theList) 
        if (x[0] == 4)
            list1.add(x);

    return list1;
}
```
Names of variables are not clear, we do not know what those variables are for.

#### DO: 
```
public List<int[]> getFlaggedCells() {
    List<int[]> flaggedCells = new ArrayList<int[]>();

    for (int[] call : gameBoard)
        if (cell[STATUS_VALUE] == FLAGGED)
            flaggedCells.add(cell);

    return flaggedCells;
}
```
When changing the names of variables, we understand the variables better and thus understand the code better.

## 2.2. AVOID DISINFORMATION

#### DON'T: 
```
Map<Integer, Account> accountList = new HashMap<>();
```
AccountList is not of type List -> disinformative.__
However, we still should not include type of variable in its name.

#### DO:
```
Map<Integer, Account> accounts = new HashMap<>();
Map<Integer, Account> accountGroup = new HashMap<>();
```
___

#### DON'T: 
```
Object XYZControllderForEfficientHandlingOfStrings;
Object XYZControllderForEfficientStorageOfStrings;
```
Names of 2 variables are hard to differentiate.

___

#### DON'T:
Don't use lowercase of L (l) which looks similar to number 1.__
Don't use uppercase of O which looks similar to number 0.__
It can be confusing.

## 2.3. MAKE MEANINGFUL DISTINCTIONS

#### DON'T:
Don't write code solely to satisfy complier/interpreter (eg. making small change in name to create another variable name)

___

#### NOT GOOD:
```
public static void copyChars(char a1[], char a2[]) {
    for (int i = 0; i < a1.length; i++) {
        a2[i] = a1[i];
    }
}
```
a1 and a2 are not disinformative but they are noninformative.

#### BETTER:
```
public static void copyChars(char source[], char destination[]) {
    ...
}
```

___

#### DON'T:
Don't name 2 different things with similar name. For example, ProfuctInfo and ProductData.__
The distinction is meaningless, Info and Data are just noise words like a, an, the.__
Noise words are redundant. __
__
The word "variable" should never appear in variable name.__
The word "table" should never appear in table name.__
For example, we have 2 classes Customer and CustomerObjects, how should we understand the different?__
Don't use names that are hard to distinguish.


## 2.4. USE PRONOUCEABLE NAMES
Pronounceability is a type of information.__
If we can't pronounce it, it's harder to discuss it.

#### DON'T:
```
class DtaRcrd102 {
    private final String pszqint = "102";
    public static Date genymdhms() {...} // generate year, month, day, hour, minite, second
    public static Date modymdhms() {...} // modify year, month, day, hour, minite, second 
}
```

#### DO:
```
class Customer {
    private final String recordId = "102";
    public static Date generateTimestamp() {...}
    public static Date modifyTimestamp() {...}
}
```

## 2.4. USE SEARCHABLE NAMES

Numbers or single-letter varible is are hard to search because they are presented everywhere.__

#### DON'T:
```
if (student_count > 7) {...}
```
7 is hard to search for when we have many 7 in different places with different meanings.

#### DO:
```
int MAX_STUDENTS_IN_CLASS = 7;
if (student_count > MAX_STUDENTS_IN_CLASS) {...}
```
MAX_STUDENTS_IN_CLASS is far easier to search for.

___

#### DON'T: 
```
Customer c = new Customer();
```
Letter 'c' is presented everywhere, it's hard to locate directly to the variable.__
EXCEPTION: we still can use single-letter variable ONLY IF it is local variable inside a short methods

#### DO: 
```
Customer customer = new Customer();
```
Longer name is easier to search for.

___

Here is an example that demonstrates the above idea.
#### DON'T:
```
for (int i = 0; i < 34; i++) {
    s += t[i] * 4 / 5;
}
```
What do 34, 4, and 5 stand for?__
What is s and t?__
i is just used in a short scope, so it's okays.


#### DO:
```
int NUMBER_OF_TASKS = 34;
int realDaysPerIdealDay = 4;
const int WORK_DAYS_PER_WEEK = 5;
int sum = 0;
for (int i = 0; i < NUMBER_OF_TASKS; i++) {
    int realTaskDays = taskEstimate[i] * realDaysPerIdealDay;
    int realTaskWeeks = realTaskDays / WORK_DAYS_PER_WEEK;
    sum += realTaskWeeks;
}
```
Note: sum is not really an useful name but it's searchable and better than s.

## 2.4. INTERFACES AND IMPLEMENTATIONS
Nowadays, we have 2 styles of naming interfaces and implementations.

Style 1:
```
public interface ICar {...}
public class Car implement ICar {...} 
```

Style 2: the authors prefer this one
```
public interface Car {...}
public class CarImp implement Car {...} 
```

## 2.5. AVOID MENTAL MAPPING
If the loop is small and short, it is okay for a loop counter to be a one-letter name (eg. i, j or k).__
But when the loop is large, and reader has to remember that letter 'r' stands for 'url', it's better to just replace 'r' with 'url' directly in the code.__
Clarity is king!!!

#### OKAY:
```
for (int i = 0; i < N; i++) {
    // few lines of code
}
```

___
#### DON'T:
for (String r : targetUrls) {
    ...
}

#### DO:
for (String url : targetUrls) {
    //...
}

## 2.6. CLASS NAMES should be nouns

## 2.7. METHOD NAMES
Should start with a verb.
```
public void postPayment() {...}
public String getName() {...}
public void setName() {...}
public boolean isReady() {...}
```

___
When constructors are overloaded,
#### NOT GOOD:
```
Complex fulcrumPoint = Complex(23.0);
```

#### BETTER:
```
Complex fulcrumPoint = Complex.FromRealNumber(23.0);
```

## 2.8. DON'T BE CUTE/HUMOUROUS IN CODE
holyHandGrenade -> should be deleteItems
whack() -> should be kill()

## 2.9. PICK ONE WORD PER CONCEPT
fetch - retrieve - get: similar meaning, pick only one for consistency.__
Other examples:__
* manager - controller - driver
* add - insert - append

#### DON'T: 
Don't use 1 word for 2 concepts or 2 words for 1 concept.

## 2.10. 
Feel free to use Computer Science Domain names because readers are programmers.__
When there are no CS terms for some concepts, use the domain knowledge terms.

## 2.11. Meaningful context

#### NOT GOOD:
```
private void printGuessStatistics(char candidate, int count) {
    String number;
    String verb;
    String pluralModifier;

    if (count == 0) {
        number = "no";
        verb = "are";
        pluralModifier = "s";
    } else if (count == 1) {
        number = "1";
        verb = "is";
        pluralModifier = "";
    } else {
        number = Integer.toString(count);
        verb = "are";
        pluralModifier = "s";
    }

    String guessMessage = String.format("There %s %s %s%s", verb, number, candidate, pluralModifier);
    print(guessMessage);
}
```
Function is a bit too long and the context is not clear.

#### BETTER:
```
public class GuessStatisticsMessage {
    private String number;
    private String verb;
    private String pluralModifier;

    public String make(char candidate, int count) {
        createPluralDependentMessageParts(count);
        return String.format("There %s %s %s%s", verb, number, candidate, pluralModifier);
    }

    private void createPluralDependentMessageParts(int count) {
        if (count == 0) {
            generateNoLetters();
        } else if (count == 1) {
            generateOneLetter();
        } else {
            generateManyLetters();
        }
    }

    private void generateNoLetters() {
        number = "no";
        verb = "are";
        pluralModifier = "s";
    }

    private void generateOneLetter() {
        number = "1";
        verb = "is";
        pluralModifier = "";
    }

    private void generateManyLetters() {
        number = Integer.toString(count);
        verb = "are";
        pluralModifier = "s";
    }
}
```