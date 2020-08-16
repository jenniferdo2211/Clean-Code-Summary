### [Quay lại mục lục](https://github.com/jenniferdo2211/Clean-Code-Summary/tree/master/B%E1%BA%A3n%20ti%E1%BA%BFng%20Vi%E1%BB%87t)

# CHƯƠNG 2: TÊN CÓ NGHĨA

## 2.1. TÊN PHẢI THỂ HIỆN MỤC ĐÍCH/Ý ĐỊNH CỦA BIẾN

#### ĐỪNG: 
```
int d; // thời gian tính theo ngày
```
Tên của biến phải thể hiện mục đích của biến và biến đó dùng để làm gì.<br>
Không nên đặt tên biến linh tinh rồi giải thích lại ý nghĩa của biến bằng comment.<br>
Tránh những biến chỉ có 1 chữ cái vì nó có thể gây sự khó hiểu hoặc hiểu nhầm.

#### NÊN: 
Bản dịch thô tiếng Việt:
```
int thoiGianTinhTheoNgay;
int soNgayKeTuLucKhoiTao;
int soNgayKeTuLucThayDoi;
int fileAgeInDays;
```
Bản gốc tiếng Anh:
```
int elapsedTimeInDays;
int daySinceCreation;
int daysSinceModification;
int fileAgeInDays;
```

Đặt tên như trên sẽ rõ ràng và dễ hiểu hơn.

<hr style="border: 1px solid gray" />

#### ĐỪNG: 
Bản dịch thô tiếng Việt:
```
public List<int[]> layHet() {
    List<int[]> danhSach1 = new ArrayList<int[]>();
    
    for (int[] x : danhSach) 
        if (x[0] == 4)
            danhSach1.add(x);

    return danhSach1;
}
```
Bản gốc tiếng Anh:
```
public List<int[]> getThem() {
    List<int[]> list1 = new ArrayList<int[]>();
    
    for (int[] x : theList) 
        if (x[0] == 4)
            list1.add(x);

    return list1;
}
```

Đặt tên như trên rất khó hiểu, người đọc code sẽ không hiểu được những biến đó để làm gì và những dòng code này đang cố gắng làm gì.<br>
Người đọc code có thể thắc mắc:
* `Het` trong `layHet` là lấy hết cái gì?
* `danhSach1` và `danhSach` là gì?
* Số `0` và số `4` trong `x[0] == 4` có ý nghĩa gì? Tại sao lại là số `0` và số `4` mà không phải số khác?

#### NÊN:
Bản dịch thô tiếng Việt:
```
private final int TINH_TRANG = 0;
private final int BI_DANH_DAU = 4;

public List<int[]> layNhungOBiDanhDau() {
    List<int[]> nhungOBiDanhDau = new ArrayList<int[]>();
    
    for (int[] oCo : banCo) 
        if (oCo[TINH_TRANG] == BI_DANH_DAU)
            nhungOBiDanhDau.add(oCo);

    return nhungOBiDanhDau;
}
```
Bản gốc tiếng Anh:
```
public List<int[]> getFlaggedCells() {
    List<int[]> flaggedCells = new ArrayList<int[]>();

    for (int[] cell : gameBoard)
        if (cell[STATUS_VALUE] == FLAGGED)
            flaggedCells.add(cell);

    return flaggedCells;
}
```

Khi đổi tên như trên, code trở nên dễ hiểu hơn.

## 2.2. TRÁNH GÂY HIỂU NHẦM/THÔNG TIN SAI LỆCH

#### ĐỪNG: 
```
Map<Integer, Account> accountList = new HashMap<>();
```
`AccountList` (Danh sách các tài khoản) không phải thuộc loại List (danh sách), mặc dù trong cuộc sống đời thường thì nó có thể thực sự là danh sách.<br />
Thường thì ta không nên để loại của biến vào tên của biến.

#### NÊN:
```
Map<Integer, Account> accounts = new HashMap<>();
Map<Integer, Account> accountGroup = new HashMap<>();
```
`accounts` (các tài khoản) - `accountGroup` (nhóm các tài khoản)

<hr style="border: 1px solid gray" />

#### ĐỪNG: 
```
Object XYZDieuKhienDeXuLyHieuQuaCacChuoi;
Object XYZDieuKhienDeLuuTruHieuQuaCacChuoi;
```
Tên của hai biến quá khó để phân biệt.

<hr style="border: 1px solid gray" />

#### ĐỪNG:
* ĐỪNG sử dụng chữ L thường (l) vì nó trông như số 1.<br />
* ĐỪNG sử dụng chữ o hoa (O) vì nó trông như số 0.<br />

## 2.3. SỰ KHÁC BIỆT PHẢI CÓ Ý NGHĨA

#### ĐỪNG:
ĐỪNG viết code chỉ để thỏa mãn trình biên dịch (compiler), ví dụ: thay đổi tên của 1 biến một chút để tạo ra 1 tên biến mới, taiKhoanNganHang - taiKhoanNagnHang - taiKhoanNganHanggg

<hr style="border: 1px solid gray" />

#### KHÔNG NÊN:
Bản dịch thô tiếng Việt:
```
public static void saoChepChuoi(char a1[], char a2[]) {
    for (int i = 0; i < a1.length; i++) {
        a2[i] = a1[i];
    }
}
```
Bản gốc tiếng Anh:
```
public static void copyChars(char a1[], char a2[]) {
    for (int i = 0; i < a1.length; i++) {
        a2[i] = a1[i];
    }
}
```
a1 và a2 không gây sai lệch thông tin nhưng chúng là những cái tên vô nghĩa và không giúp hàm dễ hiểu hơn.

#### NÊN:
Bản dịch thô tiếng Việt:
```
public static void saoChepChuoi(char nguon[], char dichDen[]) {
    //...
}
```
Bản gốc tiếng Anh:
```
public static void copyChars(char source[], char destination[]) {
    //...
}
```

<hr style="border: 1px solid gray" />

#### ĐỪNG:
* ĐỪNG đặt tên 2 thứ khác nhau với tên na ná nhau. Ví dụ, `ProductInfo` (thông tin sản phẩm) và `ProductData` (dữ liệu về sản phẩm).<br />
Sự khác biệt trong tên của hai biến này không mang lại nhiều ý nghĩa vì `Info` (thông tin) có nghĩa gần giống với `Data` (dữ liệu).<br />
* Từ `bien` (biến) không bao giờ nên có trong tên biến, ví dụ `soPhongBien`.
* Tương tự, từ `bang` (bảng) không bao giờ nên có trong tên của biến thuộc loại bảng.
* ĐỪNG sử dụng tên khó phân biệt. Ví dụ, ta có 2 lớp (class) Customer (Khach hang) and CustomerObjects (Đối tượng khách hàng), chúng ta nên hiểu chúng khác nhau như nào?


## 2.4. USE PRONOUCEABLE NAMES
Sử dụng tên mà ta có thể phát âm được, đừng đặt tên linh tinh.<br />
Nếu chúng ta không thể đọc được tên biến (vd. `nfdsnke`), sẽ khó hơn để bàn về nó.

#### ĐỪNG:
```
class DtaRcrd102 {
    private final String pszqint = "102";
    public static Date genymdhms() {...} // generate year, month, day, hour, minite, second
    public static Date modymdhms() {...} // modify year, month, day, hour, minite, second 
}
```

#### NÊN:
```
class Customer {
    private final String recordId = "102";
    public static Date generateTimestamp() {...}
    public static Date modifyTimestamp() {...}
}
```


## 2.4. USE SEARCHABLE NAMES

Numbers or single-letter varible is are hard to search because they are presented everywhere.<br />

#### ĐỪNG:
```
if (student_count > 7) {...}
```
7 is hard to search for when we have many 7 in different places with different meanings.

#### NÊN:
```
int MAX_STUDENTS_IN_CLASS = 7;
if (student_count > MAX_STUDENTS_IN_CLASS) {...}
```
MAX_STUDENTS_IN_CLASS is far easier to search for.


<hr style="border: 1px solid gray" />


#### ĐỪNG: 
```
Customer c = new Customer();
```
Letter 'c' is presented everywhere, it's hard to locate directly to the variable.<br />
EXCEPTION: we still can use single-letter variable ONLY IF it is local variable inside a short methods

#### NÊN: 
```
Customer customer = new Customer();
```
Longer name is easier to search for.


<hr style="border: 1px solid gray" />


Here is an example that demonstrates the above idea.
#### ĐỪNG:
```
for (int i = 0; i < 34; i++) {
    s += t[i] * 4 / 5;
}
```
What do 34, 4, and 5 stand for?<br />
What is s and t?<br />
Variable i is just used in a short scope, so it's okays.


#### NÊN:
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
If the loop is small and short, it is okay for a loop counter to be a one-letter name (eg. i, j or k).<br />
But when the loop is large, and reader has to remember that letter 'r' stands for 'url', it's better to just replace 'r' with 'url' directly in the code.<br />
#### Clarity is king!!!

#### OKAY:
```
for (int i = 0; i < N; i++) {
    // few lines of code
}
```


<hr style="border: 1px solid gray" />

#### ĐỪNG:
```
for (String r : targetUrls) {
    ...
}
```

#### NÊN:
```
for (String url : targetUrls) {
    //...
}
```

## 2.6. CLASS NAMES should be nouns


## 2.7. METHOD NAMES
Method names should start with a verb.
```
public void postPayment() {...}
public String getName() {...}
public void setName() {...}
public boolean isReady() {...}
```


<hr style="border: 1px solid gray" />

When constructors are overloaded,
#### KHÔNG NÊN:
```
Complex fulcrumPoint = Complex(23.0);
```

#### NÊN:
```
Complex fulcrumPoint = Complex.FromRealNumber(23.0);
```


## 2.8. ĐỪNG BE CUTE/HUMOUROUS IN CODE
holyHandGrenade -> should be deleteItems<br />
whack() -> should be kill()


## 2.9. PICK ONE WORD PER CONCEPT
fetch - retrieve - get: similar meaning, pick only one for consistency.<br />
Other examples:<br />
* manager - controller - driver
* add - insert - append

#### ĐỪNG: 
ĐỪNG use 1 word for 2 concepts or 2 words for 1 concept.<br />
#### 1 word for 1 concept only!!!


## 2.10. 
Feel free to use Computer Science Domain names because readers are programmers.<br />
When there are no CS terms for some concepts, use the domain knowledge terms.


## 2.11. Meaningful context

#### KHÔNG NÊN:
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

#### NÊN:
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

### [Quay lại mục lục](https://github.com/jenniferdo2211/Clean-Code-Summary/tree/master/B%E1%BA%A3n%20ti%E1%BA%BFng%20Vi%E1%BB%87t)