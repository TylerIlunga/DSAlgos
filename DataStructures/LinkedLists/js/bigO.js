//Used to classify how scaleable, or functional, an algorithm is
//Computes an algorithm runtime output depending on the size of the input

let a = [
  "alpha", "beta", "charlie", "delta",
  "echo", "foxtrot", "golf", "hotel",
  "india", "juliet", "kilo", "mike",
  "november", "oscar", "papa", "quebec",
  "romeo", "sierra", "tango", "uniform",
  "victor", "whiskey", "x-ray", "yankee", "zulu"
]

//?Constant runtime: "O (1)"

const constant = (array) => {
  console.log(array[0]);
  console.log(array[1]);
}

console.log('Constant:\n');
constant(a);

//Linear runtime:  "O (n)"

const linear = (array) => {
  const logic = i => console.log(i);

  array.forEach(logic)
}

console.log('\nLinear: \n');
linear(a);

//Exponential runtime: "O (n^2)"

const exponential = (array) => {
  for (var i = 0; i < array.length; i++) {
    for (var j = 0; j < array.length; j++) {
      console.log(array[i] + array[j]);
    }
  }
}

console.log('\nExponential: ');
exponential(a);

//Logrithmic runtime: "O (log n)"

const binary = (array, key) => {
  let low = 0;
  let high = array.length - 1;
  let mid;
  let element;

  while (low <= high) {
    mid = Math.floor((low + high) / 2, 10);
    element = array[mid];

    if (element < key) {
      low = mid + 1;
    } else if (element > key) {
      high = mid - 1;
    } else {
      console.log(`${array[mid]} was found`);
      return
    }
  }

  console.log(`${key} not found`);
}

binary(a, "oscar")
