"use strict";
let add;
add = (n1, n2) => n1 + n2;
class Person {
    name;
    age = 30;
    constructor(name) {
        if (name) {
            this.name = name;
        }
    }
    greet(phrase) {
        console.log(phrase + " " + this.name);
    }
}
let user1;
user1 = new Person("Max");
console.log(user1.greet("Hi there - I am"));
