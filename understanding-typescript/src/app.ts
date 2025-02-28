/* const person: {
  name: string;
  age: number;
  hobbies: string[];
  role: [number, string];
} = {
  name: "Max",
  age: 30,
  hobbies: ["Sports", "Cooking"],
  role: [2, "author"],
}; */

enum Role {
  ADMIN = "admin",
  READ_ONLY = "read-only",
  AUTHOR = "author",
}

const person = {
  name: "Max",
  age: 30,
  hobbies: ["Sports", "Cooking"],
  role: Role.ADMIN,
};

person.role = Role.AUTHOR;

console.log(person);
