type A = number extends string ? string : number;

type StringNumberSwitch<T> = T extends number ? string : number;

let varA: StringNumberSwitch<number>;
