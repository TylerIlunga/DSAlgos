const twoSum = (array, sum) => {
  const pairs = [],
    hashTable = [];

  for (var i = 0; i < array.length; i++) {
    let currentNumber = array[i];
    let counterpart = sum - currentNumber;
    if (hashTable.indexOf(counterpart) === -1) {
      pairs.push([currentNumber, counterpart]);
    }
    hashTable.push(currentNumber);
  }
  return pairs;
};

const result = twoSum([3, 2, 4], 6);
console.log(result);

const letterNotIncluded = (str) => {
  let letters = str.toLowerCase().split("");
  let alphabet = "abcdefghijklmnopqrstuvwxyz";
  let results = [];
  for (let letter in alphabet) {
    if (!letters.includes(alphabet[letter])) {
      results.push(alphabet[letter]);
    }
  }
  return results;
};

console.log(letterNotIncluded("Hello"));
