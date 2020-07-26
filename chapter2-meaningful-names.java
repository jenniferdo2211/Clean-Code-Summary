// CHAPTER 2: MEANINGFUL NAMES

// ===================================================

// 2.1. USE INTENTION-REVEALING NAMES

// DON'T: 
// name of variable should reveal its intention and tell what the variable is for.
// avoid single-letter variable -> confusing.
int d; // elapsed time in days

// DO: names are more clear.
int elapsedTimeInDays;
int daySinceCreation;
int daysSinceModification;
int fileAgeInDays;

// ===

// DON'T: 
// names of variables are not clear, we do not know what those variables are for.
public List<int[]> getThem() {
    List<int[]> list1 = new ArrayList<int[]>();
    
    for (int[] x : theList) 
        if (x[0] == 4)
            list1.add(x);

    return list1;
}

// DO: 
// when changing the names of variables, 
// we understand the variables better and thus understand the code better.
public List<int[]> getFlaggedCells() {
    List<int[]> flaggedCells = new ArrayList<int[]>();

    for (int[] call : gameBoard)
        if (cell[STATUS_VALUE] == FLAGGED)
            flaggedCells.add(cell);

    return flaggedCells;
}

// ===================================================

// 2.2. AVOID DISINFORMATION

// DON'T: 
// accountList is not of type List -> disinformative.
// However, we still should not include type of variable in its name.
Map<Integer, Account> accountList = new HashMap<>();

// DO:
Map<Integer, Account> accounts = new HashMap<>();
Map<Integer, Account> accountGroup = new HashMap<>();

// =====

// DON'T: 
// names of 2 variables are hard to differentiate.
Object XYZControllderForEfficientHandlingOfStrings;
Object XYZControllderForEfficientStorageOfStrings;

// =====

// DON'T:
// don't use lowercase of L (l) which looks similar to number 1.
// don't use uppercase of O which looks similar to number 0.
// it can be confusing.

// ===================================================

// 2.3. MAKE MEANINGFUL DISTINCTIONS

// DON'T:
// don't write code solely to satisfy complier/interpreter.

// =====

// NOT GOOD: a1 and a2 are not disinformative but they are noninformative.
public static void copyChars(char a1[], char a2[]) {
    for (int i = 0; i < a1.length; i++) {
        a2[i] = a1[i];
    }
}

// BETTER:
public static void copyChars(char source[], char destination[]) {
    // ...
}

// =====

// DON'T:
// Don't name 2 different things with similar name.
// For example, ProfuctInfo and ProductData.
// The distinction is meaningless, Info and Data are just noise words like a, an, the.
// Noise words are redundant. 
// The word "variable" should never appear in variable name.
// The word "table" should never appear in table name.
// For example, we have 2 classes Customer and CustomerObjects, how should we understand the different?
// Don't use names that are hard to distinguish.

// ===================================================

// 2.4. USE PRONOUCEABLE NAMES
// Pronounceability is a type of information.
// If we can't pronounce it, it's harder to discuss it.

// DON'T:
class DtaRcrd102 {
    private final String pszqint = "102";
    public static Date genymdhms() {...} // generate year, month, day, hour, minite, second
    public static Date modymdhms() {...} // modify year, month, day, hour, minite, second 
}

// DO:
class Customer {
    private final String recordId = "102";
    public static Date generateTimestamp() {...} // generate year, month, day, hour, minite, second
    public static Date modifyTimestamp() {...} // modify year, month, day, hour, minite, second 
}

// ===================================================

// 2.4. USE SEARCHABLE NAMES

// Numbers or single-letter varible is are hard to search because they are presented everywhere.
// DON'T: 7 is hard to search for when we have many 7 in different places with different meanings.
if (student_count > 7) {...}

// DO: MAX_STUDENTS_IN_CLASS is far easier to search for.
int MAX_STUDENTS_IN_CLASS = 7;
if (student_count > MAX_STUDENTS_IN_CLASS) {...}

// =====

// DON'T: letter 'c' is presented everywhere, it's hard to locate directly to the variable.
// EXCEPTION: we still can use single-letter variable ONLY IF it is local variable inside a short methods
Customer c = new Customer();

// DO: longer name is easier to search for.
Customer customer = new Customer();

// =====

// Here is an example that demonstrates the above idea.
// DON'T:
// what do 34, 4, and 5 stand for?
// what is s and t?
// i is just used in a short scope, so it's okays
for (int i = 0; i < 34; i++) {
    s += t[i] * 4 / 5;
}


// DO:
// Note: sum is not really an useful name but it's searchable and better than s
int NUMBER_OF_TASKS = 34;
int realDaysPerIdealDay = 4;
const int WORK_DAYS_PER_WEEK = 5;
int sum = 0;
for (int i = 0; i < NUMBER_OF_TASKS; i++) {
    int realTaskDays = taskEstimate[i] * realDaysPerIdealDay;
    int realTaskWeeks = realTaskDays / WORK_DAYS_PER_WEEK;
    sum += realTaskWeeks;
}

// ===================================================

// 2.4. INTERFACES AND IMPLEMENTATIONS
// Nowadays, we have 2 styles of naming interfaces and implementations

// Style 1:
public interface ICar {...}
public class Car implement ICar {...} 

// Style 2: the authors prefer this one
public interface Car {...}
public class CarImp implement Car {...} 

// ===================================================

// 2.5. AVOID MENTAL MAPPING
// If the loop is small and short, it is okay for a loop counter to be a one-letter name (eg. i, j or k).
// But when the loop is large, and reader has to remember that letter 'r' stands for 'url', it's better to just replace 'r' with 'url' directly in the code.
// Clarity is king
for (int i = 0; i < N; i++) {
    // few lines of code
}

// DON'T:
for (String r : targetUrls) {
    // ...
}

// DO:
for (String url : targetUrls) {
    //...
}

// ===================================================

// 2.6. CLASS NAMES - should be a noun

// ===================================================

// 2.7. METHOD NAMES
// starts with a verb
public void postPayment() {...};
public String getName() {...};
public void setName() {...};
public boolean isReady() {...};

// when constructors are overloaded
// SHOULDN'T:
Complex fulcrumPoint = Complex(23.0);

// BETTER:
Complex fulcrumPoint = Complex.FromRealNumber(23.0);

// ===================================================

// 2.8. DON'T BE CUTE/HUMOUROUS IN CODE
holyHandGrenade -> should be deleteItems
whack() -> should be kill()

// ===================================================

// 2.9. PICK ONE WORD PER CONCEPT
// fetch - retrieve - get: similar meaning, pick only one for consistency
// manager - controller - driver
// add - insert - append

// DON'T: use 1 word for 2 concepts or 2 words for 1 concept

// ===================================================

// 2.10. Use Computer Science Domain names because readers are programmers
// when there are no CS terms for some concepts, use the domain knowledge terms.

// ===================================================

// 2.11. Meaningful context

// SHOULDN'T:
// function is a bit too long
// and the context is not clear
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

// BETTER:
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
