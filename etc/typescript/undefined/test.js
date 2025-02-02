var age = 10;
var a = 5;
// a = "hello";
var student = {
    name: "John",
    course: "ts",
    codingIQ: 100,
    code: function () {
        console.log("abc");
    },
};
// student.name = 1;
var studentId = 123;
var studentName = "Jason";
var GenderType;
(function (GenderType) {
    GenderType["Male"] = "male";
    GenderType["Female"] = "femail";
})(GenderType || (GenderType = {}));
function getStudentDetail(studentID) {
    return {
        studentID: 123,
        studentName: "Emily",
        // age: 20,
        gender: "female",
        subject: "Node.js",
        courseCompleted: true,
    };
}
//
var sendGreeting = function (message, username) {
    if (message === void 0) { message = "Hello"; }
    if (username === void 0) { username = "there"; }
    console.log("".concat(message, ", ").concat(username));
};
// sendGreeting();
// sendGreeting("Good morning");
// sendGreeting("Good morning", "teacher");
var Employee = /** @class */ (function () {
    function Employee(fullName, age, salary) {
        this.fullName = fullName;
        this.age = age;
        this.salary = salary;
    }
    return Employee;
}());
var employee1 = new Employee("John Doe", 30, 10000);
console.log(employee1);
