const mergeSort = (array) => {
  if (array.length < 2) return array;
  let middleIndex = Math.floor(array.length / 2);
  let firstHalf = array.slice(0, middleIndex);
  let secondHalf = array.slice(middleIndex);

  return merge(mergeSort(firstHalf), mergeSort(secondHalf));
};

const merge = (array1, array2) => {
  let result = [];
  while (array1.length && array2.length) {
    let minimumElement;
    if (array1[0] < array2[0]) {
      minimumElement = array1.shift();
    } else {
      minimumElement = array2.shift();
    }
    result.push(minimumElement);
  }

  if (array1.length) {
    result = result.concat(array1);
  } else {
    result = result.concat(array2);
  }
  return result;
};

let firstArray = [1, 15, 2, 4, 65, 343, 23, 55, 7, 9, 323];
let secondArray = [4, 6, 89, 45, 3434, 65, 564, 54];

let output = mergeSort(firstArray);
console.log(output);
