let age: number = 10;

let a = 5;
// a = "hello";

let student = {
  name: "John",
  course: "ts",
  codingIQ: 100,
  code: function () {
    console.log("abc");
  },
};

// student.name = 1;

let studentId: number = 123;
let studentName: string = "Jason";

enum GenderType {
  Male = "male",
  Female = "femail",
}

interface Student {
  readonly studentID: number;
  studentName: string;
  age?: number;
  gender: "male" | "female";
  subject: string;
  courseCompleted: boolean;
  // addComment(comment: string): string;
  addComment?: (comment: string) => string;
}

function getStudentDetail(studentID: number): Student {
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

const sendGreeting = (message = "Hello", username = "there"): void => {
  console.log(`${message}, ${username}`);
};

// sendGreeting();
// sendGreeting("Good morning");
// sendGreeting("Good morning", "teacher");

class Employee {
  constructor(
    public fullName: string,
    public age: number,
    private salary: number
  ) {}
}

let employee1 = new Employee("John Doe", 30, 10000);
console.log(employee1);
