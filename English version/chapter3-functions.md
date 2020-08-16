### [Back to Content Table](https://github.com/jenniferdo2211/Clean-Code-Summary)

# CHAPTER 3: FUNCTIONS

## 3.1. SMALL

#### Rule 1: Functions should be small.
#### Rule 2: Functions should be smaller than that.<br /><br />

Example of a small function: `Listing 3-3: HtmlUtil.java`<br />
```
public static String renderPageWithSetupAndTeardowns(PageData pageData, boolean isSuite) throws Expcetion {
    if (isTestPage(pageData)) 
        includeSetupAndTeardownPages(pageData, isSuite);
    return pageData.getHtml();
}
```

#### How small should they be?
* Never be 100 lines long<br />
* Hardly be 20 lines long<br />
* Should be 4 lines long or even less<br />

This implies that...
* The blocks of `if` statement, `else` statement, `while` statement and so on should be **1 line long** (usually it's a function call).<br />
-> Keep the enclosing function small and add documentary value (if called function has a nicely descriptive name).
* Functions should not be large enough to hold a nested structure <br />
-> **Max indent level is 1 or 2** and all lines are at the **same abstraction level**.

## 3.2. DO ONE THING

```
Functions should do one thing. They should do it well. They should do it only.
```

#### (´･ω･`)? How to know if a function is doing one thing?<br />
* Way 1: A function is doing one thing if we can describe it as **a brief `TO` graph**.
* Way 2: A function is doing one thing if we **cannot break it into smaller parts**.

Eg. `Listing 3-3: HtmlUtil.java` from 3.1 is described as...<br/>
TO RenderPageWithSetupAndTeardowns, we check to see whether the page is a test page, and if so, we include the setups and teardowns. In either case, we render the page in HTML.<br/>
-> Listing 3-3: HtmlUtil.java is doing one thing.

## GOOD STACKEXCHANGE POST: [Ideal length of a function](https://softwareengineering.stackexchange.com/questions/133404/what-is-the-ideal-length-of-a-method-for-you)

## 3.3. STEPDOWN RULE

We want every function to be followed by those at the next level of abstraction so that we can read the program, descending one level of abstraction at a time as we read down the list of functions.

## 3.4. SWITCH STATEMENTS

Generally, `switch` statements can be tolerate when it appears **only once**, is used to create **polymorphic objects**, and are hidden behind the **inheritance relationship** so that no one can see them.

Consider this function:
```
public Money calculatePay (Employee e) throws Invalid EmployeeType {
    switch (e.type) {
        case COMMISSIONED:
            return calculateCommissionedPay(e);
        case HOURLY:
            return calculateHourlyPay(e);
        case SALARIED:
            return calculateSalariedPay(e);
        default:
            throw new InvalidEmployeeType(e.type);
    }
}
```

#### Problems of this function:
* Large: when new employee types are added, it will grow
* Does more than 1 thing
* Violates Single Responsibility Rule (SPR) because there are more than 1 reason for it to change
* Violates Open CLosed Principle (OCP) because it must change when a new type is added
* Probably worst problem: unlimited number of other functions that will also use those `switch... case...` statements. Eg. isPayDay(Employee e, Date date) or deliverPay(Employee e, Date date)
```
public boolean isPayDay(Employee e, Date date) throws Invalid EmployeeType {
    switch (e.type) {
        case COMMISSIONED:
            return isCommissionedPayDay(e);
        case HOURLY:
            return isHourlyPayDay(e);
        case SALARIED:
            return isSalariedPayDay(e);
        default:
            throw new InvalidEmployeeType(e.type);
    }
}
\\---------------------------

public void deliverPay(Employee e, Date date) throws Invalid EmployeeType {...}
```
<hr style="border: 1px solid gray" />

#### Solution: use Abstract Factory
```
public abstract class Employee {
    public abstract boolean isPayday();
    public abstract Money calculatePay();
    public abstract void deliverPay(Money pay);
}
\\---------------------------

public class CommissionedEmployee extends Employee {...}
public class HourlyEmployee extends Employee {...}
public class SalariedEmployee extends Employee {...}
\\---------------------------

public interface EmployeeFactory {
    public Employee makeEmployee (EmployeeRecord r) throws InvalidEmployeeType;
}
\\---------------------------

public class EmployeeFactoryImpl implements EmployeeFactory {
    public Employee makeEmployee (EmployeeRecord r) throws InvalidEmployeeType {
        switch (r.type) {
        case COMMISSIONED:
            return new CommissionedEmployee(r);
        case HOURLY:
            return new HourlyEmployee(r);
        case SALARIED:
            return new SalariedEmployee(r);
        default:
            throw new InvalidEmployeeType(r.type);
    }
    }
}
```

Explanation:<br/>
* **Before:** we have 3 functions `isPayday, calculatePay, deliverPay`, in each function, we have a `switch... case...` statement to call different functions based on different types of employees.<br /> We divide our cases based on functions and then by employee types.
* **After:** we use 3 classes for 3 different types of Employees, in each class, we have 3 functions `isPayday, calculatePay, deliverPay`.<br />
We divide our cases based on employee types and then by functions. This will be better structured.

## 3.4. DESCRIPTIVE NAMES (Please read [chapter 2](https://github.com/jenniferdo2211/Clean-Code-Summary/blob/master/English%20version/chapter2-meaningful-names.md) for more details)

## 3.5. FUNCTION ARGUMENTS

The number of arguments for a function should be 0, next comes 1, followed closely by 2.<br/>
3 arguments should be avoided where possible. More than 3 requires very special justification - and they shouldn't be used anyway.

### Single Argument Functions (1 Argument or Monadic Functions):

Two very common reasons to pass a single argument into a function
* Asking a question about the argument.<br/>
Eg. `fileExists("myFile")`
* Operating on that argument, transforming it into something else and then return it.<br/>
Eg. `InputStream fileOpen("myFile")` -> transforming a `String` into `InputStream`

A less common form of single argument function is an event. The overall program is meant to interpret the function call as an event and use the argument to alter the state of the system.
In this form, there is one input but no output. The nqme should be very clear to reader that this is an event<br/>
Eg. `passwordAttemptFailedNTimes(int attempts)`

Try to avoid arguments that don't follow these forms.<br/>
Eg. `void includeSetupPageInfo(StringBuffer pageText)`<br/>
Using an output argument (`pageText`) with no return (`void`) instead of return value (`return ...`) is confusing.<br/>
Indeed, `StringBuffer transform(StringBuffer in)` is better than `void transform(StringBuffer out)` even if the implementation of the first case simple return the `in`

### Flag Arguments (Boolean Arguments)

Passing a boolean into a function is truly terrible practice. It immediately complicates the signature of the method and proclaims that the method is doing more than 1 thing. It does one thing if the flag (boolean argument) is true and another if the flag is false.

render(boolean isSuite) should be split into 2 functions: renderForSuite() and renderForSingleTest()

### Two-Argument Function (Dyadic Functions)

A function with 2 arguments is harder to understand than a function with 1 argument.<br>
Eg. `writeField(name)` is easier to understand than `writeField(outputStream, name)`.<br> 
Though the meaning of both is clear, the first glides past the eye, easily depositing its meaning. The second requires a short pause until we learn to ignore the first argument. And that, of course, eventually results in problems because we should never ignore any part of code. The parts we ignore are where the bugs will hide.

Perfect case for two arguments is `new Point(x, y)`.<br>

Even the obvious function like assertEquals(expected, actual) are problematic. How many times have you put the `actual` where the `expected` should be? The two arguments have no natural ordering. The ordering `expected, actual` takes time to learn.

Two-argument functions aren't evil but you should think of how to transform them into single arugment functions.<br>
Eg. from `writeField(outputStream, name)`, you can...
* make `writeField(name)` as a member of `outputStream`, so you can call it as `outputStream.writeField(name)`
* make `outputStream` as a memeber of current class, so you don't have to pass it.
* extract a new class `FieldWriter` that takes `outputStream` in constructor and has a `write(name)` method

### Three-Arugment Function (Triads)
Functions with three arguments are significantly harder to understand than functions with two arguments. The issues of ordering, pausing and ignoring are more than doubled. 



### [Back to Content Table](https://github.com/jenniferdo2211/Clean-Code-Summary)