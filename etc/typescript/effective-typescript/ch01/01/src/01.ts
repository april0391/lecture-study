import { log } from "console";

interface Person {
  name: string;
  age: number;
}

const people: Person[] = [
  {
    name: "김길동",
    age: 10,
  },
  {
    name: "이길동",
    age: 20,
  },
  {
    name: "박길동",
    age: 30,
  },
];

const printPeople = (people: Person[]) => {
  for (const person of people) {
    console.log(person.name, person.age);
  }
};

printPeople(people);
