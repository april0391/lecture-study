import { log } from "console";

abstract class Department {
  static fiscalYear = 2020;
  protected employees: string[] = [];

  constructor(protected readonly id: string, public name: string) {}

  abstract describe(this: Department): void;

  static createEmployee(name: string) {
    return { name };
  }

  addEmployee(employee: string) {
    this.employees.push(employee);
  }

  printEmployeeInformation() {
    console.log(this.employees.length);
    console.log(this.employees);
  }
}

class ITDepartment extends Department {
  constructor(id: string, public admins: string[] = ["Max"]) {
    super(id, "IT");
  }

  describe() {
    console.log("IT Department - ID: " + this.id);
  }
}

class AccountingDepartment extends Department {
  private lastReport: string;
  private static instance: AccountingDepartment;

  private constructor(id: string, private reports: string[]) {
    super(id, "Accounting");
    this.lastReport = reports[0];
  }

  static getInstance() {
    if (!AccountingDepartment.instance) {
      this.instance = new AccountingDepartment("d1", []);
    }
    return this.instance;
  }

  get mostRecentReport() {
    if (this.lastReport) {
      return this.lastReport;
    }
    throw new Error("No report found.");
  }

  set mostRecentReport(value: string) {
    if (!value) {
      throw new Error("Please pass in a valid value!");
    }
    this.addReport(value);
  }

  describe() {
    console.log("Accounting Department - ID: " + this.id);
  }

  addEmployee(employee: string): void {
    if (employee === "Max") {
      return;
    }
    this.employees.push(employee);
  }

  addReport(text: string) {
    this.reports.push(text);
    this.lastReport = text;
  }

  printReports() {
    console.log(this.reports);
  }
}

const employee1 = Department.createEmployee("Max");
log(employee1);

const it = new ITDepartment("d1");
it.addEmployee("Max");
it.addEmployee("Manuel");
// it.printEmployeeInformation();
// console.log(it);

const accounting1 = AccountingDepartment.getInstance();
accounting1.addReport("Something went wrong...");
console.log(accounting1.mostRecentReport);
console.log(accounting1);
const accounting2 = AccountingDepartment.getInstance();
log(accounting1 === accounting2);

// accounting.describe();

// const accountingCopy = {
//   name: "DUMMY",
//   describe: accounting.describe,
// };

// accountingCopy.describe();
