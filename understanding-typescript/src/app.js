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
var Role;
(function (Role) {
    Role["ADMIN"] = "admin";
    Role["READ_ONLY"] = "read-only";
    Role["AUTHOR"] = "author";
})(Role || (Role = {}));
var person = {
    name: "Max",
    age: 30,
    hobbies: ["Sports", "Cooking"],
    role: Role.ADMIN,
};
person.role = Role.AUTHOR;
console.log(person);
