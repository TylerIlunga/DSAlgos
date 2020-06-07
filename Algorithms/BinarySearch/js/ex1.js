const binarySearch = (array, key) => {
  let middleIndex = Math.floor(array.length / 2);
  let middleValue = array[middleIndex];

  if (middleValue === key) {
    return true;
  } else if (middleValue < key && array.length > 1) {
    return binarySearch(array.splice(middleIndex, array.length), key);
  } else if (middleValue > key && array.length > 1) {
    return binarySearch(array.splice(0, middleIndex), key);
  } else {
    return false;
  }
};

let numbersArray = [12, 32213, 4323, 4543, 3423, 223, 11];
const result = binarySearch(numbersArray, 23);
console.log(result);

let testMap = new Map();
let john = { name: "John" };
testMap.set(john, "hash");
console.log(testMap);

let testSet = new Set(numbersArray);
testSet.add(john);
console.log(testSet);

for (var x in numbersArray) {
  console.log(x);
}

for (let y of numbersArray) {
  console.log(y);
}
