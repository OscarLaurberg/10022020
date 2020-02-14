function changeText() {
    document.getElementById("txt").innerHTML = "Il Nombre del Padre";
}
document.getElementById("txt").onclick = changeText;

var divs = Array.from(document.getElementsByTagName("div"));
divs.forEach(function (div) {
    div.style.backgroundColor = "blue";
});

function changeColor() {
    document.getElementById("first").style.backgroundColor = "Red";
    document.getElementById("second").style.backgroundColor = "Yellow";
    document.getElementById("third").style.backgroundColor = "Green";
}


//a)
var boys = ["Peter", "lars", "Ole"];
var girls = ["Janne", "hanne", "Sanne"];

//b)
var all = boys.concat(girls);
console.log(all);
//c)
var commaSep = all.join(',');
console.log(commaSep);
var hyphenSep = all.join('-');
console.log(hyphenSep);

//d)
all.push("Lone", "Gitte");
console.log(all);


//e)

all.unshift("Hans", "Kurt");
console.log(all);

//f)

all.shift();
console.log(all);
//g)
all.pop();
console.log(all);
//h)
all.splice(3, 2);
console.log(all);
//i)
all.reverse();
console.log(all);
//j)
all.sort();
console.log(all);
//k)
all.sort(function (a, b) {
    return a.toLowerCase().localeCompare(b.toLowerCase());
});

console.log(all);

let map = all.map(name => name.toUpperCase());
console.log(map);

let filter = map.filter(name => name.charAt(0) == 'I' || name.charAt(0) == 'L');
console.log(filter);

//1)

function add(n1, n2) {
    return n1 + n2;
}

function mul(n1, n2) {
    return n1 * n2;
}

var sub = function (n1, n2) {
    return n1 - n2
}


var cb = function (n1, n2, callback) {
    return "Result from the two numbers: " + n1 + "+" + n2 + "=" + callback(n1, n2);
};

//2)

/*
 console.log( add(1,2) )     // What will this print? - 3
 console.log( add )          // What will it print and what does add represent? - It printed infomation about add. And that 
 console.log( add(1,2,3) ) ; // What will it print it wil print 3 and ignore the third parameter
 console.log( add(1) );	  // What will it print NaN - 1 + undefined is not a number 	
 console.log( cb(3,3,add) ); // What will it print - this will print result from the two numbers: 3 + 3 =  6.
 console.log( cb(4,3,sub) ); // What will it print - this will print result from the two numbers 4 + 3 = 1
 //console.log(cb(3,3,add())); // What will it print (and what was the problem) - It gives us an error, as add() is not a function - it's a function being called.
 console.log(cb(3,"hh",add));// What will it print the result from the two numbers 3 + hh = 3hh. the plus works as a concatinator in this case, when mixing numbers and strings.
 */


//3)


let cb1 = function (n1, n2, callback) {
    if (typeof n1 === "number" && typeof n2 === "number" && typeof callback === "function") {
        return "Result from the two numbers: " + n1 + "+" + n2 + "=" + callback(n1, n2);
    } else {
        throw new Error('Noget gik galt!!');
    }
};

 console.log(cb1(3,3,add));
 try{
 console.log(cb1("polka",3,add()));
 } catch(e){
 console.error('Noget gik galt!');
 }  


//4)
console.log(cb1(2, 5, mul));

//5)
/*
console.log(cb1(10, 2, function (n1, n2) {
    return n1 / n2;
    })
);
*/


//Getting comfortable with filter, map and forEach:

//1)


let names = ["Bjarne","Dolph","Jones","Leeroy","Torben","Kim"];


let shortNames = names.filter(name => name.length<=3);
console.log(shortNames);

//2)
let upperCaseNames = names.map(name => name.toUpperCase());
console.log(upperCaseNames);

//3)
function makeArrayAIntoUL(array){
    let listNames = array.map(name => name = `<li>${name}</li>`);
    return `<ul>${listNames.join("")}</ul>`;
}
console.log(makeArrayAIntoUL(names));


//4)
var cars = [
  { id: 1, year: 1997, make: 'Ford', model: 'E350', price: 3000 },
  { id: 2, year: 1999, make: 'Chevy', model: 'Venture', price: 4900 },
  { id: 3, year: 2000, make: 'Chevy', model: 'Venture', price: 5000 },
  { id: 4, year: 1996, make: 'Jeep', model: 'Grand Cherokee', price: 4799 },
  { id: 5, year: 2005, make: 'Volvo', model: 'V70', price: 44799 }
];

let newCars = cars.filter(car => car.year > 1999);
console.log(newCars);

let volvos = cars.filter(car => car.make == 'Volvo');
console.log(volvos);

let cheapCars = cars.filter(car => car.price < 5000);
console.log(cheapCars);

//4a)

function makeCarQuery(carArray){
let carsQuery = carArray.map(car => car = `INSERT INTO cars (id,year,make,model,price) VALUES (${car.id},${car.year},${car.make},${car.model},${car.price});`);
return carsQuery.join("\n");
}

console.log(makeCarQuery(cars));


//Asynchronous Callbacks

//Jeg forventer: a,d,f,e,b:
var msgPrinter = function(msg,delay){
  setTimeout(function(){
    console.log(msg);
  },delay);
};
/*
console.log("aaaaaaaaaa");
msgPrinter ("bbbbbbbbbb",2000);
console.log("dddddddddd");
msgPrinter ("eeeeeeeeee",1000);
console.log("ffffffffff");
*/

//1+2+3)
/*
function Person(name){
  this.name = name;
  let self = this;
  console.log("Name: "+ this.name);
  setTimeout(function(){
    console.log("Hi  "+self.name);  //Explain this
  },2000);
}
//call it like this (do it, even if you know it’s silly ;-)
Person("Kurt Wonnegut");  //This calls the function
console.log("I'm global: "+ name);  //Explain this

let p = new Person("Kurt Wonnegut");  //Create an instance using the constructor function
console.log("I'm global: "+ name);  //What’s different ?

*/

//4)

let greeter = function(){
    console.log(this.message);
};

let comp1 = { message: "Hello World" };
let comp2 = { message: "Hi"};

let g1 = greeter.bind(comp1);
let g2 = greeter.bind(comp2);
 
setTimeout(g1,500);
setTimeout(g2,1000);







