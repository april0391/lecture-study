"use strict";
class Department {
  id;
  name;
  employees = [];
  constructor(id, name) {
    this.id = id;
    this.name = name;
  }
  describe() {
    console.log(`Department (${this.id}): ${this.name}`);
  }
  addEmployee(employee) {
    this.employees.push(employee);
  }
  printEmployeeInformation() {
    console.log(this.employees.length);
    console.log(this.employees);
  }
}

const accounting = new Department("d1", "Accounting");
accounting.addEmployee("Max");
accounting.addEmployee("Manuel");
accounting.printEmployeeInformation();
// accounting.describe();
// const accountingCopy = {
//   name: "DUMMY",
//   describe: accounting.describe,
// };
// accountingCopy.describe();
