const person = {
  name: "kim",
  age: 20,
  addr: {
    city: "seoul",
    zipcode: 123,
  },
};

const {
  name,
  addr: { city },
} = person;

// console.log(addr);
console.log(city);
