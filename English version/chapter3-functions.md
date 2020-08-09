### [Back to Content Table](https://github.com/jenniferdo2211/Clean-Code-Summary)

# CHAPTER 3: FUNCTIONS

## 3.1. SMALL

#### Rule 1: Functions should be small.<br />
#### Rule 2: Functions should be smaller than that.

Example of a small function.<br />
`Listing 3-3: HtmlUtil.java`<br />
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
* The blocks of `if` statement, `else` statement, `while` statement and so on should be 1 line long (usually it's a function call).<br />
-> Keep the enclosing function small and add documentary value (if called function has a nicely descriptive name).
* Functions should not be large enough to hold a nested structure <br />
-> Max indent level is 1 or 2 and all lines are at the same abstraction level.

## 3.2. DO ONE THING

```
Functions should do one thing. They should do it well. They should do it only.
```

#### (´･ω･`)? How to know if a function is doing one thing?<br />
* Way 1: A function is doing one thing if we can describe it as a brief `TO` graph.
* Way 2: A function is doing one thing if we cannot break it into smaller parts.

Eg. `Listing 3-3: HtmlUtil.java` from 3.1 is described as...<br/>
TO RenderPageWithSetupAndTeardowns, we check to see whether the page is a test page, and if so, we include the setups and teardowns. In either case, we render the page in HTML.<br/>
-> Listing 3-3: HtmlUtil.java is doing one thing.

## GOOD STACKEXCHANGE POST: [Ideal length of a function](https://softwareengineering.stackexchange.com/questions/133404/what-is-the-ideal-length-of-a-method-for-you)

## 3.3. STEPDOWN RULE

We want every function to be followed by those at the next level of abstraction so that we can read the program, descending one level of abstraction at a time as we read down the list of functions.

## 3.4. SWITCH STATEMENTS

Generally, `switch` statements can be tolerate when it appears only once, is used to create polymorphic objects, and are hidden behind the inheritance relationship so that no one can see them.

Considering this function:
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

## 3.4. DESCRIPTIVE NAMES (Please read chapter 2 for more details)

## 3.5. FUNCTION ARGUMENTS

The number of arguments for a function should be 0 (niladic), next comes 1 (monadic), followed closely by 2 (dyadic).<br/>
3 arguments (triadic) should be avoided where possible. More than 3 (polyadic) requires very special justification - and they shouldn't be used anyway.

[to be continued...]

### [Back to Content Table](https://github.com/jenniferdo2211/Clean-Code-Summary)