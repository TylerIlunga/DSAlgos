const mergeSort = (array) => {
  if (array.length < 2) return array;
  let middleIndex = Math.floor(array.length / 2);
  let firstHalf = array.slice(0, middleIndex);
  let secondHalf = array.slice(middleIndex);

  return merge(mergeSort(firstHalf), mergeSort(secondHalf));
}

const merge = (array1, array2) => {
  let result = [];
  while (array1.length && array2.length) {
    let minElement;
    if (array1[0] < array2[0]) {
      minElement = array1.shift();
    } else {
      minElement = array2.shift();
    }
    result.push(minElement);
  }

  if (array1.length) {
    result = result.concat(array1);
  } else {
    result = result.concat(array2);
  }

  return result;
}

let arrayOfNumbers = [1, 8, 6, 4, 2, 9, 10, 0, 23];
let output = mergeSort(arrayOfNumbers);
console.log(output);
